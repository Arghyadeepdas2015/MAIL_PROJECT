package project1;

import org.jsoup.Jsoup;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;


public class receivemessage {
	 static ArrayList<info> arr;
    public static void main(String args[])
    {
        System.out.println("reading the mail");
        String username="arghyadeepdas2018@gmail.com";
        String password="Arghyadeepdas@2018";

        receive(username,password);
    }

    private static void receive(String username, String password) {

        Properties p=new Properties();

        //server setting
        p.put(String.format("mail.%s.host","imaps"),"imap.gmail.com");
        p.put(String.format("mail.%s.port","imaps"),"993");

        //SSL setting
        p.setProperty(String.format("mail.%socketFactory.class","imaps"),"javax.net.ssl.SSLSocketFactory");
        p.setProperty(String.format("mail.%socketFactory.fallback","imaps"),"false");
        p.setProperty(String.format("mail.%socketFactory.port","imaps"),String.valueOf("993"));
        p.setProperty("mail.imaps.partialfetch","false");

        arr = new ArrayList<>();
        String [] result=new String[30];
        Session session=Session.getDefaultInstance(p );
        try {
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com","arghyadeepdas2018@gmail.com",password);

            Folder folder=store.getFolder("[Gmail]/All Mail");
            folder.open(Folder.READ_ONLY);

            Message [] emailMessage= folder.getMessages();
            int n=emailMessage.length;
            System.out.println("Total messeges in email = "+n);

            
            for(int i=n-1;i>=n-30;i--)
            {
                Message mess=emailMessage[i];

                InternetAddress recepient=(InternetAddress) mess.getFrom()[0];
                String from=recepient.getAddress();
                String subject=mess.getSubject();
                String bodypart="";
                if(mess.isMimeType("text/plain") && result[1]==null)
                {
                    bodypart=mess.getContent().toString();
                }
                else if(mess.isMimeType("multipart/*"))
                {
                    try {
                        Multipart multipart=(Multipart) mess.getContent();
                        if(result[1]==null)
                        {
                            bodypart=getTestFromMineMultiPart(multipart);
                        }
                    }catch (IOException e)
                    {

                    }
                }
              arr.add( new info(from,subject,bodypart));
               

            }
            folder.close(false);
            store.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(" error is found");
        }
    }

    private static String getTestFromMineMultiPart(Multipart multipart)throws MessagingException {
        String  result="";
        int n=multipart.getCount();
        try {
            for (int i = 0; i < n; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    result = result + bodyPart.getContent().toString();
                } else if (bodyPart.isMimeType("text/html")) {
                    String html =(String) bodyPart.getContent();
                    result=result+ org.jsoup.Jsoup.parse(html).text();
                }
                else if(bodyPart.getContent() instanceof Multipart){
                    result =result+ getTestFromMineMultiPart((Multipart) bodyPart.getContent());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

