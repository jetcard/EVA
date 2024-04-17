/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.CobCronogramaMetaAvance;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ICobCronogramaMetaAvanceDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author EC23329
 */
public class CobCronogramaMetaAvanceDao extends DBUtil implements ICobCronogramaMetaAvanceDao{
    private OracleConnection cn = null;

    public CobCronogramaMetaAvanceDao() {

    }

    public CobCronogramaMetaAvanceDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public ArrayList<CobCronogramaMetaAvance> fetchAll(CobCronogramaMetaAvance oCobCronogramaMetaAvance) {
        System.out.println("CobCronogramaMetaAvanceDao: fetchAll()");
        ArrayList<CobCronogramaMetaAvance> lstMetaAvance = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        
        double doMeta = 0;
        double doEfi = 0;
        double doPor = 0;
        
        try {
            CobCronogramaMetaAvance cro = new CobCronogramaMetaAvance();
            
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_RPT_META_AVANCE2(?,?,?,?,?,?,?,?)}";
            System.out.println(sp);
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus(oCobCronogramaMetaAvance);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                cro = new CobCronogramaMetaAvance();
                //cro.setN_anio(resultSet.getString("n_anio"));
                //cro.setN_mes(resultSet.getString("n_mes"));
                //cro.setF_fecha(resultSet.getDate("f_fin"));
                //cro.setC_usuario_id(resultSet.getString("c_usuario_id"));
                cro.setI_id(resultSet.getInt("ID"));
                cro.setC_concepto(resultSet.getString("C_CONCEPTO"));
                cro.setI_total(resultSet.getDouble("I_TOTAL"));
                if (cro.getC_concepto().contains("META")){
                    doMeta = cro.getI_total();
                }
                else if(cro.getC_concepto().contains("RECAUDACION")){
                    
                }
                else if(cro.getC_concepto().contains("CLIENTE")){
                    
                }
                else {
                    doEfi = cro.getI_total();
                }
                lstMetaAvance.add(cro);                
            }
            if (lstMetaAvance.size() > 0){ 
                cro = new CobCronogramaMetaAvance();
                doPor = (doEfi/doMeta)*100;
                cro.setI_id(5);
                cro.setC_concepto("% EFIC.");
                cro.setI_total(doPor);
                lstMetaAvance.add(cro);  
            }    
                    
        } catch (Exception e) {
            System.out.println(e);
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
        //System.out.println(" <i> CobCompromisoDao fetchAll " + LocalDateTime.now());
        return lstMetaAvance;
    }
    
    private List<ParameterOracle> listParameters_bus(CobCronogramaMetaAvance oCobCronogramaMetaAvance) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_N_ANIO",(Number)Integer.valueOf(oCobCronogramaMetaAvance.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", (Number)Integer.valueOf(oCobCronogramaMetaAvance.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FECHA", oCobCronogramaMetaAvance.getF_fecha(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ID", oCobCronogramaMetaAvance.getC_usuario_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oCobCronogramaMetaAvance.getC_fondo_id(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        
        return oListParam;
    }
}
