package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.TorneoGimnasioModel;
import com.championdo.torneo.model.TorneoModel;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.service.TorneoGimnasioService;
import com.championdo.torneo.service.TorneoService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
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
    private UserService userService;

    @GetMapping("/getGimnasio/{idTorneo}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView torneoGimnasioList(ModelAndView modelAndView, @PathVariable int idTorneo) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminTorneoGimnasio/getGimnasio/" + idTorneo);
        TorneoModel torneo = torneoService.findById(idTorneo);
        modelAndView.setViewName("torneo/adminTorneoGimnasio");
        modelAndView.addObject("torneoGimnasioModel", new TorneoGimnasioModel());
        modelAndView.addObject("torneoGimnasioList", torneoGimnasioService.findAll(idTorneo));
        modelAndView.addObject("torneo", torneo);
        LoggerMapper.methodOut(Level.INFO, "torneoGimnasioList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/torneoGimnasio/{idTorneo}/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragTorneoGimnasio(ModelAndView modelAndView, @PathVariable int idTorneo, @PathVariable int oldIndex, @PathVariable int newIndex) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminTorneoGimnasio/torneoGimnasio/" + idTorneo + "/" + oldIndex + "/" + newIndex);
        torneoGimnasioService.dragOfPosition(idTorneo, oldIndex, newIndex);
        return torneoGimnasioList(modelAndView, idTorneo);
    }

    @PostMapping("/addTorneoGimnasio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addTorneoGimnasio(ModelAndView modelAndView, @ModelAttribute("torneoGimnasioModel") TorneoGimnasioModel torneoGimnasioModel) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminTorneoGimnasio/addTorneoGimnasio");
        torneoGimnasioModel.setCodigoGimnasio(user.getCodigoGimnasio());
        torneoGimnasioModel.setPosition(torneoGimnasioService.findMaxPosition(torneoGimnasioModel.getIdTorneo()) + 1);
        torneoGimnasioService.add(torneoGimnasioModel);
        return torneoGimnasioList(modelAndView, torneoGimnasioModel.getIdTorneo());
    }

    @GetMapping("/torneoGimnasio/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeTorneoGimnasio(ModelAndView modelAndView, @PathVariable int id) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminTorneoGimnasio/torneoGimnasio/remove/" + id);
        TorneoGimnasioModel torneoGimnasioModel = torneoGimnasioService.findById(id);
        try {
            torneoGimnasioService.delete(torneoGimnasioModel);
        } catch (RemoveException re) {
            modelAndView.addObject("removeProblem", re.getMessage());
        }
        return torneoGimnasioList(modelAndView, torneoGimnasioModel.getIdTorneo());
    }

}
