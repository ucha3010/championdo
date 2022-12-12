package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Calidad;
import com.championdo.torneo.mapper.MapperCalidad;
import com.championdo.torneo.model.CalidadModel;
import com.championdo.torneo.repository.CalidadRepository;
import com.championdo.torneo.service.CalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class CalidadServiceImpl implements CalidadService {

    @Autowired
    private CalidadRepository calidadRepository;

    @Autowired
    private MapperCalidad mapperCalidad;

    @Override
    public List<CalidadModel> findAll() {
        List<CalidadModel> calidadModelList = new ArrayList<>();
        for (Calidad calidad: calidadRepository.findAllByOrderByPositionAsc()) {
            calidadModelList.add(mapperCalidad.entity2Model(calidad));
        }
        return calidadModelList;
    }

    @Override
    public CalidadModel findById(int id) {
        try {
            return mapperCalidad.entity2Model(calidadRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new CalidadModel();
        }
    }

    @Override
    public void add(CalidadModel calidadModel) {
        calidadRepository.save(mapperCalidad.model2Entity(calidadModel));
    }

    @Override
    public void update(CalidadModel calidadModel) {
        calidadRepository.save(mapperCalidad.model2Entity(calidadModel));
    }

    @Override
    public void delete(int idCalidad) {
        calidadRepository.deleteById(idCalidad);
        List<Calidad> calidadList = calidadRepository.findAllByOrderByPositionAsc();
        for (int i = 0; i < calidadList.size(); i++) {
            if (calidadList.get(i).getPosition() != i) {
                calidadList.get(i).setPosition(i);
                calidadRepository.save(calidadList.get(i));
            }
        }
    }

    @Override
    public void dragOfPosition(int initialPosition, int finalPosition) {
        Calidad calidad = calidadRepository.findByPosition(initialPosition);
        if (initialPosition > finalPosition) {
            for (int i = initialPosition - 1; i >= finalPosition; i--) {
                moveItem(i, true);
            }
        }
        if (initialPosition < finalPosition) {
            for (int i = initialPosition + 1; i <= finalPosition; i++) {
                moveItem(i, false);
            }
        }
        calidad.setPosition(finalPosition);
        calidadRepository.save(calidad);
    }

    @Override
    public int findMaxPosition() {
        Calidad calidad = calidadRepository.findTopByOrderByPositionDesc();
        if (calidad != null) {
            return calidad.getPosition();
        } else {
            return -1;
        }
    }

    private void moveItem(int position, boolean moveUp) {
        Calidad calidad = calidadRepository.findByPosition(position);
        calidad.setPosition(position + (moveUp ? 1 : -1));
        calidadRepository.save(calidad);
    }
}
