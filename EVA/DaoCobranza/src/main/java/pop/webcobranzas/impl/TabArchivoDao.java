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
import pop.comun.dominio.TabArchivo;
import pop.comun.dominio.TabDocumentosDet;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ITabArchivoDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class TabArchivoDao extends DBUtil implements ITabArchivoDao {
    
    private OracleConnection cn = null;

    public TabArchivoDao() {
    }
    
    public TabArchivoDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public Integer insert(TabArchivo oTabArchivo) {
        //System.out.println(" <i> TabArchivoDao insert " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_ARCHIVOS.SP_INS_TAB_ARCVHIVO(?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oTabArchivo);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_PI_C_TAB_ARCHIVO_ID").getParameterInt();

        } catch (SQLException e) {
            Logger.getLogger(TabArchivoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(TabArchivoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> TabArchivoDao insert  " + LocalDateTime.now());
        return newID;
    }

    private List<ParameterOracle> insertParameters(TabArchivo oTabArchivo) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_TAB_DOCUMENTO_DET_ID", oTabArchivo.getTabDocumentosDet().getCtabDocumentosDetId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_ARC_ID", oTabArchivo.getCtipoArcId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_SERIE_NUM", oTabArchivo.getDserieNumero(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_NUMERACION", oTabArchivo.getNnumeracion(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_NOMBRE_ARC", oTabArchivo.getDnombreArc(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_RUTA", oTabArchivo.getDruta(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_GENERACION", oTabArchivo.getFgeneracion(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oTabArchivo.getEestadoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oTabArchivo.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
       
        oListParam.add(new ParameterOracle("PO_PI_C_TAB_ARCHIVO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
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

    @Override
    public ArrayList<TabArchivo> fetchAll(TabArchivo oTabArchivo) {
        //System.out.println(" <i> fetchAll TabArchivoDao " + LocalDateTime.now());
        ArrayList<TabArchivo> lstArchivos = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_ARCHIVOS.SP_LISTAR_ARCHIVOS(?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = fetchAllParameters(oTabArchivo);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                TabArchivo newArchivo = new TabArchivo();
                TabDocumentosDet newDocumentosDet = new TabDocumentosDet();
                newDocumentosDet.setCtabDocumentosDetId(resultSet.getInt("C_TAB_DOCUMENTO_DET_ID"));
                newArchivo.setTabDocumentosDet(newDocumentosDet);
                newArchivo.setCtabArchivoId(resultSet.getInt("C_TAB_ARCHIVO_ID"));
                newArchivo.setCtipoArcId(resultSet.getString("C_TIPO_ARC_ID"));
                newArchivo.setDserieNumero(resultSet.getString("D_SERIE_NUM"));
                newArchivo.setNnumeracion(resultSet.getInt("N_NUMERACION"));
                newArchivo.setDnombreArc(resultSet.getString("D_NOMBRE_ARC"));
                newArchivo.setDruta(resultSet.getString("D_RUTA"));
                newArchivo.setFgeneracion(resultSet.getDate("F_GENERACION"));
                newArchivo.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                newArchivo.seteEstado(resultSet.getString("E_ESTADO"));
                newArchivo.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newArchivo.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newArchivo.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                newArchivo.setfUsuarioMod(resultSet.getDate("F_USUARIO_MOD"));
                lstArchivos.add(newArchivo);
            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
            }

        } catch (Exception e) {
            Logger.getLogger(TabArchivoDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> fetchAll TabArchivoDao " + LocalDateTime.now());
        return lstArchivos;
    }
    
    private List<ParameterOracle> fetchAllParameters(TabArchivo oTabArchivo) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_TAB_DOCUMENTO_DET_ID", oTabArchivo.getTabDocumentosDet().getCtabDocumentosDetId(), OracleTypes.NUMBER, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    
}
