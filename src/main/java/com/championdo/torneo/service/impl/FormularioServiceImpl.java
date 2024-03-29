package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.mapper.MapperUser;
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
    private MapperUser mapperUser;

    @Autowired
    private CalidadService calidadService;

    @Autowired
    private CinturonService cinturonService;

    @Autowired
    private GimnasioService gimnasioService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private UtilService utilService;


    @Override
    public UserModel formularioInscPropia(User user) {
        return mapperUser.entity2Model(user);
    }

    @Override
    public UserAutorizacionModel formularioInscPropiaGimnasio(User user) {
        UserAutorizacionModel userAutorizacionModel = new UserAutorizacionModel();
        userAutorizacionModel.setMayorAutorizador(mapperUser.entity2Model(user));
        userAutorizacionModel.setCuentaBancaria(new CuentaBancariaModel());
        return userAutorizacionModel;
    }

    @Override
    public UserAutorizacionModel formularioInscMenorOInclusivo(User user, boolean menorOInclusivo) {
        UserAutorizacionModel userAutorizacionModel = new UserAutorizacionModel();
        userAutorizacionModel.setMayorAutorizador(mapperUser.entity2Model(user));
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

            rellenoAutorizador(mayor, pdfModel);
            rellenoCompetidor(menor, pdfModel);
            rellenoMenor(menor, pdfModel);
        }
        pdfModel.setNombreCampeonato(utilService.findByClave(Constantes.NOMBRE_CAMPEONATO).getValor());
        pdfModel.setFechaCampeonato(utilService.findByClave(Constantes.FECHA_CAMPEONATO).getValor());
        pdfModel.setDireccionCampeonato(utilService.findByClave(Constantes.DIRECCION_CAMPEONATO).getValor());

        return pdfModel;
    }

    @Override
    public void fillObjects(UserModel userModel) {
        if (userModel != null) {
            if (userModel.getGimnasio() != null && userModel.getGimnasio().getId() != 0) {
                userModel.setGimnasio(gimnasioService.findById(userModel.getGimnasio().getId()));
            }
            if (userModel.getPais() != null && userModel.getPais().getId() != 0) {
                userModel.setPais(paisService.findById(userModel.getPais().getId()));
            }
            if (userModel.getCinturon() != null && userModel.getCinturon().getId() != 0) {
                userModel.setCinturon(cinturonService.findById(userModel.getCinturon().getId()));
            }
            if (userModel.getCalidad() != null && userModel.getCalidad().getId() != 0 && userModel.getCalidad().getOtro().isEmpty()) {
                userModel.setCalidad(calidadService.findById(userModel.getCalidad().getId()));
            }
        }
    }

    @Override
    public void cargarDesplegables(ModelAndView modelAndView) {
        modelAndView.addObject("listaSexo", Arrays.asList("Masculino","Femenino"));
        modelAndView.addObject("listaMenorConKicho", Arrays.asList("Poomsae","Kicho"));
        modelAndView.addObject("listaPaises", paisService.findAll());
        modelAndView.addObject("listaGimnasios", gimnasioService.findAll());
        modelAndView.addObject("listaCinturones", cinturonService.findAll());
        modelAndView.addObject("listaCalidad", calidadService.findAll());
    }

    @Override
    public PdfModel getPdfModelGeneral(UserAutorizacionModel userAutorizacionModel) {
        PdfModel pdfModel = new PdfModel();
        rellenoAutorizador(userAutorizacionModel.getMayorAutorizador(), pdfModel);
        if (!StringUtils.isNullOrEmpty(pdfModel.getCalidadDe())) {
            rellenoMenor(userAutorizacionModel.getAutorizado(), pdfModel);
        }
        if (userAutorizacionModel.getCuentaBancaria() != null && !StringUtils.isNullOrEmpty(userAutorizacionModel.getCuentaBancaria().getIban())) {
            pdfModel.setCuentaBancaria(userAutorizacionModel.getCuentaBancaria());
        }
        return pdfModel;
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
        if(userModel.getGimnasio() != null) {
            pdfModel.setGimnasio(userModel.getGimnasio().getNombre());
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
        pdfModel.setDniMenor(userModel.getDniMenor());
        pdfModel.setFechaNacimientoMenor(Utils.date2String(userModel.getFechaNacimiento()));
    }
}
