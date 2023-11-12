package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoriaRepository")
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByCodigoGimnasioAndNombre(int codigoGimnasio, String nombre);

    Categoria findByCodigoGimnasioAndEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndPreinfantilTrue(int codigoGimnasio, int edadInicio, int edadFin);

    List<Categoria> findByCodigoGimnasioAndEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndInfantilTrue(int codigoGimnasio, int edadInicio, int edadFin);

    List<Categoria> findByCodigoGimnasioAndEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndAdultoTrue(int codigoGimnasio, int edadInicio, int edadFin);

    List<Categoria> findByCodigoGimnasioOrderByPositionAsc(int codigoGimnasio);

    Categoria findByCodigoGimnasioAndPosition(int codigoGimnasio, int position);

    Categoria findTopByCodigoGimnasioOrderByPositionDesc(int codigoGimnasio);

    List<Categoria> findByCodigoGimnasio(int codigoGimnasio);

    List<Categoria> findByCodigoGimnasioAndPositionCinturonInicioOrPositionCinturonFin(int codigoGimnasio, int cituronInicio, int cinturonFin);

    List<Categoria> findByCodigoGimnasioAndIdPoomsae(int codigoGimnasio, int idPoomsae);

    List<Categoria> findByCodigoGimnasioAndPositionCinturonFinGreaterThanEqualAndPositionCinturonInicioLessThanEqual(int codigoGimnasio, int cinturonPosMenor, int cituronPosMayor);

    List<Categoria> findByCodigoGimnasioAndIdCinturonInicioOrIdCinturonFin(int codigoGimnasio, int idCituronInicio, int idCinturonFin);

}