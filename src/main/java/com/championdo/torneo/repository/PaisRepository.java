package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("paisRepository")
public interface PaisRepository extends JpaRepository<Pais, Serializable>{

}
