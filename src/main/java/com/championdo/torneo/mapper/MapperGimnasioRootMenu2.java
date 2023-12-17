package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.GimnasioRootMenu2;
import com.championdo.torneo.model.GimnasioRootMenu2Model;
import org.springframework.stereotype.Component;

@Component
public class MapperGimnasioRootMenu2 {

    public GimnasioRootMenu2Model entity2Model(GimnasioRootMenu2 externObject) {
        GimnasioRootMenu2Model localObject = new GimnasioRootMenu2Model();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdGimnasioRoot(externObject.getIdGimnasioRoot());
            localObject.setIdMenu2(externObject.getIdMenu2());
            localObject.setUsernameAlta(externObject.getUsernameAlta());
            localObject.setFechaAlta(externObject.getFechaAlta());
        }
        return localObject;
    }
    public GimnasioRootMenu2 model2Entity(GimnasioRootMenu2Model externObject) {
        GimnasioRootMenu2 localObject = new GimnasioRootMenu2();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdGimnasioRoot(externObject.getIdGimnasioRoot());
            localObject.setIdMenu2(externObject.getIdMenu2());
            localObject.setUsernameAlta(externObject.getUsernameAlta());
            localObject.setFechaAlta(externObject.getFechaAlta());
        }
        return localObject;
    }
}
