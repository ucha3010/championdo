package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Firma;
import com.championdo.torneo.mapper.MapperFirma;
import com.championdo.torneo.model.FirmaModel;
import com.championdo.torneo.repository.FirmaRepository;
import com.championdo.torneo.service.FirmaService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service()
public class FirmaServiceImpl implements FirmaService {

    @Autowired
    private FirmaRepository firmaRepository;

    @Autowired
    private MapperFirma mapperFirma;

    @Override
    public List<FirmaModel> findAll() {
        List<FirmaModel> firmaModelList = new ArrayList<>();
        for (Firma firma: firmaRepository.findAll()) {
            firmaModelList.add(mapperFirma.entity2Model(firma));
        }
        return firmaModelList;
    }

    @Override
    public FirmaModel findById(int id) {
        try {
            return mapperFirma.entity2Model(firmaRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new FirmaModel();
        }
    }

    @Override
    public FirmaModel add(FirmaModel firmaModel) {
        FirmaModel firmaModel1 = mapperFirma.entity2Model(firmaRepository.save(mapperFirma.model2Entity(firmaModel)));
        LoggerMapper.methodOut(Level.INFO, "add", firmaModel1, getClass());
        return firmaModel1;
    }

    @Override
    public void update(FirmaModel firmaModel) {
        firmaRepository.save(mapperFirma.model2Entity(firmaModel));
    }

    @Override
    public void delete(int idFirma) {
        try {
            Firma firma = firmaRepository.getById(idFirma);
            firmaRepository.delete(firma);
            LoggerMapper.methodOut(Level.INFO, "delete", firma, getClass());
        } catch (EntityNotFoundException e) {
            LoggerMapper.log(Level.ERROR, "delete", "id " + idFirma + " no encontrado", this.getClass());
        }
    }

    @Override
    public FirmaModel findByIdOperacion(int idOperacion) {
        try {
            return mapperFirma.entity2Model(firmaRepository.findByIdOperacion(idOperacion));
        } catch (EntityNotFoundException e) {
            return new FirmaModel();
        }
    }
}
