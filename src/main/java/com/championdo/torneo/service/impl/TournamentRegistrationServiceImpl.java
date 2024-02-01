package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.TournamentRegistration;
import com.championdo.torneo.mapper.MapperTournamentRegistration;
import com.championdo.torneo.model.*;
import com.championdo.torneo.repository.TournamentRegistrationRepository;
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
public class TournamentRegistrationServiceImpl implements TournamentRegistrationService {

    @Autowired
    private TournamentRegistrationRepository tournamentRegistrationRepository;
    @Autowired
    private MapperTournamentRegistration mapperTournamentRegistration;
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
    public List<TournamentRegistrationModel> findAll() {
        List<TournamentRegistrationModel> tournamentRegistrationModelList = new ArrayList<>();
        for (TournamentRegistration tournamentRegistration : tournamentRegistrationRepository.findAllByOrderByIdDesc()) {
            tournamentRegistrationModelList.add(mapperTournamentRegistration.entity2Model(tournamentRegistration));
        }
        return tournamentRegistrationModelList;
    }

    @Override
    public TournamentRegistrationModel findById(int id) {
        try {
            return mapperTournamentRegistration.entity2Model(tournamentRegistrationRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new TournamentRegistrationModel();
        }
    }

    @Override
    public List<TournamentRegistrationModel> findByAuthorizerIdCard(String authorizerIdCard) {
        List<TournamentRegistrationModel> tournamentRegistrationModelList = new ArrayList<>();
        for (TournamentRegistration tournamentRegistration : tournamentRegistrationRepository.findByAuthorizerIdCardOrderByRegistrationDateDesc(authorizerIdCard)) {
            tournamentRegistrationModelList.add(mapperTournamentRegistration.entity2Model(tournamentRegistration));
        }
        LoggerMapper.methodOut(Level.INFO, "findByDniAutorizador", "tournamentRegistrationModelList.size(): "+ tournamentRegistrationModelList.size(), getClass());
        return tournamentRegistrationModelList;
    }

    @Override
    public List<TournamentRegistrationModel> findByRegisteredIdCard(String registeredIdCard) {
        List<TournamentRegistrationModel> tournamentRegistrationModelList = new ArrayList<>();
        for (TournamentRegistration tournamentRegistration : tournamentRegistrationRepository.findByRegisteredIdCardOrderByRegistrationDateDesc(registeredIdCard)) {
            tournamentRegistrationModelList.add(mapperTournamentRegistration.entity2Model(tournamentRegistration));
        }
        LoggerMapper.methodOut(Level.INFO, "findByDniInscripto", "tournamentRegistrationModelList.size(): "+ tournamentRegistrationModelList.size(), getClass());
        return tournamentRegistrationModelList;
    }

    @Override
    public List<TournamentRegistrationModel> findByIdTournament(int idTournament) {
        List<TournamentRegistrationModel> tournamentRegistrationModelList = new ArrayList<>();
        for (TournamentRegistration tournamentRegistration : tournamentRegistrationRepository.findByIdTournamentOrderByRegistrationDateDesc(idTournament)) {
            tournamentRegistrationModelList.add(mapperTournamentRegistration.entity2Model(tournamentRegistration));
        }
        LoggerMapper.methodOut(Level.INFO, "findByIdTorneo", "tournamentRegistrationModelList.size(): "+ tournamentRegistrationModelList.size(), getClass());
        return tournamentRegistrationModelList;
    }

    @Override
    public TournamentRegistrationModel add(TournamentRegistrationModel tournamentRegistrationModel) {
        TournamentRegistrationModel inscripcion = mapperTournamentRegistration.entity2Model(tournamentRegistrationRepository.save(mapperTournamentRegistration.model2Entity(tournamentRegistrationModel)));
        LoggerMapper.methodOut(Level.INFO, "add", inscripcion, getClass());
        return inscripcion;
    }

    @Override
    public TournamentRegistrationModel addAdult(UserModel userModel, PdfModel pdfModel, int idGym) {
        TournamentRegistrationModel tournamentRegistrationModel = fillInscripcionModel(userModel, null, pdfModel, idGym);
        tournamentRegistrationModel.setRegistrationAdult(true);
        return add(tournamentRegistrationModel);
    }

    @Override
    public TournamentRegistrationModel addYoungOrInclusive(UserAutorizacionModel userAutorizacionModel, PdfModel pdfModel, int idGym) {
        UserModel usuarioAutorizador = userAutorizacionModel.getMayorAutorizador();
        UserModel usuarioInscripto = userAutorizacionModel.getAutorizado();

        if (usuarioInscripto.isInclusivo()) {
            return addInclusivo(usuarioAutorizador, usuarioInscripto, pdfModel, idGym);
        } else if (usuarioInscripto.isMenor()) {
            return addMenor(usuarioAutorizador, usuarioInscripto, pdfModel, idGym);
        }
        return null;
    }

    private TournamentRegistrationModel addMenor(UserModel usuarioAutorizador, UserModel usuarioInscripto, PdfModel pdfModel, int codigoGimnasio) {
        TournamentRegistrationModel tournamentRegistrationModel = fillInscripcionModel(usuarioInscripto, usuarioAutorizador, pdfModel, codigoGimnasio);
        tournamentRegistrationModel.setRegistrationYoung(true);
        return add(tournamentRegistrationModel);
    }

    private TournamentRegistrationModel addInclusivo(UserModel usuarioAutorizador, UserModel usuarioInscripto, PdfModel pdfModel, int codigoGimnasio) {
        TournamentRegistrationModel tournamentRegistrationModel = fillInscripcionModel(usuarioInscripto, usuarioAutorizador, pdfModel, codigoGimnasio);
        tournamentRegistrationModel.setRegistrationInclusive(true);
        return add(tournamentRegistrationModel);
    }

    @Override
    public void update(TournamentRegistrationModel tournamentRegistrationModel) {
        tournamentRegistrationRepository.save(mapperTournamentRegistration.model2Entity(tournamentRegistrationModel));
    }

    @Override
    public void delete(TournamentRegistrationModel tournamentRegistrationModel) {
        try {
            tournamentRegistrationRepository.delete(mapperTournamentRegistration.model2Entity(tournamentRegistrationModel));
            LoggerMapper.methodOut(Level.INFO, "delete", tournamentRegistrationModel, getClass());
        } catch (EntityNotFoundException e) {
            LoggerMapper.log(Level.ERROR, "delete", tournamentRegistrationModel, this.getClass());
        }
    }

    @Override
    public void deleteByIdCard(String idCard) {
        LoggerMapper.methodIn(Level.INFO, "deleteByDni", "DNI: " + idCard, getClass());
        List<TournamentRegistrationModel> tournamentRegistrationModelList = findByAuthorizerIdCard(idCard);
        tournamentRegistrationModelList.addAll(findByRegisteredIdCard(idCard));
        for(TournamentRegistrationModel inscripcion : tournamentRegistrationModelList) {
            delete(inscripcion);
        }
        LoggerMapper.methodOut(Level.INFO, "deleteByDni", "", getClass());
    }

    @Override
    public void deleteAll() {
        tournamentRegistrationRepository.deleteAll();
    }

    @Override
    public UtilModel getDeleteEnable(int idGym) {
        return utilService.findByClave(Constantes.HABILITAR_BORRAR_INSCRIPCIONES_CAMPEONATO, idGym);
    }

    private TournamentRegistrationModel fillInscripcionModel(UserModel usuarioInscripto, UserModel usuarioAutorizador, PdfModel pdfModel, int codigoGimnasio) {
        TournamentRegistrationModel tournamentRegistrationModel = new TournamentRegistrationModel();
        CategoriaModel categoriaModel = categoriaService.calcularCategoria(usuarioInscripto, codigoGimnasio);

        tournamentRegistrationModel.setRegistrationDate(new Date());
        tournamentRegistrationModel.setCategory(categoriaModel.getNombre());
        tournamentRegistrationModel.setIdGym(codigoGimnasio);

        tournamentRegistrationModel.setRegisteredName(usuarioInscripto.getName());
        tournamentRegistrationModel.setRegistered1Lastname(usuarioInscripto.getLastname());
        tournamentRegistrationModel.setRegistered2Lastname(usuarioInscripto.getSecondLastname());
        tournamentRegistrationModel.setRegisteredIdCard(usuarioInscripto.getUsername());
        tournamentRegistrationModel.setRegisteredBirthdate(usuarioInscripto.getFechaNacimiento());
        tournamentRegistrationModel.setRegisteredSex(usuarioInscripto.getSexo());
        tournamentRegistrationModel.setRegisteredAddressStreet(usuarioInscripto.getDomicilioCalle());
        tournamentRegistrationModel.setRegisteredAddressNumber(usuarioInscripto.getDomicilioNumero());
        tournamentRegistrationModel.setRegisteredAddressOther(usuarioInscripto.getDomicilioOtros());
        tournamentRegistrationModel.setRegisteredAddressCity(usuarioInscripto.getDomicilioLocalidad());
        tournamentRegistrationModel.setRegisteredAddressPostcode(usuarioInscripto.getDomicilioCp());
        tournamentRegistrationModel.setGym(pdfModel.getGimnasio());
        tournamentRegistrationModel.setCountry(paisService.findById(usuarioInscripto.getPais().getId()).getNombre());
        tournamentRegistrationModel.setBelt(cinturonService.findById(usuarioInscripto.getCinturon().getId()).getColor());
        tournamentRegistrationModel.setPoomsae(categoriaModel.getPoomsae().getNombre());

        if (usuarioAutorizador != null) {
            tournamentRegistrationModel.setAuthorizerName(usuarioAutorizador.getName());
            tournamentRegistrationModel.setAuthorizer1Lastname(usuarioAutorizador.getLastname());
            tournamentRegistrationModel.setAuthorizer2Lastname(usuarioAutorizador.getSecondLastname());
            tournamentRegistrationModel.setAuthorizerIdCard(usuarioAutorizador.getUsername());
            if (StringUtils.isNullOrEmpty(usuarioAutorizador.getCalidad().getOtro())) {
                tournamentRegistrationModel.setRelationship(calidadService.findById(usuarioAutorizador.getCalidad().getId()).getNombre());
            } else {
                tournamentRegistrationModel.setRelationship(usuarioAutorizador.getCalidad().getOtro());
            }
            tournamentRegistrationModel.setAuthorizerAddressStreet(usuarioAutorizador.getDomicilioCalle());
            tournamentRegistrationModel.setAuthorizerAddressNumber(usuarioAutorizador.getDomicilioNumero());
            tournamentRegistrationModel.setAuthorizerAddressOther(usuarioAutorizador.getDomicilioOtros());
            tournamentRegistrationModel.setAuthorizerAddressCity(usuarioAutorizador.getDomicilioLocalidad());
            tournamentRegistrationModel.setAuthorizerAddressPostcode(usuarioAutorizador.getDomicilioCp());
        }

        tournamentRegistrationModel.setIdTournament(usuarioInscripto.getIdTorneo());
        tournamentRegistrationModel.setTournamentDate(pdfModel.getFechaCampeonato());
        tournamentRegistrationModel.setTournamentName(pdfModel.getNombreCampeonato());
        tournamentRegistrationModel.setTournamentAddress(pdfModel.getDireccionCampeonato());

        return tournamentRegistrationModel;
    }
}
