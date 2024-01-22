package com.championdo.torneo.service.impl;

import com.championdo.torneo.model.*;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.Utils;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Service()
public class FormularioServiceImpl implements FormularioService {

    @Autowired
    private CalidadService calidadService;

    @Autowired
    private CinturonService cinturonService;

    @Autowired
    private GimnasioService gimnasioService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private TorneoService torneoService;

    @Autowired
    private TorneoGimnasioService torneoGimnasioService;

    @Autowired
    private UtilService utilService;

    @Override
    public UserAutorizacionModel formularioInscPropiaGimnasio(UserModel userModel) {
        UserAutorizacionModel userAutorizacionModel = new UserAutorizacionModel();
        userAutorizacionModel.setMayorAutorizador(userModel);
        userAutorizacionModel.setCuentaBancaria(new CuentaBancariaModel());
        return userAutorizacionModel;
    }

    @Override
    public UserAutorizacionModel formularioInscMenorOInclusivo(UserModel userModel, boolean menorOInclusivo) {
        UserAutorizacionModel userAutorizacionModel = new UserAutorizacionModel();
        userAutorizacionModel.setMayorAutorizador(userModel);
        UserModel autorizado = new UserModel();
        if (menorOInclusivo) {
            autorizado.setMenor(true);
        } else {
            autorizado.setInclusivo(true);
        }
        autorizado.setUsernameACargo(userAutorizacionModel.getMayorAutorizador().getUsername());
        userAutorizacionModel.setAutorizado(autorizado);
        userAutorizacionModel.setCuentaBancaria(new CuentaBancariaModel());
        return userAutorizacionModel;
    }

    @Override
    public PdfModel getPdfModelTorneo(UserAutorizacionModel userAutorizacionModel) {
        PdfModel pdfModel = new PdfModel();
        if (userAutorizacionModel.getAutorizado() == null) { ///inscripción de mayor
            pdfModel.setMayorEdad(true);
            UserModel userModel = userAutorizacionModel.getMayorAutorizador();

            rellenoAutorizador(userModel, pdfModel);
            rellenoCompetidor(userModel, pdfModel);
        } else { //inscripción de menor
            pdfModel.setMayorEdad(false);
            pdfModel.setInclusivo(userAutorizacionModel.getAutorizado().isInclusivo());
            UserModel mayor = userAutorizacionModel.getMayorAutorizador();
            UserModel menor = userAutorizacionModel.getAutorizado();
            menor.setIdTorneo(mayor.getIdTorneo());
            menor.setIdTorneoGimnasio(mayor.getIdTorneoGimnasio());

            rellenoAutorizador(mayor, pdfModel);
            rellenoCompetidor(menor, pdfModel);
            rellenoMenor(menor, pdfModel);
        }
        TorneoModel torneoModel = torneoService.findById(userAutorizacionModel.getMayorAutorizador().getIdTorneo());
        pdfModel.setNombreCampeonato(torneoModel.getNombre());
        pdfModel.setFechaCampeonato(Utils.date2String(torneoModel.getFechaTorneo()));
        pdfModel.setDireccionCampeonato(torneoModel.getDireccion());

        return pdfModel;
    }

    @Override
    public void fillObjects(UserModel userModel) {
        if (userModel != null) {
            if (userModel.getGimnasio() != null && userModel.getGimnasio().getId() != 0) { //TODO DAMIAN ver si se usa en algún lado
                userModel.setGimnasio(gimnasioService.findById(userModel.getGimnasio().getId()));
            }
            if (userModel.getPais() != null && userModel.getPais().getId() != 0) {
                userModel.setPais(paisService.findById(userModel.getPais().getId()));
            }
            if (userModel.getCinturon() != null && userModel.getCinturon().getId() != 0) { //TODO DAMIAN ver si se usa en algún lado
                userModel.setCinturon(cinturonService.findById(userModel.getCinturon().getId()));
            }
            if (userModel.getCalidad() != null && userModel.getCalidad().getId() != 0 && userModel.getCalidad().getOtro().isEmpty()) {
                userModel.setCalidad(calidadService.findById(userModel.getCalidad().getId()));
            }
        }
    }

