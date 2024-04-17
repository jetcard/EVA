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
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeTipoCambio;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.iface.IMaeTipoCambioDao;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeTipoCambioDao extends DBUtil implements IMaeTipoCambioDao {

    private OracleConnection cn = null;

    public MaeTipoCambioDao() {

    }
    
    public MaeTipoCambioDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeTipoCambio oMaeTipoCambio) {
        //System.out.println(" <i> insertar tipo Cambio " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_TIPO_CAMBIO.SP_INS_MAETIPCAMBIO(?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oMaeTipoCambio);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_C_MAE_TIPO_CAMBIO_ID").getParameterInt();

        } catch (SQLException e) {
            Logger.getLogger(MaeTipoCambioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(MaeTipoCambioDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> insertar tipo Cambio " + LocalDateTime.now());
        return newID;
    }

    @Override
    public void update(MaeTipoCambio oMaeTipoCambio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaeTipoCambio oMaeTipoCambio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeTipoCambio> fetchAll(MaeTipoCambio oMaeTipoCambio) {
        ArrayList<MaeTipoCambio> lstCambio = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_TIPO_CAMBIO.SP_BUS_TIPO_CAMBIO(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersBus(oMaeTipoCambio);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure            
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeTipoCambio cambio = new MaeTipoCambio();
                cambio.setcMaeTipoCambioId(resultSet.getInt("C_MAE_TIPO_CAMBIO_ID"));
                cambio.setfCambio(resultSet.getDate("F_CAMBIO"));
                cambio.setcMonedaId(resultSet.getString("C_TIPO_MONEDA"));
                cambio.setnTipoCambioCom(resultSet.getDouble("N_TIP_CAMBIO_COM"));
                cambio.setnTipoCambioVen(resultSet.getDouble("N_TIPO_CAMBIO_VEN"));
                cambio.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cambio.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                lstCambio.add(cambio);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeBancoDao.class.getName()).log(Level.SEVERE, null, e);
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


        return lstCambio;
    }

    public OracleConnection getCn() {
        return cn;
    }

    public void setCn(OracleConnection cn) {
        this.cn = cn;
    }
    
    private List<ParameterOracle> insertParameters(MaeTipoCambio oMaeTipoCambio){
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_F_CAMBIO", oMaeTipoCambio.getfCambio(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_MONEDA", oMaeTipoCambio.getcTipoMoneda(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_TIP_CAMBIO_COM", oMaeTipoCambio.getnTipoCambioCom(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_TIPO_CAMBIO_VEN", oMaeTipoCambio.getnTipoCambioVen(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaeTipoCambio.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
               
        oListParam.add(new ParameterOracle("PO_C_MAE_TIPO_CAMBIO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParametersBus(MaeTipoCambio oMaeTipoCambio){
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_FECINI", oMaeTipoCambio.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FECFIN", oMaeTipoCambio.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_CAMBIO", oMaeTipoCambio.getfCambio(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MONEDA_ID", oMaeTipoCambio.getnTipoCambioCom(), OracleTypes.CHAR, ParameterDirection.Input));
              
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
                
        return oListParam;
    }


}

