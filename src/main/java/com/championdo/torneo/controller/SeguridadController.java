package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/seguridad")
public class SeguridadController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private FirmaCodigoService firmaCodigoService;
    @Autowired
    private FormularioService formularioService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private UserService userService;

    @PostMapping("/validarCodigo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView validarCodigo(@ModelAttribute("firmaCodigoModel") FirmaCodigoModel firmaCodigoModel) {

        LoggerMapper.methodIn(Level.INFO, "validarCodigo", firmaCodigoModel, getClass());
        ModelAndView modelAndView = new ModelAndView();
        User userLogged = userService.cargarUsuarioCompleto(modelAndView);
        PdfModel pdfModelGeneral = null;
        List<File> files = new ArrayList<>();
        try {
            seguridadService.validarIntentos(firmaCodigoModel.getIdOperacion());
            String codigoEnviadoPorUsuario = firmaCodigoModel.getCodigo();
            firmaCodigoService.limpiarFirmasCaducadas();
            firmaCodigoModel = firmaCodigoService.findByIdOperacion(firmaCodigoModel.getIdOperacion());
            seguridadService.validarCodigo(codigoEnviadoPorUsuario, userLogged.getUsername(), firmaCodigoModel);


            if (Constantes.INSCRIPCION_GIMNASIO.equals(firmaCodigoModel.getOperativaOriginal())) {
                //TODO DAMIAN esto de dentro del if tiene que ir a otro service
                InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(firmaCodigoModel.getIdOperacion());
                pdfModelGeneral = pdfService.getPdfInscripcionTaekwondo(inscripcionTaekwondoModel);
                pdfModelGeneral.setIdInscripcion(inscripcionTaekwondoModel.getId());
                if (inscripcionTaekwondoModel.isMayorLicencia()) {
                    File pdfMandato = pdfService.generarPdfMandato(pdfModelGeneral);
                    files.add(pdfMandato);
                }
                File pdfAutorizacionMayor18 = pdfService.generarPdfAutorizacionMayor18(pdfModelGeneral);
                files.add(pdfAutorizacionMayor18);
                if (inscripcionTaekwondoModel.isDomiciliacionSEPA()) {
                    File pdfNormativaSEPA = pdfService.generarPdfNormativaSEPA(pdfModelGeneral);
                    files.add(pdfNormativaSEPA);
                }
                if (inscripcionTaekwondoModel.isMayorAutorizaWhatsApp()) {
                    //TODO DAMIAN hacer pdf WhatsApp (habrá que hacer un checkbox en formularioInscPropia)
                }
                emailService.sendGymJoining(inscripcionTaekwondoModel, files);
            }

            modelAndView.setViewName(firmaCodigoModel.getPaginaFirmaOk());
        } catch (ValidationException ve) {
            LoggerMapper.log(Level.ERROR, "seguridad/validarCodigo", ve.getMessage(), getClass());
            modelAndView.addObject("firmaCodigoModel", new FirmaCodigoModel(firmaCodigoModel.getIdOperacion(), null, null, null, null));
            modelAndView.setViewName("firma/envioCodigo");
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "seguridad/validarCodigo", e.getMessage(), getClass());
        }

        if (pdfModelGeneral != null) {
            modelAndView.addObject("inscripcionCorrecta", "inscripcionCorrecta");
            modelAndView.addObject("inscripcionError", "");
        } else {
            modelAndView.addObject("inscripcionCorrecta", "");
            modelAndView.addObject("inscripcionError", "inscripcionError");
        }
        LoggerMapper.methodOut(Level.INFO, "seguridad/validarCodigo", firmaCodigoModel, getClass());
        return modelAndView;
    }

}
