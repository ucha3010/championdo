package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.CalidadModel;
import com.championdo.torneo.service.CalidadService;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
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
    private PrincipalService principalService;
    @Autowired
    private SeguridadService seguridadService;

    @GetMapping("/calidadList")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView calidadList(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.roleValidation(user.getUsername(), Constantes.ROLE_ROOT, "/adminCalidad/calidadList");
        modelAndView.setViewName("management/adminCalidad");
        modelAndView.addObject("calidadModel", new CalidadModel());
        modelAndView.addObject("calidadList", calidadService.findAll());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/calidad/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView dragCalidad(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        calidadService.dragOfPosition(oldIndex, newIndex);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return calidadList(modelAndView);
    }

    @PostMapping("/addCalidad")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView addCalidad(ModelAndView modelAndView, @ModelAttribute("calidadModel") CalidadModel calidadModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.roleValidation(user.getUsername(), Constantes.ROLE_ROOT, "/adminCalidad/addCalidad");
        calidadModel.setPosition(calidadService.findMaxPosition() + 1);
        calidadService.add(calidadModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return calidadList(modelAndView);
    }

    @GetMapping("/calidad/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView removeCalidad(ModelAndView modelAndView, @PathVariable int id) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.roleValidation(user.getUsername(), Constantes.ROLE_ROOT, "/adminCalidad/remove/" + id);
        calidadService.delete(id);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return calidadList(modelAndView);
    }

}
