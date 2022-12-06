package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("paisRepository")
public interface PaisRepository extends JpaRepository<Pais, Serializable>{

    public abstract List<Pais> findAllByOrderByPositionAsc();
    public abstract Pais findByPosition(int position);
    public abstract Pais findTopByOrderByPositionDesc();

}
