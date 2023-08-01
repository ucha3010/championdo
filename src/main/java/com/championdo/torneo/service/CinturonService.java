package com.championdo.torneo.service;


import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.CinturonModel;

import java.util.List;

public interface CinturonService {

    List<CinturonModel> findAll();

    CinturonModel findById(int id);

    void add(CinturonModel cinturonModel);

    void update(CinturonModel cinturonModel);

    void delete(int idCinturon) throws RemoveException;

    void dragOfPosition(int initialPosition, int finalPosition);

    int findMaxPosition();
}
