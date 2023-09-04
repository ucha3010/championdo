package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Firma;
import com.championdo.torneo.model.FirmaModel;
import org.springframework.stereotype.Component;

@Component
public class MapperFirma {

    public FirmaModel entity2Model(Firma externObject) {
        FirmaModel localObject = new FirmaModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdOperacion(externObject.getIdOperacion());
            localObject.setNumeroIntentos(externObject.getNumeroIntentos());
            localObject.setFirmado(externObject.isFirmado());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
    public Firma model2Entity(FirmaModel externObject) {
        Firma localObject = new Firma();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdOperacion(externObject.getIdOperacion());
            localObject.setNumeroIntentos(externObject.getNumeroIntentos());
            localObject.setFirmado(externObject.isFirmado());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
}
