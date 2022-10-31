package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Gimnasio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("gimnasioRepository")
public interface GimnasioRepository extends JpaRepository<Gimnasio, Serializable>{

}
