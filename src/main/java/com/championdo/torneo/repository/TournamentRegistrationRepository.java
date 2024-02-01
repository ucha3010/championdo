package com.championdo.torneo.repository;

import com.championdo.torneo.entity.TournamentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tournamentRegistrationRepository")
public interface TournamentRegistrationRepository extends JpaRepository<TournamentRegistration, Integer> {
    List<TournamentRegistration> findByAuthorizerIdCardOrderByRegistrationDateDesc(String dniAutorizador);

    List<TournamentRegistration> findByRegisteredIdCardOrderByRegistrationDateDesc(String dniInscripto);

    List<TournamentRegistration> findAllByOrderByIdDesc();
    List<TournamentRegistration> findByIdTournamentOrderByRegistrationDateDesc(int idTorneo);
}