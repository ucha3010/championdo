package com.championdo.torneo.repository;

import com.championdo.torneo.entity.FirmaCodigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("firmaCodigoRepository")
public interface FirmaCodigoRepository extends JpaRepository<FirmaCodigo, Integer> {

    FirmaCodigo findByIdOperacion(int idOperacion);

    List<FirmaCodigo> findAllByOrderByFechaCaducidadDesc();

}