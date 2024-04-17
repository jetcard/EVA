/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pop.webcobranzas.conn.ConexionMySql;
import pop.webcobranzas.iface.IConexionDao;

/**
 *
 * @author Jyoverar
 */
public class ConexionDaoMySql implements IConexionDao {
    
    private static volatile ConexionDaoMySql instance;        
    // instancia a la seaConn
    private ConexionMySql dllCnxMySql = null;
    // conector Mysql
    private static Connection mySqlConnection;

    // singleton
    public static ConexionDaoMySql Instance() {
        if (instance == null ){
            instance = new ConexionDaoMySql();
        }        
        return instance;
    }
    
    public ConexionDaoMySql() {
        dllCnxMySql= new ConexionMySql();
    }
           
     // obtener el objeto OracleConnection
    public static Connection getMySqlConnection(){
        return ConexionMySql.getConnectionStatic();
    }
    
    @Override
    public void ConexionOpen() {      
        mySqlConnection = dllCnxMySql.getConnection();
    }

    @Override
    public void ConexionClose() {
        try {
            //System.err.println("Estado de conexion1 : " + dllCnxMySql.getConnection().isClosed());
            //dllCnxMySql.getConnection().close();
            //System.err.println("Estado de conexion2 : " + mySqlConnection.isClosed());
            mySqlConnection.close();
            //System.err.println("Estado de conexion3 : " + mySqlConnection.isClosed());
            
        } catch (SQLException ex) {
            //System.err.println("ConexionMySql.ConexionClose : " + ex.getMessage());
            Logger.getLogger(ConexionDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void AddTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SaveChanges() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void CancelChanges() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
