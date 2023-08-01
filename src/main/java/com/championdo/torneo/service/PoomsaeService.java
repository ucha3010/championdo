package com.championdo.torneo.service;


import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.PoomsaeModel;

import java.util.List;

public interface PoomsaeService {

    List<PoomsaeModel> findAll();

    PoomsaeModel findById(int id);

    void add(PoomsaeModel poomsaeModel);

    void update(PoomsaeModel poomsaeModel);

    void delete(int idPoomsae) throws RemoveException;

    void dragOfPosition(int initialPosition, int finalPosition);

    int findMaxPosition();

}
