package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Gimnasio;
import com.championdo.torneo.mapper.MapperGimnasio;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.repository.GimnasioRepository;
import com.championdo.torneo.service.GimnasioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class GimnasioServiceImpl implements GimnasioService {

    @Autowired
    private GimnasioRepository gimnasioRepository;

    @Autowired
    private MapperGimnasio mapperGimnasio;

    @Override
    public List<GimnasioModel> findAll(int codigoGimnasio) {
        List<GimnasioModel> gimnasioModelList = new ArrayList<>();
        for (Gimnasio gimnasio: gimnasioRepository.findByCodigoGimnasioOrderByPositionAsc(codigoGimnasio)) {
            gimnasioModelList.add(mapperGimnasio.entity2Model(gimnasio));
        }
        return gimnasioModelList;
    }

    @Override
    public GimnasioModel findById(int id) {
        try {
            return mapperGimnasio.entity2Model(gimnasioRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new GimnasioModel();
        }
    }

    @Override
    public GimnasioModel add(GimnasioModel gimnasioModel) {
        return mapperGimnasio.entity2Model(gimnasioRepository.save(mapperGimnasio.model2Entity(gimnasioModel)));
    }

    @Override
    public void update(GimnasioModel gimnasioModel) {
        gimnasioRepository.save(mapperGimnasio.model2Entity(gimnasioModel));
    }

    @Override
    public void delete(int idGimnasio) {
        GimnasioModel gimnasioModel = findById(idGimnasio);
        gimnasioRepository.deleteById(idGimnasio);
        List<Gimnasio> gimnasioList = gimnasioRepository.findByCodigoGimnasioOrderByPositionAsc(gimnasioModel.getCodigoGimnasio());
        for (int i = 0; i < gimnasioList.size(); i++) {
            if (gimnasioList.get(i).getPosition() != i) {
                gimnasioList.get(i).setPosition(i);
                gimnasioRepository.save(gimnasioList.get(i));
            }
        }
    }

    @Override
    public void dragOfPosition(int codigoGimnasio, int initialPosition, int finalPosition) {
        Gimnasio gimnasio = gimnasioRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, initialPosition);
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
        gimnasio.setPosition(finalPosition);
        gimnasioRepository.save(gimnasio);
    }

    @Override
    public int findMaxPosition(int codigoGimnasio) {
        Gimnasio gimnasio = gimnasioRepository.findTopByCodigoGimnasioOrderByPositionDesc(codigoGimnasio);
        if (gimnasio != null) {
            return gimnasio.getPosition();
        } else {
          return -1;
        }
    }

    @Override
    public GimnasioModel addFromRoot (GimnasioRootModel gimnasioRootModel) {
        int lastPosition = gimnasioRepository.findTopByCodigoGimnasioOrderByPositionDesc(gimnasioRootModel.getId()).getPosition();
        GimnasioModel gimnasioModel = new GimnasioModel();
        gimnasioModel.setCodigoGimnasio(gimnasioRootModel.getId());
        gimnasioModel.setNombre(gimnasioRootModel.getNombreGimnasio());
        StringBuilder address = new StringBuilder(gimnasioRootModel.getDomicilioCalle());
        if(!gimnasioRootModel.getDomicilioNumero().isEmpty()){
            address.append(" ").append(gimnasioRootModel.getDomicilioNumero());
        }
        if(!gimnasioRootModel.getDomicilioOtros().isEmpty()){
            address.append(" ").append(gimnasioRootModel.getDomicilioOtros());
        }
        address.append(" (").append(gimnasioRootModel.getDomicilioCp()).append(") ").append(gimnasioRootModel.getDomicilioLocalidad());
        gimnasioModel.setDireccion(address.toString());
        gimnasioModel.setPosition(lastPosition + 1);
        return add(gimnasioModel);
    }

    @Override
    public void deleteFromRoot (int idGimnasioRootModel) {
        List<Gimnasio> gimnasioList = gimnasioRepository.findByCodigoGimnasio(idGimnasioRootModel);
        for (Gimnasio gimnasio: gimnasioList) {
            delete(gimnasio.getId());
        }
    }

    private void moveItem(int codigoGimnasio, int position, boolean moveUp) {
        Gimnasio gimnasio = gimnasioRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, position);
        gimnasio.setPosition(position + (moveUp ? 1 : -1));
        gimnasioRepository.save(gimnasio);
    }
}
