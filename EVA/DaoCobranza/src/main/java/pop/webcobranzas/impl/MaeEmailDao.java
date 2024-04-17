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
import oracle.jdbc.OracleTypes;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeEmailDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeEmailDao extends DBUtil implements IMaeEmailDao {

    private OracleConnection cn = null;

    public MaeEmailDao() {

    }

    public MaeEmailDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeEmail oMaeEmail) {
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_EMAIL.SP_INS_MAE_EMAIL(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oMaeEmail);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_C_EMAIL_ID").getParameterInt();

        } catch (Exception e) {
            Logger.getLogger(MaeEmailDao.class.getName()).log(Level.SEVERE, null, e);
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
    public boolean update(MaeEmail oMaeEmail) {
        //Integer newID = 0;
        boolean rpta= false;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_EMAIL.SP_UPD_MAE_EMAIL(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters(oMaeEmail);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            rpta= true;

        } catch (Exception e) {
            rpta= false;
            Logger.getLogger(MaeEmailDao.class.getName()).log(Level.SEVERE, null, e);
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
    public void delete(MaeEmail oMaeEmail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     @Override
    public ArrayList<MaeEmail> fetchAll2(MaeEmail oMaeEmail,String idFondo) {
        System.out.println(" <i> MaeInversionDao fetchInvTele " + LocalDateTime.now());
        ArrayList<MaeEmail> lstEmails = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_EMAIL.SP_BUS_MAE_EMAIL_FAC(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listEMailAll(oMaeEmail,idFondo);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            //System.out.println("----");
            //System.out.println(LocalDateTime.now());
          

            while (resultSet.next()) {

                // telefono
                MaeEmail newEmail = new MaeEmail();
                newEmail.setCemailId(resultSet.getInt("Nemail"));
                MaePersona newMaePersona = new MaePersona();
                newMaePersona.setCPersona(resultSet.getInt("Cpersona"));
                System.out.println("sssss"+resultSet.getInt("Cpersona"));
                newEmail.setMaePersona(newMaePersona);
                newEmail.setCfondoid(idFondo);
                newEmail.setDemail(resultSet.getString("Temail"));
                lstEmails.add(newEmail);

            }
            //System.out.println(LocalDateTime.now());
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
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
        System.out.println(" <f> MaeInversionDao fetchInvTele " + LocalDateTime.now());
        return lstEmails;
    }
    
    
    @Override
    public ArrayList<MaeEmail> fetchAll(MaeEmail oMaeEmail) {
        System.out.println(" <i> MaeInversionDao fetchInvTele " + LocalDateTime.now());
        ArrayList<MaeEmail> lstEmails = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_EMAIL.SP_BUS_MAE_EMAIL(?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersAll(oMaeEmail);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            //System.out.println("----");
            //System.out.println(LocalDateTime.now());
            MaeInversion newInversion;
            MaeFondo newFondo;
            MaePersona newPersona;

            while (resultSet.next()) {

                // telefono
                MaeEmail newEmail = new MaeEmail();
                newEmail.setCemailId(resultSet.getInt("C_EMAIL_ID"));
                newEmail.setCtipoMailId(resultSet.getString("C_TIPO_EMAIL_ID"));
                newEmail.setDemail(resultSet.getString("D_EMAIL"));
                newEmail.setDusuario(resultSet.getString("D_USUARIO"));
                newEmail.setDcontrasenia(resultSet.getString("D_CONSTRASENA"));
                newEmail.setNpuerto(resultSet.getInt("N_PUERTO"));
                newEmail.setbDefault(resultSet.getString("B_DEFAULT"));
                newEmail.seteEstado(resultSet.getString("E_ESTADO"));
                newEmail.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newEmail.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newEmail.setcUsuarioMod(resultSet.getString("C_USUARIO_ADD"));
                newEmail.setfUsuarioMod(resultSet.getDate("F_USUARIO_ADD"));
                lstEmails.add(newEmail);

            }
            //System.out.println(LocalDateTime.now());
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
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
        System.out.println(" <f> MaeInversionDao fetchInvTele " + LocalDateTime.now());
        return lstEmails;
    }

    
      @Override
    public Integer update2(MaeEmail oMaeEmail) {
        //Integer newID = 0;
        int rpta= 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_EMAIL.SP_UPD_MAE_EMAIL_FAC(?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters2(oMaeEmail);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            rpta= 1;

        } catch (Exception e) {
            rpta= 0;
            Logger.getLogger(MaeEmailDao.class.getName()).log(Level.SEVERE, null, e);
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
    public ArrayList<MaeEmail> fetchEmailInver(MaeInversion oMaeInversion) {
        System.out.println(" <i> MaeInversionDao fetchInvTele " + LocalDateTime.now());
        ArrayList<MaeEmail> lstEmails = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_EMAIL.SP_BUS_MAE_EMAIL_INVER(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersInvEmail(oMaeInversion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            //System.out.println("----");
            //System.out.println(LocalDateTime.now());
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
                MaeEmail newEmail = new MaeEmail();
                newEmail.setCemailId(resultSet.getInt("C_EMAIL_ID"));
                newEmail.setDemail(resultSet.getString("D_EMAIL"));
                newEmail.setDusuario(resultSet.getString("D_USUARIO"));
                newEmail.setNpuerto(resultSet.getInt("N_PUERTO"));
                newEmail.setMaePersona(newPersona);

                lstEmails.add(newEmail);

            }
            //System.out.println(LocalDateTime.now());
        } catch (Exception e) {
            Logger.getLogger(MaePersona.class.getName()).log(Level.SEVERE, null, e);
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
        System.out.println(" <f> MaeInversionDao fetchInvTele " + LocalDateTime.now());
        return lstEmails;
    }

    private List<ParameterOracle> listParametersInvEmail(MaeInversion oMaeInversion) {
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

    private List<ParameterOracle> listParametersAll(MaeEmail oMaeEmail) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_EMAIL_ID", oMaeEmail.getCemailId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeEmail.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_EMAIL", oMaeEmail.getDemail(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_USUARIO", oMaeEmail.getDusuario(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_CONSTRASENA", oMaeEmail.getDcontrasenia(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DOMINIO", oMaeEmail.getDdominio(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_PUERTO", oMaeEmail.getNpuerto(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_DEFAULT", oMaeEmail.getbDefault(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeEmail.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> insertParameters(MaeEmail oMaeEmail) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeEmail.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_EMAIL_ID", oMaeEmail.getCtipoMailId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_EMAIL", oMaeEmail.getDemail(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_USUARIO", oMaeEmail.getDusuario(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_CONSTRASENA", oMaeEmail.getDcontrasenia(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DOMINIO", oMaeEmail.getDdominio(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_PUERTO", oMaeEmail.getNpuerto(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_DEFAULT", oMaeEmail.getbDefault(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeEmail.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaeEmail.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_C_EMAIL_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> updateParameters(MaeEmail oMaeEmail) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_EMAIL_ID", oMaeEmail.getCemailId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeEmail.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_EMAIL_ID", oMaeEmail.getCtipoMailId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_EMAIL", oMaeEmail.getDemail(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_USUARIO", oMaeEmail.getDusuario(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_CONSTRASENA", oMaeEmail.getDcontrasenia(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DOMINIO", oMaeEmail.getDdominio(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_PUERTO", oMaeEmail.getNpuerto(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_DEFAULT", oMaeEmail.getbDefault(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeEmail.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oMaeEmail.getcUsuarioMod(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    
     private List<ParameterOracle> listEMailAll(MaeEmail oMaeEmail,String IdFondo) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", IdFondo, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_EMAIL_ID", oMaeEmail.getCemailId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeEmail.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_EMAIL", oMaeEmail.getDemail(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> updateParameters2(MaeEmail oMaeEmail) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("Pi_C_Fondo_Id", oMaeEmail.getCfondoid(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_EMAIL_ID", oMaeEmail.getCemailId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeEmail.getMaePersona().getCPersona(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_EMAIL", oMaeEmail.getDemail(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_USUARIO", oMaeEmail.getDusuario(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_CONSTRASENA", oMaeEmail.getDcontrasenia(), OracleTypes.CHAR, ParameterDirection.Input));
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
