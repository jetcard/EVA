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
import pop.comun.dominio.LegGastoJudicial;
import pop.comun.dominio.LegalTchn;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.iface.ILegalGastosJudicialesDao;
/**
 *
 * @author HH38092
 */
public class LegalGastosJudicialesDao extends DBUtil implements ILegalGastosJudicialesDao {
    private OracleConnection cn = null;

    public LegalGastosJudicialesDao() {

    }

    public LegalGastosJudicialesDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public ArrayList<LegGastoJudicial> fetchAll(LegGastoJudicial olegGastoJudi) {
        ArrayList<LegGastoJudicial> lstGastoJudicial = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            String sp = "{call PKG_LEGAL.SP_LISTA_GASTOS_JUDICIALES(?,?,?,?,?,?)}";
            List<ParameterOracle> oList = new ArrayList<>();
            oList = listParameters(olegGastoJudi);
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                LegGastoJudicial newGastoJudicial = new LegGastoJudicial();
                newGastoJudicial.setIdGastoJudicial(resultSet.getInt("ID_LEG_GASTO_JUDICIAL"));
                newGastoJudicial.setDescEtapa(resultSet.getString("ETAPA"));
                newGastoJudicial.setFecha(resultSet.getDate("FECHA"));
                newGastoJudicial.setDescTipogasto(resultSet.getString("TIPOGASTO"));
                newGastoJudicial.setMonto(resultSet.getDouble("MONTO"));
                newGastoJudicial.setFondo(resultSet.getString("C_FONDO_ID"));
                newGastoJudicial.setCodigoTCHN(resultSet.getString("C_INVERSION"));
                newGastoJudicial.setNroexpediente(resultSet.getString("NROEXP"));
                newGastoJudicial.setNumeroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newGastoJudicial.setNroComprobante(resultSet.getString("NROCOMPROBANTE"));
                newGastoJudicial.setComentario(resultSet.getString("COMENTARIO"));
                newGastoJudicial.setTipoGasto(resultSet.getString("IDTIPOGASTO"));
                newGastoJudicial.setEtapa(resultSet.getString("IDETAPA"));
                
                lstGastoJudicial.add(newGastoJudicial);
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
        return lstGastoJudicial;
    }
    
    private List<ParameterOracle> listParameters(LegGastoJudicial olegGastoJudi) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", olegGastoJudi.getFondo().trim(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", olegGastoJudi.getCodigoTCHN().trim(), OracleTypes.VARCHAR, ParameterDirection.Input));//
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", olegGastoJudi.getNumeroDocumento().trim(), OracleTypes.CHAR, ParameterDirection.Input));
        
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
