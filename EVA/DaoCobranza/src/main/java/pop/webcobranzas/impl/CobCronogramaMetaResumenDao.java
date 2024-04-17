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
import org.omg.CORBA.INTERNAL;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ICobCronogramaMetaResumenDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author HH38092
 */
public class CobCronogramaMetaResumenDao extends DBUtil implements ICobCronogramaMetaResumenDao {
    private OracleConnection cn = null;

    public CobCronogramaMetaResumenDao() {

    }

    public CobCronogramaMetaResumenDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        //System.out.println(" <i> insertar compromiso " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call yyyyyyyyy.xxxxx(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oCobCronogramaMetaResumen);
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
    public void update(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        //System.out.println(" <i> actualizar compromiso " + LocalDateTime.now());
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call yyyyyyyyyy.xxxxxxxxxx(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters(oCobCronogramaMetaResumen);
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
    public void updatexCambioAsesor(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        System.out.println("ini updatexCambioAsesor");
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_UPD_RESUMENXCAMBIOASESOR(?,?,?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParametersxCambioAsesor(oCobCronogramaMetaResumen);            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            System.out.println("exec:"+oCobCronogramaMetaResumen.toString());
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
    public void delete(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ArrayList<CobCronogramaMetaResumen> fetchAll(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        System.out.println("CobCronogramaMetaResumenDao: fetchAll()");
        ArrayList<CobCronogramaMetaResumen> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_BUS_CRONOGRAMA_META_RESUMEN(?,?,?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus(oCobCronogramaMetaResumen);
             //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            System.out.println("exec:"+oCobCronogramaMetaResumen.toString());
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
//                // fondo
//                MaeFondo fondo = new MaeFondo();
//                fondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
//                fondo.setDFondo(resultSet.getString("D_FONDO"));
//                //inversion
//                MaeInversion inversion = new MaeInversion();
//                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
//                inversion.setCInversion(resultSet.getString("C_INVERSION"));
//                inversion.setMaeFondo(fondo);
                 // compromiso
                CobCronogramaMetaResumen cp = new CobCronogramaMetaResumen();
                cp.setN_anio(resultSet.getString("n_anio"));
                cp.setN_mes(resultSet.getString("n_mes"));
                cp.setC_fondo_id(resultSet.getString("c_fondo_id"));
                cp.setC_usuario_id(resultSet.getString("ASESOR"));
                cp.setN_cant_tchn(resultSet.getString("CANT_CODIGOS"));
                cp.setI_cuota(resultSet.getDouble("CUOTA_MES"));
                cp.setI_total_atrasado(resultSet.getDouble("TOTAL_ATRASADO_MES"));
                cp.setI_total_otros(resultSet.getDouble("TOTAL_ATRASADO_OTROS"));
                cp.setI_total(resultSet.getDouble("TOTAL"));
                cp.setE_estado(resultSet.getString("e_estado"));
                cp.setMoneda(resultSet.getString("MONEDA"));
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
    public ArrayList<CobCronogramaMetaResumen> fetchxFondo(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        System.out.println("CobCronogramaMetaResumenDao: fetchxFondo()");
        ArrayList<CobCronogramaMetaResumen> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_BUS_CRONOGRAMA_META_FONDO(?,?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus_fondo(oCobCronogramaMetaResumen);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                CobCronogramaMetaResumen cp = new CobCronogramaMetaResumen();
                cp.setN_anio(oCobCronogramaMetaResumen.getN_anio());
                cp.setN_mes(oCobCronogramaMetaResumen.getN_mes());
                cp.setC_fondo_id(resultSet.getString("c_fondo_id"));
                cp.setC_fondo_desc(resultSet.getString("D_FONDO"));
                cp.setMoneda(resultSet.getString("MONEDA")); ;
                cp.setI_total(resultSet.getDouble("TOTAL"));
                cp.setN_cant_tchn(resultSet.getString("N_CANT_TCHN"));
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
    public ArrayList<CobCronogramaMetaResumen> fetchxFondoxAsesor(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        System.out.println("CobCronogramaMetaResumenDao: fetchxFondoxAsesor()");
        ArrayList<CobCronogramaMetaResumen> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_BUS_META_FONDO_ASESOR(?,?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus_fondo_asesor(oCobCronogramaMetaResumen);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);


            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                CobCronogramaMetaResumen cp = new CobCronogramaMetaResumen();
                cp.setN_anio(oCobCronogramaMetaResumen.getN_anio());
                cp.setN_mes(oCobCronogramaMetaResumen.getN_mes());
                cp.setC_fondo_id(resultSet.getString("c_fondo_id"));
                cp.setC_fondo_desc(resultSet.getString("D_FONDO"));
                cp.setMoneda(resultSet.getString("MONEDA"));
                cp.setC_usuario_id(resultSet.getString("C_USUARIO_ID"));
                cp.setN_cant_tchn(resultSet.getString("N_CANT_TCHN"));
                cp.setV_cuota(resultSet.getString("I_CUOTA"));
                cp.setV_total_otros(resultSet.getString("I_TOTAL_OTROS"));
                cp.setV_total_atrasado(resultSet.getString("I_TOTAL_ATRASADO"));
                cp.setV_porcentajeAsesor(resultSet.getString("N_PORCENT_ASESOR"));
                cp.setV_total(resultSet.getString("TOTAL"));
                
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
    
    public ArrayList<CobCronogramaMetaResumen> fetchxFondoxAsesorJ(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        System.out.println("CobCronogramaMetaResumenDao: fetchxFondoxAsesor()");
        ArrayList<CobCronogramaMetaResumen> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_BUS_META_FONDO_ASESOR_JD(?,?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus_fondo_asesor(oCobCronogramaMetaResumen);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);


            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                CobCronogramaMetaResumen cp = new CobCronogramaMetaResumen();
                cp.setN_anio(oCobCronogramaMetaResumen.getN_anio());
                cp.setN_mes(oCobCronogramaMetaResumen.getN_mes());
                cp.setC_fondo_id(resultSet.getString("c_fondo_id"));
                cp.setC_fondo_desc(resultSet.getString("D_FONDO"));
                cp.setC_estado_cron(resultSet.getString("C_DES_ESTADO_CRON"));
                cp.setMoneda(resultSet.getString("MONEDA"));
                //cp.setC_usuario_id(resultSet.getString("C_USUARIO_ID"));
                cp.setN_cant_tchn(resultSet.getString("N_CANT_TCHN"));
                //cp.setV_cuota(resultSet.getString("I_CUOTA"));
                cp.setV_total_atrasado(resultSet.getString("I_TOTAL_ATRASADO"));
                //cp.setV_porcentajeAsesor(resultSet.getString("N_PORCENT_ASESOR"));
                //cp.setV_total(resultSet.getString("TOTAL"));
                
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
    public CobCronogramaMetaResumen fetchCall(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        //System.out.println(" <i> CobCompromisoDao fetchCall " + LocalDateTime.now());
        CobCronogramaMetaResumen cp = new CobCronogramaMetaResumen();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call yyyyyyy.xxxxxx(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busCall(oCobCronogramaMetaResumen);
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

    private List<ParameterOracle> insertParameters(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
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

    private List<ParameterOracle> updateParameters(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
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

    private List<ParameterOracle> updateParametersxCambioAsesor(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_N_ANIO", Integer.parseInt(oCobCronogramaMetaResumen.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", Integer.parseInt(oCobCronogramaMetaResumen.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobCronogramaMetaResumen.getC_fondo_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oCobCronogramaMetaResumen.getC_usuario_mod(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_JUDICIAL", oCobCronogramaMetaResumen.getJudicial(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }    
    
    private List<ParameterOracle> listParameters_bus(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO", Integer.parseInt(oCobCronogramaMetaResumen.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", Integer.parseInt(oCobCronogramaMetaResumen.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobCronogramaMetaResumen.getC_fondo_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_JUDICIAL", oCobCronogramaMetaResumen.getJudicial(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }
    
   private List<ParameterOracle> listParameters_bus_fondo(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO", Integer.parseInt(oCobCronogramaMetaResumen.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", Integer.parseInt(oCobCronogramaMetaResumen.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_JUDICIAL", oCobCronogramaMetaResumen.getJudicial(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }    

   private List<ParameterOracle> listParameters_bus_fondo_asesor(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO", Integer.parseInt(oCobCronogramaMetaResumen.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", Integer.parseInt(oCobCronogramaMetaResumen.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_JUDICIAL", oCobCronogramaMetaResumen.getJudicial(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }     
   
     private List<ParameterOracle> listParameters_busCall(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
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
