package com.championdo.torneo.service;


import com.championdo.torneo.model.PrincipalModel;
import com.championdo.torneo.model.UtilModel;

public interface PrincipalService {
	
	public abstract PrincipalModel findByDni(String dni);

    public abstract void deleteInscripcion(int id);

    public abstract UtilModel getDeleteEnable();
}
