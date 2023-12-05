package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.model.MandatoModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mandato")
public class MandatoController {

    @Autowired
    private MandatoService mandatoService;
    @Autowired
    private FormularioService formularioService;
    @Autowired
    private GimnasioRootService gimnasioRootService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;
    @Autowired
    private PaisService paisService;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private UserService userService;

    @GetMapping("/adulto")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView adulto(ModelAndView modelAndView) {
        modelAndView.setViewName("gimnasio/formularioMandatoAdulto");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("mandatoModel", fillMandatoModel(usuario));
        formularioService.cargarDesplegables(modelAndView, usuario.getCodigoGimnasio());
        LoggerMapper.methodOut(Level.INFO, "adulto", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/menor/{menor}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView menorOInclisivo(ModelAndView modelAndView, @PathVariable boolean menor) {
        modelAndView.setViewName("gimnasio/formularioMandatoMenor");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("mandatoModel", fillMandatoModel(usuario));
        formularioService.cargarDesplegables(modelAndView, usuario.getCodigoGimnasio());
        if (menor) {
            modelAndView.addObject("titulo", "Mandato para licencia menor de edad");
        } else {
            modelAndView.addObject("titulo", "Mandato para licencia inclusiva");
        }
        LoggerMapper.methodOut(Level.INFO, "menorOInclisivo", modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/guardarAdulto")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView gaurdarAdulto(ModelAndView modelAndView, @ModelAttribute("mandatoModel") MandatoModel mandatoModel) {

        User userLogged = userService.cargarUsuarioCompleto(modelAndView);
        mandatoService.fillMandato(mandatoModel, true, userLogged.getCodigoGimnasio());
        modelAndView = commonMandato(modelAndView, mandatoModel, userLogged);
        LoggerMapper.methodOut(Level.INFO, "gaurdarAdulto", modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/guardarMenor")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView guardarMenor(ModelAndView modelAndView, @ModelAttribute("mandatoModel") MandatoModel mandatoModel) {

        User userLogged = userService.cargarUsuarioCompleto(modelAndView);
        mandatoService.fillMandato(mandatoModel, false, userLogged.getCodigoGimnasio());
        modelAndView = commonMandato(modelAndView, mandatoModel, userLogged);
        LoggerMapper.methodOut(Level.INFO, "guardarMenor", modelAndView, getClass());
        return modelAndView;
    }

    private MandatoModel fillMandatoModel(User usuario) {
        MandatoModel mandatoModel = new MandatoModel();
        mandatoModel.setNombreMandante(usuario.getName());
        mandatoModel.setApellido1Mandante(usuario.getLastname());
        mandatoModel.setApellido2Mandante(usuario.getSecondLastname());
        mandatoModel.setDniMandante(usuario.getUsername());
        mandatoModel.setDomicilioCalle(usuario.getDomicilioCalle());
        mandatoModel.setDomicilioNumero(usuario.getDomicilioNumero());
        mandatoModel.setDomicilioOtros(usuario.getDomicilioOtros());
        mandatoModel.setDomicilioCp(usuario.getDomicilioCp());
        mandatoModel.setDomicilioLocalidad(usuario.getDomicilioLocalidad());
        if(usuario.getIdPais() != 0) {
            mandatoModel.setPais(paisService.findById(usuario.getIdPais()).getNombre());
        }
        return mandatoModel;
    }

    private ModelAndView commonMandato(ModelAndView modelAndView, MandatoModel mandatoModel, User userLogged) {
        mandatoModel.setCorreoMandante(userLogged.getCorreo());
        mandatoModel = mandatoService.add(mandatoModel);
        FirmaCodigoModel firmaCodigoModel = new FirmaCodigoModel(mandatoModel.getId(),
                seguridadService.obtenerCodigo(), mandatoModel.getDniMandante(),
                "gimnasio/formularioInscFinalizadaGimnasio", Constantes.INSCRIPCION_MANDATO);
        modelAndView = seguridadService.enviarCodigoFirma(modelAndView, firmaCodigoModel, userLogged);
        return modelAndView;
    }

}
