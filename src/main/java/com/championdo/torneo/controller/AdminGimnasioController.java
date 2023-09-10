package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminGimnasio")
public class AdminGimnasioController {

    @Autowired
    private GimnasioService gimnasioService;

    @Autowired
    private UserService userService;

    @GetMapping("/gimnasioList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView gimnasioList(ModelAndView modelAndView) {
        modelAndView.setViewName("adminGimnasio");
        modelAndView.addObject("gimnasioModel", new GimnasioModel());
        modelAndView.addObject("gimnasioList", gimnasioService.findAll());
        LoggerMapper.log(Level.INFO, "gimnasioList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/gimnasio/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragGimnasio(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        gimnasioService.dragOfPosition(oldIndex, newIndex);
        return gimnasioList(modelAndView);
    }

    @PostMapping("/addGimnasio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addGimnasio(ModelAndView modelAndView, @ModelAttribute("gimnasioModel") GimnasioModel gimnasioModel) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        gimnasioModel.setCodigoGimnasio(user.getCodigoGimnasio());
        gimnasioModel.setPosition(gimnasioService.findMaxPosition() + 1);
        gimnasioService.add(gimnasioModel);
        return gimnasioList(modelAndView);
    }

    @GetMapping("/gimnasio/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeGimnasio(ModelAndView modelAndView, @PathVariable int id) {
        gimnasioService.delete(id);
        return gimnasioList(modelAndView);
    }

}
