/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeUbigeo;
import pop.comun.dominio.TabUsuario;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaePersonaDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;

/**
 *
 * @author Jyoverar
 */
public class MaePersonaDao extends DBUtil implements IMaePersonaDao {

    private OracleConnection cn = null;

    public MaePersonaDao() {

    }

    public MaePersonaDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaePersona oMaePersona) {
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_PERSONA.SP_INS_MAEPERSONA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oMaePersona);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            
            newID = getOutputParameter("PO_C_PERSONA_ID").getParameterInt();

        } catch (Exception e) {
            Logger.getLogger(MaePersonaDao.class.getName()).log(Level.SEVERE, null, e);
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
    public boolean update(MaePersona oMaePersona) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean rpta = false;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_PERSONA.SP_UPD_MAEPERSONA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters(oMaePersona);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            rpta = true;
        } catch (Exception e) {
            rpta = false;
            Logger.getLogger(MaePersonaDao.class.getName()).log(Level.SEVERE, null, e);
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
    public void delete(MaePersona oMaePersona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaePersona> fetchAll(MaePersona oMaePersona) {
        ArrayList<MaePersona> lstPersona = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_PERSONA.SP_BUS_MAEPERSONA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersAll(oMaePersona);
            ///Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setCPersona(resultSet.getInt("C_PERSONA"));
                newPersona.setCTipoPersona(resultSet.getString("C_TIPO_PERSONA"));
                newPersona.setCTipoDocumento(resultSet.getString("C_TIPO_DOCUMENTO"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setCClasePersona(resultSet.getString("C_CLASE_PERSONA"));
                newPersona.setcSexoId(resultSet.getString("C_SEXO_ID"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                newPersona.setDRazonSocial(resultSet.getString("D_RAZON_SOCIAL"));
                newPersona.setDNombreComercial(resultSet.getString("D_NOMBRE_COMERCIAL"));
                newPersona.setCNacionNaci(resultSet.getString("C_NACION_NACI"));
                newPersona.setCNacionFructuacion(resultSet.getString("C_NACION_FRUCTUACION"));
                newPersona.setFNacimiento(resultSet.getDate("F_NACIMIENTO"));
                newPersona.setCEstadoCivil(resultSet.getString("C_ESTADO_CIVIL"));
                newPersona.setCGradoInstruccion(resultSet.getString("C_GRADO_INSTRUCCION"));
                newPersona.seteEstado(resultSet.getString("E_ESTADO"));
                newPersona.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newPersona.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newPersona.setcUsuarioMod(resultSet.getString("C_USUARIO_ADD"));
                newPersona.setfUsuarioMod(resultSet.getDate("F_USUARIO_ADD"));

                lstPersona.add(newPersona);
            }

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
        return lstPersona;
    }

    @Override
    public ArrayList<MaePersona> fetchDashboard(MaePersona oMaePersona) {
        ArrayList<MaePersona> lstPersona = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_PERSONA.SP_BUS_MAEPERSONA_DASHBOAR(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaePersona);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //direccion
                MaeDireccion newDireccion = new MaeDireccion();
                newDireccion.setDDir1(resultSet.getString("D_DIR1"));
                //ubigeo
                MaeUbigeo newUbigeo = new MaeUbigeo();
                newUbigeo.setDUbigeo(resultSet.getString("D_UBIGEO"));
                newDireccion.setMaeUbigeo(newUbigeo);
                //
                List<MaeDireccion> maeDireccionList = new ArrayList<>();
                maeDireccionList.add(newDireccion);
                newPersona.setMaeDireccionList(maeDireccionList);
                //deposito
                lstPersona.add(newPersona);
            }

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
        return lstPersona;
    }

    private List<ParameterOracle> listParameters(MaePersona oMaePersona) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParametersAll(MaePersona oMaePersona) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaePersona.getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", "", OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA", oMaePersona.getCPersona(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_PERSONA", oMaePersona.getCTipoPersona(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DOCUMENTO", oMaePersona.getCTipoDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaePersona.getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_CLASE_PERSONA", oMaePersona.getCClasePersona(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_SEXO_ID", oMaePersona.getcSexoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_APE_PAT", oMaePersona.getDApePat(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_APE_MAT", oMaePersona.getDApeMat(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_NOMBRES", oMaePersona.getDNombres(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_RAZON_SOCIAL", oMaePersona.getDRazonSocial(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_NOMBRE_COMERCIAL", oMaePersona.getDNombreComercial(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_NACION_NACI", oMaePersona.getCNacionNaci(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_NACION_FRUCTUACION", oMaePersona.getCNacionFructuacion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_NACIMIENTO", oMaePersona.getFNacimiento(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_ESTADO_CIVIL", oMaePersona.getCEstadoCivil(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_GRADO_INSTRUCCION", oMaePersona.getCGradoInstruccion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaePersona.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> insertParameters(MaePersona oMaePersona) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaePersona.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA", oMaePersona.getCPersona(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_PERSONA", oMaePersona.getCTipoPersona(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DOCUMENTO", oMaePersona.getCTipoDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaePersona.getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_CLASE_PERSONA", oMaePersona.getCClasePersona(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_SEXO_ID", oMaePersona.getcSexoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_APE_PAT", oMaePersona.getDApePat(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_APE_MAT", oMaePersona.getDApeMat(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_NOMBRES", oMaePersona.getDNombres(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_RAZON_SOCIAL", oMaePersona.getDRazonSocial(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_NOMBRE_COMERCIAL", oMaePersona.getDNombreComercial(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_NACION_NACI", oMaePersona.getCNacionNaci(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_NACION_FRUCTUACION", oMaePersona.getCNacionFructuacion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_NACIMIENTO", oMaePersona.getFNacimiento(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_ESTADO_CIVIL", oMaePersona.getCEstadoCivil(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_GRADO_INSTRUCCION", oMaePersona.getCGradoInstruccion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaePersona.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaePersona.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_C_PERSONA_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> updateParameters(MaePersona oMaePersona) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaePersona.getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaePersona.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA", oMaePersona.getCPersona(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_PERSONA", oMaePersona.getCTipoPersona(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DOCUMENTO", oMaePersona.getCTipoDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaePersona.getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_CLASE_PERSONA", oMaePersona.getCClasePersona(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_SEXO_ID", oMaePersona.getcSexoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_APE_PAT", oMaePersona.getDApePat(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_APE_MAT", oMaePersona.getDApeMat(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_NOMBRES", oMaePersona.getDNombres(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_RAZON_SOCIAL", oMaePersona.getDRazonSocial(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_NOMBRE_COMERCIAL", oMaePersona.getDNombreComercial(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_NACION_NACI", oMaePersona.getCNacionNaci(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_NACION_FRUCTUACION", oMaePersona.getCNacionFructuacion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_NACIMIENTO", oMaePersona.getFNacimiento(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_ESTADO_CIVIL", oMaePersona.getCEstadoCivil(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_GRADO_INSTRUCCION", oMaePersona.getCGradoInstruccion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaePersona.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oMaePersona.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

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
    public MaePersona findAssignedPerson(int dia, String fondo, Number InversionID) {
        MaePersona newPersona = new MaePersona();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_USUARIO.SP_BUS_USUARIO_ASIGNADO(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersfindAssignedPerson(dia, fondo, InversionID);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));                
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                TabUsuario tabUsuario = new TabUsuario();
                tabUsuario.setCusuarioId(resultSet.getString("C_USUARIO_ID"));
                newPersona.setTabUsuario(tabUsuario);
            }

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
        return newPersona;
    }

    private List<ParameterOracle> listParametersfindAssignedPerson(int dia, String fondo, Number InversionID) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_DIA", dia, OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", fondo, OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", InversionID, OracleTypes.NUMBER, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

}
