/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobCompromiso;
import pop.comun.dominio.CobLlamadas;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobSeguimiento;
import pop.comun.dominio.CobSeguimientoDet;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ICobMaeSeguimientoDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class CobMaeSeguimientoDao extends DBUtil implements ICobMaeSeguimientoDao {
    private OracleConnection cn = null;

    public CobMaeSeguimientoDao() {

    }

    public CobMaeSeguimientoDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(CobMaeSeguimiento oCobMaeSeguimiento) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //System.out.println(" <i> insertar CobMaeSeguimientoDao " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_MAE_SEGUIMIENTO.SP_INS_COB_MAE_SEGUIMIENTO(?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oCobMaeSeguimiento);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_C_MAE_SEGUIMIENTO_ID").getParameterInt();

        } catch (SQLException e) {
            System.out.println(e);
            //Logger.getLogger(CobMaeSeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> insertar CobMaeSeguimientoDao " + LocalDateTime.now());
        return newID;

    }

    @Override
    public void update(CobMaeSeguimiento oCobMaeSeguimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String cobSeguimientoId,String codLlamadaId,String codLlamada,String usuario) {
        boolean res = false;
        OracleCallableStatement cmd = null;
        try {
            String sp = "{call PKG_COB_MAE_SEGUIMIENTO.SP_DEL_COB_MAE_SEGUIMIENTO(?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = deleteParameters(cobSeguimientoId,codLlamadaId,codLlamada,usuario);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            String sres = getOutputParameter("PO_RESULTADO").getParameterString();
            
            if (sres.trim().equals("0"))
            {
                res=true;
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
        return res;    
    }

    @Override
    public CobMaeSeguimiento fetch(CobMaeSeguimiento oCobMaeSeguimiento) {
        //System.out.println(" <i> CobMaeSeguimiento fetch " + LocalDateTime.now());
        CobMaeSeguimiento cobMaeSeguimiento = new CobMaeSeguimiento();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_MAE_SEGUIMIENTO.SP_BUS_COB_MAE_SEGUIMIENTO(?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersFetch(oCobMaeSeguimiento);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // cobMaeSeguimiento
                cobMaeSeguimiento.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                MaeInversion inversion = new MaeInversion();
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                inversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                inversion.setCInversion(resultSet.getString("C_INVERSION"));
                cobMaeSeguimiento.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId("C_FONDO_ID");
                inversion.setMaeFondo(fondo);
                cobMaeSeguimiento.setMaeInversion(inversion);
            }
            //System.out.println(LocalDateTime.now());
        } catch (Exception e) {
            System.out.println(e);
            //Logger.getLogger(MaeDireccionDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> CobMaeSeguimiento fetch " + LocalDateTime.now());
        return cobMaeSeguimiento;
    }

    @Override
    public ArrayList<CobMaeSeguimiento> fetchAll(CobMaeSeguimiento oCobMaeSeguimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CobMaeSeguimiento fetchingSingle(CobMaeSeguimiento oCobMaeSeguimiento) {
        System.out.println(" <i> mysql resuelto fechinfSingle CobMaeSeguimiento " + LocalDateTime.now());
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat ftB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat ftD = new SimpleDateFormat("yyyy/MM/dd");
        // cuota pago 
        CobMaeSeguimiento newCobMaeSeguimiento = new CobMaeSeguimiento();

        try {
            // name of procedure
            String sp = "{call PKG_COB_SEGUIMIENTO.SP_LISTAR_SEGUIMIENTO(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oCobMaeSeguimiento);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            
            System.out.println(" <i> conexxion PKG_COB_SEGUIMIENTO.SP_LISTAR_SEGUIMIENTO " + cn);

            CobSeguimiento newSeguimiento = null;
            ArrayList<CobSeguimiento> newSegList = new ArrayList<>();
            ArrayList<CobSeguimientoDet> newCobSegDet = new ArrayList<>();
            int segIdOld = 0;
            int segId = 0;
            String tipoDato;

            //System.out.println(" <f> fechinfSingle CobMaeSeguimiento 1 - " + LocalDateTime.now());

            List<String> listPho = new ArrayList<>();

            while (resultSet.next()) {
                
               
                // fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setMaeFondo(newFondo);
                // CobMaeSeguimiento
                newCobMaeSeguimiento.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                newCobMaeSeguimiento.setMaeInversion(newInversion);
                newCobMaeSeguimiento.setEestadoId(resultSet.getString("E_ESTADO_ID"));

                // CobSeguimiento
                segId = resultSet.getInt("C_COB_SEGUIMIENTO_ID");
                if (segId != segIdOld) {
                    newSeguimiento = new CobSeguimiento();
                    newSeguimiento.setCcobSeguimientoId(segId);
                    newSeguimiento.setFfecIni(resultSet.getDate("F_FEC_INI"));
                    newSeguimiento.setFfecFin(resultSet.getDate("F_FEC_FIN"));
                    newSeguimiento.setEestadoId(resultSet.getString("E_ESTADO_ID_CS"));
                    newSegList.add(newSeguimiento);
                    newCobSegDet = new ArrayList<>();
                    newSeguimiento.setCobSeguimientoDetList(newCobSegDet);
                    segIdOld = segId;
                }

                // CobSeguimientoDet
                tipoDato = resultSet.getString("C_TIPO_ID");

                //System.out.println(" <f> fechinfSingle CobMaeSeguimiento 2 Llamada - " + LocalDateTime.now());
                // llamada
                if (tipoDato.equals("0001")) {
                    CobLlamadas cl = new CobLlamadas();
                    CobTablas tf = new CobTablas();
                    CobTablas ts = new CobTablas();
                    cl.setCcodLlamadaId(resultSet.getInt("C_COB_SEG_DET_ID"));
                    cl.setCcodLlamada(resultSet.getString("C_COB_SEG_DET_OLD"));
                    cl.setCcodDisposicionId(resultSet.getString("C_DISPOSICION_ID"));
                    cl.setCtipoFamiliaId(resultSet.getString("C_TIPO_FAMILIA_ID"));
                    tf.setDdescripcion(resultSet.getString("TF_D_DESCRIPCION"));
                    cl.setTipoFamilia(tf);
                    cl.setDtipoFamilia(resultSet.getString("D_FAMILIA"));
                    cl.setCsituacionId(resultSet.getString("C_SITUACION_ID"));
                    ts.setDdescripcion(resultSet.getString("TS_D_DESCRIPCION"));
                    cl.setTipoAccion(ts);
                    cl.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                    cl.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                    cl.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));

                    //System.out.println(" <f> fechinfSingle CobMaeSeguimiento 2 Llamada - obtener telf  " + LocalDateTime.now());
                    // obtener las lista de telefonos 

                    /*if (listPho.isEmpty()) {
                        listPho = listPhone(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                    }
                     */
                    ArrayList<CobCdr> newCdrList = new ArrayList<>();
//                    for (String string : listPho) {
//                        System.out.println(" <f> fechinfSingle CobMaeSeguimiento 2 Llamada - Buscamos las llamadas " + LocalDateTime.now());
//                        //Buscamos las llamadas realizadas por numero y fecha
//                        CallableStatement cStmt = ConexionDaoMySql.getMySqlConnection().prepareCall("{CALL sp_list_call_record(?,?)}");
//                        cStmt.setString(1, string.trim());
//                        cStmt.setString(2, ft.format(cl.getfUsuarioAdd()));
//
//                        ResultSet rs1 = cStmt.executeQuery();
//
//                        System.out.println(" <f> fechinfSingle CobMaeSeguimiento 2 Llamada - recorremos los registros  " + LocalDateTime.now());
//                        // recorremos los registros de las llamadas 
//                        while (rs1.next()) {
//                            CobCdr newCdr = new CobCdr();
//                            newCdr.setCalldate(ftB.parse(rs1.getString("calldate")));
//                            newCdr.setClid(rs1.getString("clid"));
//                            newCdr.setSrc(rs1.getString("src"));
//                            newCdr.setDst(rs1.getString("dst"));
//                            newCdr.setDcontext(rs1.getString("dcontext"));
//                            newCdr.setChannel(rs1.getString("channel"));
//                            newCdr.setDstchannel(rs1.getString("dstchannel"));
//                            newCdr.setLastapp(rs1.getString("lastapp"));
//                            newCdr.setLastdata(rs1.getString("lastdata"));
//                            newCdr.setDuration(rs1.getInt("duration"));
//                            newCdr.setBillsec(rs1.getInt("billsec"));
//                            newCdr.setDisposition(rs1.getString("disposition"));
//                            newCdr.setAmaflags(rs1.getInt("amaflags"));
//                            newCdr.setAccountcode(rs1.getString("accountcode"));
//                            newCdr.setUniqueid(rs1.getString("uniqueid"));
//                            newCdr.setUserfield(rs1.getString("userfield"));
//
//                            if (newCdr.getDisposition().equals("ANSWERED")) {
//                                newCdr.setRecordingfile(rs1.getString("recordingfile").substring(28));
//                            } else {
//                                newCdr.setRecordingfile(ftD.format(newCdr.getCalldate()) + "/" + rs1.getString("recordingfile"));
//                            }
//                            newCdr.setCnum(rs1.getString("cnum"));
//                            newCdr.setCnam(rs1.getString("cnam"));
//                            newCdr.setOutbound_cnum(rs1.getString("outbound_cnum"));
//                            newCdr.setOutbound_cnam(rs1.getString("outbound_cnam"));
//                            newCdr.setDst_cnam(rs1.getString("dst_cnam"));
//                            newCdr.setDid(rs1.getString("did"));
//                            newCdrList.add(newCdr);
//                        }
//                        rs1.close();
//                    }

//                    sp = "{call PKG_COB_SEGUIMIENTO.SP_LISTAR_TEL_INV(?,?,?,?)}";
//                    OracleCallableStatement ocs = (OracleCallableStatement) this.getCn().prepareCall(sp);
//                    ocs.setInt(1, Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
//                    ocs.registerOutParameter(2, OracleTypes.CURSOR);
//                    ocs.registerOutParameter(3, OracleTypes.VARCHAR);
//                    ocs.registerOutParameter(4, OracleTypes.VARCHAR);
//                    ocs.execute();
//                    OracleResultSet rs = (OracleResultSet) ocs.getObject(2);
                    // recorrer telfonos
//                    while (rs.next()) {
//
//                    }
                    cl.setCobCdrLits(newCdrList);
                    newCobSegDet.add(cl);
                }

                //System.out.println(" <f> fechinfSingle CobMaeSeguimiento 3 CompromisoPago - " + LocalDateTime.now());
                // CompromisoPago
                if (tipoDato.equals("0002")) {
                    CobCompromiso cc = new CobCompromiso();
                    cc.setCcodCompromisoId(resultSet.getInt("C_COB_SEG_DET_ID"));
                    cc.setCcodCompromiso(resultSet.getString("C_COB_SEG_DET_OLD"));
                    cc.setFfecha(resultSet.getDate("F_FECHA"));
                    cc.setImonto(resultSet.getDouble("I_MONTO"));
                    cc.setDcuota(resultSet.getString("D_CUOTA"));
                    cc.setEestadoId(resultSet.getString("E_ESTADO_ID_CP"));
                    cc.setFfecObs(resultSet.getDate("F_FECHA_OBS"));
                    cc.setDobservacion(resultSet.getString("D_OBSERVACION"));
                    cc.setDrespuesta(resultSet.getString("D_RESPUESTA"));
                    cc.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                    cc.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                    cc.setCobSeguimiento(newSeguimiento);
                    newCobSegDet.add(cc);
                }
            }

            newCobMaeSeguimiento.setCobSeguimientoList(newSegList);

        } catch (Exception e) {
            System.out.println(e);
            //Logger.getLogger(CobMaeSeguimiento.class.getName()).log(Level.SEVERE, null, e);
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
        System.out.println(" <f> fechinfSingle CobMaeSeguimiento " + newCobMaeSeguimiento);
        
        return newCobMaeSeguimiento;
    }

    @Override
    public ArrayList<CobMaeSeguimiento> fetchCall(CobMaeSeguimiento oCobMaeSeguimiento) {
        //System.out.println(" <i> fetchCall CobMaeSeguimiento " + LocalDateTime.now());
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        ArrayList<CobMaeSeguimiento> newCobMaeSeguimientoList = new ArrayList<>();

        try {

            // name of procedure
            String sp = "{call PKG_COB_MAE_SEGUIMIENTO.SP_BUS_COB_MAE_SEG_LLAMADA(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersCall(oCobMaeSeguimiento);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            CobMaeSeguimiento maeSeguimiento = null;
            CobSeguimiento newSeguimiento = null;
            ArrayList<CobSeguimiento> newSegList = null;
            ArrayList<CobSeguimientoDet> newCobSegDet = null;
            int maeSegIdOld = 0;
            int maeSegId = 0;
            int segIdOld = 0;
            int segId = 0;
            String tipoDato;

            while (resultSet.next()) {

                // CobMaeSeguimiento
                maeSegId = resultSet.getInt("C_MAE_SEGUIMIENTO_ID");
                if (maeSegId != maeSegIdOld) {
                    // fondo
                    MaeFondo newFondo = new MaeFondo();
                    newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                    newFondo.setDFondo(resultSet.getString("D_FONDO"));
                    // inversion
                    MaeInversion newInversion = new MaeInversion();
                    newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                    newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                    newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                    newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                    newInversion.setMaeFondo(newFondo);
                    // CobMaeSeguimiento
                    maeSeguimiento = new CobMaeSeguimiento();
                    maeSeguimiento.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                    maeSeguimiento.setMaeInversion(newInversion);
                    maeSeguimiento.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                    
                    newSegList =  new ArrayList<>();
                    maeSeguimiento.setCobSeguimientoList(newSegList);
                    maeSegIdOld = maeSegId;
                    newCobMaeSeguimientoList.add(maeSeguimiento);
                }

                // CobSeguimiento
                segId = resultSet.getInt("C_COB_SEGUIMIENTO_ID");
                if (segId != segIdOld) {
                    newSeguimiento = new CobSeguimiento();
                    newSeguimiento.setCcobSeguimientoId(segId);
                    newSeguimiento.setFfecIni(resultSet.getDate("F_FEC_INI"));
                    newSeguimiento.setFfecFin(resultSet.getDate("F_FEC_FIN"));
                    newSeguimiento.setEestadoId(resultSet.getString("E_ESTADO_ID_CS"));
                    newSegList.add(newSeguimiento);
                    newCobSegDet = new ArrayList<>();
                    newSeguimiento.setCobSeguimientoDetList(newCobSegDet);
                    segIdOld = segId;
                }

                CobLlamadas cl = new CobLlamadas();
                CobTablas tf = new CobTablas();
                CobTablas ts = new CobTablas();
                cl.setCcodLlamadaId(resultSet.getInt("C_COB_SEG_DET_ID"));
                cl.setCcodLlamada(resultSet.getString("C_COB_SEG_DET_OLD"));
                cl.setCcodDisposicionId(resultSet.getString("C_DISPOSICION_ID"));
                cl.setCtipoFamiliaId(resultSet.getString("C_TIPO_FAMILIA_ID"));
                tf.setDdescripcion(resultSet.getString("TF_D_DESCRIPCION"));
                cl.setTipoFamilia(tf);
                cl.setDtipoFamilia(resultSet.getString("D_FAMILIA"));
                cl.setCsituacionId(resultSet.getString("C_SITUACION_ID"));
                ts.setDdescripcion(resultSet.getString("TS_D_DESCRIPCION"));
                cl.setTipoAccion(ts);
                cl.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                cl.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cl.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newCobSegDet.add(cl);
            }
            
            resultSet.close();
            if (cmd != null) {
                cmd.close();
            }

        } catch (Exception e) {
            System.out.println(e);
            //Logger.getLogger(CobMaeSeguimiento.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> fetchCall CobMaeSeguimiento " + LocalDateTime.now());
        return newCobMaeSeguimientoList;
    }

    private List<String> listPhone(int cMaeInversionId) throws SQLException {
        //System.out.println(" <i> listPhone - " + LocalDateTime.now());
        List<String> listP =null;
        OracleCallableStatement ocs=null;
        OracleResultSet rs = null;
        try
        {
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            
            listP = new ArrayList<>();
            String sp;
            sp = "{call PKG_COB_SEGUIMIENTO.SP_LISTAR_TEL_INV(?,?,?,?)}";
            ocs = (OracleCallableStatement) cn.prepareCall(sp);
            ocs.setInt(1, cMaeInversionId);
            ocs.registerOutParameter(2, OracleTypes.CURSOR);
            ocs.registerOutParameter(3, OracleTypes.VARCHAR);
            ocs.registerOutParameter(4, OracleTypes.VARCHAR);
            
            java.util.Date dini = new java.util.Date();
            System.out.println("PKG_COB_SEGUIMIENTO.SP_LISTAR_TEL_INV.ini");
            ocs.execute();
            java.util.Date dfin = new java.util.Date();
            long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
            System.out.println("PKG_COB_SEGUIMIENTO.SP_LISTAR_TEL_INV.fin:"+diferencia);
            
            rs = (OracleResultSet) ocs.getObject(2);

            while (rs.next()) {
                listP.add(rs.getString("A_NUMERO"));
            }
            rs.close();
        }
        catch(Exception ex)
        {
             System.out.println(ex);
            //Logger.getLogger(CobMaeSeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (rs != null) {
              try { rs.close(); } catch (SQLException e) { ; }
              rs = null;
            }
            if (ocs != null) {
              try { ocs.close(); } catch (SQLException e) { ; }
              ocs = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        }        
        //System.out.println(" <f> listPhone - " + LocalDateTime.now());
        return listP;
    }

    private List<ParameterOracle> deleteParameters(String cobSeguimientoId,String codLlamadaId,String codLlamada,String usuario) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_COB_SEGUIMIENTO_ID", cobSeguimientoId, OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_COB_LLAMADAS_ID", codLlamadaId, OracleTypes.VARCHAR, ParameterDirection.Input));
        if (codLlamada.trim().isEmpty())
        {
            oListParam.add(new ParameterOracle("PI_C_COB_LLAMADA", null,  OracleTypes.VARCHAR, ParameterDirection.Input));
        }
        else
        {
            oListParam.add(new ParameterOracle("PI_C_COB_LLAMADA", codLlamada, OracleTypes.VARCHAR, ParameterDirection.Input));
        }
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", usuario, OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }    
    
    private List<ParameterOracle> listParameters(CobMaeSeguimiento oCobMaeSeguimiento) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oCobMaeSeguimiento.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oCobMaeSeguimiento.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobMaeSeguimiento.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> insertParameters(CobMaeSeguimiento oCobMaeSeguimiento) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oCobMaeSeguimiento.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobMaeSeguimiento.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", oCobMaeSeguimiento.getMaeInversion().getcInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oCobMaeSeguimiento.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oCobMaeSeguimiento.getMaeInversion().getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oCobMaeSeguimiento.getEestadoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobMaeSeguimiento.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_C_MAE_SEGUIMIENTO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParametersFetch(CobMaeSeguimiento oCobMaeSeguimiento) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_SEGUIMIENTO_ID", oCobMaeSeguimiento.getCmaeSeguimientoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oCobMaeSeguimiento.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobMaeSeguimiento.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", oCobMaeSeguimiento.getMaeInversion().getCInmuebleId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oCobMaeSeguimiento.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oCobMaeSeguimiento.getMaeInversion().getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oCobMaeSeguimiento.getEestadoId(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParametersCall(CobMaeSeguimiento oCobMaeSeguimiento) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_INVERSION", oCobMaeSeguimiento.getMaeInversion().getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobMaeSeguimiento.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oCobMaeSeguimiento.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oCobMaeSeguimiento.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

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
