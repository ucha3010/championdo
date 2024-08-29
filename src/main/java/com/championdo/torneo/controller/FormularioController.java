package com.championdo.torneo.controller;

import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.FormularioService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import jakarta.persistence.PersistenceException;
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
@RequestMapping("/formulario")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @GetMapping("/alta")
    @PreAuthorize("permitAll()")
    public ModelAndView getAlta(ModelAndView modelAndView) {
        modelAndView.setViewName("formularioAlta");
        formularioService.cargarDesplegablesBasicos(modelAndView);
        if (modelAndView.isEmpty() || !modelAndView.getModel().containsKey("userModel")) {
            modelAndView.addObject("userModel", new UserModel());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/alta")
    @PreAuthorize("permitAll()")
    public ModelAndView alta(ModelAndView modelAndView, @ModelAttribute("userModel") UserModel userModel) {
        boolean altaCorrecta = true;
        if(userService.findByUsername(userModel.getUsername()) == null) {
            try {
                emailService.sendUserAdded(userService.altaNuevoUsuario(userModel, Constantes.ROLE_USER));
            } catch (PersistenceException e) {
                modelAndView.addObject("problemasAlta", "Problemas dando de alta usuario con DNI " + userModel.getUsername());
                LoggerMapper.log(Level.ERROR, "alta", e.getMessage(), this.getClass());
                altaCorrecta = false;
            } catch (SenderException se) {
                LoggerMapper.log(Level.ERROR, "alta", se.getMessage(), this.getClass());
            }
        } else {
            modelAndView.addObject("dniDadoDeAlta", "Ya existe un usuario dado de alta con DNI " + userModel.getUsername());
            altaCorrecta = false;
        }
        if(altaCorrecta) {
            modelAndView.addObject("altaUsuarioOK", userModel.getName() + " te has dado de alta correctamente");
            modelAndView.setViewName(Constantes.LOGIN);
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return modelAndView;
        } else {
            modelAndView.setViewName("formularioAlta");
            modelAndView.addObject("userModel", userModel);
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return getAlta(modelAndView);
        }

    }

}
