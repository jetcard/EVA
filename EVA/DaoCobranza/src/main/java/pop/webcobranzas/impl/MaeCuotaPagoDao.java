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
import pop.comun.dominio.MaeCuotaPago;
import pop.comun.dominio.MaeCuotaPagoDet;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeCuotaPagoDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeCuotaPagoDao extends DBUtil implements IMaeCuotaPagoDao {

    private OracleConnection cn = null;

    public MaeCuotaPagoDao() {

    }

    public MaeCuotaPagoDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeCuotaPago oMaeCuotaPago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MaeCuotaPago oMaeCuotaPago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaeCuotaPago oMaeCuotaPago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeCuotaPago> fetchAll(MaeCuotaPago oMaeCuotaPago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeCuotaPago> pendingPayment(MaeCuotaPago oMaeCuotaPago) {
        //System.out.println(" <i> MaeCuotaPago PendingPayment " + LocalDateTime.now());
        ArrayList<MaeCuotaPago> lstCuotaPago = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_PERSONA.SP_CARGAR_CUOTAS_ATRASADA(?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaeCuotaPago);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            
            String concepto = "0";
            String conceptoOld = "-1";
            MaeFondo newFondo = null;
            MaeCuotaPago newCuotaPago = null;
            ArrayList<MaeCuotaPagoDet> lstCuotaPagoDet = new ArrayList<>();
            ArrayList<MaeCuotaPagoDet> lstCuotaPagoDetB = new ArrayList<>();

            while (resultSet.next()) {
                concepto = Integer.toString(resultSet.getInt("N_SECUENCIA"));
                if (!concepto.equals(conceptoOld)) {
                    // fondo
                    newFondo = new MaeFondo();
                    newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                    // inversion
                    MaeInversion newInversion = new MaeInversion();
                    newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                    newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                    newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                    newInversion.setMaeFondo(newFondo);
                    if (resultSet.getString("C_CONCEPTO_ID").equals("0000")) {
                        // cuota pago 
                        newCuotaPago = new MaeCuotaPago();
                        newCuotaPago.setnSecuencia(resultSet.getInt("N_SECUENCIA"));
                        newCuotaPago.setDsecuencia(resultSet.getString("D_SECUENCIA"));
                        newCuotaPago.setiPendiente(resultSet.getFloat("SALDO"));
                        newCuotaPago.setfPagoCrono(resultSet.getDate("F_PAGO_CRONO"));
                        newCuotaPago.setiCuota(resultSet.getDouble("I_CUOTA"));
                        newCuotaPago.seteNewflag(resultSet.getString("CALC8515"));
                        lstCuotaPagoDetB = new ArrayList<>();
                        lstCuotaPagoDetB.clear();
                        newCuotaPago.setMaeCuotaPagoDetList(lstCuotaPagoDetB);
                        lstCuotaPagoDetB.addAll(lstCuotaPagoDet);
                        lstCuotaPago.add(newCuotaPago);
                    }
                }
                if (!resultSet.getString("C_CONCEPTO_ID").equals("0000")) {
                    // cuota pago 
                    MaeCuotaPagoDet newCuotaPagoDet = new MaeCuotaPagoDet();
                    newCuotaPagoDet.setMaeCuotaPago(newCuotaPago);
                    newCuotaPagoDet.setCconceptoId(resultSet.getString("C_CONCEPTO_ID"));
                    newCuotaPagoDet.setIpendiente(resultSet.getDouble("SALDO"));
                    newCuotaPagoDet.seteFlagNew(resultSet.getString("CALC8515") );
                    
                    lstCuotaPagoDet.add(newCuotaPagoDet);
                    lstCuotaPagoDetB.add(newCuotaPagoDet);
                }
                //
                conceptoOld = concepto;
                //
                
            }
            ////System.out.println(LocalDateTime.now());
            ////System.out.println("----------");
        } catch (Exception e) {
            Logger.getLogger(MaeCuotaPagoDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeCuotaPago PendingPayment " + LocalDateTime.now());

        return lstCuotaPago;

    }

    @Override
    public MaeCuotaPago pendingPaymentFuture(MaeCuotaPago oMaeCuotaPago) {

        //System.out.println(" <i> MaeCuotaPago PendingPaymentFuture " + LocalDateTime.now());

        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        // cuota pago 
        MaeCuotaPago newCuotaPago = new MaeCuotaPago();
        try {
            // name of procedure
            String sp = "{call PKG_COB_PERSONA.SP_SALDO_FUTURO_FECHA(?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busEstCta(oMaeCuotaPago);
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
                //newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setMaeFondo(newFondo);

                newCuotaPago.setiPendiente(resultSet.getFloat("I_SALDO"));
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDeposito.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeCuotaPago PendingPaymentFuture " + LocalDateTime.now());
        return newCuotaPago;

    }

    private List<ParameterOracle> listParameters(MaeCuotaPago OMaeCuotaPago) {
        List<ParameterOracle> oListParam = new ArrayList<>();
       
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", OMaeCuotaPago.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", OMaeCuotaPago.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", OMaeCuotaPago.getMaeInversion().getcInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", OMaeCuotaPago.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PD_FECHA", OMaeCuotaPago.getfProceso(), OracleTypes.DATE, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        //oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADOB", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_busEstCta(MaeCuotaPago oMaeCuotaPago) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeCuotaPago.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oMaeCuotaPago.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", oMaeCuotaPago.getMaeInversion().getcInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeCuotaPago.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_PROCESO", oMaeCuotaPago.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        //        
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
