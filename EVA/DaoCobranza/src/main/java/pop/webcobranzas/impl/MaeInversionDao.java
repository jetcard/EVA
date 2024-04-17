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
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.MaeAsesor;
import pop.comun.dominio.MaeCronograma;
import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeInversionEstado;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaePersonaInmueble;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeUbigeo;
import pop.comun.dominio.reporte.RepSaldoDeudor;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeInversionDoa;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeInversionDao extends DBUtil implements IMaeInversionDoa {

    private OracleConnection cn = null;

    public MaeInversionDao() {

    }

    public MaeInversionDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeInversion oMaeInversion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MaeInversion oMaeInversion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaeInversion oMaeInversion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeInversion> fetchAll(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchAll " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_INVERSION.SP_BUS_MAE_INVERSION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersAll(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            List<MaeTelefono> oListTel = new ArrayList<>();

            ////System.out.println("----");
            ////System.out.println(LocalDateTime.now());
            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));

                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));

                newInversion.setBcancelado(resultSet.getString("B_CANCELADO"));

                lstInversion.add(newInversion);
            }
            ////System.out.println(LocalDateTime.now());
            ////System.out.println("----------");
        } catch (Exception e) {
            Logger.getLogger(MaeInversionDao.class.getName()).log(Level.SEVERE, null, e);        
        }finally {
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
        //System.out.println(" <f> MaeInversionDao fetchAll " + LocalDateTime.now());
        return lstInversion;
    }

    @Override
    public ArrayList<MaeInversion> fetchDebtors(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //OracleResultSet resultSetB = null;
        
        //System.out.println("1----");
        try {
            // name of procedure
            String sp = "{call PKG_COB_PERSONA.SP_COB_BUSCAR_PERS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameter(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
                    //System.out.println("2----");
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            //resultSetB = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            List<MaeTelefono> oListTel = new ArrayList<>();

            ////System.out.println(LocalDateTime.now());
//            while (resultSetB.next()) {
//                MaeTelefono newTelefono = new MaeTelefono();
//                newTelefono.setCPersonaId(resultSetB.getInt("C_PERSONA_ID"));
//                newTelefono.setCTelefonoId(resultSetB.getInt("C_TELEFONO_ID"));
//                newTelefono.setANumero(resultSetB.getString("A_NUMERO"));
//                newTelefono.setBDefault(resultSetB.getString("B_DEFAULT"));
//                oListTel.add(newTelefono);
//            }
            ////System.out.println(LocalDateTime.now());
            ////System.out.println("----");
            ////System.out.println(LocalDateTime.now());
        //System.out.println("3----");            
            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //persona
                        //System.out.println("4----");
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //newPersona.setMaeTelefonoList(listTel(oListTel, newPersona.getCPersonaId()));
                newPersona.setMaeTelefonoList(oListTel);
                ////System.out.println("------ telefonos ----" + newPersona.getCPersonaId() + " " + newPersona.getMaeTelefonoList().size());

                //direccion
                //MaeDireccion newDireccion = new MaeDireccion();
                //newDireccion.setDDir1(resultSet.getString("D_DIR1"));
                //ubigeo
                MaeUbigeo newUbigeo = new MaeUbigeo();
                newUbigeo.setDDUbigeoDist(resultSet.getString("D_UBIGEO_D"));
                newUbigeo.setDDUbigeoProv(resultSet.getString("D_UBIGEO_P"));
                //newDireccion.setMaeUbigeo(newUbigeo);
                //
                        //System.out.println("5----");
                //List<MaeDireccion> maeDireccionList = new ArrayList<>();
                //maeDireccionList.add(newDireccion);
                //newPersona.setMaeDireccionList(maeDireccionList);
                // inmueble
                MaeInmueble newInmueble = new MaeInmueble();
                newInmueble.setDDir1(resultSet.getString("D_DIR1"));
                newInmueble.setMaeUbigeo(newUbigeo);
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setMaeInmueble(newInmueble);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setFVencimiento(resultSet.getDate("F_VENCIMIENTO"));
                newInversion.setNVencimientoDia(resultSet.getInt("N_VENCIMIENTO_DIA"));
                newInversion.setNCuotasAtrasadas(resultSet.getInt("N_CUOTAS_ATRASADAS"));
                newInversion.setNcompromPendi(resultSet.getInt("N_COMPROM_PENDI"));
                newInversion.setCgeneraDoc(resultSet.getString("C_GENERA_DOC"));
                newInversion.setICuota(resultSet.getFloat("I_CUOTA"));
                        //System.out.println("6----");
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
                if (resultSet.getString("COBRANZA").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("COBRANZA"));
                    newInversionEstado.setEEstadoId("0006");
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
                 if (resultSet.getString("EJUDICIAL").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("EJUDICIAL"));
                    newInversionEstado.setEEstadoId("0010");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                         //System.out.println("7----");
                //asesor
                MaeAsesor newAsesor = new MaeAsesor();
                newAsesor.setCusuarioId(resultSet.getString("C_USUARIO_ID"));  
                
                newInversion.setMaeAsesor(newAsesor);
                newInversion.setMaeInversionEstadoList(maeInversionEstadoList);
                //System.out.println("8----");
                CobRequerimientoCartas req = new CobRequerimientoCartas();
                newInversion.setRequerimiento(req);
        
                lstInversion.add(newInversion);
            }
            ////System.out.println(LocalDateTime.now());
            ////System.out.println("----------");
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
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
            if (cn != null){
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeInversionDao fetchDebtors " + LocalDateTime.now());
        return lstInversion;
    }

    @Override
    public ArrayList<MaeInversion> fetchRed(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchRed " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_PERSONA.SP_COB_BUSCAR_PERS_DET(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            resultSetB = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            List<MaePersonaInmueble> oListPersonaInmueble = new ArrayList<>();

            ////System.out.println(LocalDateTime.now());
            while (resultSetB.next()) {
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(resultSetB.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSetB.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSetB.getString("C_INVERSION"));
                newInversion.setcTipoInv(resultSetB.getString("C_TIPO_INV"));
                // inmueble
                MaeInmueble newInmueble = new MaeInmueble();
                newInmueble.setCInmuebleId(resultSetB.getInt("C_INMUEBLE_ID"));
                newInmueble.setMaeInversion(newInversion);
                // persona
                MaePersona newPersona = new MaePersona();
                newPersona.setCPersonaId(resultSetB.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSetB.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSetB.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSetB.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSetB.getString("D_NOMBRES"));
                // persona inmueble
                MaePersonaInmueble newPerInm = new MaePersonaInmueble();
                newPerInm.setMaeInmueble(newInmueble);
                newPerInm.setMaePersona(newPersona);
                newPerInm.setPInmueble(resultSetB.getFloat("P_INMUEBLE"));

                oListPersonaInmueble.add(newPerInm);
            }
            ////System.out.println(LocalDateTime.now());

            ////System.out.println("----");
            ////System.out.println(LocalDateTime.now());
            while (resultSet.next()) {
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
                newInmueble.setDDir1(resultSet.getString("D_DIR1"));
                newInmueble.setMaeUbigeo(newUbigeo);
                newInmueble.setMaePersonaInmuebleList(listPer(oListPersonaInmueble, resultSet.getInt("C_INMUEBLE_ID"), resultSet.getInt("C_MAE_INVERSION_ID")));
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setMaeInmueble(newInmueble);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
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

                lstInversion.add(newInversion);
            }
            ////System.out.println(LocalDateTime.now());
            ////System.out.println("----------");
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
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
        //System.out.println(" <f> MaeInversionDao fetchRed " + LocalDateTime.now());
        return lstInversion;
    }

    private List<MaeTelefono> listTel(List<MaeTelefono> oListBusq, int cPersId) {
        List<MaeTelefono> oList = new ArrayList<>();

        for (MaeTelefono tel : oListBusq) {
            if (tel.getCPersonaId() == (cPersId)) {
                oList.add(tel);
            }
        }
        return oList;
    }

    private List<MaePersonaInmueble> listPer(List<MaePersonaInmueble> oListBusq, Number cInmuebleId, Number cMaeInversionId) {
        List<MaePersonaInmueble> oList = new ArrayList<>();

        oListBusq.stream().filter((perInm) -> (perInm.getMaeInmueble().getCInmuebleId().equals(cInmuebleId)
                & perInm.getMaeInmueble().getMaeInversion().getcMaeInversionId().equals(cMaeInversionId))).forEach((perInm) -> {
            oList.add(perInm);
        });
        return oList;
    }

    private List<ParameterOracle> listParameter(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeInversion.getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oMaeInversion.getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_DIAS_INI", oMaeInversion.getNIniDiaBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_DIAS_FIN", oMaeInversion.getNFinDiaBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_CUOTAS_INI", oMaeInversion.getnIniBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_CUOTAS_FIN", oMaeInversion.getnFinBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_P", oMaeInversion.getMaeInmueble().getMaeUbigeo().getCUbigeoPad(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_D", oMaeInversion.getMaeInmueble().getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oMaeInversion.getEestadoCompromiso(), OracleTypes.CHAR, ParameterDirection.Input));
        //oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_D", oMaeInversion.getMaeInmueble().getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeInversion.getMaeAsesor().getCusuarioId(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOB", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParameters(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeInversion.getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oMaeInversion.getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_DIAS_INI", oMaeInversion.getNIniDiaBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_DIAS_FIN", oMaeInversion.getNFinDiaBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_CUOTAS_INI", oMaeInversion.getnIniBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_CUOTAS_FIN", oMaeInversion.getnFinBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_P", oMaeInversion.getMaeInmueble().getMaeUbigeo().getCUbigeoPad(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_D", oMaeInversion.getMaeInmueble().getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oMaeInversion.getEestadoCompromiso(), OracleTypes.CHAR, ParameterDirection.Input));
        //oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_D", oMaeInversion.getMaeInmueble().getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        //oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oMaeInversion.getMaeAsesor().getCusuarioId(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOB", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    
     private List<ParameterOracle> listParametersInm(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DIRECCION", oMaeInversion.getMaeInmueble().getDDir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oMaeInversion.getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_P", oMaeInversion.getMaeInmueble().getMaeUbigeo().getCUbigeoPad(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_D", oMaeInversion.getMaeInmueble().getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oMaeInversion.getEestadoCompromiso(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeInversion.getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
                
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOB", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
     
     private List<ParameterOracle> listParametersBusCrono(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_INI", oMaeInversion.getFinicio(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FIN", oMaeInversion.getFfin(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOB", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParametersAll(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeInversion.getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", oMaeInversion.getcInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oMaeInversion.getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID_OLD", oMaeInversion.getCInversionIdOld(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeInversion.getcPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_COLOCACION", oMaeInversion.getFColocacion(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_EMISION", oMaeInversion.getFEmision(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_VENCIMIENTO", oMaeInversion.getFVencimiento(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_INVERSION", oMaeInversion.getIInversion(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MESES", oMaeInversion.getNMeses(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_P_TASA", oMaeInversion.getPTasa(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_DIAS_ANIO", oMaeInversion.getNDiasAnio(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_INTERES", oMaeInversion.getIInteres(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_CUOTA", oMaeInversion.getICuota(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INMUEBLE_ID", oMaeInversion.getCInmuebleId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_ORIGEN_ID", oMaeInversion.getCorigenId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_CODIGO_IDENT", oMaeInversion.getCcodigoIdent(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeInversion.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

   @Override
    public RepSaldoDeudor reportDebitBalance(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao reportDebitBalance " + LocalDateTime.now());
        RepSaldoDeudor repSaldoDeudor = new RepSaldoDeudor();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        OracleResultSet resultSetB = null;
        OracleResultSet resultSetC = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_SALDO_DEUDOR.SP_SALDO_DEUDOR(?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_DebitBalance(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            
            resultSetC = getOutputParameter("PO_CURSOR_RESULTADO_C").getParameterResultSet();
            ArrayList<MaeInversion> newInversionList = new ArrayList<>();
            while (resultSetC.next()) {
                MaeInversion newMaeInversion = new MaeInversion();
                newMaeInversion.setCbanco(resultSetC.getString("BANCO"));  
                newMaeInversion.setCmoneda(resultSetC.getString("MONEDA"));
                newMaeInversion.setcFONDO(resultSetC.getString("DFONDO"));
                newMaeInversion.setCgeneraDoc(resultSetC.getString("NROCTA"));
                newInversionList.add(newMaeInversion);
            }
            
            resultSetB = getOutputParameter("PO_CURSOR_RESULTADO_B").getParameterResultSet();
            ArrayList<MaeCronograma> maeCronogramaList = new ArrayList<>();
            while (resultSetB.next()) {
                MaeCronograma newMaeCronograma = new MaeCronograma();
                newMaeCronograma.setnSecuencia(resultSetB.getInt("N_SECUENCIA"));
                newMaeCronograma.setIcapital(resultSetB.getDouble("I_CAPITAL"));
                newMaeCronograma.setIinteres(resultSetB.getDouble("I_IC"));
                Double lnic=resultSetB.getDouble("I_IC");
                Double lncap=resultSetB.getDouble("I_CAPITAL");
                Number lntot=lncap+lnic;
                newMaeCronograma.setIcuota(lntot);
                newMaeCronograma.setImora(resultSetB.getDouble("I_IM"));
                newMaeCronograma.setIca(resultSetB.getDouble("I_ICA"));
                maeCronogramaList.add(newMaeCronograma);
            }

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                //newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFVencimiento(resultSet.getDate("F_VENCIMIENTO"));
                newInversion.setCmoneda(resultSet.getString("MONEDA"));
                if (resultSet.getString("MONEDA").contains("SOLES")){
                    newInversion.setSimbolo("S/.");
                }else{
                    newInversion.setSimbolo("$");
                }
                
                newInversion.setPTasa(resultSet.getDouble("P_TASA"));
                newInversion.setPTasa2(resultSet.getDouble("P_TASA2"));
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
                    
                repSaldoDeudor.setMaeInversion(newInversion);

                // repSaldoDeudor
                repSaldoDeudor.setFactual(resultSet.getDate("FACTUAL"));
                repSaldoDeudor.setFfutura(resultSet.getDate("FFUTURA"));
                repSaldoDeudor.setFultCuota(resultSet.getDate("F_UTL_CUOT"));

                repSaldoDeudor.setIcapFut(resultSet.getDouble("I_CAP_FUT"));
                repSaldoDeudor.setIicFut(resultSet.getDouble("I_IC_FUT"));

                repSaldoDeudor.setIcapAtra(resultSet.getDouble("I_CAP_ATRA"));
                repSaldoDeudor.setIicAtra(resultSet.getDouble("I_IC_ATRA"));
                repSaldoDeudor.setIicAAtra(resultSet.getDouble("I_ICA_ATRA"));
                repSaldoDeudor.setIimAtra(resultSet.getDouble("I_IM_ATRA"));
                repSaldoDeudor.setIsaldoFavor(resultSet.getDouble("I_SALDO_FAVOR"));
                repSaldoDeudor.setIcargCuoAtra(resultSet.getDouble("I_CARG_CUO_ATRA"));
                repSaldoDeudor.setIgastLegalFut(resultSet.getDouble("I_GAST_LEGAL_FUT"));
                repSaldoDeudor.setIgastAdmin(resultSet.getDouble("I_GAST_ADMIN"));
                repSaldoDeudor.setnDiasPago(resultSet.getInt("DIASPAGO") );
                repSaldoDeudor.setnCuotas(resultSet.getInt("N_MESES"));
                repSaldoDeudor.setNtotAtra(resultSet.getDouble("N_TOT_ATRA"));
                repSaldoDeudor.setNtotFut(resultSet.getDouble("N_TOT_FUT"));
                repSaldoDeudor.setNfavorFondo(resultSet.getDouble("N_FAVOR_FONDO"));
                repSaldoDeudor.setNtotDebe(resultSet.getDouble("N_TOT_DEBE"));
                repSaldoDeudor.setLs_estadoCrono(resultSet.getString("ESTCRONO"));
                repSaldoDeudor.setnTasaDia(resultSet.getDouble("TASDIA"));
                repSaldoDeudor.setnTasa15Dia(resultSet.getDouble("TAS15DIA"));
                repSaldoDeudor.setxFLGNew(resultSet.getString("CALC8515"));
                repSaldoDeudor.setImpresionSD(resultSet.getString("IMPRESO_SD"));
                repSaldoDeudor.setUltcuota(resultSet.getString("ULTIMACUOTA"));
                repSaldoDeudor.setFproxcuota(resultSet.getDate("FECHA_PROX")); 
            }
            repSaldoDeudor.setMaeInversionList(newInversionList);
            repSaldoDeudor.setMaeCronogramaList(maeCronogramaList);

        } catch (Exception e) {
            Logger.getLogger(MaeInversionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
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
        return repSaldoDeudor;
    }

    private List<ParameterOracle> listParameters_DebitBalance(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeInversion.getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oMaeInversion.getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_PROCESO", oMaeInversion.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO_B", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO_C", null, OracleTypes.CURSOR, ParameterDirection.Output));
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

    @Override
    public MaeInversion fecthInvDoc(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fecthInvDoc " + LocalDateTime.now());
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        //inversion
        MaeInversion newInversion = new MaeInversion();
        try {

            // name of procedure
            String sp = "{call PKG_MAE_INVERSION.SP_BUS_INV_DOC(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_fecthInvDoc(oMaeInversion);
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

                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCcodigoIdent(resultSet.getString("C_CODIGO_IDENT"));
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFVencimiento(resultSet.getDate("F_VENCIMIENTO"));
                newInversion.setNMeses(resultSet.getInt("N_MESES"));
                newInversion.setPTasa(resultSet.getDouble("P_TASA"));
                newInversion.setICuota(resultSet.getDouble("I_CUOTA"));
                newInversion.setIInversion(resultSet.getDouble("I_INVERSION"));
                
                newInversion.setNCuotasAtrasadas(resultSet.getInt("N_CUOTAS_ATRASADAS"));
                newInversion.setfFinBusq(resultSet.getDate("F_ULT_CUO_VEN"));

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
                MaeInmueble newInmueble = new MaeInmueble();
                newInmueble.setCInmuebleId(resultSet.getInt("C_INMUEBLE_ID"));
                newInmueble.setADir1(resultSet.getString("D_DIR1"));
                //
                MaeDireccion newDireccion = new MaeDireccion();
                newDireccion.setADir1(resultSet.getString("D_DIR1"));
                //
                MaeUbigeo newUbigeo = new MaeUbigeo();
                newUbigeo.setDDUbigeoDist(resultSet.getString("D_UBIGEO_D"));
                newUbigeo.setDDUbigeoProv(resultSet.getString("D_UBIGEO_P"));
                newDireccion.setMaeUbigeo(newUbigeo);
                newInmueble.setMaeUbigeo(newUbigeo);
                newInversion.setMaeInmueble(newInmueble);
                List<MaeDireccion> oListDir = new ArrayList<>();
                oListDir.add(newDireccion);
                newPersona.setMaeDireccionList(oListDir);

                newInversion.setcPersonaId(newPersona);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeInversionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
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
        //System.out.println(" <f> MaeInversionDao fecthInvDoc " + LocalDateTime.now());

        return newInversion;
    }

    private List<ParameterOracle> listParameters_fecthInvDoc(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeInversion.getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FECHA_CORTE", oMaeInversion.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    @Override
    public ArrayList<MaeInversion> fetchInm(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchRed " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_DIRECCION.SP_BUSCA_DIREPROP(?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersInm(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            resultSetB = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            List<MaePersonaInmueble> oListPersonaInmueble = new ArrayList<>();

            ////System.out.println(LocalDateTime.now());
            while (resultSetB.next()) {
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(resultSetB.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSetB.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSetB.getString("C_INVERSION"));
                newInversion.setcTipoInv(resultSetB.getString("C_TIPO_INV"));
                // inmueble
                MaeInmueble newInmueble = new MaeInmueble();
                newInmueble.setCInmuebleId(resultSetB.getInt("C_INMUEBLE_ID"));
                newInmueble.setMaeInversion(newInversion);
                // persona
                MaePersona newPersona = new MaePersona();
                newPersona.setCPersonaId(resultSetB.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSetB.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSetB.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSetB.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSetB.getString("D_NOMBRES"));
                // persona inmueble
                MaePersonaInmueble newPerInm = new MaePersonaInmueble();
                newPerInm.setMaeInmueble(newInmueble);
                newPerInm.setMaePersona(newPersona);
                newPerInm.setPInmueble(resultSetB.getFloat("P_INMUEBLE"));

                oListPersonaInmueble.add(newPerInm);
            }
            ////System.out.println(LocalDateTime.now());

            ////System.out.println("----");
            ////System.out.println(LocalDateTime.now());
            while (resultSet.next()) {
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
                newInmueble.setDDir1(resultSet.getString("D_DIR1"));
                newInmueble.setMaeUbigeo(newUbigeo);
                newInmueble.setMaePersonaInmuebleList(listPer(oListPersonaInmueble, resultSet.getInt("C_INMUEBLE_ID"), resultSet.getInt("C_MAE_INVERSION_ID")));
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setMaeInmueble(newInmueble);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
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

                lstInversion.add(newInversion);
            }
            ////System.out.println(LocalDateTime.now());
            ////System.out.println("----------");
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
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
        //System.out.println(" <f> MaeInversionDao fetchRed " + LocalDateTime.now());
        return lstInversion;
  
    }
    
     @Override
    public ArrayList<MaeInversion> fetchListCronoEst(MaeInversion oMaeInversion) {
        //System.out.println(" <i> MaeInversionDao fetchRed " + LocalDateTime.now());
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        OracleResultSet resultSetB = null;
        
        try {
            // name of procedure
            String sp = "{call PKG_MAE_CRONOGRAMA.SP_BUS_ESTA_CRONOGRAMA(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersBusCrono(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            
            resultSetB = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            List<MaePersonaInmueble> oListPersonaInmueble = new ArrayList<>();

               
            ////System.out.println(LocalDateTime.now());
            while (resultSetB.next()) {
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(resultSetB.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSetB.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSetB.getString("C_INVERSION"));
                newInversion.setcTipoInv(resultSetB.getString("C_TIPO_INV"));
                // inmueble
                MaeInmueble newInmueble = new MaeInmueble();
                newInmueble.setCInmuebleId(resultSetB.getInt("C_INMUEBLE_ID"));
                newInmueble.setMaeInversion(newInversion);
                newInmueble.setDDir1(resultSetB.getString("D_DIR1"));
                
                // persona
                MaePersona newPersona = new MaePersona();
                newPersona.setCPersonaId(resultSetB.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSetB.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSetB.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSetB.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSetB.getString("D_NOMBRES"));
                
                // persona inmueble
                MaePersonaInmueble newPerInm = new MaePersonaInmueble();
                newPerInm.setMaeInmueble(newInmueble);
                newPerInm.setMaePersona(newPersona);
                newPerInm.setPInmueble(resultSetB.getFloat("P_INMUEBLE"));

                oListPersonaInmueble.add(newPerInm);
            }
            
               while (resultSet.next()) {
                   MaeInversion newInversion = new MaeInversion();
                // cronograma
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("TCHN"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcFONDO(resultSet.getString("FONDO"));
                newInversion.setcESTADOCRONO(resultSet.getString("ESTADOCRONO"));
                newInversion.setnCUOTASPENDXGENERAR(resultSet.getInt("CUOTASPENDXGENERAR"));
                newInversion.setnTOTALCUOTAS(resultSet.getInt("TOTALCUOTAS"));
                newInversion.setNCuotasAtrasadas(resultSet.getInt("CUOTASATRASADAS"));
                newInversion.setnCUOTASPAGADAS(resultSet.getInt("CUOTASPAGADAS"));
                newInversion.setdFULTDEPOSITO(resultSet.getDate("FULTDEPOSITO"));
                newInversion.setdFpago(resultSet.getDate("F_PAGO"));
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFVencimiento(resultSet.getDate("F_VENCIMIENTO"));
                newInversion.setNMeses(resultSet.getInt("N_MESES"));
                newInversion.setPTasa(resultSet.getFloat("P_TASA"));
                newInversion.setIInversion(resultSet.getFloat("I_INVERSION"));
                newInversion.setNDiasAnio(resultSet.getInt("N_DIAS_ANIO"));
                
                MaeInmueble newInmueble = new MaeInmueble();
                newInmueble.setMaePersonaInmuebleList(listPer(oListPersonaInmueble, resultSet.getInt("C_INMUEBLE_ID"), resultSet.getInt("C_MAE_INVERSION_ID")));                
                newInversion.setMaeInmueble(newInmueble);
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
                
                lstInversion.add(newInversion);
                     }
              ////System.out.println(LocalDateTime.now());

            
            ////System.out.println(LocalDateTime.now());
            ////System.out.println("----------");
        } catch (Exception e) {
            Logger.getLogger(MaeInversion.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
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
        //System.out.println(" <f> MaeInversionDao fetchRed " + LocalDateTime.now());
        return lstInversion;
  
    }

    
     @Override
    public ArrayList<MaeInversion> fetchAllLegal(MaeInversion oMaeInversion) {
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_COB_BUSCAR_PERS_DET_LEG(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersLegal(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            List<MaePersonaInmueble> oListPersonaInmueble = new ArrayList<>();

            ////System.out.println("----");
            ////System.out.println(LocalDateTime.now());
            while (resultSet.next()) {
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
                newInmueble.setDDir1(resultSet.getString("D_DIR1"));
                newInmueble.setMaeUbigeo(newUbigeo);
                newInmueble.setMaePersonaInmuebleList(listPer(oListPersonaInmueble, resultSet.getInt("C_INMUEBLE_ID"), resultSet.getInt("C_MAE_INVERSION_ID")));
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setMaeInmueble(newInmueble);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFVencimiento(resultSet.getDate("F_VENCIMIENTO"));
                newInversion.setNVencimientoDia(resultSet.getInt("N_VENCIMIENTO_DIA"));
                newInversion.setNMeses(resultSet.getInt("N_MESES"));
                newInversion.setPTasa(resultSet.getFloat("P_TASA"));
                newInversion.setIInversion(resultSet.getFloat("I_INVERSION"));
                newInversion.setNDiasAnio(resultSet.getInt("N_DIAS_ANIO"));
                newInversion.setiCronograma(resultSet.getFloat("I_SALDO"));
                newInversion.setnTOTALCUOTAS(resultSet.getFloat("NCUOTASLEGAL"));
                newInversion.setFVencimiento(resultSet.getDate("F_PAGO"));
                newInversion.setnCUOTASPENDXGENERAR(resultSet.getFloat("MONTOPEN"));
                newInversion.setpTasaMor(resultSet.getFloat("P_TASAMORA"));
                newInversion.setCmoneda(resultSet.getString("MONEDA"));
                lstInversion.add(newInversion);
            }
            ////System.out.println(LocalDateTime.now());
            ////System.out.println("----------");
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
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
        //System.out.println(" <f> MaeInversionDao fetchRed " + LocalDateTime.now());
        return lstInversion;
    }

    @Override
    public ArrayList<MaeInversion> fetchAllJudi(MaeInversion oMaeInversion) {
        ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        OracleResultSet resultSetB = null;

        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_DATOS_JUDI(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersLegal_2(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            List<MaePersonaInmueble> oListPersonaInmueble = new ArrayList<>();

            ////System.out.println("----");
            ////System.out.println(LocalDateTime.now());
            while (resultSet.next()) {
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
                newInmueble.setDDir1(resultSet.getString("D_DIR1"));
                newInmueble.setMaeUbigeo(newUbigeo);
                newInmueble.setMaePersonaInmuebleList(listPer(oListPersonaInmueble, resultSet.getInt("C_INMUEBLE_ID"), resultSet.getInt("C_MAE_INVERSION_ID")));
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcPersonaId(newPersona);
                newInversion.setMaeInmueble(newInmueble);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFVencimiento(resultSet.getDate("F_VENCIMIENTO"));
                newInversion.setNVencimientoDia(resultSet.getInt("N_VENCIMIENTO_DIA"));
                newInversion.setNMeses(resultSet.getInt("N_MESES"));
                newInversion.setPTasa(resultSet.getFloat("P_TASA"));
                newInversion.setIInversion(resultSet.getFloat("I_INVERSION"));
                newInversion.setNDiasAnio(resultSet.getInt("N_DIAS_ANIO"));
                newInversion.setiCronograma(resultSet.getFloat("I_SALDO"));
                newInversion.setnCUOTASPENDXGENERAR(resultSet.getFloat("MONTOPEN"));
                newInversion.setpTasaMor(resultSet.getFloat("P_TASAMORA"));
                newInversion.setIInteres(resultSet.getFloat("INTERES"));
                newInversion.setiMora(resultSet.getFloat("MMORA"));
                newInversion.setCmoneda(resultSet.getString("MONEDA"));
                lstInversion.add(newInversion);
            }
            ////System.out.println(LocalDateTime.now());
            ////System.out.println("----------");
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
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
        //System.out.println(" <f> MaeInversionDao fetchRed " + LocalDateTime.now());
        return lstInversion;
    }
    
     private List<ParameterOracle> listParametersLegal(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeInversion.getcMaeInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
     private List<ParameterOracle> listParametersLegal_2(MaeInversion oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInversion.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeInversion.getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeInversion.getcMaeInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA",oMaeInversion.getFEmision(),OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    @Override
    public ArrayList<MaeInversion> fetchResumen(Number oMaeInversion) {
            ArrayList<MaeInversion> lstInversion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_SALDO_DEUDOR.SP_RESUMEN_TCHN(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersResumen(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            //System.out.println("--<>--");
            ////System.out.println(LocalDateTime.now());
            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFVencimiento(resultSet.getDate("F_VENCIMIENTO"));
                newInversion.setNMeses(resultSet.getInt("N_MESES"));
                newInversion.setPTasa(resultSet.getFloat("P_TASA"));
                newInversion.setPTasa2(resultSet.getFloat("P_TASA2"));
                newInversion.setCmoneda(resultSet.getString("MONEDA"));
                if (resultSet.getString("MONEDA").contains("SOLES")){
                    newInversion.setSimbolo("S/.");
                }else{
                    newInversion.setSimbolo("$");
                }
                
                newInversion.setIInversion(resultSet.getDouble("I_INVERSION"));
                //System.out.println("I_INVERSION>1: "+resultSet.getString("I_INVERSION"));
                //System.out.println("I_INVERSION>2: "+resultSet.getDouble("I_INVERSION"));
                //newInversion.setRequerimiento(new CobRequerimientoCartas());
                lstInversion.add(newInversion);
            }
            //System.out.println(LocalDateTime.now());
            ////System.out.println("----------");
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
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
        //System.out.println(" <f> MaeInversionDao fetchRed " + LocalDateTime.now());
        return lstInversion;
    }
    
    private List<ParameterOracle> listParametersResumen(Number oMaeInversion) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeInversion, OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", "0001", OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

}
