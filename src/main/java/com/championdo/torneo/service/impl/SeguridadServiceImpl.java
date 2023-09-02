package com.championdo.torneo.service.impl;

import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.model.FirmaModel;
import com.championdo.torneo.service.FirmaService;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service()
public class SeguridadServiceImpl implements SeguridadService {

    @Autowired
    private FirmaService firmaService;

    @Override
    public String obtenerCodigo() {
        String alphabet = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";

        Random r = new Random();
        StringBuilder codigo = new StringBuilder();
        for (int i=0; i < 6; i++) {
            codigo.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return codigo.toString();
    }

    @Override
    public void validarCodigo(String codigoEnviadoPorUsuario, String dni, FirmaCodigoModel firmaCodigoModel) throws ValidationException {
        if (firmaCodigoModel == null || codigoEnviadoPorUsuario == null || dni == null) {
            throw new ValidationException(Constantes.AVISO_VALIDACION_ERROR_DATOS_ENTRADA, "No existen datos a validar");
        } else {
            FirmaModel firmaModel = firmaService.findByIdOperacion(firmaCodigoModel.getIdOperacion());
            if (firmaModel.getId() == 0) {
                throw new ValidationException(Constantes.AVISO_VALIDACION_TIEMPO_EXCEDIDO, "Tiempo de validación de código excedido");
            }
            firmaModel.setNumeroIntentos(firmaModel.getNumeroIntentos() + 1);
            if (!codigoEnviadoPorUsuario.equals(firmaCodigoModel.getCodigo()) || !dni.equals(firmaCodigoModel.getDni())) {
                firmaService.update(firmaModel);
                throw new ValidationException(Constantes.AVISO_VALIDACION_DATOS_NO_VALIDOS, "Datos de entrada no válidos");
            } else {
                firmaModel.setFirmado(Boolean.TRUE);
                firmaService.update(firmaModel);
            }
        }
    }

    @Override
    public void validarIntentos(int idOperacion) throws ValidationException {
        FirmaModel firmaModel = firmaService.findByIdOperacion(idOperacion);
        if (firmaModel.getNumeroIntentos() > 2) {
            throw new ValidationException(Constantes.AVISO_VALIDACION_NUMERO_INTENTOS_SUPERADO, "Ha superado el número de intentos válidos");
        } else if (firmaModel.isFirmado()) {
            throw new ValidationException(Constantes.AVISO_VALIDACION_OPERACION_FIRMADA_ANTES, "La operación ha sido firmada con anterioridad");
        } else if (firmaModel.getNumeroIntentos() == 0) {
            if (firmaModel.getId() != 0) {
                firmaService.delete(firmaModel.getId());
            }
            firmaService.add(new FirmaModel(idOperacion));
        }
    }
}
