package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Menu2;
import com.championdo.torneo.model.Menu2Model;
import org.springframework.stereotype.Component;

@Component
public class MapperMenu2 {

    public Menu2Model entity2Model(Menu2 externObject) {
        Menu2Model localObject = new Menu2Model();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdMenu1(externObject.getIdMenu1());
            localObject.setEnabled(externObject.isEnabled());
            localObject.setNombre(externObject.getNombre());
            localObject.setPosition(externObject.getPosition());
            localObject.setUrl(externObject.getUrl());
            localObject.setAviso(externObject.getAviso());
        }
        return localObject;
    }
    public Menu2 model2Entity(Menu2Model externObject) {
        Menu2 localObject = new Menu2();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdMenu1(externObject.getIdMenu1());
            localObject.setEnabled(externObject.isEnabled());
            localObject.setNombre(externObject.getNombre());
            localObject.setPosition(externObject.getPosition());
            localObject.setUrl(externObject.getUrl());
            localObject.setAviso(externObject.getAviso());
        }
        return localObject;
    }
}
