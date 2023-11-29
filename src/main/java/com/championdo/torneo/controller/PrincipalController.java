package com.championdo.torneo.controller;

import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.service.GimnasioRootService;
import com.championdo.torneo.service.InscripcionTaekwondoService;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    @Autowired
    private PrincipalService principalService;
    @Autowired
    private GimnasioRootService gimnasioRootService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;
    @Autowired
    private UserService userService;
// TODO DAMIAN falta hacer la renovación de licencia
    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView paginaPrincipal(ModelAndView modelAndView) {
        modelAndView.setViewName("principal");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = inscripcionTaekwondoService.findByMayorDni(usuario.getUsername());
        for (InscripcionTaekwondoModel inscripcionTaekwondoModel: inscripcionTaekwondoModelList) {
            if (!inscripcionTaekwondoModel.isInscripcionFirmada() || (inscripcionTaekwondoModel.isDomiciliacionSEPA() && !inscripcionTaekwondoModel.isMandatoSEPAFirmado())) {
                modelAndView.addObject("inscripcionTaekwondo", "Documentación pendiente de firma");
            }
        }
        modelAndView.addObject("inscripcion", principalService.findByDni(usuario.getUsername()));
        modelAndView.addObject("deleteEnable", Boolean.parseBoolean(principalService.getDeleteEnable(usuario.getCodigoGimnasio()).getValor()));
        modelAndView.addObject("gimnasioAvailable", gimnasioRootService.verifyEnable(usuario.getCodigoGimnasio()));
        LoggerMapper.methodOut(Level.INFO, "paginaPrincipal", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/principalTorneo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView paginaPrincipalTorneo(ModelAndView modelAndView) {
        modelAndView.setViewName("torneo/principalTorneo");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("inscripcion", principalService.findByDni(usuario.getUsername()));
        modelAndView.addObject("deleteEnable", Boolean.parseBoolean(principalService.getDeleteEnable(usuario.getCodigoGimnasio()).getValor()));
        LoggerMapper.methodOut(Level.INFO, "paginaPrincipalTorneo", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/eliminarInscripcion/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView eliminarInscripcion(ModelAndView modelAndView, @PathVariable int id) {
        principalService.deleteInscripcion(id);
        return paginaPrincipalTorneo(modelAndView);
    }

}
