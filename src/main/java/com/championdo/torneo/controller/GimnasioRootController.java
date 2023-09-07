package com.championdo.torneo.controller;

import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.service.GimnasioRootService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gimnasioRoot")
public class GimnasioRootController {

    @Autowired
    private GimnasioRootService gimnasioRootService;

    @Autowired
    private UserService userService;

    @GetMapping("/customers")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView customers(ModelAndView modelAndView) {
        modelAndView.setViewName("management/customers");
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("customerList", gimnasioRootService.findAllOrderByNombreGimnasioAsc());
        LoggerMapper.log(Level.INFO, "customers", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView customersId(ModelAndView modelAndView, int id) {
        modelAndView.setViewName("management/customer");
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("customer", gimnasioRootService.findById(id));
        LoggerMapper.log(Level.INFO, "customersId", modelAndView, this.getClass());
        return modelAndView;
    }

    @PutMapping("/customers")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView save(ModelAndView modelAndView, @ModelAttribute("customer") GimnasioRootModel customer) {
        modelAndView.setViewName("management/customer");
        //TODO DAMIAN actualizar cliente
        //updateOk
        //updateProblem
        return modelAndView;
    }

}
