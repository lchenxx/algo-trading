package algotrading.common;

import algotrading.services.MACDProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

  private static final Logger log = LoggerFactory.getLogger(SendMail.class);

  public void sendEmail(String input) {

    String receiverEmail = "monkeydog32@gmail.com";
    String senderEmail = "lichen.21534@gmail.com";
    String host = "smtp.gmail.com";

    Properties properties = System.getProperties();
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "465");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");

    Session session =
        Session.getInstance(
            properties,
            new javax.mail.Authenticator() {

              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                    System.getenv("EMAIL"), System.getenv("PASSWORD"));
              }
            });

    // Used to debug SMTP issues
    //    session.setDebug(true);

    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(senderEmail));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
      message.setSubject("Recommended trading actions " + new Timestamp(new Date().getTime()));
      message.setText(input);

      Transport.send(message);
      log.info("email sent successfully to " + receiverEmail);
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
  }
}
