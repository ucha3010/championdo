package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Inscripcion;
import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperInscripcion {

    @Autowired
    private CalidadService calidadService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CinturonService cinturonService;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private PaisService paisService;

    public InscripcionModel entity2Model(Inscripcion externObject) {

        InscripcionModel localObject = new InscripcionModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setInscripcionPropia(externObject.isInscripcionPropia());
            localObject.setInscripcionMenor(externObject.isInscripcionMenor());
            localObject.setInscripcionInclusiva(externObject.isInscripcionInclusiva());
            localObject.setFechaInscripcion(externObject.getFechaInscripcion());
            localObject.setFechaCampeonato(externObject.getFechaCampeonato());
            localObject.setCategoria(categoriaService.findById(externObject.getIdCategoria()));

            UserModel usuarioInscripto = new UserModel();
            usuarioInscripto.setName(externObject.getNombreInscripto());
            usuarioInscripto.setLastname(externObject.getApellido1Inscripto());
            usuarioInscripto.setSecondLastname(externObject.getApellido2Inscripto());
            usuarioInscripto.setUsername(externObject.getDniInscripto());
            usuarioInscripto.setFechaNacimiento(externObject.getFechaNacimientoInscripto());
            usuarioInscripto.setSexo(externObject.getSexoInscripto());
            usuarioInscripto.setDomicilioCalle(externObject.getDomicilioCalleInscripto());
            usuarioInscripto.setDomicilioNumero(externObject.getDomicilioNumeroInscripto());
            usuarioInscripto.setDomicilioOtros(externObject.getDomicilioOtrosInscripto());
            usuarioInscripto.setDomicilioLocalidad(externObject.getDomicilioLocalidadInscripto());
            usuarioInscripto.setDomicilioCp(externObject.getDomicilioCpInscripto());
            usuarioInscripto.setGimnasio(gimnasioService.findById(externObject.getIdGimnasio()));
            usuarioInscripto.setPais(paisService.findById(externObject.getIdPais()));
            usuarioInscripto.setCinturon(cinturonService.findById(externObject.getIdCinturon()));
            localObject.setUsuarioInscripto(usuarioInscripto);

            if (externObject.isInscripcionMenor() || externObject.isInscripcionInclusiva()) {
                UserModel usuarioAutorizador = new UserModel();
                usuarioAutorizador.setName(externObject.getNombreAutorizador());
                usuarioAutorizador.setLastname(externObject.getApellido1Autorizador());
                usuarioAutorizador.setSecondLastname(externObject.getApellido2Autorizador());
                usuarioAutorizador.setUsername(externObject.getDniAutorizador());
                usuarioAutorizador.setCalidad(calidadService.findById(externObject.getIdCalidad()));
                usuarioAutorizador.setDomicilioCalle(externObject.getDomicilioCalleAutorizador());
                usuarioAutorizador.setDomicilioNumero(externObject.getDomicilioNumeroAutorizador());
                usuarioAutorizador.setDomicilioOtros(externObject.getDomicilioOtrosAutorizador());
                usuarioAutorizador.setDomicilioLocalidad(externObject.getDomicilioLocalidadAutorizador());
                usuarioAutorizador.setDomicilioCp(externObject.getDomicilioCpAutorizador());
                localObject.setUsuarioAutorizador(usuarioAutorizador);
            }

            localObject.setEnvioJustificanteEmail(externObject.isEnvioJustificanteEmail());
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
            localObject.setIdCategoria(externObject.getCategoria().getId());

            if (externObject.getUsuarioInscripto() != null) {
                UserModel usuarioInscripto = externObject.getUsuarioInscripto();
                localObject.setNombreInscripto(usuarioInscripto.getName());
                localObject.setApellido1Inscripto(usuarioInscripto.getLastname());
                localObject.setApellido2Inscripto(usuarioInscripto.getSecondLastname());
                localObject.setDniInscripto(usuarioInscripto.getUsername());
                localObject.setFechaNacimientoInscripto(usuarioInscripto.getFechaNacimiento());
                localObject.setSexoInscripto(usuarioInscripto.getSexo());
                localObject.setDomicilioCalleInscripto(usuarioInscripto.getDomicilioCalle());
                localObject.setDomicilioNumeroInscripto(usuarioInscripto.getDomicilioNumero());
                localObject.setDomicilioOtrosInscripto(usuarioInscripto.getDomicilioOtros());
                localObject.setDomicilioLocalidadInscripto(usuarioInscripto.getDomicilioLocalidad());
                localObject.setDomicilioCpInscripto(usuarioInscripto.getDomicilioCp());
                localObject.setIdGimnasio(usuarioInscripto.getGimnasio().getId());
                localObject.setIdPais(usuarioInscripto.getPais().getId());
                localObject.setIdCinturon(usuarioInscripto.getCinturon().getId());
            }

            if (externObject.getUsuarioAutorizador() != null) {
                UserModel usuarioAutorizador = externObject.getUsuarioAutorizador();
                localObject.setNombreAutorizador(usuarioAutorizador.getName());
                localObject.setApellido1Autorizador(usuarioAutorizador.getLastname());
                localObject.setApellido2Autorizador(usuarioAutorizador.getSecondLastname());
                localObject.setDniAutorizador(usuarioAutorizador.getUsername());
                localObject.setIdCalidad(usuarioAutorizador.getCalidad().getId());
                localObject.setDomicilioCalleAutorizador(usuarioAutorizador.getDomicilioCalle());
                localObject.setDomicilioNumeroAutorizador(usuarioAutorizador.getDomicilioNumero());
                localObject.setDomicilioOtrosAutorizador(usuarioAutorizador.getDomicilioOtros());
                localObject.setDomicilioLocalidadAutorizador(usuarioAutorizador.getDomicilioLocalidad());
                localObject.setDomicilioCpAutorizador(usuarioAutorizador.getDomicilioCp());
            }

            localObject.setEnvioJustificanteEmail(externObject.isEnvioJustificanteEmail());
            localObject.setPagoRealizado(externObject.isPagoRealizado());
            localObject.setFechaPago(externObject.getFechaPago());
            localObject.setNotas(externObject.getNotas());
        }
        return localObject;
    }
}
