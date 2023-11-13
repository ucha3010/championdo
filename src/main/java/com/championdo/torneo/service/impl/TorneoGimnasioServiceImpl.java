package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.TorneoGimnasio;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.mapper.MapperTorneoGimnasio;
import com.championdo.torneo.model.TorneoGimnasioModel;
import com.championdo.torneo.repository.TorneoGimnasioRepository;
import com.championdo.torneo.service.TorneoGimnasioService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class TorneoGimnasioServiceImpl implements TorneoGimnasioService {

    @Autowired
    private TorneoGimnasioRepository torneoGimnasioRepository;
    @Autowired
    private MapperTorneoGimnasio mapperTorneoGimnasio;

    @Override
    public List<TorneoGimnasioModel> findAll(int idTorneo) {
        List<TorneoGimnasioModel> torneoGimnasioModelList = new ArrayList<>();
        for (TorneoGimnasio torneoGimnasio: torneoGimnasioRepository.findByIdTorneoOrderByPositionAsc(idTorneo)) {
            torneoGimnasioModelList.add(mapperTorneoGimnasio.entity2Model(torneoGimnasio));
        }
        return torneoGimnasioModelList;
    }

    @Override
    public TorneoGimnasioModel findById(int id) {
        try {
            return mapperTorneoGimnasio.entity2Model(torneoGimnasioRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new TorneoGimnasioModel();
        }
    }

    @Override
    public void add(TorneoGimnasioModel torneoGimnasioModel) {
        torneoGimnasioRepository.save(mapperTorneoGimnasio.model2Entity(torneoGimnasioModel));
    }

    @Override
    public void update(TorneoGimnasioModel torneoGimnasioModel) {
        torneoGimnasioRepository.save(mapperTorneoGimnasio.model2Entity(torneoGimnasioModel));
    }

    @Override
    public void delete(TorneoGimnasioModel torneoGimnasioModel) throws RemoveException {
        try {
            torneoGimnasioRepository.deleteById(torneoGimnasioModel.getId());
            List<TorneoGimnasio> torneoGimnasioList = torneoGimnasioRepository.findByIdTorneoOrderByPositionAsc(torneoGimnasioModel.getIdTorneo());
            for (int i = 0; i < torneoGimnasioList.size(); i++) {
                if (torneoGimnasioList.get(i).getPosition() != i) {
                    torneoGimnasioList.get(i).setPosition(i);
                    torneoGimnasioRepository.save(torneoGimnasioList.get(i));
                }
            }
        } catch (IllegalArgumentException e){
            LoggerMapper.log(Level.ERROR, "delete", e.getMessage(), getClass());
            throw new RemoveException("100", "Error al borrar el gimnasio");
        }
        LoggerMapper.methodOut(Level.INFO, "delete", torneoGimnasioModel.getId(), getClass());
    }

    @Override
    public void deleteByCodigoGimnasio(int codigoGimnasio) {
        torneoGimnasioRepository.deleteByCodigoGimnasio(codigoGimnasio);
    }

    @Override
    public void dragOfPosition(int idTorneo, int initialPosition, int finalPosition) {
        TorneoGimnasio torneoGimnasio = torneoGimnasioRepository.findByIdTorneoAndPosition(idTorneo, initialPosition);
        if (initialPosition > finalPosition) {
            for (int i = initialPosition - 1; i >= finalPosition; i--) {
                moveItem(idTorneo, i, true);
            }
        }
        if (initialPosition < finalPosition) {
            for (int i = initialPosition + 1; i <= finalPosition; i++) {
                moveItem(idTorneo, i, false);
            }
        }
        torneoGimnasio.setPosition(finalPosition);
        torneoGimnasioRepository.save(torneoGimnasio);
    }

    @Override
    public int findMaxPosition(int idTorneo) {
        TorneoGimnasio torneoGimnasio = torneoGimnasioRepository.findTopByIdTorneoOrderByPositionDesc(idTorneo);
        if (torneoGimnasio != null) {
            return torneoGimnasio.getPosition();
        } else {
            return -1;
        }
    }

    private void moveItem(int idTorneo, int position, boolean moveUp) {
        TorneoGimnasio torneoGimnasio = torneoGimnasioRepository.findByIdTorneoAndPosition(idTorneo, position);
        torneoGimnasio.setPosition(position + (moveUp ? 1 : -1));
        torneoGimnasioRepository.save(torneoGimnasio);
    }
}
