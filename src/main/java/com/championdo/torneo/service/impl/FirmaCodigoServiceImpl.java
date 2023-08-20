package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.FirmaCodigo;
import com.championdo.torneo.entity.InscripcionTaekwondo;
import com.championdo.torneo.mapper.MapperFirmaCodigo;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.repository.FirmaCodigoRepository;
import com.championdo.torneo.service.FirmaCodigoService;
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
public class FirmaCodigoServiceImpl implements FirmaCodigoService {

    @Autowired
    private FirmaCodigoRepository firmaCodigoRepository;

    @Autowired
    private MapperFirmaCodigo mapperFirmaCodigo;

    @Override
    public List<FirmaCodigoModel> findAll() {
        List<FirmaCodigoModel> firmaCodigoModelList = new ArrayList<>();
        for (FirmaCodigo firmaCodigo: firmaCodigoRepository.findAll()) {
            firmaCodigoModelList.add(mapperFirmaCodigo.entity2Model(firmaCodigo));
        }
        return firmaCodigoModelList;
    }

    @Override
    public FirmaCodigoModel findById(int id) {
        try {
            return mapperFirmaCodigo.entity2Model(firmaCodigoRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new FirmaCodigoModel();
        }
    }

    @Override
    public FirmaCodigoModel add(FirmaCodigoModel firmaCodigoModel) {
        Date ahora = new Date();
        Calendar caducidad = Calendar.getInstance();
        caducidad.setTime(ahora);
        caducidad.add(Calendar.MINUTE, 15);
        firmaCodigoModel.setFechaCreacion(ahora);
        firmaCodigoModel.setFechaCaducidad(caducidad.getTime());
        FirmaCodigoModel firmaCodigoModel1 = mapperFirmaCodigo.entity2Model(firmaCodigoRepository.save(mapperFirmaCodigo.model2Entity(firmaCodigoModel)));
        LoggerMapper.methodOut(Level.INFO, "add", firmaCodigoModel1, getClass());
        return firmaCodigoModel1;
    }

    @Override
    public void update(FirmaCodigoModel firmaCodigoModel) {
        firmaCodigoRepository.save(mapperFirmaCodigo.model2Entity(firmaCodigoModel));
    }

    @Override
    public void delete(int idFirmaCodigo) {
        try {
            FirmaCodigo firmaCodigo = firmaCodigoRepository.getById(idFirmaCodigo);
            firmaCodigoRepository.delete(firmaCodigo);
            LoggerMapper.methodOut(Level.INFO, "delete", firmaCodigo, getClass());
        } catch (EntityNotFoundException e) {
            LoggerMapper.log(Level.ERROR, "delete", "id " + idFirmaCodigo + " no encontrado", this.getClass());
        }
    }

    @Override
    public FirmaCodigoModel findByIdOperacion(int idOperacion) {
        try {
            return mapperFirmaCodigo.entity2Model(firmaCodigoRepository.findByIdOperacion(idOperacion));
        } catch (EntityNotFoundException e) {
            return new FirmaCodigoModel();
        }
    }

    @Override
    public void limpiarFirmasCaducadas() {
        List<FirmaCodigo> firmaCodigoList= firmaCodigoRepository.findAllByOrderByFechaCaducidadDesc();
        for (FirmaCodigo firmaCodigo: firmaCodigoList) {
            if (Utils.milisegEntreDosFechas(firmaCodigo.getFechaCaducidad(), new Date()) < 0) {
                delete(firmaCodigo.getId());
            }
        }
    }
}
