package com.championdo.torneo.service;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.TokenModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;

import java.io.File;
import java.util.List;

public interface EmailService {

    void sendChangePassword(UserModel userModel, TokenModel tokenModel) throws SenderException;

    void sendTournamentRegistration(UserModel userModel, File inscripcion) throws SenderException;

    void confirmAdminTournamentRegistration(UserAutorizacionModel userAutorizacionModel) throws SenderException;

    void sendGymJoining(InscripcionTaekwondoModel inscripcionTaekwondoModel, List<File> files) throws SenderException;

    void confirmAdminGymJoining(InscripcionTaekwondoModel inscripcionTaekwondoModel) throws SenderException;

    void sendCodeValidation(User userModel, String code) throws SenderException;

    void sendAttachedFile(UserModel userModel, String messageSubject, String messageBody, List<File> files) throws SenderException;

    void confirmAdminSepaSigned(InscripcionTaekwondoModel inscripcionTaekwondoModel) throws SenderException;

    void sendUserAdded(User user) throws SenderException;
}
