package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.UtilModel;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.service.UtilService;
import com.championdo.torneo.service.impl.UserService;
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

@Controller
@RequestMapping("/adminUtil")
public class AdminUtilController {

    @Autowired
    private UtilService utilService;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private UserService userService;

    @GetMapping("/utilList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView utilList(ModelAndView modelAndView) {
        //TODO DAMIAN al configurar el correo debería preguntar cuál es la plataforma del mismo. Ya que no es lo mismo enviar un correo de Gmail a uno de Msn
        modelAndView.setViewName("gimnasio/adminUtil");
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminUtil/utilList");
        modelAndView.addObject("utilModel", new UtilModel());
        modelAndView.addObject("gimnasioModel", gimnasioService.findByCodigoGimnasio(user.getCodigoGimnasio()));
        modelAndView.addObject("utilListCorreo", utilService.findAllEndWith(".correo", user.getCodigoGimnasio()));
        modelAndView.addObject("utilListInscripciones", utilService.findAllStarsWith("inscripciones", user.getCodigoGimnasio()));
        modelAndView.addObject("listaSiNo", Utils.cargarListaSiNo());
        LoggerMapper.log(Level.INFO, "utilList", modelAndView, this.getClass());
        return modelAndView;
    }

    @PostMapping("/updateUtil")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateUtil(ModelAndView modelAndView, @ModelAttribute("utilModel") UtilModel utilModel) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/adminUtil/updateUtil");
        utilModel.setCodigoGimnasio(user.getCodigoGimnasio());
        utilService.update(utilModel);
        modelAndView.addObject("updateOK", "Campo " + utilModel.getClave() + " actualizado con éxito");
        return utilList(modelAndView);
    }
}
