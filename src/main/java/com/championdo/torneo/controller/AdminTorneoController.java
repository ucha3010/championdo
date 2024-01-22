package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.TorneoModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/adminTorneo")
public class AdminTorneoController {

    @Autowired
    private TorneoService torneoService;
    @Autowired
    private TorneoGimnasioService torneoGimnasioService;
    @Autowired
    private InscripcionService inscripcionService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private SessionData sessionData;

    @GetMapping("/torneoList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView torneoList(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.setViewName("torneo/adminTorneoList");
        modelAndView.addObject("gimnasio", sessionData.getGimnasioModel());
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminTorneo/torneoList");
        modelAndView.addObject("torneoList", torneoService.findAll(sessionData.getGimnasioModel().getId()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/torneo/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getTorneo(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("torneo/adminTorneo");
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminTorneo/torneo/" + id);
        TorneoModel torneoModel = torneoService.findById(id);
        if(torneoModel.getId() == 0) {
            modelAndView.addObject("buttonAddUpdate", "Crear");
        } else {
            modelAndView.addObject("buttonAddUpdate", "Actualizar");
            modelAndView.addObject("buttonGyms",true);
            List<InscripcionModel> inscripcionList = inscripcionService.findByIdTorneo(torneoModel.getId());
            if (inscripcionList.isEmpty()) {
                modelAndView.addObject("buttonDelete",true);
            } else {
                modelAndView.addObject("inscripcionList", inscripcionList);
            }
        }
        modelAndView.addObject("torneoModel", torneoModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/addTorneo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addTorneo(ModelAndView modelAndView, @ModelAttribute("torneoModel") TorneoModel torneoModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminTorneo/addTorneo");
        torneoModel.setCodigoGimnasio(sessionData.getGimnasioModel().getId());
        if (torneoModel.getId() == 0) {
            torneoService.add(torneoModel);
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return torneoList(modelAndView);
        } else {
            torneoService.update(torneoModel);
            modelAndView.addObject("updateOK", "Actualización realizada con éxito");
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return getTorneo(modelAndView, torneoModel.getId());
        }
    }

    @GetMapping("/torneo/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeTorneo(ModelAndView modelAndView, @PathVariable int id) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminTorneo/torneo/remove/" + id);
        try {
            torneoService.delete(id);
        } catch (RemoveException re) {
            modelAndView.addObject("removeProblem", re.getMessage());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return torneoList(modelAndView);
    }

}
