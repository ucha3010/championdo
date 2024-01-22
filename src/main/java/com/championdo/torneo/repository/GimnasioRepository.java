package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Gimnasio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gimnasioRepository")
public interface GimnasioRepository extends JpaRepository<Gimnasio, Integer> {

    List<Gimnasio> findAllByOrderByNombreGimnasioAsc();
    List<Gimnasio> findByCifNif(String cifNif);

}