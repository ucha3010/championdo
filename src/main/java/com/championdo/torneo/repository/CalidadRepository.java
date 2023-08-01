package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Calidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("calidadRepository")
public interface CalidadRepository extends JpaRepository<Calidad, Integer> {
    List<Calidad> findAllByOrderByPositionAsc();

    Calidad findByPosition(int position);

    Calidad findTopByOrderByPositionDesc();
}