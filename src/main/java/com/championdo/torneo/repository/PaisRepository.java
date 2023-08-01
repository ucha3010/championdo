package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("paisRepository")
public interface PaisRepository extends JpaRepository<Pais, Integer> {
    List<Pais> findAllByOrderByPositionAsc();

    Pais findByPosition(int position);

    Pais findTopByOrderByPositionDesc();
}