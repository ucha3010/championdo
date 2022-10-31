package com.championdo.torneo.service;


import com.championdo.torneo.model.PaisModel;

import java.util.List;

public interface PaisService {

	public abstract List<PaisModel> findAll();
	
	public abstract PaisModel findById(int id);
	
	public abstract void add(PaisModel paisModel);
	
	public abstract void update(PaisModel paisModel);

	public abstract void delete(int idPais);
}
