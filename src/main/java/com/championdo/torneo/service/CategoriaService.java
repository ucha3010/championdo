package com.championdo.torneo.service;


import com.championdo.torneo.model.CategoriaModel;
import com.championdo.torneo.model.UserModel;

import java.util.List;

public interface CategoriaService {

    List<CategoriaModel> findAll(int codigoGimnasio);

    List<CategoriaModel> findAllNameExtended(int codigoGimnasio);

    CategoriaModel findById(int id);

    void add(CategoriaModel categoriaModel);

    void update(CategoriaModel categoriaModel);

    void delete(int idCategoria);

    CategoriaModel calcularCategoria(UserModel usuarioInscripto);

    void dragOfPosition(int codigoGimnasio, int initialPosition, int finalPosition);

    int findMaxPosition(int codigoGimnasio);

    void deleteFromRoot (int idGimnasioRootModel);
}
