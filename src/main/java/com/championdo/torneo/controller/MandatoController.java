package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.MandatoModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.GimnasioRootService;
import com.championdo.torneo.service.InscripcionTaekwondoService;
import com.championdo.torneo.service.MandatoService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mandato")
public class MandatoController {

    @Autowired
    private MandatoService mandatoService;
    @Autowired
    private GimnasioRootService gimnasioRootService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;
    @Autowired
    private UserService userService;

    @GetMapping("/adulto")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView adulto(ModelAndView modelAndView) {
        modelAndView.setViewName("formularioMandatoAdulto");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("mandatoModel", new MandatoModel());
        LoggerMapper.methodOut(Level.INFO, "adulto", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/menor/{menor}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView menorOInclisivo(ModelAndView modelAndView, @PathVariable boolean menor) {
        modelAndView.setViewName("formularioMandatoMenor");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        if (menor) {
            modelAndView.addObject("titulo", "Mandato para licencia menor de edad");
        } else {
            modelAndView.addObject("titulo", "Mandato para licencia inclusiva");
        }
        modelAndView.addObject("mandatoModel", new MandatoModel());
        LoggerMapper.methodOut(Level.INFO, "menorOInclisivo", modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/gaurdarAdulto")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView gaurdarAdulto(ModelAndView modelAndView, @ModelAttribute("mandatoModel") MandatoModel mandatoModel) {

        LoggerMapper.log(Level.INFO, "ENTRADA gaurdarAdulto", mandatoModel, getClass());
        User user = userService.cargarUsuarioCompleto(modelAndView);

        //TODO DAMIAN hacer lógica mandato

        LoggerMapper.methodOut(Level.INFO, "gaurdarAdulto", modelAndView, getClass());
        return modelAndView;
    }

}
