package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Menu1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("menu1Repository")
public interface Menu1Repository extends JpaRepository<Menu1, Integer> {

    List<Menu1> findAllByOrderByPositionAsc();

    Menu1 findByPosition(int position);

    Menu1 findTopByOrderByPositionDesc();
}