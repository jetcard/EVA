/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

 
public class ManageProperties {
    
    private String ruta_conf;
    private String mail_from;
    private String mail_pwd;
    private String[] mail_to;




    public String getMail_from() {
        return mail_from;
    }

    public void setMail_from(String mail_from) {
        this.mail_from = mail_from;
    }

    public String getMail_pwd() {
        return mail_pwd;
    }

    public void setMail_pwd(String mail_pwd) {
        this.mail_pwd = mail_pwd;
    }

    public String[] getMail_to() {
        return mail_to;
    }

    public void setMail_to(String[] mail_to) {
        this.mail_to = mail_to;
    }
    
  public String getRuta_conf() {
        return ruta_conf;
    }

    public void setRuta_conf(String ruta_conf) {
        this.ruta_conf = ruta_conf;
    }
    
    
    public void getProperties() {
        
        Properties prop = new Properties();
        FileInputStream fis = null;

        try {

            // open existing file from the project's root folder
            fis = new FileInputStream("C:/Aplicaciones/Java/Eva/wildfly-10.1.0.Final/bin/eva.properties");
            
            // load properties
            prop.load(fis);
            
            // read property
            //setRuta_conf(prop.getProperty("RUTA_CONF"));
            setMail_from(prop.getProperty("MAIL_FROM"));
            setMail_pwd(prop.getProperty("MAIL_PWD"));
            String[] arrMail_to=prop.getProperty("MAIL_TO").split(",");
            setMail_to(arrMail_to);
          
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    
    
}
