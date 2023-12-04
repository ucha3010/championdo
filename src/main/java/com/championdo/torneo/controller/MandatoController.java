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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

        User user = userService.cargarUsuarioCompleto(modelAndView);
        mandatoService.fillMandato(mandatoModel, true, user.getCodigoGimnasio());
        mandatoModel = mandatoService.add(mandatoModel);

        //TODO DAMIAN MandatoModel tendrá que tener:
        // nombre, apellido1 y apellido2 de las dos personas
        // calidadDe
        // todos los datos de la dirección
        // para poder rellenar pdfModel y crear el archivo llamando a pdfService.generarPdfMandato(pdfModel)
        // haciendo antes lo que se indica en pdfService.getPdfInscripcionTaekwondo(mandatoModel) hay que crear el método

        LoggerMapper.methodOut(Level.INFO, "gaurdarAdulto", modelAndView, getClass());
        return modelAndView;
    }

}
