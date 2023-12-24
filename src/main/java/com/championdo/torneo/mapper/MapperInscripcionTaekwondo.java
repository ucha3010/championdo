package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.InscripcionTaekwondo;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import org.springframework.stereotype.Component;

@Component
public class MapperInscripcionTaekwondo {

    public InscripcionTaekwondoModel entity2Model(InscripcionTaekwondo externObject) {

        InscripcionTaekwondoModel localObject = new InscripcionTaekwondoModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setFechaInscripcion(externObject.getFechaInscripcion());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
            localObject.setNombreGimnasio(externObject.getNombreGimnasio());
            localObject.setMayorDni(externObject.getMayorDni());
            localObject.setMayorNombre(externObject.getMayorNombre());
            localObject.setMayorApellido1(externObject.getMayorApellido1());
            localObject.setMayorApellido2(externObject.getMayorApellido2());
            localObject.setMayorSexo(externObject.getMayorSexo());
            localObject.setMayorFechaNacimiento(externObject.getMayorFechaNacimiento());
            localObject.setMayorCalidad(externObject.getMayorCalidad());
            localObject.setMayorPais(externObject.getMayorPais());
            localObject.setMayorCinturon(externObject.getMayorCinturon());
            localObject.setMayorCorreo(externObject.getMayorCorreo());
            localObject.setMayorDomicilioCalle(externObject.getMayorDomicilioCalle());
            localObject.setMayorDomicilioNumero(externObject.getMayorDomicilioNumero());
            localObject.setMayorDomicilioOtros(externObject.getMayorDomicilioOtros());
            localObject.setMayorDomicilioLocalidad(externObject.getMayorDomicilioLocalidad());
            localObject.setMayorDomicilioCp(externObject.getMayorDomicilioCp());
            localObject.setMayorLicencia(externObject.isMayorLicencia());
            localObject.setMayorTelefono(externObject.getMayorTelefono());
            localObject.setMayorAutorizaWhatsApp(externObject.isMayorAutorizaWhatsApp());
            localObject.setAutorizadoMenor(externObject.isAutorizadoMenor());
            localObject.setAutorizadoNombre(externObject.getAutorizadoNombre());
            localObject.setAutorizadoApellido1(externObject.getAutorizadoApellido1());
            localObject.setAutorizadoApellido2(externObject.getAutorizadoApellido2());
            localObject.setAutorizadoSexo(externObject.getAutorizadoSexo());
            localObject.setAutorizadoFechaNacimiento(externObject.getAutorizadoFechaNacimiento());
            localObject.setAutorizadoPais(externObject.getAutorizadoPais());
            localObject.setAutorizadoCinturon(externObject.getAutorizadoCinturon());
            localObject.setAutorizadoDni(externObject.getAutorizadoDni());
            localObject.setAutorizadoLicencia(externObject.isAutorizadoLicencia());
            localObject.setDomiciliacionSEPA(externObject.isDomiciliacionSEPA());
            localObject.setTitularCuenta(externObject.getTitularCuenta());
            localObject.setIban(externObject.getIban());
            localObject.setSwift(externObject.getSwift());
            localObject.setNotas(externObject.getNotas());
            localObject.setInscripcionFirmada(externObject.isInscripcionFirmada());
            localObject.setDomiciliacionSEPAFirmada(externObject.isDomiciliacionSEPAFirmada());
            localObject.setExtensionSEPAFirmado(externObject.getExtensionSEPAFirmado());
        }
        return localObject;
    }

    public InscripcionTaekwondo model2Entity(InscripcionTaekwondoModel externObject) {

        InscripcionTaekwondo localObject = new InscripcionTaekwondo();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setFechaInscripcion(externObject.getFechaInscripcion());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
            localObject.setNombreGimnasio(externObject.getNombreGimnasio());
            localObject.setMayorDni(externObject.getMayorDni());
            localObject.setMayorNombre(externObject.getMayorNombre());
            localObject.setMayorApellido1(externObject.getMayorApellido1());
            localObject.setMayorApellido2(externObject.getMayorApellido2());
            localObject.setMayorSexo(externObject.getMayorSexo());
            localObject.setMayorFechaNacimiento(externObject.getMayorFechaNacimiento());
            localObject.setMayorCalidad(externObject.getMayorCalidad());
            localObject.setMayorPais(externObject.getMayorPais());
            localObject.setMayorCinturon(externObject.getMayorCinturon());
            localObject.setMayorCorreo(externObject.getMayorCorreo());
            localObject.setMayorDomicilioCalle(externObject.getMayorDomicilioCalle());
            localObject.setMayorDomicilioNumero(externObject.getMayorDomicilioNumero());
            localObject.setMayorDomicilioOtros(externObject.getMayorDomicilioOtros());
            localObject.setMayorDomicilioLocalidad(externObject.getMayorDomicilioLocalidad());
            localObject.setMayorDomicilioCp(externObject.getMayorDomicilioCp());
            localObject.setMayorLicencia(externObject.isMayorLicencia());
            localObject.setMayorTelefono(externObject.getMayorTelefono());
            localObject.setMayorAutorizaWhatsApp(externObject.isMayorAutorizaWhatsApp());
            localObject.setAutorizadoMenor(externObject.isAutorizadoMenor());
            localObject.setAutorizadoNombre(externObject.getAutorizadoNombre());
            localObject.setAutorizadoApellido1(externObject.getAutorizadoApellido1());
            localObject.setAutorizadoApellido2(externObject.getAutorizadoApellido2());
            localObject.setAutorizadoSexo(externObject.getAutorizadoSexo());
            localObject.setAutorizadoFechaNacimiento(externObject.getAutorizadoFechaNacimiento());
            localObject.setAutorizadoPais(externObject.getAutorizadoPais());
            localObject.setAutorizadoCinturon(externObject.getAutorizadoCinturon());
            localObject.setAutorizadoDni(externObject.getAutorizadoDni());
            localObject.setAutorizadoLicencia(externObject.isAutorizadoLicencia());
            localObject.setDomiciliacionSEPA(externObject.isDomiciliacionSEPA());
            localObject.setTitularCuenta(externObject.getTitularCuenta());
            localObject.setIban(externObject.getIban());
            localObject.setSwift(externObject.getSwift());
            localObject.setNotas(externObject.getNotas());
            localObject.setInscripcionFirmada(externObject.isInscripcionFirmada());
            localObject.setDomiciliacionSEPAFirmada(externObject.isDomiciliacionSEPAFirmada());
            localObject.setExtensionSEPAFirmado(externObject.getExtensionSEPAFirmado());
        }
        return localObject;
    }
}
