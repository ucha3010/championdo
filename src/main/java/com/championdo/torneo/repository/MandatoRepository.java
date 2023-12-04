package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Mandato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MandatoRepository")
public interface MandatoRepository extends JpaRepository<Mandato, Integer> {
    List<Mandato> findByCodigoGimnasioOrderByFechaAltaDesc(int codigoGimnasio);
    List<Mandato> findByCodigoGimnasioAndDniMandanteOrderByFechaAltaDesc(int codigoGimnasio, String dniMandante);
}