package com.championdo.torneo.util;

import com.championdo.torneo.service.UtilService;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.List;
import java.util.Properties;

@Component
public class SendMessage {

    @Autowired
    private UtilService utilService;

    /**
     * Primero ir a la configuración de la cuenta de Google.
     * Entrar en el apartado Seguridad.
     * La verificación en dos pasos debe estar activada.
     * Entrar en el apartado "Verificación en dos pasos" y tocar "Contraseñas de aplicaciones".
     * Generar nueva contraseña con opción "Otra (nombre personalizado)" y poner un nombre cualquiera.
     * Copiar la contraseña que me genera y guardarla en tabla de utilidades bajo la llave clave.correo
     * Indicaciones www.youtube.com/watch?v=ZggjlwLzrxg
     * Indicaciones archivos adjuntos www.youtube.com/watch?v=o7v0EQgxP50
    */
    public void enviarCorreo(String fromEmailAddress, String toEmailAddress, String messageSubject, String bodyText, List<File> files)
            throws MessagingException {

        LoggerMapper.methodIn(Level.INFO, "enviarCorreo", "fromEmailAddress: " + fromEmailAddress + ", toEmailAddress: " + toEmailAddress, getClass());

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", fromEmailAddress);
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(fromEmailAddress));
        email.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toEmailAddress));
        email.setSubject(messageSubject);
        if (files == null) {
            email.setText(bodyText, "ISO-8859-1", "html");
        } else {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(bodyText, "text/html; charset=utf-8");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            for (File file: files) {
                mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
                mimeBodyPart.setFileName(file.getName());
                multipart.addBodyPart(mimeBodyPart);
            }
            email.setContent(multipart);
        }
        LoggerMapper.log(Level.INFO, "enviarCorreo", email, SendMessage.class);

        //Enviar el correo
        Transport transport = session.getTransport("smtp");
        transport.connect(fromEmailAddress, utilService.findByClave(Constantes.CLAVE_CORREO).getValor());
        transport.sendMessage(email, email.getRecipients(javax.mail.Message.RecipientType.TO));
        transport.close();

        LoggerMapper.methodOut(Level.INFO, "enviarCorreo", "Correo enviado", SendMessage.class);
    }

}
