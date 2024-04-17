/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeDashboardDao;
import pop.comun.dominio.CobCronogramaMetaAvance;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jhon Yovera
 */
public class MaeDashboardDao extends DBUtil implements IMaeDashboardDao {

    private OracleConnection cn = null;

    public MaeDashboardDao() {

    }

    public MaeDashboardDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public ArrayList<List<Object>> fetchAll() {
        ArrayList<List<Object>> lstDatos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        String fMes = "0";
        try {
            // name of procedure
            String sp = "{call PKG_DASHBOARD.SP_GRAFICO_DEPO(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            List<Object> lstMes = new ArrayList<>();
            List<Object> lstDep = new ArrayList<>();

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOM").getParameterResultSet();
            // MESES
            while (resultSet.next()) {
                lstMes.add("'" + (String) resultSet.getString("D_MES") + "'");
            }
            lstDatos.add(lstMes);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            // fondo 0001
            while (resultSet.next()) {
                lstDep.add(resultSet.getFloat("I_DEPO"));
            }
            lstDatos.add(lstDep);
            // fondo 0002
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            List<Object> lstDepB = new ArrayList<>();
            while (resultSet.next()) {
                lstDepB.add(resultSet.getFloat("I_DEPO"));
            }
            lstDatos.add(lstDepB);
            // fondo 0003
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOC").getParameterResultSet();
            List<Object> lstDepC = new ArrayList<>();
            while (resultSet.next()) {
                lstDepC.add(resultSet.getFloat("I_DEPO"));
            }
            lstDatos.add(lstDepC);
            // fondo 0004
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOD").getParameterResultSet();
            List<Object> lstDepD = new ArrayList<>();
            while (resultSet.next()) {
                lstDepD.add(resultSet.getFloat("I_DEPO"));
            }
            lstDatos.add(lstDepD);
        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
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
        return lstDatos;
    }

    @Override
    public ArrayList<List<Object>> fetchAllMeta(CobCronogramaMetaAvance oCobCronogramaMetaAvance) {
        ArrayList<List<Object>> lstDatos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        String fMes = "0";
        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_GRAFICO_META2(?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersMeta(oCobCronogramaMetaAvance);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            List<Object> lstMes = new ArrayList<>();
            List<Object> lstDep = new ArrayList<>();

            resultSet = getOutputParameter("PO_CURSOR_RESULTADOM").getParameterResultSet();
            // MESES
            while (resultSet.next()) {
                lstMes.add("'" + (String) resultSet.getString("D_MES") + "'");
            }
            lstDatos.add(lstMes);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            // fondo 0001
            while (resultSet.next()) {
                lstDep.add(resultSet.getFloat("I_DEPO"));
            }
            lstDatos.add(lstDep);
            // fondo 0002
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOB").getParameterResultSet();
            List<Object> lstDepB = new ArrayList<>();
            while (resultSet.next()) {
                lstDepB.add(resultSet.getFloat("I_DEPO"));
            }
            lstDatos.add(lstDepB);
            /*
            // fondo 0003
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOC").getParameterResultSet();
            List<Object> lstDepC = new ArrayList<>();
            while (resultSet.next()) {
                lstDepC.add(resultSet.getFloat("I_DEPO"));
            }
            lstDatos.add(lstDepC);
            // fondo 0004
            resultSet = getOutputParameter("PO_CURSOR_RESULTADOD").getParameterResultSet();
            List<Object> lstDepD = new ArrayList<>();
            while (resultSet.next()) {
                lstDepD.add(resultSet.getFloat("I_DEPO"));
            }
            lstDatos.add(lstDepD);
            */
        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
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
        return lstDatos;
    }
    
    @Override
    public Boolean loadDataFPH() {
        Boolean bolDatos = false;
        OracleCallableStatement cmd = null;
        try {
            // name of procedure
            String sp = "{call PKG_CARGA_DATA.SP_CARGA_DATA_PEH(?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersLoadDataFPH();
             //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oList, cn, cmd, sp);
            bolDatos = true;

        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
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
        return bolDatos;
    }

    @Override
    public Boolean loadDataFEM() {
        Boolean bolDatos = false;
        OracleCallableStatement cmd = null;
        try {
            // name of procedure
            String sp = "{call PKG_CARGA_DATA.SP_CARGA_DATA_EMP(?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersLoadDataFPH();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oList, cn, cmd, sp);
            bolDatos = true;

        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
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
        return bolDatos;
    }

    @Override
    public Boolean loadDataFMY() {
        Boolean bolDatos = false;
        OracleCallableStatement cmd = null;
        try {
            // name of procedure
            String sp = "{call PKG_CARGA_DATA.SP_CARGA_DATA_MYP(?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersLoadDataFPH();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oList, cn, cmd, sp);
            bolDatos = true;

        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
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
        return bolDatos;
    }

    @Override
    public Boolean loadDataFPO() {
        Boolean bolDatos = false;
        OracleCallableStatement cmd = null;
        try {
            // name of procedure
            String sp = "{call PKG_CARGA_DATA.SP_CARGA_DATA_POP(?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersLoadDataFPH();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oList, cn, cmd, sp);
            bolDatos = true;

        } catch (Exception e) {
            Logger.getLogger(MaeDashboardDao.class.getName()).log(Level.SEVERE, null, e);
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
        return bolDatos;
    }

    private List<ParameterOracle> listParameters() {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOM", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOB", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOC", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOD", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParametersMeta(CobCronogramaMetaAvance oCobCronogramaMetaAvance) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_N_ANIO",(Number)Integer.valueOf(oCobCronogramaMetaAvance.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", (Number)Integer.valueOf(oCobCronogramaMetaAvance.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FECHA", oCobCronogramaMetaAvance.getF_fecha(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oCobCronogramaMetaAvance.getC_usuario_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobCronogramaMetaAvance.getC_fondo_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOM", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOB", null, OracleTypes.CURSOR, ParameterDirection.Output));
        //oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOC", null, OracleTypes.CURSOR, ParameterDirection.Output));
        //oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOD", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParametersLoadDataFPH() {
        List<ParameterOracle> oListParam = new ArrayList<>();

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
