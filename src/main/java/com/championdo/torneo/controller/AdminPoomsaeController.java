package com.championdo.torneo.controller;

import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.PoomsaeModel;
import com.championdo.torneo.service.PoomsaeService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminPoomsae")
public class AdminPoomsaeController {

    @Autowired
    private PoomsaeService poomsaeService;

    @GetMapping("/poomsaeList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView poomsaeList(ModelAndView modelAndView) {
        modelAndView.setViewName("adminPoomsae");
        modelAndView.addObject("poomsaeModel", new PoomsaeModel());
        modelAndView.addObject("poomsaeList", poomsaeService.findAll());
        LoggerMapper.log(Level.INFO, "poomsaeList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/poomsae/{oldIndex}/{newIndex}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView dragPoomsae(ModelAndView modelAndView, @PathVariable int oldIndex, @PathVariable int newIndex) {
        poomsaeService.dragOfPosition(oldIndex, newIndex);
        return poomsaeList(modelAndView);
    }

    @PostMapping("/addPoomsae")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addPoomsae(ModelAndView modelAndView, @ModelAttribute("poomsaeModel") PoomsaeModel poomsaeModel) {
        poomsaeModel.setPosition(poomsaeService.findMaxPosition() + 1);
        poomsaeService.add(poomsaeModel);
        return poomsaeList(modelAndView);
    }

    @GetMapping("/poomsae/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removePoomsae(ModelAndView modelAndView, @PathVariable int id) {
        try {
            poomsaeService.delete(id);
        } catch (RemoveException re) {
            modelAndView.addObject("removeProblem", re.getMessage());
        }
        return poomsaeList(modelAndView);
    }

}
