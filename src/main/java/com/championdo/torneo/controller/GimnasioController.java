package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.PdfModel;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
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
    private PdfService pdfService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private UserService userService;

    @GetMapping("/tipoInscripcion")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tipoInscripcion(ModelAndView modelAndView) {
        modelAndView.setViewName("gimnasio/formularioTipoInscripcionGimnasio");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = inscripcionTaekwondoService.findByMayorDni(usuario.getUsername());
        if (!inscripcionTaekwondoModelList.isEmpty()) {
            for (InscripcionTaekwondoModel inscripcionTaekwondoModel : inscripcionTaekwondoModelList) {
                if (!inscripcionTaekwondoModel.isAutorizadoMenor()) {
                    modelAndView.addObject("ocultarAdulto", "ocultarAdulto");
                }
            }
            modelAndView.addObject("inscripciones", inscripcionTaekwondoModelList);
        }
        modelAndView.addObject("deleteEnable", Boolean.parseBoolean(inscripcionTaekwondoService.getDeleteEnable(usuario.getCodigoGimnasio()).getValor()));
        LoggerMapper.methodOut(Level.INFO, "gimnasio/tipoInscripcion", modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/formularioInscripcion/{tipo}/{licencia}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView formularioInscripcion(ModelAndView modelAndView, @PathVariable String tipo, @PathVariable String licencia) {
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        modelAndView.addObject("accountBoxEnable", Boolean.parseBoolean(inscripcionTaekwondoService.getAccountBoxEnable(usuario.getCodigoGimnasio()).getValor()));
        if ("infantil".equalsIgnoreCase(tipo)) {
            modelAndView.setViewName("gimnasio/formularioInscMenorGimnasio");
            modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(usuario, true));
        } else {
            modelAndView.setViewName("gimnasio/formularioInscPropiaGimnasio");
            modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscPropiaGimnasio(usuario));
        }
        modelAndView.addObject("licencia", "con licencia".equals(licencia));
        formularioService.cargarDesplegables(modelAndView, usuario.getCodigoGimnasio());
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
        userService.cargarUsuarioCompleto(modelAndView);
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(id);
        if (!inscripcionTaekwondoModel.isAutorizadoMenor()) {
            modelAndView.setViewName("gimnasio/vistaInscPropiaGimnasio");
        } else {
            modelAndView.setViewName("gimnasio/vistaInscMenorGimnasio");
        }
        modelAndView.addObject("inscripcion", inscripcionTaekwondoModel);
        modelAndView.addObject("pdfModel", new PdfModel());
        LoggerMapper.methodOut(Level.INFO, "gimnasio/getInscripcion", modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("isAuthenticated()")
    public void descargarPdf(@ModelAttribute("pdfModel") PdfModel pdfModel, HttpServletResponse response) {
        pdfService.descargarArchivo(pdfModel, response, pdfModel.getSeccion());
        LoggerMapper.log(Level.INFO, "formulario/descargarPdf", "Descarga de documento correcta", getClass());
    }

    @GetMapping("/eliminarInscripcion/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView eliminarInscripcion(ModelAndView modelAndView, @PathVariable int id) {
        inscripcionTaekwondoService.delete(id);
        return tipoInscripcion(modelAndView);
    }

    @GetMapping("/normativa-sepa/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView normativaSepa(ModelAndView modelAndView, @PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "gimnasio/normativa-sepa", id, getClass());
        modelAndView.setViewName("gimnasio/normativaSepaGimnasio");
        com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
        InscripcionTaekwondoModel inscripcion = inscripcionTaekwondoService.findById(id);
        modelAndView.addObject("inscripcion", inscripcion);
        LoggerMapper.methodOut(Level.INFO, "gimnasio/normativa-sepa", modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/normativa-sepa-firmado")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView normativaSepaFirmado(ModelAndView modelAndView, @RequestParam("idInscripcion") Integer idInscripcion, @RequestParam("file") MultipartFile file) {

        LoggerMapper.methodIn(Level.INFO, "gimnasio/normativa-sepa", idInscripcion, getClass());
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(idInscripcion);
        if(pdfService.subirArchivo(pdfService.getPdfInscripcionTaekwondo(inscripcionTaekwondoModel), file, Constantes.SECCION_NORMATIVA_SEPA_FIRMADO)) {
            inscripcionTaekwondoModel.setMandatoSEPAFirmado(Boolean.TRUE);
            inscripcionTaekwondoModel.setExtensionSEPAFirmado(pdfService.getFileExtension(file));
            inscripcionTaekwondoService.update(inscripcionTaekwondoModel);
            try {
                emailService.confirmAdminSepaSigned(inscripcionTaekwondoModel);
            } catch (Exception e) {
                LoggerMapper.log(Level.ERROR, "gimnasio/normativa-sepa", e.getMessage(), getClass());
            }
            LoggerMapper.methodOut(Level.INFO, "gimnasio/normativa-sepa", modelAndView, getClass());
            return tipoInscripcion(modelAndView);
        } else {
            modelAndView.addObject("subidaError", "Error en la subida del archivo");
            LoggerMapper.methodOut(Level.INFO, "gimnasio/normativa-sepa", modelAndView, getClass());
            return normativaSepa(modelAndView, idInscripcion);
        }
    }

    private ModelAndView logicaComunGuardar (UserAutorizacionModel userAutorizacionModel, boolean menor) {

        String recurso = (menor ? "guardarMenor" : "gaurdarPropia");
        LoggerMapper.methodIn(Level.INFO, "gimnasio/" + recurso, userAutorizacionModel, getClass());

        ModelAndView modelAndView = new ModelAndView();
        User userLogged = userService.cargarUsuarioCompleto(modelAndView);
        userAutorizacionModel.getMayorAutorizador().setCodigoGimnasio(userLogged.getCodigoGimnasio());
        if (menor) {
            formularioService.fillObjects(userAutorizacionModel.getAutorizado());
        }
        formularioService.fillObjects(userAutorizacionModel.getMayorAutorizador());
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.add(userAutorizacionModel);
        FirmaCodigoModel firmaCodigoModel = new FirmaCodigoModel(inscripcionTaekwondoModel.getId(),
                seguridadService.obtenerCodigo(), inscripcionTaekwondoModel.getMayorDni(),
                "gimnasio/formularioInscFinalizadaGimnasio", Constantes.INSCRIPCION_TAEKWONDO);
        modelAndView = seguridadService.enviarCodigoFirma(modelAndView, firmaCodigoModel, userLogged);
        LoggerMapper.methodOut(Level.INFO, "gimnasio/" + recurso, firmaCodigoModel, getClass());
        return modelAndView;

    }

}
