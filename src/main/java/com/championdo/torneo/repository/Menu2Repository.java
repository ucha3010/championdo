package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Menu2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("menu2Repository")
public interface Menu2Repository extends JpaRepository<Menu2, Integer> {

    Menu2 findTopByIdMenu1OrderByPositionDesc(int idMenu1);

    List<Menu2> findByIdMenu1OrderByPositionAsc(int idMenu1);

    Menu2 findByIdMenu1AndPosition(int idMenu1, int position);

    void deleteByIdMenu1(int idMenu1);
}