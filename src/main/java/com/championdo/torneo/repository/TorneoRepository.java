package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("torneoRepository")
public interface TorneoRepository extends JpaRepository<Torneo, Integer> {
    
    List<Torneo> findByCodigoGimnasio(int codigoGimnasio);
    List<Torneo> findByCodigoGimnasioOrderByFechaTorneoDesc(int codigoGimnasio);
    Torneo findByCodigoGimnasioAndId(int codigoGimnasio, int id);
    List<Torneo> findByCodigoGimnasioAndNombre(int codigoGimnasio, String nombre);
    List<Torneo> findByCodigoGimnasioAndFechaComienzoInscripcionLessThanEqualAndFechaFinInscripcionGreaterThanEqual(int codigoGimnasio, Date fechaComienzoInscripcion, Date fechaFinInscripcion);
    List<Torneo> findByFechaComienzoInscripcionLessThanEqualAndFechaFinInscripcionGreaterThanEqualAndAdultoTrue(Date fechaComienzoInscripcion, Date fechaFinInscripcion);
    List<Torneo> findByFechaComienzoInscripcionLessThanEqualAndFechaFinInscripcionGreaterThanEqualAndMenorTrue(Date fechaComienzoInscripcion, Date fechaFinInscripcion);
    List<Torneo> findByFechaComienzoInscripcionLessThanEqualAndFechaFinInscripcionGreaterThanEqualAndInclusivoTrue(Date fechaComienzoInscripcion, Date fechaFinInscripcion);

}