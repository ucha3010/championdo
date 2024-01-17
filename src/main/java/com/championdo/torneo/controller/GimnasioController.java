package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.*;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gimnasio")
public class GimnasioController {

    @Autowired
    private FormularioService formularioService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;
    @Autowired
    private Menu2Service menu2Service;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private GimnasioRootMenu2Service gimnasioRootMenu2Service;
    @Autowired
    private GimnasioRootService gimnasioRootService;
    @Autowired
    private SessionData sessionData;

    @GetMapping("/tipoInscripcion")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tipoInscripcion(ModelAndView modelAndView) {
        modelAndView.setViewName("formularioInscripcionSeleccionGimnasio");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = inscripcionTaekwondoService.findByMayorDni(usuario.getUsername());
        if (!inscripcionTaekwondoModelList.isEmpty()) {
            modelAndView.addObject("inscripciones", inscripcionTaekwondoModelList);
        }
        modelAndView.addObject("operativaOriginal", Constantes.INSCRIPCION_TAEKWONDO);
        modelAndView.addObject("inscripcionA", "Taekwondo");
        modelAndView.addObject("controller", "gimnasio");
        modelAndView.addObject("gimnasios", gimnasioRootService.findByMenu2Url("/gimnasio/tipoInscripcion"));
        //TODO DAMIAN usuario viene sin codigoGimnasio. Hay que habilitar para borrado SOLO las inscripciones del gimnasio que traigo acá en id
        modelAndView.addObject("deleteEnable", Boolean.parseBoolean(inscripcionTaekwondoService.getDeleteEnable(usuario.getCodigoGimnasio()).getValor()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/tipoInscripcionConGimnasio/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tipoInscripcionConGimnasio(ModelAndView modelAndView, @PathVariable Integer id) {
        modelAndView.setViewName("gimnasio/formularioTipoInscripcionGimnasio");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        sessionData.setGimnasioRootModel(gimnasioRootService.findById(id));
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = inscripcionTaekwondoService.findByMayorDni(usuario.getUsername());
        if (!inscripcionTaekwondoModelList.isEmpty()) {
            for (InscripcionTaekwondoModel inscripcionTaekwondoModel : inscripcionTaekwondoModelList) {
                if (!inscripcionTaekwondoModel.isAutorizadoMenor()) {
                    modelAndView.addObject("ocultarAdulto", "ocultarAdulto");
                }
            }
            modelAndView.addObject("inscripciones", inscripcionTaekwondoModelList);
        }
        modelAndView.addObject("operativaOriginal", Constantes.INSCRIPCION_TAEKWONDO);
        modelAndView.addObject("inscripcionA", "Taekwondo");
        modelAndView.addObject("controller", "gimnasio");
        modelAndView.addObject("gimnasio", gimnasioRootService.findById(id));
        //TODO DAMIAN usuario viene sin codigoGimnasio. Hay que habilitar para borrado SOLO las inscripciones del gimnasio que traigo acá en id
        modelAndView.addObject("deleteEnable", Boolean.parseBoolean(inscripcionTaekwondoService.getDeleteEnable(sessionData.getGimnasioRootModel().getId()).getValor()));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/formularioInscripcion/{id}/{tipo}/{licencia}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView formularioInscripcion(ModelAndView modelAndView, @PathVariable Integer id, @PathVariable String tipo, @PathVariable String licencia) {
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
//        usuario.setCodigoGimnasio(id);
        modelAndView.addObject("accountBoxEnable", Boolean.parseBoolean(inscripcionTaekwondoService.getAccountBoxEnable(sessionData.getGimnasioRootModel().getId()).getValor()));
        if ("infantil".equalsIgnoreCase(tipo)) {
            modelAndView.setViewName("gimnasio/formularioInscMenorGimnasio");
            modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(usuario, true));
        } else {
            modelAndView.setViewName("gimnasio/formularioInscPropiaGimnasio");
            modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscPropiaGimnasio(usuario));
        }
        modelAndView.addObject("licencia", "con licencia".equals(licencia));
        formularioService.cargarDesplegables(modelAndView, sessionData.getGimnasioRootModel().getId());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
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
        principalService.cargaBasicaCompleta(modelAndView);
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(id);
        if (!inscripcionTaekwondoModel.isAutorizadoMenor()) {
            modelAndView.setViewName("gimnasio/vistaInscPropiaGimnasio");
        } else {
            modelAndView.setViewName("gimnasio/vistaInscMenorGimnasio");
        }
        modelAndView.addObject("inscripcion", inscripcionTaekwondoModel);
        modelAndView.addObject("pdfModel", new PdfModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("isAuthenticated()")
    public void descargarPdf(@ModelAttribute("pdfModel") PdfModel pdfModel, HttpServletResponse response) {
        pdfService.descargarArchivo(pdfModel, response, pdfModel.getSeccion());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), "Descarga de documento correcta", getClass());
    }

    @GetMapping("/eliminarInscripcion/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView eliminarInscripcion(ModelAndView modelAndView, @PathVariable int id) {
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(id);
        inscripcionTaekwondoService.delete(inscripcionTaekwondoModel);
        emailService.confirmAdminDelete(inscripcionTaekwondoModel.getCodigoGimnasio(), "gimnasio",
                usuario, inscripcionTaekwondoModel.getAutorizadoNombre());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return tipoInscripcion(modelAndView);
    }

    @GetMapping("/normativa-sepa/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView normativaSepa(ModelAndView modelAndView, @PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "gimnasio/normativa-sepa", id, getClass());
        modelAndView.setViewName("gimnasio/normativaSepaGimnasio");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        InscripcionTaekwondoModel inscripcion = inscripcionTaekwondoService.findById(id);
        modelAndView.addObject("inscripcion", inscripcion);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/normativa-sepa-firmado")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView normativaSepaFirmado(ModelAndView modelAndView, @RequestParam("idInscripcion") Integer idInscripcion, @RequestParam("file") MultipartFile file) {

        LoggerMapper.methodIn(Level.INFO, "gimnasio/normativa-sepa", idInscripcion, getClass());
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(idInscripcion);
        if(pdfService.subirArchivo(pdfService.getPdfInscripcionTaekwondo(inscripcionTaekwondoModel), file, Constantes.SECCION_NORMATIVA_SEPA_FIRMADO)) {
            inscripcionTaekwondoModel.setDomiciliacionSEPAFirmada(Boolean.TRUE);
            inscripcionTaekwondoModel.setExtensionSEPAFirmado(pdfService.getFileExtension(file));
            inscripcionTaekwondoService.update(inscripcionTaekwondoModel);
            try {
                emailService.confirmAdminSepaSigned(inscripcionTaekwondoModel);
            } catch (Exception e) {
                LoggerMapper.log(Level.ERROR, "gimnasio/normativa-sepa", e.getMessage(), getClass());
            }
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return tipoInscripcion(modelAndView);
        } else {
            modelAndView.addObject("subidaError", "Error en la subida del archivo");
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return normativaSepa(modelAndView, idInscripcion);
        }
    }

    @GetMapping("/detalle/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detalle(ModelAndView modelAndView, @PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "gimnasio/detalle", id, getClass());
        modelAndView.setViewName("gimnasio/detalle");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        GimnasioRootModel gimnasioRootModel = gimnasioRootService.findById(id);
        modelAndView.addObject("gimnasio", gimnasioRootModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    private ModelAndView logicaComunGuardar (UserAutorizacionModel userAutorizacionModel, boolean menor) {

        String recurso = (menor ? "guardarMenor" : "gaurdarPropia");
        LoggerMapper.methodIn(Level.INFO, "gimnasio/" + recurso, userAutorizacionModel, getClass());

        ModelAndView modelAndView = new ModelAndView();
        User userLogged = principalService.cargaBasicaCompleta(modelAndView);
        if (menor) {
            formularioService.fillObjects(userAutorizacionModel.getAutorizado());
        }
        formularioService.fillObjects(userAutorizacionModel.getMayorAutorizador());
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.add(userAutorizacionModel, sessionData.getGimnasioRootModel().getId());
        FirmaCodigoModel firmaCodigoModel = new FirmaCodigoModel(inscripcionTaekwondoModel.getId(),
                seguridadService.obtenerCodigo(), inscripcionTaekwondoModel.getMayorDni(),
                "formularioInscFinalizada", Constantes.INSCRIPCION_TAEKWONDO, sessionData.getGimnasioRootModel().getId());
        modelAndView = seguridadService.enviarCodigoFirma(modelAndView, firmaCodigoModel, userLogged);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;

    }

}
