package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Pais;
import com.championdo.torneo.mapper.MapperPais;
import com.championdo.torneo.model.PaisModel;
import com.championdo.torneo.repository.PaisRepository;
import com.championdo.torneo.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class PaisServiceImpl implements PaisService {

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private MapperPais mapperPais;

    @Override
    public List<PaisModel> findAll() {
        List<PaisModel> paisModelList = new ArrayList<>();
        for (Pais pais: paisRepository.findAll()) {
            paisModelList.add(mapperPais.entity2Model(pais));
        }
        return paisModelList;
    }

    @Override
    public PaisModel findById(int id) {
        if (id != 0) {
            return mapperPais.entity2Model(paisRepository.getById(id));
        } else {
            return new PaisModel();
        }
    }

    @Override
    public void add(PaisModel paisModel) {

    }

    @Override
    public void update(PaisModel paisModel) {

    }

    @Override
    public void delete(int idPais) {

    }
}
