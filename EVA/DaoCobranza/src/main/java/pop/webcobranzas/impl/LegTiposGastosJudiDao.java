/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.LegTabla;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.iface.ILegTiposGastosJudiDao;
/**
 *
 * @author HH38092
 */
public class LegTiposGastosJudiDao   extends DBUtil implements ILegTiposGastosJudiDao {
    private OracleConnection cn = null;

    public LegTiposGastosJudiDao() {
        
    }

    public LegTiposGastosJudiDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public ArrayList<LegTabla> fetchAll() {
        ArrayList<LegTabla> lstTabla = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            String sp = "{call PKG_LEGAL.SP_LISTA_TABLA(?,?,?,?)}";
            List<ParameterOracle> oList = new ArrayList<>();
            oList = listParameters("0002");//00012 TIPOS GASTOS JUDICIALES
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                LegTabla newTabla = new LegTabla();
                newTabla.setCTablaId(resultSet.getString("C_TABLA_DET_ID"));
                newTabla.setDTabla(resultSet.getString("D_DESCRIPCION"));
                
                lstTabla.add(newTabla);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        finally 
        {
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
        return lstTabla;
    }
    
    private List<ParameterOracle> listParameters(String tablaID) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_TABLA_ID", tablaID, OracleTypes.VARCHAR, ParameterDirection.Input));
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