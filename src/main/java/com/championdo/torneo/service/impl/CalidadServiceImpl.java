package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Calidad;
import com.championdo.torneo.mapper.MapperCalidad;
import com.championdo.torneo.model.CalidadModel;
import com.championdo.torneo.repository.CalidadRepository;
import com.championdo.torneo.service.CalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service()
public class CalidadServiceImpl implements CalidadService {

    @Autowired
    private CalidadRepository calidadRepository;

    @Autowired
    private MapperCalidad mapperCalidad;

    @Override
    public List<CalidadModel> findAll() {
        List<CalidadModel> calidadModelList = new ArrayList<>();
        for (Calidad calidad: calidadRepository.findAll()) {
            calidadModelList.add(mapperCalidad.entity2Model(calidad));
        }
        return calidadModelList;
    }

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
