/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeDepositoPago;
import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeEstadoCuentaDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeEstadoCuentaDao extends DBUtil implements IMaeEstadoCuentaDao {

    private OracleConnection cn = null;

    public MaeEstadoCuentaDao() {

    }

    public MaeEstadoCuentaDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public Integer insert(MaeEstadoCuenta oMaeEstadoCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MaeEstadoCuenta oMaeEstadoCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaeEstadoCuenta oMaeEstadoCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeEstadoCuenta> fetchAll(MaeEstadoCuenta oMaeEstadoCuenta) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAll " + LocalDateTime.now());
        ArrayList<MaeEstadoCuenta> lstEstCta = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_PERSONA.SP_GENERAR_ESTAD_CUENTA(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busEstCta(oMaeEstadoCuenta);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFVencimiento(resultSet.getDate("F_VENCIMIENTO"));
                newInversion.setPTasa(resultSet.getDouble("P_TASA"));
                newInversion.setIInversion(resultSet.getDouble("I_INVERSION"));
                
                newInversion.setMaeFondo(newFondo);
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(Integer.parseInt(resultSet.getString("C_PERSONA_ID")));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //newPersona.setCTipoDocumento(resultSet.getString("D_DESC_CORTA"));
                MaeDireccion newDireccion = new MaeDireccion();
                newDireccion.setADir1(resultSet.getString("D_DIR1"));
                //
                MaeUbigeo newUbigeo = new MaeUbigeo();
                newUbigeo.setDDUbigeoDist(resultSet.getString("D_UBIGEO_D"));
                newUbigeo.setDDUbigeoProv(resultSet.getString("D_UBIGEO_P"));
                newDireccion.setMaeUbigeo(newUbigeo);
                List<MaeDireccion> oListDir = new ArrayList<>();
                oListDir.add(newDireccion);
                newPersona.setMaeDireccionList(oListDir);

                newInversion.setcPersonaId(newPersona);
                //EstaCuenta
                
                MaeEstadoCuenta newEstadoCuenta = new MaeEstadoCuenta();
                newEstadoCuenta.setCinversion(resultSet.getString("C_INVERSION"));
                newEstadoCuenta.setNsecuencia(resultSet.getInt("N_SECUENCIA"));
                newEstadoCuenta.setFoperacion(resultSet.getDate("F_OPERACION"));
                newEstadoCuenta.setCconceptoId(resultSet.getString("C_CONCEPTO_ID"));
                newEstadoCuenta.setDcuota(resultSet.getString("D_CUOTA"));
                newEstadoCuenta.setDdescriocion(resultSet.getString("D_DESCRIPCION"));
                newEstadoCuenta.setIabono(resultSet.getFloat("I_ABONO"));
                newEstadoCuenta.setIcargo(resultSet.getFloat("I_CARGO"));
                newEstadoCuenta.setIsaldo(resultSet.getFloat("I_SALDO"));
                newEstadoCuenta.setNcuota(resultSet.getInt("N_CUOTA"));
                newEstadoCuenta.setNdeposito(resultSet.getInt("N_DEPOSITO"));
                newEstadoCuenta.setbTranslado(resultSet.getString("B_TRANSLADO"));
                newEstadoCuenta.seteEstado(resultSet.getString("E_ESTADO"));
                newEstadoCuenta.setMaeInversion(newInversion);
                newEstadoCuenta.setMoneda(resultSet.getString("MONEDA"));
                if (resultSet.getString("MONEDA").equals("SOLES")){
                    newEstadoCuenta.setSimbolo("S/.");
                }else{
                   newEstadoCuenta.setSimbolo("$.");
                }    
                lstEstCta.add(newEstadoCuenta);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAll " + LocalDateTime.now());
        return lstEstCta;
    }

    @Override
    public ArrayList<MaeEstadoCuenta> fetchAllB(MaeEstadoCuenta oMaeEstadoCuenta) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllB " + LocalDateTime.now());
        ArrayList<MaeEstadoCuenta> lstEstCta = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_PERSONA.SP_GENERAR_ESTAD_CUENTA_B(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busEstCta(oMaeEstadoCuenta);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                //newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setMaeFondo(newFondo);
                //EstaCuenta
                MaeEstadoCuenta newEstadoCuenta = new MaeEstadoCuenta();
                newEstadoCuenta.setCinversion(resultSet.getString("C_INVERSION"));
                newEstadoCuenta.setNsecuencia(resultSet.getInt("N_SECUENCIA"));
                newEstadoCuenta.setFoperacion(resultSet.getDate("F_OPERACION"));
                newEstadoCuenta.setCconceptoId(resultSet.getString("C_CONCEPTO_ID"));
                newEstadoCuenta.setDcuota(resultSet.getString("D_CUOTA"));
                newEstadoCuenta.setDdescriocion(resultSet.getString("D_DESCRIPCION"));
                newEstadoCuenta.setIabono(resultSet.getFloat("I_ABONO"));
                newEstadoCuenta.setIcargo(resultSet.getFloat("I_CARGO"));
                newEstadoCuenta.setIsaldo(resultSet.getFloat("I_SALDO"));
                newEstadoCuenta.setNcuota(resultSet.getInt("N_CUOTA"));
                newEstadoCuenta.setFondo(newFondo);   
                newEstadoCuenta.setMaeInversion(newInversion);
                newEstadoCuenta.setNdeposito(resultSet.getInt("N_DEPOSITO"));
                newEstadoCuenta.setbTranslado(resultSet.getString("B_TRANSLADO"));
                newEstadoCuenta.seteEstado(resultSet.getString("E_ESTADO"));
                
                newEstadoCuenta.setIcapital(resultSet.getFloat("I_CAPITAL"));
                newEstadoCuenta.setIinteres(resultSet.getFloat("I_INTERES"));
                newEstadoCuenta.setMoneda(resultSet.getString("MONEDA"));
                if (resultSet.getString("MONEDA").equals("SOLES")){
                    newEstadoCuenta.setSimbolo("S/.");
                }else{
                   newEstadoCuenta.setSimbolo("$.");
                }    
                lstEstCta.add(newEstadoCuenta);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllB " + LocalDateTime.now());
        return lstEstCta;
    }

    @Override
    public ArrayList<MaeDeposito> fetchAllC(MaeEstadoCuenta oMaeEstadoCuenta) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllC " + LocalDateTime.now());
        ArrayList<MaeDeposito> lstEstCta = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_PERSONA.SP_GENERAR_ESTAD_CUENTA_C(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busEstCta(oMaeEstadoCuenta);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            MaeDeposito newDeposito;
            List<MaeDepositoPago> newDepPagList = null;
            
            int nDepo=1;
            int nDepoOld=0;
            
            while (resultSet.next()) {
                
                MaeDepositoPago newDepPag= new MaeDepositoPago();
                nDepo=resultSet.getInt("N_DEPOSITO");
                if(resultSet.getString("C_CONCEPTO_ID").equals("0000"))
                {
                    newDeposito=new MaeDeposito();
                    newDeposito.setcMaeDepositoId(resultSet.getInt("C_MAE_DEPOSITO_ID"));
                    newDeposito.setnDeposito(resultSet.getInt("N_DEPOSITO"));
                    newDeposito.setDdescripcion(resultSet.getString("DDESCRIPCION"));
                    newDeposito.setIBcoDepositado(resultSet.getFloat("I_BCO_DEPOSITADO"));
                    newDeposito.setFBcoDeposito(resultSet.getDate("F_BCO_DEPOSITO"));
                    newDeposito.setMoneda(resultSet.getString("MONEDA"));
                     if (resultSet.getString("MONEDA").equals("SOLES")){
                         newDeposito.setSimbolo("S/.");
                     }else{
                        newDeposito.setSimbolo("$.");
                     }   
                    
                    newDepPagList=new ArrayList<>();
                    if (nDepo!=nDepoOld)
                    {
                       newDeposito.setMaeDepositoPagoList(newDepPagList);
                       lstEstCta.add(newDeposito);
                       nDepoOld=nDepo;
                    }
                }
                if(!resultSet.getString("C_CONCEPTO_ID").equals("0000"))
                {
                    newDepPag.setDdescripcion(resultSet.getString("DDESCRIPCION"));
                    newDepPag.setcMaeDepositoId(resultSet.getInt("C_MAE_DEPOSITO_ID"));
                    newDepPag.setnDeposito(resultSet.getInt("N_DEPOSITO"));
                    newDepPag.setIPagado(resultSet.getFloat("I_BCO_DEPOSITADO"));
                    newDepPag.setNCuota(resultSet.getInt("N_CUOTA"));
                    newDepPag.setCConceptoId(resultSet.getString("C_CONCEPTO_ID"));
                     newDepPag.setMoneda(resultSet.getString("MONEDA"));
                     if (resultSet.getString("MONEDA").equals("SOLES")){
                         newDepPag.setSimbolo("S/.");
                     }else{
                        newDepPag.setSimbolo("$.");
                     }
                    newDepPagList.add(newDepPag);
                }
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllC " + LocalDateTime.now());
        return lstEstCta;
    }

    @Override
    public ArrayList<MaeEstadoCuenta> fetchAllD(MaeEstadoCuenta oMaeEstadoCuenta) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllB " + LocalDateTime.now());
        ArrayList<MaeEstadoCuenta> lstEstCta = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_PERSONA.SP_GENERAR_ESTAD_CUENTA_D(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busEstCta(oMaeEstadoCuenta);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                //newFondo.setDFondo(resultSet.getString("D_FONDO"));
                
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setMaeFondo(newFondo);
                
                //EstaCuenta
                MaeEstadoCuenta newEstadoCuenta = new MaeEstadoCuenta();
                newEstadoCuenta.setCinversion(resultSet.getString("C_INVERSION"));
                newEstadoCuenta.setNsecuencia(resultSet.getInt("N_SECUENCIA"));
                newEstadoCuenta.setFoperacion(resultSet.getDate("F_OPERACION"));
                newEstadoCuenta.setCconceptoId(resultSet.getString("C_CONCEPTO_ID"));
                newEstadoCuenta.setDcuota(resultSet.getString("D_CUOTA"));
                newEstadoCuenta.setDdescriocion(resultSet.getString("D_DESCRIPCION"));
                newEstadoCuenta.setIabono(resultSet.getFloat("I_ABONO"));
                newEstadoCuenta.setIcargo(resultSet.getFloat("I_CARGO"));
                newEstadoCuenta.setIsaldo(resultSet.getFloat("I_SALDO"));
                newEstadoCuenta.setNcuota(resultSet.getInt("N_CUOTA"));
                newEstadoCuenta.setFondo(newFondo);   
                newEstadoCuenta.setMaeInversion(newInversion);
                newEstadoCuenta.setNdeposito(resultSet.getInt("N_DEPOSITO"));
                newEstadoCuenta.setbTranslado(resultSet.getString("B_TRANSLADO"));
                newEstadoCuenta.seteEstado(resultSet.getString("E_ESTADO"));
                
                newEstadoCuenta.setIcapital(resultSet.getFloat("I_CAPITAL"));
                newEstadoCuenta.setIinteres(resultSet.getFloat("I_INTERES"));
                newEstadoCuenta.setImora(resultSet.getFloat("i_MORA"));
                newEstadoCuenta.setMoneda(resultSet.getString("MONEDA"));
                
                if (resultSet.getString("MONEDA").equals("SOLES")){
                    newEstadoCuenta.setSimbolo("S/.");
                }else{
                   newEstadoCuenta.setSimbolo("$.");
                }    
                lstEstCta.add(newEstadoCuenta);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllB " + LocalDateTime.now());
        return lstEstCta;
    }
    
    
    @Override
    public ArrayList<CobTchn> fetchAllTchn(CobTchn oCobTchn) {
        //System.out.println(" <i> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        ArrayList<CobTchn> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_PERSONA.SP_BUSCAR_EST_CUENTA_LIST(?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus(oCobTchn);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setMaeFondo(newFondo);
                
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(Integer.parseInt(resultSet.getString("C_PERSONA_ID")));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //newPersona.setCTipoDocumento(resultSet.getString("D_DESC_CORTA"));

                newInversion.setcPersonaId(newPersona);

                //tchn
                CobTchn newCobTchn = new CobTchn();
                newCobTchn.setFondo(newFondo);
                newCobTchn.setMaeInversion(newInversion);
                newCobTchn.setNroCuotasAtrasadas(resultSet.getInt("NROCUOTAS_ATRAS"));
                newCobTchn.setFechaUltDeposito(resultSet.getDate("FEC_ULT_DEPO"));
                newCobTchn.setTotalDeposito(resultSet.getFloat("TOT_DEPOS"));
                newCobTchn.setCancelado(resultSet.getString("CANCELADO"));
                newCobTchn.setAmpliado(resultSet.getString("AMPLIACION"));
                newCobTchn.setRefinanciado(resultSet.getString("REFINANCIADO"));
                newCobTchn.setJudicial(resultSet.getString("JUDICIAL"));
                newCobTchn.setTransfendosado(resultSet.getString("TRANFEND"));
                newCobTchn.setTransfrefin(resultSet.getString("TRANFREF"));
                newCobTchn.setTransfAmpl(resultSet.getString("TRANFAMP"));
                newCobTchn.setEjudicial(resultSet.getString("EJUDICIAL"));
                System.out.println(resultSet.getString("EJUDICIAL"));
                newCobTchn.setMoneda(resultSet.getString("MONEDA"));
                if (resultSet.getString("MONEDA").equals("SOLES")){
                    newCobTchn.setSimbolo("S/.");
                }else{
                    newCobTchn.setSimbolo("$.");
                }
                lstTchn.add(newCobTchn);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }

    private List<ParameterOracle> listParameters_bus(CobTchn oCobTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobTchn.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oCobTchn.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oCobTchn.getMaeInversion().getCInversion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oCobTchn.getMaeInversion().getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oCobTchn.getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_P", oCobTchn.getMaeInversion().getMaeInmueble().getMaeUbigeo().getCUbigeoPad(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_D", oCobTchn.getMaeInversion().getMaeInmueble().getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        //        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_busEstCta(MaeEstadoCuenta oMaeEstadoCuenta) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeEstadoCuenta.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oMaeEstadoCuenta.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_PROCESO", oMaeEstadoCuenta.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaeEstadoCuenta.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        //        
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

