/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.CobCronogramaMetaAsesor;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ICobCronogramaMetaAsesorDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author HH38092
 */
public class CobCronogramaMetaAsesorDao extends DBUtil implements ICobCronogramaMetaAsesorDao {
    private OracleConnection cn = null;

    public CobCronogramaMetaAsesorDao() {

    }

    public CobCronogramaMetaAsesorDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) {
        //System.out.println(" <i> insertar compromiso " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call yyyyyyyyy.xxxxx(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oCobCronogramaMetaAsesor);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            newID = getOutputParameter("PI_C_COB_COMPROMISO_ID").getParameterInt();

        } catch (SQLException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
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
    public void update(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) {
        //System.out.println(" <i> actualizar compromiso " + LocalDateTime.now());
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call yyyyyyyyyy.xxxxxxxxxx(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters(oCobCronogramaMetaAsesor);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

        } catch (SQLException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
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
    public void delete(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CobCronogramaMetaAsesor> fetchAll(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) {
        System.out.println("CobCronogramaMetaAsesorDao: fetchAll()");
        ArrayList<CobCronogramaMetaAsesor> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_BUS_CRONOGRAMA_META_ASESOR(?,?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus(oCobCronogramaMetaAsesor);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                CobCronogramaMetaAsesor cp = new CobCronogramaMetaAsesor();
                cp.setN_anio(resultSet.getString("n_anio"));
                cp.setN_mes(resultSet.getString("n_mes"));
                cp.setC_fondo_id(resultSet.getString("c_fondo_id"));
                cp.setC_usuario_id(resultSet.getString("c_usuario_id"));
                cp.setN_quincena(resultSet.getString("n_quincena"));
                cp.setF_inicio(resultSet.getDate("f_inicio"));
                cp.setF_fin(resultSet.getDate("f_fin"));
                cp.setE_estado(resultSet.getString("e_estado"));
                
                cp.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cp.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                cp.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                cp.setfUsuarioMod(resultSet.getDate("F_USUARIO_MOD"));

                lstCompromiso.add(cp);

            }

        } catch (Exception e) {
            System.out.println(e);
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
    public CobCronogramaMetaAsesor fetchCall(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) {
        //System.out.println(" <i> CobCompromisoDao fetchCall " + LocalDateTime.now());
        CobCronogramaMetaAsesor cp = new CobCronogramaMetaAsesor();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call yyyyyyy.xxxxxx(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busCall(oCobCronogramaMetaAsesor);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                // compromiso
//                cp.setCcodCompromisoId(resultSet.getInt("C_COB_COMPROMISO_ID"));
//                cp.setCcodCompromiso(resultSet.getString("C_COB_COMPROMISO"));
//                cp.setFfecha(resultSet.getDate("F_FECHA"));
//                cp.setImonto(resultSet.getDouble("I_MONTO"));
//                cp.setDcuota(resultSet.getString("D_CUOTA"));
//
//                cp.setEestadoId(resultSet.getString("E_ESTADO_ID"));
//                cp.setFfecObs(resultSet.getDate("F_FECHA_OBS"));
//                cp.setDobservacion(resultSet.getString("D_OBSERVACION"));
//                cp.setDrespuesta(resultSet.getString("D_RESPUESTA"));
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
            System.out.println(e);
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

    private List<ParameterOracle> insertParameters(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) {
        List<ParameterOracle> oListParam = new ArrayList<>();

//        oListParam.add(new ParameterOracle("PI_C_COB_SEGUIMIENTO_ID", oCobCompromiso.getCobSeguimiento().getCcobSeguimientoId(), OracleTypes.NUMBER, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_C_COB_COMPROMISO", oCobCompromiso.getCcodCompromiso(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_F_FECHA", oCobCompromiso.getFfecha(), OracleTypes.DATE, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_I_MONTO", oCobCompromiso.getImonto(), OracleTypes.DOUBLE, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_D_CUOTA", oCobCompromiso.getDcuota(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oCobCompromiso.getEestadoId(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_F_FECHA_OBS", oCobCompromiso.getFfecObs(), OracleTypes.DATE, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_D_OBSERVACION", oCobCompromiso.getDobservacion(), OracleTypes.CHAR, ParameterDirection.Input));
//
//        oListParam.add(new ParameterOracle("PI_C_TIPO_NEXO_ID", oCobCompromiso.getCtipoNexoId(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_C_NEXO_ID", oCobCompromiso.getCnexoId(), OracleTypes.NUMBER, ParameterDirection.Input));
//
//        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobCompromiso.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PI_C_COB_COMPROMISO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> updateParameters(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) {
        List<ParameterOracle> oListParam = new ArrayList<>();

//        oListParam.add(new ParameterOracle("PI_C_COB_COMPROMISO_ID", oCobCompromiso.getCcodCompromisoId(), OracleTypes.NUMBER, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_C_COB_SEGUIMIENTO_ID", oCobCompromiso.getCobSeguimiento().getCcobSeguimientoId(), OracleTypes.NUMBER, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_C_COB_COMPROMISO", oCobCompromiso.getCcodCompromiso(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_F_FECHA", oCobCompromiso.getFfecha(), OracleTypes.DATE, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_I_MONTO", oCobCompromiso.getImonto(), OracleTypes.DOUBLE, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_D_CUOTA", oCobCompromiso.getDcuota(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oCobCompromiso.getEestadoId(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_F_FECHA_OBS", oCobCompromiso.getFfecObs(), OracleTypes.DATE, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_D_OBSERVACION", oCobCompromiso.getDobservacion(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_D_RESPUESTA", oCobCompromiso.getDrespuesta(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_E_ESTADO", oCobCompromiso.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oCobCompromiso.getcUsuarioMod(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_bus(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO",oCobCronogramaMetaAsesor.getN_anio(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", oCobCronogramaMetaAsesor.getN_mes(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobCronogramaMetaAsesor.getC_fondo_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }

     private List<ParameterOracle> listParameters_busCall(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) {
        List<ParameterOracle> oListParam = new ArrayList<>();

//        oListParam.add(new ParameterOracle("PI_C_TIPO_NEXO_ID", oCobCompromiso.getCtipoNexoId(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_C_NEXO_ID", oCobCompromiso.getCnexoId(), OracleTypes.NUMBER, ParameterDirection.Input));

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
