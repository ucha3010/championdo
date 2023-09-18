package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Categoria;
import com.championdo.torneo.entity.Poomsae;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.mapper.MapperPoomsae;
import com.championdo.torneo.model.PoomsaeModel;
import com.championdo.torneo.repository.CategoriaRepository;
import com.championdo.torneo.repository.PoomsaeRepository;
import com.championdo.torneo.service.PoomsaeService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class PoomsaeServiceImpl implements PoomsaeService {

    @Autowired
    private PoomsaeRepository poomsaeRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private MapperPoomsae mapperPoomsae;

    @Override
    public List<PoomsaeModel> findAll(int codigoGimnasio) {
        List<PoomsaeModel> poomsaeModelList = new ArrayList<>();
        for (Poomsae poomsae: poomsaeRepository.findByCodigoGimnasioOrderByPositionAsc(codigoGimnasio)) {
            poomsaeModelList.add(mapperPoomsae.entity2Model(poomsae));
        }
        return poomsaeModelList;
    }

    @Override
    public PoomsaeModel findById(int id) {
        try {
            return mapperPoomsae.entity2Model(poomsaeRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new PoomsaeModel();
        }
    }

    @Override
    public void add(PoomsaeModel poomsaeModel) {
        poomsaeRepository.save(mapperPoomsae.model2Entity(poomsaeModel));
    }

    @Override
    public void update(PoomsaeModel poomsaeModel) {
        poomsaeRepository.save(mapperPoomsae.model2Entity(poomsaeModel));
    }

    @Override
    public void delete(int idPoomsae) throws RemoveException {
        int codigoGimnasio = findById(idPoomsae).getCodigoGimnasio();
        List<Categoria> categoriaList = categoriaRepository.findByCodigoGimnasioAndIdCinturonInicioOrIdCinturonFin(codigoGimnasio, idPoomsae, idPoomsae);
        if (categoriaList == null || categoriaList.isEmpty()) {
            poomsaeRepository.deleteById(idPoomsae);
            List<Poomsae> poomsaeList = poomsaeRepository.findByCodigoGimnasioOrderByPositionAsc(codigoGimnasio);
            for (int i = 0; i < poomsaeList.size(); i++) {
                if (poomsaeList.get(i).getPosition() != i) {
                    poomsaeList.get(i).setPosition(i);
                    poomsaeRepository.save(poomsaeList.get(i));
                }
            }
        } else {
            StringBuilder errorMessage = new StringBuilder("No se puede borrar poomsae por estar en categoría");
            LoggerMapper.log(Level.INFO, "delete", categoriaList, getClass());
            for (Categoria categoria : categoriaList) {
                errorMessage.append(" ").append(categoria.getNombre());
            }
            throw new RemoveException("100", errorMessage.toString());
        }
    }

    @Override
    public void dragOfPosition(int codigoGimnasio, int initialPosition, int finalPosition) {
        Poomsae poomsae = poomsaeRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, initialPosition);
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
        poomsae.setPosition(finalPosition);
        poomsaeRepository.save(poomsae);
    }

    @Override
    public int findMaxPosition(int codigoGimnasio) {
        Poomsae poomsae = poomsaeRepository.findTopByCodigoGimnasioOrderByPositionDesc(codigoGimnasio);
        if (poomsae != null) {
            return poomsae.getPosition();
        } else {
            return -1;
        }
    }

    @Override
    public void deleteFromRoot(int idGimnasioRootModel) {
        List<Poomsae> poomsaeList = poomsaeRepository.findByCodigoGimnasio(idGimnasioRootModel);
        for (Poomsae poomsae: poomsaeList) {
            try {
                delete(poomsae.getId());
            } catch (RemoveException e) {
                LoggerMapper.log(Level.ERROR, "deleteFromRoot", e.getMessage(), getClass());
            }
        }

    }

    private void moveItem(int codigoGimnasio, int position, boolean moveUp) {
        Poomsae poomsae = poomsaeRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, position);
        poomsae.setPosition(position + (moveUp ? 1 : -1));
        poomsaeRepository.save(poomsae);
    }
}
