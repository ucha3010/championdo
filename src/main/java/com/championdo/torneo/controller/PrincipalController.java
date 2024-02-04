package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.TournamentRegistrationModel;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.TournamentRegistrationService;
import com.championdo.torneo.service.PrincipalService;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    @Autowired
    private PrincipalService principalService;
    @Autowired
    private TournamentRegistrationService tournamentRegistrationService;
    @Autowired
    private EmailService emailService;
    //TODO DAMIAN hacer la validación de usuario
    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView mainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("mainPage");
        String ruta = "src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "static" + File.separator + "imgs" + File.separator + File.separator + "principal";
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        List<String> fotosList = new ArrayList<>(Utils.obtenerNombresArchivos(ruta));
        if (!fotosList.isEmpty()) {
            modelAndView.addObject("fotoPrincipal", fotosList.get(0));
            fotosList.remove(0);
        }
        modelAndView.addObject("listaFotos", fotosList);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/principalTorneo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView paginaPrincipalTorneo(ModelAndView modelAndView) {
        modelAndView.setViewName("torneo/principalTorneo");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("inscripciones", principalService.findByDni(usuario.getUsername()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    //TODO DAMIAN este método (y lo que tenga que ver con las inscripciones) deberían irse a otro controlador
    //TODO DAMIAN al eliminar una inscripción (a torneo, a actividad, un mandato, etc) debería mover los archivos correspondientes a una carpeta genérica con fecha de eliminación para luego borrarlos con algún batch
    @GetMapping("/eliminarInscripcion/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView eliminarInscripcion(ModelAndView modelAndView, @PathVariable int id) {
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        TournamentRegistrationModel tournamentRegistrationModel = tournamentRegistrationService.findById(id);
        principalService.deleteTournamentRegistration(tournamentRegistrationModel);
        emailService.confirmAdminDelete(tournamentRegistrationModel.getIdGym(), "torneo",
                usuario, !tournamentRegistrationModel.isRegistrationAdult() ? tournamentRegistrationModel.getRegisteredName() : null);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return paginaPrincipalTorneo(modelAndView);
    }

}
