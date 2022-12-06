package com.championdo.torneo.service;


import com.championdo.torneo.model.CategoriaModel;
import com.championdo.torneo.model.UserModel;

import java.util.List;

public interface CategoriaService {

	public abstract List<CategoriaModel> findAll();
	public abstract List<CategoriaModel> findAllNameExtended();

	public abstract CategoriaModel findById(int id);
	
	public abstract void add(CategoriaModel categoriaModel);
	
	public abstract void update(CategoriaModel categoriaModel);

	public abstract void delete(int idCategoria);

	public abstract CategoriaModel calcularCategoria(UserModel usuarioInscripto);

	public abstract void dragOfPosition(int initialPosition, int finalPosition);

	public abstract int findMaxPosition();
}
