package com.ocr.util;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class MailUtil {
    private static final String HOST = "smtp.example.com";
    private static final String USER = "your@email.com";
    private static final String PASS = "your_email_password";
    public static void sendStatusEmail(String to, int complaintId, String newStatus){
        // Simple example - replace with valid SMTP settings.
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(USER, PASS);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Complaint #"+complaintId+" Status Updated");
            message.setText("Your complaint (ID:"+complaintId+") status changed to: "+newStatus);
            Transport.send(message);
        } catch(Exception e){ e.printStackTrace(); }
    }
}
