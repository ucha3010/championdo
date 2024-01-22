package com.championdo.torneo.service;


import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;
import org.springframework.web.servlet.ModelAndView;

public interface FormularioService {

    UserAutorizacionModel formularioInscPropiaGimnasio(UserModel userModel);

    UserAutorizacionModel formularioInscMenorOInclusivo(UserModel userModel, boolean menorOInclusivo);

    PdfModel getPdfModelTorneo(UserAutorizacionModel userAutorizacionModel);

    void fillObjects(UserModel userModel);

    void cargarDesplegables(ModelAndView modelAndView, int codigoGimnasio);

    void cargarDesplegablesBasicos(ModelAndView modelAndView);
}
