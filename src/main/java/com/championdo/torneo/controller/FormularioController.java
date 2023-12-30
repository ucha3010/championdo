package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.model.*;
import com.championdo.torneo.service.*;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
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
    private PrincipalService principalService;
    @Autowired
    private UserService userService;

    @GetMapping("/selectTournament/{tournamentType}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView selectTournament(ModelAndView modelAndView, @PathVariable String tournamentType) {

        List<TorneoModel> torneoModelList = selectTournamentCommon(modelAndView, tournamentType);
        if (!torneoModelList.isEmpty()) {
            modelAndView.addObject("torneoGimnasioModelList", torneoGimnasioService.findAll(torneoModelList.get(0).getId()));
        }
        if (!tournamentType.isEmpty() && torneoModelList.isEmpty()) {
            modelAndView.addObject("errorMessage", "No hay torneo disponibles en este momento");
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/selectTournament/{tournamentType}/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView selectTournamentChargeGyms(ModelAndView modelAndView, @PathVariable String tournamentType, @PathVariable int id) {

        selectTournamentCommon(modelAndView, tournamentType);
        modelAndView.addObject("torneoGimnasioModelList", torneoGimnasioService.findAll(id));
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setId(id);
        modelAndView.addObject("torneoModel", torneoModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/cargar-formulario/{tournamentType}/{idTorneo}/{idGimnasio}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView cargarFormulario(ModelAndView modelAndView, @PathVariable String tournamentType, @PathVariable int idTorneo, @PathVariable int idGimnasio) {

        com.championdo.torneo.entity.User usuario = principalService.cargaBasicaCompleta(modelAndView);
        usuario.setIdTorneo(idTorneo);
        usuario.setIdTorneoGimnasio(idGimnasio);
        switch (tournamentType) {
            case Constantes.ADULTO:
                modelAndView.setViewName("torneo/formularioInscPropia");
                modelAndView.addObject("userModel", formularioService.formularioInscPropia(usuario));
                break;
            case Constantes.MENOR:
                modelAndView.setViewName("torneo/formularioInscMenor");
                modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(usuario, true));
                break;
            case Constantes.INCLUSIVO_MINUSCULAS:
                modelAndView.setViewName("torneo/formularioInscMenor");
                modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(usuario, false));
                break;
            default:
                LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
                return selectTournament(modelAndView, tournamentType);
        }
        formularioService.cargarDesplegables(modelAndView, usuario.getCodigoGimnasio());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/gaurdarPropia")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView gaurdarPropia(@ModelAttribute("userModel") UserModel userModel) {

        LoggerMapper.methodIn(Level.INFO, "gaurdarPropia", userModel, getClass());
        ModelAndView modelAndView = new ModelAndView();
        User user = principalService.cargaBasicaCompleta(modelAndView);
        userModel.setCodigoGimnasio(user.getCodigoGimnasio());
        modelAndView.setViewName("formularioInscFinalizada");
        PdfModel pdfModel;
        try {
            formularioService.fillObjects(userModel);
            pdfModel = formularioService.getPdfModelTorneo(new UserAutorizacionModel(userModel));
            InscripcionModel inscripcionModel = inscripcionService.addPropia(userModel, pdfModel);
            pdfModel.setIdInscripcion(inscripcionModel.getId());
            pdfModel.setCategoria(inscripcionModel.getCategoria());
            pdfModel.setPoomsae(inscripcionModel.getPoomsae());
            File file = pdfService.generarPdfTorneo(pdfModel);
            emailService.sendTournamentRegistration(userModel, file);
            emailService.confirmAdminTournamentRegistration(new UserAutorizacionModel(userModel));
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR,"gaurdarPropia", e.getMessage(), getClass());
            pdfModel = null;
            modelAndView.addObject("inscripcionError", "Hubo problemas al guardar la información");
            modelAndView.addObject("inscripcionCorrecta", "");
        }
        if (pdfModel != null) {
            modelAndView.addObject("inscripcionCorrecta", "¡La inscripción se realizó con éxito!");
            modelAndView.addObject("pdfModel", pdfModel);
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/getPropia/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getPropia(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("torneo/vistaInscPropia");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        InscripcionModel inscripcionModel = inscripcionService.findById(id);
        modelAndView.addObject("inscripcion", inscripcionModel);
        modelAndView.addObject("pdfModel", pdfService.getImpresion(inscripcionModel));
        modelAndView.addObject("deleteEnable", Boolean.parseBoolean(inscripcionService.getDeleteEnable(usuario.getCodigoGimnasio()).getValor()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/guardarMenorOInclisivo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView guardarMenorOInclisivo(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {
        ModelAndView modelAndView = new ModelAndView();
        User user = principalService.cargaBasicaCompleta(modelAndView);
        userAutorizacionModel.getAutorizado().setCodigoGimnasio(user.getCodigoGimnasio());
        userAutorizacionModel.getMayorAutorizador().setCodigoGimnasio(user.getCodigoGimnasio());
        modelAndView.setViewName("formularioInscFinalizada");
        PdfModel pdfModel;
        try {
            formularioService.fillObjects(userAutorizacionModel.getAutorizado());
            pdfModel = formularioService.getPdfModelTorneo(userAutorizacionModel);
            InscripcionModel inscripcionModel = inscripcionService.addMenorOInclusivo(userAutorizacionModel, pdfModel);
            pdfModel.setIdInscripcion(inscripcionModel.getId());
            pdfModel.setCategoria(inscripcionModel.getCategoria());
            pdfModel.setPoomsae(inscripcionModel.getPoomsae());
            File file = pdfService.generarPdfTorneo(pdfModel);
            emailService.sendTournamentRegistration(userAutorizacionModel.getMayorAutorizador(), file);
            emailService.confirmAdminTournamentRegistration(userAutorizacionModel);
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR,"gaurdarPropia", e.getMessage(), getClass());
            pdfModel = null;
            modelAndView.addObject("inscripcionError", "Hubo problemas al guardar la información");
            modelAndView.addObject("inscripcionCorrecta", "");
        }
        if (pdfModel != null) {
            modelAndView.addObject("inscripcionCorrecta", "¡La inscripción se realizó con éxito!");
            modelAndView.addObject("pdfModel", pdfModel);
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;

    }

    @GetMapping("/getMenorOInclisivo/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getMenorOInclisivo(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("torneo/vistaInscMenorOInclisivo");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        InscripcionModel inscripcionModel = inscripcionService.findById(id);
        TorneoModel torneoModel = torneoService.findById(inscripcionModel.getIdTorneo());
        modelAndView.addObject("inscripcion", inscripcionModel);
        modelAndView.addObject("pdfModel", pdfService.getImpresion(inscripcionModel));
        modelAndView.addObject("deleteEnable", Boolean.parseBoolean(inscripcionService.getDeleteEnable(torneoModel.getCodigoGimnasio()).getValor()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("isAuthenticated()")
    public void descargarPdf(@ModelAttribute("pdfModel") PdfModel pdfModel, HttpServletResponse response) {
        pdfService.descargarArchivo(pdfModel, response, Constantes.SECCION_TORNEO);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), "Descarga de documento correcta", getClass());
    }

    @GetMapping("/alta")
    @PreAuthorize("permitAll()")
    public ModelAndView getAlta(ModelAndView modelAndView) {
        modelAndView.setViewName("formularioAlta");
        formularioService.cargarDesplegablesAltaUsuario(modelAndView);
        if (modelAndView.isEmpty() || !modelAndView.getModel().containsKey("userModel")) {
            modelAndView.addObject("userModel", new UserModel());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/alta")
    @PreAuthorize("permitAll()")
    public ModelAndView alta(ModelAndView modelAndView, @ModelAttribute("userModel") UserModel userModel) {
        boolean altaCorrecta = true;
        if(userService.findByUsername(userModel.getUsername()) == null) {
            try {
                emailService.sendUserAdded(userService.altaNuevoUsuario(userModel, Constantes.ROLE_USER));
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
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return modelAndView;
        } else {
            modelAndView.setViewName("formularioAlta");
            modelAndView.addObject("userModel", userModel);
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return getAlta(modelAndView);
        }

    }

    private List<TorneoModel> selectTournamentCommon(ModelAndView modelAndView, String tournamentType) {

        com.championdo.torneo.entity.User usuario = principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("inscripciones", principalService.findByDni(usuario.getUsername()));
        modelAndView.setViewName("torneo/formularioSeleccionTorneo");
        List<TorneoModel> torneoModelList = torneoService.findAllowed(new Date(), tournamentType);
        modelAndView.addObject("torneoModelList", torneoModelList);
        modelAndView.addObject("tournamentType", tournamentType);
        modelAndView.addObject("torneoModel", new TorneoModel());
        if (tournamentType.isEmpty()) {
            modelAndView.addObject("errorMessage", "Problemas con la opción seleccionada");
        }
        return torneoModelList;
    }

}
