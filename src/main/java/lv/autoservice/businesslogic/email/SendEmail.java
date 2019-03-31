package lv.autoservice.businesslogic.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    String emailHost = "smtp.gmail.com";
    String fromUser = "autoserviceAandA";
    String fromUserEmailPassword = "Training007";

    public static boolean SendMailMessage(StringBuilder sb, String toEmail){

        SendEmail javaEmail = new SendEmail();
        javaEmail.setMailServerProperties();
        try {
            javaEmail.createEmailMessage(sb, toEmail);
            javaEmail.sendEmailMessage();
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    public void setMailServerProperties() {
        String emailPort = "587";//gmail's smtp port
        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
    }

    public void createEmailMessage(StringBuilder sb, String toEmail) throws AddressException, MessagingException {
        String emailSubject = "AutoServiceAandA";

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);
        emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(sb.toString(), "text/html");
    }

    public void sendEmailMessage() throws AddressException, MessagingException {

        Transport transport = mailSession.getTransport("smtp");

        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
    }
}

