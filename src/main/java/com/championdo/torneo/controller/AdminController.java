package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.service.GimnasioRootService;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private GimnasioRootService gimnasioRootService;

    @GetMapping("/adminList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminList(ModelAndView modelAndView) {
        modelAndView.setViewName("adminList");
        User user = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("gimnasioAvailable", gimnasioRootService.verifyEnable(user.getCodigoGimnasio()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

}
