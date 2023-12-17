package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.GimnasioRoot;
import com.championdo.torneo.entity.GimnasioRootMenu2;
import com.championdo.torneo.mapper.MapperGimnasioRoot;
import com.championdo.torneo.mapper.MapperMenu2;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.model.Menu2Model;
import com.championdo.torneo.repository.GimnasioRootMenu2Repository;
import com.championdo.torneo.repository.GimnasioRootRepository;
import com.championdo.torneo.repository.Menu2Repository;
import com.championdo.torneo.service.GimnasioRootService;
import com.championdo.torneo.service.Menu2Service;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
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
    @Autowired
    private GimnasioRootMenu2Repository gimnasioRootMenu2Repository;
    @Autowired
    private Menu2Repository menu2Repository;
    @Autowired
    private MapperMenu2 mapperMenu2;

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
            GimnasioRootModel gimnasioRootModel = mapperGimnasioRoot.entity2Model(gimnasioRootRepository.getById(id));
            List<Menu2Model> menu2ModelList = new ArrayList<>();
            for(GimnasioRootMenu2 gimnasioRootMenu2: gimnasioRootMenu2Repository.findByIdGimnasioRoot(id)) {
                menu2ModelList.add(mapperMenu2.entity2Model(menu2Repository.getById(gimnasioRootMenu2.getIdMenu2())));
            }
            gimnasioRootModel.setMenu2ModelList(menu2ModelList);
            return gimnasioRootModel;
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
        gimnasioRootMenu2Repository.deleteByIdGimnasioRoot(idGimnasioRoot);
    }

    @Override
    public void enableDisable(int idGimnasioRootModel, boolean enableDisable) {
        GimnasioRootModel gimnasioRootModel = findById(idGimnasioRootModel);
        gimnasioRootModel.setEnabled(enableDisable);
        try {
            update(gimnasioRootModel);
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "enableDisable", e.getMessage(), this.getClass());
        }
    }

    @Override
    public boolean verifyEnable(int idGimnasioRootModel) {
        GimnasioRootModel gimnasioRootModel = findById(idGimnasioRootModel);
        return gimnasioRootModel.getId() != 0 && gimnasioRootModel.isEnabled();
    }
}
