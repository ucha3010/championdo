package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Calidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("calidadRepository")
public interface CalidadRepository extends JpaRepository<Calidad, Serializable>{

    public abstract List<Calidad> findAllByOrderByPositionAsc();
    public abstract Calidad findByPosition(int position);
    public abstract Calidad findTopByOrderByPositionDesc();

}
