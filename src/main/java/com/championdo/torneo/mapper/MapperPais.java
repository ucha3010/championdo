package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Pais;
import com.championdo.torneo.model.PaisModel;
import org.springframework.stereotype.Component;

@Component
public class MapperPais {

    public PaisModel entity2Model(Pais externObject) {
        PaisModel localObject = new PaisModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setNombre(externObject.getNombre());
            localObject.setPosition(externObject.getPosition());
        }
        return localObject;
    }
    public Pais model2Entity(PaisModel externObject) {
        Pais localObject = new Pais();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setNombre(externObject.getNombre());
            localObject.setPosition(externObject.getPosition());
        }
        return localObject;
    }
}
