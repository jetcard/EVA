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
import pop.comun.dominio.CobSeguimiento;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.iface.ICobSeguimientoDao;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class CobSeguimientoDao extends DBUtil implements ICobSeguimientoDao {

    private OracleConnection cn = null;

    public CobSeguimientoDao() {
    }

    public CobSeguimientoDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(CobSeguimiento oCobSeguimiento) {
        //System.out.println(" <i> insertar CobSeguimientoDao " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_SEGUIMIENTO.SP_INS_COB_SEGUIMIENTO(?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oCobSeguimiento);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            newID = getOutputParameter("PO_C_COB_SEGUIMIENTO_ID").getParameterInt();

        } catch (SQLException e) {
            Logger.getLogger(CobSeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(CobSeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        }             
        //System.out.println(" <f> insertar CobSeguimientoDao " + LocalDateTime.now());
        return newID;
    }

    @Override
    public void update(CobSeguimiento oCobSeguimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CobSeguimiento oCobSeguimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CobSeguimiento fetch(CobSeguimiento oCobSeguimiento) {
        //System.out.println(" <i> CobSeguimiento fetch " + LocalDateTime.now());
        CobSeguimiento cobSeguimiento = new CobSeguimiento();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_SEGUIMIENTO.SP_BUS_COB_SEGUIMIENTO(?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersFetch(oCobSeguimiento);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // cobMaeSeguimiento
                cobSeguimiento.setCcobSeguimientoId(resultSet.getInt("C_COB_SEGUIMIENTO_ID"));
                CobMaeSeguimiento oCobMaeSeguimiento = new CobMaeSeguimiento();
                oCobMaeSeguimiento.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                cobSeguimiento.setFfecIni(resultSet.getDate("F_FEC_INI"));
                cobSeguimiento.setFfecFin(resultSet.getDate("F_FEC_FIN"));
                cobSeguimiento.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                cobSeguimiento.seteEstado(resultSet.getString("E_ESTADO"));
                cobSeguimiento.setCobMaeSeguimiento(oCobMaeSeguimiento);
            }
            ////System.out.println(LocalDateTime.now());
        } catch (Exception e) {
            Logger.getLogger(MaeDireccionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (resultSet != null) {
              try { resultSet.close(); } catch (SQLException e) { ; }
              resultSet = null;
            }
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        }       
        //System.out.println(" <f> CobSeguimiento fetch " + LocalDateTime.now());
        return cobSeguimiento;
    }

    @Override
    public ArrayList<CobSeguimiento> fetchAll(CobSeguimiento oCobSeguimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<ParameterOracle> insertParameters(CobSeguimiento oCobSeguimiento) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_SEGUIMIENTO_ID",oCobSeguimiento.getCobMaeSeguimiento().getCmaeSeguimientoId() , OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_FEC_INI", oCobSeguimiento.getFfecIni(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_FEC_FIN", oCobSeguimiento.getFfecFin(), OracleTypes.DATE, ParameterDirection.Input));
         oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oCobSeguimiento.getEestadoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobSeguimiento.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_C_COB_SEGUIMIENTO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

     private List<ParameterOracle> listParametersFetch(CobSeguimiento oCobSeguimiento) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_COB_SEGUIMIENTO_ID", oCobSeguimiento.getCcobSeguimientoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_SEGUIMIENTO_ID", oCobSeguimiento.getCobMaeSeguimiento().getCmaeSeguimientoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_FEC_INI", oCobSeguimiento.getFfecIni(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_FEC_FIN", oCobSeguimiento.getFfecFin(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oCobSeguimiento.getEestadoId(), OracleTypes.CHAR, ParameterDirection.Input));
                
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
