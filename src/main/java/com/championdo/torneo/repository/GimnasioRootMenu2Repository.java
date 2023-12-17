package com.championdo.torneo.repository;

import com.championdo.torneo.entity.GimnasioRootMenu2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gimnasioRootMenu2Repository")
public interface GimnasioRootMenu2Repository extends JpaRepository<GimnasioRootMenu2, Integer> {

    List<GimnasioRootMenu2> findByIdGimnasioRoot(int idGimnasioRoot);

    List<GimnasioRootMenu2> findByIdMenu2(int idMenu2);

    GimnasioRootMenu2 findByIdMenu2AndIdGimnasioRoot(int idMenu2, int idGimnasioRoot);

    void deleteByIdMenu2(int idMenu2);
    void deleteByIdGimnasioRoot(int idGimnasioRoot);

}