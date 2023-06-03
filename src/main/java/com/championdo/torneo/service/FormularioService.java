package com.championdo.torneo.service;


import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;
import com.sun.xml.internal.ws.client.SenderException;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

public interface FormularioService {
	
	public abstract UserModel formularioInscPropia(User user);
	public abstract UserAutorizacionModel formularioInscPropiaGimnasio(User user);
	public abstract UserAutorizacionModel formularioInscMenorOInclusivo(User user, boolean menorOInclusivo);
	public abstract PdfModel getPdfModelTorneo(UserAutorizacionModel userAutorizacionModel);
	public abstract void fillObjects(UserModel userModel);
	public abstract void cargarDesplegables(ModelAndView modelAndView);
	public abstract PdfModel getPdfModelGeneral(UserAutorizacionModel userAutorizacionModel);
}
