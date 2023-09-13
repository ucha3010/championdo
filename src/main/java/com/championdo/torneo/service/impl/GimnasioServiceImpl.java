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
    public List<GimnasioModel> findAll() {
        List<GimnasioModel> gimnasioModelList = new ArrayList<>();
        for (Gimnasio gimnasio: gimnasioRepository.findAllByOrderByPositionAsc()) {
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
        gimnasioRepository.deleteById(idGimnasio);
        List<Gimnasio> gimnasioList = gimnasioRepository.findAllByOrderByPositionAsc();
        for (int i = 0; i < gimnasioList.size(); i++) {
            if (gimnasioList.get(i).getPosition() != i) {
                gimnasioList.get(i).setPosition(i);
                gimnasioRepository.save(gimnasioList.get(i));
            }
        }
    }

    @Override
    public void dragOfPosition(int initialPosition, int finalPosition) {
        Gimnasio gimnasio = gimnasioRepository.findByPosition(initialPosition);
        if (initialPosition > finalPosition) {
            for (int i = initialPosition - 1; i >= finalPosition; i--) {
                moveItem(i, true);
            }
        }
        if (initialPosition < finalPosition) {
            for (int i = initialPosition + 1; i <= finalPosition; i++) {
                moveItem(i, false);
            }
        }
        gimnasio.setPosition(finalPosition);
        gimnasioRepository.save(gimnasio);
    }

    @Override
    public int findMaxPosition() {
        Gimnasio gimnasio = gimnasioRepository.findTopByOrderByPositionDesc();
        if (gimnasio != null) {
            return gimnasio.getPosition();
        } else {
          return -1;
        }
    }

    @Override
    public GimnasioModel addFromRoot (GimnasioRootModel gimnasioRootModel) {
        int lastPosition = gimnasioRepository.findTopByOrderByPositionDesc().getPosition();
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

    private void moveItem(int position, boolean moveUp) {
        Gimnasio gimnasio = gimnasioRepository.findByPosition(position);
        gimnasio.setPosition(position + (moveUp ? 1 : -1));
        gimnasioRepository.save(gimnasio);
    }
}
