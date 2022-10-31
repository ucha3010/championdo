package com.championdo.torneo.service.impl;

import com.championdo.torneo.mapper.MapperPoomsae;
import com.championdo.torneo.model.PoomsaeModel;
import com.championdo.torneo.repository.PoomsaeRepository;
import com.championdo.torneo.service.PoomsaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class PoomsaeServiceImpl implements PoomsaeService {

    @Autowired
    private MapperPoomsae mapperPoomsae;

    @Autowired
    private PoomsaeRepository poomsaeRepository;

    @Override
    public PoomsaeModel findById(int id) {
        return mapperPoomsae.entity2Model(poomsaeRepository.getById(id));
    }

    @Override
    public void add(PoomsaeModel poomsaeModel) {

    }

    @Override
    public void update(PoomsaeModel poomsaeModel) {

    }

    @Override
    public void delete(int idPoomsae) {

    }
}
