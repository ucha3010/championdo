package com.championdo.torneo.service;


import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.CalidadModel;

import java.util.Set;

public interface CalidadService {
	
	public abstract CalidadModel findById(int id);
	
	public abstract Set<CalidadModel> findByNombre(String nombre);
	
	public abstract void add(CalidadModel calidadModel);
	
	public abstract void update(CalidadModel calidadModel);

	public abstract void delete(int idCalidad);

}
