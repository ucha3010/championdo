package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoriaRepository")
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByCodigoGimnasioAndNombre(int codigoGimnasio, String nombre);

    Categoria findByCodigoGimnasioAndEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndInfantilFalse(int codigoGimnasio, int edadInicio, int edadFin);

    Categoria findByCodigoGimnasioAndEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndIdCinturonInicioLessThanEqualAndIdCinturonFinGreaterThanEqualAndInfantil(int codigoGimnasio, int edadInicio, int edadFin, int cituronInicio, int cinturonFin, boolean infantil);

    List<Categoria> findByCodigoGimnasioOrderByPositionAsc(int codigoGimnasio);

    Categoria findByCodigoGimnasioAndPosition(int codigoGimnasio, int position);

    Categoria findTopByCodigoGimnasioOrderByPositionDesc(int codigoGimnasio);

    List<Categoria> findByCodigoGimnasio(int codigoGimnasio);

    List<Categoria> findByCodigoGimnasioAndIdCinturonInicioOrIdCinturonFin(int codigoGimnasio, int idCinturonInicio, int idCinturonFin);

}