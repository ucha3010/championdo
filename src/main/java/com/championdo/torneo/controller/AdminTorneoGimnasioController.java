package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.TorneoGimnasioModel;
import com.championdo.torneo.model.TorneoModel;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.service.TorneoGimnasioService;
import com.championdo.torneo.service.TorneoService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminTorneoGimnasio")
public class AdminTorneoGimnasioController {

    @Autowired
    private TorneoGimnasioService torneoGimnasioService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private TorneoService torneoService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private SessionData sessionData;

    @GetMapping("/getGimnasio/{idTorneo}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView torneoGimnasioList(ModelAndView modelAndView, @PathVariable int idTorneo) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminTorneoGimnasio/getGimnasio/" + idTorneo);
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminTorneoGimnasio/getGimnasio/" + idTorneo);
        TorneoModel torneo = torneoService.findById(idTorneo);
        modelAndView.setViewName("torneo/adminTorneoGimnasio");
        modelAndView.addObject("torneoGimnasioModel", new TorneoGimnasioModel());
        modelAndView.addObject("torneoGimnasioList", torneoGimnasioService.findAll(idTorneo));
        modelAndView.addObject("torneo", torneo);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/torneoGimnasio/{idTorneo}/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragTorneoGimnasio(ModelAndView modelAndView, @PathVariable int idTorneo, @PathVariable int oldIndex, @PathVariable int newIndex) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminTorneoGimnasio/torneoGimnasio/" + idTorneo + "/" + oldIndex + "/" + newIndex);
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminTorneoGimnasio/torneoGimnasio/" + idTorneo + "/" + oldIndex + "/" + newIndex);
        torneoGimnasioService.dragOfPosition(idTorneo, oldIndex, newIndex);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return torneoGimnasioList(modelAndView, idTorneo);
    }

    @PostMapping("/addTorneoGimnasio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addTorneoGimnasio(ModelAndView modelAndView, @ModelAttribute("torneoGimnasioModel") TorneoGimnasioModel torneoGimnasioModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminTorneoGimnasio/addTorneoGimnasio");
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminTorneoGimnasio/addTorneoGimnasio");
        torneoGimnasioModel.setCodigoGimnasio(sessionData.getGimnasioModel().getId());
        torneoGimnasioModel.setPosition(torneoGimnasioService.findMaxPosition(torneoGimnasioModel.getIdTorneo()) + 1);
        torneoGimnasioService.add(torneoGimnasioModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return torneoGimnasioList(modelAndView, torneoGimnasioModel.getIdTorneo());
    }

    @GetMapping("/torneoGimnasio/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeTorneoGimnasio(ModelAndView modelAndView, @PathVariable int id) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminTorneoGimnasio/torneoGimnasio/remove/" + id);
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminTorneoGimnasio/torneoGimnasio/remove/" + id);
        TorneoGimnasioModel torneoGimnasioModel = torneoGimnasioService.findById(id);
        try {
            torneoGimnasioService.delete(torneoGimnasioModel);
        } catch (RemoveException re) {
            modelAndView.addObject("removeProblem", re.getMessage());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return torneoGimnasioList(modelAndView, torneoGimnasioModel.getIdTorneo());
    }

}
