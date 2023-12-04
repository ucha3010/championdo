package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Mandato;
import com.championdo.torneo.model.MandatoModel;
import org.springframework.stereotype.Component;

@Component
public class MapperMandato {

    public MandatoModel entity2Model(Mandato externObject) {
        MandatoModel localObject = new MandatoModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setFechaAlta(externObject.getFechaAlta());
            localObject.setTemporada(externObject.getTemporada());
            localObject.setAdulto(externObject.isAdulto());
            localObject.setMandante(externObject.getMandante());
            localObject.setDniMandante(externObject.getDniMandante());
            localObject.setAutorizado(externObject.getAutorizado());
            localObject.setDniAutorizado(externObject.getDniAutorizado());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
    public Mandato model2Entity(MandatoModel externObject) {
        Mandato localObject = new Mandato();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setFechaAlta(externObject.getFechaAlta());
            localObject.setTemporada(externObject.getTemporada());
            localObject.setAdulto(externObject.isAdulto());
            localObject.setMandante(externObject.getMandante());
            localObject.setDniMandante(externObject.getDniMandante());
            localObject.setAutorizado(externObject.getAutorizado());
            localObject.setDniAutorizado(externObject.getDniAutorizado());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
}
