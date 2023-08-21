package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Firma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository("firmaRepository")
public interface FirmaRepository extends JpaRepository<Firma, Integer> {

    Firma findByIdOperacion(int idOperacion) throws EntityNotFoundException;

}