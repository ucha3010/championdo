package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.mapper.MapperUser;
import com.championdo.torneo.model.*;
import com.championdo.torneo.service.*;
import com.championdo.torneo.util.Utils;
import com.championdo.torneo.util.Constantes;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            pdfModel.setNombre(userModel.getName() + " " + userModel.getLastname() + (userModel.getSecondLastname() != null ? " " + userModel.getSecondLastname() : ""));
            pdfModel.setDni(userModel.getUsername());
            pdfModel.setFechaNacimiento(Utils.date2String(userModel.getFechaNacimiento()));
            if (!StringUtils.isNullOrEmpty(userModel.getDomicilioCalle())) {
                pdfModel.setDomicilio(userModel.getDomicilioCalle() + " " + userModel.getDomicilioNumero() + " " + userModel.getDomicilioOtros());
                pdfModel.setLocalidad(userModel.getDomicilioLocalidad() + " (" + userModel.getDomicilioCp() + ") - " + userModel.getPais().getNombre());
            }
            pdfModel.setGimnasio(userModel.getGimnasio().getNombre());
            pdfModel.setCinturonActual(userModel.getCinturon().getColor());
            if (userModel.getCinturon().getColor().equalsIgnoreCase(Constantes.BLANCO)) {
                pdfModel.setCinturonBlanco(true);
            }
        } else { //inscripción de menor
            pdfModel.setMayorEdad(false);
            UserModel mayor = userAutorizacionModel.getMayorAutorizador();
            UserModel menor = userAutorizacionModel.getAutorizado();

            if (menor.getCinturon().getColor().equalsIgnoreCase(Constantes.BLANCO)) {
                pdfModel.setCinturonBlanco(true);
            }
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
}
