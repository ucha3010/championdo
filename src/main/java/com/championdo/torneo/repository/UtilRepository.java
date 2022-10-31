package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("utilRepository")
public interface UtilRepository extends JpaRepository<Util, Serializable>{

    public abstract Util findByClave(String clave);

}
