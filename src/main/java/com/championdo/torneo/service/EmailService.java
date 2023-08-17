package com.championdo.torneo.service;

import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.exception.SenderException;

import java.io.File;
import java.util.List;

public interface EmailService {

    void sendNewPassword(UserModel userModel) throws SenderException;

    void sendTournamentRegistration(UserModel userModel, File inscripcion) throws SenderException;

    void sendGymJoining(UserModel userModel, List<File> files) throws SenderException;

    void sendAttachedFile(UserModel userModel, String messageSubject, String messageBody, List<File> files) throws SenderException;

}
