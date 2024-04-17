/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.util;



 
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
 
public class MailSender implements Runnable {
    private String subject;
    private String body;
    private String from;
    private String pass;
    private String[] to;
    private Boolean fo;

    public MailSender(String body,String subject,String from,String pass,String[] to ) {
            this.body = body;
            this.subject=subject;
            this.from=from;
            this.pass=pass;
            this.to=to;
          
    }

    @Override
    public void run() {
       sendFromGMail(from, pass, to, subject, body );
    }

 private static void sendFromGMail(String from, String pass, String[] to, String subject, String body ) 
 {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");//OK
        props.put("mail.transport.protocol", "smtp");//OK
        props.put("mail.smtp.starttls.enable", "true");//OK
        props.put("mail.smtp.port", "587");//OK
        //props.put("mail.smtp.port", "587");//OK
        //props.put("mail.smtp.user", from);
        //props.put("mail.smtp.password", pass);
        //props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart(); 

        try 
        {
            message.setFrom(new InternetAddress(from));            
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                    toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            
             MimeBodyPart mbp = new MimeBodyPart(); 
            if(true){ 
               mbp.setContent(body.toString(), "text/html"); 
            } 
            else{ 
               mbp.setText(body.toString()); 
            } 
            
             multipart.addBodyPart(mbp); 
             message.setContent(multipart); 
             
            Transport.send(message,from, pass);
            //Transport transport = session.getTransport("smtp");
            //transport.connect("smtp.gmail.com", from, pass);
            //transport.sendMessage(message, message.getAllRecipients());
            //transport.close();
        }
        catch (AddressException ae) {
           System.out.println("AddressException: "+ae);
        }
        catch (MessagingException me) {
                System.out.println("Exception: "+me);
        }
    }    

}
