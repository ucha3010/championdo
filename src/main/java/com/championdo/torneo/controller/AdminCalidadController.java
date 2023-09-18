package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.CalidadModel;
import com.championdo.torneo.service.CalidadService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminCalidad")
public class AdminCalidadController {

    @Autowired
    private CalidadService calidadService;

    @Autowired
    private UserService userService;

    @GetMapping("/calidadList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView calidadList(ModelAndView modelAndView) {
        modelAndView.setViewName("adminCalidad");
        modelAndView.addObject("calidadModel", new CalidadModel());
        modelAndView.addObject("calidadList", calidadService.findAll());
        LoggerMapper.log(Level.INFO, "calidadList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/calidad/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragCalidad(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        calidadService.dragOfPosition(oldIndex, newIndex);
        return calidadList(modelAndView);
    }

    @PostMapping("/addCalidad")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addCalidad(ModelAndView modelAndView, @ModelAttribute("calidadModel") CalidadModel calidadModel) {
        calidadModel.setPosition(calidadService.findMaxPosition() + 1);
        User user = userService.cargarUsuarioCompleto(modelAndView);
        calidadService.add(calidadModel);
        return calidadList(modelAndView);
    }

    @GetMapping("/calidad/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeCalidad(ModelAndView modelAndView, @PathVariable int id) {
        calidadService.delete(id);
        return calidadList(modelAndView);
    }

}
