package com.championdo.torneo.service;


import com.championdo.torneo.model.PaisModel;

import java.util.List;

public interface PaisService {

    List<PaisModel> findAll();

    PaisModel findById(int id);

    void add(PaisModel paisModel);

    void update(PaisModel paisModel);

    void delete(int idPais);

    void dragOfPosition(int initialPosition, int finalPosition);

    int findMaxPosition();
}
