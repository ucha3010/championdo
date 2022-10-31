package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("inscripcionRepository")
public interface InscripcionRepository extends JpaRepository<Inscripcion, Serializable>{

    public abstract List<Inscripcion> findByUsername(String username);

    public abstract Inscripcion findByUsernameInscripto(String usernameInscripto);

}
