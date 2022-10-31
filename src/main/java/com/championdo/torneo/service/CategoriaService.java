package com.championdo.torneo.service;


import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.CalidadModel;
import com.championdo.torneo.model.CategoriaModel;
import com.championdo.torneo.model.UserModel;

import java.util.Set;

public interface CategoriaService {
	
	public abstract CategoriaModel findById(int id);
	
	public abstract void add(CategoriaModel categoriaModel);
	
	public abstract void update(CategoriaModel categoriaModel);

	public abstract void delete(int idCategoria);

	public abstract CategoriaModel calcularCategoria(UserModel usuarioInscripto);
}
