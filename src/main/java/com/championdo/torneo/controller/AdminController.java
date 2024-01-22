package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private SessionData sessionData;

    @GetMapping("/adminList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminList(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        //TODO DAMIAN cada gimnasio seguirá teniendo una persona responsable pero tengo que hacer una tabla nueva para poder asignar varias personas a un gimnasio o varios gimnasios a una persona
        List<GimnasioModel> gimnasioModelList = gimnasioService.findByCifNif(user.getUsername());
        if (gimnasioModelList.size() == 1) {
            sessionData.setGimnasioModel(gimnasioModelList.get(0));
            modelAndView.setViewName("gimnasio/adminList");
            modelAndView.addObject("gimnasio", gimnasioModelList.get(0));
            modelAndView.addObject("gimnasioAvailable", gimnasioService.verifyEnable(sessionData.getGimnasioModel().getId()));
        } else {
            modelAndView.setViewName("gimnasio/selectGym");
            modelAndView.addObject("gyms", gimnasioModelList);
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/adminGymList/{gymCode}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminList(ModelAndView modelAndView, @PathVariable int gymCode) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        sessionData.setGimnasioModel(gimnasioService.findById(gymCode));
        modelAndView.setViewName("gimnasio/adminList");
        modelAndView.addObject("gimnasio", sessionData.getGimnasioModel());
        modelAndView.addObject("gimnasioAvailable", gimnasioService.verifyEnable(sessionData.getGimnasioModel().getId()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

}
