package com.championdo.torneo.service;


import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.UserModel;

import java.util.List;

public interface InscripcionService {
	
	public abstract InscripcionModel findById(int id);

	public abstract List<InscripcionModel> findByUsername(String username);

	public abstract InscripcionModel findByUsernameInscripto(String usernameInscripto);

	public abstract InscripcionModel add(InscripcionModel inscripcionModel);

	public abstract InscripcionModel addPropia(UserModel userModel);

	public abstract InscripcionModel addAutorizado(UserModel usuarioMayor, UserModel usuarioInscripto);

	public abstract void update(InscripcionModel inscripcionModel);

	public abstract void delete(int idInscripcion);

}
