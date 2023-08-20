package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Firma;
import com.championdo.torneo.model.FirmaModel;
import org.springframework.stereotype.Component;

@Component
public class MapperFirma {

    public FirmaModel entity2Model(Firma externObject) {
        FirmaModel localObject = new FirmaModel();
        localObject.setId(externObject.getId());
        localObject.setIdOperacion(externObject.getIdOperacion());
        localObject.setNumeroIntentos(externObject.getNumeroIntentos());
        localObject.setFirmado(externObject.isFirmado());
        return localObject;
    }
    public Firma model2Entity(FirmaModel externObject) {
        Firma localObject = new Firma();
        localObject.setId(externObject.getId());
        localObject.setIdOperacion(externObject.getIdOperacion());
        localObject.setNumeroIntentos(externObject.getNumeroIntentos());
        localObject.setFirmado(externObject.isFirmado());
        return localObject;
    }
}
