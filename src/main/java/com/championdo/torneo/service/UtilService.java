package com.championdo.torneo.service;


import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.model.UtilModel;

import java.util.List;

public interface UtilService {

    List<UtilModel> findAllStarsWith(String startWord, int codigoGimnasio);

    UtilModel findByClave(String clave, int codigoGimnasio);

    void update(UtilModel utilModel);

    void addFromRoot(GimnasioRootModel customer);
}
