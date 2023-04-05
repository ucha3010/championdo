package com.championdo.torneo.controller;

import com.championdo.torneo.mapper.MapperUser;
import com.championdo.torneo.model.*;
import com.championdo.torneo.service.*;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping("/gimnasio")
public class GimnasioController {

    @Autowired
    private FormularioService formularioService;

    @Autowired
    private CalidadService calidadService;

    @Autowired
    private CinturonService cinturonService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private GimnasioService gimnasioService;

    @Autowired
    private InscripcionService inscripcionService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PrincipalController principalController;

    @GetMapping("/tipoInscripcion")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tipoInscripcion(ModelAndView modelAndView) {
        modelAndView.setViewName("gimnasio/formularioTipoInscripcion");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        LoggerMapper.log(Level.INFO, "gimnasio/tipoInscripcion", modelAndView, getClass());
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
        LoggerMapper.log(Level.INFO, "gimnasio/formularioInscripcion/" + tipo + "/" + licencia, modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/gaurdarPropia")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView gaurdarPropia(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {

        LoggerMapper.log(Level.INFO, "ENTRADA gaurdarPropia", userAutorizacionModel, getClass());
        ModelAndView modelAndView = new ModelAndView();
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.setViewName("formularioInscFinalizada");
        PdfModel pdfModel = new PdfModel();
        try {
            formularioService.fillObjects(userAutorizacionModel.getMayorAutorizador());

            // TODO DAMIAN modificar a partir de acá
/*            UserModel userModel = userAutorizacionModel.getMayorAutorizador();
            pdfModel = formularioService.getPdf(new UserAutorizacionModel(userModel));*/
            InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.add(userAutorizacionModel);
/*            pdfModel.setIdInscripcion(inscripcionModel.getId());
            pdfModel.setCategoria(inscripcionModel.getCategoria());
            pdfModel.setPoomsae(inscripcionModel.getPoomsae());
            File file = pdfService.generarPdf(pdfModel);
            emailService.sendConfirmation(userModel, file);*/
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
        LoggerMapper.log(Level.INFO, "gimnasio/gaurdarPropia", pdfModel, getClass());
        return modelAndView;
    }

    /*@GetMapping("/getPropia/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getPropia(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("vistaInscPropia");
        userService.cargarUsuarioCompleto(modelAndView);
        InscripcionModel inscripcionModel = inscripcionService.findById(id);
        modelAndView.addObject("inscripcion", inscripcionModel);
        modelAndView.addObject("pdfModel", pdfService.getImpresion(inscripcionModel));
        LoggerMapper.log(Level.INFO, "formulario/getPropia", modelAndView, getClass());
        return modelAndView;
    }


    @GetMapping("/menorOInclisivo/{menor}")
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

        LoggerMapper.log(Level.INFO, "ENTRADA guardarMenor", userAutorizacionModel, getClass());
        ModelAndView modelAndView = new ModelAndView();
        userService.cargarUsuarioCompleto(modelAndView);

        //1 evaluar qué tengo que guardar HECHO
        //2 evaluar dónde guardar la información HECHO
        //3 evaluar documentos a generar


        modelAndView.setViewName("formularioInscFinalizada");
        PdfModel pdfModelMandato = null;
        try {
            formularioService.fillObjects(userAutorizacionModel.getAutorizado());
            formularioService.fillObjects(userAutorizacionModel.getMayorAutorizador());

            if (userAutorizacionModel.getAutorizado().isLicencia()) {
                InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.add(userAutorizacionModel);
                pdfModelMandato = formularioService.getPdfMandato(userAutorizacionModel);
                pdfModelMandato.setIdInscripcion(inscripcionTaekwondoModel.getId());
                //TODO DAMIAN generar el documento mandato. Los datos están cargados en pdfModelMandato
                //luego hacer lo mismo con los otros documentos
//            File file = pdfService.generarPdf(pdfModel);
            }
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR,"formulario/gaurdarPropia", e.getMessage(), getClass());
            pdfModelMandato = null;
            modelAndView.addObject("inscripcionError", "inscripcionError");
            modelAndView.addObject("inscripcionCorrecta", "");
        }

        //TODO DAMIAN estos tres pasos luego deben cambiar
        modelAndView.addObject("inscripcionCorrecta", "inscripcionCorrecta");
        modelAndView.addObject("inscripcionError", "");
        modelAndView.addObject("pdfModel", pdfModelMandato);

        LoggerMapper.log(Level.INFO, "gimnasio/guardarMenor", pdfModelMandato, getClass());
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

}
