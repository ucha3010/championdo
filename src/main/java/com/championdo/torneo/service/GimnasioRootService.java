package com.championdo.torneo.service;


import com.championdo.torneo.model.GimnasioRootModel;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface GimnasioRootService {

    List<GimnasioRootModel> findAll();

    List<GimnasioRootModel> findAllOrderByNombreGimnasioAsc();
    List<GimnasioRootModel> findByCifNif(String cifNif);

    GimnasioRootModel findById(int id);

    GimnasioRootModel add(GimnasioRootModel gimnasioModel);

    void update(GimnasioRootModel gimnasioModel) throws Exception;

    void delete(int idGimnasio);

    void enableDisable(int idGimnasioRootModel, boolean enableDisable);

    boolean verifyEnable(int idGimnasioRootModel);

    void fillMenu2Checked(ModelAndView modelAndView);

    List<GimnasioRootModel> findByMenu2Url(String url);

}
