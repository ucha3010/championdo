package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Poomsae;
import com.championdo.torneo.model.PoomsaeModel;
import org.springframework.stereotype.Component;

@Component
public class MapperPoomsae {

    public PoomsaeModel entity2Model(Poomsae externObject) {
        PoomsaeModel localObject = new PoomsaeModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setNombre(externObject.getNombre());
            localObject.setPosition(externObject.getPosition());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
    public Poomsae model2Entity(PoomsaeModel externObject) {
        Poomsae localObject = new Poomsae();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setNombre(externObject.getNombre());
            localObject.setPosition(externObject.getPosition());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
}
