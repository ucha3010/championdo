package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.UtilService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.SendMessage;
import com.mysql.cj.util.StringUtils;
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
            sendMessage.enviarCorreo(utilService.findByClave(Constantes.CORREO_GIMNASIO, userModel.getCodigoGimnasio()).getValor(), userModel.getCorreo(),
                    "Nueva contraseña inscripción torneo", textMessageNewPassword(userModel), null, userModel.getCodigoGimnasio());
        } catch (Exception e) {
            throw new SenderException(Constantes.AVISO_EMAIL,e.getMessage());
        }
    }

    @Override
    public void sendTournamentRegistration(UserModel userModel, File inscripcion) throws SenderException {
        List<File> files = new ArrayList<>();
        files.add(inscripcion);
        sendAttachedFile(userModel, "Confirmación inscripción torneo taekwondo", textMessageTournamentRegistration(userModel), files);
    }

    @Override
    public void confirmAdminTournamentRegistration(UserAutorizacionModel userAutorizacionModel) throws SenderException {
        try {
            String correoGimnasio = utilService.findByClave(Constantes.CORREO_GIMNASIO, userAutorizacionModel.getMayorAutorizador().getCodigoGimnasio()).getValor();
            sendMessage.enviarCorreo(correoGimnasio, correoGimnasio,
                    "Nueva inscripción en torneo", textMessageConfirmAdminTournamentRegistration(userAutorizacionModel), null, userAutorizacionModel.getMayorAutorizador().getCodigoGimnasio());
        } catch (Exception e) {
            throw new SenderException(Constantes.AVISO_EMAIL,e.getMessage());
        }

    }

    @Override
    public void sendGymJoining(InscripcionTaekwondoModel inscripcionTaekwondoModel, List<File> files) throws SenderException {
        UserModel userModel = new UserModel();
        userModel.setName(inscripcionTaekwondoModel.getMayorNombre());
        userModel.setCorreo(inscripcionTaekwondoModel.getMayorCorreo());
        sendAttachedFile(userModel, "Confirmación inscripción gimnasio", textMessageGymJoining(inscripcionTaekwondoModel), files);
    }

    @Override
    public void confirmAdminGymJoining(InscripcionTaekwondoModel inscripcionTaekwondoModel) throws SenderException {
        try {
            String correoGimnasio = utilService.findByClave(Constantes.CORREO_GIMNASIO, inscripcionTaekwondoModel.getCodigoGimnasio()).getValor();
            sendMessage.enviarCorreo(correoGimnasio, correoGimnasio,
                    "Nueva inscripción en el gimnasio", textMessageConfirmAdminGymJoining(inscripcionTaekwondoModel), null, inscripcionTaekwondoModel.getCodigoGimnasio());
        } catch (Exception e) {
            throw new SenderException(Constantes.AVISO_EMAIL,e.getMessage());
        }
    }

    @Override
    public void sendCodeValidation(User user, String code) throws SenderException {
        try {
            sendMessage.enviarCorreo(utilService.findByClave(Constantes.CORREO_GIMNASIO, user.getCodigoGimnasio()).getValor(), user.getCorreo(),
                    "Código de validación", textMessageCodeValidation(user, code), null, user.getCodigoGimnasio());
        } catch (Exception e) {
            throw new SenderException(Constantes.AVISO_EMAIL,e.getMessage());
        }
    }

    @Override
    public void sendAttachedFile(UserModel userModel, String messageSubject, String messageBody, List<File> files) throws SenderException {
        try {
            sendMessage.enviarCorreo(utilService.findByClave(Constantes.CORREO_GIMNASIO, userModel.getCodigoGimnasio()).getValor(), userModel.getCorreo(),
                    messageSubject, messageBody, files, userModel.getCodigoGimnasio());
        } catch (Exception e) {
            throw new SenderException(Constantes.AVISO_EMAIL_ARCHIVO_ADJUNTO,e.getMessage());
        }
    }

    @Override
    public void confirmAdminSepaSigned(InscripcionTaekwondoModel inscripcionTaekwondoModel) throws SenderException {
        try {
            String correoGimnasio = utilService.findByClave(Constantes.CORREO_GIMNASIO, inscripcionTaekwondoModel.getCodigoGimnasio()).getValor();
            sendMessage.enviarCorreo(correoGimnasio, correoGimnasio,
                    "Nuevo formulario de domiciliación firmado", textMessageConfirmAdminSepaSigned(inscripcionTaekwondoModel), null, inscripcionTaekwondoModel.getCodigoGimnasio());
        } catch (Exception e) {
            throw new SenderException(Constantes.AVISO_EMAIL,e.getMessage());
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

    private String textMessageGymJoining(InscripcionTaekwondoModel inscripcionTaekwondoModel) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<HTML><BODY>");
        stringBuilder.append("<h1>Hola<b>").append(inscripcionTaekwondoModel.getMayorNombre()).append("</b>!</h1><br>");
        stringBuilder.append("<p>Te adjuntamos la confirmación de inscripción al Gimnasio.</p>");
        if (inscripcionTaekwondoModel.isDomiciliacionSEPA()) {
            stringBuilder.append("<p>Por favor no olvides enviarnos la autorización de domiciliación firmada.</p>");
            stringBuilder.append("<p>Es un proceso muy sencillo y lo debes hacer desde la misma página de inscripción.</p>");
            stringBuilder.append("<br><br>");
        }
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>¡Que pases un buen día!</p>");
        stringBuilder.append("</BODY></HTML>");
        return stringBuilder.toString();
    }

    private String textMessageConfirmAdminGymJoining(InscripcionTaekwondoModel insc) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<HTML><BODY>");
        String mayor = insc.getMayorNombre() + " " + insc.getMayorApellido1() +
                (StringUtils.isNullOrEmpty(insc.getMayorApellido2()) ? "" : " " + insc.getMayorApellido2());
        String autorizado = null;
        if(!StringUtils.isNullOrEmpty(insc.getAutorizadoNombre())) {
            autorizado = insc.getAutorizadoNombre() + " " + insc.getAutorizadoApellido1() +
                    (StringUtils.isNullOrEmpty(insc.getAutorizadoApellido2()) ? "" : " " + insc.getAutorizadoApellido2());
        }
        stringBuilder.append("<h1>Hola<b>").append("</b>!</h1><br>");
        if (autorizado == null) {
            stringBuilder.append("<p>¡Se acaba de inscribir ").append(mayor).append(" en el gimnasio!</p>");
        } else {
            stringBuilder.append("<p>¡Se acaba de inscribir ").append(autorizado).append(" (que es menor de edad) en el gimnasio!</p>");
            stringBuilder.append("<p>Está autorizado por su ").append(insc.getMayorCalidad()).append(" que se llama ").append(mayor).append(".</p>");
        }
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>¡Que pases un buen día!</p>");
        stringBuilder.append("</BODY></HTML>");
        return stringBuilder.toString();
    }

    private String textMessageConfirmAdminTournamentRegistration(UserAutorizacionModel userAutorizacionModel) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<HTML><BODY>");
        UserModel mayor = userAutorizacionModel.getMayorAutorizador();
        UserModel autorizado = userAutorizacionModel.getAutorizado();
        if (autorizado == null) {
            stringBuilder.append("<p>¡Se acaba de inscribir ").append(mayor.getName()).append(" ")
                    .append(mayor.getLastname()).append(" ").append(mayor.getSecondLastname()).append(" en el torneo!</p>");
        } else {
            stringBuilder.append("<p>¡Se acaba de inscribir ").append(autorizado.getName()).append(" ")
                    .append(autorizado.getLastname()).append(" ").append(autorizado.getSecondLastname()).append(" (que es menor de edad) en el torneo!</p>");
            stringBuilder.append("<p>Está autorizado por ").append(mayor.getName()).append(" ").append(mayor.getLastname())
                    .append(" ").append(mayor.getSecondLastname()).append(".</p>");
        }
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>¡Que pases un buen día!</p>");
        stringBuilder.append("</BODY></HTML>");
        return stringBuilder.toString();
    }

    private String textMessageCodeValidation(User user, String code) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<HTML><BODY>");
        String name = user.getName() != null ? " " + user.getName() : "";
        stringBuilder.append("<h1>Hola<b>").append(name).append("</b>!</h1><br>");
        stringBuilder.append("<p>El código de validación que debes utilizar es el siguiente</p>");
        stringBuilder.append("<br>");
        stringBuilder.append("<h2>").append(code).append("</h2>");
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>Este código tiene una validez de 15 minutos.</p>");
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>¡Que pases un buen día!</p>");
        stringBuilder.append("</BODY></HTML>");
        return stringBuilder.toString();
    }

    private String textMessageConfirmAdminSepaSigned(InscripcionTaekwondoModel insc) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<HTML><BODY>");
        String mayor = insc.getMayorNombre() + " " + insc.getMayorApellido1() +
                (StringUtils.isNullOrEmpty(insc.getMayorApellido2()) ? "" : " " + insc.getMayorApellido2());
        stringBuilder.append("<h1>Hola<b>").append("</b>!</h1><br>");
        stringBuilder.append("<p>El cliente ").append(mayor).append(" acaba de subir un formulario de domiciliación bancaria firmado.</p>");
        stringBuilder.append("<br><br>");
        stringBuilder.append("<p>¡Que pases un buen día!</p>");
        stringBuilder.append("</BODY></HTML>");
        return stringBuilder.toString();
    }
}
