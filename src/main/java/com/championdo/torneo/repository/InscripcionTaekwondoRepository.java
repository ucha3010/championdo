package com.championdo.torneo.repository;

import com.championdo.torneo.entity.InscripcionTaekwondo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("InscripcionTaekwondoRepository")
public interface InscripcionTaekwondoRepository extends JpaRepository<InscripcionTaekwondo, Integer> {
    List<InscripcionTaekwondo> findByMayorDni(String mayorDni);

    List<InscripcionTaekwondo> findAllByOrderByIdDesc();
}