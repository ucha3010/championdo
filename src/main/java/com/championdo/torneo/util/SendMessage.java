package com.championdo.torneo.util;

import com.championdo.torneo.model.EmailModel;
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
    public void enviarCorreo(EmailModel emailModel) throws MessagingException {

        LoggerMapper.methodIn(Level.INFO, "enviarCorreo", "fromEmailAddress: " + emailModel.getFromEmailAddress()
                + ", toEmailAddress: " + emailModel.getToEmailAddress(), getClass());

        Properties props = new Properties();
        props.put("mail.smtp.host", emailModel.getHost());//smtp.gmail.com,smtp.office365.com
        props.put("mail.smtp.ssl.trust", emailModel.getHost());//smtp.gmail.com,smtp.office365.com
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", emailModel.getPort());//587
        props.setProperty("mail.smtp.user", emailModel.getFromEmailAddress());
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");//no está
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(emailModel.getFromEmailAddress()));
        email.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(emailModel.getToEmailAddress()));
        email.setSubject(emailModel.getMessageSubject());
        if (emailModel.getFiles() == null) {
            email.setText(emailModel.getBodyText(), "ISO-8859-1", "html");
        } else {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(emailModel.getBodyText(), "text/html; charset=utf-8");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            for (File file: emailModel.getFiles()) {
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
        transport.connect(emailModel.getFromEmailAddress(), utilService.findByClave(Constantes.CLAVE_CORREO, emailModel.getCodigoGimnasio()).getValor());
        transport.sendMessage(email, email.getRecipients(javax.mail.Message.RecipientType.TO));
        transport.close();

        LoggerMapper.methodOut(Level.INFO, "enviarCorreo", "Correo enviado", SendMessage.class);
    }

}
