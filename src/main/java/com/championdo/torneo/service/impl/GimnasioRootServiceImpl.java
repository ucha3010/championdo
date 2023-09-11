package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.GimnasioRoot;
import com.championdo.torneo.mapper.MapperGimnasioRoot;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.repository.GimnasioRootRepository;
import com.championdo.torneo.service.GimnasioRootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service()
public class GimnasioRootServiceImpl implements GimnasioRootService {

    @Autowired
    private GimnasioRootRepository gimnasioRootRepository;

    @Autowired
    private MapperGimnasioRoot mapperGimnasioRoot;

    @Override
    public List<GimnasioRootModel> findAll() {
        List<GimnasioRootModel> gimnasioRootModelList = new ArrayList<>();
        for (GimnasioRoot gimnasioRoot: gimnasioRootRepository.findAll()) {
            gimnasioRootModelList.add(mapperGimnasioRoot.entity2Model(gimnasioRoot));
        }
        return gimnasioRootModelList;
    }

    @Override
    public List<GimnasioRootModel> findAllOrderByNombreGimnasioAsc() {
        List<GimnasioRootModel> gimnasioRootModelList = new ArrayList<>();
        for (GimnasioRoot gimnasioRoot: gimnasioRootRepository.findAllByOrderByNombreGimnasioAsc()) {
            gimnasioRootModelList.add(mapperGimnasioRoot.entity2Model(gimnasioRoot));
        }
        return gimnasioRootModelList;
    }

    @Override
    public GimnasioRootModel findById(int id) {
        try {
            return mapperGimnasioRoot.entity2Model(gimnasioRootRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new GimnasioRootModel();
        }
    }

    @Override
    public GimnasioRootModel add(GimnasioRootModel gimnasioRootModel) {
        Date now = new Date();
        gimnasioRootModel.setFechaAlta(now);
        gimnasioRootModel.setFechaModificacion(now);
        gimnasioRootModel.setEnabled(Boolean.TRUE);
         return mapperGimnasioRoot.entity2Model(gimnasioRootRepository.save(mapperGimnasioRoot.model2Entity(gimnasioRootModel)));
    }

    @Override
    public void update(GimnasioRootModel gimnasioRootModel) throws Exception {
        gimnasioRootModel.setFechaModificacion(new Date());
        gimnasioRootRepository.save(mapperGimnasioRoot.model2Entity(gimnasioRootModel));
    }

    @Override
    public void delete(int idGimnasioRoot) {
        gimnasioRootRepository.deleteById(idGimnasioRoot);
    }
}
