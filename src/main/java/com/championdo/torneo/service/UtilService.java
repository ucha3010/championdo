package com.championdo.torneo.service;


import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.UtilModel;

import java.util.List;

public interface UtilService {

    List<UtilModel> findAllStarsWith(String startWord, int codigoGimnasio);

    List<UtilModel> findAllEndWith(String endWord, int codigoGimnasio);

    UtilModel findByClave(String clave, int codigoGimnasio);

    void update(UtilModel utilModel);

    void addFromRoot(GimnasioModel customer);

    void deleteFromRoot (int idGimnasioRootModel);
}
