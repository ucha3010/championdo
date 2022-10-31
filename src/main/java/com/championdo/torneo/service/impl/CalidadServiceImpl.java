package com.championdo.torneo.service.impl;

import com.championdo.torneo.mapper.MapperCalidad;
import com.championdo.torneo.model.CalidadModel;
import com.championdo.torneo.repository.CalidadRepository;
import com.championdo.torneo.service.CalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service()
public class CalidadServiceImpl implements CalidadService {

    @Autowired
    private CalidadRepository calidadRepository;

    @Autowired
    private MapperCalidad mapperCalidad;

    @Override
    public CalidadModel findById(int id) {
        if (id != 0) {
            return mapperCalidad.entity2Model(calidadRepository.getById(id));
        } else {
            return new CalidadModel();
        }
    }

    @Override
    public Set<CalidadModel> findByNombre(String nombre) {
        return null;
    }

    @Override
    public void add(CalidadModel calidadModel) {

    }

    @Override
    public void update(CalidadModel calidadModel) {

    }

    @Override
    public void delete(int idCalidad) {

    }
}
