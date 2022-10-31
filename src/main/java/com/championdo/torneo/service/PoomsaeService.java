package com.championdo.torneo.service;


import com.championdo.torneo.model.PoomsaeModel;

public interface PoomsaeService {
	
	public abstract PoomsaeModel findById(int id);
	
	public abstract void add(PoomsaeModel poomsaeModel);
	
	public abstract void update(PoomsaeModel poomsaeModel);

	public abstract void delete(int idPoomsae);

}
