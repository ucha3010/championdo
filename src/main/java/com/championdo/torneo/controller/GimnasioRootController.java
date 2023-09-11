package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.service.GimnasioRootService;
import com.championdo.torneo.service.GimnasioService;
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
    private GimnasioService gimnasioService;

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
    public ModelAndView customersId(ModelAndView modelAndView,@PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "customersId", "id: " + id, this.getClass());
        modelAndView.setViewName("management/updateCustomer");
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("customer", gimnasioRootService.findById(id));
        LoggerMapper.methodOut(Level.INFO, "customersId", modelAndView, this.getClass());
        return modelAndView;
    }

    @PostMapping("/updateCustomer")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView updateCustomer(ModelAndView modelAndView, @ModelAttribute("customer") GimnasioRootModel customer) {
        LoggerMapper.methodIn(Level.INFO, "updateCustomer", customer, this.getClass());
        modelAndView.setViewName("management/updateCustomer");
        User user = userService.cargarUsuarioCompleto(modelAndView);
        customer.setUsuarioModificacion(user.getUsername());
        try {
            gimnasioRootService.update(customer);
            modelAndView.addObject("updateOk", "Actualización correcta");
        } catch (Exception e) {
            modelAndView.addObject("updateProblem", "Hubo un problema con la actualización");
            LoggerMapper.log(Level.ERROR, "updateCustomer", e.getMessage(), this.getClass());
        }
        modelAndView.addObject("customer", customer);
        LoggerMapper.methodOut(Level.INFO, "updateCustomer", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/formNewCustomer")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView formNewCustomer(ModelAndView modelAndView) {
        LoggerMapper.methodIn(Level.INFO, "formNewCustomer", "", this.getClass());
        modelAndView.setViewName("management/addCustomer");
        userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("customer", new GimnasioRootModel());
        LoggerMapper.methodOut(Level.INFO, "formNewCustomer", modelAndView, this.getClass());
        return modelAndView;
    }

    @PostMapping("/customers")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView addCustomer(ModelAndView modelAndView, @ModelAttribute("customer") GimnasioRootModel customer) {
        LoggerMapper.methodIn(Level.INFO, "addCustomer", customer, this.getClass());
        User user = userService.cargarUsuarioCompleto(modelAndView);
        customer.setUsuarioModificacion(user.getUsername());
        try {
            customer = gimnasioRootService.add(customer);
            GimnasioModel gimnasioModel = gimnasioService.addFromRoot(customer);
            userService.addFromRoot(customer, gimnasioModel);
            return customers(modelAndView);
        } catch (Exception e) {
            modelAndView.setViewName("management/addCustomer");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("addProblem", "Hubo un problema con la actualización");
            LoggerMapper.log(Level.ERROR, "addCustomer", e.getMessage(), this.getClass());
        }
        LoggerMapper.methodOut(Level.INFO, "addCustomer", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/deleteCustomer/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView deleteCustomer(ModelAndView modelAndView,@PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "deleteCustomer", "id: " + id, this.getClass());
        userService.cargarUsuarioCompleto(modelAndView);
        gimnasioRootService.delete(id);
        LoggerMapper.methodOut(Level.INFO, "deleteCustomer", modelAndView, this.getClass());
        return customers(modelAndView);
    }

}
