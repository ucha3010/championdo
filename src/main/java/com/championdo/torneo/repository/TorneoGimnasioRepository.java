package com.championdo.torneo.repository;

import com.championdo.torneo.entity.TorneoGimnasio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("torneoGimnasioRepository")
public interface TorneoGimnasioRepository extends JpaRepository<TorneoGimnasio, Integer> {
    
    List<TorneoGimnasio> findByIdTorneoOrderByPositionDesc(int idTorneo);

    TorneoGimnasio findTopByIdTorneoOrderByPositionDesc(int idTorneo);

    TorneoGimnasio findByIdTorneoAndPosition(int idTorneo, int position);
    
}