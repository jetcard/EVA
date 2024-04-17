/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas;

import java.util.Properties;
import javax.mail.Authenticator;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

/**
 *
 * @author Jhon Yovera
 */
public class MailService {

    private Session session;
    private Store store;
    private Folder folder;

    public void login(String host, final String username, final String password, String file)
            throws Exception {
        Properties props = new Properties();
        // SSL setting
        props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.pop3.socketFactory.fallback", "false"); //SSL Factory Class
        props.put("mail.pop3.socketFactory.port", "995"); //SSL Factory Class

        props.put("mail.pop3.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.pop3.port", "995"); //SMTP Port

        props.put("mail.store.protocol", "pop3");

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        session = Session.getInstance(props, auth);

        URLName url = new URLName("pop3", host, 995, file, username, password);

        store = session.getStore(url);
        store.connect();
        folder = store.getFolder(url);
        folder.open(Folder.READ_WRITE);
    }

    public boolean isLoggedIn() {
        return store.isConnected();
    }

    public void logout() throws MessagingException {
        folder.close(false);
        store.close();
        store = null;
        session = null;
    }

    public int getMessageCount() {
        int messageCount = 0;
        try {
            Folder[] f = store.getDefaultFolder().list();
            for (Folder fd : f) {
                System.out.println(">> " + fd.getName());
            }
            messageCount = folder.getMessageCount();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return messageCount;
    }

    public Message[] getMessages() throws MessagingException {
        return folder.getMessages();
    }

}
