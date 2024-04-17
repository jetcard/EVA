/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeTelefonoDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.util.Connect;
import pop.webcobranzas.util.ObjConnection;

/**
 *
 * @author Jyoverar
 */
public class MaeTelefonoDao extends DBUtil implements IMaeTelefonoDao {
    private OracleConnection cn = null;

    public MaeTelefonoDao() {

    }

    public MaeTelefonoDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeTelefono oMaeTelefono) {
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_TELEFONO.SP_INS_MAE_TELEFONO(?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oMaeTelefono);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_C_TELEFONO_ID").getParameterInt();

        } catch (Exception e) {
            System.out.println(e);
            //Logger.getLogger(MaeTelefonoDao.class.getName()).log(Level.SEVERE, null, e);
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

        return newID;
    }

    
    @Override
    public Integer insertPresta(MaeTelefono oMaeTelefono) {
        System.out.println("insertar telefono presta3333");
        Integer newID = 0;
        Connection cn = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        try {
            ObjConnection ObjConnection = new ObjConnection();
            ObjConnection.setCn(cn);
           System.out.println("insertar telefono presta1");
            //cn = Connect.getDbCon(ObjConnection);
            cn = Connect.getDbConInitial();
            String consulta = "{call SincronizaTelefonoSacifaPresta(?,?,?,?,?)}";
            System.out.println("insertar telefono presta1"+consulta);
            cstm = cn.prepareCall(consulta);
            
            cstm.setString(1, oMaeTelefono.getMaePersona().getANroDocumento());
            
            cstm.setString(2, oMaeTelefono.getANumero());
            cstm.setString(3, oMaeTelefono.getSEstado());
            cstm.setInt(4, oMaeTelefono.getNPrede());
            cstm.setString(5, oMaeTelefono.getCTipoTel());
            System.out.println("insertar telefono presta1"+cstm.executeQuery());
            
            
            rs = cstm.executeQuery();
            System.out.println("finalilo presta1");
            return 1;
        } catch (Exception ex) {

            return 0;
        } finally {
            if (cstm != null) {
              try { cstm.close(); } catch (Exception e) { ; }
              cstm = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
    
    }

    
    @Override
    public boolean update(MaeTelefono oMaeTelefono) {
        //Integer newID = 0;
        boolean rpta = false;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
                String sp = "{call PKG_MAE_TELEFONO.SP_UPD_MAE_TELEFONO(?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters(oMaeTelefono);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            rpta = true;
        } catch (Exception e) {
            rpta = false;
            System.out.println(e);
            //Logger.getLogger(MaeTelefonoDao.class.getName()).log(Level.SEVERE, null, e);
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
        return rpta;
    }

    
    @Override
    public boolean modificar(MaeTelefono oMaeTelefono) {
        //Integer newID = 0;
        boolean rpta = false;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_TELEFONO.SP_MODI_MAE_TELEFONO(?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            System.out.println("telefono"+oMaeTelefono.getANumero());
            oLis = modifiParameters(oMaeTelefono);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
    
            rpta = true;
        } catch (Exception e) {
            rpta = false;
            System.out.println(e.getMessage());
            //Logger.getLogger(MaeTelefonoDao.class.getName()).log(Level.SEVERE, null, e);
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
        return rpta;
    }

    
    @Override
    public void delete(MaeTelefono oMaeTelefono) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeTelefono> fetchAll(MaeTelefono oMaeTelefono) {
        //System.out.println(" <i> MaeTelefonoDao fetchAll " + LocalDateTime.now());
        ArrayList<MaeTelefono> lstTelefonos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_TELEFONO.SP_BUS_MAE_TELEFONO(?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersAll(oMaeTelefono);
            // execute procedure
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // telefono
                MaeTelefono newTelefono = new MaeTelefono();
                newTelefono.setCTelefonoId(resultSet.getInt("C_TELEFONO_ID"));
                newTelefono.setANumero(resultSet.getString("A_NUMERO"));
                newTelefono.setBDefault(resultSet.getString("B_DEFAULT"));
                newTelefono.setCTipoTel(resultSet.getString("C_TIPO_TEL"));
                newTelefono.setNAnexo(resultSet.getInt("N_ANEXO"));

                newTelefono.seteEstado(resultSet.getString("E_ESTADO"));
                newTelefono.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newTelefono.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newTelefono.setcUsuarioMod(resultSet.getString("C_USUARIO_ADD"));
                newTelefono.setfUsuarioMod(resultSet.getDate("F_USUARIO_ADD"));
                lstTelefonos.add(newTelefono);
            }
            ////System.out.println(LocalDateTime.now());
        } catch (Exception e) {
            System.out.println(e);
            //Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
        }finally 
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
        //System.out.println(" <f> MaeTelefonoDao fetchAll " + LocalDateTime.now());
        return lstTelefonos;
    }

    @Override
    public ArrayList<MaeTelefono> fetchPhoneInver(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeTelefonoDao fetchInvTele " + LocalDateTime.now());
        ArrayList<MaeTelefono> lstTelefonos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_TELEFONO.SP_BUS_MAE_TELEF_INVER(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersInvTele(oMaeInversion);
           //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            ////System.out.println(LocalDateTime.now());
            MaeInversion newInversion;
            MaeFondo newFondo;
            MaePersona newPersona;

            while (resultSet.next()) {

                // nuevo fondo
                newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                // nueva inversion
                newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setMaeFondo(newFondo);

                //ubigeo
                MaeUbigeo newUbigeo = new MaeUbigeo();
                newUbigeo.setDDUbigeoDist(resultSet.getString("D_UBIGEO_D"));
                newUbigeo.setDDUbigeoProv(resultSet.getString("D_UBIGEO_P"));

                // inmueble
                MaeInmueble newInmueble = new MaeInmueble();
                newInmueble.setDDir1(resultSet.getString("D_DIR1"));
                newInmueble.setMaeUbigeo(newUbigeo);

                newInversion.setMaeInmueble(newInmueble);

                // persona
                newPersona = new MaePersona();
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setCTipoDocumento(resultSet.getString("C_TIPO_DOCUMENTO"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                newPersona.setMaeInversion(newInversion);

                // telefono
                MaeTelefono newTelefono = new MaeTelefono();
                newTelefono.setMaePersona(newPersona);
                newTelefono.getMaePersona().setMaeFondo(newFondo);
                newTelefono.setCTelefonoId(resultSet.getInt("C_TELEFONO_ID"));
                newTelefono.setANumero(resultSet.getString("A_NUMERO"));
                newTelefono.setCTipoTel(resultSet.getString("C_TIPO_TEL"));
                newTelefono.setBDefault(resultSet.getString("B_DEFAULT"));
                if (newTelefono.getCTipoTel().equals("0001")){
                    newTelefono.setDEScTipoTel("Telefono Fijo");
                }
                if (newTelefono.getCTipoTel().equals("0002")){
                    newTelefono.setDEScTipoTel("Movil");
                }
                if (newTelefono.getCTipoTel().equals("0003")){
                    newTelefono.setDEScTipoTel("Whatssap");
                }
                newTelefono.setNPrede(resultSet.getInt("NPRIORIDAD"));
                if (resultSet.getInt("NPRIORIDAD")==1 ) {
                    newTelefono.setBPrede(true);
                }else{
                   newTelefono.setBPrede(false); 
                }
                newTelefono.setCUsoTel(resultSet.getString("C_USO_TEL"));
                newTelefono.setSEstado(resultSet.getString("E_ESTADO"));
                newTelefono.setMaePersona(newPersona);

                lstTelefonos.add(newTelefono);

            }
            ////System.out.println(LocalDateTime.now());
        } catch (Exception e) {
            System.out.println(e);
            //Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeTelefonoDao fetchInvTele " + LocalDateTime.now());
        return lstTelefonos;
    }

    @Override
    public ArrayList<String> listPhone(int cMaeInversionId) {
        ArrayList<String> listP = new ArrayList<>();
        OracleCallableStatement ocs = null;
        OracleResultSet rs = null;
        try {
            //System.out.println(" <i> listPhone - " + LocalDateTime.now());

            String sp;
            sp = "{call PKG_COB_SEGUIMIENTO.SP_LISTAR_TEL_INV(?,?,?,?)}";
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
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
            //System.out.println(" <f> listPhone - " + LocalDateTime.now());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        finally 
        {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (rs != null) {
                try { rs.close(); } catch (Exception e) { ; }
                rs = null;
              }
            if (ocs != null) {
              try { ocs.close(); } catch (Exception e) { ; }
              ocs = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        return listP;
    }

    @Override
    public ArrayList<MaeTelefono> listPhoneSendMsj(MaeInversion oMaeInversion) {
       //System.out.println(" <i> MaeTelefonoDao fetchInvTele " + LocalDateTime.now());
        ArrayList<MaeTelefono> lstTelefonos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_TELEFONO.SP_BUS_MAE_TELEF_ENVIO_MSJ(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersSendMsj(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            ////System.out.println("----");
            ////System.out.println(LocalDateTime.now());
            MaeInversion newInversion;
            MaeFondo newFondo;
            MaePersona newPersona;

            while (resultSet.next()) {

                // nuevo fondo
                newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                // nueva inversion
                newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setMaeFondo(newFondo);

                //ubigeo
                MaeUbigeo newUbigeo = new MaeUbigeo();
                newUbigeo.setDDUbigeoDist(resultSet.getString("D_UBIGEO_D"));
                newUbigeo.setDDUbigeoProv(resultSet.getString("D_UBIGEO_P"));

                // inmueble
                MaeInmueble newInmueble = new MaeInmueble();
                newInmueble.setDDir1(resultSet.getString("D_DIR1"));
                newInmueble.setMaeUbigeo(newUbigeo);

                newInversion.setMaeInmueble(newInmueble);

                // persona
                newPersona = new MaePersona();
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setCTipoDocumento(resultSet.getString("C_TIPO_DOCUMENTO"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                newPersona.setMaeInversion(newInversion);

                // telefono
                MaeTelefono newTelefono = new MaeTelefono();
                newTelefono.setCTelefonoId(resultSet.getInt("C_TELEFONO_ID"));
                newTelefono.setANumero(resultSet.getString("A_NUMERO"));
                newTelefono.setCTipoTel(resultSet.getString("C_TIPO_TEL"));
                newTelefono.setBDefault(resultSet.getString("B_DEFAULT"));
                newTelefono.setMaePersona(newPersona);

                lstTelefonos.add(newTelefono);

            }
            ////System.out.println(LocalDateTime.now());
        } catch (Exception e) {
            System.out.println(e);
            //Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeTelefonoDao fetchInvTele " + LocalDateTime.now());
        return lstTelefonos;
    }
    
    private List<ParameterOracle> listParametersInvTele(MaeInversion oMaeInversion) {
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

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParametersAll(MaeTelefono oMaeTelefono) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_TELEFONO_ID", oMaeTelefono.getCTelefonoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeTelefono.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", "", OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_TEL", oMaeTelefono.getCTipoTel(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NUMERO", oMaeTelefono.getANumero(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_ANEXO", oMaeTelefono.getNAnexo(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_DEFAULT", oMaeTelefono.getBDefault(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeTelefono.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> insertParameters(MaeTelefono oMaeTelefono) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeTelefono.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeTelefono.getMaePersona().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_TEL", oMaeTelefono.getCTipoTel(), OracleTypes.CHAR, ParameterDirection.Input));
         oListParam.add(new ParameterOracle("PI_C_USO_TEL", oMaeTelefono.getCUsoTel(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NUMERO", oMaeTelefono.getANumero(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_ANEXO", oMaeTelefono.getNAnexo(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_DEFAULT", oMaeTelefono.getBDefault(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaeTelefono.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_C_TELEFONO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> updateParameters(MaeTelefono oMaeTelefono) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_TELEFONO_ID", oMaeTelefono.getCTelefonoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeTelefono.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeTelefono.getMaePersona().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_TEL", oMaeTelefono.getCTipoTel(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NUMERO", oMaeTelefono.getANumero(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_ANEXO", oMaeTelefono.getNAnexo(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_DEFAULT", oMaeTelefono.getBDefault(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeTelefono.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oMaeTelefono.getcUsuarioMod(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> modifiParameters(MaeTelefono oMaeTelefono) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_TELEFONO_ID", oMaeTelefono.getCTelefonoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeTelefono.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeTelefono.getMaePersona().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NUMERO", oMaeTelefono.getANumero(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_PRIORIDAD", oMaeTelefono.getNPrede(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeTelefono.getSEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oMaeTelefono.getcUsuarioMod(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    
    private List<ParameterOracle> listParametersSendMsj(MaeInversion oMaeInversion) {
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
