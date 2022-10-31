package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Calidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("calidadRepository")
public interface CalidadRepository extends JpaRepository<Calidad, Serializable>{

}
