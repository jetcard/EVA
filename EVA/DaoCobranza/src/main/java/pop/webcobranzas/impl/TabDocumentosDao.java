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
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeInversionEstado;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeUbigeo;
import pop.comun.dominio.TabDocumentos;
import pop.comun.dominio.TabTipoDocumento;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ITabDocumentosDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class TabDocumentosDao extends DBUtil implements ITabDocumentosDao{

    private OracleConnection cn = null;

    public TabDocumentosDao() {
    }
    
    public TabDocumentosDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public Integer generar(TabDocumentos oTabDocumentos) {
        //System.out.println(" <i> generar TabDocumentosDao " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_DOCUMENTO.SP_GENERAR_DOCUMENTO(?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = generateParameters(oTabDocumentos);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_C_TAB_DOCUMENTO_ID").getParameterInt();

        } catch (SQLException e) {
            Logger.getLogger(TabDocumentosDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(TabDocumentosDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> generar TabDocumentosDao " + LocalDateTime.now());
        return newID;
    }
    
    private List<ParameterOracle> generateParameters(TabDocumentos oTabDocumentos) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oTabDocumentos.getMaeInversion().getMaeFondo().getCFondoId()  , OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oTabDocumentos.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oTabDocumentos.getMaeInversion().getcTipoInv(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", oTabDocumentos.getMaeInversion().getcInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oTabDocumentos.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_C_TAB_DOCUMENTO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    @Override
    public ArrayList<TabDocumentos> fetchAll(TabDocumentos oTabDocumentos) {
        //System.out.println(" <i> fetchAll TabDocumentosDao " + LocalDateTime.now());
        ArrayList<TabDocumentos> lstDocumentos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_DOCUMENTO.SP_TAB_BUSCAR(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = fetchAllParameters(oTabDocumentos);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                TabDocumentos newDocumento = new TabDocumentos();
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //ubigeo
                MaeUbigeo newUbigeo = new MaeUbigeo();
                newUbigeo.setDDUbigeoDist(resultSet.getString("D_UBIGEO_D"));
                newUbigeo.setDDUbigeoProv(resultSet.getString("D_UBIGEO_P"));
                // inmueble
                MaeInmueble newInmueble = new MaeInmueble();
                newInmueble.setCInmuebleId(resultSet.getInt("C_INMUEBLE_ID"));
                newInmueble.setDDir1(resultSet.getString("D_DIR1"));
                newInmueble.setMaeUbigeo(newUbigeo);
                //newInmueble.setMaePersonaInmuebleList(listPer(oListPersonaInmueble, resultSet.getInt("C_INMUEBLE_ID"), resultSet.getInt("C_MAE_INVERSION_ID")));
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setMaeInmueble(newInmueble);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setCcodigoIdent(resultSet.getString("C_CODIGO_IDENT"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFVencimiento(resultSet.getDate("F_VENCIMIENTO"));
                newInversion.setNVencimientoDia(resultSet.getInt("N_VENCIMIENTO_DIA"));
                newInversion.setNCuotasAtrasadas(resultSet.getInt("N_CUOTAS_ATRASADAS"));
                newInversion.setNMeses(resultSet.getInt("N_MESES"));
                newInversion.setPTasa(resultSet.getFloat("P_TASA"));
                newInversion.setIInversion(resultSet.getFloat("I_INVERSION"));
                newInversion.setNDiasAnio(resultSet.getInt("N_DIAS_ANIO"));

                // inversion estado
                List<MaeInversionEstado> maeInversionEstadoList = new ArrayList<>();
                if (resultSet.getString("CANCELADO").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("CANCELADO"));
                    newInversionEstado.setEEstadoId("0002");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                if (resultSet.getString("AMPLIACION").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("AMPLIACION"));
                    newInversionEstado.setEEstadoId("0003");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                if (resultSet.getString("REFINANCIADO").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("REFINANCIADO"));
                    newInversionEstado.setEEstadoId("0004");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                if (resultSet.getString("JUDICIAL").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("JUDICIAL"));
                    newInversionEstado.setEEstadoId("0005");
                    maeInversionEstadoList.add(newInversionEstado);
                }
              if (resultSet.getString("TRANFEND").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("TRANFEND"));
                    newInversionEstado.setEEstadoId("0007");
                    maeInversionEstadoList.add(newInversionEstado);
                }
              if (resultSet.getString("TRANFREF").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("TRANFREF"));
                    newInversionEstado.setEEstadoId("0008");
                    maeInversionEstadoList.add(newInversionEstado);
                }
              if (resultSet.getString("TRANFAMP").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("TRANFAMP"));
                    newInversionEstado.setEEstadoId("0009");
                    maeInversionEstadoList.add(newInversionEstado);
                }

                newInversion.setMaeInversionEstadoList(maeInversionEstadoList);
                // documento
                newDocumento.setCtabDocumentosId(resultSet.getInt("C_TAB_DOCUMENTO_ID"));
                TabTipoDocumento tabTipoDocumento = new TabTipoDocumento();
                tabTipoDocumento.setCtabTipoDocId(resultSet.getInt("C_TAB_TIPO_DOC_ID"));
                tabTipoDocumento.setDnombre(resultSet.getString("D_NOMBRE"));
                tabTipoDocumento.setDnombreCorto(resultSet.getString("D_NOMBRE_CORTO"));
                newDocumento.setTabTipoDocumento(tabTipoDocumento);
                newDocumento.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                newDocumento.seteEstado(resultSet.getString("E_ESTADO"));
                newDocumento.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newDocumento.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newDocumento.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                newDocumento.setfUsuarioMod(resultSet.getDate("F_USUARIO_MOD"));
                                        
                newDocumento.setMaeInversion(newInversion);
                lstDocumentos.add(newDocumento);
            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
            }

        } catch (Exception e) {
            Logger.getLogger(TabTipoDocumentoDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> fetchAll TabDocumentosDao " + LocalDateTime.now());
        return lstDocumentos;
    }

    private List<ParameterOracle> fetchAllParameters(TabDocumentos oTabDocumentos) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oTabDocumentos.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oTabDocumentos.getMaeInversion().getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oTabDocumentos.getMaeInversion().getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oTabDocumentos.getMaeInversion().getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_DIAS_INI", oTabDocumentos.getMaeInversion().getNIniDiaBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_DIAS_FIN", oTabDocumentos.getMaeInversion().getNFinDiaBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_CUOTAS_INI", oTabDocumentos.getMaeInversion().getnIniBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_CUOTAS_FIN", oTabDocumentos.getMaeInversion().getnFinBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_P", oTabDocumentos.getMaeInversion().getMaeInmueble().getMaeUbigeo().getCUbigeoPad(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_D", oTabDocumentos.getMaeInversion().getMaeInmueble().getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TAB_TIPO_DOC_ID", oTabDocumentos.getTabTipoDocumento().getCtabTipoDocId(), OracleTypes.NUMBER, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    @Override
    public String flagGenerate(TabDocumentos oTabDocumentos) {
        //System.out.println(" <i> flagGenerate TabDocumentosDao " + LocalDateTime.now());
        String flag = "";
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_DOCUMENTO.SP_ACTIVAR_PRE(?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = flagGenerateParameters(oTabDocumentos);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            flag = getOutputParameter("PO_C_GENERA_DOC").getParameterString();

        } catch (SQLException e) {
            Logger.getLogger(TabDocumentosDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(TabDocumentosDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> flagGenerate TabDocumentosDao " + LocalDateTime.now());
        return flag;
    }
    
    private List<ParameterOracle> flagGenerateParameters(TabDocumentos oTabDocumentos) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oTabDocumentos.getMaeInversion().getMaeFondo().getCFondoId()  , OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oTabDocumentos.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oTabDocumentos.getMaeInversion().getcTipoInv(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_C_GENERA_DOC", 0, OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

     @Override
    public Integer generateManual(TabDocumentos oTabDocumentos) {
        //System.out.println(" <i> generateManual TabDocumentosDao " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_DOCUMENTO.SP_GENERAR_DOCUMENTO_MANUAL(?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = generateManualParameters(oTabDocumentos);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            newID = getOutputParameter("PO_C_TAB_DOCUMENTO_ID").getParameterInt();

        } catch (SQLException e) {
            Logger.getLogger(TabDocumentosDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(TabDocumentosDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> generateManual TabDocumentosDao " + LocalDateTime.now());
        return newID;
    }
    
    private List<ParameterOracle> generateManualParameters(TabDocumentos oTabDocumentos) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oTabDocumentos.getMaeInversion().getMaeFondo().getCFondoId()  , OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oTabDocumentos.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oTabDocumentos.getMaeInversion().getcTipoInv(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", oTabDocumentos.getMaeInversion().getcInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TAB_TIPO_DOC_ID", oTabDocumentos.getTabTipoDocumento().getCtabTipoDocId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oTabDocumentos.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_C_TAB_DOCUMENTO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
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
