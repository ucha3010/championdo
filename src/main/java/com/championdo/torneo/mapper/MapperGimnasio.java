package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Gimnasio;
import com.championdo.torneo.model.GimnasioModel;
import org.springframework.stereotype.Component;

@Component
public class MapperGimnasio {

    public GimnasioModel entity2Model(Gimnasio externObject) {
        GimnasioModel localObject = new GimnasioModel();
        localObject.setId(externObject.getId());
        localObject.setNombre(externObject.getNombre());
        localObject.setDireccion(externObject.getDireccion());
        localObject.setPosition(externObject.getPosition());
        return localObject;
    }
    public Gimnasio model2Entity(GimnasioModel externObject) {
        Gimnasio localObject = new Gimnasio();
        localObject.setId(externObject.getId());
        localObject.setNombre(externObject.getNombre());
        localObject.setDireccion(externObject.getDireccion());
        localObject.setPosition(externObject.getPosition());
        return localObject;
    }
}
