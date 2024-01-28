package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.CategoriaModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminCategoria")
public class AdminCategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CinturonService cinturonService;
    @Autowired
    private PoomsaeService poomsaeService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private SessionData sessionData;

    @GetMapping("/categoriaList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView categoriaList(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminCategoria/categoriaList");
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminCategoria/categoriaList");
        modelAndView.setViewName("torneo/adminCategoria");
        modelAndView.addObject("categoriaModel", new CategoriaModel());
        modelAndView.addObject("categoriaList", categoriaService.findAllNameExtended(sessionData.getGimnasioModel().getId()));
        modelAndView.addObject("cinturonList", cinturonService.findAll(sessionData.getGimnasioModel().getId()));
        modelAndView.addObject("poomsaeList", poomsaeService.findAll(sessionData.getGimnasioModel().getId()));
        modelAndView.addObject("gimnasio", sessionData.getGimnasioModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/categoria/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragCategoria(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminCategoria/categoria/" + oldIndex + "/" + newIndex);
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminCategoria/categoria/" + oldIndex + "/" + newIndex);
        categoriaService.dragOfPosition(sessionData.getGimnasioModel().getId(), oldIndex, newIndex);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return categoriaList(modelAndView);
    }

    @PostMapping("/addCategoria")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addCategoria(ModelAndView modelAndView, @ModelAttribute("categoriaModel") CategoriaModel categoriaModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminCategoria/addCategoria");
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminCategoria/addCategoria");
        categoriaModel.setCodigoGimnasio(sessionData.getGimnasioModel().getId());
        categoriaModel.setPosition(categoriaService.findMaxPosition(sessionData.getGimnasioModel().getId()) + 1);
        categoriaModel.setCinturonInicio(cinturonService.findById(categoriaModel.getCinturonInicio().getId()));
        categoriaModel.setCinturonFin(cinturonService.findById(categoriaModel.getCinturonFin().getId()));
        categoriaService.add(categoriaModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return categoriaList(modelAndView);
    }

    @GetMapping("/categoria/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeCategoria(ModelAndView modelAndView, @PathVariable int id) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminCategoria/remove/" + id);
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminCategoria/remove/" + id);
        categoriaService.delete(id);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return categoriaList(modelAndView);
    }

}
