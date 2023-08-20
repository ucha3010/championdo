package com.championdo.torneo.service;


import com.championdo.torneo.model.FirmaCodigoModel;

import java.util.List;

public interface FirmaCodigoService {

    List<FirmaCodigoModel> findAll();

    FirmaCodigoModel findById(int id);

    FirmaCodigoModel add(FirmaCodigoModel firmaCodigoModel);

    void update(FirmaCodigoModel firmaCodigoModel);

    void delete(int idFirmaCodigo);

    FirmaCodigoModel findByIdOperacion(int idOperacion);

    void limpiarFirmasCaducadas();

}
