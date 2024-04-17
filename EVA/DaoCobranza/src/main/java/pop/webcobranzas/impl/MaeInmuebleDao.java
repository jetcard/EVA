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
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaePersonaInmueble;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeInmuebleDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeInmuebleDao extends DBUtil implements IMaeInmuebleDao {

    private OracleConnection cn = null;

    public MaeInmuebleDao() {
    }

    public MaeInmuebleDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeInmueble oMaeInmueble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(MaeInmueble oMaeInmueble) {
         //Integer newID = 0;
        boolean rpta = false;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_INMUEBLE.SP_UPD_INMUEBLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters(oMaeInmueble);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            rpta = true;
        } catch (Exception e) {
            rpta = false;
            Logger.getLogger(MaeInmuebleDao.class.getName()).log(Level.SEVERE, null, e);
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
    public void delete(MaeInmueble oMaeInmueble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeInmueble> fetchAll(MaeInmueble oMaeInmueble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaePersonaInmueble> fetchPerson(MaeInmueble oMaeInmueble) {
        ArrayList<MaePersonaInmueble> lstPersonasInm = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_INMUEBLE.SP_BUS_PER_INMUEBLE(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaeInmueble);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // persona
                MaePersona mp = new MaePersona();
                mp.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                mp.setDApePat(resultSet.getString("D_APE_PAT"));
                mp.setCTipoPersona(resultSet.getString("C_TIPO_PERSONA"));
                mp.setCTipoDocumento(resultSet.getString("C_TIPO_DOCUMENTO"));
                mp.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                mp.setDApePat(resultSet.getString("D_APE_PAT"));
                mp.setDApeMat(resultSet.getString("D_APE_MAT"));
                mp.setDNombres(resultSet.getString("D_NOMBRES"));

                MaePersonaInmueble mpi = new MaePersonaInmueble();
                mpi.setMaePersona(mp);
                lstPersonasInm.add(mpi);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeFondoDao.class.getName()).log(Level.SEVERE, null, e);
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


        return lstPersonasInm;
    }

    private List<ParameterOracle> listParameters(MaeInmueble oMaeInmueble) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeInmueble.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeInmueble.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INMUEBLE_ID", oMaeInmueble.getCInmuebleId(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> updateParameters(MaeInmueble oMaeInmueble) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_INMUEBLE_ID", oMaeInmueble.getCInmuebleId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID", oMaeInmueble.getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR1", oMaeInmueble.getCTipoDir1(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DIR1", oMaeInmueble.getDDir1(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_DIR2",oMaeInmueble.getCTipoDir2() , OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DIR2", oMaeInmueble.getDDir2(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_ASIGNACION", oMaeInmueble.getBAsignacion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_ANALISIS", oMaeInmueble.getDAnalisis(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_OPINION", oMaeInmueble.getDOpinion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_CONSERV_ID", oMaeInmueble.getCConservId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USO_ID", oMaeInmueble.getCUsoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_P_ASIG_MAX", oMaeInmueble.getPAsigMax(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_ANTIGUEDAD", oMaeInmueble.getFAntiguedad(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DIR3", oMaeInmueble.getDdir3(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeInmueble.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_MOD", oMaeInmueble.getcUsuarioMod(), OracleTypes.CHAR, ParameterDirection.Input));

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
