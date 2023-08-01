package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Categoria;
import com.championdo.torneo.entity.Cinturon;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.mapper.MapperCinturon;
import com.championdo.torneo.model.CinturonModel;
import com.championdo.torneo.repository.CategoriaRepository;
import com.championdo.torneo.repository.CinturonRepository;
import com.championdo.torneo.service.CinturonService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class CinturonServiceImpl implements CinturonService {

    @Autowired
    private CinturonRepository cinturonRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MapperCinturon mapperCinturon;

    @Override
    public List<CinturonModel> findAll() {
        List<CinturonModel> cinturonModelList = new ArrayList<>();
        for (Cinturon cinturon: cinturonRepository.findAllByOrderByPositionAsc()) {
            cinturonModelList.add(mapperCinturon.entity2Model(cinturon));
        }
        return cinturonModelList;
    }

    @Override
    public CinturonModel findById(int id) {
        try {
            return mapperCinturon.entity2Model(cinturonRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new CinturonModel();
        }
    }

    @Override
    public void add(CinturonModel cinturonModel) {
        cinturonRepository.save(mapperCinturon.model2Entity(cinturonModel));
    }

    @Override
    public void update(CinturonModel cinturonModel) {
        cinturonRepository.save(mapperCinturon.model2Entity(cinturonModel));
    }

    @Override
    public void delete(int idCinturon) throws RemoveException {
        List<Categoria> categoriaList = categoriaRepository.findByIdCinturonInicioOrIdCinturonFin(idCinturon, idCinturon);
        if (categoriaList == null || categoriaList.isEmpty()) {
            cinturonRepository.deleteById(idCinturon);
            List<Cinturon> cinturonList = cinturonRepository.findAllByOrderByPositionAsc();
            for (int i = 0; i < cinturonList.size(); i++) {
                if (cinturonList.get(i).getPosition() != i) {
                    cinturonList.get(i).setPosition(i);
                    cinturonRepository.save(cinturonList.get(i));
                }
            }
        } else {
            StringBuilder errorMessage = new StringBuilder("No se puede borrar cinturón por estar en categoría");
            LoggerMapper.log(Level.INFO, "delete", categoriaList, getClass());
            for (Categoria categoria : categoriaList) {
                errorMessage.append(" ").append(categoria.getNombre());
            }
            throw new RemoveException("100", errorMessage.toString());
        }
    }

    @Override
    public void dragOfPosition(int initialPosition, int finalPosition) {
        Cinturon cinturon = cinturonRepository.findByPosition(initialPosition);
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
        cinturon.setPosition(finalPosition);
        cinturonRepository.save(cinturon);
    }

    @Override
    public int findMaxPosition() {
        Cinturon cinturon = cinturonRepository.findTopByOrderByPositionDesc();
        if (cinturon != null) {
            return cinturon.getPosition();
        } else {
            return -1;
        }
    }

    private void moveItem(int position, boolean moveUp) {
        Cinturon cinturonAux = cinturonRepository.findByPosition(position);
        cinturonAux.setPosition(position + (moveUp ? 1 : -1));
        cinturonRepository.save(cinturonAux);
    }
}
