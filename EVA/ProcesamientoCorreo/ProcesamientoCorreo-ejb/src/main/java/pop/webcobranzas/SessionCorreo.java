/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import pop.comun.dominio.MaeCorreo;
import pop.webcobranzas.correo.IEnvioCorreo;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbEnviarCorreo")
public class SessionCorreo implements IEnvioCorreo {

    @Override
    public List<MaeCorreo> enviar(List<MaeCorreo> oMaeCorreos) throws Exception {
        
        Locale.setDefault(new Locale("es", "PE"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMMM 'de' yyyy");

        List<MaeCorreo> lista = new ArrayList<>();
        for (MaeCorreo maeCorreo : oMaeCorreos) {
            Properties props = new Properties();

            props.put("mail.smtp.host", "smtpout.secureserver.net"); //SMTP Host
            props.put("mail.smtp.socketFactory.port", "25"); //SSL Port
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
            props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
            props.put("mail.smtp.port", "25"); //SMTP Port

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("jyovera@popular-safi.com", "bubucREwref7VasSafi23");
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress("jyovera@popular-safi.com", "JYOVERA"));
            msg.setReplyTo(InternetAddress.parse("jyovera@popular-safi.com", false));
            msg.setSubject("Estado de Cuenta", "UTF-8");
            msg.setText("TLSEmail Testing Body", "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(maeCorreo.getMaeEmails().get(0).getDemail(), false));

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            
               
            String plmMensaje = new String(Files.readAllBytes(Paths.get("/pop/webcobranzas/resources/template/templatemail.html")));
            
            plmMensaje=plmMensaje.replace("$NOMBRE", maeCorreo.getMaeEmails().get(0).getMaePersona().getDApePat() + " " + maeCorreo.getMaeEmails().get(0).getMaePersona().getDApeMat() + maeCorreo.getMaeEmails().get(0).getMaePersona().getDNombres());
            
            plmMensaje=plmMensaje.replace("$CODIGOINVERSION", maeCorreo.getMaeEmails().get(0).getMaePersona().getMaeInversion().getCInversion());
            
            plmMensaje=plmMensaje.replace("$FECHA",formatter.format(maeCorreo.getMaeEmails().get(0).getfIniBusq() ));

            System.out.println(plmMensaje);

            //messageBodyPart.setText(mensaje, "text/html");
            messageBodyPart.setText(plmMensaje, "utf-8", "html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();

            DataSource source = new ByteArrayDataSource(maeCorreo.getAdjuntos().get(0), "application/pdf");
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("test.pdf");
            multipart.addBodyPart(messageBodyPart);

            msg.setContent(multipart);

            System.out.println("Message is ready");
            Transport.send(msg);

        }

        return lista;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
