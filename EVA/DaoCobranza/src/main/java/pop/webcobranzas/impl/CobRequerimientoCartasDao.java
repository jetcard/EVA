/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pop.webcobranzas.impl;



import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobRequerimientoCriterios;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeCustodia;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MovimientoCartas;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.iface.ICobRequerimientoCartasDao;

/**
 *
 * @author RC433838
 */
public class CobRequerimientoCartasDao extends DBUtil implements ICobRequerimientoCartasDao{

    private OracleConnection cn = null;

    @Override
    public String removerRequerimiento(CobRequerimientoCartas oRequerimiento) {
    
        System.out.println(" <i> removerRequerimiento(CobRequerimientoCartas oRequerimiento) "+ LocalDateTime.now() );
        String Rspta = null;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_REQUERIMIENTOS.SP_ELIMINAR_REQUERIMIENTO(?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           oList.add(new ParameterOracle("P_REQ_TIPO", oRequerimiento.getReqTipo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_NUMERO", oRequerimiento.getReqNumero(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_C_USUARIO_MOD", oRequerimiento.getcUsuarioMod(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output)); 

            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();
            
            while (resultSet.next()) {   
                Rspta = resultSet.getString("RPTA");
            }
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta="Error en los parametros";
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
            if (getCn() != null) {
              try { getCn().close(); } catch (Exception e) { ; }
                setCn(null);
            }
        }
        System.out.println(" <f> removerRequerimiento(CobRequerimientoCartas oRequerimiento) " + LocalDateTime.now());
        return Rspta;
    }
    
    @Override
    public String cambiaEstadoReq(CobRequerimientoCartas oRequerimiento) {
        
        System.out.println(" <i> cambiaEstadoReq(CobRequerimientoCartas oRequerimiento) "+ LocalDateTime.now() );
        String Rspta = null;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_REQUERIMIENTOS.SP_CAMBIA_ESTADO(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            
            oList.add(new ParameterOracle("P_REQ_TIPO", oRequerimiento.getReqTipo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_NUMERO", oRequerimiento.getReqNumero(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_ESTADO", oRequerimiento.getReqEstado(), OracleTypes.VARCHAR, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_C_USUARIO_MOD", oRequerimiento.getcUsuarioMod(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output)); 

            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();
            
            while (resultSet.next()) {   
            
                Rspta = resultSet.getString("RPTA");
            }
            
        } catch (Exception e) {
            
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta="Error en los parametros";
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
            if (getCn() != null) {
              try { getCn().close(); } catch (Exception e) { ; }
                setCn(null);
            }
        }
        System.out.println(" <f> rcambiaEstadoReq(CobRequerimientoCartas oRequerimiento) " + LocalDateTime.now());
        return Rspta;
        
    }    

    @Override
    public String addRequerimiento(CobRequerimientoCriterios oCriterio) {
        System.out.println(" <i> addRequerimiento(CobRequerimientoCartas oRequerimiento) "+ LocalDateTime.now() );
        String Rspta = null;
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_REQUERIMIENTOS.SP_CREAR_REQUERIMIENTO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
           
            oList.add(new ParameterOracle("P_REQ_TIPO", oCriterio.getRequerimiento().getReqTipo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_NUMERO", oCriterio.getRequerimiento().getReqNumero(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_FONDO_ID", oCriterio.getRequerimiento().getFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_DVALOR_BV", oCriterio.getRequerimiento().getDvalor_bv(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_EMISION", oCriterio.getRequerimiento().getReqEmision(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_TIPO_CARTA", oCriterio.getRequerimiento().getTipoCarta(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_COMENTARIO", oCriterio.getRequerimiento().getReqComentario(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_C_USUARIO_ADD", oCriterio.getRequerimiento().getcUsuarioAdd(), OracleTypes.VARCHAR, ParameterDirection.Input));
            
            oList.add(new ParameterOracle("P_CUOTA_VEN_TOT", oCriterio.getCuota_ven_tot(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_ULT_PAGO", oCriterio.getUlt_pago(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_DIAS_SINPAGO", oCriterio.getDias_sinpago(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CANT_2CUOTA_ATRAS", oCriterio.getCant_2cuota_atras(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_FRECUENCIA_PAGO", oCriterio.getFrecuencia_pago(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CON_TCHN", oCriterio.getCon_tchn(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CON_ASHIPO", oCriterio.getCon_ashipo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CUOTA_TOTAL", oCriterio.getCuota_total(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CUOTA_GENERADA", oCriterio.getCuota_generada(), OracleTypes.NUMBER, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_CUOTA_DIA", oCriterio.getCuota_dia(), OracleTypes.NUMBER, ParameterDirection.Input));            
            
            oList.add(new ParameterOracle("P_CARTA_NOTARIAL_F1", oCriterio.getCarta_notarial_f1(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CARTA_NOTARIAL_R1", oCriterio.getCarta_notarial_r1(), OracleTypes.NUMBER, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_CARTA_NOTARIAL_O1", oCriterio.getCarta_notarial_o1(), OracleTypes.VARCHAR, ParameterDirection.Input));
            
            oList.add(new ParameterOracle("P_CARTA_NOTARIAL_F2", oCriterio.getCarta_notarial_f2(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CARTA_NOTARIAL_R2", oCriterio.getCarta_notarial_r2(), OracleTypes.NUMBER, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_CARTA_NOTARIAL_O2", oCriterio.getCarta_notarial_o2(), OracleTypes.VARCHAR, ParameterDirection.Input));            
            
            oList.add(new ParameterOracle("P_CARTA_NOTARIAL_F3", oCriterio.getCarta_notarial_f3(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CARTA_NOTARIAL_R3", oCriterio.getCarta_notarial_r3(), OracleTypes.NUMBER, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_CARTA_NOTARIAL_O3", oCriterio.getCarta_notarial_o3(), OracleTypes.VARCHAR, ParameterDirection.Input));  
            
            oList.add(new ParameterOracle("P_PROTESTO_NOTARIAL_F1", oCriterio.getProtesto_notarial_f1(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_PROTESTO_NOTARIAL_R1", oCriterio.getProtesto_notarial_r1(), OracleTypes.NUMBER, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_PROTESTO_NOTARIAL_O1", oCriterio.getProtesto_notarial_o1(), OracleTypes.VARCHAR, ParameterDirection.Input));  

            oList.add(new ParameterOracle("P_CARTA_PROTESTO_F1", oCriterio.getCarta_protesto_f1(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CARTA_PROTESTO_R1", oCriterio.getCarta_protesto_r1(), OracleTypes.NUMBER, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_CARTA_PROTESTO_O1", oCriterio.getCarta_protesto_o1(), OracleTypes.VARCHAR, ParameterDirection.Input)); 
            
            oList.add(new ParameterOracle("P_CARTA_PREJECUCION_F1", oCriterio.getCarta_prejecucion_f1(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CARTA_PREJECUCION_R1", oCriterio.getCarta_prejecucion_r1(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CARTA_PREJECUCION_O1", oCriterio.getCarta_prejecucion_o1(), OracleTypes.VARCHAR, ParameterDirection.Input));
       
            
            oList.add(new ParameterOracle("P_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output)); 
            
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();
            
            while (resultSet.next()) {   
                Rspta = resultSet.getString("RPTA");
            }
            
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
            Rspta="Error en los parametros";
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
        System.out.println(" <f> addRequerimiento(CobRequerimientoCartas oRequerimiento) " + LocalDateTime.now());
        return Rspta;
    }

    @Override
    public List<MaeInversion> allInversiones(MaeInversion oInversion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobRequerimientoCartas> allRequerimientos(CobRequerimientoCartas oRequerimiento) {
        //System.out.println(" <i> CobSeguimiento fetch " + LocalDateTime.now());
        List<CobRequerimientoCartas> listRequerimiento = new ArrayList<CobRequerimientoCartas>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_REQUERIMIENTOS.SP_BUSCAR_REQUERIMIENTO(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
                        
            oList.add(new ParameterOracle("P_FONDO_ID", oRequerimiento.getFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_DVALOR_BV", oRequerimiento.getDvalor_bv(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CUOTA_DIA1", oRequerimiento.getInversion().getNIniDiaBusq(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_CUOTA_DIA2", oRequerimiento.getInversion().getNFinDiaBusq(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_ENVIO1", oRequerimiento.getReqEnvio(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_ENVIO2", oRequerimiento.getReqEnvio2(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_RECEPCION1", oRequerimiento.getReqRecepcion(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_RECEPCION2", oRequerimiento.getReqRecepcion2(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_TNOMBRES", oRequerimiento.getInversion().getcPersonaId().getDNombres(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_ESTADO", oRequerimiento.getReqEstado(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_TIPO_CARTA", oRequerimiento.getTipoCarta(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_PRIORI", 0, OracleTypes.NUMBER, ParameterDirection.Input));            
            oList.add(new ParameterOracle("P_RESULTADO", "", OracleTypes.CURSOR, ParameterDirection.Output));
                              
        
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
                CobRequerimientoCartas oReq = new CobRequerimientoCartas();
                oReq.setReqTipo(resultSet.getString("REQ_TIPO"));
                oReq.setReqNumero(resultSet.getInt("REQ_NUMERO"));
                oReq.setFondoId(resultSet.getString("FONDO_ID"));
                oReq.setDvalor_bv(resultSet.getString("DVALOR_BV"));
                oReq.setReqEmision(resultSet.getDate("REQ_EMISION"));
                oReq.setReqEnvio(resultSet.getDate("REQ_ENVIO"));
                oReq.setReqRecepcion(resultSet.getDate("REQ_RECEPCION"));
                oReq.setTipoCarta(resultSet.getString("TIPO_CARTA"));
                oReq.setReqEstado(resultSet.getString("REQ_ESTADO"));
                oReq.setReqComentario(resultSet.getString("REQ_COMENTARIO"));
                
                CobTablas tipoCarta = new CobTablas();
                tipoCarta.setCtablaDetId(resultSet.getString("TIPO_CARTA"));                
                tipoCarta.setDdescripcion(resultSet.getString("DESC_CARTA"));
                oReq.setCarta(tipoCarta);
                
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("FONDO_ID"));
                fondo.setDFondo(resultSet.getString("D_ABREVIADO"));
                oReq.setFondo(fondo);
                
                MaeInversion inversion = new MaeInversion();
                inversion.setCInversion(resultSet.getString("DVALOR_BV"));
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                
                MaeInmueble inm = new MaeInmueble();
                inm.setDDir1(resultSet.getString("DUBIGEO"));                
                inversion.setMaeInmueble(inm);
                                
                MaePersona persona= new MaePersona();
                persona.setDApePat(resultSet.getString("TAPATERNO"));
                persona.setDApeMat(resultSet.getString("TAMATERNO"));
                persona.setDNombres(resultSet.getString("TNOMBRES"));
                inversion.setcPersonaId(persona);
                inversion.setMaeFondo(fondo);
                
                CobMaeSeguimiento seguimiento =new CobMaeSeguimiento();
                seguimiento.setMaeInversion(inversion);
                oReq.setInversion(inversion);
                oReq.setMaeSeguimiento(seguimiento);
                List<MaeEstadoCuenta> maeEstadoCuentaList = new ArrayList<MaeEstadoCuenta>();
                oReq.setMaeEstadoCuentaList(maeEstadoCuentaList);

                listRequerimiento.add(oReq);
            
                                              
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
        return listRequerimiento;
    }
    
    
    /**
     * @return the cn
     */
    public OracleConnection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(OracleConnection cn) {
        this.cn = cn;
    }
    
    @Override
    public List<CobRequerimientoCriterios> findCriterios(CobRequerimientoCriterios oCriterio) {
        //System.out.println(" <i> CobSeguimiento fetch " + LocalDateTime.now());
        List<CobRequerimientoCriterios> listCriterios = new ArrayList<CobRequerimientoCriterios>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_REQUERIMIENTOS.SP_BUSCAR_REQ_CRITERIO(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
                        
            oList.add(new ParameterOracle("P_REQ_TIPO", oCriterio.getReqTipo(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_REQ_NUMERO", oCriterio.getReqNumero(), OracleTypes.NUMBER, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_RESULTADO", "", OracleTypes.CURSOR, ParameterDirection.Output));
        
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
                CobRequerimientoCriterios oReq = new CobRequerimientoCriterios();
                oReq.setReqTipo(resultSet.getString("REQ_TIPO"));
                oReq.setReqNumero(resultSet.getInt("REQ_NUMERO"));
                oReq.setCriItem(resultSet.getInt("CRI_ITEM"));
                oReq.setCuota_ven_tot(resultSet.getInt("CUOTA_VEN_TOT"));
                oReq.setUlt_pago(resultSet.getDate("ULT_PAGO"));
                oReq.setDias_sinpago(resultSet.getInt("DIAS_SINPAGO"));
                oReq.setCant_2cuota_atras(resultSet.getInt("CANT_2CUOTA_ATRAS"));
                oReq.setFrecuencia_pago(resultSet.getInt("FRECUENCIA_PAGO"));
                oReq.setCon_tchn(resultSet.getString("CON_TCHN"));
                oReq.setCon_ashipo(resultSet.getString("CON_ASHIPO"));                
                oReq.setCuota_total(resultSet.getInt("CUOTA_TOTAL"));
                oReq.setCuota_generada(resultSet.getInt("CUOTA_GENERADA"));                
                oReq.setCuota_dia(resultSet.getInt("CUOTA_DIA"));
                oReq.setCarta_notarial_f1(resultSet.getDate("CARTA_NOTARIAL_F1"));
                oReq.setCarta_notarial_r1(resultSet.getInt("CARTA_NOTARIAL_R1"));
                oReq.setCarta_notarial_o1(resultSet.getString("CARTA_NOTARIAL_O1"));
                oReq.setCarta_notarial_f2(resultSet.getDate("CARTA_NOTARIAL_F2"));
                oReq.setCarta_notarial_r2(resultSet.getInt("CARTA_NOTARIAL_R2"));
                oReq.setCarta_notarial_o2(resultSet.getString("CARTA_NOTARIAL_O2"));   
                oReq.setCarta_notarial_f3(resultSet.getDate("CARTA_NOTARIAL_F3"));
                oReq.setCarta_notarial_r3(resultSet.getInt("CARTA_NOTARIAL_R3"));
                oReq.setCarta_notarial_o3(resultSet.getString("CARTA_NOTARIAL_O3"));
                oReq.setProtesto_notarial_f1(resultSet.getDate("PROTESTO_NOTARIAL_F1"));
                oReq.setProtesto_notarial_r1(resultSet.getInt("PROTESTO_NOTARIAL_R1"));
                oReq.setProtesto_notarial_o1(resultSet.getString("PROTESTO_NOTARIAL_O1"));
                oReq.setCarta_protesto_f1(resultSet.getDate("CARTA_PROTESTO_F1"));
                oReq.setCarta_protesto_r1(resultSet.getInt("CARTA_PROTESTO_R1"));
                oReq.setCarta_protesto_o1(resultSet.getString("CARTA_PROTESTO_O1")); 
                oReq.setCarta_prejecucion_f1(resultSet.getDate("CARTA_PREJECUCION_F1"));
                oReq.setCarta_prejecucion_r1(resultSet.getInt("CARTA_PREJECUCION_R1"));
                oReq.setCarta_prejecucion_o1(resultSet.getString("CARTA_PREJECUCION_O1"));      
           
        
                listCriterios.add(oReq);
                                              
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
        return listCriterios;
    }    

    @Override
    public List<CobRequerimientoCriterios> findReqSugerido() {
        System.out.println(" <i> findReqSugerido() fetch " + LocalDateTime.now());
        List<CobRequerimientoCriterios> listCriterios = new ArrayList<CobRequerimientoCriterios>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_REQUERIMIENTOS.SP_REQ_SUGERIDOS(?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
                        
            oList.add(new ParameterOracle("P_RESULTADO", "", OracleTypes.CURSOR, ParameterDirection.Output));
        
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
                CobRequerimientoCriterios oReq = new CobRequerimientoCriterios(); 
                
                CobRequerimientoCartas oCarta = new CobRequerimientoCartas();
                oCarta.setFondoId(resultSet.getString("COD_FONDO"));
                oCarta.setDvalor_bv(resultSet.getString("DVALOR_BV"));
                
                oCarta.setTipoCarta(resultSet.getString("TIPO_CARTA"));
                
                CobTablas tipCarta = new CobTablas();
                tipCarta.setCtablaDetId(resultSet.getString("TIPO_CARTA"));
                tipCarta.setPriori(resultSet.getInt("PRIORI"));
                oCarta.setCarta(tipCarta);
                
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("COD_FONDO"));
                fondo.setDFondo(resultSet.getString("D_FONDO"));
                oCarta.setFondo(fondo);
                
                MaeInversion inver = new MaeInversion();
                inver.setCInversion(resultSet.getString("DVALOR_BV"));
                inver.setFVencimiento(resultSet.getDate("FVCTO"));
                
                MaePersona per = new MaePersona();
                per.setDApePat(resultSet.getString("TAPATERNO"));
                per.setDApeMat(resultSet.getString("TAMATERNO"));
                per.setDNombres(resultSet.getString("TNOMBRES"));
                inver.setcPersonaId(per);
                oCarta.setInversion(inver);
                
                oReq.setRequerimiento(oCarta);
                oReq.setCuota_ven_tot(resultSet.getInt("CUOTA_VEN_TOT"));
                oReq.setUlt_pago(resultSet.getDate("ULT_PAGO"));
                oReq.setDias_sinpago(resultSet.getInt("DIAS_SINPAGO"));
                oReq.setCant_2cuota_atras(resultSet.getInt("CANT_2CUOTA_ATRAS"));
                oReq.setFrecuencia_pago(resultSet.getInt("FRECUENCIA_PAGO"));
                oReq.setCon_tchn(resultSet.getString("CON_TCHN"));
                oReq.setCon_ashipo(resultSet.getString("CON_ASHIPO"));
                oReq.setCuota_total(resultSet.getInt("CUOTA_TOTAL"));
                oReq.setCuota_generada(resultSet.getInt("CUOTA_GENERADA"));
                oReq.setCuota_dia(resultSet.getInt("CUOTA_DIA"));
                oReq.setCarta_notarial_f1(resultSet.getDate("CARTA_NOTARIAL_F1"));
                oReq.setCarta_notarial_r1(resultSet.getInt("CARTA_NOTARIAL_R1"));
                oReq.setCarta_notarial_o1(resultSet.getString("CARTA_NOTARIAL_O1"));
                oReq.setCarta_notarial_f2(resultSet.getDate("CARTA_NOTARIAL_F2"));
                oReq.setCarta_notarial_r2(resultSet.getInt("CARTA_NOTARIAL_R2"));
                oReq.setCarta_notarial_o2(resultSet.getString("CARTA_NOTARIAL_O2"));   
                oReq.setCarta_notarial_f3(resultSet.getDate("CARTA_NOTARIAL_F3"));
                oReq.setCarta_notarial_r3(resultSet.getInt("CARTA_NOTARIAL_R3"));
                oReq.setCarta_notarial_o3(resultSet.getString("CARTA_NOTARIAL_O3"));
                oReq.setProtesto_notarial_f1(resultSet.getDate("PROTESTO_NOTARIAL_F"));
                oReq.setProtesto_notarial_r1(resultSet.getInt("PROTESTO_NOTARIAL_R"));
                oReq.setProtesto_notarial_o1(resultSet.getString("PROTESTO_NOTARIAL_O"));
                oReq.setCarta_protesto_f1(resultSet.getDate("CARTA_PROTESTO_F"));
                oReq.setCarta_protesto_r1(resultSet.getInt("CARTA_PROTESTO_R"));
                oReq.setCarta_protesto_o1(resultSet.getString("CARTA_PROTESTO_O")); 
                oReq.setCarta_prejecucion_f1(resultSet.getDate("CARTA_PREJECUCION_F"));
                oReq.setCarta_prejecucion_r1(resultSet.getInt("CARTA_PREJECUCION_R"));
                oReq.setCarta_prejecucion_o1(resultSet.getString("CARTA_PREJECUCION_O"));                 
                    
                listCriterios.add(oReq);
                                              
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
        return listCriterios;
    }    

    @Override
    public List<MovimientoCartas> findCartas(MaeInversion oInversion) {
        //System.out.println(" <i> CobSeguimiento fetch " + LocalDateTime.now());
        List<MovimientoCartas> movimientoCartas = new ArrayList<MovimientoCartas>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_REQUERIMIENTOS.SP_LISTAR_CARTAS(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
                        
            oList.add(new ParameterOracle("P_FONDO_ID", oInversion.getcFONDO() , OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_DVALOR_BV", oInversion.getCInversion(), OracleTypes.VARCHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("P_RESULTADO", "", OracleTypes.CURSOR, ParameterDirection.Output));
        
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("P_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
               
                MovimientoCartas mCarta = new  MovimientoCartas();                
                mCarta.setCodfondo(resultSet.getString("CODFONDO"));
                
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("CODFONDO"));
                fondo.setDFondo(resultSet.getString("D_FONDO"));
                mCarta.setFondo(fondo);
                
                MaeInversion inversion = new MaeInversion();
                inversion.setCInversion(resultSet.getString("C_INVERSION"));
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                
                MaePersona persona= new MaePersona();
                persona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                persona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                persona.setDApePat(resultSet.getString("TAPATERNO"));
                persona.setDApeMat(resultSet.getString("TAMATERNO"));
                persona.setDNombres(resultSet.getString("TNOMBRES"));
                inversion.setcPersonaId(persona);                
                
                MaeCustodia custodia = new MaeCustodia();
                
                custodia.setInversion(inversion);
                mCarta.setCustodia(custodia);
                
                mCarta.setTipoformato(resultSet.getString("TIPOFORMATO"));
                mCarta.setTipocarta(resultSet.getString("TIPOCARTA"));
                CobTablas tipoCarta = new CobTablas();
                tipoCarta.setCtablaDetId(resultSet.getString("TIPOCARTA"));                
                tipoCarta.setDdescripcion(resultSet.getString("CARTA"));
                mCarta.setCartas(tipoCarta);
                mCarta.setTrecibido(resultSet.getString("TRECIBIDO"));
                mCarta.setFrecibido(resultSet.getDate("FRECIBIDO"));
                mCarta.setcUsuarioAdd(resultSet.getString("c_usuario_add"));
                mCarta.setFemision(resultSet.getDate("femision"));
                mCarta.setSperrecibido(resultSet.getString("sperrecibido"));
                mCarta.setScomentariov(resultSet.getString("scomentario"));
                
                CobRequerimientoCartas requerimiento = new CobRequerimientoCartas();
                requerimiento.setReqEmision(resultSet.getDate("REQ_EMISION"));
                
                CobTablas tipoReq = new CobTablas();
                tipoReq.setCtablaDetId(resultSet.getString("TIPO_CARTA"));
                tipoReq.setDdescripcion(resultSet.getString("REQ_NOMBRE"));
                requerimiento.setCarta(tipoReq);
                mCarta.setRequerimiento(requerimiento);
                            
                movimientoCartas.add(mCarta);
            
                                              
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
        
        return movimientoCartas;
        
        
    }


    
}
