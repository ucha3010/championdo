package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Gimnasio;
import com.championdo.torneo.entity.Pais;
import com.championdo.torneo.mapper.MapperGimnasio;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.PaisModel;
import com.championdo.torneo.repository.GimnasioRepository;
import com.championdo.torneo.service.GimnasioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class GimnasioServiceImpl implements GimnasioService {

    @Autowired
    private GimnasioRepository gimnasioRepository;

    @Autowired
    private MapperGimnasio mapperGimnasio;

    @Override
    public List<GimnasioModel> findAll() {
        List<GimnasioModel> gimnasioModelList = new ArrayList<>();
        for (Gimnasio gimnasio: gimnasioRepository.findAll()) {
            gimnasioModelList.add(mapperGimnasio.entity2Model(gimnasio));
        }
        return gimnasioModelList;
    }

    @Override
    public GimnasioModel findById(int id) {
        if (id != 0) {
            return mapperGimnasio.entity2Model(gimnasioRepository.getById(id));
        } else {
            return new GimnasioModel();
        }
    }

    @Override
    public void add(GimnasioModel gimnasioModel) {

    }

    @Override
    public void update(GimnasioModel gimnasioModel) {

    }

    @Override
    public void delete(int idGimnasio) {

    }
}
