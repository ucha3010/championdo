package com.championdo.torneo.controller;

import com.championdo.torneo.model.CategoriaModel;
import com.championdo.torneo.service.CategoriaService;
import com.championdo.torneo.service.CinturonService;
import com.championdo.torneo.service.PoomsaeService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminCategoria")
public class AdminCategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CinturonService cinturonService;
    @Autowired
    private PoomsaeService poomsaeService;

    @GetMapping("/categoriaList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView categoriaList(ModelAndView modelAndView) {
        modelAndView.setViewName("adminCategoria");
        modelAndView.addObject("categoriaModel", new CategoriaModel());
        modelAndView.addObject("categoriaList", categoriaService.findAllNameExtended());
        modelAndView.addObject("cinturonList", cinturonService.findAll());
        modelAndView.addObject("poomsaeList", poomsaeService.findAll());
        LoggerMapper.log(Level.INFO, "categoriaList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/categoria/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragCategoria(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        categoriaService.dragOfPosition(oldIndex, newIndex);
        return categoriaList(modelAndView);
    }

    @PostMapping("/addCategoria")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addCategoria(ModelAndView modelAndView, @ModelAttribute("categoriaModel") CategoriaModel categoriaModel) {
        categoriaModel.setPosition(categoriaService.findMaxPosition() + 1);
        categoriaService.add(categoriaModel);
        return categoriaList(modelAndView);
    }

    @GetMapping("/categoria/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeCategoria(ModelAndView modelAndView, @PathVariable int id) {
        categoriaService.delete(id);
        return categoriaList(modelAndView);
    }

}
