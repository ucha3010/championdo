package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Cinturon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cinturonRepository")
public interface CinturonRepository extends JpaRepository<Cinturon, Integer> {
    List<Cinturon> findByCodigoGimnasioOrderByPositionAsc(int codigoGimnasio);

    Cinturon findByCodigoGimnasioAndPosition(int codigoGimnasio, int position);

    Cinturon findTopByCodigoGimnasioOrderByPositionDesc(int codigoGimnasio);

    List<Cinturon> findByCodigoGimnasio(int codigoGimnasio);

    int findPositionByCodigoGimnasioAndId(int codigoGimnasio, int id);
}