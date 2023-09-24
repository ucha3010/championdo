package com.championdo.torneo.service;


import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.model.PoomsaeModel;

import java.util.List;

public interface PoomsaeService {

    List<PoomsaeModel> findAll(int codigoGimnasio);

    PoomsaeModel findById(int id);

    PoomsaeModel findByCodigoGimnasioAndNombre(int codigoGimnasio, String nombre);

    void add(PoomsaeModel poomsaeModel);

    void update(PoomsaeModel poomsaeModel);

    void delete(int idPoomsae) throws RemoveException;

    void dragOfPosition(int codigoGimnasio, int initialPosition, int finalPosition);

    int findMaxPosition(int codigoGimnasio);

    void deleteFromRoot (int idGimnasioRootModel);

    void addFromRoot(GimnasioRootModel customer);
}
