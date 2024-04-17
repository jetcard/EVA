/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.conn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.pool.OracleDataSource;


/**
 *
 * @author Jyoverar
 */
public class Conexion {
    
    
    //ileInputStream fis = new FileInputStream("src/main/resources/settings.properties");
    
    private OracleConnection oracleConnection = null;
    private static String driver = "";
    private static String url = "";
    private static String driverType = "";
    private static String databaseName = "";
    private static String serverName = "";
    private static String portNumber = "";
    private static String user = "";
    private static String password = "";
    
    public Conexion()  throws FileNotFoundException, IOException{
      try 
      {
        Properties propiedades = new Properties();
        propiedades.load(new FileInputStream("C:/Aplicaciones/Java/Eva/wildfly-10.1.0.Final/bin/eva.properties"));
        //propiedades.load(new FileInputStream("C:/Proyectos/wildfly-10.1.0.Final/bin/eva.properties"));
        //propiedades.load(new FileInputStream("C:/Aplicaciones/Java/wildfly-10.0.0.Final_test/bin/eva.properties"));
        driver = propiedades.getProperty("driver");
        driverType= propiedades.getProperty("driverType");
        user = propiedades.getProperty("user");
        Seguridad sec = new Seguridad();
        sec.addKey("37WF234HFHKJHFKSHDFSHDFK328Y294FKFHKHFWHEW734294YDNKFWSK4454r4234regerljwlrwlrlwjro34u2ofsflksj");
        password=sec.desencriptar(propiedades.getProperty("password"));
        serverName=propiedades.getProperty("serverName");
        portNumber=propiedades.getProperty("portNumber");
        databaseName=propiedades.getProperty("databaseName");
       
        url=driver + ":" + driverType + ":" + user + "/" + password + "@" + serverName + ":" + portNumber + ":" + databaseName;
        System.out.println("esta es la conexcion" + url);
      } 
      catch (FileNotFoundException e) {
            throw e;
      } 
      catch (IOException e) {
            throw e;
      }
    }
    
    public OracleConnection getOracleConnection() {
        return oracleConnection;
    }
    
    public void setOracleConnection(OracleConnection aOracleConnection) {
        oracleConnection = aOracleConnection;
    }
  
    /// <summary>
    /// Test de conexion
    /// </summary>
    /// <returns>true = conexion    false = sin conexion</returns>
    public boolean IsConnectionAlive() throws ClassNotFoundException, SQLException{
        boolean status = false;
        OracleDataSource ods = null;
        OracleConnection ocon = null;
        OracleStatement stmt = null;
        OracleResultSet rs = null;
        
        try {
            ods = this.setOracleDataSourceUrl();
            // open the connection to the database
            ocon = (OracleConnection)ods.getConnection();            
            stmt = (OracleStatement) ocon.createStatement();
            rs = (OracleResultSet) stmt.executeQuery("SELECT C_USUARIO_ID, D_NOMBRES, E_ESTADO  FROM COB_USUARIO WHERE C_USUARIO_ID = 'MOTOYA'");    
            while (rs.next()) {
                System.out.print(rs.getString(1) + "|");
                System.out.print(rs.getString(2) + "|");
                System.out.println(rs.getString(3) + "|");
            }
            
            status = true;
            // close the connection the database and the close the datasource
            rs.close();
            stmt.close();
            ocon.close();
            ods.close();        
            
        } catch (SQLException e) {
            throw e;
        }        
        
        return status;
    }
    
    private OracleDataSource setOracleDataSourceUrl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(Conexion.url);
        return ods;
    }


    /// <summary>
    /// Abrir y devuelve un conexion
    /// </summary>    
    public OracleConnection ConexionOpen() throws SQLException{
        OracleConnection cn;
        try {
            cn = (OracleConnection) this.setOracleDataSourceUrl().getConnection();
            //oracleConnection.setAutoClose(true);
            //cn.setAutoClose(true);
            //System.out.println("Estado de conexion : " + !cn.isClosed());
            
        } catch (SQLException e) {
            if (oracleConnection != null)
                oracleConnection.close();
            throw e;
        }
        return cn;
    }
    
    
//    /// <summary>
//    /// Abrir una conexion
//    /// </summary>    
//    public void ConexionOpen() throws SQLException{        
//        try {
//            oracleConnection = (OracleConnection) this.setOracleDataSourceUrl().getConnection();
//            //oracleConnection.setAutoClose(true);            
//            System.out.println("Estado de conexion : " + !oracleConnection.isClosed());
//            
//        } catch (SQLException e) {
//            if (oracleConnection != null)
//                oracleConnection.close();
//            throw e;
//        }
//    }
    
    /// <summary>
    /// Cerrar una conexion
    /// </summary>
    public void ConexionClose() throws SQLException {
        try {
            if (oracleConnection != null && !oracleConnection.isClosed()) {
                oracleConnection.close();                
                //System.err.println("Estado de conexion : " + !oracleConnection.isClosed());
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    
    /// <summary>
    /// Agregar una transaccion a la conexion
    /// </summary>
    public void AddTransaction() throws SQLException {
        try {
            // Disable auto-commit mode -> http://www.informit.com/articles/article.aspx?p=26251&seqNum=4
            oracleConnection.setAutoCommit(false);
        } catch (SQLException e) {
            throw e;
        }
    }
    
    /// <summary>
    //  Guardar los cambios de la transaccion
    /// </summary>
    public void SaveChanges() throws SQLException{
        try {
            oracleConnection.commit();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    /// <summary>
    /// Cancelar los cambios de la transaccion
    /// </summary>
    public void CancelChanges() throws SQLException{
        try {
            oracleConnection.rollback();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    //terminar el objeto creado
    public void Dispose() throws SQLException {
        try {
            if (oracleConnection != null && !oracleConnection.isClosed()) {
                oracleConnection.close();
            }
            
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
