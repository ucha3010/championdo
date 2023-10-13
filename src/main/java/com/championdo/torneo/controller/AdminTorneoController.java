package com.championdo.torneo.controller;

import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminTorneo")
public class AdminTorneoController {

    @Autowired
    private UserService userService;

    @GetMapping("/torneoList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView torneoList(ModelAndView modelAndView) {
        modelAndView.setViewName("torneoList"); //TODO DAMIAN hacer html
        userService.cargarUsuarioCompleto(modelAndView);
        LoggerMapper.log(Level.INFO, "torneoList", modelAndView, this.getClass());
        return modelAndView;
    }

}
