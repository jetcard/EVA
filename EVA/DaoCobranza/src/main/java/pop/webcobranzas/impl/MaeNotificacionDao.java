/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.MaeNotificacion;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeNotificacionDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeNotificacionDao extends DBUtil implements IMaeNotificacionDao {
    
    private OracleConnection cn = null;

    public MaeNotificacionDao() {

    }

    public MaeNotificacionDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeNotificacion oMaeNotificacion) {
        //System.out.println(" <i> MaeNotificacionDao Insertar " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_NOTIFICACION.SP_INS_MAE_NOTIFICACION(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insParameters(oMaeNotificacion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            newID = getOutputParameter("PO_C_MAE_NOTIFICATION_ID").getParameterInt();

        } catch (SQLException e) {
            Logger.getLogger(MaeNotificacionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(MaeNotificacionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        //System.out.println(" <f> MaeNotificacionDao Insertar " + LocalDateTime.now());
        return newID;
    }

    @Override
    public void update(MaeNotificacion oMaeNotificacion) {
        //System.out.println(" <i> MaeNotificacionDao update " + LocalDateTime.now());
       OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_NOTIFICACION.SP_UPD_MAE_NOTIFICACION(?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updParameters(oMaeNotificacion);            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, this.getCn(), cmd, sp);

            //newID =  getOutputParameter("PO_NEW_ID").getParameterInt();
        } catch (SQLException e) {
            Logger.getLogger(MaeNotificacionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(MaeNotificacionDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeNotificacionDao update " + LocalDateTime.now());
    }

    @Override
    public void delete(MaeNotificacion oMaeNotificacion) {
        //System.out.println(" <i> MaeNotificacionDao delete " + LocalDateTime.now());
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_NOTIFICACION.SP_DEL_MAE_NOTIFICACION(?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = delParameters(oMaeNotificacion);
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
        //System.out.println(" <f> MaeNotificacionDao delete " + LocalDateTime.now());
    }

    @Override
    public ArrayList<MaeNotificacion> fetchAll(MaeNotificacion oMaeNotificacion) {
        //System.out.println(" <i> MaeNotificacionDao fetch all " + LocalDateTime.now());
        ArrayList<MaeNotificacion> lstNotificacion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_NOTIFICACION.SP_BUS_MAE_NOTIFICACION(?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaeNotificacion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                //persona
                MaeNotificacion newMaeSesion = new MaeNotificacion();
                newMaeSesion.setCmaeNotificacionId(resultSet.getInt("C_MAE_NOTIFICATION_ID"));
                newMaeSesion.setCusuarioDeId(resultSet.getString("C_USUARIO_DE_ID"));
                newMaeSesion.setCusuarioPaId(resultSet.getString("C_USUARIO_PA_ID"));
                newMaeSesion.setCtipoId(resultSet.getString("C_TIPO_ID"));
                newMaeSesion.setFnotificacion(resultSet.getDate("F_NOTIFICACION"));
                newMaeSesion.setDtitulo(resultSet.getString("D_TITULO"));
                newMaeSesion.setDcuerpo(resultSet.getString("D_CUERPO"));
                newMaeSesion.setBenviado(resultSet.getString("B_ENVIADO"));
                newMaeSesion.setBrecibido(resultSet.getString("B_RECIBIDO"));
                newMaeSesion.seteEstado(resultSet.getString("E_ESTADO"));
                newMaeSesion.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));

                newMaeSesion.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newMaeSesion.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                newMaeSesion.setfUsuarioMod(resultSet.getDate("F_USUARIO_MOD"));

                lstNotificacion.add(newMaeSesion);
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
        //System.out.println(" <f> MaeNotificacionDao fetch all " + LocalDateTime.now());
        return lstNotificacion;
    }

    @Override
    public ArrayList<MaeNotificacion> fetchAllMsj(MaeNotificacion oMaeNotificacion) {
        //System.out.println(" <i> MaeNotificacionDao fetch all msj " + LocalDateTime.now());
        ArrayList<MaeNotificacion> lstNotificacion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_NOTIFICACION.SP_BUS_ALL_MAE_NOTIFICACION(?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersMsj(oMaeNotificacion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                //persona
                MaeNotificacion newMaeSesion = new MaeNotificacion();
                newMaeSesion.setCmaeNotificacionId(resultSet.getInt("C_MAE_NOTIFICATION_ID"));
                newMaeSesion.setCusuarioDeId(resultSet.getString("C_USUARIO_DE_ID"));
                newMaeSesion.setCusuarioPaId(resultSet.getString("C_USUARIO_PA_ID"));
                newMaeSesion.setCtipoId(resultSet.getString("C_TIPO_ID"));
                newMaeSesion.setFnotificacion(resultSet.getDate("F_NOTIFICACION"));
                newMaeSesion.setDtitulo(resultSet.getString("D_TITULO"));
                newMaeSesion.setDcuerpo(resultSet.getString("D_CUERPO"));
                newMaeSesion.setBenviado(resultSet.getString("B_ENVIADO"));
                newMaeSesion.setBrecibido(resultSet.getString("B_RECIBIDO"));
                newMaeSesion.seteEstado(resultSet.getString("E_ESTADO"));
                
                newMaeSesion.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newMaeSesion.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newMaeSesion.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                newMaeSesion.setfUsuarioMod(resultSet.getDate("F_USUARIO_MOD"));
                
                newMaeSesion.setNcantNotificacion(resultSet.getInt("N_CANT_NOTIFICACION"));

                lstNotificacion.add(newMaeSesion);
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
        //System.out.println(" <f> MaeNotificacionDao fetch all msj " + LocalDateTime.now());
        return lstNotificacion;
    }

    private List<ParameterOracle> insParameters(MaeNotificacion oMaeNotificacion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_USUARIO_DE_ID", oMaeNotificacion.getCusuarioDeId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_PA_ID", oMaeNotificacion.getCusuarioPaId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_ID", oMaeNotificacion.getCtipoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_NOTIFICACION", oMaeNotificacion.getFnotificacion(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_TITULO", oMaeNotificacion.getDtitulo(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_CUERPO", oMaeNotificacion.getDcuerpo(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_ENVIADO", oMaeNotificacion.getBenviado(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_RECIBIDO", oMaeNotificacion.getBrecibido(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeNotificacion.geteEstado(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaeNotificacion.getcUsuarioAdd(), OracleTypes.VARCHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_C_MAE_NOTIFICATION_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> delParameters(MaeNotificacion oMaeNotificacion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_NOTIFICATION_ID", oMaeNotificacion.getCmaeNotificacionId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oMaeNotificacion.getcUsuarioMod(), OracleTypes.VARCHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> updParameters(MaeNotificacion oMaeNotificacion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_NOTIFICATION_ID", oMaeNotificacion.getCmaeNotificacionId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_ENVIADO", oMaeNotificacion.getBenviado(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_RECIBIDO", oMaeNotificacion.getBrecibido(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oMaeNotificacion.getcUsuarioMod(), OracleTypes.VARCHAR, ParameterDirection.Input));      
        
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParameters(MaeNotificacion oMaeNotificacion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_USUARIO_DE_ID", oMaeNotificacion.getCusuarioDeId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_PA_ID", oMaeNotificacion.getCusuarioPaId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_ID", oMaeNotificacion.getCtipoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_NOTIFICACION", oMaeNotificacion.getFnotificacion(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_TITULO", oMaeNotificacion.getDtitulo(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_CUERPO", oMaeNotificacion.getDcuerpo(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_ENVIADO", oMaeNotificacion.getBenviado(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_RECIBIDO", oMaeNotificacion.getBrecibido(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeNotificacion.geteEstado(), OracleTypes.VARCHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParametersMsj(MaeNotificacion oMaeNotificacion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_F_BUSQUEDA", oMaeNotificacion.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        
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
