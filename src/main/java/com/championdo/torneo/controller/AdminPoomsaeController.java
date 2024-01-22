package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.PoomsaeModel;
import com.championdo.torneo.service.PoomsaeService;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminPoomsae")
public class AdminPoomsaeController {

    @Autowired
    private PoomsaeService poomsaeService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private SessionData sessionData;

    @GetMapping("/poomsaeList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView poomsaeList(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminPoomsae/poomsaeList");
        modelAndView.setViewName("torneo/adminPoomsae");
        modelAndView.addObject("poomsaeModel", new PoomsaeModel());
        modelAndView.addObject("poomsaeList", poomsaeService.findAll(sessionData.getGimnasioModel().getId()));
        modelAndView.addObject("gimnasio", sessionData.getGimnasioModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/poomsae/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragPoomsae(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminPoomsae/poomsae/" + oldIndex + "/" + newIndex);
        poomsaeService.dragOfPosition(sessionData.getGimnasioModel().getId(), oldIndex, newIndex);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return poomsaeList(modelAndView);
    }

    @PostMapping("/addPoomsae")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addPoomsae(ModelAndView modelAndView, @ModelAttribute("poomsaeModel") PoomsaeModel poomsaeModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminPoomsae/addPoomsae");
        poomsaeModel.setCodigoGimnasio(sessionData.getGimnasioModel().getId());
        poomsaeModel.setPosition(poomsaeService.findMaxPosition(sessionData.getGimnasioModel().getId()) + 1);
        poomsaeService.add(poomsaeModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return poomsaeList(modelAndView);
    }

    @GetMapping("/poomsae/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removePoomsae(ModelAndView modelAndView, @PathVariable int id) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminPoomsae/poomsae/remove/" + id);
        try {
            poomsaeService.delete(id);
        } catch (RemoveException re) {
            modelAndView.addObject("removeProblem", re.getMessage());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return poomsaeList(modelAndView);
    }

}
