package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.GimnasioMenu2;
import com.championdo.torneo.model.GimnasioMenu2Model;
import org.springframework.stereotype.Component;

@Component
public class MapperGimnasioMenu2 {

    public GimnasioMenu2Model entity2Model(GimnasioMenu2 externObject) {
        GimnasioMenu2Model localObject = new GimnasioMenu2Model();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdGimnasio(externObject.getIdGimnasio());
            localObject.setIdMenu2(externObject.getIdMenu2());
            localObject.setUsernameAlta(externObject.getUsernameAlta());
            localObject.setFechaAlta(externObject.getFechaAlta());
        }
        return localObject;
    }
    public GimnasioMenu2 model2Entity(GimnasioMenu2Model externObject) {
        GimnasioMenu2 localObject = new GimnasioMenu2();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdGimnasio(externObject.getIdGimnasio());
            localObject.setIdMenu2(externObject.getIdMenu2());
            localObject.setUsernameAlta(externObject.getUsernameAlta());
            localObject.setFechaAlta(externObject.getFechaAlta());
        }
        return localObject;
    }
}
