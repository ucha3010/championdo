package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.service.FirmaCodigoService;
import com.championdo.torneo.service.InscripcionTaekwondoService;
import com.championdo.torneo.service.SeguridadService;
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

@Controller
@RequestMapping("/seguridad")
public class SeguridadController {

    @Autowired
    private FirmaCodigoService firmaCodigoService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;
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
        try {
            seguridadService.validarIntentos(firmaCodigoModel.getIdOperacion());
            String codigoEnviadoPorUsuario = firmaCodigoModel.getCodigo();
            firmaCodigoService.limpiarFirmasCaducadas();
            firmaCodigoModel = firmaCodigoService.findByIdOperacion(firmaCodigoModel.getIdOperacion());
            seguridadService.validarCodigo(codigoEnviadoPorUsuario, userLogged.getUsername(), firmaCodigoModel);

            //Acá se agregan los procesos para generar y enviar archivos firmados
            if (Constantes.INSCRIPCION_GIMNASIO.equals(firmaCodigoModel.getOperativaOriginal())) {
                inscripcionTaekwondoService.crearEnviarArchivosInscripcionTaekwondo(firmaCodigoModel);
            }

            modelAndView.addObject("inscripcionCorrecta", "¡La inscripción se realizó con éxito!");
            modelAndView.setViewName(firmaCodigoModel.getPaginaFirmaOk());
        } catch (ValidationException ve) {
            LoggerMapper.log(Level.ERROR, "seguridad/validarCodigo", ve.getMessage(), getClass());
            modelAndView.addObject("firmaCodigoModel", new FirmaCodigoModel(firmaCodigoModel.getIdOperacion(),
                    null, null, null, firmaCodigoModel.getOperativaOriginal()));
            modelAndView.addObject("inscripcionError", ve.getMessage());
            modelAndView.setViewName("firma/envioCodigo");
        } catch (SenderException se) {
            LoggerMapper.log(Level.ERROR, "seguridad/validarCodigo", se.getMessage(), getClass());
            modelAndView.addObject("firmaCodigoModel", new FirmaCodigoModel(firmaCodigoModel.getIdOperacion(),
                    null, null, null, firmaCodigoModel.getOperativaOriginal()));
            modelAndView.addObject("inscripcionError", "Hubo un problema en el envío del correo " +
                    "electrónico. Por favor contacte con soporte técnico. Gracias.");
            modelAndView.setViewName("firma/envioCodigo");
        }

        LoggerMapper.methodOut(Level.INFO, "seguridad/validarCodigo", firmaCodigoModel, getClass());
        return modelAndView;
    }

}
