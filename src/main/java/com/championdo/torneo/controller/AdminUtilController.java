package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.model.Menu1Model;
import com.championdo.torneo.model.Menu2Model;
import com.championdo.torneo.model.UtilModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.EmailEnum;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/adminUtil")
public class AdminUtilController {

    @Autowired
    private UtilService utilService;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private GimnasioRootService gimnasioRootService;
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
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminUtil/utilList");
        modelAndView.addObject("utilModel", new UtilModel());
        modelAndView.addObject("gimnasioModel", gimnasioService.findByCodigoGimnasio(sessionData.getGimnasioRootModel().getId()));
        modelAndView.addObject("utilListCorreo", utilService.findAllEndWith(".correo", sessionData.getGimnasioRootModel().getId()));
        modelAndView.addObject("utilListInscripciones", utilService.findAllStarsWith("inscripciones", sessionData.getGimnasioRootModel().getId()));
        modelAndView.addObject("utilHost", utilService.findAllEndWith("host.email", sessionData.getGimnasioRootModel().getId()).get(0));
        modelAndView.addObject("utilListHost", Utils.cargarListaProveedoresHost());
        modelAndView.addObject("listaSiNo", Utils.cargarListaSiNo());
        modelAndView.addObject("gimnasio", sessionData.getGimnasioRootModel());
        gimnasioRootService.fillMenu2Checked(modelAndView);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/updateUtil")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateUtil(ModelAndView modelAndView, @ModelAttribute("utilModel") UtilModel utilModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminUtil/updateUtil");
        utilModel.setCodigoGimnasio(sessionData.getGimnasioRootModel().getId());
        utilService.update(utilModel);
        modelAndView.addObject("updateOK", "Campo " + utilModel.getClave() + " actualizado con éxito");
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return utilList(modelAndView);
    }

    @PostMapping("/updateHost")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateHost(ModelAndView modelAndView, @ModelAttribute("utilModel") UtilModel utilModel) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(sessionData.getGimnasioRootModel().getId(), "/adminUtil/updateHost");
        UtilModel utilPort = new UtilModel();
        for (EmailEnum emailEnum : EmailEnum.values()) {
            if (emailEnum.getHost().equals(utilModel.getValor())) {
                utilPort = new UtilModel(Constantes.PORT_CORREO, emailEnum.getPort(), sessionData.getGimnasioRootModel().getId());
                break;
            }
        }
        utilModel.setCodigoGimnasio(sessionData.getGimnasioRootModel().getId());
        utilService.update(utilModel);
        utilService.update(utilPort);
        modelAndView.addObject("updateOK", "Proovedor de correo actualizado con éxito");
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return utilList(modelAndView);
    }

    @GetMapping("/root-util")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView rootUtil(ModelAndView modelAndView) {
        modelAndView.setViewName("management/adminUtil");
        principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("utilModel", utilService.findByClave(Constantes.HOST_PAGE_NAME,0));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/root-update-util")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView rootUpdateUtil(ModelAndView modelAndView, @ModelAttribute("utilModel") UtilModel utilModel) {
        principalService.cargaBasicaCompleta(modelAndView);
        utilService.update(utilModel);
        modelAndView.addObject("updateOK", "Campo " + utilModel.getClave() + " actualizado con éxito");
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return rootUtil(modelAndView);
    }
}
