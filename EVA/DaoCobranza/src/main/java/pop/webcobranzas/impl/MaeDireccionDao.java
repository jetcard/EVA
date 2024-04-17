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
import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeDireccionDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeDireccionDao extends DBUtil implements IMaeDireccionDao {

    private OracleConnection cn = null;

    public MaeDireccionDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    public MaeDireccionDao() {
    }

    @Override
    public Integer insert(MaeDireccion oMaeDireccion) {
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_DIRECCION.SP_INS_MAE_DIRECCION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oMaeDireccion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_C_DIRECCION_ID").getParameterInt();

        } catch (Exception e) {
            Logger.getLogger(MaeDireccionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
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
    public boolean update(MaeDireccion oMaeDireccion) {
        //Integer newID = 0;
        boolean rpta = false;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_DIRECCION.SP_UPD_MAE_DIRECCION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters(oMaeDireccion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            rpta = true;

        } catch (Exception e) {
            rpta = false;
            Logger.getLogger(MaeDireccionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
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
    public void delete(MaeDireccion oMaeDireccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeDireccion> fetchAll(MaeDireccion oMaeDireccion) {
        //System.out.println(" <i> MaeDireccionDao fetchAll " + LocalDateTime.now());
        ArrayList<MaeDireccion> lstDireccion = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_DIRECCION.SP_BUS_MAE_DIRECCION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersAll(oMaeDireccion);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // telefono
                MaeDireccion newDireccion = new MaeDireccion();
                newDireccion.setCDireccionId(resultSet.getInt("C_DIRECCION_ID"));
                //newDireccion.setCDireccionId(resultSet.getInt("C_PERSONA_ID"));
                //newDireccion.setCDireccionId(resultSet.getInt("C_FONDO_ID"));
                MaeUbigeo maeUbigeo = new MaeUbigeo();
                maeUbigeo.setCUbigeoId(resultSet.getString("C_UBIGEO_ID"));
                newDireccion.setMaeUbigeo(maeUbigeo);

                newDireccion.setCtipoDirId(resultSet.getString("C_TIPO_DIR_ID"));
                newDireccion.setCTipoDir1(resultSet.getString("C_TIPO_DIR1"));
                newDireccion.setDDir1(resultSet.getString("D_DIR1"));
                newDireccion.setNDir1(resultSet.getInt("N_DIR1"));
                newDireccion.setADir1(resultSet.getString("A_DIR1"));
                newDireccion.setCTipoDir2(resultSet.getString("C_TIPO_DIR2"));
                newDireccion.setDDir2(resultSet.getString("D_DIR2"));
                newDireccion.setNDir2(resultSet.getInt("N_DIR2"));
                newDireccion.setADir2(resultSet.getString("A_DIR2"));
                newDireccion.setBDefault(resultSet.getString("B_DEFAULT"));

                newDireccion.seteEstado(resultSet.getString("E_ESTADO"));
                newDireccion.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newDireccion.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newDireccion.setcUsuarioMod(resultSet.getString("C_USUARIO_ADD"));
                newDireccion.setfUsuarioMod(resultSet.getDate("F_USUARIO_ADD"));
                lstDireccion.add(newDireccion);
            }
            ////System.out.println(LocalDateTime.now());
        } catch (Exception e) {
            Logger.getLogger(MaeDireccionDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeDireccionDao fetchAll " + LocalDateTime.now());
        return lstDireccion;
    }

    private List<ParameterOracle> listParametersAll(MaeDireccion oMaeDireccion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_DIRECCION_ID", oMaeDireccion.getCDireccionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeDireccion.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", "", OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID", oMaeDireccion.getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR_ID", oMaeDireccion.getCtipoDirId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR1", oMaeDireccion.getCTipoDir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DIR1", oMaeDireccion.getDDir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_DIR1", oMaeDireccion.getNDir1(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_DIR1", oMaeDireccion.getADir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR2", oMaeDireccion.getCTipoDir2(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DIR2", oMaeDireccion.getDDir2(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_DIR2", oMaeDireccion.getNDir2(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_DIR2", oMaeDireccion.getADir2(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_DEFAULT", oMaeDireccion.getBDefault(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeDireccion.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> insertParameters(MaeDireccion oMaeDireccion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeDireccion.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeDireccion.getMaePersona().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID", oMaeDireccion.getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR_ID", oMaeDireccion.getCtipoDirId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR1", oMaeDireccion.getCTipoDir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DIR1", oMaeDireccion.getDDir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_DIR1", oMaeDireccion.getNDir1(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_DIR1", oMaeDireccion.getADir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR2", oMaeDireccion.getCTipoDir2(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DIR2", oMaeDireccion.getDDir2(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_DIR2", oMaeDireccion.getNDir2(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_DIR2", oMaeDireccion.getADir2(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_DEFAULT", oMaeDireccion.getBDefault(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeDireccion.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaeDireccion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_C_DIRECCION_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> updateParameters(MaeDireccion oMaeDireccion) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_DIRECCION_ID", oMaeDireccion.getCDireccionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PERSONA_ID", oMaeDireccion.getMaePersona().getCPersonaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeDireccion.getMaePersona().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID", oMaeDireccion.getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR_ID", oMaeDireccion.getCtipoDirId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR1", oMaeDireccion.getCTipoDir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DIR1", oMaeDireccion.getDDir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_DIR1", oMaeDireccion.getNDir1(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_DIR1", oMaeDireccion.getADir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR2", oMaeDireccion.getCTipoDir2(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DIR2", oMaeDireccion.getDDir2(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_DIR2", oMaeDireccion.getNDir2(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_DIR2", oMaeDireccion.getADir2(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_DEFAULT", oMaeDireccion.getBDefault(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeDireccion.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oMaeDireccion.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));

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
