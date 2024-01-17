package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.GimnasioRootMenu2;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.*;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/adminGimnasio")
public class AdminGimnasioController {

    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private GimnasioRootService gimnasioRootService;
    @Autowired
    private GimnasioRootMenu2Service gimnasioRootMenu2Service;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private AdminUtilController adminUtilController;
    @Autowired
    private SessionData sessionData;
// TODO DAMIAN en la administración del gimnasio se debe separar los usuarios de la plataforma dados de alta a los usuarios inscritos en taekwondo
    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView gymAdministration(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminGimnasio/");
        modelAndView.setViewName("gimnasio/adminGimnasio");
        modelAndView.addObject("gimnasio", sessionData.getGimnasioRootModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

/*    @GetMapping("/gimnasioList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView gimnasioList(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminGimnasio/gimnasioList");
        modelAndView.setViewName("adminGimnasio");
        modelAndView.addObject("gimnasioModel", new GimnasioModel());
        modelAndView.addObject("gimnasioList", gimnasioService.findAll(sessionData.getGimnasioRootModel().getId()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }*/

/*    @GetMapping("/gimnasio/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragGimnasio(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminGimnasio/gimnasio/" + oldIndex + "/" + newIndex);
        gimnasioService.dragOfPosition(sessionData.getGimnasioRootModel().getId(), oldIndex, newIndex);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return gimnasioList(modelAndView);
    }*/

/*    @PostMapping("/addGimnasio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addGimnasio(ModelAndView modelAndView, @ModelAttribute("gimnasioModel") GimnasioModel gimnasioModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminGimnasio/addGimnasio");
        gimnasioModel.setCodigoGimnasio(sessionData.getGimnasioRootModel().getId());
        gimnasioModel.setPosition(gimnasioService.findMaxPosition(sessionData.getGimnasioRootModel().getId()) + 1);
        gimnasioService.add(gimnasioModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return gimnasioList(modelAndView);
    }*/

    @PostMapping("/updateGimnasio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateGimnasio(ModelAndView modelAndView, @ModelAttribute("gimnasioModel") GimnasioModel gimnasioModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminGimnasio/updateGimnasio");
        GimnasioModel gimnasioModelBBDD = gimnasioService.findById(gimnasioModel.getId());
        gimnasioModel.setCodigoGimnasio(gimnasioModelBBDD.getCodigoGimnasio());
        gimnasioModel.setPosition(gimnasioModelBBDD.getPosition());
        gimnasioService.update(gimnasioModel);
        modelAndView.addObject("updateOK", "Dato gimnasio actualizado con éxito");
        adminUtilController.utilList(modelAndView);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

/*    @GetMapping("/gimnasio/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeGimnasio(ModelAndView modelAndView, @PathVariable int id) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminGimnasio/gimnasio/remove/" + id);
        gimnasioService.delete(id);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return gimnasioList(modelAndView);
    }*/

    @PostMapping("/updateMenu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateMenu(ModelAndView modelAndView, @RequestParam(name = "selectedData", required = false) List<Integer> selectedData) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminGimnasio/procesar");
        gimnasioRootMenu2Service.deleteByIdGimnasioRoot(sessionData.getGimnasioRootModel().getId());
        List<GimnasioRootMenu2> gimnasioRootMenu2List = new ArrayList<>();
        if (selectedData != null) {
            for (Integer idMenu2 : selectedData) {
                gimnasioRootMenu2List.add(new GimnasioRootMenu2(0, sessionData.getGimnasioRootModel().getId(), idMenu2, user.getUsername(), new Date()));
            }
        }
        gimnasioRootMenu2Service.addList(gimnasioRootMenu2List);
        modelAndView.addObject("updateOK", "Asginación de Gimnasio a los submenú realizada correctamente");
        adminUtilController.utilList(modelAndView);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

}
