package com.championdo.torneo.service;


import com.championdo.torneo.model.CalidadModel;

import java.util.List;

public interface CalidadService {

    List<CalidadModel> findAll();

    CalidadModel findById(int id);

    void add(CalidadModel calidadModel);

    void update(CalidadModel calidadModel);

    void delete(int idCalidad);

    void dragOfPosition(int initialPosition, int finalPosition);

    int findMaxPosition();

}
