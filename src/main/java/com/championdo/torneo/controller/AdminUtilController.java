package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.UtilManagerModel;
import com.championdo.torneo.model.UtilModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.EmailEnum;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import com.mysql.cj.util.StringUtils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminUtil")
public class AdminUtilController {

    @Autowired
    private UtilService utilService;
    @Autowired
    private UtilManagerService utilManagerService;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private SessionData sessionData;

    @GetMapping("/utilList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView utilList(ModelAndView modelAndView) {
        modelAndView.setViewName("gimnasio/adminUtil");
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminUtil/utilList");
        modelAndView.addObject("utilModel", new UtilModel());
        modelAndView.addObject("gimnasioModel", sessionData.getGimnasioModel());
        modelAndView.addObject("emptyPass", StringUtils.isNullOrEmpty(sessionData.getGimnasioModel().getEmailPassword()));
        modelAndView.addObject("utilListInscripciones", utilService.findAllStarsWith("inscripciones", sessionData.getGimnasioModel().getId()));
        modelAndView.addObject("utilListHost", Utils.cargarListaProveedoresHost());
        modelAndView.addObject("listaSiNo", Utils.cargarListaSiNo());
        gimnasioService.fillMenu2Checked(modelAndView, sessionData.getGimnasioModel().getId());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/updateUtil")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateUtil(ModelAndView modelAndView, @ModelAttribute("utilModel") UtilModel utilModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminUtil/updateUtil");
        utilModel.setCodigoGimnasio(sessionData.getGimnasioModel().getId());
        utilService.update(utilModel);
        modelAndView.addObject("updateOK", "Campo " + utilModel.getClave() + " actualizado con éxito");
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return utilList(modelAndView);
    }

   /* @PostMapping("/updateHost")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateHost(ModelAndView modelAndView, @ModelAttribute("utilModel") UtilModel utilModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioModel().getId(), "/adminUtil/updateHost");
        UtilModel utilPort = new UtilModel();
        for (EmailEnum emailEnum : EmailEnum.values()) {
            if (emailEnum.getHost().equals(utilModel.getValor())) {
                utilPort = new UtilModel(Constantes.PORT_CORREO, emailEnum.getPort(), sessionData.getGimnasioModel().getId());
                break;
            }
        }
        utilModel.setCodigoGimnasio(sessionData.getGimnasioModel().getId());
        utilService.update(utilModel);
        utilService.update(utilPort);
        modelAndView.addObject("updateOK", "Proovedor de correo actualizado con éxito");
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return utilList(modelAndView);
    }*/

    @GetMapping("/root-util")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView rootUtil(ModelAndView modelAndView) {
        modelAndView.setViewName("management/adminUtil");
        principalService.cargaBasicaCompleta(modelAndView);
//        modelAndView.addObject("utilModel", utilService.findByClave(Constantes.HOST_PAGE_NAME,0));
        UtilManagerModel utilManagerModel = utilManagerService.get();
        modelAndView.addObject("utilManagerModel", utilManagerModel);
        modelAndView.addObject("existsPass", utilManagerModel.getPassword() != null);
        modelAndView.addObject("utilListHost", Utils.cargarListaProveedoresHost());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    //TODO DAMIAN para borrar una vez adaptada la página util del root
    @PostMapping("/root-update-util")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView rootUpdateUtil(ModelAndView modelAndView, @ModelAttribute("utilModel") UtilModel utilModel) {
        principalService.cargaBasicaCompleta(modelAndView);
        utilService.update(utilModel);
        modelAndView.addObject("updateOK", "Campo " + utilModel.getClave() + " actualizado con éxito");
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return rootUtil(modelAndView);
    }

    @PostMapping("/update-util-manager")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView updateUtilManager(ModelAndView modelAndView, @ModelAttribute("utilManagerModel") UtilManagerModel utilManagerModel) {
        LoggerMapper.methodIn(Level.INFO, Utils.obtenerNombreMetodo(), utilManagerModel, getClass());
        principalService.cargaBasicaCompleta(modelAndView);
        utilManagerModel.setPassword(utilManagerService.get().getPassword());
        for (EmailEnum emailEnum : EmailEnum.values()) {
            if (emailEnum.getHost().equals(utilManagerModel.getEmailHost())) {
                utilManagerModel.setEmailPort(emailEnum.getPort());
                break;
            }
        }
        utilManagerService.update(utilManagerModel);
        modelAndView.addObject("updateOK", "Actualización realizada con éxito");
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return rootUtil(modelAndView);
    }

    @PostMapping("/update-util-manager/connection")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView updateUtilManagerConnection(ModelAndView modelAndView, @ModelAttribute("utilManagerModel") UtilManagerModel utilManagerModel) {
        principalService.cargaBasicaCompleta(modelAndView);
        UtilManagerModel utilManagerModelAux = utilManagerService.get();
        utilManagerModelAux.setPassword(utilManagerModel.getPassword());
        utilManagerService.update(utilManagerModelAux);
        modelAndView.addObject("updateOK", "Contraseña actualizada con éxito");
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return rootUtil(modelAndView);
    }
}
