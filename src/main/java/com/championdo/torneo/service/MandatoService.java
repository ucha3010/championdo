package com.championdo.torneo.service;

import com.championdo.torneo.model.MandatoModel;

import java.util.List;

public interface MandatoService {

    List<MandatoModel> findAll(int codigoGimnasio);
    MandatoModel findById(int id);
    List<MandatoModel> findByDniMandante(int codigoGimnasio, String dniMandante);
    MandatoModel add(MandatoModel mandatoModel);
    void delete(int idMandato);
    void fillMandato(MandatoModel mandatoModel, boolean adulto, int codigoGimnasio);
}
