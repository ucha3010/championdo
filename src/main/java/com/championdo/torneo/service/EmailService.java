package com.championdo.torneo.service;

import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.exception.SenderException;

import java.io.File;

public interface EmailService {

    void sendNewPassword(UserModel userModel) throws SenderException;

    void sendConfirmation(UserModel userModel, File inscripcion) throws SenderException;

    void sendAttachedFile(UserModel userModel, String messageSubject, String messageBody, File file) throws SenderException;

}
