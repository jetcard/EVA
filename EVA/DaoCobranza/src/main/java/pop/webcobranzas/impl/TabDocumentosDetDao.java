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
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.TabDocumentos;
import pop.comun.dominio.TabDocumentosDet;
import pop.comun.dominio.TabTipoDocumento;
import pop.comun.dominio.TabTipoDocumentoEstado;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ITabDocumentosDetDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class TabDocumentosDetDao extends DBUtil implements ITabDocumentosDetDao {

    private OracleConnection cn = null;

    public TabDocumentosDetDao() {
    }

    public TabDocumentosDetDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(TabDocumentosDet oTabDocumentosDet) {
        System.out.println(" <i> insert TabDocumentosDetDao " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_DOCUMENTO_DET.SP_INS_TAB_DOC_DET(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oTabDocumentosDet);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            newID = getOutputParameter("PO_C_TAB_DOCUMENTO_DET_ID").getParameterInt();

        } catch (SQLException e) {
            Logger.getLogger(TabDocumentosDetDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(TabDocumentosDetDao.class.getName()).log(Level.SEVERE, null, e);
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
        System.out.println(" <f> insert TabDocumentosDetDao " + LocalDateTime.now());
        return newID;
    }

    @Override
    public void update(TabDocumentosDet oTabDocumentosDet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TabDocumentosDet oTabDocumentosDet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TabDocumentosDet> fetchAll(TabDocumentosDet oTabDocumentosDet) {
        ArrayList<TabDocumentosDet> lstDetDoc = new ArrayList<>();

        System.out.println(" <i> fetchAll TabDocumentosDetDao " + LocalDateTime.now());
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_DOCUMENTO_DET.SP_LISTAR_DOC_DET(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = fetchAllParameters(oTabDocumentosDet);
           //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));

                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));

                // documento
                TabDocumentos newDoc = new TabDocumentos();
                newDoc.setCtabDocumentosId(resultSet.getInt("C_TAB_DOCUMENTO_ID"));
                newDoc.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                newDoc.seteEstado(resultSet.getString("E_ESTADO"));
                newDoc.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newDoc.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newDoc.setMaeInversion(newInversion);
                // detalle de doc
                TabDocumentosDet newDetDoc = new TabDocumentosDet();
                newDetDoc.setCtabDocumentosDetId(resultSet.getInt("C_TAB_DOCUMENTO_DET_ID"));
                newDetDoc.setTabDocumentos(newDoc);
                newDetDoc.setFfecha(resultSet.getDate("F_FECHA"));
                newDetDoc.setDobs(resultSet.getString("D_OBS"));
                newDetDoc.setCtablaDetId(resultSet.getString("C_TABLA_DET_ID"));
                newDetDoc.seteEstado(resultSet.getString("E_ESTADO_DET"));
                newDetDoc.setFopc1(resultSet.getDate("F_OPC_1"));
                newDetDoc.setFopc2(resultSet.getDate("F_OPC_2"));
                newDetDoc.setNopc1(resultSet.getInt("N_OPC_1"));
                newDetDoc.setNopc2(resultSet.getInt("N_OPC_2"));
                newDetDoc.setDopc1(resultSet.getString("D_OPC_1"));
                newDetDoc.setDopc2(resultSet.getString("D_OPC_2"));
                newDetDoc.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD_DET"));
                newDetDoc.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD_DET"));
                newDetDoc.setBsoloLectura(resultSet.getString("B_SOLO_LECTURA"));
                newDetDoc.setBcargo(resultSet.getString("B_CARGO"));

                // tipo documento
                TabTipoDocumento newTipoDoc = new TabTipoDocumento();
                newTipoDoc.setCtabTipoDocId(resultSet.getInt("C_TAB_TIPO_DOC_ID"));
                newTipoDoc.setDnombre(resultSet.getString("D_NOMBRE"));
                newTipoDoc.setDnombreCorto(resultSet.getString("D_NOMBRE_CORTO"));
                newDoc.setTabTipoDocumento(newTipoDoc);

                // tipo documento estado
                TabTipoDocumentoEstado newTipoDocEst = new TabTipoDocumentoEstado();
                newTipoDocEst.setCtabTipDocEstId(resultSet.getInt("C_TAB_TIPO_DOC_EST_ID"));
                newTipoDocEst.setDnombre(resultSet.getString("D_NOMBRE_EST"));
                newTipoDocEst.setNorden(resultSet.getInt("N_ORDEN_EST"));
                newTipoDocEst.setBsoloLectura(resultSet.getString("B_SOLO_LECTURA"));
                newTipoDocEst.setBcargo(resultSet.getString("B_CARGO"));
                newTipoDocEst.setCtipoVisualzaId(resultSet.getString("C_TIPO_VISUALIZA_ID"));
                newTipoDocEst.setTabTipoDocumento(newTipoDoc);

                newDetDoc.setTabTipoDocumentoEstado(newTipoDocEst);
                lstDetDoc.add(newDetDoc);
            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
            }

        } catch (Exception e) {
            Logger.getLogger(TabDocumentosDetDao.class.getName()).log(Level.SEVERE, null, e);
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
        System.out.println(" <f> fetchAll TabDocumentosDetDao " + LocalDateTime.now());

        return lstDetDoc;
    }

    @Override
    public TabDocumentosDet fetchOnly(TabDocumentosDet oTabDocumentosDet) {
        TabDocumentosDet detDoc = new TabDocumentosDet();

        System.out.println(" <i> fetchOnly TabDocumentosDetDao " + LocalDateTime.now());
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_TAB_DOCUMENTO_DET.SP_BUSCAR_DOC_DET(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = fetchOnlyParameters(oTabDocumentosDet);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));

                // inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setMaeFondo(newFondo);
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                //newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));

                // documento
                TabDocumentos newDoc = new TabDocumentos();
                newDoc.setCtabDocumentosId(resultSet.getInt("C_TAB_DOCUMENTO_ID"));
                newDoc.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                newDoc.seteEstado(resultSet.getString("E_ESTADO"));
                newDoc.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newDoc.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newDoc.setMaeInversion(newInversion);

                detDoc.setCtabDocumentosDetId(resultSet.getInt("C_TAB_DOCUMENTO_DET_ID"));
                detDoc.setTabDocumentos(newDoc);
                detDoc.setFfecha(resultSet.getDate("F_FECHA"));
                detDoc.setDobs(resultSet.getString("D_OBS"));
                
                detDoc.setFopc1(resultSet.getDate("F_OPC_1"));
                detDoc.setFopc2(resultSet.getDate("F_OPC_2"));
                detDoc.setNopc1(resultSet.getInt("N_OPC_1"));
                detDoc.setNopc2(resultSet.getInt("N_OPC_2"));
                detDoc.setDopc1(resultSet.getString("D_OPC_1"));
                detDoc.setDopc2(resultSet.getString("D_OPC_2"));
                
                detDoc.seteEstado(resultSet.getString("E_ESTADO_DET"));
                detDoc.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD_DET"));
                detDoc.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD_DET"));

                // tipo documento
                TabTipoDocumento newTipoDoc = new TabTipoDocumento();
                newTipoDoc.setCtabTipoDocId(resultSet.getInt("C_TAB_TIPO_DOC_ID"));
                newTipoDoc.setDnombre(resultSet.getString("D_NOMBRE"));
                newTipoDoc.setDnombreCorto(resultSet.getString("D_NOMBRE_CORTO"));

                // tipo documento estado
                TabTipoDocumentoEstado newTipoDocEst = new TabTipoDocumentoEstado();
                newTipoDocEst.setCtabTipDocEstId(resultSet.getInt("C_TAB_TIPO_DOC_EST_ID"));
                newTipoDocEst.setDnombre(resultSet.getString("D_NOMBRE_EST"));
                newTipoDocEst.setNorden(resultSet.getInt("N_ORDEN_EST"));
                newTipoDocEst.setTabTipoDocumento(newTipoDoc);

                detDoc.setTabTipoDocumentoEstado(newTipoDocEst);
            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
            }

        } catch (Exception e) {
            Logger.getLogger(TabDocumentosDetDao.class.getName()).log(Level.SEVERE, null, e);
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
        System.out.println(" <f> fetchOnly TabDocumentosDetDao " + LocalDateTime.now());

        return detDoc;
    }

    private List<ParameterOracle> fetchOnlyParameters(TabDocumentosDet oTabDocumentosDet) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oTabDocumentosDet.getTabDocumentos().getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oTabDocumentosDet.getTabDocumentos().getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oTabDocumentosDet.getTabDocumentos().getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> fetchAllParameters(TabDocumentosDet oTabDocumentosDet) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oTabDocumentosDet.getTabDocumentos().getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oTabDocumentosDet.getTabDocumentos().getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oTabDocumentosDet.getTabDocumentos().getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TAB_DOCUMENTO_ID", oTabDocumentosDet.getTabDocumentos().getCtabDocumentosId(), OracleTypes.NUMBER, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> insertParameters(TabDocumentosDet oTabDocumentosDet) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_TAB_DOCUMENTO_ID", oTabDocumentosDet.getTabDocumentos().getCtabDocumentosId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TAB_TIPO_DOC_EST_ID", oTabDocumentosDet.getTabTipoDocumentoEstado().getCtabTipDocEstId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_FECHA", oTabDocumentosDet.getFfecha(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_OBS", oTabDocumentosDet.getDobs(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TABLA_ID", oTabDocumentosDet.getCtablaId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TABLA_DET_ID", oTabDocumentosDet.getCtablaDetId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oTabDocumentosDet.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PI_F_OPC_1", oTabDocumentosDet.getFopc1(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_OPC_2", oTabDocumentosDet.getFopc2(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_OPC_1", oTabDocumentosDet.getNopc1(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_OPC_2", oTabDocumentosDet.getNopc2(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_OPC_1", oTabDocumentosDet.getDopc1(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_OPC_2", oTabDocumentosDet.getDopc2(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_C_TAB_DOCUMENTO_DET_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
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
