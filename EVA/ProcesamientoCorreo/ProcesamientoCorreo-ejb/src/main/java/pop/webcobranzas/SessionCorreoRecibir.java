/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import pop.comun.dominio.MaeCorreo;
import pop.comun.dominio.MaeEmail;
import pop.webcobranzas.correo.IRecepcionCorreo;

/**
 *
 * @author Jhon Yovera
 */
@Stateless(mappedName = "ejbRecibirCorreo")
public class SessionCorreoRecibir implements IRecepcionCorreo {

    @Override
    public int cantidadCorreos(String file) throws Exception {
        MailService mailService = new MailService();
        mailService.login("pop.secureserver.net", "jyovera@popular-safi.com", "bubucREwref7VasSafi23", file);
        int messageCount = mailService.getMessageCount();
        mailService.logout();
        return messageCount;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<MaeCorreo> recibir(String file) throws Exception {
        List<MaeCorreo> lista = new ArrayList<>();
        MailService mailService = new MailService();
        mailService.login("pop.secureserver.net", "jyovera@popular-safi.com", "bubucREwref7VasSafi23", file);
        Message[] messages = mailService.getMessages();
        System.out.println("messages.length---" + messages.length);

        System.out.println("This is the message envelope");
        System.out.println("---------------------------");

        for (Message message : messages) {
            MaeCorreo correo = new MaeCorreo();
            //MaeEmail email = new MaeEmail();
            List<MaeEmail> listMaeEmail = new ArrayList<>();
            //Address[] a;
            InternetAddress[] address;
            // FROM
            if ((address = (InternetAddress[]) message.getFrom()) != null) {
                for (int j = 0; j < address.length; j++) {
                    MaeEmail email = new MaeEmail();

//                    if(address[j].getPersonal().isEmpty()){
//                        email.setDnombre(address[j].getAddress());
//                    }else{
//                        email.setDnombre(address[j].getPersonal());
//                    }
                    email.setDnombre(address[j].getAddress());
                    email.setDemail(address[j].getAddress());
                    listMaeEmail.add(email);
                    System.out.println("FROM: " + address[j].getAddress());
                }
            }

            // SUBJECT
            if (message.getSubject() != null) {
                correo.setAsunto(message.getSubject());
                System.out.println("SUBJECT: " + message.getSubject());
            }
            correo.setMaeEmails((ArrayList<MaeEmail>) listMaeEmail);
            correo.setFenvio(message.getSentDate());

            Part p = message;
            if (p.isMimeType("multipart/*")) {
                System.out.println("This is a Multipart");
                System.out.println("---------------------------");
                correo.setBadjunto(true);
            }

            lista.add(correo);
        }
        return lista;
    }
}
