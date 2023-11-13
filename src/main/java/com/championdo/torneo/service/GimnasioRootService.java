package com.championdo.torneo.service;


import com.championdo.torneo.model.GimnasioRootModel;

import java.util.List;

public interface GimnasioRootService {

    List<GimnasioRootModel> findAll();

    List<GimnasioRootModel> findAllOrderByNombreGimnasioAsc();

    GimnasioRootModel findById(int id);

    GimnasioRootModel add(GimnasioRootModel gimnasioModel);

    void update(GimnasioRootModel gimnasioModel) throws Exception;

    void delete(int idGimnasio);

    void enableDisable(int idGimnasioRootModel, boolean enableDisable);

    boolean verifyEnable(int idGimnasioRootModel);

}
