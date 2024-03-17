package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.TournamentRegistrationModel;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.service.TournamentRegistrationService;
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

@Controller
@RequestMapping("/tournamentRegistration")
public class TournamentRegistrationController {
    @Autowired
    private TournamentRegistrationService tournamentRegistrationService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PrincipalService principalService;

    @GetMapping("/mainPage")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tournamentMainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("torneo/principalTorneo");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("inscripciones", principalService.findByDni(usuario.getUsername()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    // TODO DAMIAN al eliminar una inscripción (a torneo, a actividad, un mandato, etc) debería mover los archivos correspondientes a una carpeta genérica con fecha de eliminación para luego borrarlos con algún batch
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView delete(ModelAndView modelAndView, @PathVariable int id) {
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        TournamentRegistrationModel tournamentRegistrationModel = tournamentRegistrationService.findById(id);
        principalService.deleteTournamentRegistration(tournamentRegistrationModel);
        emailService.confirmAdminDelete(tournamentRegistrationModel.getIdGym(), "torneo",
                usuario, !tournamentRegistrationModel.isRegistrationAdult() ? tournamentRegistrationModel.getRegisteredName() : null);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return tournamentMainPage(modelAndView);
    }

}
