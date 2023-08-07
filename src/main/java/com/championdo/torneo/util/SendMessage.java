package com.championdo.torneo.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class SendMessage {

    private static final String APPLICATION_NAME = "Torneo inscripcion" ;
    private static final String CREDENTIALS_FILE_PATH = "/static/files/credentials.json";
    private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_SEND, GmailScopes.GMAIL_COMPOSE);

    private final Gmail service;

    public SendMessage() throws Exception{
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory gsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, gsonFactory, getCredentials(httpTransport, gsonFactory))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Send an email from the user's mailbox to its recipient.
     *
     * @param fromEmailAddress - Email address to appear in the form: header
     * @param toEmailAddress   - Email address of the recipient
     * @throws MessagingException - if a wrongly formatted address is encountered.
     * @throws IOException        - if service account credentials file not found.
     */
    public void sendEmail(String fromEmailAddress, String toEmailAddress, String messageSubject, String bodyText, File file)
            throws MessagingException, IOException {


        // Encode as MIME message
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(fromEmailAddress));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(toEmailAddress));
        email.setSubject(messageSubject);

        if (file == null) {
            email.setContent(bodyText, "text/html; charset=utf-8");
        } else {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(bodyText, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            mimeBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            mimeBodyPart.setDataHandler(new DataHandler(source));
            mimeBodyPart.setFileName(file.getName());
            multipart.addBodyPart(mimeBodyPart);
            email.setContent(multipart);
        }

        // Encode and wrap the MIME message into a gmail message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);

        try {
                // Create send message
                message = service.users().messages().send("me", message).execute();
                LoggerMapper.log(Level.INFO, "sendEmail", "Message id: " + message.getId(), SendMessage.class);
                LoggerMapper.log(Level.INFO, "sendEmail", "Message string: " + message.toPrettyString(), SendMessage.class);
        } catch (GoogleJsonResponseException e) {
            LoggerMapper.log(Level.ERROR, "sendEmail", e.getDetails(), SendMessage.class);
            throw new MessagingException(e.getMessage());
        }
    }

    /**
     * Creates an authorized Credential object.
     *
     * @param httpTransport The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory gsonFactory)
            throws IOException {
        // Load client secrets.
        InputStream in = SendMessage.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(gsonFactory, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, gsonFactory, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**primero dentro de GMAIL ir a la configuración de la cuenta
     * apartado Seguridad
     * en el apartado "Acceso a Google" tocar Contraseñas de aplicaciones
     * Generar nueva contraseña con opción "Otra (nombre personalizado)"
     * le pongo un nombre cualquiera
     * copiar la contraseña que me genera para usarla después acá
    */
    public void enviarCorreo(String fromEmailAddress, String toEmailAddress, String messageSubject, String bodyText, File file)
            throws MessagingException, IOException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", fromEmailAddress);
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.auth", "true");
        //TODO DAMIAN seguir acá con indicaciones https://www.youtube.com/watch?v=ZggjlwLzrxg minuto 5:25


        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);

    }

}
