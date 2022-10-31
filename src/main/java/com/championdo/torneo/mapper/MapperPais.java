package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Pais;
import com.championdo.torneo.model.PaisModel;
import org.springframework.stereotype.Component;

@Component
public class MapperPais {

    public PaisModel entity2Model(Pais externObject) {
        PaisModel localObject = new PaisModel();
        localObject.setId(externObject.getId());
        localObject.setNombre(externObject.getNombre());
        return localObject;
    }
}
