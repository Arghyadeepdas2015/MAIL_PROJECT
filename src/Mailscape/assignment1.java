package Mailscape;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class assignment1 {
public static void main(String args[])
{
    System.out.println("preparing the message");
    String message="MESSAGE";
    String subject="SUBJECT";
    String to="mondalkingshuk29@gmail.com";
    String from="arghyadeepdas2018@gmail.com";
    sendmail(message,subject,to,from);
}

    private static void sendmail(String message, String subject, String to, String from) {
    Properties p=new Properties();

    // setting the properties of host
    p.setProperty("mail.smtp.host","smtp.gmail.com");
    p.setProperty("mail.smtp.port","465");
    p.setProperty("mail.smtp.ssl.enable","true");
    p.setProperty("mail.smtp.auth","true");

    Session session=Session.getInstance(p, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("arghyadeepdas2018@gmail.com","");
        }
    });
session.setDebug(true);
    Message message1=new MimeMessage(session);

    try{
        message1.setFrom(new InternetAddress(from));

        message1.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

        message1.setSubject(subject);

        Multipart multi=new MimeMultipart();

        MimeBodyPart text=new MimeBodyPart();
        text.setText(message);

        MimeBodyPart attachment =new MimeBodyPart();
        String path="D:\\Pictures\\Camera Roll\\IMG_20200912_215439165.jpg";
        File file=new File(path);
        attachment.attachFile(file);

        multi.addBodyPart(text);
        multi.addBodyPart(attachment);

        message1.setContent(multi);

        Transport.send(message1);
        System.out.println("Message is send");
    }
    catch (Exception e){

    }
    }
}
