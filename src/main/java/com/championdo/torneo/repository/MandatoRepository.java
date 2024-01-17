package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Mandato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MandatoRepository")
public interface MandatoRepository extends JpaRepository<Mandato, Integer> {
    List<Mandato> findByCodigoGimnasioOrderByFechaAltaDesc(int codigoGimnasio);
    List<Mandato> findByDniMandanteOrderByFechaAltaDesc(String dniMandante);
    List<Mandato> findByCodigoGimnasioAndDniMandanteAndMandatoFirmadoFalseOrderByFechaAltaDesc(int codigoGimnasio, String dniMandante);
    List<Mandato> findByDniMandanteAndTemporadaAndMandatoFirmadoTrue(String dniMandante, String temporada);
}