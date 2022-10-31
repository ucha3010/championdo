package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Calidad;
import com.championdo.torneo.model.CalidadModel;
import org.springframework.stereotype.Component;

@Component
public class MapperCalidad {

    public CalidadModel entity2Model(Calidad externObject) {
        CalidadModel localObject = new CalidadModel();
        localObject.setId(externObject.getId());
        localObject.setNombre(externObject.getNombre());
        localObject.setOtro(externObject.getOtro());
        return localObject;
    }
}
