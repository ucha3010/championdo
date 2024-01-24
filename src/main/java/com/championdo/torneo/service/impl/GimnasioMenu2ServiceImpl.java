package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.GimnasioMenu2;
import com.championdo.torneo.mapper.MapperGimnasioMenu2;
import com.championdo.torneo.model.GimnasioMenu2Model;
import com.championdo.torneo.repository.GimnasioMenu2Repository;
import com.championdo.torneo.service.GimnasioMenu2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class GimnasioMenu2ServiceImpl implements GimnasioMenu2Service {

    @Autowired
    private GimnasioMenu2Repository gimnasioMenu2Repository;

    @Autowired
    private MapperGimnasioMenu2 mapperGimnasioMenu2;

    @Override
    public List<GimnasioMenu2Model> findByIdGimnasio(int id) {
        return fillList(gimnasioMenu2Repository.findByIdGimnasio(id));
    }

    @Override
    public void deleteByIdGimnasio(int id) {
        deleteList(gimnasioMenu2Repository.findByIdGimnasio(id));
    }

    @Override
    public List<GimnasioMenu2Model> findByIdMenu2(int id) {
        return fillList(gimnasioMenu2Repository.findByIdMenu2(id));
    }

    @Override
    public void deleteByIdMenu2(int id) {
        deleteList(gimnasioMenu2Repository.findByIdMenu2(id));
    }

    public void addList(List<GimnasioMenu2> gimnasioMenu2List) {
        gimnasioMenu2Repository.saveAll(gimnasioMenu2List);
    }

    private List<GimnasioMenu2Model> fillList(List<GimnasioMenu2> gimnasioMenu2List) {
        List<GimnasioMenu2Model> gimnasioMenu2ModelList = new ArrayList<>();
        for (GimnasioMenu2 gimnasioMenu2: gimnasioMenu2List) {
            gimnasioMenu2ModelList.add(mapperGimnasioMenu2.entity2Model(gimnasioMenu2));
        }
        return gimnasioMenu2ModelList;
    }

    private void deleteList(List<GimnasioMenu2> gimnasioMenu2List) {
        for(GimnasioMenu2 gimnasioMenu2 : gimnasioMenu2List) {
            gimnasioMenu2Repository.deleteById(gimnasioMenu2.getId());
        }
    }
}
