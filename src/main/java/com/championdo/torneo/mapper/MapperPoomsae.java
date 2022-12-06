package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Poomsae;
import com.championdo.torneo.model.PoomsaeModel;
import org.springframework.stereotype.Component;

@Component
public class MapperPoomsae {

    public PoomsaeModel entity2Model(Poomsae externObject) {
        PoomsaeModel localObject = new PoomsaeModel();
        localObject.setId(externObject.getId());
        localObject.setNombre(externObject.getNombre());
        localObject.setPosition(externObject.getPosition());
        return localObject;
    }
    public Poomsae model2Entity(PoomsaeModel externObject) {
        Poomsae localObject = new Poomsae();
        localObject.setId(externObject.getId());
        localObject.setNombre(externObject.getNombre());
        localObject.setPosition(externObject.getPosition());
        return localObject;
    }
}
