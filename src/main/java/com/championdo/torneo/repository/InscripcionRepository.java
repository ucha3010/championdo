package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("inscripcionRepository")
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    List<Inscripcion> findByDniAutorizadorOrderByFechaInscripcionDesc(String dniAutorizador);

    List<Inscripcion> findByDniInscriptoOrderByFechaInscripcionDesc(String dniInscripto);

    List<Inscripcion> findAllByOrderByIdDesc();
    List<Inscripcion> findByIdTorneoOrderByFechaInscripcionDesc(int idTorneo);
}