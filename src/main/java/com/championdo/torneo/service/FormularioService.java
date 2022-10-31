package com.championdo.torneo.service;


import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;

public interface FormularioService {
	
	public abstract UserModel formularioInscPropia(User user);
	public abstract UserAutorizacionModel formularioInscMenor(User user, String menorOInclusivo);

	public abstract PdfModel getPdf(UserAutorizacionModel userAutorizacionModel);

	public abstract void fillObjects(UserModel userModel);
}
