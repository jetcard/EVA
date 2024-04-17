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
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeUbigeoDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;

/**
 *
 * @author Jhon Yovera
 */
public class MaeUbigeoDao extends DBUtil implements IMaeUbigeoDao {

    private OracleConnection cn = null;

    public MaeUbigeoDao() {

    }

    public MaeUbigeoDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public ArrayList<MaeUbigeo> fetchProvince(MaeUbigeo oMaeUbigeo) {
        ArrayList<MaeUbigeo> lstProvince = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_UBIGEO.SP_LISTAR_PROVINCIA(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaeUbigeo);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //ubigeo
                MaeUbigeo newMaeUbigeo = new MaeUbigeo();
                newMaeUbigeo.setCUbigeoId(resultSet.getString("C_UBIGEO_ID"));
                newMaeUbigeo.setCUbigeoPad(resultSet.getString("C_UBIGEO_PAD"));
                newMaeUbigeo.setDDUbigeoProv(resultSet.getString("D_UBIGEO"));
                newMaeUbigeo.seteEstado(resultSet.getString("E_ESTADO"));

                //
                lstProvince.add(newMaeUbigeo);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeUbigeo.class.getName()).log(Level.SEVERE, null, e);
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
        return lstProvince;
    }

    @Override
    public ArrayList<MaeUbigeo> fetchDistrict(MaeUbigeo oMaeUbigeo) {
        ArrayList<MaeUbigeo> lstDistrict = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_UBIGEO.SP_LISTAR_DISTRITO(?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersDistrict(oMaeUbigeo);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //ubigeo
                MaeUbigeo newMaeUbigeo = new MaeUbigeo();
                newMaeUbigeo.setCUbigeoId(resultSet.getString("C_UBIGEO_ID"));
                newMaeUbigeo.setCUbigeoPad(resultSet.getString("C_UBIGEO_PAD"));
                newMaeUbigeo.setDDUbigeoDist(resultSet.getString("D_UBIGEO"));
                newMaeUbigeo.seteEstado(resultSet.getString("E_ESTADO"));

                //
                lstDistrict.add(newMaeUbigeo);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeUbigeo.class.getName()).log(Level.SEVERE, null, e);
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

        return lstDistrict;
    }

    private List<ParameterOracle> listParameters(MaeUbigeo oMaeUbigeo) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParametersDistrict(MaeUbigeo oMaeUbigeo) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_PAD", oMaeUbigeo.getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));

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

    @Override
    public ArrayList<MaeUbigeo> fetchAll(MaeUbigeo oMaeUbigeo) {
        ArrayList<MaeUbigeo> lstDistrict = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_UBIGEO.SP_BUS_UBIGEO(?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersBus(oMaeUbigeo);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //ubigeo
                MaeUbigeo newMaeUbigeo = new MaeUbigeo();
                newMaeUbigeo.setCUbigeoId(resultSet.getString("C_UBIGEO_ID"));
                newMaeUbigeo.setCUbigeoPad(resultSet.getString("C_UBIGEO_PAD"));
                newMaeUbigeo.setDUbigeo(resultSet.getString("D_UBIGEO"));
                newMaeUbigeo.seteEstado(resultSet.getString("E_ESTADO"));

                //
                lstDistrict.add(newMaeUbigeo);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeUbigeo.class.getName()).log(Level.SEVERE, null, e);
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

        return lstDistrict;
    }

    private List<ParameterOracle> listParametersBus(MaeUbigeo oMaeUbigeo) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID", oMaeUbigeo.getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
}
