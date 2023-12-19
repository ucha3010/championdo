package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.Menu1Model;
import com.championdo.torneo.model.Menu2Model;
import com.championdo.torneo.service.Menu1Service;
import com.championdo.torneo.service.Menu2Service;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminMenu")
public class AdminMenuController {

    @Autowired
    private Menu1Service menu1Service;

    @Autowired
    private Menu2Service menu2Service;

    @Autowired
    private UserService userService;

    @GetMapping("/menuList")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView menuList(ModelAndView modelAndView) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.setViewName("management/adminMenu1");
        modelAndView.addObject("menu1Model", new Menu1Model());
        modelAndView.addObject("menu2Model", new Menu2Model());
        modelAndView.addObject("menu1List", menu1Service.findAll());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/menu1/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView dragMenu1(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        menu1Service.dragOfPosition(oldIndex, newIndex);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return menuList(modelAndView);
    }

    @GetMapping("/menu2/{idMenu1}/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView dragMenu2(ModelAndView modelAndView, @PathVariable int idMenu1, @PathVariable int oldIndex, @PathVariable int newIndex) {
        menu2Service.dragOfPosition(idMenu1, oldIndex, newIndex);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return menuList(modelAndView);
    }

    @PostMapping("/menu1-add")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView addMenu1(ModelAndView modelAndView, @ModelAttribute("menu1Model") Menu1Model menu1Model) {
        menu1Model.setPosition(menu1Service.findMaxPosition() + 1);
        menu1Service.add(menu1Model);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return menuList(modelAndView);
    }

    @PostMapping("/menu2-add")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView addMenu2(ModelAndView modelAndView, @ModelAttribute("menu2Model") Menu2Model menu2Model) {
        menu2Model.setPosition(menu2Service.findMaxPosition(menu2Model.getIdMenu1()) + 1);
        menu2Service.add(menu2Model);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return menuList(modelAndView);
    }

    @GetMapping("/menu1-remove/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView removeMenu1(ModelAndView modelAndView, @PathVariable int id) {
        try{
            menu1Service.delete(id);
        } catch (
                RemoveException re) {
            modelAndView.addObject("removeProblem", re.getMessage());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return menuList(modelAndView);
    }

    @GetMapping("/menu2-remove/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView removeMenu2(ModelAndView modelAndView, @PathVariable int id) {
    try{
        menu2Service.delete(id);
    } catch (
    RemoveException re) {
        modelAndView.addObject("removeProblem", re.getMessage());
    }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return menuList(modelAndView);
    }

}
