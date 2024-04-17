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
import pop.comun.dominio.TabTipoDocumentoEstado;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ITabTipoDocumentoEstadoDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;

/**
 *
 * @author Jyoverar
 */
public class TabTipoDocumentoEstadoDao extends DBUtil implements ITabTipoDocumentoEstadoDao {

    private OracleConnection cn = null;

    public TabTipoDocumentoEstadoDao() {
    }
    
    public TabTipoDocumentoEstadoDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public ArrayList<TabTipoDocumentoEstado> fetchAll(TabTipoDocumentoEstado oTabTipoDocumentoEstado) {
        ArrayList<TabTipoDocumentoEstado> lstTipoDocumentoEstado = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_TIPO_DOC.SP_BUSCAR_TIPO_DOC_EST(?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oTabTipoDocumentoEstado);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            oList = listParameters(oTabTipoDocumentoEstado);
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //ubigeo
                TabTipoDocumentoEstado newTipoDocumentoEstado = new TabTipoDocumentoEstado();
                newTipoDocumentoEstado.setCtabTipDocEstId(resultSet.getInt("C_TAB_TIPO_DOC_EST_ID"));
                newTipoDocumentoEstado.setDnombre(resultSet.getString("D_NOMBRE"));
                
                TabTipoDocumento newTabTipoDocumento = new TabTipoDocumento();
                newTabTipoDocumento.setCtabTipoDocId(resultSet.getInt("C_TAB_TIPO_DOC_ID"));
                
                newTipoDocumentoEstado.setTabTipoDocumento(newTabTipoDocumento);
                //
                lstTipoDocumentoEstado.add(newTipoDocumentoEstado);
            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
            }

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
        
        return lstTipoDocumentoEstado;
    }

     private List<ParameterOracle> listParameters(TabTipoDocumentoEstado oTabTipoDocumentoEstado) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_TAB_TIPO_DOC_ID", oTabTipoDocumentoEstado.getTabTipoDocumento().getCtabTipoDocId(), OracleTypes.NUMBER, ParameterDirection.Input));
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
