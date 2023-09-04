package com.championdo.torneo.repository;

import com.championdo.torneo.entity.GimnasioRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gimnasioRootRepository")
public interface GimnasioRootRepository extends JpaRepository<GimnasioRoot, Integer> {

    List<GimnasioRoot> findAllByOrderByNombreGimnasioAsc();

}