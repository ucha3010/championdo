package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Cinturon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("cinturonRepository")
public interface CinturonRepository extends JpaRepository<Cinturon, Serializable>{

    public abstract List<Cinturon> findAllByOrderByPositionAsc();
    public abstract Cinturon findByPosition(int position);
    public abstract Cinturon findTopByOrderByPositionDesc();

}
