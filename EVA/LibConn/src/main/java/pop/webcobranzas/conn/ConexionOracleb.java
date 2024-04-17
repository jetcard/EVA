/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.conn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import oracle.jdbc.OracleConnection;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

/**
 *
 * @author Jyoverar
 */
public class ConexionOracleb {
    
    private static final ResourceBundle conectionProperties = ResourceBundle.getBundle("settings");
    private static final String url = conectionProperties.getString("url");
    private static final String user = conectionProperties.getString("user");
    private static final String password = conectionProperties.getString("password");
    private OracleConnection oracleConnection = null;

    public ConexionOracleb() {
    }   
    
    public Connection getConnection() {
        oracleConnection = null;
        try {
            PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
            pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            pds.setURL(url);
            oracleConnection=(OracleConnection) pds.getConnection();
        } catch (SQLException e) {
            oracleConnection = null;
        }
        return oracleConnection;
    }
    
    public OracleConnection ConexionOpen() {
       oracleConnection = null;
        try {
            PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
            pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            pds.setURL(url);
            pds.setUser(ConexionOracleb.user);
            pds.setPassword(ConexionOracleb.password);
            
            oracleConnection=(OracleConnection) pds.getConnection();
        } catch (SQLException e) {
            oracleConnection = null;
        }
        return oracleConnection;
    }
    
    
    
    
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

    public OracleConnection getOracleConnection() {
        return oracleConnection;
    }

    public void setOracleConnection(OracleConnection oracleConnection) {
        this.oracleConnection = oracleConnection;
    }

   
   
    
  
}
