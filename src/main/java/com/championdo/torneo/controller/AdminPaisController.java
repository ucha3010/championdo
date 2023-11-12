package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.PaisModel;
import com.championdo.torneo.service.PaisService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminPais")
public class AdminPaisController {

    @Autowired
    private PaisService paisService;

    @Autowired
    private UserService userService;

    @GetMapping("/paisList")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView paisList(ModelAndView modelAndView) {
        User user = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.setViewName("management/adminPais");
        modelAndView.addObject("paisModel", new PaisModel());
        modelAndView.addObject("paisList", paisService.findAll());
        LoggerMapper.log(Level.INFO, "paisList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/pais/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView dragPais(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        paisService.dragOfPosition(oldIndex, newIndex);
        return paisList(modelAndView);
    }

    @PostMapping("/addPais")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView addPais(ModelAndView modelAndView, @ModelAttribute("paisModel") PaisModel paisModel) {
        paisModel.setPosition(paisService.findMaxPosition() + 1);
        paisService.add(paisModel);
        return paisList(modelAndView);
    }

    @GetMapping("/pais/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView removePais(ModelAndView modelAndView, @PathVariable int id) {
        paisService.delete(id);
        return paisList(modelAndView);
    }

}
