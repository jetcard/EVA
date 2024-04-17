/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleConnection;
import pop.webcobranzas.conn.ConexionOracle;
import pop.webcobranzas.iface.IConexionDao;

/**
 *
 * @author Jyoverar
 */
public class ConexionOracleDao implements IConexionDao{
    
    private static volatile ConexionOracleDao instance;        
    // instancia a la seaConn
    private ConexionOracle dllCnxOracle = null;
    // conector Oracle
    private static OracleConnection oracleConnection;    
    
    // singleton
    public static ConexionOracleDao Instance() {
        if (instance == null ){
            instance = new ConexionOracleDao();
        }        
        return instance;
    }
    
    // constructor
    private ConexionOracleDao() {
        dllCnxOracle = new ConexionOracle();        
    }

     // obtener el objeto OracleConnection
    public static OracleConnection getOracleConnection(){
        return oracleConnection;
    }
        
    /// <summary>
    /// abrir la conexion de la dll 
    /// </summary>
    @Override
    public void ConexionOpen() {
        try {
            oracleConnection = dllCnxOracle.ConexionOpen();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /// <summary>
    /// Cerrar la conexion oracle
    /// </summary>    
    @Override
    public void ConexionClose() {
        try {
            dllCnxOracle.ConexionClose();
            oracleConnection.close();
            dllCnxOracle.ConexionClose();
            //System.err.println("Estado de conexion ORACLE ----------- : " + !oracleConnection.isClosed());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// <summary>
    /// agregar una transaccion a la conexion
    /// </summary>    
    @Override
    public void AddTransaction() {
        try {
            //dllCnxOracle.AddTransaction();            
            oracleConnection.setAutoCommit(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// <summary>
    /// guardar los cambios de la transaccion
    /// </summary>  
    @Override
    public void SaveChanges() {
        try {
            //dllCnxOracle.SaveChanges();
            oracleConnection.commit();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// <summary>
    /// cancelar los cambios de la transaccion
    /// </summary> 
    @Override
    public void CancelChanges() {
        try {
            //dllCnxOracle.CancelChanges();
            oracleConnection.rollback();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // terminar el objeto creado
    private void Dispose() throws SQLException{
        oracleConnection.close();
        //dllCnxOracle.Dispose();
    }
    
}
