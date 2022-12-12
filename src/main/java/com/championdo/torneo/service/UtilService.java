package com.championdo.torneo.service;


import com.championdo.torneo.model.UtilModel;

import java.util.List;

public interface UtilService {

	public abstract List<UtilModel> findAllCampeonato();
	
	public abstract UtilModel findByClave(String clave);
	
	public abstract void update(UtilModel utilModel);

}
