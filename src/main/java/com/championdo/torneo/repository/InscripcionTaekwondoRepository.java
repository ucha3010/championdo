package com.championdo.torneo.repository;

import com.championdo.torneo.entity.InscripcionTaekwondo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("InscripcionTaekwondoRepository")
public interface InscripcionTaekwondoRepository extends JpaRepository<InscripcionTaekwondo, Serializable>{

    public abstract List<InscripcionTaekwondo> findByMayorDni(String mayorDni);
    public abstract List<InscripcionTaekwondo> findAllByOrderByIdDesc();

}
