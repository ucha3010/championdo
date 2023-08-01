package com.championdo.torneo.service;


import com.championdo.torneo.model.GimnasioModel;

import java.util.List;

public interface GimnasioService {

    List<GimnasioModel> findAll();

    GimnasioModel findById(int id);

    void add(GimnasioModel gimnasioModel);

    void update(GimnasioModel gimnasioModel);

    void delete(int idGimnasio);

    void dragOfPosition(int initialPosition, int finalPosition);

    int findMaxPosition();
}
