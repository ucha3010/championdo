package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
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
        LoggerMapper.methodIn(Level.INFO, "customers", "", this.getClass());
        modelAndView.setViewName("management/customers");
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("customerList", gimnasioRootService.findAllOrderByNombreGimnasioAsc());
        LoggerMapper.methodOut(Level.INFO, "customers", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView customersId(ModelAndView modelAndView, int id) {
        LoggerMapper.methodIn(Level.INFO, "customersId", "id: " + id, this.getClass());
        modelAndView.setViewName("management/customer");
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("customer", gimnasioRootService.findById(id));
        LoggerMapper.methodOut(Level.INFO, "customersId", modelAndView, this.getClass());
        return modelAndView;
    }

    @PutMapping("/customers")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView update(ModelAndView modelAndView, @ModelAttribute("customer") GimnasioRootModel customer) {
        LoggerMapper.methodIn(Level.INFO, "update", customer, this.getClass());
        modelAndView.setViewName("management/customer");
        User user = userService.cargarUsuarioCompleto(modelAndView);
        customer.setUsuarioModificacion(user.getUsername());
        try {
            gimnasioRootService.update(customer);
            modelAndView.addObject("updateOk", "Actualización correcta");
        } catch (Exception e) {
            modelAndView.addObject("updateProblem", "Hubo un problema con la actualización");
            LoggerMapper.log(Level.ERROR, "update", e.getMessage(), this.getClass());
        }
        modelAndView.addObject("customer", customer);
        LoggerMapper.methodOut(Level.INFO, "update", modelAndView, this.getClass());
        return modelAndView;
    }

}
