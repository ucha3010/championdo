package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.InscripcionTaekwondo;
import com.championdo.torneo.mapper.MapperInscripcionTaekwondo;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.model.UtilModel;
import com.championdo.torneo.repository.InscripcionTaekwondoRepository;
import com.championdo.torneo.service.InscripcionTaekwondoService;
import com.championdo.torneo.service.UtilService;
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
public class InscripcionTaekwondoServiceImpl implements InscripcionTaekwondoService {

    @Autowired
    private InscripcionTaekwondoRepository inscripcionTaekwondoRepository;
    @Autowired
    private MapperInscripcionTaekwondo mapperInscripcionTaekwondo;
    @Autowired
    private UtilService utilService;

    @Override
    public List<InscripcionTaekwondoModel> findAll() {
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = new ArrayList<>();
        for (InscripcionTaekwondo inscripcion : inscripcionTaekwondoRepository.findAllByOrderByIdDesc()) {
            inscripcionTaekwondoModelList.add(mapperInscripcionTaekwondo.entity2Model(inscripcion));
        }
        return inscripcionTaekwondoModelList;
    }

    @Override
    public InscripcionTaekwondoModel findById(int id) {
        try {
            return mapperInscripcionTaekwondo.entity2Model(inscripcionTaekwondoRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new InscripcionTaekwondoModel();
        }
    }

    @Override
    public List<InscripcionTaekwondoModel> findByMayorDni(String mayorDni) {
        List<InscripcionTaekwondo> inscripcionTaekwondoList = inscripcionTaekwondoRepository.findByMayorDni(mayorDni);
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = new ArrayList<>();
        if (inscripcionTaekwondoList != null) {
            for (InscripcionTaekwondo inscripcion : inscripcionTaekwondoList) {
                inscripcionTaekwondoModelList.add(mapperInscripcionTaekwondo.entity2Model(inscripcion));
            }
        }
        LoggerMapper.methodOut(Level.INFO, "findByMayorDni", inscripcionTaekwondoList, getClass());
        return inscripcionTaekwondoModelList;
    }

    @Override
    public InscripcionTaekwondoModel add(InscripcionTaekwondoModel inscripcionTaekwondoModel) {
        inscripcionTaekwondoModel.setFechaInscripcion(new Date());
        InscripcionTaekwondoModel inscripcion = mapperInscripcionTaekwondo.entity2Model(inscripcionTaekwondoRepository
                .save(mapperInscripcionTaekwondo.model2Entity(inscripcionTaekwondoModel)));
        LoggerMapper.methodOut(Level.INFO, "add", inscripcion, getClass());
        return inscripcion;
    }

    @Override
    public InscripcionTaekwondoModel add(UserAutorizacionModel userAutorizacionModel) {
        InscripcionTaekwondoModel inscripcionTaekwondoModel = add(fillInscripcionTaekwondoModel(userAutorizacionModel));
        LoggerMapper.methodOut(Level.INFO, "add", inscripcionTaekwondoModel, getClass());
        return inscripcionTaekwondoModel;
    }

    @Override
    public void update(InscripcionTaekwondoModel inscripcionTaekwondoModel) {
        inscripcionTaekwondoRepository.save(mapperInscripcionTaekwondo.model2Entity(inscripcionTaekwondoModel));
    }

    @Override
    public void delete(int idInscripcionTaekwondo) {
        try {
            InscripcionTaekwondo inscripcion = inscripcionTaekwondoRepository.getById(idInscripcionTaekwondo);
            inscripcionTaekwondoRepository.delete(inscripcion);
            LoggerMapper.methodOut(Level.INFO, "delete", inscripcion, getClass());
        } catch (EntityNotFoundException e) {
            LoggerMapper.log(Level.ERROR, "delete", "id " + idInscripcionTaekwondo + " no encontrado", this.getClass());
        }
    }

    @Override
    public void deleteAll() {
        inscripcionTaekwondoRepository.deleteAll();
    }

    public UtilModel getDeleteEnable() {
        return utilService.findByClave(Constantes.HABILITAR_BORRAR_INSCRIPCIONES_TAEKWONDO);
    }

    @Override
    public boolean changeValueDeleteEnable() {
        UtilModel utilModel = getDeleteEnable();
        boolean deleteEnable = Boolean.FALSE;
        if (!StringUtils.isNullOrEmpty(utilModel.getValor())) {
            deleteEnable = Boolean.parseBoolean(utilModel.getValor());
        }
        deleteEnable = !deleteEnable;
        utilModel.setValor(Boolean.toString(deleteEnable));
        utilService.update(utilModel);
        return deleteEnable;
    }

    private InscripcionTaekwondoModel fillInscripcionTaekwondoModel(UserAutorizacionModel userAutorizacionModel) {

        InscripcionTaekwondoModel inscripcionTaekwondoModel = new InscripcionTaekwondoModel();

        UserModel mayorAutorizador = userAutorizacionModel.getMayorAutorizador();
        UserModel autorizado = userAutorizacionModel.getAutorizado();

        inscripcionTaekwondoModel.setMayorDni(mayorAutorizador.getUsername());
        inscripcionTaekwondoModel.setMayorNombre(mayorAutorizador.getName());
        inscripcionTaekwondoModel.setMayorApellido1(mayorAutorizador.getLastname());
        inscripcionTaekwondoModel.setMayorApellido2(mayorAutorizador.getSecondLastname());
        inscripcionTaekwondoModel.setMayorSexo(mayorAutorizador.getSexo());
        inscripcionTaekwondoModel.setMayorFechaNacimiento(mayorAutorizador.getFechaNacimiento());
        if (mayorAutorizador.getCalidad() != null) {
            if (StringUtils.isNullOrEmpty(mayorAutorizador.getCalidad().getOtro())) {
                inscripcionTaekwondoModel.setMayorCalidad(mayorAutorizador.getCalidad().getNombre());
            } else {
                inscripcionTaekwondoModel.setMayorCalidad(mayorAutorizador.getCalidad().getOtro());
            }
        }
        if (mayorAutorizador.getPais() != null) {
            inscripcionTaekwondoModel.setMayorPais(mayorAutorizador.getPais().getNombre());
        }
        if (mayorAutorizador.getCinturon() != null) {
            inscripcionTaekwondoModel.setMayorCinturon(mayorAutorizador.getCinturon().getColor());
        }
        inscripcionTaekwondoModel.setMayorCorreo(mayorAutorizador.getCorreo());
        inscripcionTaekwondoModel.setMayorDomicilioCalle(mayorAutorizador.getDomicilioCalle());
        inscripcionTaekwondoModel.setMayorDomicilioNumero(mayorAutorizador.getDomicilioNumero());
        inscripcionTaekwondoModel.setMayorDomicilioOtros(mayorAutorizador.getDomicilioOtros());
        inscripcionTaekwondoModel.setMayorDomicilioLocalidad(mayorAutorizador.getDomicilioLocalidad());
        inscripcionTaekwondoModel.setMayorDomicilioCp(mayorAutorizador.getDomicilioCp());
        inscripcionTaekwondoModel.setMayorLicencia(mayorAutorizador.isLicencia());
        inscripcionTaekwondoModel.setMayorTelefono(mayorAutorizador.getTelefono());
        inscripcionTaekwondoModel.setMayorAutorizaWhatsApp(mayorAutorizador.isAutorizaWhatsApp());

        if (autorizado != null) {
            inscripcionTaekwondoModel.setAutorizadoNombre(autorizado.getName());
            inscripcionTaekwondoModel.setAutorizadoApellido1(autorizado.getLastname());
            inscripcionTaekwondoModel.setAutorizadoApellido2(autorizado.getSecondLastname());
            inscripcionTaekwondoModel.setAutorizadoSexo(autorizado.getSexo());
            inscripcionTaekwondoModel.setAutorizadoFechaNacimiento(autorizado.getFechaNacimiento());
            if (autorizado.getPais() != null) {
                inscripcionTaekwondoModel.setAutorizadoPais(autorizado.getPais().getNombre());
            }
            if (autorizado.getCinturon() != null) {
                inscripcionTaekwondoModel.setAutorizadoCinturon(autorizado.getCinturon().getColor());
            }
            inscripcionTaekwondoModel.setAutorizadoDni(autorizado.getDniMenor());
            inscripcionTaekwondoModel.setAutorizadoLicencia(autorizado.isLicencia());
        }

        if (userAutorizacionModel.getCuentaBancaria() != null) {
            inscripcionTaekwondoModel.setTitularCuenta(userAutorizacionModel.getCuentaBancaria().getTitular());
            inscripcionTaekwondoModel.setIban(userAutorizacionModel.getCuentaBancaria().getIban());
            inscripcionTaekwondoModel.setSwift(userAutorizacionModel.getCuentaBancaria().getSwift());
        }

        return inscripcionTaekwondoModel;
    }
}
