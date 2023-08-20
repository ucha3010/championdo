package com.championdo.torneo.service;

import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.model.FirmaCodigoModel;

public interface SeguridadService {

    String obtenerCodigo();

    void validarCodigo(String codigoEnviadoPorUsuario, String dni, FirmaCodigoModel firmaCodigoModel) throws ValidationException;

    void validarIntentos(int idOperacion) throws ValidationException;
}