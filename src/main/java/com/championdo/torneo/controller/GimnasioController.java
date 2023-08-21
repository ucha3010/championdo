package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.UserAutorizacionModel;
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

import java.util.List;

@Controller
@RequestMapping("/gimnasio")
public class GimnasioController {

    @Autowired
    private FormularioService formularioService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FirmaCodigoService firmaCodigoService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private UserService userService;

    @GetMapping("/tipoInscripcion")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tipoInscripcion(ModelAndView modelAndView) {
        modelAndView.setViewName("gimnasio/formularioTipoInscripcion");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = inscripcionTaekwondoService.findByMayorDni(usuario.getUsername());
        modelAndView.addObject("inscripcion", inscripcionTaekwondoModelList);
        LoggerMapper.methodOut(Level.INFO, "gimnasio/tipoInscripcion", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/formularioInscripcion/{tipo}/{licencia}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView formularioInscripcion(ModelAndView modelAndView, @PathVariable String tipo, @PathVariable String licencia) {
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        if ("infantil".equalsIgnoreCase(tipo)) {
            modelAndView.setViewName("gimnasio/formularioInscMenor");
            modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(usuario, true));
        } else {
            modelAndView.setViewName("gimnasio/formularioInscPropia");
            modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscPropiaGimnasio(usuario));
        }
        modelAndView.addObject("licencia", "con licencia".equals(licencia));
        formularioService.cargarDesplegables(modelAndView);
        LoggerMapper.methodOut(Level.INFO, "gimnasio/formularioInscripcion/" + tipo + "/" + licencia, modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/gaurdarPropia")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView gaurdarPropia(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {
        return logicaComunGuardar(userAutorizacionModel, Boolean.FALSE);
    }

    @PostMapping("/guardarMenor")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView guardarMenor(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {
        return logicaComunGuardar(userAutorizacionModel, Boolean.TRUE);
    }

    @GetMapping("/getInscripcion/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getInscripcion(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("tengoquehacer"); //TODO DAMIAN hacer html
        userService.cargarUsuarioCompleto(modelAndView);
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(id);
        modelAndView.addObject("inscripcion", inscripcionTaekwondoModel);
//        modelAndView.addObject("pdfModel", pdfService.getImpresion(inscripcionTaekwondoModel));
        LoggerMapper.methodOut(Level.INFO, "gimnasio/getInscripcion", modelAndView, getClass());
        return modelAndView;
    }

/*
    @GetMapping("/getMenorOInclisivo/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getMenorOInclisivo(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.setViewName("vistaInscMenorOInclisivo");
        userService.cargarUsuarioCompleto(modelAndView);
        InscripcionModel inscripcionModel = inscripcionService.findById(id);
        modelAndView.addObject("inscripcion", inscripcionModel);
        modelAndView.addObject("pdfModel", pdfService.getImpresion(inscripcionModel));
        LoggerMapper.log(Level.INFO, "formulario/getMenorOInclisivo", modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("isAuthenticated()")
    public void descargarPdf(@ModelAttribute("pdfModel") PdfModel pdfModel, HttpServletResponse response) {
        pdfService.descargarPdf(pdfModel, response);
        LoggerMapper.log(Level.INFO, "formulario/descargarPdf", "Descarga de documento correcta", getClass());
    }*/

    @GetMapping("/eliminarInscripcion/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView eliminarInscripcion(ModelAndView modelAndView, @PathVariable int id) {
        inscripcionTaekwondoService.delete(id);
        return tipoInscripcion(modelAndView);
    }

    private ModelAndView logicaComunGuardar (UserAutorizacionModel userAutorizacionModel, boolean menor) {

        String recurso = (menor ? "guardarMenor" : "gaurdarPropia");
        LoggerMapper.methodIn(Level.INFO, "gimnasio/" + recurso, userAutorizacionModel, getClass());

        ModelAndView modelAndView = new ModelAndView();
        User userLogged = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.setViewName("firma/envioCodigo");
        FirmaCodigoModel firmaCodigoModel = null;
        try {
            if (menor) {
                formularioService.fillObjects(userAutorizacionModel.getAutorizado());
            }
            formularioService.fillObjects(userAutorizacionModel.getMayorAutorizador());
            InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.add(userAutorizacionModel);
            firmaCodigoModel = firmaCodigoService.add(new FirmaCodigoModel(inscripcionTaekwondoModel.getId(),
                    seguridadService.obtenerCodigo(), inscripcionTaekwondoModel.getMayorDni(),
                    "formularioInscFinalizada", Constantes.INSCRIPCION_GIMNASIO));
            emailService.sendCodeValidation(userLogged, firmaCodigoModel.getCodigo());
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "gimnasio/" + recurso, e.getMessage(), getClass());
        }

        modelAndView.addObject("firmaCodigoModel", new FirmaCodigoModel(firmaCodigoModel.getIdOperacion(),
                null, null, null, Constantes.INSCRIPCION_GIMNASIO));
        LoggerMapper.methodOut(Level.INFO, "gimnasio/" + recurso, firmaCodigoModel, getClass());
        return modelAndView;

    }

}
