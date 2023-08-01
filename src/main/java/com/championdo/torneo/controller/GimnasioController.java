package com.championdo.torneo.controller;

import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/gimnasio")
public class GimnasioController {

    @Autowired
    private FormularioService formularioService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private UserService userService;

    @GetMapping("/tipoInscripcion")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tipoInscripcion(ModelAndView modelAndView) {
        modelAndView.setViewName("gimnasio/formularioTipoInscripcion");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = inscripcionTaekwondoService.findByMayorDni(usuario.getUsername());
        modelAndView.addObject("inscripcion", inscripcionTaekwondoModelList);
        LoggerMapper.methodOut(Level.INFO, "gimnasio/tipoInscripcion", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/formularioInscripcion/{tipo}/{licencia}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView formularioInscripcion(ModelAndView modelAndView, @PathVariable String tipo, @PathVariable String licencia) {
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        if ("infantil".equalsIgnoreCase(tipo)){
            modelAndView.setViewName("gimnasio/formularioInscMenor");
            modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(usuario, true));
        } else {
            modelAndView.setViewName("gimnasio/formularioInscPropia");
            modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscPropiaGimnasio(usuario));
        }
        modelAndView.addObject("licencia","con licencia".equals(licencia));
        formularioService.cargarDesplegables(modelAndView);
        LoggerMapper.methodOut(Level.INFO, "gimnasio/formularioInscripcion/" + tipo + "/" + licencia, modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/gaurdarPropia")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView gaurdarPropia(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {

        LoggerMapper.methodIn(Level.INFO, "ENTRADA gaurdarPropia", userAutorizacionModel, getClass());
        ModelAndView modelAndView = new ModelAndView();
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.setViewName("formularioInscFinalizada"); //TODO DAMIAN debería ir a una página de firma con una clave enviada al email y acceso a los PDFs
        PdfModel pdfModelGeneral = null;
        try {
            formularioService.fillObjects(userAutorizacionModel.getMayorAutorizador());
            InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.add(userAutorizacionModel);
            pdfModelGeneral = formularioService.getPdfModelGeneral(userAutorizacionModel);
            pdfModelGeneral.setIdInscripcion(inscripcionTaekwondoModel.getId());
            if (userAutorizacionModel.getMayorAutorizador().isLicencia()) {
                File pdfMandato = pdfService.generarPdfMandato(pdfModelGeneral);
            }
            File pdfAutorizacionMayor18 = pdfService.generarPdfAutorizacionMayor18(pdfModelGeneral);
            if (userAutorizacionModel.getMayorAutorizador().isDomiciliacion()) {
                File pdfNormativaSEPA = pdfService.generarPdfNormativaSEPA(pdfModelGeneral);
            }
            if (userAutorizacionModel.getMayorAutorizador().isAutorizaWhatsApp()) {
                //TODO DAMIAN hacer pdf WhatsApp (habrá que hacer un checkbox en formularioInscPropia)
            }
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR,"gimnasio/gaurdarPropia", e.getMessage(), getClass());
        }

        if (pdfModelGeneral != null) {
            modelAndView.addObject("inscripcionCorrecta", "inscripcionCorrecta");
            modelAndView.addObject("inscripcionError", "");
        } else {
            pdfModelGeneral = new PdfModel();
            modelAndView.addObject("inscripcionCorrecta", "");
            modelAndView.addObject("inscripcionError", "inscripcionError");
        }
        modelAndView.addObject("pdfModel", pdfModelGeneral);
        LoggerMapper.methodOut(Level.INFO, "gimnasio/gaurdarPropia", pdfModelGeneral, getClass());
        return modelAndView;
    }

    @GetMapping("/getInscripcion/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getInscripcion(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("tengoquehacer"); //TODO DAMIAN hacer html
        userService.cargarUsuarioCompleto(modelAndView);
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(id);
        modelAndView.addObject("inscripcion", inscripcionTaekwondoModel);
//        modelAndView.addObject("pdfModel", pdfService.getImpresion(inscripcionTaekwondoModel));
        LoggerMapper.methodOut(Level.INFO, "gimnasio/getInscripcion", modelAndView, getClass());
        return modelAndView;
    }


    /*@GetMapping("/menorOInclisivo/{menor}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView menorOInclisivo(ModelAndView modelAndView, @PathVariable boolean menor) {
        if (menor) {
            modelAndView.setViewName("formularioInscMenor");
        } else {
            modelAndView.setViewName("formularioInscInclusivo");
        }
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(usuario, menor));
        formularioService.cargarDesplegables(modelAndView);
        LoggerMapper.log(Level.INFO, "formulario/menorOInclisivo/" + menor, modelAndView, getClass());
        return modelAndView;
    }*/

