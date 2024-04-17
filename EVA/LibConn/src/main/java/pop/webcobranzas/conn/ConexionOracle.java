/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.conn;

import java.sql.SQLException;
import java.util.ResourceBundle;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.pool.OracleDataSource;


/**
 *
 * @author Jyoverar
 */
public class ConexionOracle {
    
    
    //ileInputStream fis = new FileInputStream("src/main/resources/settings.properties");
    
    private OracleConnection oracleConnection = null;
    private static final ResourceBundle conectionProperties = ResourceBundle.getBundle("settings");
    private static final String driver = conectionProperties.getString("driver");
    private static final String url = conectionProperties.getString("url");
    private static final String driverType = conectionProperties.getString("driverType");
    private static final String networkProtocol = conectionProperties.getString("networkProtocol");
    private static final String databaseName = conectionProperties.getString("databaseName");
    private static final String serverName = conectionProperties.getString("serverName");
    private static final int portNumber = Integer.parseInt(conectionProperties.getString("portNumber"));
    private static final String user = conectionProperties.getString("user");
    private static final String password = conectionProperties.getString("password");   

    public ConexionOracle(){
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
    
    private OracleDataSource setOracleDataSourceProperty() throws SQLException{
        OracleDataSource ods = new OracleDataSource();
        
        ods.setDriverType(ConexionOracle.driverType);
        ods.setNetworkProtocol(ConexionOracle.networkProtocol);
        ods.setDatabaseName(ConexionOracle.databaseName);
        ods.setServerName(ConexionOracle.serverName);
        ods.setPortNumber(ConexionOracle.portNumber);
        ods.setUser(ConexionOracle.user);
        ods.setPassword(ConexionOracle.password);
              
        return ods;
    }
    
    private OracleDataSource setOracleDataSourceUrl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(ConexionOracle.url);
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
