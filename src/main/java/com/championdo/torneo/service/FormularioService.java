package com.championdo.torneo.service;


import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;
import org.springframework.web.servlet.ModelAndView;

public interface FormularioService {

    UserModel formularioInscPropia(User user);

    UserAutorizacionModel formularioInscPropiaGimnasio(User user);

    UserAutorizacionModel formularioInscMenorOInclusivo(User user, boolean menorOInclusivo);

    PdfModel getPdfModelTorneo(UserAutorizacionModel userAutorizacionModel);

    void fillObjects(UserModel userModel);

    void cargarDesplegables(ModelAndView modelAndView, int codigoGimnasio);
}
