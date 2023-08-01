package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoriaRepository")
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByNombre(String nombre);

    Categoria findByEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndInfantilFalse(int edadInicio, int edadFin);

    Categoria findByEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndIdCinturonInicioLessThanEqualAndIdCinturonFinGreaterThanEqualAndInfantil(int edadInicio, int edadFin, int cituronInicio, int cinturonFin, boolean infantil);

    List<Categoria> findAllByOrderByPositionAsc();

    Categoria findByPosition(int position);

    Categoria findTopByOrderByPositionDesc();

    List<Categoria> findByIdCinturonInicioOrIdCinturonFin(int idCinturonInicio, int idCinturonFin);
}