/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Diego
 */
// File Name SendMail.java
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {

    public static void main(String[] args) {

        SendMail sm = new SendMail();
        boolean rep = sm.send("juanmanuelroldan86@gmail.com", "Asunto mensaje", "Cuertpo del mensaje");
        System.out.println("Resultado: " + rep);
    }

    public static boolean verificaEmail(String email) {
        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(emailreg);

    }

    public final boolean send(String email, String asunto, String cuerpo, String email_from) {
        Properties config = new Properties();

        try {
            InputStream config_file = getClass().getClassLoader().getResourceAsStream("ss_config.properties");
            config.load(config_file);
        } catch (IOException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }

        final String email_cuenta = "okn2017.app@gmail.com";
        final String email_pass = "okn*2017";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        //props.put("mail.transport.protocol", "smtp");
        Session mailSession = null;

        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);

        MimeMessage message = new MimeMessage(session);

        try {
            // Quien envia el correo            
            message.setFrom(new InternetAddress(email_from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(asunto);
            message.setReplyTo(new InternetAddress[]{new InternetAddress(email_from)});

            String body = cuerpo;
            message.setContent(body, "text/html");
            Transport t = session.getTransport("smtp");
            t.connect(email_cuenta, email_pass);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            System.out.println("Respuesta: True");
            return true;
        } catch (MessagingException ex) {
            System.out.println("CAUSE: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Respuesta: False");
            return false;
        }

        /*mailSession = Session.getInstance(props,
         new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(email_cuenta, email_pass);
         }
         });

         try {

         Transport transport = mailSession.getTransport();

         MimeMessage message = new MimeMessage(mailSession);

         message.setSubject(asunto);
         message.setFrom(new InternetAddress(email_from));
         String[] to = new String[]{email};
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[0]));
         String body = cuerpo;
         message.setContent(body, "text/html");
         transport.connect();

         transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
         transport.close();
         return true;

         } catch (Exception exception) {
         return false;
         }*/
    }

    public final boolean sendCuentaParalela(String email, String asunto, String cuerpo) {
        Properties config = new Properties();

        try {
            InputStream config_file = getClass().getClassLoader().getResourceAsStream("ss_config.properties");
            config.load(config_file);
        } catch (IOException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }

        final String email_cuenta = config.getProperty("email_cuenta");
        final String email_pass = config.getProperty("email_pass");
        final String email_from = config.getProperty("email_from");
        final String email_smtp = config.getProperty("email_smtp");
        final Integer email_port = Integer.parseInt(config.getProperty("email_port"));

        Properties props = new Properties();
        props.put("mail.smtp.host", email_smtp);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", email_port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", email_smtp);

        //props.put("mail.transport.protocol", "smtp");
        Session mailSession = null;

        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);

        MimeMessage message = new MimeMessage(session);

        try {
            // Quien envia el correo
            message.setFrom(new InternetAddress(email_from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(asunto);
            String body = cuerpo;
            message.setContent(body, "text/html");
            Transport t = session.getTransport("smtp");
            t.connect(email_cuenta, email_pass);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            System.out.println("Respuesta: True");
            return true;
        } catch (MessagingException ex) {
            System.out.println("CAUSE: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Respuesta: False");
            return false;
        }

        /*mailSession = Session.getInstance(props,
         new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(email_cuenta, email_pass);
         }
         });

         try {

         Transport transport = mailSession.getTransport();

         MimeMessage message = new MimeMessage(mailSession);

         message.setSubject(asunto);
         message.setFrom(new InternetAddress(email_from));
         String[] to = new String[]{email};
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[0]));
         String body = cuerpo;
         message.setContent(body, "text/html");
         transport.connect();

         transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
         transport.close();
         return true;

         } catch (Exception exception) {
         return false;
         }*/
    }

    public final boolean send(String email, String asunto, String cuerpo) {
        

        final String email_cuenta = "okn2017.app@gmail.com";
        final String email_pass = "okn*2017";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        //props.put("mail.transport.protocol", "smtp");
        Session mailSession = null;

        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);

        MimeMessage message = new MimeMessage(session);

        try {
            // Quien envia el correo
            message.setFrom(new InternetAddress(email_cuenta));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(asunto);
            String body = cuerpo;
            message.setContent(body, "text/html");
            Transport t = session.getTransport("smtp");
            t.connect(email_cuenta, email_pass);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            System.out.println("Respuesta: True");
            return true;
        } catch (MessagingException ex) {
            System.out.println("CAUSE: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Respuesta: False");
            return false;
        }

        /*mailSession = Session.getInstance(props,
         new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(email_cuenta, email_pass);
         }
         });

         try {

         Transport transport = mailSession.getTransport();

         MimeMessage message = new MimeMessage(mailSession);

         message.setSubject(asunto);
         message.setFrom(new InternetAddress(email_from));
         String[] to = new String[]{email};
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[0]));
         String body = cuerpo;
         message.setContent(body, "text/html");
         transport.connect();

         transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
         transport.close();
         return true;

         } catch (Exception exception) {
         return false;
         }*/
    }
}
