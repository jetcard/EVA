package pop.webcobranzas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {

    public Connection conn;
    public Connection connMySQL;
    public static Connect db;
    public static String usuarioActivo;
    private Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
         
    
    public static synchronized Connection getDbCon(ObjConnection oObjConnection) {
        try {
            String url = "jdbc:postgresql://" + Useful.CargaConfiguracionEstatica.ObtenerClave("host") + ":" + Useful.CargaConfiguracionEstatica.ObtenerClave("port") + "/" + Useful.CargaConfiguracionEstatica.ObtenerClave("scope");
            Class.forName("org.postgresql.Driver");
            if (db == null) {
                db = new Connect();
            //    db.conn = (Connection) DriverManager.getConnection(url, Useful.CargaConfiguracionEstatica.ObtenerClave("username") , Encryption.Desencriptar(Useful.CargaConfiguracionEstatica.ObtenerClave("pwd")));
                
            }
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
        return db.conn;
    }

    public static synchronized Connection getDbConInitial() {
        Object temp = new Object();
        try {
            String url = "jdbc:postgresql://" + Useful.CargaConfiguracionEstatica.ObtenerClave("host") + ":" + Useful.CargaConfiguracionEstatica.ObtenerClave("port") + "/" + Useful.CargaConfiguracionEstatica.ObtenerClave("scope");
            System.out.println(url);
            Class.forName("org.postgresql.Driver");
            //if (db == null) {
            db = new Connect();
        } catch (Exception sqle) {
            sqle.printStackTrace();
        } finally {
            //db = null;
        }
        return (Connection) db.conn;//temp;
    }
 
    


  
   
  
    public static synchronized Connection getDbConInitial(String usuario) {
        Object temp = new Object();
        try {
            String url = "jdbc:postgresql://" + Useful.CargaConfiguracionEstatica.ObtenerClave("host") + ":" + Useful.CargaConfiguracionEstatica.ObtenerClave("port") + "/" + Useful.CargaConfiguracionEstatica.ObtenerClave("scope");
            System.out.println(url);
            Class.forName("org.postgresql.Driver");
            
            //if (db == null) {
            db = new Connect();
//            db.conn = (Connection) DriverManager.getConnection(url, usuario, Encryption.Desencriptar(Useful.CargaConfiguracionEstatica.ObtenerClave("pwd")));
            usuarioActivo = usuario;
            //}
        } catch (Exception sqle) {
            sqle.printStackTrace();
        } finally {
            //db = null;
        }
        return (Connection) db.conn;//temp;
    }

    
}
