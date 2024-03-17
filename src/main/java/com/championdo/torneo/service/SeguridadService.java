package com.championdo.torneo.service;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.model.FirmaCodigoModel;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

public interface SeguridadService {

    String obtenerCodigo();

    ModelAndView enviarCodigoFirma(ModelAndView modelAndView, FirmaCodigoModel firmaCodigoModel, User userLogged, List<File> files);

    void validarCodigo(String codigoEnviadoPorUsuario, String dni, FirmaCodigoModel firmaCodigoModel) throws ValidationException;

    void validarIntentos(int idOperacion) throws ValidationException;

    void gimnasioHabilitadoAdministracion(int idGimnasio, String uri) throws AccessDeniedException;

    void usuarioGimnasioHabilitadoAdministracion(String username, int idGimnasio, String uri) throws AccessDeniedException;
}
