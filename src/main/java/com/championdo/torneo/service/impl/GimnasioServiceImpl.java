package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Gimnasio;
import com.championdo.torneo.mapper.MapperGimnasio;
import com.championdo.torneo.mapper.MapperMenu2;
import com.championdo.torneo.model.GimnasioMenu2Model;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.Menu1Model;
import com.championdo.torneo.model.Menu2Model;
import com.championdo.torneo.repository.GimnasioRepository;
import com.championdo.torneo.repository.Menu2Repository;
import com.championdo.torneo.service.GimnasioMenu2Service;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service()
public class GimnasioServiceImpl implements GimnasioService {

    @Autowired
    private GimnasioRepository gimnasioRepository;
    @Autowired
    private MapperGimnasio mapperGimnasio;
    @Autowired
    private GimnasioMenu2Service gimnasioMenu2Service;
    @Autowired
    private Menu2Repository menu2Repository;
    @Autowired
    private MapperMenu2 mapperMenu2;



    @Override
    public List<GimnasioModel> findAll() {
        List<GimnasioModel> gimnasioModelList = new ArrayList<>();
        for (Gimnasio gimnasio: gimnasioRepository.findAll()) {
            gimnasioModelList.add(mapperGimnasio.entity2Model(gimnasio));
        }
        return gimnasioModelList;
    }

    @Override
    public List<GimnasioModel> findAllOrderByNombreGimnasioAsc() {
        List<GimnasioModel> gimnasioModelList = new ArrayList<>();
        for (Gimnasio gimnasio: gimnasioRepository.findAllByOrderByNombreGimnasioAsc()) {
            gimnasioModelList.add(mapperGimnasio.entity2Model(gimnasio));
        }
        return gimnasioModelList;
    }

    @Override
    public List<GimnasioModel> findByCifNif(String cifNif) {
        List<GimnasioModel> gimnasioModelList = new ArrayList<>();
        for (Gimnasio gimnasio: gimnasioRepository.findByCifNif(cifNif)) {
            gimnasioModelList.add(mapperGimnasio.entity2Model(gimnasio));
        }
        return gimnasioModelList;
    }

    @Override
    public GimnasioModel findById(int id) {
        try {
            GimnasioModel gimnasioModel = mapperGimnasio.entity2Model(gimnasioRepository.getById(id));
            List<Menu2Model> menu2ModelList = new ArrayList<>();
            for(GimnasioMenu2Model gimnasioMenu2: gimnasioMenu2Service.findByIdGimnasio(id)) {
                menu2ModelList.add(mapperMenu2.entity2Model(menu2Repository.getById(gimnasioMenu2.getIdMenu2())));
            }
            gimnasioModel.setMenu2ModelList(menu2ModelList);
            return gimnasioModel;
        } catch (EntityNotFoundException e) {
            return new GimnasioModel();
        }
    }

    @Override
    public GimnasioModel add(GimnasioModel gimnasioModel) {
        Date now = new Date();
        gimnasioModel.setFechaAlta(now);
        gimnasioModel.setFechaModificacion(now);
        gimnasioModel.setEnabled(Boolean.TRUE);
        return mapperGimnasio.entity2Model(gimnasioRepository.save(mapperGimnasio.model2Entity(gimnasioModel)));
    }

    @Override
    public GimnasioModel update(GimnasioModel gimnasioModel) {
        gimnasioModel.setFechaModificacion(new Date());
        return mapperGimnasio.entity2Model(gimnasioRepository.save(mapperGimnasio.model2Entity(gimnasioModel)));
    }

    @Override
    public void delete(int idGimnasio) {
        gimnasioRepository.deleteById(idGimnasio);
        gimnasioMenu2Service.deleteByIdGimnasio(idGimnasio);
    }

    @Override
    public void enableDisable(int idGimnasioModel, boolean enableDisable) {
        GimnasioModel gimnasioModel = findById(idGimnasioModel);
        gimnasioModel.setEnabled(enableDisable);
        try {
            update(gimnasioModel);
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "enableDisable", e.getMessage(), this.getClass());
        }
    }

    @Override
    public boolean verifyEnable(int idGimnasioModel) {
        GimnasioModel gimnasioModel = findById(idGimnasioModel);
        return gimnasioModel.getId() != 0 && gimnasioModel.isEnabled();
    }

    @Override
    public void fillMenu2Checked(ModelAndView modelAndView, int codigoGimnasio) {
        List<Menu1Model> menu1ModelList = (List<Menu1Model>) modelAndView.getModel().get("menu1List");
        GimnasioModel gimnasioModel = findById(codigoGimnasio);
        List<Integer> idList = new ArrayList<>();
        for (Menu2Model menu2ModelAux: gimnasioModel.getMenu2ModelList()){
            idList.add(menu2ModelAux.getId());
        }
        for (Menu1Model menu1Model : menu1ModelList) {
            for (Menu2Model menu2Model : menu1Model.getMenu2ModelList()) {
                menu2Model.setChecked(idList.contains(menu2Model.getId()));
            }
        }
        modelAndView.addObject("menu1List", menu1ModelList);
    }

    @Override
    public List<GimnasioModel> findByMenu2Url(String url) {
        List<GimnasioMenu2Model> gimnasioMenu2ModelList = gimnasioMenu2Service.findByIdMenu2(menu2Repository.findByUrl(url).getId());
        List<GimnasioModel> gimnasioModelList = new ArrayList<>();
        GimnasioModel gimnasioModel;
        for (GimnasioMenu2Model gimnasioMenu2Model : gimnasioMenu2ModelList) {
            gimnasioModel = findById(gimnasioMenu2Model.getIdGimnasio());
            if(gimnasioModel.isEnabled()) {
                gimnasioModelList.add(gimnasioModel);
            }
        }
        return gimnasioModelList;
    }
}
