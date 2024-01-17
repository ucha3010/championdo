package com.championdo.torneo.service;


import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.GimnasioRootModel;

import java.util.List;

public interface GimnasioService {

    List<GimnasioModel> findAll(int codigoGimnasio);

    GimnasioModel findById(int id);

    GimnasioModel findByCodigoGimnasio(int codigoGimnasio);

    GimnasioModel add(GimnasioModel gimnasioModel);

    GimnasioModel update(GimnasioModel gimnasioModel);

    void delete(int idGimnasio);

    void dragOfPosition(int codigoGimnasio, int initialPosition, int finalPosition);

    int findMaxPosition(int codigoGimnasio);

/*    GimnasioModel addFromRoot (GimnasioRootModel gimnasioRootModel);

    void deleteFromRoot (int idGimnasioRootModel);*/
}
