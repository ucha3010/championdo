package com.championdo.torneo.service;


import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.TorneoGimnasioModel;

import java.util.List;

public interface TorneoGimnasioService {

    List<TorneoGimnasioModel> findAll(int idTorneo);

    TorneoGimnasioModel findById(int id);

    void add(TorneoGimnasioModel torneoGimnasioModel);

    void update(TorneoGimnasioModel torneoGimnasioModel);

    void delete(TorneoGimnasioModel torneoGimnasioModel) throws RemoveException;

    void dragOfPosition(int idTorneo, int initialPosition, int finalPosition);

    int findMaxPosition(int idTorneo);
}
