package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.model.DocumentManagerModel;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.model.MandatoModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mandato")
public class MandatoController {

    @Autowired
    private MandatoService mandatoService;
    @Autowired
    private FormularioService formularioService;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;
    @Autowired
    private PaisService paisService;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private PrincipalService principalService;

    @GetMapping("/mandatos")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView mandatos(ModelAndView modelAndView) {
        modelAndView.setViewName("gimnasio/principalMandato");
        com.championdo.torneo.entity.User usuario = principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("mandatoModelList", mandatoService.findByDniMandante(usuario.getUsername()));
        modelAndView.addObject("mandatoModel", new MandatoModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/adulto")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView adulto(ModelAndView modelAndView) {
        modelAndView.setViewName("gimnasio/formularioMandatoAdulto");
        com.championdo.torneo.entity.User usuario = principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("mandatoModel", fillMandatoModel(usuario));
        modelAndView.addObject("titulo", "Mandato para licencia mayor de edad");
        modelAndView.addObject("gimnasios", gimnasioService.findByMenu2Url("/mandato/mandatos"));
        formularioService.cargarDesplegablesBasicos(modelAndView);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/menor/{menor}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView menorOInclisivo(ModelAndView modelAndView, @PathVariable boolean menor) {
        modelAndView.setViewName("gimnasio/formularioMandatoMenor");
        com.championdo.torneo.entity.User usuario = principalService.cargaBasicaCompleta(modelAndView);
        MandatoModel mandatoModel = fillMandatoModel(usuario);
        mandatoModel.setMenor(menor);
        modelAndView.addObject("mandatoModel", mandatoModel);
        modelAndView.addObject("gimnasios", gimnasioService.findByMenu2Url("/mandato/mandatos"));
        formularioService.cargarDesplegablesBasicos(modelAndView);
        titulo(modelAndView, menor);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/guardarAdulto")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView gaurdarAdulto(ModelAndView modelAndView, @ModelAttribute("mandatoModel") MandatoModel mandatoModel) {

        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        mandatoService.fillMandato(mandatoModel, true);
        try {
            commonMandato(modelAndView, mandatoModel, usuario);
        } catch (ValidationException e) {
            modelAndView.setViewName("gimnasio/formularioMandatoAdulto");
            modelAndView.addObject("addKO", e.getMessage());
            modelAndView.addObject("mandatoModel", mandatoModel);
            formularioService.cargarDesplegablesBasicos(modelAndView);
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/guardarMenor")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView guardarMenor(ModelAndView modelAndView, @ModelAttribute("mandatoModel") MandatoModel mandatoModel) {

        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        mandatoService.fillMandato(mandatoModel, false);
        try {
            commonMandato(modelAndView, mandatoModel, usuario);
        } catch (ValidationException e) {
            modelAndView.setViewName("gimnasio/formularioMandatoMenor");
            titulo(modelAndView, mandatoModel.isMenor());
            modelAndView.addObject("addKO", e.getMessage());
            modelAndView.addObject("mandatoModel", mandatoModel);
            formularioService.cargarDesplegablesBasicos(modelAndView);
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("isAuthenticated()")
    public void descargarPdf(@ModelAttribute("mandatoModel") MandatoModel mandatoModel, HttpServletResponse response) {
        mandatoModel = mandatoService.findById(mandatoModel.getId());
        PdfModel pdfModel = new PdfModel();
        if(mandatoModel.isAdulto()) {
            pdfModel.setDni(mandatoModel.getDniMandante());
        } else {
            pdfModel.setDni(mandatoModel.getDniMandante());
            pdfModel.setDniMenor(mandatoModel.getDniAutorizado());
        }
        pdfModel.setIdInscripcion(mandatoModel.getId());
        pdfService.descargarArchivo(pdfModel, response, Constantes.SECCION_MANDATO);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), "Descarga de documento correcta", getClass());
    }

    @GetMapping("/remove/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView removeMandato(ModelAndView modelAndView, @PathVariable int id) {
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        if (!mandatoService.findById(id).isMandatoFirmado()) {
            mandatoService.delete(id);
            modelAndView.addObject("deleteOK", "Mandato eliminado correctamente");
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return mandatos(modelAndView);
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

    private void titulo(ModelAndView modelAndView, boolean menor) {
        if (menor) {
            modelAndView.addObject("titulo", "Mandato para licencia menor de edad");
        } else {
            modelAndView.addObject("titulo", "Mandato para licencia inclusiva");
        }
    }

    private void commonMandato(ModelAndView modelAndView, MandatoModel mandatoModel, User userLogged) throws ValidationException{
        mandatoModel.setCorreoMandante(userLogged.getCorreo());
        mandatoModel = mandatoService.add(mandatoModel);
        List<File> files = new ArrayList<>();
        PdfModel pdfModel = pdfService.getPdfMandato(mandatoModel);
        DocumentManagerModel documentManagerModel = pdfService.createPdfFederativeLicenseMandate(pdfModel, false);
        files.add(new File(documentManagerModel.getFullPath()));
        FirmaCodigoModel firmaCodigoModel = new FirmaCodigoModel(mandatoModel.getId(),
                seguridadService.obtenerCodigo(), mandatoModel.getDniMandante(),
                "formularioInscFinalizada", Constantes.INSCRIPCION_MANDATO, mandatoModel.getCodigoGimnasio());
        seguridadService.enviarCodigoFirma(modelAndView, firmaCodigoModel, userLogged, files);
    }

}
