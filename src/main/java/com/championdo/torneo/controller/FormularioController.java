package com.championdo.torneo.controller;

import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
@RequestMapping("/formulario")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @Autowired
    private CalidadService calidadService;

    @Autowired
    private CinturonService cinturonService;

    @Autowired
    private GimnasioService gimnasioService;

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private UserService userService;

    @Autowired
    private PrincipalController principalController;

    @GetMapping("/propia")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView propia(ModelAndView modelAndView) {
        modelAndView.setViewName("formularioInscPropia");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("userModel", formularioService.formularioInscPropia(usuario));
        cargarDesplegables(modelAndView);
        LoggerMapper.log(Level.INFO, "formulario/propia", modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/gaurdarPropia")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView gaurdarPropia(@ModelAttribute("userModel") UserModel userModel) {
        ModelAndView modelAndView = new ModelAndView();
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.setViewName("formularioInscFinalizada");
        PdfModel pdfModel = null;
        try {
            formularioService.fillObjects(userModel);
            pdfModel = formularioService.getPdf(new UserAutorizacionModel(userModel));
            InscripcionModel inscripcionModel = inscripcionService.addPropia(userModel);
            pdfModel.setIdInscripcion(inscripcionModel.getId());
            pdfModel.setCategoria(inscripcionModel.getCategoria().getNombre());
            pdfModel.setPoomsae(inscripcionModel.getCategoria().getPoomsae().getNombre());
            pdfService.generarPdf(pdfModel);
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR,"formulario/gaurdarPropia", e.getMessage(), getClass());
            pdfModel = null;
            modelAndView.addObject("inscripcionError", "inscripcionError");
            modelAndView.addObject("inscripcionCorrecta", "");
        }
        if (pdfModel != null) {
            modelAndView.addObject("inscripcionCorrecta", "inscripcionCorrecta");
            modelAndView.addObject("inscripcionError", "");
            modelAndView.addObject("pdfModel", pdfModel);
        }
        LoggerMapper.log(Level.INFO, "formulario/gaurdarPropia", pdfModel, getClass());
        return modelAndView;
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public void descargarPdf(@ModelAttribute("pdfModel") PdfModel pdfModel, HttpServletResponse response) {
        pdfService.descargarPdf(pdfModel, response);
        LoggerMapper.log(Level.INFO, "formulario/descargarPdf", "Descarga de documento correcta", getClass());
    }

    @GetMapping("/inscripcionPropia/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView inscripcionPropia(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("vistaInscPropia");
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("inscripcion", inscripcionService.findById(id));
        LoggerMapper.log(Level.INFO, "formulario/inscripcionPropia", modelAndView, getClass());
        return modelAndView;
    }


    @GetMapping("/menorOInclisivo/{menor}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView menorOInclisivo(ModelAndView modelAndView, @PathVariable boolean menor) {
        if (menor) {
            modelAndView.setViewName("formularioInscMenor");
        } else {
            modelAndView.setViewName("formularioInscInclusivo");
        }
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(usuario, menor));
        cargarDesplegables(modelAndView);
        LoggerMapper.log(Level.INFO, "formulario/menorOInclisivo/" + menor, modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/guardarMenorOInclisivo")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView guardarMenorOInclisivo(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {
        ModelAndView modelAndView = new ModelAndView();
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.setViewName("formularioInscFinalizada");
        PdfModel pdfModel = null;
        try {
            formularioService.fillObjects(userAutorizacionModel.getAutorizado());
            pdfModel = formularioService.getPdf(userAutorizacionModel);
            InscripcionModel inscripcionModel = inscripcionService.addMenorOInclusivo(userAutorizacionModel);
            pdfModel.setIdInscripcion(inscripcionModel.getId());
            pdfModel.setCategoria(inscripcionModel.getCategoria().getNombre());
            pdfModel.setPoomsae(inscripcionModel.getCategoria().getPoomsae().getNombre());
            pdfService.generarPdf(pdfModel);
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR,"formulario/gaurdarPropia", e.getMessage(), getClass());
            pdfModel = null;
            modelAndView.addObject("inscripcionError", "inscripcionError");
            modelAndView.addObject("inscripcionCorrecta", "");
        }
        if (pdfModel != null) {
            modelAndView.addObject("inscripcionCorrecta", "inscripcionCorrecta");
            modelAndView.addObject("inscripcionError", "");
            modelAndView.addObject("pdfModel", pdfModel);
        }
        LoggerMapper.log(Level.INFO, "formulario/guardarMenorOInclisivo", pdfModel, getClass());
        return modelAndView;

    }

    private void cargarDesplegables(ModelAndView modelAndView) {
        modelAndView.addObject("listaSexo", Arrays.asList("Masculino","Femenino"));
        modelAndView.addObject("listaMenorConKicho", Arrays.asList("Poomsae","Kicho"));
        modelAndView.addObject("listaPaises", paisService.findAll());
        modelAndView.addObject("listaGimnasios", gimnasioService.findAll());
        modelAndView.addObject("listaCinturones", cinturonService.findAll());
        modelAndView.addObject("listaCalidad", calidadService.findAll());
    }

}
