package com.championdo.torneo.service;


import com.championdo.torneo.model.*;

import java.util.List;

public interface TournamentRegistrationService {

    List<TournamentRegistrationModel> findAll();

    TournamentRegistrationModel findById(int id);

    List<TournamentRegistrationModel> findByAuthorizerIdCard(String authorizerIdCard);

    List<TournamentRegistrationModel> findByRegisteredIdCard(String registeredIdCard);
    List<TournamentRegistrationModel> findByIdTournament(int idTournament);

    TournamentRegistrationModel add(TournamentRegistrationModel tournamentRegistrationModel);

    TournamentRegistrationModel addAdult(UserModel userModel, PdfModel pdfModel, int idGym);

    TournamentRegistrationModel addYoungOrInclusive(UserAutorizacionModel userAutorizacionModel, PdfModel pdfModel, int idGym);

    void update(TournamentRegistrationModel tournamentRegistrationModel);

    void delete(TournamentRegistrationModel tournamentRegistrationModel);

    void deleteByIdCard(String idCard);

    void deleteAll();

    UtilModel getDeleteEnable(int idGym);
}
