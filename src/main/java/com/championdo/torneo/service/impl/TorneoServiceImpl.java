package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Torneo;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.mapper.MapperTorneo;
import com.championdo.torneo.model.TorneoGimnasioModel;
import com.championdo.torneo.model.TorneoModel;
import com.championdo.torneo.repository.TorneoRepository;
import com.championdo.torneo.service.GimnasioRootService;
import com.championdo.torneo.service.InscripcionService;
import com.championdo.torneo.service.TorneoGimnasioService;
import com.championdo.torneo.service.TorneoService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service()
public class TorneoServiceImpl implements TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;
    @Autowired
    private MapperTorneo mapperTorneo;
    @Autowired
    private InscripcionService inscripcionService;
    @Autowired
    private GimnasioRootService gimnasioRootService;
    @Autowired
    private TorneoGimnasioService torneoGimnasioService;

    @Override
    public List<TorneoModel> findAll(int codigoGimnasio) {
        List<TorneoModel> torneoModelList = new ArrayList<>();
        for (Torneo torneo: torneoRepository.findByCodigoGimnasioOrderByFechaTorneoDesc(codigoGimnasio)) {
            torneoModelList.add(mapperTorneo.entity2Model(torneo));
        }
        return torneoModelList;
    }

    @Override
    public TorneoModel findById(int id) {
        try {
            return mapperTorneo.entity2Model(torneoRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new TorneoModel();
        }
    }

    @Override
    public void add(TorneoModel torneoModel) {
        torneoModel = mapperTorneo.entity2Model(torneoRepository.save(mapperTorneo.model2Entity(torneoModel)));
        TorneoGimnasioModel torneoGimnasioModel = new TorneoGimnasioModel();
        torneoGimnasioModel.setIdTorneo(torneoModel.getId());
        torneoGimnasioModel.setNombreGimnasio(gimnasioRootService.findById(torneoModel.getCodigoGimnasio()).getNombreGimnasio());
        torneoGimnasioModel.setCodigoGimnasio(torneoModel.getCodigoGimnasio());
        torneoGimnasioService.add(torneoGimnasioModel);
    }

    @Override
    public void update(TorneoModel torneoModel) {
        torneoRepository.save(mapperTorneo.model2Entity(torneoModel));
    }

    @Override
    public void delete(int id) throws RemoveException {
        try {
            if(!inscripcionService.findByIdTorneo(id).isEmpty()) {
                throw new RemoveException(Constantes.ERROR_BORRAR_TORNEO_CON_INSCRIPCIONES, "Se está intentando eliminar un torneo que tiene inscripciones");
            }
            torneoRepository.deleteById(id);
            for (TorneoGimnasioModel torneoGimnasioModel: torneoGimnasioService.findAll(id)) {
                torneoGimnasioService.delete(torneoGimnasioModel);
            }
        } catch (IllegalArgumentException e) {
            LoggerMapper.log(Level.ERROR, "delete", e.getMessage(), getClass());
            throw new RemoveException(Constantes.ERROR_BORRAR_TORNEO, "Error al borrar el torneo");
        }
        LoggerMapper.methodOut(Level.INFO, "delete", id, getClass());
    }

    @Override
    public List<TorneoModel> findAllowedWithGyms(Date date, String tournamentType) {
        // TODO DAMIAN filtrar para que la fecha esté dentro de la fecha de inscripción y que tenga habilitado lo que llegue en tournamentType
        return null;
    }
}
