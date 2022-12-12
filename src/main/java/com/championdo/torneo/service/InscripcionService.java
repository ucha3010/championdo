package com.championdo.torneo.service;


import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.model.UtilModel;

import java.util.List;

public interface InscripcionService {

	public abstract List<InscripcionModel> findAll();

	public abstract InscripcionModel findById(int id);

	public abstract List<InscripcionModel> findByDniAutorizador(String dniAutorizador);

	public abstract InscripcionModel findByDniInscripto(String dniInscripto);

	public abstract InscripcionModel add(InscripcionModel inscripcionModel);

	public abstract InscripcionModel addPropia(UserModel userModel);

	public abstract InscripcionModel addMenorOInclusivo(UserAutorizacionModel userAutorizacionModel);

	public abstract void update(InscripcionModel inscripcionModel);

	public abstract void delete(int idInscripcion);

	public abstract void deleteAll();

	public abstract UtilModel getDeleteEnable();

	public abstract boolean changeValueDeleteEnable();
}
