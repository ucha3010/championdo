package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.model.MandatoModel;
import com.championdo.torneo.service.MandatoService;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.service.SeguridadService;
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

@Controller
@RequestMapping("/adminMandato")
public class AdminMandatoController {

    @Autowired
    private MandatoService mandatoService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private SessionData sessionData;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView mandatos(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminMandato/");
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminMandato/");
        modelAndView.setViewName("gimnasio/adminMandatoList");
        modelAndView.addObject("mandatoModelList", mandatoService.findAll(sessionData.getGimnasioModel().getId()));
        modelAndView.addObject("mandatoModel", new MandatoModel());
        modelAndView.addObject("gimnasio", sessionData.getGimnasioModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/mandato/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView mandato(ModelAndView modelAndView, @PathVariable int id) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminMandato/mandato/" + id);
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminMandato/mandato/" + id);
        modelAndView.setViewName("gimnasio/adminMandato");
        modelAndView.addObject("mandatoModel", mandatoService.findById(id));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeMandato(ModelAndView modelAndView, @PathVariable int id) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminMandato/remove/" + id);
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminMandato/remove/" + id);
        mandatoService.delete(id);
        modelAndView.addObject("deleteOK", "Mandato eliminado correctamente");
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return mandatos(modelAndView);
    }

    @GetMapping("/pay/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView pay(ModelAndView modelAndView, @PathVariable int id) throws ValidationException {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminMandato/pay/" + id);
        seguridadService.usuarioGimnasioHabilitadoAdministracion(user.getUsername(), sessionData.getGimnasioModel().getId(), "/adminMandato/pay/" + id);
        MandatoModel mandatoModel = mandatoService.findById(id);
        mandatoModel.setLicenciaAbonada(!mandatoModel.isLicenciaAbonada());
        mandatoService.update(mandatoModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return mandato(modelAndView, id);
    }

}
