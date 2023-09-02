package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.FirmaCodigo;
import com.championdo.torneo.mapper.MapperFirmaCodigo;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.model.FirmaModel;
import com.championdo.torneo.repository.FirmaCodigoRepository;
import com.championdo.torneo.service.FirmaCodigoService;
import com.championdo.torneo.service.FirmaService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service()
public class FirmaCodigoServiceImpl implements FirmaCodigoService {

    @Autowired
    private FirmaCodigoRepository firmaCodigoRepository;

    @Autowired
    private MapperFirmaCodigo mapperFirmaCodigo;
    @Autowired
    private FirmaService firmaService;

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
        firmaCodigoModel.setFechaCreacion(ahora);
        firmaCodigoModel.setFechaCaducidad(Utils.sumaRestaMinutos(15));
        List<FirmaCodigo> firmaCodigoList = firmaCodigoRepository.findByIdOperacion(firmaCodigoModel.getIdOperacion());
        if (firmaCodigoList != null && !firmaCodigoList.isEmpty()) {
            for (FirmaCodigo firmaCodigo: firmaCodigoList) {
                firmaCodigoRepository.delete(firmaCodigo);
            }
        }
        FirmaModel firmaModel = firmaService.findByIdOperacion(firmaCodigoModel.getIdOperacion());
        if (firmaModel.getNumeroIntentos() > 0) {
            firmaModel.setNumeroIntentos(0);
            firmaService.update(firmaModel);
        }
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
        List<FirmaCodigo> firmaCodigoList = firmaCodigoRepository.findByIdOperacion(idOperacion);
        if (firmaCodigoList == null || firmaCodigoList.isEmpty()) {
            return new FirmaCodigoModel();
        } else {
            return mapperFirmaCodigo.entity2Model(firmaCodigoList.get(0));
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
