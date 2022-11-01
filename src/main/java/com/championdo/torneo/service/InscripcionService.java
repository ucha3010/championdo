package com.championdo.torneo.service;


import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.UserModel;

import java.util.List;

public interface InscripcionService {
	
	public abstract InscripcionModel findById(int id);

	public abstract List<InscripcionModel> findByDniAutorizador(String dniAutorizador);

	public abstract InscripcionModel findByDniInscripto(String dniInscripto);

	public abstract InscripcionModel add(InscripcionModel inscripcionModel);

	public abstract InscripcionModel addPropia(UserModel userModel);

	public abstract InscripcionModel addMenor(UserModel usuarioAutorizador, UserModel usuarioInscripto, boolean menorNoPreinfantil);
	public abstract InscripcionModel addInclusivo(UserModel usuarioAutorizador, UserModel usuarioInscripto);

	public abstract void update(InscripcionModel inscripcionModel);

	public abstract void delete(int idInscripcion);

}
