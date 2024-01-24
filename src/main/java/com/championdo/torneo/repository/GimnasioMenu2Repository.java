package com.championdo.torneo.repository;

import com.championdo.torneo.entity.GimnasioMenu2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gimnasioMenu2Repository")
public interface GimnasioMenu2Repository extends JpaRepository<GimnasioMenu2, Integer> {

    List<GimnasioMenu2> findByIdGimnasio(int idGimnasio);

    List<GimnasioMenu2> findByIdMenu2(int idMenu2);

    GimnasioMenu2 findByIdMenu2AndIdGimnasio(int idMenu2, int idGimnasio);

    void deleteByIdMenu2(int idMenu2);
    void deleteByIdGimnasio(int idGimnasio);

}