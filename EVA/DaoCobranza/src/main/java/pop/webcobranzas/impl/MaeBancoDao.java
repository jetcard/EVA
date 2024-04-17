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
import pop.comun.dominio.MaeBanco;
import pop.comun.dominio.MaeBancoCuenta;
import pop.comun.dominio.MaeFondo;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeBancoDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeBancoDao extends DBUtil implements IMaeBancoDao {

    private OracleConnection cn = null;

    public MaeBancoDao() {

    }

    public MaeBancoDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeBanco oMaeBanco) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MaeBanco oMaeBanco) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaeBanco oMaeBanco) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeBanco> fetchAll(MaeBanco oMaeBanco) {
        ArrayList<MaeBanco> lstBanco = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_BANCO.SP_BUS_BANCO(?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersBus(oMaeBanco);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeBanco maeBanco = new MaeBanco();
                maeBanco.setCbancoId(resultSet.getInt("C_BANCO_ID"));
                maeBanco.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                maeBanco.setDdescCorto(resultSet.getString("D_DESC_CORTO"));
                maeBanco.setCsbs(resultSet.getInt("C_SBS"));
                lstBanco.add(maeBanco);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeBancoDao.class.getName()).log(Level.SEVERE, null, e);
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
        return lstBanco;
    }

    @Override
    public ArrayList<MaeBancoCuenta> fetchAllCuenta(MaeBancoCuenta oMaeBancoCuenta) {
        ArrayList<MaeBancoCuenta> lstBancoCuenta = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_BANCO.SP_BUS_BANCO_CUENTA(?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaeBancoCuenta);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure            
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeBanco maeBanco = new MaeBanco();
                maeBanco.setCbancoId(resultSet.getInt("C_BANCO_ID"));
                maeBanco.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                maeBanco.setDdescCorto(resultSet.getString("D_DESC_CORTO"));
                maeBanco.setCsbs(resultSet.getInt("C_SBS"));

                MaeBancoCuenta bancoCuenta = new MaeBancoCuenta();
                bancoCuenta.setCcuentaId(resultSet.getInt("C_CUENTA_ID"));
                bancoCuenta.setCtipoCuenta(resultSet.getString("C_TIPO_CUENTA_ID"));
                bancoCuenta.setCtipoMoneda(resultSet.getString("C_TIPO_MONEDA_ID"));
                bancoCuenta.setFapertura(resultSet.getDate("F_APERTURA"));
                bancoCuenta.setDnroCuenta(resultSet.getString("D_NRO_CUENTA"));
                bancoCuenta.setNsaldoContable(resultSet.getDouble("N_SALDO_CONTABLE"));
                bancoCuenta.setNsaldoDisponible(resultSet.getDouble("N_SALDO_DISPONIBLE"));

                MaeFondo maeFondo = new MaeFondo();
                maeFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                maeFondo.setDFondo(resultSet.getString("D_FONDO"));
                maeBanco.setMaeFondo(maeFondo);
                bancoCuenta.setMaeFondo(maeFondo);
                bancoCuenta.setMaeBanco(maeBanco);

                lstBancoCuenta.add(bancoCuenta);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeBancoDao.class.getName()).log(Level.SEVERE, null, e);
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
        return lstBancoCuenta;
    }

    private List<ParameterOracle> listParameters(MaeBancoCuenta oMaeBancoCuenta) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_BANCO_ID", oMaeBancoCuenta.getMaeBanco().getCbancoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DESCRIPCION", oMaeBancoCuenta.getMaeBanco().getDdescripcion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DESC_CORTO", oMaeBancoCuenta.getMaeBanco().getDdescCorto(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_SBS", oMaeBancoCuenta.getMaeBanco().getCsbs(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_CUENTA_ID", oMaeBancoCuenta.getCcuentaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_CUENTA_ID", oMaeBancoCuenta.getCtipoCuenta(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeBancoCuenta.getMaeFondo().getCFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_MONEDA_ID", oMaeBancoCuenta.getCtipoMoneda(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_NRO_CUENTA", oMaeBancoCuenta.getDnroCuenta(), OracleTypes.VARCHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

     private List<ParameterOracle> listParametersBus(MaeBanco oMaeBanco) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_BANCO_ID", oMaeBanco.getCbancoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DESCRIPCION", oMaeBanco.getDdescripcion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DESC_CORTO", oMaeBanco.getDdescCorto(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_SBS", oMaeBanco.getCsbs(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeBanco.getMaeFondo().getCFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
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
