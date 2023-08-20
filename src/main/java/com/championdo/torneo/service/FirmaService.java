package com.championdo.torneo.service;


import com.championdo.torneo.model.FirmaModel;

import java.util.List;

public interface FirmaService {

    List<FirmaModel> findAll();

    FirmaModel findById(int id);

    FirmaModel add(FirmaModel firmaModel);

    void update(FirmaModel firmaModel);

    void delete(int idFirma);

    FirmaModel findByIdOperacion(int idOperacion);

}
