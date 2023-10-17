package com.championdo.torneo.service;


import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.CinturonModel;
import com.championdo.torneo.model.GimnasioRootModel;

import java.util.List;

public interface CinturonService {

    List<CinturonModel> findAll(int codigoGimnasio);

    CinturonModel findById(int id);

    CinturonModel findByCodigoGimnasioAndPosition(int codigoGimnasio, int position);

    void add(CinturonModel cinturonModel);

    void update(CinturonModel cinturonModel);

    void delete(int idCinturon) throws RemoveException;

    void dragOfPosition(int codigoGimnasio, int initialPosition, int finalPosition);

    int findMaxPosition(int codigoGimnasio);

    void deleteFromRoot (int idGimnasioRootModel);

    void addFromRoot(GimnasioRootModel customer);

    int findPositionById(int id);
}
