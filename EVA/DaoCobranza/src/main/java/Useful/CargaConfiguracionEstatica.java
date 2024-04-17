package Useful;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CargaConfiguracionEstatica {

   public static String ObtenerClave(String clave) {
      try {
         CargaConfiguracion oCargaConfiguracion = new CargaConfiguracion();

         return oCargaConfiguracion.ObtenerPropiedad(clave);
      } catch (Exception ex) {
         return null;
      }

   }

}

class CargaConfiguracion {

   public String ObtenerPropiedad(String clave) {
         Properties prop = new Properties();
        FileInputStream fis = null;

        try {
           // prop.load(new FileInputStream("C:/Aplicaciones/Java/Eva/wildfly-10.1.0.Final/bin/eva.properties"));
            prop.load(new FileInputStream("C:/Proyectos/wildfly-10.1.0.Final/bin/Config.properties"));
            //prop.load(new FileInputStream("C:/Aplicaciones/Java/wildfly-10.0.0.Final_test/bin/eva.properties"));
            // read property
           prop.getProperty(clave);
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
          return prop.getProperty(clave);
   
   }
}
