package com.championdo.torneo.service;


import com.championdo.torneo.entity.GimnasioMenu2;
import com.championdo.torneo.model.GimnasioMenu2Model;

import java.util.List;

public interface GimnasioMenu2Service {

    List<GimnasioMenu2Model> findByIdGimnasio(int id);

    void deleteByIdGimnasio(int id);

    List<GimnasioMenu2Model> findByIdMenu2(int id);

    void deleteByIdMenu2(int id);

    void addList(List<GimnasioMenu2> gimnasioMenu2List);

}
