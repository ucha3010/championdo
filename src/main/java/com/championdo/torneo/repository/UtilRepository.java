package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("utilRepository")
public interface UtilRepository extends JpaRepository<Util, String> {
    Util findByClave(String clave);
}