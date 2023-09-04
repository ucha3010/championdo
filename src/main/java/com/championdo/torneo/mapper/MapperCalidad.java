package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Calidad;
import com.championdo.torneo.model.CalidadModel;
import org.springframework.stereotype.Component;

@Component
public class MapperCalidad {

    public CalidadModel entity2Model(Calidad externObject) {
        CalidadModel localObject = new CalidadModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setNombre(externObject.getNombre());
            localObject.setOtro(externObject.getOtro());
            localObject.setPosition(externObject.getPosition());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
    public Calidad model2Entity(CalidadModel externObject) {
        Calidad localObject = new Calidad();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setNombre(externObject.getNombre());
            localObject.setOtro(externObject.getOtro());
            localObject.setPosition(externObject.getPosition());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
}
