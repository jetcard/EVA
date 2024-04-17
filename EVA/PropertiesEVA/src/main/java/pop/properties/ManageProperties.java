/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 *
 * @author HH38092
 */
public class ManageProperties {
    private String url_servidorweb;
    
    /**
     * @return the url_servidorweb
     */
    public String getUrl_servidorweb() {
        return url_servidorweb;
    }

    /**
     * @param url_servidorweb the url_servidorweb to set
     */
    public void setUrl_servidorweb(String url_servidorweb) {
        this.url_servidorweb = url_servidorweb;
    }
    
    public void getProperties() {
        
        Properties prop = new Properties();
        FileInputStream fis = null;

        try {
            prop.load(new FileInputStream("C:/Aplicaciones/Java/Eva/wildfly-10.1.0.Final/bin/eva.properties"));
           //prop.load(new FileInputStream("C:/Proyectos/wildfly-10.1.0.Final/bin/eva.properties"));
           // prop.load(new FileInputStream("C:/Aplicaciones/Java/wildfly-10.0.0.Final_test/bin/eva.properties"));
           
            
            // read property
            setUrl_servidorweb(prop.getProperty("URL_SERVIDORWEB"));
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
    
    public ManageProperties() {
        getProperties();
    }
}