package com.championdo.torneo.service;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.model.*;

import java.io.File;
import java.util.List;

public interface EmailService {

    void sendChangePassword(UserModel userModel, TokenModel tokenModel) throws SenderException;

    void sendTournamentRegistration(UserModel userModel, File inscripcion, InscripcionModel inscripcionModel) throws SenderException;

    void confirmAdminTournamentRegistration(UserAutorizacionModel userAutorizacionModel, InscripcionModel inscripcionModel) throws SenderException;

    void sendGymJoining(InscripcionTaekwondoModel inscripcionTaekwondoModel, List<File> files) throws SenderException;

    void confirmAdminGymJoining(InscripcionTaekwondoModel inscripcionTaekwondoModel) throws SenderException;

    void sendCodeValidation(User userModel, String code) throws SenderException;

    void confirmAdminSepaSigned(InscripcionTaekwondoModel inscripcionTaekwondoModel) throws SenderException;

    void sendUserAdded(User user) throws SenderException;

    void sendNewMandato(MandatoModel mandatoModel, List<File> files) throws SenderException;

    void confirmAdminNewMandato(MandatoModel mandatoModel) throws SenderException;

    void confirmAdminDelete(int codigoGimnasio, String actividad, User user, String nombreMenor);
}
