package com.championdo.torneo.service;


import com.championdo.torneo.model.GimnasioModel;

import java.util.List;

public interface GimnasioService {

	public abstract List<GimnasioModel> findAll();
	
	public abstract GimnasioModel findById(int id);
	
	public abstract void add(GimnasioModel gimnasioModel);
	
	public abstract void update(GimnasioModel gimnasioModel);

	public abstract void delete(int idGimnasio);

	public abstract void dragOfPosition(int initialPosition, int finalPosition);

	public abstract int findMaxPosition();
}
