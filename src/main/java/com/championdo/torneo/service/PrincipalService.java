package com.championdo.torneo.service;


import com.championdo.torneo.model.PrincipalModel;

public interface PrincipalService {
	
	public abstract PrincipalModel findByUsername(String username);

    public abstract void deleteInscripcion(int id);
}
