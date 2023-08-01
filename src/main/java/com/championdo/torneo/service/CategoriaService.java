package com.championdo.torneo.service;


import com.championdo.torneo.model.CategoriaModel;
import com.championdo.torneo.model.UserModel;

import java.util.List;

public interface CategoriaService {

    List<CategoriaModel> findAll();

    List<CategoriaModel> findAllNameExtended();

    CategoriaModel findById(int id);

    void add(CategoriaModel categoriaModel);

    void update(CategoriaModel categoriaModel);

    void delete(int idCategoria);

    CategoriaModel calcularCategoria(UserModel usuarioInscripto);

    void dragOfPosition(int initialPosition, int finalPosition);

    int findMaxPosition();
}
