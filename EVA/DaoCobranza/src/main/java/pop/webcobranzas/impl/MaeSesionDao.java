/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.MaeSesion;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeSesionDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeSesionDao extends DBUtil implements IMaeSesionDao{
    
    private OracleConnection cn = null;

    public MaeSesionDao() {

    }

    public MaeSesionDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeSesion oMaeSesion) {
        Integer newID = 0;
        OracleCallableStatement cmd = null;
        
        try {                        
            // name of procedure
            String sp = "{call PKG_MAE_SESION.SP_INS_MAESESION(?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insdelParameters(oMaeSesion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            //newID =  getOutputParameter("PO_NEW_ID").getParameterInt();
                    
        } catch (SQLException e) {            
            Logger.getLogger(MaeSesionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {            
            Logger.getLogger(MaeSesionDao.class.getName()).log(Level.SEVERE, null, e);
        }        
        finally 
        {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool            
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }        
        return newID;
    }

    @Override
    public void update(MaeSesion oMaeSesion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaeSesion oMaeSesion) {
        
        OracleCallableStatement cmd = null;
        
        try {                        
            // name of procedure
            String sp = "{call PKG_MAE_SESION.SP_DEL_MAESESION(?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insdelParameters(oMaeSesion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
                    
        } catch (SQLException e) {            
            Logger.getLogger(MaeSesionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {            
            Logger.getLogger(MaeSesionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool            
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }        
    }

    @Override
    public ArrayList<MaeSesion> fetchAll(MaeSesion oMaeSesion) {
        ArrayList<MaeSesion> lstSesion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_SESION.SP_BUS_MAESESION(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaeSesion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
                //persona
                MaeSesion newMaeSesion = new MaeSesion();
                newMaeSesion.setCsesionId(resultSet.getString("C_SESSION_ID"));
                newMaeSesion.setCsesionId(resultSet.getString("C_USUARIO_ID"));
                lstSesion.add(newMaeSesion);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeSesionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        return lstSesion;
    }
 
    private List<ParameterOracle> insdelParameters(MaeSesion oMaeSesion){
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        
        oListParam.add(new ParameterOracle("PI_C_SESSION_ID", oMaeSesion.getCsesionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeSesion.getCusuarioId(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
                
        return oListParam;
    }
    
    private List<ParameterOracle> listParameters(MaeSesion oMaeSesion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_SESSION_ID", oMaeSesion.getCsesionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeSesion.getCusuarioId(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    } 

    public OracleConnection getCn() {
        return cn;
    }

    public void setCn(OracleConnection cn) {
        this.cn = cn;
    }
}
