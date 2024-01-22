package com.championdo.torneo.service;


import com.championdo.torneo.model.GimnasioModel;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface GimnasioService {

    List<GimnasioModel> findAll();
    GimnasioModel findById(int id);
    GimnasioModel add(GimnasioModel gimnasioModel);
    GimnasioModel update(GimnasioModel gimnasioModel);
    void delete(int idGimnasio);
    List<GimnasioModel> findAllOrderByNombreGimnasioAsc();
    List<GimnasioModel> findByCifNif(String cifNif);
    void enableDisable(int idGimnasioModel, boolean enableDisable);
    boolean verifyEnable(int idGimnasioModel);
    void fillMenu2Checked(ModelAndView modelAndView, int codigoGimnasio);
    List<GimnasioModel> findByMenu2Url(String url);
}
