package com.championdo.torneo.service;


import com.championdo.torneo.model.CinturonModel;

import java.util.List;

public interface CinturonService {

	public abstract List<CinturonModel> findAll();
	
	public abstract CinturonModel findById(int id);
	
	public abstract void add(CinturonModel cinturonModel);
	
	public abstract void update(CinturonModel cinturonModel);

	public abstract void delete(int idCinturon);
}
