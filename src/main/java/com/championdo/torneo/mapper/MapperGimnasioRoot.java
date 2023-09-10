package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.GimnasioRoot;
import com.championdo.torneo.model.GimnasioRootModel;
import org.springframework.stereotype.Component;

@Component
public class MapperGimnasioRoot {

    public GimnasioRootModel entity2Model(GimnasioRoot externObject) {
        GimnasioRootModel localObject = new GimnasioRootModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setEnabled(externObject.isEnabled());
            localObject.setNombreGimnasio(externObject.getNombreGimnasio());
            localObject.setNombreResponsable(externObject.getNombreResponsable());
            localObject.setApellido1Responsable(externObject.getApellido1Responsable());
            localObject.setApellido2Responsable(externObject.getApellido2Responsable());
            localObject.setFechaNacimiento(externObject.getFechaNacimiento());
            localObject.setDomicilioCalle(externObject.getDomicilioCalle());
            localObject.setDomicilioNumero(externObject.getDomicilioNumero());
            localObject.setDomicilioOtros(externObject.getDomicilioOtros());
            localObject.setDomicilioLocalidad(externObject.getDomicilioLocalidad());
            localObject.setDomicilioCp(externObject.getDomicilioCp());
            localObject.setCifNif(externObject.getCifNif());
            localObject.setVisibilidadContratada(externObject.getVisibilidadContratada());
            localObject.setCantidadRegistrosContratados(externObject.getCantidadRegistrosContratados());
            localObject.setFechaAlta(externObject.getFechaAlta());
            localObject.setFechaModificacion(externObject.getFechaModificacion());
            localObject.setUsuarioModificacion(externObject.getUsuarioModificacion());
            localObject.setTelefono(externObject.getTelefono());
            localObject.setCorreo(externObject.getCorreo());
        }
        return localObject;
    }
    public GimnasioRoot model2Entity(GimnasioRootModel externObject) {
        GimnasioRoot localObject = new GimnasioRoot();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setEnabled(externObject.isEnabled());
            localObject.setNombreGimnasio(externObject.getNombreGimnasio());
            localObject.setNombreResponsable(externObject.getNombreResponsable());
            localObject.setApellido1Responsable(externObject.getApellido1Responsable());
            localObject.setApellido2Responsable(externObject.getApellido2Responsable());
            localObject.setFechaNacimiento(externObject.getFechaNacimiento());
            localObject.setDomicilioCalle(externObject.getDomicilioCalle());
            localObject.setDomicilioNumero(externObject.getDomicilioNumero());
            localObject.setDomicilioOtros(externObject.getDomicilioOtros());
            localObject.setDomicilioLocalidad(externObject.getDomicilioLocalidad());
            localObject.setDomicilioCp(externObject.getDomicilioCp());
            localObject.setCifNif(externObject.getCifNif());
            localObject.setVisibilidadContratada(externObject.getVisibilidadContratada());
            localObject.setCantidadRegistrosContratados(externObject.getCantidadRegistrosContratados());
            localObject.setFechaAlta(externObject.getFechaAlta());
            localObject.setFechaModificacion(externObject.getFechaModificacion());
            localObject.setUsuarioModificacion(externObject.getUsuarioModificacion());
            localObject.setTelefono(externObject.getTelefono());
            localObject.setCorreo(externObject.getCorreo());
        }
        return localObject;
    }
}
