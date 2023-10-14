package com.championdo.torneo.service;


import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.model.TorneoModel;

import java.util.List;

public interface TorneoService {

    List<TorneoModel> findAll(int codigoGimnasio);

    TorneoModel findById(int id);

    void add(TorneoModel torneoModel);

    void update(TorneoModel torneoModel);

    void delete(int id) throws RemoveException;
}