    @PostMapping("/guardarMenor")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView guardarMenor(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {

        LoggerMapper.methodIn(Level.INFO, "gimnasio/guardarMenor", userAutorizacionModel, getClass());
        ModelAndView modelAndView = new ModelAndView();
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.setViewName("formularioInscFinalizada"); //TODO DAMIAN debería ir a una página de firma con una clave enviada al email y acceso a los PDFs
        PdfModel pdfModelGeneral = null;
        try {
            formularioService.fillObjects(userAutorizacionModel.getAutorizado());
            formularioService.fillObjects(userAutorizacionModel.getMayorAutorizador());
            InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.add(userAutorizacionModel);
            pdfModelGeneral = formularioService.getPdfModelGeneral(userAutorizacionModel);
            pdfModelGeneral.setIdInscripcion(inscripcionTaekwondoModel.getId());
            if (userAutorizacionModel.getAutorizado().isLicencia()) {
                File pdfMandato = pdfService.generarPdfMandato(pdfModelGeneral);
                //emailService.sendConfirmation(userAutorizacionModel.getAutorizado(), file);
            }
            File pdfAutorizacionMayor18 = pdfService.generarPdfAutorizacionMenor18(pdfModelGeneral);
            if (userAutorizacionModel.getMayorAutorizador().isDomiciliacion()) {
                File pdfNormativaSEPA = pdfService.generarPdfNormativaSEPA(pdfModelGeneral);
            }
            if (userAutorizacionModel.getMayorAutorizador().isAutorizaWhatsApp()) {
                //TODO Damián hacer pdf WhatsApp (habrá que hacer un checkbox en formularioInscPropia)
            }
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR,"gimnasio/guardarMenor", e.getMessage(), getClass());
        }

        if (pdfModelGeneral != null) {
            modelAndView.addObject("inscripcionCorrecta", "inscripcionCorrecta");
            modelAndView.addObject("inscripcionError", "");
        } else {
            pdfModelGeneral = new PdfModel();
            modelAndView.addObject("inscripcionCorrecta", "");
            modelAndView.addObject("inscripcionError", "inscripcionError");
        }
        modelAndView.addObject("pdfModel", pdfModelGeneral);

        LoggerMapper.methodOut(Level.INFO, "gimnasio/guardarMenor", pdfModelGeneral, getClass());
        return modelAndView;

    }
/*
    @GetMapping("/getMenorOInclisivo/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getMenorOInclisivo(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("vistaInscMenorOInclisivo");
        userService.cargarUsuarioCompleto(modelAndView);
        InscripcionModel inscripcionModel = inscripcionService.findById(id);
        modelAndView.addObject("inscripcion", inscripcionModel);
        modelAndView.addObject("pdfModel", pdfService.getImpresion(inscripcionModel));
        LoggerMapper.log(Level.INFO, "formulario/getMenorOInclisivo", modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("isAuthenticated()")
    public void descargarPdf(@ModelAttribute("pdfModel") PdfModel pdfModel, HttpServletResponse response) {
        pdfService.descargarPdf(pdfModel, response);
        LoggerMapper.log(Level.INFO, "formulario/descargarPdf", "Descarga de documento correcta", getClass());
    }

    @GetMapping("/alta")
    @PreAuthorize("permitAll()")
    public ModelAndView getAlta(ModelAndView modelAndView) {
        modelAndView.setViewName("formularioAlta");
        formularioService.cargarDesplegables(modelAndView);
        if (modelAndView.getModel() == null || modelAndView.isEmpty() || !modelAndView.getModel().containsKey("userModel")) {
            modelAndView.addObject("userModel", new UserModel());
        }
        return modelAndView;
    }

    @PostMapping("/alta")
    @PreAuthorize("permitAll()")
    public ModelAndView alta(ModelAndView modelAndView, @ModelAttribute("userModel") UserModel userModel) {
        if(userService.findByUsername(userModel.getUsername()) == null) {
            try {
                userService.altaNuevoUsuario(userModel, "ROLE_USER");
                modelAndView.addObject("altaUsuarioOK", userModel.getName() + " te has dado de alta correctamente");
                modelAndView.setViewName(Constantes.LOGIN);
                return modelAndView;
            } catch (PersistenceException e) {
                modelAndView.addObject("problemasAlta", "Problemas dando de alta usuario con DNI " + userModel.getUsername());
            }
        } else {
            modelAndView.addObject("dniDadoDeAlta", "Ya existe un usuario dado de alta con DNI " + userModel.getUsername());
        }
        modelAndView.setViewName("formularioAlta");
        modelAndView.addObject("userModel", userModel);
        formularioService.cargarDesplegables(modelAndView);
        return modelAndView;

    }*/

    @GetMapping("/eliminarInscripcion/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView eliminarInscripcion(ModelAndView modelAndView, @PathVariable int id) {
        inscripcionTaekwondoService.delete(id);
        return tipoInscripcion(modelAndView);
    }

}
