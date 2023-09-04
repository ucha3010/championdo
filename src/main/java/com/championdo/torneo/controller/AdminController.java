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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/adminList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminList(ModelAndView modelAndView) {
        modelAndView.setViewName("adminList");
        userService.cargarUsuarioCompleto(modelAndView);
        LoggerMapper.log(Level.INFO, "adminList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/rootList")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView rootList(ModelAndView modelAndView) {
        modelAndView.setViewName("adminList"); //TODO DAMIAN cambiar vista y crearla. Acá manejaré(como ROOT) los gimnasios-clientes
        userService.cargarUsuarioCompleto(modelAndView);
        LoggerMapper.log(Level.INFO, "rootList", modelAndView, this.getClass());
        return modelAndView;
    }

}
