package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.InscripcionTaekwondo;
import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.mapper.MapperInscripcionTaekwondo;
import com.championdo.torneo.model.*;
import com.championdo.torneo.repository.InscripcionTaekwondoRepository;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.mysql.cj.util.StringUtils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service()
public class InscripcionTaekwondoServiceImpl implements InscripcionTaekwondoService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private InscripcionTaekwondoRepository inscripcionTaekwondoRepository;
    @Autowired
    private MapperInscripcionTaekwondo mapperInscripcionTaekwondo;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private MandatoService mandatoService;
    @Autowired
    private PdfService pdfService;
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
        List<InscripcionTaekwondo> inscripcionTaekwondoList = inscripcionTaekwondoRepository.findByMayorDniOrderByFechaInscripcionDesc(mayorDni);
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
    public InscripcionTaekwondoModel add(UserAutorizacionModel userAutorizacionModel, int codigoGimnasio) {
        InscripcionTaekwondoModel inscripcionTaekwondoModel = add(fillInscripcionTaekwondoModel(userAutorizacionModel, codigoGimnasio));
        LoggerMapper.methodOut(Level.INFO, "add", inscripcionTaekwondoModel, getClass());
        return inscripcionTaekwondoModel;
    }

    @Override
    public void update(InscripcionTaekwondoModel inscripcionTaekwondoModel) {
        inscripcionTaekwondoRepository.save(mapperInscripcionTaekwondo.model2Entity(inscripcionTaekwondoModel));
    }

    @Override
    public void delete(InscripcionTaekwondoModel inscripcion) {
        try {
            inscripcionTaekwondoRepository.delete(mapperInscripcionTaekwondo.model2Entity(inscripcion));
            LoggerMapper.methodOut(Level.INFO, "delete", inscripcion, getClass());
        } catch (EntityNotFoundException e) {
            LoggerMapper.log(Level.ERROR, "delete", inscripcion, this.getClass());
        }
    }

    @Override
    public void deleteByDni(String dni) {
        LoggerMapper.methodIn(Level.INFO, "deleteByDni", "DNI: " + dni, getClass());
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = findByMayorDni(dni);
        for(InscripcionTaekwondoModel inscripcion : inscripcionTaekwondoModelList) {
            delete(inscripcion);
        }
        LoggerMapper.methodOut(Level.INFO, "deleteByDni", "", getClass());

    }

    @Override
    public void deleteAll() {
        inscripcionTaekwondoRepository.deleteAll();
    }

    @Override
    public UtilModel getDeleteEnable(int codigoGimnasio) {
        return utilService.findByClave(Constantes.HABILITAR_BORRAR_INSCRIPCIONES_TAEKWONDO, codigoGimnasio);
    }

    @Override
    public boolean changeValueDeleteEnable(int codigoGimnasio) {
        UtilModel utilModel = getDeleteEnable(codigoGimnasio);
        boolean deleteEnable = Boolean.FALSE;
        if (!StringUtils.isNullOrEmpty(utilModel.getValor())) {
            deleteEnable = Boolean.parseBoolean(utilModel.getValor());
        }
        deleteEnable = !deleteEnable;
        utilModel.setValor(Boolean.toString(deleteEnable));
        utilService.update(utilModel);
        return deleteEnable;
    }

    @Override
    public void crearEnviarArchivosInscripcionTaekwondo(FirmaCodigoModel firmaCodigoModel) throws SenderException {
        List<File> files = new ArrayList<>();
        InscripcionTaekwondoModel inscripcionTaekwondoModel = findById(firmaCodigoModel.getIdOperacion());
        PdfModel pdfModelGeneral = pdfService.getPdfInscripcionTaekwondo(inscripcionTaekwondoModel);
        if (inscripcionTaekwondoModel.isMayorLicencia() || inscripcionTaekwondoModel.isAutorizadoLicencia()) {
            MandatoModel mandatoModel = mandatoService.fromInscripcionTaekwondoToMandato(inscripcionTaekwondoModel);
            try {
                mandatoModel = mandatoService.add(mandatoModel);
                pdfModelGeneral.setIdInscripcion(mandatoModel.getId());
                File pdfMandato = pdfService.generarPdfMandato(pdfModelGeneral);
                files.add(pdfMandato);
                pdfModelGeneral.setIdInscripcion(inscripcionTaekwondoModel.getId());
            } catch (ValidationException e) {
                LoggerMapper.log(Level.ERROR, "crearEnviarArchivosInscripcionTaekwondo", e.getMessage(), this.getClass());
            }
        }
        if (inscripcionTaekwondoModel.isAutorizadoMenor()) {
            File pdfAutorizacionMenor18 = pdfService.generarPdfAutorizacionMenor18(pdfModelGeneral);
            files.add(pdfAutorizacionMenor18);
        } else {
            File pdfAutorizacionMayor18 = pdfService.generarPdfAutorizacionMayor18(pdfModelGeneral);
            files.add(pdfAutorizacionMayor18);
        }
        if (inscripcionTaekwondoModel.isDomiciliacionSEPA()) {
            File pdfNormativaSEPA = pdfService.generarPdfNormativaSEPA(pdfModelGeneral);
            files.add(pdfNormativaSEPA);
        }
        if (inscripcionTaekwondoModel.isMayorAutorizaWhatsApp()) {
            File pdfAutorizaWhatsApp = pdfService.generarPdfAutorizaWhatsApp(pdfModelGeneral);
            files.add(pdfAutorizaWhatsApp);
        }
        inscripcionTaekwondoModel.setInscripcionFirmada(Boolean.TRUE);
        update(inscripcionTaekwondoModel);
        emailService.sendGymJoining(inscripcionTaekwondoModel, files);
        emailService.confirmAdminGymJoining(inscripcionTaekwondoModel);
    }

    @Override
    public UtilModel getAccountBoxEnable(int codigoGimnasio) {
        return utilService.findByClave(Constantes.HABILITAR_CUENTA_BANCARIA, codigoGimnasio);
    }

    private InscripcionTaekwondoModel fillInscripcionTaekwondoModel(UserAutorizacionModel userAutorizacionModel, int codigoGimnasio) {

        InscripcionTaekwondoModel inscripcionTaekwondoModel = new InscripcionTaekwondoModel();

        UserModel mayorAutorizador = userAutorizacionModel.getMayorAutorizador();
        UserModel autorizado = userAutorizacionModel.getAutorizado();

        inscripcionTaekwondoModel.setMayorDni(mayorAutorizador.getUsername());
        inscripcionTaekwondoModel.setCodigoGimnasio(codigoGimnasio);
        inscripcionTaekwondoModel.setNombreGimnasio(gimnasioService.findById(codigoGimnasio).getNombreGimnasio());
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
            inscripcionTaekwondoModel.setAutorizadoMenor(Boolean.TRUE);
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

        if (userAutorizacionModel.getCuentaBancaria() != null && userAutorizacionModel.getMayorAutorizador().isDomiciliacion()) {
            inscripcionTaekwondoModel.setDomiciliacionSEPA(Boolean.TRUE);
            inscripcionTaekwondoModel.setTitularCuenta(userAutorizacionModel.getCuentaBancaria().getTitular());
            inscripcionTaekwondoModel.setIban(userAutorizacionModel.getCuentaBancaria().getIban());
            inscripcionTaekwondoModel.setSwift(userAutorizacionModel.getCuentaBancaria().getSwift());
        }

        return inscripcionTaekwondoModel;
    }
}
