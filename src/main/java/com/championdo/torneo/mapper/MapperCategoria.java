package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Categoria;
import com.championdo.torneo.model.CategoriaModel;
import com.championdo.torneo.service.CinturonService;
import com.championdo.torneo.service.PoomsaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperCategoria {

    @Autowired
    private CinturonService cinturonService;

    @Autowired
    private PoomsaeService poomsaeService;

    public CategoriaModel entity2Model(Categoria externObject) {
        CategoriaModel localObject = new CategoriaModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setEdadInicio(externObject.getEdadInicio());
            localObject.setEdadFin(externObject.getEdadFin());
            localObject.setNombre(externObject.getNombre());
            localObject.setCinturonInicio(cinturonService.findById(externObject.getIdCinturonInicio()));
            localObject.setCinturonFin(cinturonService.findById(externObject.getIdCinturonFin()));
            localObject.setPoomsae(poomsaeService.findById(externObject.getIdPoomsae()));
            localObject.setInclusivo(externObject.isInclusivo());
            localObject.setInfantil(externObject.isInfantil());
            localObject.setPosition(externObject.getPosition());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
    public Categoria model2Entity(CategoriaModel externObject) {
        Categoria localObject = new Categoria();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setEdadInicio(externObject.getEdadInicio());
            localObject.setEdadFin(externObject.getEdadFin());
            localObject.setNombre(externObject.getNombre());
            if (externObject.getCinturonInicio() != null) {
                localObject.setIdCinturonInicio(externObject.getCinturonInicio().getId());
            }
            if (externObject.getCinturonFin() != null) {
                localObject.setIdCinturonFin(externObject.getCinturonFin().getId());
            }
            if (externObject.getPoomsae() != null) {
                localObject.setIdPoomsae(externObject.getPoomsae().getId());
            }
            localObject.setInclusivo(externObject.isInclusivo());
            localObject.setInfantil(externObject.isInfantil());
            localObject.setPosition(externObject.getPosition());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
}
