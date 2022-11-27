package com.championdo.torneo.service;

import com.championdo.torneo.model.UserModel;
import com.sun.xml.internal.ws.client.SenderException;

import java.io.File;

public interface EmailService {

    public void sendNewPassword (UserModel userModel) throws SenderException;
    public abstract void sendConfirmation(UserModel userModel, File inscripcion) throws SenderException;
    public void sendAttachedFile (UserModel userModel, String messageSubject, String messageBody, File file) throws SenderException;

}
