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
import pop.comun.dominio.LaftPersona;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ILaftPersonaDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class LaftPersonaDao extends DBUtil implements ILaftPersonaDao {
    
    private OracleConnection cn = null;

    public LaftPersonaDao() {

    }

    public LaftPersonaDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(LaftPersona oLaftPersona) {
        Integer newID = 0;
        OracleCallableStatement cmd = null;
        
        try {                        
            // name of procedure
            String sp = "{call PKG_LAFT_PERSONA.SP_INS_LAFTPERSONA(?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oLaftPersona);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID =  getOutputParameter("PO_NEW_ID").getParameterInt();
                    
        } catch (SQLException e) {            
            Logger.getLogger(LaftPersonaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {            
            Logger.getLogger(LaftPersonaDao.class.getName()).log(Level.SEVERE, null, e);
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
        return newID;
    }

    @Override
    public void update(LaftPersona oLaftPersona) {
        
    }

    @Override
    public void delete(LaftPersona oLaftPersona) {
        
    }

    @Override
    public ArrayList<LaftPersona> fetchAll(LaftPersona oLaftPersona) {
        ArrayList<LaftPersona> lstPersona = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_LAFT_PERSONA.SP_BUS_LAFT_REG_PERSONA(?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oLaftPersona);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
                //persona
                LaftPersona newPersona = new LaftPersona();
                newPersona.setfRegistro(resultSet.getDate("F_REGISTRO"));
                newPersona.setaNroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setcEntidadId(resultSet.getString("D_DESC_CORTA"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                newPersona.setdObservaciones(resultSet.getString("D_OBSERVACIONES"));

                lstPersona.add(newPersona);
            }

        } catch (Exception e) {
            Logger.getLogger(LaftPersona.class.getName()).log(Level.SEVERE, null, e);
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
        return lstPersona;
    }
    
    
     private List<ParameterOracle> insertParameters(LaftPersona oLaftPersona){
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_F_REGISTRO", oLaftPersona.getfRegistro(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oLaftPersona.getaNroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_ENTIDAD_ID", oLaftPersona.getcEntidadId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_APE_PAT", oLaftPersona.getDApePat(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_APE_MAT", oLaftPersona.getDApeMat(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_NOMBRES", oLaftPersona.getDNombres(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_SEXO_ID", oLaftPersona.getcSexoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_OBSERVACIONES", oLaftPersona.getdObservaciones(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oLaftPersona.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_NEW_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
                
        return oListParam;
    }
    
    private List<ParameterOracle> listParameters(LaftPersona oLaftPersona) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_FECINI", oLaftPersona.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FECFIN", oLaftPersona.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oLaftPersona.getaNroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_ENTIDAD_ID", oLaftPersona.getcEntidadId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oLaftPersona.getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
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
