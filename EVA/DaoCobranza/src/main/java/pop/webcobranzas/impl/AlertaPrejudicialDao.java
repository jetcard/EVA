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
import pop.comun.dominio.AlertaPreJudicial;
import pop.comun.dominio.LegalTchn;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.iface.IAlertaPrejudicialDao;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
/**
 *
 * @author PR154357
 */
public class AlertaPrejudicialDao extends DBUtil implements IAlertaPrejudicialDao{
    private OracleConnection cn = null;
 
    
    public AlertaPrejudicialDao() {
        super();
    }

    public AlertaPrejudicialDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public ArrayList<AlertaPreJudicial> consultarAlertasPreJudiciales(LegalTchn oLegalTchn) {
        System.out.println(" <i> EVA.PKG_LEGAL.SP_CONSULTA_ALERTA_JUDICIAL(?,?,?,?,?)");
        ArrayList<AlertaPreJudicial> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LEGAL.SP_CONSULTA_ALERTA_JUDICIAL(?,?,?,?,?,?)}";
            // list of parameter
            System.out.println("paso1");
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            System.out.println("paso2");
            oList = listParameters_PJ(oLegalTchn);
            //Abre conexion a la BD
        System.out.println("paso3");
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            System.out.println("paso4");
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                AlertaPreJudicial alertaPreJudicial = new AlertaPreJudicial();
                alertaPreJudicial.setCliente(resultSet.getString("NOMBRES"));
                alertaPreJudicial.setCodigoTchn(resultSet.getString("DVALOR_BV"));
                System.out.println("paso55");
                alertaPreJudicial.setCuotasAtrasadas(resultSet.getString("NCUOTA_VENCIDA"));
                alertaPreJudicial.setCuotasGeneradas(resultSet.getString("NCUOTAS_GENERADAS"));
                alertaPreJudicial.setFechaDesembolso(resultSet.getString("FDESEMBOLSO"));
                System.out.println("paso66");
                alertaPreJudicial.setFechaProtesto(resultSet.getString("FPROTESTO"));
                alertaPreJudicial.setFechaVencimiento(resultSet.getString("FVCTO"));
                alertaPreJudicial.setFondo(resultSet.getString("FONDO"));
                alertaPreJudicial.setMoneda(resultSet.getString("CMONEDA"));
                alertaPreJudicial.setMonto(resultSet.getString("MONTO"));
                alertaPreJudicial.setDiasDesdeProtesto(resultSet.getString("DIAS_TRANSURRIDOS"));
                System.out.println("pas77");
                lstTchn.add(alertaPreJudicial);
            }
            System.out.println("termino"+lstTchn.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        //System.out.println(" <f> MaeEstadoCuentaDao fetchAllTchn " + LocalDateTime.now());
        return lstTchn;
    }
    
    private List<ParameterOracle> listParameters_PJ(LegalTchn oLegalTchn) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PC_FONDO", oLegalTchn.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHAINI", oLegalTchn.getFIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHAFIN", oLegalTchn.getFFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }    
    
}
