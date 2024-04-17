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
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaCabecera;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.iface.ICobCronogramaMetaCabeceraDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author EC23329
 */
public class CobCronogramaMetaCabeceraDao extends DBUtil implements ICobCronogramaMetaCabeceraDao {
    private OracleConnection cn = null;

    public CobCronogramaMetaCabeceraDao() {

    }

    public CobCronogramaMetaCabeceraDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public Integer insert(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) {
        //System.out.println(" <i> insertar compromiso " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call pppppp.xxxxx(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oCobCronogramaMetaCabecera);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PI_C_COB_COMPROMISO_ID").getParameterInt();

        } catch (SQLException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
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
        //System.out.println(" <f> insertar compromiso " + LocalDateTime.now());
        return newID;
    }

    @Override
    public Integer validarAprobacion(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) {
        //System.out.println(" <i> insertar compromiso " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_BUS_META_CABECERA_APROBAR(?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = validarParameters(oCobCronogramaMetaCabecera);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PI_TOTAL").getParameterInt();

        } catch (SQLException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
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
        //System.out.println(" <f> insertar compromiso " + LocalDateTime.now());
        return newID;
    }
    
    @Override
    public void update(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) {
        //System.out.println(" <i> actualizar compromiso " + LocalDateTime.now());
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_COB_METAS_ASESORES.SP_UPD_META_CABECERA_APROBAR(?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = updateParameters(oCobCronogramaMetaCabecera);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);


        } catch (SQLException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
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
        //System.out.println(" <f> insertar compromiso " + LocalDateTime.now());
    }
    
    private List<ParameterOracle> insertParameters(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_COB_COMPROMISO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
   
    private List<ParameterOracle> updateParameters(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_N_ANIO", Integer.parseInt(oCobCronogramaMetaCabecera.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", Integer.parseInt(oCobCronogramaMetaCabecera.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));        
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobCronogramaMetaCabecera.getC_usuario_aprobado(), OracleTypes.VARCHAR, ParameterDirection.Input));
                
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }  
    
    private List<ParameterOracle> validarParameters(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_N_ANIO", Integer.parseInt(oCobCronogramaMetaCabecera.getN_anio()), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_MES", Integer.parseInt(oCobCronogramaMetaCabecera.getN_mes()), OracleTypes.NUMBER, ParameterDirection.Input));        
                        
        oListParam.add(new ParameterOracle("PI_TOTAL", "", OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }  
}
