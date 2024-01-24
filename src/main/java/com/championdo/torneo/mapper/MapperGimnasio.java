package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Gimnasio;
import com.championdo.torneo.model.GimnasioModel;
import org.springframework.stereotype.Component;

@Component
public class MapperGimnasio {

    public GimnasioModel entity2Model(Gimnasio externObject) {
        GimnasioModel localObject = new GimnasioModel();
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
            localObject.setEmailPassword(externObject.getEmailPassword());
            localObject.setEmailHost(externObject.getEmailHost());
            localObject.setEmailPort(externObject.getEmailPort());
        }
        return localObject;
    }
    public Gimnasio model2Entity(GimnasioModel externObject) {
        Gimnasio localObject = new Gimnasio();
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
            localObject.setEmailPassword(externObject.getEmailPassword());
            localObject.setEmailHost(externObject.getEmailHost());
            localObject.setEmailPort(externObject.getEmailPort());
        }
        return localObject;
    }
}
