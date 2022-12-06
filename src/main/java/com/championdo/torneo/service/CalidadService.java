package com.championdo.torneo.service;


import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.CalidadModel;

import java.util.List;
import java.util.Set;

public interface CalidadService {

	public abstract List<CalidadModel> findAll();
	
	public abstract CalidadModel findById(int id);
	
	public abstract void add(CalidadModel calidadModel);
	
	public abstract void update(CalidadModel calidadModel);

	public abstract void delete(int idCalidad);

	public abstract void dragOfPosition(int initialPosition, int finalPosition);

	public abstract int findMaxPosition();

}
