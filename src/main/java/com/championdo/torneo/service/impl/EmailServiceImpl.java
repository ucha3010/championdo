package com.championdo.torneo.service.impl;

import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.UtilService;
import com.championdo.torneo.util.SendMessage;
import com.sun.xml.internal.ws.client.SenderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service()
public class EmailServiceImpl implements EmailService {
    @Autowired
    private SendMessage sendMessage;

    @Autowired
    private UtilService utilService;

    @Override
    public void sendNewPassword (UserModel userModel) throws SenderException {
        try {
            sendMessage.sendEmail(utilService.findByClave("gimnasio.correo"), userModel.getCorreo(),
                    "Nueva contraseña inscripción torneo", textMessageNewPassword(userModel), null);
        } catch (Exception e) {
            throw new SenderException(e);
        }
    }

    @Override
    public void sendConfirmation(UserModel userModel, File inscripcion) throws SenderException {
        sendAttachedFile(userModel, "Confirmación inscripción torneo taekwondo", textMessageConfirmation(userModel), inscripcion);
    }

    @Override
    public void sendAttachedFile(UserModel userModel, String messageSubject, String messageBody, File file) throws SenderException {
        try {
            sendMessage.sendEmail(utilService.findByClave("gimnasio.correo"), userModel.getCorreo(),
                    messageSubject, messageBody, file);
        } catch (Exception e) {
            throw new SenderException(e);
        }
    }

    private String textMessageNewPassword (UserModel userModel) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<HTML><BODY>");
        String name = userModel.getName() != null ? " " + userModel.getName() : "";
        stringBuilder.append("<h1>Hola<b>"+name+"</b>!</h1><br>");
        stringBuilder.append("<p>Nos ha llegado tu solicitud de cambio de contraseña</p>");
        stringBuilder.append("<p>Tu nueva contraseña es: " + userModel.getPassword() + "</p>");
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>¡Que pases un buen día!</p>");
        stringBuilder.append("</BODY></HTML>");
        return stringBuilder.toString();

    }

    private String textMessageConfirmation(UserModel userModel) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<HTML><BODY>");
        String name = userModel.getName() != null ? " " + userModel.getName() : "";
        stringBuilder.append("<h1>Hola<b>"+name+"</b>!</h1><br>");
        stringBuilder.append("<p>Te adjuntamos la confirmación de inscripción al Torneo de Tres Cantos.</p>");
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>¡Que pases un buen día!</p>");
        stringBuilder.append("</BODY></HTML>");
        return stringBuilder.toString();
    }
}
