package com.championdo.torneo.service;


import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.TorneoModel;

import java.util.Date;
import java.util.List;

public interface TorneoService {

    List<TorneoModel> findAll(int codigoGimnasio);

    TorneoModel findById(int id);

    void add(TorneoModel torneoModel);

    void update(TorneoModel torneoModel);

    void delete(int id) throws RemoveException;

    void deleteByCodigoGimnasio(int codigoGimnasio);

    List<TorneoModel> findAllowed(Date date, String tournamentType);
}
