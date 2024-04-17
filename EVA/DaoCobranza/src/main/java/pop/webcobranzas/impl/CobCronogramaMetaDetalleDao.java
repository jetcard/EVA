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
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.comun.dominio.reporte.RepMetaCobranza;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.iface.ICobCronogramaMetaDetalleDao;

import pop.comun.dominio.reporte.RepMetaRecaudo;
import pop.comun.dominio.CobCronogramaRecaudoDetalle;
import pop.comun.dominio.CobCronogramaRecaudoResumen;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import java.util.Date;
/**
 *
 * @author HH38092
 */
public class CobCronogramaMetaDetalleDao extends DBUtil implements ICobCronogramaMetaDetalleDao {
    private OracleConnection cn = null;

    public CobCronogramaMetaDetalleDao() {

    }

    public CobCronogramaMetaDetalleDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        //System.out.println(" <i> insertar compromiso " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call pppppp.xxxxx(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oCobCronogramaMetaDetalle);
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
    public void update(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        //System.out.println(" <i> actualizar compromiso " + LocalDateTime.now());
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call ppppp.xxxxxx(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters(oCobCronogramaMetaDetalle);
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
    public void updateList(List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalleList)
    {
        System.out.println("inicio");
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_UPD_META_DETALLE(?,?,?,?,?,?,?,?,?,?)}";
            System.out.println(sp);
            
            System.out.println("oCobCronogramaMetaDetalleList.size():"+oCobCronogramaMetaDetalleList.size());
            for(int i=0;i<oCobCronogramaMetaDetalleList.size();i++)
            {
                if(oCobCronogramaMetaDetalleList.get(i).isSelected())
                {
    //                OracleConnection cn = this.getCn();
    //                if (i>0)
    //                {
    //                    Conexion conex = new Conexion();
    //                    cn=conex.ConexionOpen();
    //                    this.setCn(cn);
    //                }
                    CobCronogramaMetaDetalle oCobCronogramaMetaDetalle=oCobCronogramaMetaDetalleList.get(i);

                    // list of parameters
                    List<ParameterOracle> oLis = new ArrayList<>();
                    // fill parameters
                    oLis = updateParametersList(oCobCronogramaMetaDetalle);
                    //Abre conexion a la BD
                    Conexion conex = new Conexion();
                    cn = conex.ConexionOpen();
                    // execute procedure
                    System.out.println("exec:"+oCobCronogramaMetaDetalleList.get(i).toString());
                    runSP(oLis, cn, cmd, sp);
                    System.out.println("termino execute");
                }
            }

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
    public void delete(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CobCronogramaMetaDetalle> fetchAll(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        System.out.println("fetchAll()");
        ArrayList<CobCronogramaMetaDetalle> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_BUS_CRONOGRAMA_META_DETALLE(?,?,?,?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus(oCobCronogramaMetaDetalle);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                CobCronogramaMetaDetalle cp = new CobCronogramaMetaDetalle();
                cp.setN_anio(resultSet.getString("n_anio"));
                cp.setN_mes(resultSet.getString("n_mes"));
                cp.setC_fondo_id(resultSet.getString("c_fondo_id"));
                cp.setC_mae_inversion_id(resultSet.getString("c_mae_inversion_id"));
                cp.setC_inversion_id(resultSet.getString("c_inversion_id"));
                cp.setC_inversion(resultSet.getString("c_inversion"));
                cp.setN_quincena(resultSet.getString("n_quincena"));
                cp.setF_ejecucion(resultSet.getDate("f_ejecucion"));
                cp.setF_vencimiento(resultSet.getDate("f_vencimiento"));
                cp.setF_pago(resultSet.getDate("f_pago"));
                cp.setJudicial(resultSet.getString("judicial"));
                cp.setN_cuota_atrasada(resultSet.getString("n_cuota_atrasada"));
                cp.setI_cuota(resultSet.getDouble("i_cuota"));
                cp.setI_capital(resultSet.getDouble("i_capital"));
                cp.setI_interes(resultSet.getDouble("i_interes"));
                cp.setI_capital_atrasado(resultSet.getDouble("i_capital_atrasado"));
                cp.setI_interes_atrasado(resultSet.getDouble("i_interes_atrasado"));
                cp.setI_mora_atrasado(resultSet.getDouble("i_mora_atrasado"));
                cp.setI_total_atrasado(resultSet.getDouble("i_total_atrasado"));
                cp.setC_estado_cron(resultSet.getString("c_estado_cron"));
                cp.setC_usuario_id(resultSet.getString("c_usuario_id"));
                cp.setN_dia(resultSet.getString("n_dia"));
                cp.setE_estado(resultSet.getString("e_estado"));
                cp.setC_usuario_add(resultSet.getString("c_usuario_add"));
                cp.setF_usuario_add(resultSet.getDate("f_usuario_add"));
                cp.setC_usuario_mod(resultSet.getString("c_usuario_mod"));
                cp.setF_usuario_mod(resultSet.getDate("f_usuario_mod"));
                cp.setMoneda(resultSet.getString("moneda"));
                if(resultSet.getString("MONEDA").contains("SOLES")) {
                    cp.setSimbolo("S/.");
                }else{
                    cp.setSimbolo("$.");
                }
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
    public ArrayList<CobCronogramaMetaDetalle> fetchRepAll(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        System.out.println("fetchAll()");
        ArrayList<CobCronogramaMetaDetalle> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
                
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_RPT_META_COBRANZA_DET(?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_Rep(oCobCronogramaMetaDetalle);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                CobCronogramaMetaDetalle cp = new CobCronogramaMetaDetalle();
                cp.setN_anio(resultSet.getString("n_anio"));
                cp.setN_mes(resultSet.getString("n_mes"));
                cp.setC_fondo_id(resultSet.getString("c_fondo_id"));
                cp.setC_fondo(resultSet.getString("c_fondo"));
                //cp.setC_mae_inversion_id(resultSet.getString("c_mae_inversion_id"));
                //cp.setC_inversion_id(resultSet.getString("c_inversion_id"));
                cp.setC_inversion(resultSet.getString("c_inversion"));
                //cp.setN_quincena(resultSet.getString("n_quincena"));
                cp.setF_ejecucion(resultSet.getDate("f_ejecucion"));
                cp.setF_vencimiento(resultSet.getDate("f_vencimiento"));
                cp.setF_pago(resultSet.getDate("f_pago"));
                cp.setJudicial(resultSet.getString("judicial"));
                cp.setN_cuota_atrasada(resultSet.getString("n_cuota_atrasada"));
                cp.setI_cuota(resultSet.getDouble("i_cuota"));
                cp.setI_capital(resultSet.getDouble("i_capital"));
                cp.setI_interes(resultSet.getDouble("i_interes"));
                cp.setI_capital_atrasado(resultSet.getDouble("i_capital_atrasado"));
                cp.setI_interes_atrasado(resultSet.getDouble("i_interes_atrasado"));
                cp.setI_mora_atrasado(resultSet.getDouble("i_mora_atrasado"));
                cp.setI_ica_atrasado(resultSet.getDouble("i_ica_atrasado"));
                cp.setI_total_atrasado(resultSet.getDouble("i_total_atrasado"));
                cp.setC_estado_cron(resultSet.getString("c_estado_cron"));
                cp.setC_usuario_id(resultSet.getString("c_usuario_id"));
                cp.setN_dia(resultSet.getString("n_dia"));
                cp.setMoneda(resultSet.getString("moneda"));
                /*
                cp.setE_estado(resultSet.getString("e_estado"));
                cp.setC_usuario_add(resultSet.getString("c_usuario_add"));
                cp.setF_usuario_add(resultSet.getDate("f_usuario_add"));
                cp.setC_usuario_mod(resultSet.getString("c_usuario_mod"));
                cp.setF_usuario_mod(resultSet.getDate("f_usuario_mod"));
                */
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
    public ArrayList<CobCronogramaMetaAgrupxFecha> fetchAgrupFecha(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        System.out.println("fetchAgrupFecha()");
        ArrayList<CobCronogramaMetaAgrupxFecha> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_BUS_CRONOG_META_AGRUXFEC(?,?,?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersAgrupxFecha_bus(oCobCronogramaMetaResumen);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                CobCronogramaMetaAgrupxFecha cp = new CobCronogramaMetaAgrupxFecha();
                cp.setFecha(resultSet.getString("Fecha"));
                cp.setNroCodigos(resultSet.getString("NroCodigos"));
                cp.setMoneda(resultSet.getString("MONEDA"));
                cp.setCuotaMes(resultSet.getString("CuotaMes"));
                cp.setTotalAtrasado(resultSet.getString("TotalAtrasado"));
                //cabecera
                cp.setN_anio(oCobCronogramaMetaResumen.getN_anio());
                cp.setN_mes(oCobCronogramaMetaResumen.getN_mes());
                cp.setC_fondo_id(oCobCronogramaMetaResumen.getC_fondo_id());
                cp.setC_usuario_id(oCobCronogramaMetaResumen.getC_usuario_id());
                cp.setMoneda(oCobCronogramaMetaResumen.getMoneda());
                System.out.println(cp.toString());
                
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
        System.out.println("termino de listar()");
        return lstCompromiso;
    }
    
    @Override
    public CobCronogramaMetaDetalle fetchCall(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        //System.out.println(" <i> CobCompromisoDao fetchCall " + LocalDateTime.now());
        CobCronogramaMetaDetalle cp = new CobCronogramaMetaDetalle();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_COMPROMISO.xxxxxx(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busCall(oCobCronogramaMetaDetalle);
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
    
    @Override
    public RepMetaCobranza buscarReporteMetaCobranza(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        //System.out.println(" <i> MaeInversionDao reportDebitBalance " + LocalDateTime.now());
        RepMetaCobranza repMetaCobranza = new RepMetaCobranza();
        ArrayList<RepMetaCobranza> lstRepMetaCobranza = new ArrayList<>();
        
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        OracleResultSet resultSetB = null;
        OracleResultSet resultSetC = null;
        OracleResultSet resultSetD = null;
        OracleResultSet resultSetE = null;
        
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_RPT_META_COBRANZA(?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters            
            oList = listParameters_RepMeta(oCobCronogramaMetaDetalle);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            ArrayList<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaList = new ArrayList<>();
            while (resultSet.next()) {
                CobCronogramaMetaAgrupxFecha newCobCronogramaMetaAgrupxFecha = new CobCronogramaMetaAgrupxFecha();
                newCobCronogramaMetaAgrupxFecha.setN_anio(oCobCronogramaMetaDetalle.getN_anio());
                newCobCronogramaMetaAgrupxFecha.setN_mes(oCobCronogramaMetaDetalle.getN_mes());
                newCobCronogramaMetaAgrupxFecha.setMoneda(resultSet.getString("MONEDA"));
                newCobCronogramaMetaAgrupxFecha.setC_fondo_id(resultSet.getString("C_FONDO_ID"));
                newCobCronogramaMetaAgrupxFecha.setCuotaMes(resultSet.getString("TOTAL"));
                newCobCronogramaMetaAgrupxFecha.setTotalAtrasado(resultSet.getString("N_CANT_TCHN"));
                cobCronogramaMetaAgrupxFechaList.add(newCobCronogramaMetaAgrupxFecha);
            }
            repMetaCobranza.setCobCronogramaMetaAgrupxFechaList(cobCronogramaMetaAgrupxFechaList);
            
            resultSetB = getOutputParameter("PO_CURSOR_RESULTADO_B").getParameterResultSet();
            ArrayList<CobCronogramaMetaResumen> cobCronogramaMetaResumenList = new ArrayList<>();
            while (resultSetB.next()) {
                CobCronogramaMetaResumen newCobCronogramaMetaResumen = new CobCronogramaMetaResumen();
                newCobCronogramaMetaResumen.setN_anio(resultSetB.getString("N_ANIO"));
                newCobCronogramaMetaResumen.setN_mes(resultSetB.getString("N_MES"));
                newCobCronogramaMetaResumen.setMoneda(resultSetB.getString("MONEDA"));
                newCobCronogramaMetaResumen.setC_fondo_id(resultSetB.getString("C_FONDO_ID"));
                newCobCronogramaMetaResumen.setC_fondo_desc(resultSetB.getString("D_FONDO"));
                newCobCronogramaMetaResumen.setN_quincena(resultSetB.getString("N_QUINCENA"));
                newCobCronogramaMetaResumen.setC_usuario_id(resultSetB.getString("ASESOR"));
                newCobCronogramaMetaResumen.setN_cant_tchn(resultSetB.getString("CANT_CODIGOS"));
                newCobCronogramaMetaResumen.setI_cuota(resultSetB.getDouble("CUOTA_MES"));
                newCobCronogramaMetaResumen.setI_total_atrasado(resultSetB.getDouble("TOTAL_ATRASADO_MES"));
                newCobCronogramaMetaResumen.setI_total_otros(resultSetB.getDouble("TOTAL_ATRASADO_OTROS"));
                newCobCronogramaMetaResumen.setI_total(resultSetB.getDouble("TOTAL"));
                cobCronogramaMetaResumenList.add(newCobCronogramaMetaResumen);
            }            
            repMetaCobranza.setCobCronogramaMetaResumenList(cobCronogramaMetaResumenList);
            
            ArrayList<CobCronogramaMetaDetalle> cobCronogramaMetaDetalleList = new ArrayList<>();
            resultSetC = getOutputParameter("PO_CURSOR_RESULTADO_C").getParameterResultSet();
            while (resultSetC.next()) {
                CobCronogramaMetaDetalle newCobCronogramaMetaDetalle = new CobCronogramaMetaDetalle();
                newCobCronogramaMetaDetalle.setN_anio(resultSetC.getString("n_anio"));
                newCobCronogramaMetaDetalle.setN_mes(resultSetC.getString("n_mes"));
                newCobCronogramaMetaDetalle.setMoneda(resultSetC.getString("moneda"));
                newCobCronogramaMetaDetalle.setC_fondo_id(resultSetC.getString("c_fondo_id"));
                newCobCronogramaMetaDetalle.setC_fondo(resultSetC.getString("d_fondo"));
                newCobCronogramaMetaDetalle.setC_inversion(resultSetC.getString("c_inversion"));
                newCobCronogramaMetaDetalle.setF_ejecucion(resultSetC.getDate("f_ejecucion"));
                newCobCronogramaMetaDetalle.setF_vencimiento(resultSetC.getDate("f_vencimiento"));
                newCobCronogramaMetaDetalle.setF_pago(resultSetC.getDate("f_pago"));
                newCobCronogramaMetaDetalle.setJudicial(resultSetC.getString("judicial"));
                newCobCronogramaMetaDetalle.setN_cuota_atrasada(resultSetC.getString("n_cuota_atrasada"));
                newCobCronogramaMetaDetalle.setI_cuota(resultSetC.getDouble("i_cuota"));
                newCobCronogramaMetaDetalle.setI_capital(resultSetC.getDouble("i_capital"));
                newCobCronogramaMetaDetalle.setI_interes(resultSetC.getDouble("i_interes"));
                newCobCronogramaMetaDetalle.setI_capital_atrasado(resultSetC.getDouble("saldo_capital_atrasado"));
                newCobCronogramaMetaDetalle.setI_interes_atrasado(resultSetC.getDouble("saldo_interes"));
                newCobCronogramaMetaDetalle.setI_ica_atrasado(resultSetC.getDouble("total_ica"));
                newCobCronogramaMetaDetalle.setI_mora_atrasado(resultSetC.getDouble("total_mora"));
                newCobCronogramaMetaDetalle.setI_total_atrasado(resultSetC.getDouble("total_atrasado"));
                newCobCronogramaMetaDetalle.setC_estado_cron(resultSetC.getString("estado_cronograma"));
                newCobCronogramaMetaDetalle.setC_usuario_id(resultSetC.getString("asesor"));
                newCobCronogramaMetaDetalle.setN_dia(resultSetC.getString("considerar_dia"));                
                cobCronogramaMetaDetalleList.add(newCobCronogramaMetaDetalle);                
            }            
            repMetaCobranza.setCobCronogramaMetaDetalleList(cobCronogramaMetaDetalleList);
            
            resultSetD = getOutputParameter("PO_CURSOR_RESULTADO_D").getParameterResultSet();
            ArrayList<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaListJD = new ArrayList<>();
            while (resultSetD.next()) {
                CobCronogramaMetaAgrupxFecha newCobCronogramaMetaAgrupxFecha = new CobCronogramaMetaAgrupxFecha();
                newCobCronogramaMetaAgrupxFecha.setC_fondo_id(resultSetD.getString("C_FONDO_ID"));
                newCobCronogramaMetaAgrupxFecha.setMoneda(resultSetD.getString("MONEDA"));
                newCobCronogramaMetaAgrupxFecha.setCuotaMes(resultSetD.getString("TOTAL"));
                newCobCronogramaMetaAgrupxFecha.setTotalAtrasado(resultSetD.getString("N_CANT_TCHN"));
                cobCronogramaMetaAgrupxFechaListJD.add(newCobCronogramaMetaAgrupxFecha);
            }
            repMetaCobranza.setCobCronogramaMetaAgrupxFechaListJD(cobCronogramaMetaAgrupxFechaListJD);
            
            resultSetE = getOutputParameter("PO_CURSOR_RESULTADO_E").getParameterResultSet();
            ArrayList<CobCronogramaMetaResumen> cobCronogramaMetaResumenListJD = new ArrayList<>();
            while (resultSetE.next()) {
                CobCronogramaMetaResumen newCobCronogramaMetaResumen = new CobCronogramaMetaResumen();
                newCobCronogramaMetaResumen.setC_fondo_id(resultSetE.getString("C_FONDO_ID"));
                newCobCronogramaMetaResumen.setC_fondo_desc(resultSetE.getString("D_FONDO"));
                newCobCronogramaMetaResumen.setC_est_tchn(resultSetE.getString("ESTADO_CRONOGRAMA"));
                newCobCronogramaMetaResumen.setMoneda(resultSetE.getString("MONEDA"));
                newCobCronogramaMetaResumen.setN_cant_tchn(resultSetE.getString("CANT_CODIGOS"));
                newCobCronogramaMetaResumen.setI_total(Double.valueOf(resultSetE.getString("TOTAL")));
                cobCronogramaMetaResumenListJD.add(newCobCronogramaMetaResumen);
            }            
            repMetaCobranza.setCobCronogramaMetaResumenListJD(cobCronogramaMetaResumenListJD);

        } catch (Exception e) {
            //Logger.getLogger(MaeInversionDao.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSetE != null) {
                try { resultSetE.close(); } catch (Exception e) { ; }
                resultSetE = null;
              }
            if (resultSetD != null) {
                try { resultSetD.close(); } catch (Exception e) { ; }
                resultSetD = null;
              }
            if (resultSetC != null) {
                try { resultSetC.close(); } catch (Exception e) { ; }
                resultSetC = null;
              }
            if (resultSetB != null) {
                try { resultSetB.close(); } catch (Exception e) { ; }
                resultSetB = null;
              }
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
        //System.out.println(" <f> MaeInversionDao reportDebitBalance " + LocalDateTime.now());
        return repMetaCobranza;

    }
        
    @Override
    public RepMetaRecaudo buscarReporteMetaRecaudo(CobCronogramaRecaudoDetalle oCobCronogramaRecaudoDetalle){
        RepMetaRecaudo repMetaRecaudo = new RepMetaRecaudo();
        /*
        ArrayList<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList = new ArrayList<>();
        ArrayList<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList1 = new ArrayList<>();
        ArrayList<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList2 = new ArrayList<>();
        */

        ArrayList<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList = new ArrayList<>();
        ArrayList<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList1 = new ArrayList<>();
        
        ArrayList<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleListJD = new ArrayList<>();        
        ArrayList<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList2 = new ArrayList<>();
        
        try
        {
            oCobCronogramaRecaudoDetalle.setC_judicial("N");
           
            cobCronogramaRecaudoDetalleList = obtieneDataRecaudo(cobCronogramaRecaudoDetalleList1, oCobCronogramaRecaudoDetalle);
            repMetaRecaudo.setCobCronogramaRecaudoDetalleList(cobCronogramaRecaudoDetalleList);
            oCobCronogramaRecaudoDetalle.setC_judicial("S");
            
            cobCronogramaRecaudoDetalleListJD = obtieneDataRecaudo(cobCronogramaRecaudoDetalleList2, oCobCronogramaRecaudoDetalle);
            
            repMetaRecaudo.setCobCronogramaRecaudoDetalleJDList(cobCronogramaRecaudoDetalleListJD);
           
            
            /*
            oCobCronogramaRecaudoDetalle.setC_judicial("N");
            cobCronogramaRecaudoDetalleList2 = obtieneDataRecaudo(cobCronogramaRecaudoDetalleList1, oCobCronogramaRecaudoDetalle);
            
            oCobCronogramaRecaudoDetalle.setC_judicial("S");
            cobCronogramaRecaudoDetalleList = obtieneDataRecaudo(cobCronogramaRecaudoDetalleList2, oCobCronogramaRecaudoDetalle);
            
            repMetaRecaudo.setCobCronogramaRecaudoDetalleList(cobCronogramaRecaudoDetalleList);
            */
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return repMetaRecaudo;
    } 
    
    private ArrayList<CobCronogramaRecaudoDetalle> obtieneDataRecaudo(ArrayList<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList, CobCronogramaRecaudoDetalle oCobCronogramaRecaudoDetalle){
        String c_fondo_id;        
        String c_usuario;
        String c_tipo;
        String c_concepto;
        String c_fondo_id_org;
        String c_usuario_org;
        String c_tipo_org;
        String c_concepto_org;
        String c_moneda;
        String c_moneda_org;
        int i_dias_avance;
        int i_dias_recaudo;
        
        c_fondo_id = "";
        c_usuario = "";
        c_moneda = "";
        
        c_fondo_id_org = c_fondo_id;
        c_usuario_org = c_fondo_id;
        c_moneda_org = c_moneda;
        i_dias_avance = 0;
        i_dias_recaudo = 0;
        
        RepMetaRecaudo repMetaRecaudo = new RepMetaRecaudo();
        CobCronogramaRecaudoDetalle newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();
        ArrayList<RepMetaRecaudo> lstRepMetaRecaudo = new ArrayList<>();
        
        OracleCallableStatement cmd = null;
        OracleResultSet resultSetDias = null;
        OracleResultSet resultSetPosGlobal = null;
        OracleResultSet resultSetPosFondo = null; 
        OracleResultSet resultSetPosEstado1 = null;
        OracleResultSet resultSetPosEstado2 = null;
        
        try
        {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_RPT_META_RECAUDO(?,?,?,?,?,?,?,?,?,?,?,?)}";            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            //ArrayList<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList = new ArrayList<>();             
            
            // fill parameters            
            oList = listParameters_RepRecaudo(oCobCronogramaRecaudoDetalle);
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            resultSetDias = getOutputParameter("PO_CURSOR_FECHAS").getParameterResultSet();     
            while (resultSetDias.next()) {                              
                i_dias_avance = resultSetDias.getInt("DIAS_AVANCE");
                i_dias_recaudo = resultSetDias.getInt("DIAS_RECAUDO"); 
            }            
            
            
            //1. POSICION GLOBAL
            resultSetPosGlobal = getOutputParameter("PO_CURSOR_POS_GLOBAL").getParameterResultSet();     
            while (resultSetPosGlobal.next()) {                              
                c_fondo_id = resultSetPosGlobal.getString("C_FONDO_ID");
                c_concepto = resultSetPosGlobal.getString("CONCEPTO");
                c_moneda = resultSetPosGlobal.getString("MONEDA");
                if (c_fondo_id_org.isEmpty())
                {
                    c_fondo_id_org = resultSetPosGlobal.getString("C_FONDO_ID");
                    c_moneda_org = resultSetPosGlobal.getString("MONEDA");
                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();
                    
                    newCobCronogramaRecaudoDetalle.setI_dias_avance(i_dias_avance);
                    newCobCronogramaRecaudoDetalle.setI_dias_recaudo(i_dias_recaudo);
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(1);
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosGlobal.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosGlobal.getString("CONCEPTO"));                
                    newCobCronogramaRecaudoDetalle.setC_judicial(resultSetPosGlobal.getString("JUDICIAL"));
                    newCobCronogramaRecaudoDetalle.setMoneda(resultSetPosGlobal.getString("MONEDA"));
                }
                else if (!c_fondo_id.equals(c_fondo_id_org))
                {
                    cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
                    
                    c_fondo_id_org = resultSetPosGlobal.getString("C_FONDO_ID");
                    c_moneda_org = resultSetPosGlobal.getString("MONEDA");
                    
                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();                    
                    
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(1);
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosGlobal.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosGlobal.getString("CONCEPTO"));                
                    newCobCronogramaRecaudoDetalle.setC_judicial(resultSetPosGlobal.getString("JUDICIAL"));
                    newCobCronogramaRecaudoDetalle.setMoneda(resultSetPosGlobal.getString("MONEDA"));
                }
              
                if (c_concepto.contains("01")  )
                {
                    newCobCronogramaRecaudoDetalle.setI_meta(resultSetPosGlobal.getDouble("META"));
                }
                else if (c_concepto.contains("02"))
                {
                    newCobCronogramaRecaudoDetalle.setI_recaudo(resultSetPosGlobal.getDouble("META"));
                }
                else if (c_concepto.contains("03"))
                {
                    newCobCronogramaRecaudoDetalle.setI_recaudo_ant(resultSetPosGlobal.getDouble("META"));
                }
            }            
            cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
            
            
            
            //2. POSICION FONDO
            c_fondo_id = "";
            c_moneda = "";
            c_usuario = "";

            c_fondo_id_org = "";
            c_usuario_org = "";
            c_moneda_org = "";
            resultSetPosFondo = getOutputParameter("PO_CURSOR_POS_FONDO").getParameterResultSet();     
            while (resultSetPosFondo.next()) {                              
                
            
                c_fondo_id = resultSetPosFondo.getString("C_FONDO_ID");
                c_concepto = resultSetPosFondo.getString("CONCEPTO");   
                c_usuario = resultSetPosFondo.getString("C_USUARIO_ID");
                c_moneda = resultSetPosFondo.getString("MONEDA");
 
                if (c_fondo_id_org.isEmpty())
                {
                    c_fondo_id_org = resultSetPosFondo.getString("C_FONDO_ID");  
                    c_usuario_org = resultSetPosFondo.getString("C_USUARIO_ID");
                    c_moneda_org = resultSetPosFondo.getString("MONEDA");
                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();
                    
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(2);
                    newCobCronogramaRecaudoDetalle.setC_usuario(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(resultSetPosFondo.getString("C_USUARIO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosFondo.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosFondo.getString("CONCEPTO")); 
                    newCobCronogramaRecaudoDetalle.setMoneda(resultSetPosFondo.getString("MONEDA"));
                }
                else if (!c_fondo_id.equals(c_fondo_id_org) )
                {
                    cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
                    
                    c_fondo_id_org = resultSetPosFondo.getString("C_FONDO_ID");
                    c_usuario_org = resultSetPosFondo.getString("C_USUARIO_ID");
                    c_moneda_org = resultSetPosFondo.getString("MONEDA");
                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();                    
                    
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(2);
                    newCobCronogramaRecaudoDetalle.setC_usuario(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(resultSetPosFondo.getString("C_USUARIO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosFondo.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosFondo.getString("CONCEPTO"));
                    newCobCronogramaRecaudoDetalle.setMoneda(resultSetPosFondo.getString("MONEDA"));
                }
                
                if (!c_usuario.equals(c_usuario_org) )
                {
                    cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
                    
                    c_fondo_id_org = resultSetPosFondo.getString("C_FONDO_ID");
                    c_usuario_org = resultSetPosFondo.getString("C_USUARIO_ID");
                    c_moneda_org = resultSetPosFondo.getString("MONEDA");
                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();                    
                    
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(2);
                    newCobCronogramaRecaudoDetalle.setC_usuario(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(resultSetPosFondo.getString("C_USUARIO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosFondo.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosFondo.getString("CONCEPTO"));
                    newCobCronogramaRecaudoDetalle.setMoneda(resultSetPosFondo.getString("MONEDA"));
                }
              
                if (c_concepto.contains("04"))
                {
                    newCobCronogramaRecaudoDetalle.setI_meta(resultSetPosFondo.getDouble("TOTAL"));
                }
                else if (c_concepto.contains("05"))
                {
                    newCobCronogramaRecaudoDetalle.setI_recaudo(resultSetPosFondo.getDouble("TOTAL"));
                }
                else if (c_concepto.contains("06"))
                {
                    newCobCronogramaRecaudoDetalle.setI_recaudo_ase(resultSetPosFondo.getDouble("TOTAL"));
                }
                else if (c_concepto.contains("07"))
                {
                    newCobCronogramaRecaudoDetalle.setI_recaudo_cli(resultSetPosFondo.getDouble("TOTAL"));
                }
                
            }   
            cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
            
            
            
            //3. POSICION POR ESTADO_1
            c_fondo_id = "";
            c_usuario = "";
            c_tipo = "";

            c_fondo_id_org = "";
            c_usuario_org = "";
            c_moneda_org="";
            c_tipo_org = "";
            
            resultSetPosEstado1 = getOutputParameter("PO_CURSOR_POS_ESTADO_1").getParameterResultSet();     
            while (resultSetPosEstado1.next()) {                
                c_fondo_id = resultSetPosEstado1.getString("C_FONDO_ID");
                c_concepto = resultSetPosEstado1.getString("CONCEPTO");  
                c_tipo = resultSetPosEstado1.getString("TIPO");  
                c_moneda = resultSetPosEstado1.getString("MONEDA");
                if (c_fondo_id_org.isEmpty())
                {
                    c_fondo_id_org = resultSetPosEstado1.getString("C_FONDO_ID");        
                    c_tipo_org = resultSetPosEstado1.getString("TIPO"); 
                    c_moneda_org = resultSetPosEstado1.getString("MONEDA");

                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();
                    
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(3);
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosEstado1.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosEstado1.getString("CONCEPTO"));                
                    newCobCronogramaRecaudoDetalle.setC_judicial(resultSetPosEstado1.getString("JUDICIAL"));
                    newCobCronogramaRecaudoDetalle.setMoneda(resultSetPosEstado1.getString("MONEDA"));
                }
                else if (!c_fondo_id.equals(c_fondo_id_org) )
                {
                    cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
                    
                    c_fondo_id_org = resultSetPosEstado1.getString("C_FONDO_ID");
                    c_tipo_org = resultSetPosEstado1.getString("TIPO"); 
                    c_moneda_org = resultSetPosEstado1.getString("MONEDA");
                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();                    
                    
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(3);
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosEstado1.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosEstado1.getString("CONCEPTO"));                
                    newCobCronogramaRecaudoDetalle.setC_judicial(resultSetPosEstado1.getString("JUDICIAL"));
                    newCobCronogramaRecaudoDetalle.setMoneda(resultSetPosEstado1.getString("MONEDA"));

                }
                /*
                if (!c_tipo.equals(c_tipo_org))
                {
                    cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
                    
                    c_fondo_id_org = resultSetPosEstado1.getString("C_FONDO_ID");
                    c_tipo_org = resultSetPosEstado1.getString("TIPO"); 
                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();                    
                    
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(3);
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosEstado1.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosEstado1.getString("CONCEPTO"));                
                    newCobCronogramaRecaudoDetalle.setC_judicial(resultSetPosEstado1.getString("JUDICIAL"));
                }
                */
                if (c_tipo.contains("00"))
                {
                   newCobCronogramaRecaudoDetalle.setI_cliente(resultSetPosEstado1.getDouble("I_DEPO"));
                }
                else if (c_tipo.contains("01"))
                {
                   newCobCronogramaRecaudoDetalle.setI_cobranza(resultSetPosEstado1.getDouble("I_DEPO"));
                }                
            }
            cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
            
            
            
            
            //3. POSICION POR ESTADO_2
            c_fondo_id = "";
            c_concepto = "";

            c_fondo_id_org = "";
            c_concepto_org = "";
            
            resultSetPosEstado2 = getOutputParameter("PO_CURSOR_POS_ESTADO_2").getParameterResultSet();     
            while (resultSetPosEstado2.next()) {                
                c_fondo_id = resultSetPosEstado2.getString("C_FONDO_ID");
                c_concepto = resultSetPosEstado2.getString("CONCEPTO");                  
                c_moneda=resultSetPosEstado2.getString("MONEDA");   
                if (c_fondo_id_org.isEmpty())
                {
                    c_fondo_id_org = resultSetPosEstado2.getString("C_FONDO_ID");
                    c_concepto_org = resultSetPosEstado2.getString("CONCEPTO");
                    c_moneda_org=resultSetPosEstado2.getString("MONEDA");
                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();
                    
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(4);
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosEstado2.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosEstado2.getString("CONCEPTO"));                
                    newCobCronogramaRecaudoDetalle.setC_judicial(resultSetPosEstado2.getString("JUDICIAL"));                    
                    newCobCronogramaRecaudoDetalle.setMoneda(resultSetPosEstado2.getString("MONEDA"));

                }
                else if (!c_fondo_id.equals(c_fondo_id_org))
                {
                    cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
                    
                    c_fondo_id_org = resultSetPosEstado2.getString("C_FONDO_ID");
                    c_concepto_org = resultSetPosEstado2.getString("CONCEPTO");
                    c_moneda_org=resultSetPosEstado2.getString("MONEDA");
                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();                    
                    
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(4);
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosEstado2.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosEstado2.getString("CONCEPTO"));                
                    newCobCronogramaRecaudoDetalle.setC_judicial(resultSetPosEstado2.getString("JUDICIAL"));
                    newCobCronogramaRecaudoDetalle.setMoneda(resultSetPosEstado2.getString("MONEDA"));

                }
                
                if (!c_concepto.equals(c_concepto_org) )
                {
                    cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
                    
                    c_fondo_id_org = resultSetPosEstado2.getString("C_FONDO_ID");
                    c_concepto_org = resultSetPosEstado2.getString("CONCEPTO");
                    c_moneda_org=resultSetPosEstado2.getString("MONEDA");

                    newCobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();                    
                    
                    newCobCronogramaRecaudoDetalle.setN_anio(oCobCronogramaRecaudoDetalle.getN_anio());
                    newCobCronogramaRecaudoDetalle.setN_mes(oCobCronogramaRecaudoDetalle.getN_mes());
                    newCobCronogramaRecaudoDetalle.setF_fecha(oCobCronogramaRecaudoDetalle.getF_fecha());
                    newCobCronogramaRecaudoDetalle.setI_posicion(4);
                    newCobCronogramaRecaudoDetalle.setC_usuario_id(oCobCronogramaRecaudoDetalle.getC_usuario_id());
                    newCobCronogramaRecaudoDetalle.setC_fondo_id(resultSetPosEstado2.getString("C_FONDO_ID"));
                    newCobCronogramaRecaudoDetalle.setC_concepto(resultSetPosEstado2.getString("CONCEPTO"));                
                    newCobCronogramaRecaudoDetalle.setC_judicial(resultSetPosEstado2.getString("JUDICIAL"));
                    newCobCronogramaRecaudoDetalle.setMoneda(resultSetPosEstado2.getString("MONEDA"));

                }
                newCobCronogramaRecaudoDetalle.setI_deposito(resultSetPosEstado2.getDouble("I_DEPO"));
                newCobCronogramaRecaudoDetalle.setI_cantidad(resultSetPosEstado2.getInt("CANTIDAD"));                                
            }
            cobCronogramaRecaudoDetalleList.add(newCobCronogramaRecaudoDetalle);
            
            
          }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSetDias != null) {
                try { resultSetDias.close(); } catch (Exception e) { ; }
                resultSetDias = null;
              }
            if (resultSetPosGlobal != null) {
                try { resultSetPosGlobal.close(); } catch (Exception e) { ; }
                resultSetPosGlobal = null;
              }
            if (resultSetPosFondo != null) {
                try { resultSetPosFondo.close(); } catch (Exception e) { ; }
                resultSetPosFondo = null;
              }
            if (resultSetPosEstado1 != null) {
                try { resultSetPosEstado1.close(); } catch (Exception e) { ; }
                resultSetPosEstado1 = null;
              }
            if (resultSetPosEstado2 != null) {
                try { resultSetPosEstado2.close(); } catch (Exception e) { ; }
                resultSetPosEstado2 = null;
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
        System.out.println("total es ="+cobCronogramaRecaudoDetalleList.size());
        return cobCronogramaRecaudoDetalleList;
    }        
    
    @Override
    public ArrayList<CobCronogramaRecaudoResumen> fetchRecAll(CobCronogramaRecaudoResumen oCobCronogramaRecaudoResumen) {
        System.out.println("fetchRecAll()");
        ArrayList<CobCronogramaRecaudoResumen> lstCompromiso = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
                
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_RPT_RECAUDO_RESUMEN(?,?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_Rec(oCobCronogramaRecaudoResumen);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                CobCronogramaRecaudoResumen cp = new CobCronogramaRecaudoResumen();
                cp.setN_anio(resultSet.getString("n_anio"));
                cp.setN_mes(resultSet.getString("n_mes"));
                cp.setC_fondo_id(resultSet.getString("c_fondo_id"));
                cp.setC_fondo(resultSet.getString("d_fondo"));
                cp.setC_inversion(resultSet.getString("c_inversion"));
                cp.setF_deposito(resultSet.getDate("f_bco_deposito"));
                cp.setI_deposito(resultSet.getDouble("i_bco_depositado"));
                cp.setF_deposito_ant(resultSet.getDate("f_bco_deposito_ant"));
                cp.setI_deposito_ant(resultSet.getDouble("i_bco_depositado_ant"));
                cp.setC_asesor(resultSet.getString("C_USUARIO_ADD"));
                cp.setC_gestor(resultSet.getString("C_USUARIO_ID"));                
                cp.setMoneda(resultSet.getString("MONEDA"));
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
   
    
    private List<ParameterOracle> insertParameters(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
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

    private List<ParameterOracle> updateParametersList(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_N_ANIO", Integer.parseInt(oCobCronogramaMetaDetalle.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", Integer.parseInt(oCobCronogramaMetaDetalle.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobCronogramaMetaDetalle.getC_fondo_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", Integer.parseInt(oCobCronogramaMetaDetalle.getC_mae_inversion_id()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", oCobCronogramaMetaDetalle.getC_inversion_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oCobCronogramaMetaDetalle.getC_inversion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_QUINCENA", Integer.parseInt(oCobCronogramaMetaDetalle.getN_quincena()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oCobCronogramaMetaDetalle.getC_usuario_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }

    private List<ParameterOracle> updateParameters(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
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

    private List<ParameterOracle> listParameters_bus(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO",(Number)Integer.valueOf(oCobCronogramaMetaDetalle.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", (Number)Integer.valueOf(oCobCronogramaMetaDetalle.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobCronogramaMetaDetalle.getC_fondo_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oCobCronogramaMetaDetalle.getC_usuario_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_PAGO", oCobCronogramaMetaDetalle.getF_pago(), OracleTypes.DATE, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }

    private List<ParameterOracle> listParameters_Rep(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO",(Number)Integer.valueOf(oCobCronogramaMetaDetalle.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", (Number)Integer.valueOf(oCobCronogramaMetaDetalle.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }
    
    private List<ParameterOracle> listParametersAgrupxFecha_bus(CobCronogramaMetaResumen oCobCronogramaMetaResumen) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO",(Number)Integer.valueOf(oCobCronogramaMetaResumen.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", (Number)Integer.valueOf(oCobCronogramaMetaResumen.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobCronogramaMetaResumen.getC_fondo_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oCobCronogramaMetaResumen.getC_usuario_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }    
    
    private List<ParameterOracle> listParameters_busCall(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        List<ParameterOracle> oListParam = new ArrayList<>();

//        oListParam.add(new ParameterOracle("PI_C_TIPO_NEXO_ID", oCobCompromiso.getCtipoNexoId(), OracleTypes.CHAR, ParameterDirection.Input));
//        oListParam.add(new ParameterOracle("PI_C_NEXO_ID", oCobCompromiso.getCnexoId(), OracleTypes.NUMBER, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_RepMeta(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO",(Number)Integer.valueOf(oCobCronogramaMetaDetalle.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", (Number)Integer.valueOf(oCobCronogramaMetaDetalle.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oCobCronogramaMetaDetalle.getC_usuario_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO_B", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO_C", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO_D", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO_E", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }
        
    private List<ParameterOracle> listParameters_RepRecaudo(CobCronogramaRecaudoDetalle oCobCronogramaRecaudoDetalle) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO", (Number)Integer.valueOf(oCobCronogramaRecaudoDetalle.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", (Number)Integer.valueOf(oCobCronogramaRecaudoDetalle.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PF_FECHA", oCobCronogramaRecaudoDetalle.getF_fecha(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_JUDICIAL", oCobCronogramaRecaudoDetalle.getC_judicial(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oCobCronogramaRecaudoDetalle.getC_usuario_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_FECHAS", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_POS_GLOBAL", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_POS_FONDO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_POS_ESTADO_1", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_POS_ESTADO_2", null, OracleTypes.CURSOR, ParameterDirection.Output));        
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }
    
    private List<ParameterOracle> listParameters_Rec(CobCronogramaRecaudoResumen oCobCronogramaRecaudoResumen) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO",(Number)Integer.valueOf(oCobCronogramaRecaudoResumen.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", (Number)Integer.valueOf(oCobCronogramaRecaudoResumen.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PF_FECHA", oCobCronogramaRecaudoResumen.getF_fecha(), OracleTypes.DATE, ParameterDirection.Input));
        
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
