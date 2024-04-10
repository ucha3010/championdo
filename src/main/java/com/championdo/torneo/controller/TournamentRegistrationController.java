package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.mapper.MapperUser;
import com.championdo.torneo.model.*;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tournamentRegistration")
public class TournamentRegistrationController {
    @Autowired
    private TournamentRegistrationService tournamentRegistrationService;
    @Autowired
    private DocumentManagerService documentManagerService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FormularioService formularioService;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private MapperUser mapperUser;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private SessionData sessionData;
    @Autowired
    private TorneoGimnasioService torneoGimnasioService;
    @Autowired
    private TorneoService torneoService;

    @GetMapping("/mainPage")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tournamentMainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("torneo/principalTorneo");
        User user = principalService.cargaBasicaCompleta(modelAndView);
        List<PrincipalUserModel> principalUserModelList = principalService.findByDni(user.getUsername());
        if (principalUserModelList != null && !principalUserModelList.isEmpty()) {
            TournamentRegistrationModel tournamentRegistrationModel = tournamentRegistrationService.findById(principalUserModelList.get(0).getId());
            String idCard;
            if (tournamentRegistrationModel.getAuthorizerIdCard() != null) {
                idCard = tournamentRegistrationModel.getAuthorizerIdCard();
            } else {
                idCard = tournamentRegistrationModel.getRegisteredIdCard();
            }
            seguridadService.userAccessValidation(user.getUsername(), idCard, "/tournamentRegistration/mainPage");
        }
        modelAndView.addObject("inscripciones", principalUserModelList);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

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

