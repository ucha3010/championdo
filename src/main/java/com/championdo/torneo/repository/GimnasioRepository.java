package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Gimnasio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("gimnasioRepository")
public interface GimnasioRepository extends JpaRepository<Gimnasio, Serializable>{

    public abstract List<Gimnasio> findAllByOrderByPositionAsc();
    public abstract Gimnasio findByPosition(int position);
    public abstract Gimnasio findTopByOrderByPositionDesc();

}
