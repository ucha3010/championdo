package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.TorneoGimnasio;
import com.championdo.torneo.model.TorneoGimnasioModel;
import org.springframework.stereotype.Component;

@Component
public class MapperTorneoGimnasio {

    public TorneoGimnasioModel entity2Model(TorneoGimnasio externObject) {
        TorneoGimnasioModel localObject = new TorneoGimnasioModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdTorneo(externObject.getIdTorneo());
            localObject.setNombreGimnasio(externObject.getNombreGimnasio());
            localObject.setPosition(externObject.getPosition());
        }
        return localObject;
    }
    public TorneoGimnasio model2Entity(TorneoGimnasioModel externObject) {
        TorneoGimnasio localObject = new TorneoGimnasio();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdTorneo(externObject.getIdTorneo());
            localObject.setNombreGimnasio(externObject.getNombreGimnasio());
            localObject.setPosition(externObject.getPosition());
        }
        return localObject;
    }
}
