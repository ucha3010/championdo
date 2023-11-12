package com.championdo.torneo.service;


import com.championdo.torneo.entity.Cinturon;
import com.championdo.torneo.exception.PositionException;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.CinturonModel;

import java.util.List;

public interface CinturonService {

    List<CinturonModel> findAll(int codigoGimnasio);

    CinturonModel findById(int id);

    Cinturon findByIdEntity(int id);

    void add(CinturonModel cinturonModel);

    void update(CinturonModel cinturonModel);

    void delete(int idCinturon) throws RemoveException;

    void verifyDragOfPositionAvailable(int codigoGimnasio, int initialPosition, int finalPosition) throws PositionException;

    void dragOfPosition(int codigoGimnasio, int initialPosition, int finalPosition);

    int findMaxPosition(int codigoGimnasio);
}
