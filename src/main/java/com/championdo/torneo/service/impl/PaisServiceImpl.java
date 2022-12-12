package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Pais;
import com.championdo.torneo.mapper.MapperPais;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.PaisModel;
import com.championdo.torneo.repository.PaisRepository;
import com.championdo.torneo.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        for (Pais pais: paisRepository.findAllByOrderByPositionAsc()) {
            paisModelList.add(mapperPais.entity2Model(pais));
        }
        return paisModelList;
    }

    @Override
    public PaisModel findById(int id) {
        try {
            return mapperPais.entity2Model(paisRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new PaisModel();
        }
    }

    @Override
    public void add(PaisModel paisModel) {
        paisRepository.save(mapperPais.model2Entity(paisModel));
    }

    @Override
    public void update(PaisModel paisModel) {
        paisRepository.save(mapperPais.model2Entity(paisModel));
    }

    @Override
    public void delete(int idPais) {
        paisRepository.deleteById(idPais);
        List<Pais> paisList = paisRepository.findAllByOrderByPositionAsc();
        for (int i = 0; i < paisList.size(); i++) {
            if (paisList.get(i).getPosition() != i) {
                paisList.get(i).setPosition(i);
                paisRepository.save(paisList.get(i));
            }
        }
    }

    @Override
    public void dragOfPosition(int initialPosition, int finalPosition) {
        Pais pais = paisRepository.findByPosition(initialPosition);
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
        pais.setPosition(finalPosition);
        paisRepository.save(pais);
    }

    @Override
    public int findMaxPosition() {
        Pais pais = paisRepository.findTopByOrderByPositionDesc();
        if (pais != null) {
            return pais.getPosition();
        } else {
            return -1;
        }
    }

    private void moveItem(int position, boolean moveUp) {
        Pais pais = paisRepository.findByPosition(position);
        pais.setPosition(position + (moveUp ? 1 : -1));
        paisRepository.save(pais);
    }
}
