package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Inscripcion;
import com.championdo.torneo.mapper.MapperInscripcion;
import com.championdo.torneo.model.*;
import com.championdo.torneo.repository.InscripcionRepository;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.mysql.cj.util.StringUtils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service()
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private MapperInscripcion mapperInscripcion;
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
    @Autowired
    private UtilService utilService;

    @Override
    public List<InscripcionModel> findAll() {
        List<InscripcionModel> inscripcionModelList = new ArrayList<>();
        for (Inscripcion inscripcion: inscripcionRepository.findAllByOrderByIdDesc()) {
            inscripcionModelList.add(mapperInscripcion.entity2Model(inscripcion));
        }
        return inscripcionModelList;
    }

    @Override
    public InscripcionModel findById(int id) {
        try {
            return mapperInscripcion.entity2Model(inscripcionRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new InscripcionModel();
        }
    }

    @Override
    public List<InscripcionModel> findByDniAutorizador(String dniAutorizador) {
        List<Inscripcion> inscripcionList = inscripcionRepository.findByDniAutorizador(dniAutorizador);
        List<InscripcionModel> inscripcionModelList = new ArrayList<>();
        if (inscripcionList != null) {
            for (Inscripcion inscripcion : inscripcionList) {
                inscripcionModelList.add(mapperInscripcion.entity2Model(inscripcion));
            }
        }
        LoggerMapper.log(Level.INFO, "findByDniAutorizador", inscripcionList, getClass());
        return inscripcionModelList;
    }

    @Override
    public InscripcionModel findByDniInscripto(String dniInscripto) {
        InscripcionModel inscripcion = mapperInscripcion.entity2Model(inscripcionRepository.findByDniInscripto(dniInscripto));
        LoggerMapper.log(Level.INFO, "findByDniInscripto", inscripcion, getClass());
        return inscripcion;
    }

    @Override
    public InscripcionModel add(InscripcionModel inscripcionModel) {
        InscripcionModel inscripcion = mapperInscripcion.entity2Model(inscripcionRepository.save(mapperInscripcion.model2Entity(inscripcionModel)));
        LoggerMapper.log(Level.INFO, "add", inscripcion, getClass());
        return inscripcion;
    }

    @Override
    public InscripcionModel addPropia(UserModel userModel) {
        InscripcionModel inscripcionModel = fillInscripcionModel(userModel, null);
        inscripcionModel.setInscripcionPropia(true);
        return add(inscripcionModel);
    }

    @Override
    public InscripcionModel addMenorOInclusivo(UserAutorizacionModel userAutorizacionModel) {
        UserModel usuarioAutorizador = userAutorizacionModel.getMayorAutorizador();
        UserModel usuarioInscripto = userAutorizacionModel.getAutorizado();

        if (usuarioInscripto.isInclusivo()) {
            return addInclusivo(usuarioAutorizador, usuarioInscripto);
        } else if (usuarioInscripto.isMenor()) {
            return addMenor(usuarioAutorizador, usuarioInscripto);
        }
        return null;
    }

    private InscripcionModel addMenor(UserModel usuarioAutorizador, UserModel usuarioInscripto) {
        InscripcionModel inscripcionModel = fillInscripcionModel(usuarioInscripto, usuarioAutorizador);
        inscripcionModel.setInscripcionMenor(true);
        return add(inscripcionModel);
    }

    private InscripcionModel addInclusivo(UserModel usuarioAutorizador, UserModel usuarioInscripto) {
        InscripcionModel inscripcionModel = fillInscripcionModel(usuarioInscripto, usuarioAutorizador);
        inscripcionModel.setInscripcionInclusiva(true);
        return add(inscripcionModel);
    }

    @Override
    public void update(InscripcionModel inscripcionModel) {
        inscripcionRepository.save(mapperInscripcion.model2Entity(inscripcionModel));
    }

    @Override
    public void delete(int idInscripcion) {
        try {
            Inscripcion inscripcion = inscripcionRepository.getById(idInscripcion);
            inscripcionRepository.delete(inscripcion);
            LoggerMapper.methodOut(Level.INFO, "delete", inscripcion, getClass());
        } catch (EntityNotFoundException e) {
            LoggerMapper.log(Level.ERROR, "delete", "id " + idInscripcion + " no encontrado", this.getClass());
        }
    }

    @Override
    public void deleteAll() {
        inscripcionRepository.deleteAll();
    }

    public UtilModel getDeleteEnable() {
        return utilService.findByClave(Constantes.HABILITAR_BORRAR_INSCRIPCIONES);
    }

    @Override
    public void changeValueDeleteEnable() {
        UtilModel utilModel = getDeleteEnable();
        boolean deleteEnable = Boolean.FALSE;
        if (!StringUtils.isNullOrEmpty(utilModel.getValor())) {
            deleteEnable = Boolean.parseBoolean(utilModel.getValor());
        }
        deleteEnable = !deleteEnable;
        utilModel.setValor(Boolean.toString(deleteEnable));
        utilService.update(utilModel);
    }

    private InscripcionModel fillInscripcionModel(UserModel usuarioInscripto, UserModel usuarioAutorizador) {
        InscripcionModel inscripcionModel = new InscripcionModel();
        CategoriaModel categoriaModel = categoriaService.calcularCategoria(usuarioInscripto);

        inscripcionModel.setFechaInscripcion(new Date());
        inscripcionModel.setFechaCampeonato(utilService.findByClave(Constantes.FECHA_CAMPEONATO).getValor());
        inscripcionModel.setNombreCampeonato(utilService.findByClave(Constantes.NOMBRE_CAMPEONATO).getValor());
        inscripcionModel.setDireccionCampeonato(utilService.findByClave(Constantes.DIRECCION_CAMPEONATO).getValor());
        inscripcionModel.setCategoria(categoriaModel.getNombre());

        inscripcionModel.setNombreInscripto(usuarioInscripto.getName());
        inscripcionModel.setApellido1Inscripto(usuarioInscripto.getLastname());
        inscripcionModel.setApellido2Inscripto(usuarioInscripto.getSecondLastname());
        inscripcionModel.setDniInscripto(usuarioInscripto.getUsername());
        inscripcionModel.setFechaNacimientoInscripto(usuarioInscripto.getFechaNacimiento());
        inscripcionModel.setSexoInscripto(usuarioInscripto.getSexo());
        inscripcionModel.setDomicilioCalleInscripto(usuarioInscripto.getDomicilioCalle());
        inscripcionModel.setDomicilioNumeroInscripto(usuarioInscripto.getDomicilioNumero());
        inscripcionModel.setDomicilioOtrosInscripto(usuarioInscripto.getDomicilioOtros());
        inscripcionModel.setDomicilioLocalidadInscripto(usuarioInscripto.getDomicilioLocalidad());
        inscripcionModel.setDomicilioCpInscripto(usuarioInscripto.getDomicilioCp());
        inscripcionModel.setGimnasio(gimnasioService.findById(usuarioInscripto.getGimnasio().getId()).getNombre());
        inscripcionModel.setPais(paisService.findById(usuarioInscripto.getPais().getId()).getNombre());
        inscripcionModel.setCinturon(cinturonService.findById(usuarioInscripto.getCinturon().getId()).getColor());
        inscripcionModel.setPoomsae(categoriaModel.getPoomsae().getNombre());

        if (usuarioAutorizador != null) {
            inscripcionModel.setNombreAutorizador(usuarioAutorizador.getName());
            inscripcionModel.setApellido1Autorizador(usuarioAutorizador.getLastname());
            inscripcionModel.setApellido2Autorizador(usuarioAutorizador.getSecondLastname());
            inscripcionModel.setDniAutorizador(usuarioAutorizador.getUsername());
            if (StringUtils.isNullOrEmpty(usuarioAutorizador.getCalidad().getOtro())) {
                inscripcionModel.setCalidad(calidadService.findById(usuarioAutorizador.getCalidad().getId()).getNombre());
            } else {
                inscripcionModel.setCalidad(usuarioAutorizador.getCalidad().getOtro());
            }
            inscripcionModel.setDomicilioCalleAutorizador(usuarioAutorizador.getDomicilioCalle());
            inscripcionModel.setDomicilioNumeroAutorizador(usuarioAutorizador.getDomicilioNumero());
            inscripcionModel.setDomicilioOtrosAutorizador(usuarioAutorizador.getDomicilioOtros());
            inscripcionModel.setDomicilioLocalidadAutorizador(usuarioAutorizador.getDomicilioLocalidad());
            inscripcionModel.setDomicilioCpAutorizador(usuarioAutorizador.getDomicilioCp());
        }

        return inscripcionModel;
    }
}
