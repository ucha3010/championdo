package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.mapper.MapperUser;
import com.championdo.torneo.model.CalidadModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;
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
    public UserAutorizacionModel formularioInscMenorOInclusivo(User user, boolean menorOInclusivo) {
        UserAutorizacionModel userAutorizacionModel = new UserAutorizacionModel();
        userAutorizacionModel.setMayorAutorizador(mapperUser.entity2Model(user));
        UserModel autorizado = new UserModel();
        if (menorOInclusivo) {
            autorizado.setMenor(true);
        } else {
            autorizado.setInclusivo(true);
        }
        userAutorizacionModel.setAutorizado(autorizado);
        return userAutorizacionModel;
    }

    @Override
    public PdfModel getPdf(UserAutorizacionModel userAutorizacionModel) {
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
        pdfModel.setFechaCampeonato(utilService.findByClave(Constantes.FECHA_CAMPEONATO));
        pdfModel.setDireccionCampeonato(utilService.findByClave(Constantes.DIRECCION_CAMPEONATO));

        return pdfModel;
    }

    @Override
    public void fillObjects(UserModel userModel) {
        userModel.setGimnasio(gimnasioService.findById(userModel.getGimnasio().getId()));
        userModel.setPais(paisService.findById(userModel.getPais().getId()));
        userModel.setCinturon(cinturonService.findById(userModel.getCinturon().getId()));
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

    private void rellenoAutorizador (UserModel userModel, PdfModel pdfModel) {
        pdfModel.setNombre(userModel.getName() + " " + userModel.getLastname() + (userModel.getSecondLastname() != null ? " " + userModel.getSecondLastname() : ""));
        pdfModel.setDni(userModel.getUsername());
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

        pdfModel.setFechaNacimiento(Utils.date2String(userModel.getFechaNacimiento()));
        pdfModel.setGimnasio(userModel.getGimnasio().getNombre());
        pdfModel.setCinturonActual(userModel.getCinturon().getColor());
        if (userModel.getCinturon().getColor().equalsIgnoreCase(Constantes.BLANCO)) {
            pdfModel.setCinturonBlanco(true);
        }
    }

    private void rellenoMenor(UserModel userModel, PdfModel pdfModel) {
        pdfModel.setNombreMenor(userModel.getName() + " " + userModel.getLastname() + (userModel.getSecondLastname() != null ? " " + userModel.getSecondLastname() : ""));
        pdfModel.setDniMenor(userModel.getUsername());
    }
}
