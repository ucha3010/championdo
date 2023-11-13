package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.service.InscripcionService;
import com.championdo.torneo.service.PdfService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.mysql.cj.util.StringUtils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/adminInscripcion")
public class AdminInscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private UserService userService;
    //TODO DAMIAN ver si tengo que meter control acceso en este controller cuando el cliente está deshabilitado

    @GetMapping("/inscripcionList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView inscripcionList(ModelAndView modelAndView) {
        modelAndView.setViewName("adminInscripcion");
        User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("inscripcionList", inscripcionService.findAll());
        modelAndView.addObject("inscripcionModel", new InscripcionModel());
        if(Boolean.parseBoolean(inscripcionService.getDeleteEnable(usuario.getCodigoGimnasio()).getValor())) {
            modelAndView.addObject("deleteEnable", "Deshabilitar borrar");
        } else {
            modelAndView.addObject("deleteEnable", "Habilitar borrar");
        }
        LoggerMapper.log(Level.INFO, "inscripcionList", modelAndView, this.getClass());
        return modelAndView;
    }

    @GetMapping("/pay/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updatePay(ModelAndView modelAndView, @PathVariable int id) {
        InscripcionModel inscripcionModel = inscripcionService.findById(id);
        inscripcionModel.setPagoRealizado(!inscripcionModel.isPagoRealizado());
        inscripcionModel.setFechaPago(new Date());
        inscripcionService.update(inscripcionModel);
        modelAndView.addObject("updateOK", "Pago de " + inscripcionModel.getNombreInscripto()
                + " " + inscripcionModel.getApellido1Inscripto()
                + (!StringUtils.isNullOrEmpty(inscripcionModel.getApellido2Inscripto()) ? " " + inscripcionModel.getApellido2Inscripto() : "")
                + " guardado correctamente");
        return inscripcionList(modelAndView);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView update(ModelAndView modelAndView, @ModelAttribute("inscripcionModel") InscripcionModel inscripcionModel) {
        InscripcionModel inscripcionModelBBDD = inscripcionService.findById(inscripcionModel.getId());
        inscripcionModelBBDD.setFechaPago(inscripcionModel.getFechaPago());
        inscripcionModelBBDD.setNotas(inscripcionModel.getNotas());
        inscripcionService.update(inscripcionModelBBDD);
        modelAndView.addObject("updateOK", "Cambios en inscripción de " + inscripcionModelBBDD.getNombreInscripto()
            + " " + inscripcionModelBBDD.getApellido1Inscripto()
            + (!StringUtils.isNullOrEmpty(inscripcionModelBBDD.getApellido2Inscripto()) ? " " + inscripcionModelBBDD.getApellido2Inscripto() : "")
            + " realizados correctamente");
        return inscripcionList(modelAndView);
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void descargarPdf(@ModelAttribute("inscripcionModel") InscripcionModel inscripcionModel, HttpServletResponse response) {
        inscripcionModel = inscripcionService.findById(inscripcionModel.getId());
        PdfModel pdfModel = new PdfModel();
        if(inscripcionModel.isInscripcionPropia()) {
            pdfModel.setDni(inscripcionModel.getDniInscripto());
        } else {
            pdfModel.setDni(inscripcionModel.getDniAutorizador());
            pdfModel.setDniMenor(inscripcionModel.getDniInscripto());
        }
        pdfModel.setFechaCampeonato(inscripcionModel.getFechaCampeonato());
        pdfModel.setIdInscripcion(inscripcionModel.getId());
        pdfService.descargarArchivo(pdfModel, response, Constantes.SECCION_TORNEO);
        LoggerMapper.log(Level.INFO, "adminInscripcion/descargarPdf", "Descarga de documento correcta", getClass());
    }

    @GetMapping("/deleteEnable")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deleteEnable(ModelAndView modelAndView) {
        User usuario = userService.cargarUsuarioCompleto(modelAndView);
        inscripcionService.changeValueDeleteEnable(usuario.getCodigoGimnasio());
        return inscripcionList(modelAndView);
    }

    @GetMapping("/deleteAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deleteAll(ModelAndView modelAndView) {
        inscripcionService.deleteAll();
        return inscripcionList(modelAndView);
    }

}
