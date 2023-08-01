package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Cinturon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cinturonRepository")
public interface CinturonRepository extends JpaRepository<Cinturon, Integer> {
    List<Cinturon> findAllByOrderByPositionAsc();

    Cinturon findByPosition(int position);

    Cinturon findTopByOrderByPositionDesc();
}