    @GetMapping("/cargar-formulario/{tournamentType}/{idTorneo}/{idTorneoGimnasio}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView cargarFormulario(ModelAndView modelAndView, @PathVariable String tournamentType, @PathVariable int idTorneo, @PathVariable int idTorneoGimnasio) {

        com.championdo.torneo.entity.User user = principalService.cargaBasicaCompleta(modelAndView);
        UserModel userModel = mapperUser.entity2Model(user);
        userModel.setIdTorneo(idTorneo);
        userModel.setIdTorneoGimnasio(idTorneoGimnasio);
        TorneoModel torneoModel = torneoService.findById(idTorneo);
        sessionData.setGimnasioModel(gimnasioService.findById(torneoModel.getCodigoGimnasio()));
        switch (tournamentType) {
            case Constantes.ADULTO:
                modelAndView.setViewName("torneo/formularioInscPropia");
                modelAndView.addObject("userModel", userModel);
                break;
            case Constantes.MENOR:
                modelAndView.setViewName("torneo/formularioInscMenor");
                modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(userModel, true));
                break;
            case Constantes.INCLUSIVO_MINUSCULAS:
                modelAndView.setViewName("torneo/formularioInscMenor");
                modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(userModel, false));
                break;
            default:
                LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
                return selectTournament(modelAndView, tournamentType);
        }
        formularioService.cargarDesplegables(modelAndView, sessionData.getGimnasioModel().getId());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/gaurdarPropia")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView gaurdarPropia(@ModelAttribute("userModel") UserModel userModel) {

        LoggerMapper.methodIn(Level.INFO, "gaurdarPropia", userModel, getClass());
        ModelAndView modelAndView = new ModelAndView();
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.userAccessValidation(user.getUsername(), userModel.getUsername(), "/tournamentRegistration/guardarPropia");
        modelAndView.setViewName("formularioInscFinalizada");
        PdfModel pdfModel;
        try {
            formularioService.fillObjects(userModel);
            pdfModel = formularioService.getPdfModelTorneo(new UserAutorizacionModel(userModel));
            TournamentRegistrationModel tournamentRegistrationModel = tournamentRegistrationService.addAdult(userModel, pdfModel, sessionData.getGimnasioModel().getId());
            pdfModel.setCodigoGimnasio(tournamentRegistrationModel.getIdGym());
            pdfModel.setIdInscripcion(tournamentRegistrationModel.getId());
            pdfModel.setCategoria(tournamentRegistrationModel.getCategory());
            pdfModel.setPoomsae(tournamentRegistrationModel.getPoomsae());
            DocumentManagerModel documentManagerModel = pdfService.generarPdfTorneo(pdfModel, true);
            emailService.sendTournamentRegistration(userModel, documentManagerModel, tournamentRegistrationModel);
            emailService.confirmAdminTournamentRegistration(new UserAutorizacionModel(userModel), tournamentRegistrationModel);
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
        User user = principalService.cargaBasicaCompleta(modelAndView);
        TournamentRegistrationModel tournamentRegistrationModel = tournamentRegistrationService.findById(id);
        String idCard;
        if (tournamentRegistrationModel.getAuthorizerIdCard() != null) {
            idCard = tournamentRegistrationModel.getAuthorizerIdCard();
        } else {
            idCard = tournamentRegistrationModel.getRegisteredIdCard();
        }
        seguridadService.userAccessValidation(user.getUsername(), idCard, "/tournamentRegistration/getPropia/" + id);
        modelAndView.addObject("tournamentRegistration", tournamentRegistrationModel);
        modelAndView.addObject("pdfModel", pdfService.getImpresion(tournamentRegistrationModel));
        modelAndView.addObject("deleteEnable", Boolean.parseBoolean(tournamentRegistrationService.getDeleteEnable(tournamentRegistrationModel.getIdGym()).getValor()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/guardarMenorOInclisivo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView guardarMenorOInclisivo(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {
        ModelAndView modelAndView = new ModelAndView();
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.userAccessValidation(user.getUsername(), userAutorizacionModel.getMayorAutorizador().getUsername(), "/tournamentRegistration/guardarMenorOInclusivo");
        modelAndView.setViewName("formularioInscFinalizada");
        PdfModel pdfModel;
        try {
            formularioService.fillObjects(userAutorizacionModel.getAutorizado());
            pdfModel = formularioService.getPdfModelTorneo(userAutorizacionModel);
            TournamentRegistrationModel tournamentRegistrationModel = tournamentRegistrationService.addYoungOrInclusive(userAutorizacionModel, pdfModel, sessionData.getGimnasioModel().getId());
            pdfModel.setCodigoGimnasio(tournamentRegistrationModel.getIdGym());
            pdfModel.setIdInscripcion(tournamentRegistrationModel.getId());
            pdfModel.setCategoria(tournamentRegistrationModel.getCategory());
            pdfModel.setPoomsae(tournamentRegistrationModel.getPoomsae());
            DocumentManagerModel documentManagerModel = pdfService.generarPdfTorneo(pdfModel, true);
            emailService.sendTournamentRegistration(userAutorizacionModel.getMayorAutorizador(), documentManagerModel, tournamentRegistrationModel);
            emailService.confirmAdminTournamentRegistration(userAutorizacionModel, tournamentRegistrationModel);
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
        User user = principalService.cargaBasicaCompleta(modelAndView);
        TournamentRegistrationModel tournamentRegistrationModel = tournamentRegistrationService.findById(id);
        String idCard;
        if (tournamentRegistrationModel.getAuthorizerIdCard() != null) {
            idCard = tournamentRegistrationModel.getAuthorizerIdCard();
        } else {
            idCard = tournamentRegistrationModel.getRegisteredIdCard();
        }
        seguridadService.userAccessValidation(user.getUsername(), idCard, "/tournamentRegistration/getMenorOInclisivo/" + id);
        TorneoModel torneoModel = torneoService.findById(tournamentRegistrationModel.getIdTournament());
        modelAndView.addObject("tournamentRegistration", tournamentRegistrationModel);
        modelAndView.addObject("pdfModel", pdfService.getImpresion(tournamentRegistrationModel));
        modelAndView.addObject("deleteEnable", Boolean.parseBoolean(tournamentRegistrationService.getDeleteEnable(torneoModel.getCodigoGimnasio()).getValor()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("isAuthenticated()")
    public void descargarPdf(@ModelAttribute("pdfModel") PdfModel pdfModel, HttpServletResponse response) {
        User user = principalService.cargaBasicaCompleta(new ModelAndView());
        TournamentRegistrationModel tournamentRegistrationModel = tournamentRegistrationService.findById(pdfModel.getIdInscripcion());
        String idCard;
        if (tournamentRegistrationModel.getAuthorizerIdCard() != null) {
            idCard = tournamentRegistrationModel.getAuthorizerIdCard();
        } else {
            idCard = tournamentRegistrationModel.getRegisteredIdCard();
        }
        seguridadService.userAccessValidation(user.getUsername(), idCard, "/tournamentRegistration/descargarPdf");
        pdfService.descargarArchivo(pdfModel, response, Constantes.SECCION_TORNEO);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), "Descarga de documento correcta", getClass());
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView delete(ModelAndView modelAndView, @PathVariable int id) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        TournamentRegistrationModel tournamentRegistrationModel = tournamentRegistrationService.findById(id);
        String idCard;
        if (tournamentRegistrationModel.getAuthorizerIdCard() != null) {
            idCard = tournamentRegistrationModel.getAuthorizerIdCard();
        } else {
            idCard = tournamentRegistrationModel.getRegisteredIdCard();
        }
        seguridadService.userAccessValidation(user.getUsername(), idCard, "/tournamentRegistration/delete/" + id);
        principalService.deleteTournamentRegistration(tournamentRegistrationModel);
        documentManagerService.deleteByIdOriginalOperativeAndSectionAndIdCard(id, Constantes.SECCION_TORNEO, user.getUsername());
        emailService.confirmAdminDelete(tournamentRegistrationModel.getIdGym(), Constantes.SECCION_TORNEO,
                user, !tournamentRegistrationModel.isRegistrationAdult() ? tournamentRegistrationModel.getRegisteredName() : null);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return tournamentMainPage(modelAndView);
    }

    private List<TorneoModel> selectTournamentCommon(ModelAndView modelAndView, String tournamentType) {

        com.championdo.torneo.entity.User user = principalService.cargaBasicaCompleta(modelAndView);
        List<PrincipalUserModel> principalUserModelList = principalService.findByDni(user.getUsername());
        if (principalUserModelList != null && !principalUserModelList.isEmpty()) {
            TournamentRegistrationModel tournamentRegistrationModel = tournamentRegistrationService.findById(principalUserModelList.get(0).getId());
            String idCard;
            if (tournamentRegistrationModel.getAuthorizerIdCard() != null) {
                idCard = tournamentRegistrationModel.getAuthorizerIdCard();
            } else {
                idCard = tournamentRegistrationModel.getRegisteredIdCard();
            }
            seguridadService.userAccessValidation(user.getUsername(), idCard, "/tournamentRegistration/selectTournament");
        }
        modelAndView.addObject("inscripciones", principalUserModelList);
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
