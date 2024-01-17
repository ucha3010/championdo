package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.service.GimnasioRootService;
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
    private GimnasioRootService gimnasioRootService;
    @Autowired
    private SessionData sessionData;

    @GetMapping("/adminList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminList(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        List<GimnasioRootModel> gimnasioRootModelList = gimnasioRootService.findByCifNif(user.getUsername());
        if (gimnasioRootModelList.size() == 1) {
            sessionData.setGimnasioRootModel(gimnasioRootModelList.get(0));
            modelAndView.setViewName("gimnasio/adminList");
            modelAndView.addObject("gimnasio", gimnasioRootModelList.get(0));
            modelAndView.addObject("gimnasioAvailable", gimnasioRootService.verifyEnable(sessionData.getGimnasioRootModel().getId()));
        } else {
            modelAndView.setViewName("gimnasio/selectGym");
            modelAndView.addObject("gyms", gimnasioRootModelList);
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/adminGymList/{gymCode}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminList(ModelAndView modelAndView, @PathVariable int gymCode) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        sessionData.setGimnasioRootModel(gimnasioRootService.findById(gymCode));
        modelAndView.setViewName("gimnasio/adminList");
        modelAndView.addObject("gimnasio", sessionData.getGimnasioRootModel());
        modelAndView.addObject("gimnasioAvailable", gimnasioRootService.verifyEnable(sessionData.getGimnasioRootModel().getId()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

}
