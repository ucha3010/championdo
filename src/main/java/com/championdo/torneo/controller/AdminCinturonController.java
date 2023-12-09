package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.PositionException;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.CinturonModel;
import com.championdo.torneo.service.CinturonService;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminCinturon")
public class AdminCinturonController {

    @Autowired
    private CinturonService cinturonService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private UserService userService;

    @GetMapping("/cinturonList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView cinturonList(ModelAndView modelAndView) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminCinturon/cinturonList");
        modelAndView.setViewName("gimnasio/adminCinturon");
        modelAndView.addObject("cinturonModel", new CinturonModel());
        modelAndView.addObject("cinturonList", cinturonService.findAll(user.getCodigoGimnasio()));
        LoggerMapper.methodOut(Level.INFO, "cinturonList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/cinturon/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragCinturon(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminCinturon/cinturon/" + oldIndex + "/" + newIndex);
        try {
            cinturonService.verifyDragOfPositionAvailable(user.getCodigoGimnasio(), oldIndex, newIndex);
            cinturonService.dragOfPosition(user.getCodigoGimnasio(), oldIndex, newIndex);
        } catch (PositionException pe) {
            modelAndView.addObject("dragPositionProblem", pe.getMessage());
        }
        return cinturonList(modelAndView);
    }

    @PostMapping("/addCinturon")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addCinturon(ModelAndView modelAndView, @ModelAttribute("cinturonModel") CinturonModel cinturonModel) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminCinturon/addCinturon");
        cinturonModel.setCodigoGimnasio(user.getCodigoGimnasio());
        cinturonModel.setPosition(cinturonService.findMaxPosition(user.getCodigoGimnasio()) + 1);
        cinturonService.add(cinturonModel);
        return cinturonList(modelAndView);
    }

    @GetMapping("/cinturon/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeCinturon(ModelAndView modelAndView, @PathVariable int id) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminCinturon/cinturon/remove/" + id);
        try {
            cinturonService.delete(id);
        } catch (RemoveException re) {
            modelAndView.addObject("removeProblem", re.getMessage());
        }
        return cinturonList(modelAndView);
    }

}
