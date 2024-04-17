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
import pop.comun.dominio.TabTipoDocumento;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ITabTipoDocumentoDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class TabTipoDocumentoDao extends DBUtil implements ITabTipoDocumentoDao {

    private OracleConnection cn = null;

    public TabTipoDocumentoDao() {
    }
       
    public TabTipoDocumentoDao(OracleConnection cnx) {
        cn = cnx;
    }
       

    @Override
    public ArrayList<TabTipoDocumento> fetchAll(TabTipoDocumento oTabTipoDocumento) {
        ArrayList<TabTipoDocumento> lstTipoDocumento = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_TIPO_DOC.SP_BUSCAR_TIPO_DOC(?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oTabTipoDocumento);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //ubigeo
                TabTipoDocumento newTipoDocumento = new TabTipoDocumento();
                newTipoDocumento.setCtabTipoDocId(resultSet.getInt("C_TAB_TIPO_DOC_ID"));
                newTipoDocumento.setNorden(resultSet.getInt("N_ORDEN"));
                newTipoDocumento.setDnombre(resultSet.getString("D_NOMBRE"));
                newTipoDocumento.setDnombreCorto(resultSet.getString("D_NOMBRE_CORTO"));
                newTipoDocumento.setNcolor(resultSet.getInt("N_COLOR"));
                newTipoDocumento.setNdiasEjecutar(resultSet.getInt("N_DIAS_EJECUTAR"));
                newTipoDocumento.setNdiasAprox(resultSet.getInt("N_DIAS_PROX"));
                newTipoDocumento.setNproxTipoDoc(resultSet.getInt("N_PROX_TIPO_DOC"));

                //
                lstTipoDocumento.add(newTipoDocumento);
            }
            resultSet.close();
            //if (cmd != null) {
            //    cmd.close();
            //}

        } catch (Exception e) {
            Logger.getLogger(TabTipoDocumentoDao.class.getName()).log(Level.SEVERE, null, e);
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
        return lstTipoDocumento;
    }
    
    private List<ParameterOracle> listParameters(TabTipoDocumento oTabTipoDocumento) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_TAB_TIPO_DOC_ID", oTabTipoDocumento.getCtabTipoDocId(), OracleTypes.NUMBER, ParameterDirection.Input));
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
