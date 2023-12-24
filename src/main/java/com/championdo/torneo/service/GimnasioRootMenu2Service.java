package com.championdo.torneo.service;


import com.championdo.torneo.entity.GimnasioRootMenu2;
import com.championdo.torneo.model.GimnasioRootMenu2Model;

import java.util.List;

public interface GimnasioRootMenu2Service {

    List<GimnasioRootMenu2Model> findByIdGimnasioRoot(int id);

    void deleteByIdGimnasioRoot(int id);

    List<GimnasioRootMenu2Model> findByIdMenu2(int id);

    void deleteByIdMenu2(int id);

    void addList(List<GimnasioRootMenu2> gimnasioRootMenu2List);

}
