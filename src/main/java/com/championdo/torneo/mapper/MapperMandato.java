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
            localObject.setNombreMandante(externObject.getNombreMandante());
            localObject.setApellido1Mandante(externObject.getApellido1Mandante());
            localObject.setApellido2Mandante(externObject.getApellido2Mandante());
            localObject.setDniMandante(externObject.getDniMandante());
            localObject.setCorreoMandante(externObject.getCorreoMandante());
            localObject.setCalidad(externObject.getCalidad());
            localObject.setCalidadOtro(externObject.getCalidadOtro());
            localObject.setNombreAutorizado(externObject.getNombreAutorizado());
            localObject.setApellido1Autorizado(externObject.getApellido1Autorizado());
            localObject.setApellido2Autorizado(externObject.getApellido2Autorizado());
            localObject.setDniAutorizado(externObject.getDniAutorizado() != null ? externObject.getDniAutorizado().toUpperCase(): null);
            localObject.setDomicilioCalle(externObject.getDomicilioCalle());
            localObject.setDomicilioNumero(externObject.getDomicilioNumero());
            localObject.setDomicilioOtros(externObject.getDomicilioOtros());
            localObject.setDomicilioLocalidad(externObject.getDomicilioLocalidad());
            localObject.setDomicilioCp(externObject.getDomicilioCp());
            localObject.setPais(externObject.getPais());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
            localObject.setMandatoFirmado(externObject.isMandatoFirmado());
            localObject.setLicenciaAbonada(externObject.isLicenciaAbonada());
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
            localObject.setNombreMandante(externObject.getNombreMandante());
            localObject.setApellido1Mandante(externObject.getApellido1Mandante());
            localObject.setApellido2Mandante(externObject.getApellido2Mandante());
            localObject.setDniMandante(externObject.getDniMandante());
            localObject.setCorreoMandante(externObject.getCorreoMandante());
            localObject.setCalidad(externObject.getCalidad());
            localObject.setCalidadOtro(externObject.getCalidadOtro());
            localObject.setNombreAutorizado(externObject.getNombreAutorizado());
            localObject.setApellido1Autorizado(externObject.getApellido1Autorizado());
            localObject.setApellido2Autorizado(externObject.getApellido2Autorizado());
            localObject.setDniAutorizado(externObject.getDniAutorizado() != null ? externObject.getDniAutorizado().toUpperCase(): null);
            localObject.setDomicilioCalle(externObject.getDomicilioCalle());
            localObject.setDomicilioNumero(externObject.getDomicilioNumero());
            localObject.setDomicilioOtros(externObject.getDomicilioOtros());
            localObject.setDomicilioLocalidad(externObject.getDomicilioLocalidad());
            localObject.setDomicilioCp(externObject.getDomicilioCp());
            localObject.setPais(externObject.getPais());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
            localObject.setMandatoFirmado(externObject.isMandatoFirmado());
            localObject.setLicenciaAbonada(externObject.isLicenciaAbonada());
        }
        return localObject;
    }
}
