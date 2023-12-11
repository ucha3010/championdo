package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.TorneoModel;
import com.championdo.torneo.service.InscripcionService;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.service.TorneoGimnasioService;
import com.championdo.torneo.service.TorneoService;
import com.championdo.torneo.service.impl.UserService;
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
    private UserService userService;

    @GetMapping("/torneoList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView torneoList(ModelAndView modelAndView) {
        modelAndView.setViewName("torneo/adminTorneoList");
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminTorneo/torneoList");
        modelAndView.addObject("torneoList", torneoService.findAll(user.getCodigoGimnasio()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/torneo/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getTorneo(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("torneo/adminTorneo");
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminTorneo/torneo/" + id);
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
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminTorneo/addTorneo");
        torneoModel.setCodigoGimnasio(user.getCodigoGimnasio());
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
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminTorneo/torneo/remove/" + id);
        try {
            torneoService.delete(id);
        } catch (RemoveException re) {
            modelAndView.addObject("removeProblem", re.getMessage());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return torneoList(modelAndView);
    }

}
