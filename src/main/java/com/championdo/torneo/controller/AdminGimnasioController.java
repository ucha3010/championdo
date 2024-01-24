package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.GimnasioMenu2;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.UtilModel;
import com.championdo.torneo.service.GimnasioMenu2Service;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.EmailEnum;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import com.mysql.cj.util.StringUtils;
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
    private GimnasioMenu2Service gimnasioMenu2Service;
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
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminGimnasio/");
        modelAndView.setViewName("gimnasio/adminGimnasio");
        modelAndView.addObject("gimnasio", sessionData.getGimnasioModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

/*    @GetMapping("/gimnasioList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView gimnasioList(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminGimnasio/gimnasioList");
        modelAndView.setViewName("adminGimnasio");
        modelAndView.addObject("gimnasioModel", new GimnasioModel());
        modelAndView.addObject("gimnasioList", gimnasioService.findAll(sessionData.getGimnasioModel().getId()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }*/

/*    @GetMapping("/gimnasio/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragGimnasio(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminGimnasio/gimnasio/" + oldIndex + "/" + newIndex);
        gimnasioService.dragOfPosition(sessionData.getGimnasioModel().getId(), oldIndex, newIndex);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return gimnasioList(modelAndView);
    }*/

/*    @PostMapping("/addGimnasio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addGimnasio(ModelAndView modelAndView, @ModelAttribute("gimnasioModel") GimnasioModel gimnasioModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminGimnasio/addGimnasio");
        gimnasioModel.setCodigoGimnasio(sessionData.getGimnasioModel().getId());
        gimnasioModel.setPosition(gimnasioService.findMaxPosition(sessionData.getGimnasioModel().getId()) + 1);
        gimnasioService.add(gimnasioModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return gimnasioList(modelAndView);
    }*/

    @PostMapping("/updateGimnasio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateGimnasio(ModelAndView modelAndView, @ModelAttribute("gimnasioModel") GimnasioModel gimnasioModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminGimnasio/updateGimnasio");
        GimnasioModel gimnasioModelBBDD = gimnasioService.findById(gimnasioModel.getId());
        gimnasioModelBBDD.setNombreGimnasio(gimnasioModel.getNombreGimnasio());
        gimnasioModelBBDD.setDomicilioCalle(gimnasioModel.getDomicilioCalle());
        gimnasioModelBBDD.setDomicilioNumero(gimnasioModel.getDomicilioNumero());
        gimnasioModelBBDD.setDomicilioOtros(gimnasioModel.getDomicilioOtros());
        gimnasioModelBBDD.setDomicilioLocalidad(gimnasioModel.getDomicilioLocalidad());
        gimnasioModelBBDD.setDomicilioCp(gimnasioModel.getDomicilioCp());
        gimnasioModelBBDD.setUsuarioModificacion(user.getUsername());
        sessionData.setGimnasioModel(gimnasioService.update(gimnasioModelBBDD));
        modelAndView.addObject("updateOK", "Datos del gimnasio actualizados con éxito");
        adminUtilController.utilList(modelAndView);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/updateEmail")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateEmail(ModelAndView modelAndView, @ModelAttribute("gimnasioModel") GimnasioModel gimnasioModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminGimnasio/updateEmail");
        GimnasioModel gimnasioModelBBDD = gimnasioService.findById(gimnasioModel.getId());
        if (!StringUtils.isNullOrEmpty(gimnasioModel.getCorreo())) {
            gimnasioModelBBDD.setCorreo(gimnasioModel.getCorreo());
        }
        if (!StringUtils.isNullOrEmpty(gimnasioModel.getEmailPassword())) {
            gimnasioModelBBDD.setEmailPassword(gimnasioModel.getEmailPassword());
        }
        if (!StringUtils.isNullOrEmpty(gimnasioModel.getEmailHost())) {
            gimnasioModelBBDD.setEmailHost(gimnasioModel.getEmailHost());
            for (EmailEnum emailEnum : EmailEnum.values()) {
                if (emailEnum.getHost().equals(gimnasioModel.getEmailHost())) {
                    gimnasioModelBBDD.setEmailPort(emailEnum.getPort());
                    break;
                }
            }
        }
        sessionData.setGimnasioModel(gimnasioService.update(gimnasioModelBBDD));
        modelAndView.addObject("updateOK", "Datos del correo del gimnasio actualizados con éxito");
        adminUtilController.utilList(modelAndView);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/changePass")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView changePass(ModelAndView modelAndView) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminGimnasio/changePass");
        modelAndView.setViewName("gimnasio/adminChangePass");
        modelAndView.addObject("gimnasioModel", sessionData.getGimnasioModel());
        return modelAndView;
    }

    @PostMapping("/updateMenu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateMenu(ModelAndView modelAndView, @RequestParam(name = "selectedData", required = false) List<Integer> selectedData) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminGimnasio/procesar");
        gimnasioMenu2Service.deleteByIdGimnasio(sessionData.getGimnasioModel().getId());
        List<GimnasioMenu2> gimnasioMenu2List = new ArrayList<>();
        if (selectedData != null) {
            for (Integer idMenu2 : selectedData) {
                gimnasioMenu2List.add(new GimnasioMenu2(0, sessionData.getGimnasioModel().getId(), idMenu2, user.getUsername(), new Date()));
            }
        }
        gimnasioMenu2Service.addList(gimnasioMenu2List);
        modelAndView.addObject("updateOK", "Asginación de Gimnasio a los submenú realizada correctamente");
        adminUtilController.utilList(modelAndView);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

}