    @Override
    public void cargarDesplegables(ModelAndView modelAndView, int codigoGimnasio) {
        modelAndView.addObject("listaSexo", Arrays.asList("Masculino","Femenino"));
        modelAndView.addObject("listaMenorConKicho", Arrays.asList("Poomsae","Kicho"));
        modelAndView.addObject("listaPaises", paisService.findAll());
        modelAndView.addObject("listaGimnasios", gimnasioService.findAll());
        modelAndView.addObject("listaCinturones", cinturonService.findAll(codigoGimnasio));
        modelAndView.addObject("listaCalidad", calidadService.findAll());
        modelAndView.addObject("listaSiNo", Utils.cargarListaSiNo());
    }

    @Override
    public void cargarDesplegablesBasicos(ModelAndView modelAndView) {
        modelAndView.addObject("listaSexo", Arrays.asList("Masculino","Femenino"));
        modelAndView.addObject("listaMenorConKicho", Arrays.asList("Poomsae","Kicho"));
        modelAndView.addObject("listaPaises", paisService.findAll());
        modelAndView.addObject("listaGimnasios", gimnasioService.findAll());
        modelAndView.addObject("listaCalidad", calidadService.findAll());
        modelAndView.addObject("listaSiNo", Utils.cargarListaSiNo());
    }

    private void rellenoAutorizador (UserModel userModel, PdfModel pdfModel) {
        pdfModel.setNombre(userModel.getName() + " " + userModel.getLastname() + (userModel.getSecondLastname() != null ? " " + userModel.getSecondLastname() : ""));
        pdfModel.setDni(userModel.getUsername());
        pdfModel.setTelefono(userModel.getTelefono());
        pdfModel.setCorreo(userModel.getCorreo());
        pdfModel.setFechaNacimiento(Utils.date2String(userModel.getFechaNacimiento()));
        if (!StringUtils.isNullOrEmpty(userModel.getDomicilioCalle())) {
            pdfModel.setDomicilio(userModel.getDomicilioCalle() + " " + userModel.getDomicilioNumero() + " " + userModel.getDomicilioOtros());
            pdfModel.setLocalidad(userModel.getDomicilioLocalidad() + " (" + userModel.getDomicilioCp() + ")" + (userModel.getPais() != null ? " - " + userModel.getPais().getNombre() : ""));
        }
        if (userModel.getCalidad() != null) {
            if (!StringUtils.isNullOrEmpty(userModel.getCalidad().getOtro())) {
                pdfModel.setCalidadDe(userModel.getCalidad().getOtro());
            } else {
                CalidadModel calidad = calidadService.findById(userModel.getCalidad().getId());
                pdfModel.setCalidadDe(calidad.getNombre());
            }
        }
    }

    private void rellenoCompetidor (UserModel userModel, PdfModel pdfModel) {

        if(userModel.getFechaNacimiento() != null) {
            if (pdfModel.isMayorEdad()) {
                pdfModel.setFechaNacimiento(Utils.date2String(userModel.getFechaNacimiento()));
            } else {
                pdfModel.setFechaNacimientoMenor(Utils.date2String(userModel.getFechaNacimiento()));
            }
        }
        if(userModel.getIdTorneoGimnasio() != 0) {
            pdfModel.setGimnasio(torneoGimnasioService.findById(userModel.getIdTorneoGimnasio()).getNombreGimnasio());
        }
        if(userModel.getCinturon() != null) {
            pdfModel.setCinturonActual(userModel.getCinturon().getColor());
            if (userModel.getCinturon().getColor().equalsIgnoreCase(Constantes.BLANCO)) {
                pdfModel.setCinturonBlanco(true);
            }
        }
    }

    private void rellenoMenor(UserModel userModel, PdfModel pdfModel) {
        pdfModel.setNombreMenor(userModel.getName() + " " + userModel.getLastname() + (userModel.getSecondLastname() != null ? " " + userModel.getSecondLastname() : ""));
        pdfModel.setDniMenor(userModel.getUsername());
        pdfModel.setFechaNacimientoMenor(Utils.date2String(userModel.getFechaNacimiento()));
    }
}
