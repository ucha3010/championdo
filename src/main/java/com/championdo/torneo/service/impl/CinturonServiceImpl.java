package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Cinturon;
import com.championdo.torneo.mapper.MapperCinturon;
import com.championdo.torneo.model.CinturonModel;
import com.championdo.torneo.model.CinturonModel;
import com.championdo.torneo.repository.CinturonRepository;
import com.championdo.torneo.service.CinturonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class CinturonServiceImpl implements CinturonService {

    @Autowired
    private CinturonRepository cinturonRepository;

    @Autowired
    private MapperCinturon mapperCinturon;

    @Override
    public List<CinturonModel> findAll() {
        List<CinturonModel> cinturonModelList = new ArrayList<>();
        for (Cinturon cinturon: cinturonRepository.findAll()) {
            cinturonModelList.add(mapperCinturon.entity2Model(cinturon));
        }
        return cinturonModelList;
    }

    @Override
    public CinturonModel findById(int id) {
        if (id != 0) {
            return mapperCinturon.entity2Model(cinturonRepository.getById(id));
        } else {
            return new CinturonModel();
        }
    }

    @Override
    public void add(CinturonModel cinturonModel) {

    }

    @Override
    public void update(CinturonModel cinturonModel) {

    }

    @Override
    public void delete(int idCinturon) {

    }
}
