package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.SenderException;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/formulario")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private InscripcionService inscripcionService;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private TorneoService torneoService;
    @Autowired
    private TorneoGimnasioService torneoGimnasioService;
    @Autowired
    private UserService userService;

    @GetMapping("/selectTournament/{tournamentType}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView selectTournament(ModelAndView modelAndView, @PathVariable String tournamentType) {

        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.setViewName("torneo/formularioSeleccionTorneo");
        List<TorneoModel> torneoModelList = torneoService.findAllowedWithGyms(new Date(), tournamentType);
        if (tournamentType.isEmpty()) {
            modelAndView.addObject("errorMessage", "Problemas con la opción seleccionada");
        }
        modelAndView.addObject("torneoModelList", torneoModelList);
        LoggerMapper.methodOut(Level.INFO, "selectTournament", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/propia")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView propia(ModelAndView modelAndView) {
        modelAndView.setViewName("torneo/formularioInscPropia");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("userModel", formularioService.formularioInscPropia(usuario));
        formularioService.cargarDesplegables(modelAndView, usuario.getCodigoGimnasio());
        LoggerMapper.log(Level.INFO, "propia", modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/gaurdarPropia")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView gaurdarPropia(@ModelAttribute("userModel") UserModel userModel) {

        LoggerMapper.log(Level.INFO, "ENTRADA gaurdarPropia", userModel, getClass());
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.cargarUsuarioCompleto(modelAndView);
        userModel.setCodigoGimnasio(user.getCodigoGimnasio());
        modelAndView.setViewName("torneo/formularioInscFinalizada");
        PdfModel pdfModel;
        try {
            formularioService.fillObjects(userModel);
            pdfModel = formularioService.getPdfModelTorneo(new UserAutorizacionModel(userModel));
            InscripcionModel inscripcionModel = inscripcionService.addPropia(userModel);
            pdfModel.setIdInscripcion(inscripcionModel.getId());
            pdfModel.setCategoria(inscripcionModel.getCategoria());
            pdfModel.setPoomsae(inscripcionModel.getPoomsae());
            File file = pdfService.generarPdfTorneo(pdfModel);
            emailService.sendTournamentRegistration(userModel, file);
            emailService.confirmAdminTournamentRegistration(new UserAutorizacionModel(userModel));
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR,"gaurdarPropia", e.getMessage(), getClass());
            pdfModel = null;
            modelAndView.addObject("inscripcionError", "inscripcionError");
            modelAndView.addObject("inscripcionCorrecta", "");
        }
        if (pdfModel != null) {
            modelAndView.addObject("inscripcionCorrecta", "inscripcionCorrecta");
            modelAndView.addObject("inscripcionError", "");
            modelAndView.addObject("pdfModel", pdfModel);
        }
        LoggerMapper.log(Level.INFO, "gaurdarPropia", pdfModel, getClass());
        return modelAndView;
    }

    @GetMapping("/getPropia/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getPropia(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("torneo/vistaInscPropia");
        userService.cargarUsuarioCompleto(modelAndView);
        InscripcionModel inscripcionModel = inscripcionService.findById(id);
        modelAndView.addObject("inscripcion", inscripcionModel);
        modelAndView.addObject("pdfModel", pdfService.getImpresion(inscripcionModel));
        LoggerMapper.log(Level.INFO, "getPropia", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/menorOInclisivo/{menor}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView menorOInclisivo(ModelAndView modelAndView, @PathVariable boolean menor) {
        if (menor) {
            modelAndView.setViewName("torneo/formularioInscMenor");
        } else {
            modelAndView.setViewName("torneo/formularioInscInclusivo");
        }
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(usuario, menor));
        formularioService.cargarDesplegables(modelAndView, usuario.getCodigoGimnasio());
        LoggerMapper.log(Level.INFO, "menorOInclisivo/" + menor, modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/guardarMenorOInclisivo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView guardarMenorOInclisivo(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.cargarUsuarioCompleto(modelAndView);
        userAutorizacionModel.getAutorizado().setCodigoGimnasio(user.getCodigoGimnasio());
        userAutorizacionModel.getMayorAutorizador().setCodigoGimnasio(user.getCodigoGimnasio());
        modelAndView.setViewName("torneo/formularioInscFinalizada");
        PdfModel pdfModel;
        try {
            formularioService.fillObjects(userAutorizacionModel.getAutorizado());
            pdfModel = formularioService.getPdfModelTorneo(userAutorizacionModel);
            InscripcionModel inscripcionModel = inscripcionService.addMenorOInclusivo(userAutorizacionModel);
            pdfModel.setIdInscripcion(inscripcionModel.getId());
            pdfModel.setCategoria(inscripcionModel.getCategoria());
            pdfModel.setPoomsae(inscripcionModel.getPoomsae());
            File file = pdfService.generarPdfTorneo(pdfModel);
            emailService.sendTournamentRegistration(userAutorizacionModel.getMayorAutorizador(), file);
            emailService.confirmAdminTournamentRegistration(userAutorizacionModel);
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR,"gaurdarPropia", e.getMessage(), getClass());
            pdfModel = null;
            modelAndView.addObject("inscripcionError", "inscripcionError");
            modelAndView.addObject("inscripcionCorrecta", "");
        }
        if (pdfModel != null) {
            modelAndView.addObject("inscripcionCorrecta", "inscripcionCorrecta");
            modelAndView.addObject("inscripcionError", "");
            modelAndView.addObject("pdfModel", pdfModel);
        }
        LoggerMapper.log(Level.INFO, "guardarMenorOInclisivo", pdfModel, getClass());
        return modelAndView;

    }

    @GetMapping("/getMenorOInclisivo/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getMenorOInclisivo(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("torneo/vistaInscMenorOInclisivo");
        userService.cargarUsuarioCompleto(modelAndView);
        InscripcionModel inscripcionModel = inscripcionService.findById(id);
        modelAndView.addObject("inscripcion", inscripcionModel);
        modelAndView.addObject("pdfModel", pdfService.getImpresion(inscripcionModel));
        LoggerMapper.log(Level.INFO, "getMenorOInclisivo", modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("isAuthenticated()")
    public void descargarPdf(@ModelAttribute("pdfModel") PdfModel pdfModel, HttpServletResponse response) {
        pdfService.descargarArchivo(pdfModel, response, Constantes.SECCION_TORNEO);
        LoggerMapper.log(Level.INFO, "descargarPdf", "Descarga de documento correcta", getClass());
    }

    @GetMapping("/alta")
    @PreAuthorize("permitAll()")
    public ModelAndView getAlta(ModelAndView modelAndView) { // TODO DAMIAN el problema es que estoy relacionando el alta de un usuario a un gimnasio que debe ser cliente mío
        modelAndView.setViewName("formularioAlta");
        formularioService.cargarDesplegablesAltaUsuario(modelAndView);
        if (modelAndView.isEmpty() || !modelAndView.getModel().containsKey("userModel")) {
            modelAndView.addObject("userModel", new UserModel());
        }
        return modelAndView;
    }

    @PostMapping("/alta")
    @PreAuthorize("permitAll()")
    public ModelAndView alta(ModelAndView modelAndView, @ModelAttribute("userModel") UserModel userModel) {
        boolean altaCorrecta = true;
        User user = new User();
        if(userService.findByUsername(userModel.getUsername()) == null) {
            try {
                user = userService.altaNuevoUsuario(userModel, Constantes.ROLE_USER);
                emailService.sendUserAdded(user);
            } catch (PersistenceException e) {
                modelAndView.addObject("problemasAlta", "Problemas dando de alta usuario con DNI " + userModel.getUsername());
                LoggerMapper.log(Level.ERROR, "alta", e.getMessage(), this.getClass());
                altaCorrecta = false;
            } catch (SenderException se) {
                LoggerMapper.log(Level.ERROR, "alta", se.getMessage(), this.getClass());
            }
        } else {
            modelAndView.addObject("dniDadoDeAlta", "Ya existe un usuario dado de alta con DNI " + userModel.getUsername());
            altaCorrecta = false;
        }
        if(altaCorrecta) {
            modelAndView.addObject("altaUsuarioOK", userModel.getName() + " te has dado de alta correctamente");
            modelAndView.setViewName(Constantes.LOGIN);
            LoggerMapper.log(Level.INFO, "alta", user, this.getClass());
            return modelAndView;
        } else {
            modelAndView.setViewName("formularioAlta");
            modelAndView.addObject("userModel", userModel);
            return getAlta(modelAndView);
        }

    }

}
