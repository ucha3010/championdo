package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.service.GimnasioService;
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
@RequestMapping("/adminGimnasio")
public class AdminGimnasioController {

    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminUtilController adminUtilController;
// TODO DAMIAN en la administración del gimnasio se debe separar los usuarios de la plataforma dados de alta a los usuarios inscritos en taekwondo
    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView gymAdministration(ModelAndView modelAndView) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminGimnasio/");
        modelAndView.setViewName("gimnasio/adminGimnasio");
        LoggerMapper.methodOut(Level.INFO, "gymAdministration", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/gimnasioList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView gimnasioList(ModelAndView modelAndView) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminGimnasio/gimnasioList");
        modelAndView.setViewName("adminGimnasio");
        modelAndView.addObject("gimnasioModel", new GimnasioModel());
        modelAndView.addObject("gimnasioList", gimnasioService.findAll(user.getCodigoGimnasio()));
        LoggerMapper.methodOut(Level.INFO, "gimnasioList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/gimnasio/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragGimnasio(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminGimnasio/gimnasio/" + oldIndex + "/" + newIndex);
        gimnasioService.dragOfPosition(user.getCodigoGimnasio(), oldIndex, newIndex);
        return gimnasioList(modelAndView);
    }

    @PostMapping("/addGimnasio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addGimnasio(ModelAndView modelAndView, @ModelAttribute("gimnasioModel") GimnasioModel gimnasioModel) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminGimnasio/addGimnasio");
        gimnasioModel.setCodigoGimnasio(user.getCodigoGimnasio());
        gimnasioModel.setPosition(gimnasioService.findMaxPosition(user.getCodigoGimnasio()) + 1);
        gimnasioService.add(gimnasioModel);
        return gimnasioList(modelAndView);
    }

    @PostMapping("/updateGimnasio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateGimnasio(ModelAndView modelAndView, @ModelAttribute("gimnasioModel") GimnasioModel gimnasioModel) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminGimnasio/updateGimnasio");
        GimnasioModel gimnasioModelBBDD = gimnasioService.findById(gimnasioModel.getId());
        gimnasioModel.setCodigoGimnasio(gimnasioModelBBDD.getCodigoGimnasio());
        gimnasioModel.setPosition(gimnasioModelBBDD.getPosition());
        gimnasioService.update(gimnasioModel);
        modelAndView.addObject("updateOK", "Dato gimnasio actualizado con éxito");
        adminUtilController.utilList(modelAndView);
        LoggerMapper.methodOut(Level.INFO, "updateGimnasio", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/gimnasio/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeGimnasio(ModelAndView modelAndView, @PathVariable int id) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminGimnasio/gimnasio/remove/" + id);
        gimnasioService.delete(id);
        return gimnasioList(modelAndView);
    }

}
