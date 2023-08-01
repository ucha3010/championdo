package com.championdo.torneo.service;


import com.championdo.torneo.model.UtilModel;

import java.util.List;

public interface UtilService {

    List<UtilModel> findAllCampeonato();

    UtilModel findByClave(String clave);

    void update(UtilModel utilModel);

}
