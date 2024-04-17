/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.CobTablas;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ICobTablasDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class CobTablasDao extends DBUtil implements ICobTablasDao {
    
    private OracleConnection cn = null;

    public CobTablasDao() {

    }

    public CobTablasDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(CobTablas oCobTablas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(CobTablas oCobTablas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CobTablas oCobTablas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CobTablas> fetchAll(CobTablas oCobTablas) {
        
        //System.out.println(" <i> CobTablas fetch all " + LocalDateTime.now());
        
        ArrayList<CobTablas> lstTablas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_TABLAS.SP_CARGAR_TABLA(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oCobTablas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();            
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //tablas
                CobTablas newCobTablas = new CobTablas();
                newCobTablas.setCtablaId(resultSet.getString("C_TABLA_ID"));
                newCobTablas.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newCobTablas.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                newCobTablas.setDdescCorta(resultSet.getString("D_DESC_CORTA"));
                newCobTablas.seteEstado(resultSet.getString("E_ESTADO"));
                newCobTablas.setPriori(resultSet.getInt("PRIORI"));
                
                //
                lstTablas.add(newCobTablas);
            }

        } catch (Exception e) {
            Logger.getLogger(CobTablas.class.getName()).log(Level.SEVERE, null, e);
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

        //System.out.println(" <f> CobTablas fetch all " + LocalDateTime.now());
        
        return lstTablas;
    }

    private List<ParameterOracle> listParameters(CobTablas oCobTablas) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_TABLA_ID", oCobTablas.getCtablaId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TABLA_DET_ID", oCobTablas.getCtablaDetId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DESCRIPCION", oCobTablas.getDdescripcion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO", oCobTablas.geteEstado(), OracleTypes.CHAR, ParameterDirection.Input));
        
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

