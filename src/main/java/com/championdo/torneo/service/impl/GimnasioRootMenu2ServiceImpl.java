package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.GimnasioRootMenu2;
import com.championdo.torneo.mapper.MapperGimnasioRootMenu2;
import com.championdo.torneo.model.GimnasioRootMenu2Model;
import com.championdo.torneo.repository.GimnasioRootMenu2Repository;
import com.championdo.torneo.service.GimnasioRootMenu2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class GimnasioRootMenu2ServiceImpl implements GimnasioRootMenu2Service {

    @Autowired
    private GimnasioRootMenu2Repository gimnasioRootMenu2Repository;

    @Autowired
    private MapperGimnasioRootMenu2 mapperGimnasioRootMenu2;

    @Override
    public List<GimnasioRootMenu2Model> findByIdGimnasioRoot(int id) {
        return fillList(gimnasioRootMenu2Repository.findByIdGimnasioRoot(id));
    }

    @Override
    public void deleteByIdGimnasioRoot(int id) {
        deleteList(gimnasioRootMenu2Repository.findByIdGimnasioRoot(id));
    }

    @Override
    public List<GimnasioRootMenu2Model> findByIdMenu2(int id) {
        return fillList(gimnasioRootMenu2Repository.findByIdMenu2(id));
    }

    @Override
    public void deleteByIdMenu2(int id) {
        deleteList(gimnasioRootMenu2Repository.findByIdMenu2(id));
    }

    public void addList(List<GimnasioRootMenu2> gimnasioRootMenu2List) {
        gimnasioRootMenu2Repository.saveAll(gimnasioRootMenu2List);
    }

    private List<GimnasioRootMenu2Model> fillList(List<GimnasioRootMenu2> gimnasioRootMenu2List) {
        List<GimnasioRootMenu2Model> gimnasioRootMenu2ModelList = new ArrayList<>();
        for (GimnasioRootMenu2 gimnasioRootMenu2: gimnasioRootMenu2List) {
            gimnasioRootMenu2ModelList.add(mapperGimnasioRootMenu2.entity2Model(gimnasioRootMenu2));
        }
        return gimnasioRootMenu2ModelList;
    }

    private void deleteList(List<GimnasioRootMenu2> gimnasioRootMenu2List) {
        for(GimnasioRootMenu2 gimnasioRootMenu2 : gimnasioRootMenu2List) {
            gimnasioRootMenu2Repository.deleteById(gimnasioRootMenu2.getId());
        }
    }
}
