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
import pop.comun.dominio.LegalAsesor;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.iface.ILegalAsesorDao;

/**
 *
 * @author HH38092 XXXXX
 */
public class LegalAsesorDao extends DBUtil implements ILegalAsesorDao {
    
    private OracleConnection cn = null;

    public LegalAsesorDao() {

    }

    public LegalAsesorDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public ArrayList<LegalAsesor> fetchAll() {
        ArrayList<LegalAsesor> lstAsesor = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            String sp = "{call PKG_LEGAL.SP_BUS_ASESOR(?,?,?)}";
            List<ParameterOracle> oList = new ArrayList<>();
            oList = listParameters();
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                LegalAsesor newAsesor = new LegalAsesor();
                
                newAsesor.setCusuarioId(resultSet.getString("c_usuario_id"));
                newAsesor.setNombreyapellido(resultSet.getString("c_nombre"));
                
                lstAsesor.add(newAsesor);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeAsesorDao.class.getName()).log(Level.SEVERE, null, e);
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
        return lstAsesor;
    }
    
    private List<ParameterOracle> listParameters() {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
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
