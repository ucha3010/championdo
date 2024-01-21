package com.championdo.torneo.repository;

import com.championdo.torneo.entity.UtilManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("utilManagerRepository")
public interface UtilManagerRepository extends JpaRepository<UtilManager, Integer> {
}