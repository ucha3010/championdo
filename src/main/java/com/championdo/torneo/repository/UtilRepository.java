package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("utilRepository")
public interface UtilRepository extends JpaRepository<Util, String> {
    Util findByClaveAndCodigoGimnasio(String clave, int codigoGimnasio);
    List<Util> findByCodigoGimnasio(int codigoGimnasio);
}