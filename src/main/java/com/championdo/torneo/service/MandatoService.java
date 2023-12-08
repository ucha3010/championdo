package com.championdo.torneo.service;

import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.MandatoModel;

import java.util.List;

public interface MandatoService {

    List<MandatoModel> findAll(int codigoGimnasio);
    MandatoModel findById(int id);
    List<MandatoModel> findByDniMandante(int codigoGimnasio, String dniMandante);
    List<MandatoModel> findByDniMandanteAndMandatoFirmadoFalse(int codigoGimnasio, String dniMandante);
    MandatoModel add(MandatoModel mandatoModel) throws ValidationException;
    MandatoModel update(MandatoModel mandatoModel) throws ValidationException;
    void delete(int idMandato);
    void fillMandato(MandatoModel mandatoModel, boolean adulto, int codigoGimnasio);
    MandatoModel fromInscripcionTaekwondoToMandato(InscripcionTaekwondoModel inscripcionTaekwondoModel);
    void crearEnviarArchivosInscripcionTaekwondo(FirmaCodigoModel firmaCodigoModel) throws SenderException, ValidationException;

}
