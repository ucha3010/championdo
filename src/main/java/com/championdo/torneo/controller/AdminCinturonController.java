package com.championdo.torneo.controller;

import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.CinturonModel;
import com.championdo.torneo.service.CinturonService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminCinturon")
public class AdminCinturonController {

    @Autowired
    private CinturonService cinturonService;

    @GetMapping("/cinturonList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView cinturonList(ModelAndView modelAndView) {
        modelAndView.setViewName("adminCinturon");
        modelAndView.addObject("cinturonModel", new CinturonModel());
        modelAndView.addObject("cinturonList", cinturonService.findAll());
        LoggerMapper.log(Level.INFO, "cinturonList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/cinturon/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragCinturon(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        cinturonService.dragOfPosition(oldIndex, newIndex);
        return cinturonList(modelAndView);
    }

    @PostMapping("/addCinturon")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addCinturon(ModelAndView modelAndView, @ModelAttribute("cinturonModel") CinturonModel cinturonModel) {
        cinturonModel.setPosition(cinturonService.findMaxPosition() + 1);
        cinturonService.add(cinturonModel);
        return cinturonList(modelAndView);
    }

    @GetMapping("/cinturon/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeCinturon(ModelAndView modelAndView, @PathVariable int id) {
        try {
            cinturonService.delete(id);
        } catch (RemoveException re) {
            modelAndView.addObject("removeProblem", re.getMessage());
        }
        return cinturonList(modelAndView);
    }

}
