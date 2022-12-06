package com.championdo.torneo.service;


import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.PoomsaeModel;

import java.util.List;

public interface PoomsaeService {

	public abstract List<PoomsaeModel> findAll();
	
	public abstract PoomsaeModel findById(int id);
	
	public abstract void add(PoomsaeModel poomsaeModel);
	
	public abstract void update(PoomsaeModel poomsaeModel);

	public abstract void delete(int idPoomsae) throws RemoveException;

	public abstract void dragOfPosition(int initialPosition, int finalPosition);

	public abstract int findMaxPosition();

}
