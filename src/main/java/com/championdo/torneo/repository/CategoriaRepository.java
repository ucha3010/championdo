package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("categoriaRepository")
public interface CategoriaRepository extends JpaRepository<Categoria, Serializable>{

    public abstract Categoria findByNombre(String nombre);
    public abstract Categoria findByEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndInfantilFalse(int edadInicio, int edadFin);
    public abstract Categoria findByEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndIdCinturonInicioLessThanEqualAndIdCinturonFinGreaterThanEqualAndInfantil(int edadInicio, int edadFin, int cituronInicio, int cinturonFin, boolean infantil);
    public abstract List<Categoria> findAllByOrderByPositionAsc();
    public abstract Categoria findByPosition(int position);
    public abstract Categoria findTopByOrderByPositionDesc();
    public abstract List<Categoria> findByIdCinturonInicioOrIdCinturonFin(int idCinturonInicio, int idCinturonFin);

}
