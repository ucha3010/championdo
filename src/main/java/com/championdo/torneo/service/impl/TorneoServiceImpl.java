package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Torneo;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.mapper.MapperTorneo;
import com.championdo.torneo.model.TorneoGimnasioModel;
import com.championdo.torneo.model.TorneoModel;
import com.championdo.torneo.repository.TorneoRepository;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.service.TorneoGimnasioService;
import com.championdo.torneo.service.TorneoService;
import com.championdo.torneo.service.TournamentRegistrationService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TournamentRegistrationService tournamentRegistrationService;
    @Autowired
    private GimnasioService gimnasioService;
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
            return mapperTorneo.entity2Model(torneoRepository.findById(id).orElse(null));
        } catch (EntityNotFoundException e) {
            return new TorneoModel();
        }
    }

    @Override
    public void add(TorneoModel torneoModel) {
        torneoModel.setFechaFinInscripcion(Utils.cambiarHMS(torneoModel.getFechaFinInscripcion(),23,59,59));
        torneoModel = mapperTorneo.entity2Model(torneoRepository.save(mapperTorneo.model2Entity(torneoModel)));
        TorneoGimnasioModel torneoGimnasioModel = new TorneoGimnasioModel();
        torneoGimnasioModel.setIdTorneo(torneoModel.getId());
        torneoGimnasioModel.setNombreGimnasio(gimnasioService.findById(torneoModel.getCodigoGimnasio()).getNombreGimnasio());
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
            if(!tournamentRegistrationService.findByIdTournament(id).isEmpty()) {
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
    public void deleteByCodigoGimnasio(int codigoGimnasio) {
        torneoRepository.deleteByCodigoGimnasio(codigoGimnasio);
    }

    @Override
    public List<TorneoModel> findAllowed(Date date, String tournamentType) {
        List<Torneo> torneoList;
        List<TorneoModel> torneoModelList = new ArrayList<>();
        torneoList = switch (tournamentType) {
            case Constantes.ADULTO ->
                    torneoRepository.findByFechaComienzoInscripcionLessThanEqualAndFechaFinInscripcionGreaterThanEqualAndAdultoTrue(date, date);
            case Constantes.MENOR ->
                    torneoRepository.findByFechaComienzoInscripcionLessThanEqualAndFechaFinInscripcionGreaterThanEqualAndMenorTrue(date, date);
            case Constantes.INCLUSIVO_MINUSCULAS ->
                    torneoRepository.findByFechaComienzoInscripcionLessThanEqualAndFechaFinInscripcionGreaterThanEqualAndInclusivoTrue(date, date);
            default -> new ArrayList<>();
        };
        for (Torneo torneo : torneoList) {
            torneoModelList.add(mapperTorneo.entity2Model(torneo));
        }
        return torneoModelList;
    }
}
