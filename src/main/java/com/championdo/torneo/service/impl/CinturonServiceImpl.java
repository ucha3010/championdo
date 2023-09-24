package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Categoria;
import com.championdo.torneo.entity.Cinturon;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.mapper.MapperCinturon;
import com.championdo.torneo.model.CinturonModel;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.repository.CategoriaRepository;
import com.championdo.torneo.repository.CinturonRepository;
import com.championdo.torneo.service.CinturonService;
import com.championdo.torneo.util.Constantes;
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
    public List<CinturonModel> findAll(int codigoGimnasio) {
        List<CinturonModel> cinturonModelList = new ArrayList<>();
        for (Cinturon cinturon: cinturonRepository.findByCodigoGimnasioOrderByPositionAsc(codigoGimnasio)) {
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
    public CinturonModel findByCodigoGimnasioAndPosition(int codigoGimnasio, int position) {
        try {
            return mapperCinturon.entity2Model(cinturonRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, position));
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
        CinturonModel cinturonModel = findById(idCinturon);
        List<Categoria> categoriaList = categoriaRepository.findByCodigoGimnasioAndPositionCinturonInicioOrPositionCinturonFin(cinturonModel.getCodigoGimnasio(), cinturonModel.getPosition(), cinturonModel.getPosition());
        if (categoriaList == null || categoriaList.isEmpty()) {
            cinturonRepository.deleteById(idCinturon);
            List<Cinturon> cinturonList = cinturonRepository.findByCodigoGimnasioOrderByPositionAsc(cinturonModel.getCodigoGimnasio());
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
    public void dragOfPosition(int codigoGimnasio, int initialPosition, int finalPosition) {
        Cinturon cinturon = cinturonRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, initialPosition);
        if (initialPosition > finalPosition) {
            for (int i = initialPosition - 1; i >= finalPosition; i--) {
                moveItem(codigoGimnasio, i, true);
            }
        }
        if (initialPosition < finalPosition) {
            for (int i = initialPosition + 1; i <= finalPosition; i++) {
                moveItem(codigoGimnasio, i, false);
            }
        }
        cinturon.setPosition(finalPosition);
        cinturonRepository.save(cinturon);
    }

    @Override
    public int findMaxPosition(int codigoGimnasio) {
        Cinturon cinturon = cinturonRepository.findTopByCodigoGimnasioOrderByPositionDesc(codigoGimnasio);
        if (cinturon != null) {
            return cinturon.getPosition();
        } else {
            return -1;
        }
    }

    @Override
    public void deleteFromRoot(int idGimnasioRootModel){
        List<Cinturon> cinturonList = cinturonRepository.findByCodigoGimnasio(idGimnasioRootModel);
        for (Cinturon cinturon: cinturonList) {
            try {
                delete(cinturon.getId());
            } catch (RemoveException e) {
                LoggerMapper.log(Level.ERROR, "deleteFromRoot", e.getMessage(), getClass());
            }
        }
    }

    @Override
    public void addFromRoot(GimnasioRootModel customer) {
        List<Cinturon> cinturonList = new ArrayList<>();
        cinturonList.add(new Cinturon(Constantes.BLANCO, 0, customer.getId()));
        cinturonList.add(new Cinturon("Amarillo", 1, customer.getId()));
        cinturonList.add(new Cinturon("Naranja", 2, customer.getId()));
        cinturonList.add(new Cinturon("Verde", 3, customer.getId()));
        cinturonList.add(new Cinturon("Azul", 4, customer.getId()));
        cinturonList.add(new Cinturon("Rojo", 5, customer.getId()));
        cinturonList.add(new Cinturon("Negro 1º DAN", 6, customer.getId()));
        cinturonList.add(new Cinturon("Negro 2º DAN", 7, customer.getId()));
        cinturonList.add(new Cinturon("Negro 3º DAN", 8, customer.getId()));
        cinturonList.add(new Cinturon("Negro 4º DAN", 9, customer.getId()));
        cinturonList.add(new Cinturon("Negro 5º DAN", 10, customer.getId()));
        cinturonList.add(new Cinturon("Negro 6º DAN", 11, customer.getId()));
        cinturonList.add(new Cinturon("Negro 7º DAN", 12, customer.getId()));
        cinturonList.add(new Cinturon("Negro 8º DAN", 13, customer.getId()));
        for(Cinturon cinturon: cinturonList) {
            cinturonRepository.save(cinturon);
        }

    }

    private void moveItem(int codigoGimnasio, int position, boolean moveUp) {
        Cinturon cinturonAux = cinturonRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, position);
        cinturonAux.setPosition(position + (moveUp ? 1 : -1));
        cinturonRepository.save(cinturonAux);
    }
}
