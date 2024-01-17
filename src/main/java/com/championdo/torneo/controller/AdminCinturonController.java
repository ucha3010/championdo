package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.PositionException;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.CinturonModel;
import com.championdo.torneo.service.CinturonService;
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
@RequestMapping("/adminCinturon")
public class AdminCinturonController {

    @Autowired
    private CinturonService cinturonService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private SessionData sessionData;

    @GetMapping("/cinturonList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView cinturonList(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminCinturon/cinturonList");
        modelAndView.setViewName("gimnasio/adminCinturon");
        modelAndView.addObject("cinturonModel", new CinturonModel());
        modelAndView.addObject("cinturonList", cinturonService.findAll(sessionData.getGimnasioRootModel().getId()));
        modelAndView.addObject("gimnasio", sessionData.getGimnasioRootModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/cinturon/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragCinturon(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminCinturon/cinturon/" + oldIndex + "/" + newIndex);
        try {
            cinturonService.verifyDragOfPositionAvailable(sessionData.getGimnasioRootModel().getId(), oldIndex, newIndex);
            cinturonService.dragOfPosition(sessionData.getGimnasioRootModel().getId(), oldIndex, newIndex);
        } catch (PositionException pe) {
            modelAndView.addObject("dragPositionProblem", pe.getMessage());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return cinturonList(modelAndView);
    }

    @PostMapping("/addCinturon")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addCinturon(ModelAndView modelAndView, @ModelAttribute("cinturonModel") CinturonModel cinturonModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminCinturon/addCinturon");
        cinturonModel.setCodigoGimnasio(sessionData.getGimnasioRootModel().getId());
        cinturonModel.setPosition(cinturonService.findMaxPosition(sessionData.getGimnasioRootModel().getId()) + 1);
        cinturonService.add(cinturonModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return cinturonList(modelAndView);
    }

    @GetMapping("/cinturon/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeCinturon(ModelAndView modelAndView, @PathVariable int id) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminCinturon/cinturon/remove/" + id);
        try {
            cinturonService.delete(id);
        } catch (RemoveException re) {
            modelAndView.addObject("removeProblem", re.getMessage());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return cinturonList(modelAndView);
    }

}
