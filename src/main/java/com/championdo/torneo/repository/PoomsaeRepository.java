package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Poomsae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("poomsaeRepository")
public interface PoomsaeRepository extends JpaRepository<Poomsae, Integer> {
    List<Poomsae> findAllByOrderByPositionAsc();

    Poomsae findByPosition(int position);

    Poomsae findTopByOrderByPositionDesc();
}