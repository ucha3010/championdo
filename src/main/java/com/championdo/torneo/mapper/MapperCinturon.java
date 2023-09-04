package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Cinturon;
import com.championdo.torneo.model.CinturonModel;
import org.springframework.stereotype.Component;

@Component
public class MapperCinturon {
    public CinturonModel entity2Model(Cinturon externObject) {
        CinturonModel localObject = new CinturonModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setColor((externObject.getColor()));
            localObject.setCategoria(externObject.getCategoria());
            localObject.setPosition(externObject.getPosition());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
    public Cinturon model2Entity(CinturonModel externObject) {
        Cinturon localObject = new Cinturon();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setColor((externObject.getColor()));
            localObject.setCategoria(externObject.getCategoria());
            localObject.setPosition(externObject.getPosition());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
}
