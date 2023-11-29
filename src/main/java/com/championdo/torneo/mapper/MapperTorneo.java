package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Torneo;
import com.championdo.torneo.model.TorneoModel;
import com.championdo.torneo.service.TorneoGimnasioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperTorneo {

    @Autowired
    private TorneoGimnasioService torneoGimnasioService;

    public TorneoModel entity2Model(Torneo externObject) {
        TorneoModel localObject = new TorneoModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setNombre(externObject.getNombre());
            localObject.setDireccion(externObject.getDireccion());
            localObject.setFechaTorneo(externObject.getFechaTorneo());
            localObject.setFechaComienzoInscripcion(externObject.getFechaComienzoInscripcion());
            localObject.setFechaFinInscripcion(externObject.getFechaFinInscripcion());
            localObject.setAdulto(externObject.isAdulto());
            localObject.setMenor(externObject.isMenor());
            localObject.setInclusivo(externObject.isInclusivo());
            localObject.setGimnasios(torneoGimnasioService.findAll(externObject.getId()));
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
    public Torneo model2Entity(TorneoModel externObject) {
        Torneo localObject = new Torneo();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setNombre(externObject.getNombre());
            localObject.setDireccion(externObject.getDireccion());
            localObject.setFechaTorneo(externObject.getFechaTorneo());
            localObject.setFechaComienzoInscripcion(externObject.getFechaComienzoInscripcion());
            localObject.setFechaFinInscripcion(externObject.getFechaFinInscripcion());
            localObject.setAdulto(externObject.isAdulto());
            localObject.setMenor(externObject.isMenor());
            localObject.setInclusivo(externObject.isInclusivo());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
}
