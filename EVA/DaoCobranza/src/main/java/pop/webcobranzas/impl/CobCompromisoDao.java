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
import pop.comun.dominio.CobCompromiso;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobSeguimiento;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ICobCompromisoDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;

/**
 *
 * @author Jyoverar
 */
public class CobCompromisoDao extends DBUtil implements ICobCompromisoDao {

    private OracleConnection cn = null;

    public CobCompromisoDao() {

    }

    public CobCompromisoDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(CobCompromiso oCobCompromiso) {
        //System.out.println(" <i> insertar compromiso " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_COMPROMISO.SP_INS_COB_COMPROMISO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // fill parameters
            oLis = insertParameters(oCobCompromiso);
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PI_C_COB_COMPROMISO_ID").getParameterInt();

        } catch (SQLException e) {
            Logger.getLogger(LaftPersonaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(LaftPersonaDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> insertar compromiso " + LocalDateTime.now());
        return newID;
    }

    @Override
    public void update(CobCompromiso oCobCompromiso) {
        //System.out.println(" <i> actualizar compromiso " + LocalDateTime.now());
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_COMPROMISO.SP_UPD_COB_COMPROMISO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters(oCobCompromiso);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

        } catch (SQLException e) {
            Logger.getLogger(CobCompromisoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(CobCompromisoDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> insertar compromiso " + LocalDateTime.now());
    }

    @Override
    public void delete(CobCompromiso oCobCompromiso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CobCompromiso> fetchAll(CobCompromiso oCobCompromiso) {
        //System.out.println(" <i> CobCompromisoDao fetchAll " + LocalDateTime.now());
        ArrayList<CobCompromiso> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_COMPROMISO.SP_BUS_COB_COMPROMISO(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus(oCobCompromiso);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // fondo
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                fondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion inversion = new MaeInversion();
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setCInversion(resultSet.getString("C_INVERSION"));
                inversion.setMaeFondo(fondo);
                // mae seguimiento
                CobMaeSeguimiento cms = new CobMaeSeguimiento();
                cms.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                cms.setMaeInversion(inversion);
                // cob seguimiento
                CobSeguimiento cs = new CobSeguimiento();
                cs.setCcobSeguimientoId(resultSet.getInt("C_COB_SEGUIMIENTO_ID"));
                cs.setCobMaeSeguimiento(cms);
                // compromiso
                CobCompromiso cp = new CobCompromiso();
                cp.setCcodCompromisoId(resultSet.getInt("C_COB_COMPROMISO_ID"));
                cp.setCcodCompromiso(resultSet.getString("C_COB_COMPROMISO"));
                cp.setFfecha(resultSet.getDate("F_FECHA"));
                cp.setImonto(resultSet.getDouble("I_MONTO"));
                cp.setDcuota(resultSet.getString("D_CUOTA"));

                cp.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                cp.setFfecObs(resultSet.getDate("F_FECHA_OBS"));
                cp.setDobservacion(resultSet.getString("D_OBSERVACION"));
                cp.setDrespuesta(resultSet.getString("D_RESPUESTA"));
                cp.seteEstado(resultSet.getString("E_ESTADO"));
                cp.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cp.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                cp.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                cp.setfUsuarioMod(resultSet.getDate("F_USUARIO_MOD"));
                cp.setCobSeguimiento(cs);

                lstCompromiso.add(cp);

            }

        } catch (Exception e) {
            Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <i> CobCompromisoDao fetchAll " + LocalDateTime.now());
        return lstCompromiso;
    }

    @Override
    public ArrayList<CobCompromiso> fetchDesposit(CobCompromiso oCobCompromiso) {
        //System.out.println(" <i> CobCompromisoDao fetchAll " + LocalDateTime.now());
        ArrayList<CobCompromiso> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_COMPROMISO.SP_BUS_COB_COMPRO_DEPOSIT(?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busDep(oCobCompromiso);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // fondo
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                fondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion inversion = new MaeInversion();
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setCInversion(resultSet.getString("C_INVERSION"));
                inversion.setMaeFondo(fondo);
                // mae seguimiento
                CobMaeSeguimiento cms = new CobMaeSeguimiento();
                cms.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                cms.setMaeInversion(inversion);
                // cob seguimiento
                CobSeguimiento cs = new CobSeguimiento();
                cs.setCcobSeguimientoId(resultSet.getInt("C_COB_SEGUIMIENTO_ID"));
                cs.setCobMaeSeguimiento(cms);
                // compromiso
                CobCompromiso cp = new CobCompromiso();
                cp.setCcodCompromisoId(resultSet.getInt("C_COB_COMPROMISO_ID"));
                cp.setCcodCompromiso(resultSet.getString("C_COB_COMPROMISO"));
                cp.setFfecha(resultSet.getDate("F_FECHA"));
                cp.setImonto(resultSet.getDouble("I_MONTO"));
                cp.setDcuota(resultSet.getString("D_CUOTA"));

                cp.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                cp.setFfecObs(resultSet.getDate("F_FECHA_OBS"));
                cp.setDobservacion(resultSet.getString("D_OBSERVACION"));
                cp.setDrespuesta(resultSet.getString("D_RESPUESTA"));
                cp.seteEstado(resultSet.getString("E_ESTADO"));
                cp.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cp.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                cp.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                cp.setfUsuarioMod(resultSet.getDate("F_USUARIO_MOD"));
                cp.setImontoDepostado(resultSet.getDouble("I_BCO_DEPOSITADO"));
                cp.setCobSeguimiento(cs);
                lstCompromiso.add(cp);
            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
            }

        } catch (Exception e) {
            Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <i> CobCompromisoDao fetchAll " + LocalDateTime.now());
        return lstCompromiso;
    }

    @Override
    public CobCompromiso fetchCall(CobCompromiso oCobCompromiso) {
        //System.out.println(" <i> CobCompromisoDao fetchCall " + LocalDateTime.now());
        CobCompromiso cp = new CobCompromiso();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_COMPROMISO.SP_BUS_COB_COMPROMISO_LLA(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busCall(oCobCompromiso);
             //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                // compromiso
                cp.setCcodCompromisoId(resultSet.getInt("C_COB_COMPROMISO_ID"));
                cp.setCcodCompromiso(resultSet.getString("C_COB_COMPROMISO"));
                cp.setFfecha(resultSet.getDate("F_FECHA"));
                cp.setImonto(resultSet.getDouble("I_MONTO"));
                cp.setDcuota(resultSet.getString("D_CUOTA"));

                cp.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                cp.setFfecObs(resultSet.getDate("F_FECHA_OBS"));
                cp.setDobservacion(resultSet.getString("D_OBSERVACION"));
                cp.setDrespuesta(resultSet.getString("D_RESPUESTA"));
                cp.seteEstado(resultSet.getString("E_ESTADO"));
                cp.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cp.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                cp.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                cp.setfUsuarioMod(resultSet.getDate("F_USUARIO_MOD"));

            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
            }

        } catch (Exception e) {
            Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <i> CobCompromisoDao fetchCall " + LocalDateTime.now());
        return cp;

    }

    private List<ParameterOracle> insertParameters(CobCompromiso oCobCompromiso) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_COB_SEGUIMIENTO_ID", oCobCompromiso.getCobSeguimiento().getCcobSeguimientoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_COB_COMPROMISO", oCobCompromiso.getCcodCompromiso(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_FECHA", oCobCompromiso.getFfecha(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_MONTO", oCobCompromiso.getImonto(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_CUOTA", oCobCompromiso.getDcuota(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oCobCompromiso.getEestadoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_FECHA_OBS", oCobCompromiso.getFfecObs(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_OBSERVACION", oCobCompromiso.getDobservacion(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PI_C_TIPO_NEXO_ID", oCobCompromiso.getCtipoNexoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_NEXO_ID", oCobCompromiso.getCnexoId(), OracleTypes.NUMBER, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobCompromiso.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PI_C_COB_COMPROMISO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> updateParameters(CobCompromiso oCobCompromiso) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_COB_COMPROMISO_ID", oCobCompromiso.getCcodCompromisoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_COB_SEGUIMIENTO_ID", oCobCompromiso.getCobSeguimiento().getCcobSeguimientoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_COB_COMPROMISO", oCobCompromiso.getCcodCompromiso(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_FECHA", oCobCompromiso.getFfecha(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_MONTO", oCobCompromiso.getImonto(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_CUOTA", oCobCompromiso.getDcuota(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oCobCompromiso.getEestadoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_FECHA_OBS", oCobCompromiso.getFfecObs(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_OBSERVACION", oCobCompromiso.getDobservacion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_RESPUESTA", oCobCompromiso.getDrespuesta(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oCobCompromiso.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oCobCompromiso.getcUsuarioMod(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_bus(CobCompromiso oCobCompromiso) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_FINICIO", oCobCompromiso.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FFIN", oCobCompromiso.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobCompromiso.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_busDep(CobCompromiso oCobCompromiso) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_FINICIO", oCobCompromiso.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FFIN", oCobCompromiso.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FINICIO_C", oCobCompromiso.getFiniBusqFecEje(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FFIN_C", oCobCompromiso.getFfinBusqFecEje(), OracleTypes.DATE, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobCompromiso.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobCompromiso.getCobSeguimiento().getCobMaeSeguimiento().getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_busCall(CobCompromiso oCobCompromiso) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_TIPO_NEXO_ID", oCobCompromiso.getCtipoNexoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_NEXO_ID", oCobCompromiso.getCnexoId(), OracleTypes.NUMBER, ParameterDirection.Input));

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
