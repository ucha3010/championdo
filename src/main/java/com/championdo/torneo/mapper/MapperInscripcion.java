package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Inscripcion;
import com.championdo.torneo.model.InscripcionModel;
import org.springframework.stereotype.Component;

@Component
public class MapperInscripcion {

    public InscripcionModel entity2Model(Inscripcion externObject) {

        InscripcionModel localObject = new InscripcionModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setInscripcionPropia(externObject.isInscripcionPropia());
            localObject.setInscripcionMenor(externObject.isInscripcionMenor());
            localObject.setInscripcionInclusiva(externObject.isInscripcionInclusiva());
            localObject.setFechaInscripcion(externObject.getFechaInscripcion());
            localObject.setFechaCampeonato(externObject.getFechaCampeonato());
            localObject.setNombreCampeonato(externObject.getNombreCampeonato());
            localObject.setDireccionCampeonato(externObject.getDireccionCampeonato());
            localObject.setCategoria(externObject.getCategoria());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
            localObject.setIdTorneo(externObject.getIdTorneo());

            localObject.setNombreInscripto(externObject.getNombreInscripto());
            localObject.setApellido1Inscripto(externObject.getApellido1Inscripto());
            localObject.setApellido2Inscripto(externObject.getApellido2Inscripto());
            localObject.setDniInscripto(externObject.getDniInscripto());
            localObject.setFechaNacimientoInscripto(externObject.getFechaNacimientoInscripto());
            localObject.setSexoInscripto(externObject.getSexoInscripto());
            localObject.setDomicilioCalleInscripto(externObject.getDomicilioCalleInscripto());
            localObject.setDomicilioNumeroInscripto(externObject.getDomicilioNumeroInscripto());
            localObject.setDomicilioOtrosInscripto(externObject.getDomicilioOtrosInscripto());
            localObject.setDomicilioLocalidadInscripto(externObject.getDomicilioLocalidadInscripto());
            localObject.setDomicilioCpInscripto(externObject.getDomicilioCpInscripto());
            localObject.setGimnasio(externObject.getGimnasio());
            localObject.setPais(externObject.getPais());
            localObject.setCinturon(externObject.getCinturon());
            localObject.setPoomsae(externObject.getPoomsae());

            localObject.setNombreAutorizador(externObject.getNombreAutorizador());
            localObject.setApellido1Autorizador(externObject.getApellido1Autorizador());
            localObject.setApellido2Autorizador(externObject.getApellido2Autorizador());
            localObject.setDniAutorizador(externObject.getDniAutorizador());
            localObject.setCalidad(externObject.getCalidad());
            localObject.setDomicilioCalleAutorizador(externObject.getDomicilioCalleAutorizador());
            localObject.setDomicilioNumeroAutorizador(externObject.getDomicilioNumeroAutorizador());
            localObject.setDomicilioOtrosAutorizador(externObject.getDomicilioOtrosAutorizador());
            localObject.setDomicilioLocalidadAutorizador(externObject.getDomicilioLocalidadAutorizador());
            localObject.setDomicilioCpAutorizador(externObject.getDomicilioCpAutorizador());

            localObject.setPagoRealizado(externObject.isPagoRealizado());
            localObject.setFechaPago(externObject.getFechaPago());
            localObject.setNotas(externObject.getNotas());
        }
        return localObject;
    }

    public Inscripcion model2Entity(InscripcionModel externObject) {

        Inscripcion localObject = new Inscripcion();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setInscripcionPropia(externObject.isInscripcionPropia());
            localObject.setInscripcionMenor(externObject.isInscripcionMenor());
            localObject.setInscripcionInclusiva(externObject.isInscripcionInclusiva());
            localObject.setFechaInscripcion(externObject.getFechaInscripcion());
            localObject.setFechaCampeonato(externObject.getFechaCampeonato());
            localObject.setNombreCampeonato(externObject.getNombreCampeonato());
            localObject.setDireccionCampeonato(externObject.getDireccionCampeonato());
            localObject.setCategoria(externObject.getCategoria());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
            localObject.setIdTorneo(externObject.getIdTorneo());

            localObject.setNombreInscripto(externObject.getNombreInscripto());
            localObject.setApellido1Inscripto(externObject.getApellido1Inscripto());
            localObject.setApellido2Inscripto(externObject.getApellido2Inscripto());
            localObject.setDniInscripto(externObject.getDniInscripto());
            localObject.setFechaNacimientoInscripto(externObject.getFechaNacimientoInscripto());
            localObject.setSexoInscripto(externObject.getSexoInscripto());
            localObject.setDomicilioCalleInscripto(externObject.getDomicilioCalleInscripto());
            localObject.setDomicilioNumeroInscripto(externObject.getDomicilioNumeroInscripto());
            localObject.setDomicilioOtrosInscripto(externObject.getDomicilioOtrosInscripto());
            localObject.setDomicilioLocalidadInscripto(externObject.getDomicilioLocalidadInscripto());
            localObject.setDomicilioCpInscripto(externObject.getDomicilioCpInscripto());
            localObject.setGimnasio(externObject.getGimnasio());
            localObject.setPais(externObject.getPais());
            localObject.setCinturon(externObject.getCinturon());
            localObject.setPoomsae(externObject.getPoomsae());

            localObject.setNombreAutorizador(externObject.getNombreAutorizador());
            localObject.setApellido1Autorizador(externObject.getApellido1Autorizador());
            localObject.setApellido2Autorizador(externObject.getApellido2Autorizador());
            localObject.setDniAutorizador(externObject.getDniAutorizador());
            localObject.setCalidad(externObject.getCalidad());
            localObject.setDomicilioCalleAutorizador(externObject.getDomicilioCalleAutorizador());
            localObject.setDomicilioNumeroAutorizador(externObject.getDomicilioNumeroAutorizador());
            localObject.setDomicilioOtrosAutorizador(externObject.getDomicilioOtrosAutorizador());
            localObject.setDomicilioLocalidadAutorizador(externObject.getDomicilioLocalidadAutorizador());
            localObject.setDomicilioCpAutorizador(externObject.getDomicilioCpAutorizador());

            localObject.setPagoRealizado(externObject.isPagoRealizado());
            localObject.setFechaPago(externObject.getFechaPago());
            localObject.setNotas(externObject.getNotas());
        }
        return localObject;
    }
}
