package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Menu2;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.mapper.MapperMenu2;
import com.championdo.torneo.model.GimnasioRootMenu2Model;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.model.Menu2Model;
import com.championdo.torneo.repository.Menu2Repository;
import com.championdo.torneo.service.GimnasioRootMenu2Service;
import com.championdo.torneo.service.GimnasioRootService;
import com.championdo.torneo.service.Menu2Service;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class Menu2ServiceImpl implements Menu2Service {

    @Autowired
    private Menu2Repository menu2Repository;
    @Autowired
    private MapperMenu2 mapperMenu2;
    @Autowired
    private GimnasioRootMenu2Service gimnasioRootMenu2Service;
    @Autowired
    private GimnasioRootService gimnasioRootService;

    @Override
    public List<Menu2Model> findAll(int idMenu1) {
        List<Menu2Model> menu2ModelList = new ArrayList<>();
        for (Menu2 menu2: menu2Repository.findByIdMenu1OrderByPositionAsc(idMenu1)) {
            menu2ModelList.add(mapperMenu2.entity2Model(menu2));
        }
        return menu2ModelList;
    }

    @Override
    public Menu2Model findById(int id) {
        try {
            Menu2Model menu2Model = mapperMenu2.entity2Model(menu2Repository.getById(id));
            List<GimnasioRootModel> gimnasioRootModelList = new ArrayList<>();
            for(GimnasioRootMenu2Model gimnasioRootMenu2: gimnasioRootMenu2Service.findByIdMenu2(id)) {
                gimnasioRootModelList.add(gimnasioRootService.findById(gimnasioRootMenu2.getIdGimnasioRoot()));
            }
            menu2Model.setGimnasioRootModelList(gimnasioRootModelList);
            return menu2Model;
        } catch (EntityNotFoundException e) {
            return new Menu2Model();
        }
    }

    @Override
    public void add(Menu2Model menu2Model) {
        menu2Repository.save(mapperMenu2.model2Entity(menu2Model));
    }

    @Override
    public void update(Menu2Model menu2Model) {
        menu2Repository.save(mapperMenu2.model2Entity(menu2Model));
    }

    @Override
    public void delete(int id) throws RemoveException {
        Menu2 menu2 = menu2Repository.getById(id);
        try {
            menu2Repository.deleteById(id);
            List<Menu2> menu2List = menu2Repository.findByIdMenu1OrderByPositionAsc(menu2.getIdMenu1());
            for (int i = 0; i < menu2List.size(); i++) {
                if (menu2List.get(i).getPosition() != i) {
                    menu2List.get(i).setPosition(i);
                    menu2Repository.save(menu2List.get(i));
                }
            }
            gimnasioRootMenu2Service.deleteByIdMenu2(id);
        } catch (IllegalArgumentException e){
            LoggerMapper.log(Level.ERROR, "delete", e.getMessage(), getClass());
            throw new RemoveException(Constantes.ERROR_BORRAR_MENU, "Error al borrar el menú secundario");
        }
        LoggerMapper.methodOut(Level.INFO, "delete", menu2.getId(), getClass());
    }

    @Override
    public void dragOfPosition(int idMenu1, int initialPosition, int finalPosition) {
        Menu2 menu2 = menu2Repository.findByIdMenu1AndPosition(idMenu1, initialPosition);
        if (initialPosition > finalPosition) {
            for (int i = initialPosition - 1; i >= finalPosition; i--) {
                moveItem(idMenu1, i, true);
            }
        }
        if (initialPosition < finalPosition) {
            for (int i = initialPosition + 1; i <= finalPosition; i++) {
                moveItem(idMenu1, i, false);
            }
        }
        menu2.setPosition(finalPosition);
        menu2Repository.save(menu2);
    }

    @Override
    public int findMaxPosition(int idMenu1) {
        Menu2 menu2 = menu2Repository.findTopByIdMenu1OrderByPositionDesc(idMenu1);
        if (menu2 != null) {
            return menu2.getPosition();
        } else {
            return -1;
        }
    }

    @Override
    public Menu2Model findByUrl(String url) {
        return mapperMenu2.entity2Model(menu2Repository.findByUrl(url));
    }

    private void moveItem(int idMenu1, int position, boolean moveUp) {
        Menu2 menu2 = menu2Repository.findByIdMenu1AndPosition(idMenu1, position);
        menu2.setPosition(position + (moveUp ? 1 : -1));
        menu2Repository.save(menu2);
    }
}
