package com.championdo.torneo.service;


import com.championdo.torneo.model.PrincipalModel;

public interface PrincipalService {
	
	public abstract PrincipalModel findByDni(String dni);

    public abstract void deleteInscripcion(int id);
}
