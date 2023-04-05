package com.championdo.torneo.service;


import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UtilModel;

import java.util.List;

public interface InscripcionTaekwondoService {

	public abstract List<InscripcionTaekwondoModel> findAll();
	public abstract InscripcionTaekwondoModel findById(int id);
	public abstract List<InscripcionTaekwondoModel> findByMayorDni(String mayorDni);
	public abstract InscripcionTaekwondoModel add(InscripcionTaekwondoModel inscripcionModel);
	public abstract InscripcionTaekwondoModel add(UserAutorizacionModel userAutorizacionModel);
	public abstract void update(InscripcionTaekwondoModel inscripcionModel);
	public abstract void delete(int idInscripcion);
	public abstract void deleteAll();
	public abstract UtilModel getDeleteEnable();
	public abstract boolean changeValueDeleteEnable();
}
