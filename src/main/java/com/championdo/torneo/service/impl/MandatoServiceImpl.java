package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Mandato;
import com.championdo.torneo.mapper.MapperMandato;
import com.championdo.torneo.model.MandatoModel;
import com.championdo.torneo.repository.MandatoRepository;
import com.championdo.torneo.service.MandatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class MandatoServiceImpl implements MandatoService {

    @Autowired
    private MandatoRepository mandatoRepository;
    @Autowired
    private MapperMandato mapperMandato;

    @Override
    public List<MandatoModel> findAll(int codigoGimnasio) {
        return fillModelList(mandatoRepository.findByCodigoGimnasioOrderByFechaAltaDesc(codigoGimnasio));
    }

    @Override
    public MandatoModel findById(int id) {
        try {
            return mapperMandato.entity2Model(mandatoRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new MandatoModel();
        }
    }

    @Override
    public List<MandatoModel> findByDniMandante(int codigoGimnasio, String dniMandante) {
        return fillModelList(mandatoRepository.findByCodigoGimnasioAndDniMandanteOrderByFechaAltaDesc(codigoGimnasio, dniMandante));
    }

    @Override
    public MandatoModel add(MandatoModel MandatoModel) {
        return mapperMandato.entity2Model(mandatoRepository.save(mapperMandato.model2Entity(MandatoModel)));
    }

    @Override
    public void delete(int idMandato) {
        mandatoRepository.deleteById(idMandato);
    }

    private List<MandatoModel> fillModelList(List<Mandato> mandatoList) {
        List<MandatoModel> mandatoModelList = new ArrayList<>();
        for (Mandato Mandato: mandatoList) {
            mandatoModelList.add(mapperMandato.entity2Model(Mandato));
        }
        return mandatoModelList;
    }
}
