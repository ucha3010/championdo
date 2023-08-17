package com.championdo.torneo.service.impl;

import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.UtilService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.SendMessage;
import com.championdo.torneo.exception.SenderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service()
public class EmailServiceImpl implements EmailService {
    @Autowired
    private SendMessage sendMessage;

    @Autowired
    private UtilService utilService;

    @Override
    public void sendNewPassword (UserModel userModel) throws SenderException {
        try {
            sendMessage.enviarCorreo(utilService.findByClave(Constantes.CORREO_GIMNASIO).getValor(), userModel.getCorreo(),
                    "Nueva contraseña inscripción torneo", textMessageNewPassword(userModel), null);
        } catch (Exception e) {
            throw new SenderException("1000",e.getMessage());
        }
    }

    @Override
    public void sendTournamentRegistration(UserModel userModel, File inscripcion) throws SenderException {
        List<File> files = new ArrayList<>();
        files.add(inscripcion);
        sendAttachedFile(userModel, "Confirmación inscripción torneo taekwondo", textMessageTournamentRegistration(userModel), files);
    }

    @Override
    public void sendGymJoining(UserModel userModel, List<File> files) throws SenderException {
        sendAttachedFile(userModel, "Confirmación inscripción gimnasio", textMessageGymJoining(userModel), files);
    }

    @Override
    public void sendAttachedFile(UserModel userModel, String messageSubject, String messageBody, List<File> files) throws SenderException {
        try {
            sendMessage.enviarCorreo(utilService.findByClave(Constantes.CORREO_GIMNASIO).getValor(), userModel.getCorreo(),
                    messageSubject, messageBody, files);
        } catch (Exception e) {
            throw new SenderException("1001",e.getMessage());
        }
    }

    private String textMessageNewPassword (UserModel userModel) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<HTML><BODY>");
        String name = userModel.getName() != null ? " " + userModel.getName() : "";
        stringBuilder.append("<h1>Hola<b>").append(name).append("</b>!</h1><br>");
        stringBuilder.append("<p>Nos ha llegado tu solicitud de cambio de contraseña</p>");
        stringBuilder.append("<p>Tu nueva contraseña es: ").append(userModel.getPassword()).append("</p>");
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>¡Que pases un buen día!</p>");
        stringBuilder.append("</BODY></HTML>");
        return stringBuilder.toString();

    }

    private String textMessageTournamentRegistration(UserModel userModel) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<HTML><BODY>");
        String name = userModel.getName() != null ? " " + userModel.getName() : "";
        stringBuilder.append("<h1>Hola<b>").append(name).append("</b>!</h1><br>");
        stringBuilder.append("<p>Te adjuntamos la confirmación de inscripción al Torneo de Tres Cantos.</p>");
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>¡Que pases un buen día!</p>");
        stringBuilder.append("</BODY></HTML>");
        return stringBuilder.toString();
    }

    private String textMessageGymJoining(UserModel userModel) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<HTML><BODY>");
        String name = userModel.getName() != null ? " " + userModel.getName() : "";
        stringBuilder.append("<h1>Hola<b>").append(name).append("</b>!</h1><br>");
        stringBuilder.append("<p>Te adjuntamos la confirmación de inscripción al Gimnasio.</p>");
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>¡Que pases un buen día!</p>");
        stringBuilder.append("</BODY></HTML>");
        return stringBuilder.toString();
    }
}
