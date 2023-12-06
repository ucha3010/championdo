package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.MandatoModel;
import com.championdo.torneo.service.MandatoService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
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
    private UserService userService;
// TODO DAMIAN hay que agregar un campo para indicar si se abonó la licencia del mandato
    // TODO DAMIAN al crear el mandato hay que verificar si es menor o inclusivo que el DNI no se haya utilizado para esa temporada en un mandato
    // de adulto. Y al revés cuando se va a crear un mandato de adulto
    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView mandatos(ModelAndView modelAndView) {
        modelAndView.setViewName("gimnasio/adminMandatoList");
        User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("mandatoModelList", mandatoService.findAll(usuario.getCodigoGimnasio()));
        modelAndView.addObject("mandatoModel", new MandatoModel());
        LoggerMapper.methodOut(Level.INFO, "mandatos", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/mandato/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView mandato(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("gimnasio/adminMandato");
        User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("mandatoModel", mandatoService.findById(id));
        LoggerMapper.methodOut(Level.INFO, "mandato", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeMandato(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("gimnasio/adminMandato");
        User usuario = userService.cargarUsuarioCompleto(modelAndView);
        mandatoService.delete(id);
        modelAndView.addObject("deleteOK", "Mandato eliminado correctamente");
        LoggerMapper.methodOut(Level.INFO, "removeMandato", modelAndView, getClass());
        return modelAndView;
    }

}
