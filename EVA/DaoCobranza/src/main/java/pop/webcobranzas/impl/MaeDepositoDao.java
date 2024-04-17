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
import pop.comun.dominio.CobCompromiso;
import pop.comun.dominio.MaeAsesor;
import pop.comun.dominio.MaeBanco;
import pop.comun.dominio.MaeBancoCuenta;
import pop.comun.dominio.MaeControl;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeInversionEstado;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeTipoCambio;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeDepositoDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeDepositoDao extends DBUtil implements IMaeDepositoDao {

    private OracleConnection cn = null;

    public MaeDepositoDao() {

    }

    public MaeDepositoDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeDeposito oMaeDeposito) {
        //System.out.println(" <i> MaeDepositoDao insertar " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_DEPOSITO.SP_INS_MAEDEPOSITO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insParameters(oMaeDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_C_MAE_DEPOSITO_ID").getParameterInt();
            
            if (cmd != null) {
                cmd.close();
            }

        } catch (SQLException e) {
            Logger.getLogger(MaeDepositoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(MaeDepositoDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeDepositoDao insertar " + LocalDateTime.now());
        return newID;
    }

    @Override
    public void update(MaeDeposito oMaeDeposito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaeDeposito oMaeDeposito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeDeposito> fetchAll(MaeDeposito oMaeDeposito) {
        //System.out.println(" <i> MaeDepositoDao fetchAll " + LocalDateTime.now());
        ArrayList<MaeDeposito> lstDeposito = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_DEPOSITO.SP_BUS_MAEDEPOSITO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus(oMaeDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setMaeFondo(newFondo);
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                newPersona.setCTipoDocumento(resultSet.getString("D_DESC_CORTA"));
                //telefono
                MaeTelefono newTelefono = new MaeTelefono();
                newTelefono.setANumero(resultSet.getString("A_NUMERO"));
                List<MaeTelefono> lstTel = new ArrayList<>();
                lstTel.add(newTelefono);
                newPersona.setMaeTelefonoList(lstTel);
                //direccion
                MaeDireccion newDireccion = new MaeDireccion();
                newDireccion.setDDir1(resultSet.getString("D_DIR1"));

                List<MaeDireccion> maeDireccionList = new ArrayList<>();
                maeDireccionList.add(newDireccion);
                newPersona.setMaeDireccionList(maeDireccionList);

                newInversion.setcPersonaId(newPersona);
                //tipo cambio
                MaeTipoCambio newCambio = new MaeTipoCambio();
                newCambio.setnTipoCambioVen(resultSet.getFloat("N_TIPO_CAMBIO_VEN"));

                //deposito
                MaeDeposito newDeposito = new MaeDeposito();
                newDeposito.setcMaeDepositoId(resultSet.getInt("C_MAE_DEPOSITO_ID"));
                newDeposito.setnDeposito(resultSet.getInt("N_DEPOSITO"));
                newDeposito.setDBcoNoperacion(resultSet.getString("D_BCO_NOPERACION"));
                newDeposito.setFBcoDeposito(resultSet.getDate("F_BCO_DEPOSITO"));
                newDeposito.setIBcoDepositado(resultSet.getFloat("I_BCO_DEPOSITADO"));
                newDeposito.setiBcoDepositadoD(resultSet.getFloat("I_BCO_DEPOSITADO_D"));
                newDeposito.setMoneda(resultSet.getString("MONEDA"));
                newDeposito.setMaeInversion(newInversion);
                newDeposito.setMaeTipoCambio(newCambio);
                lstDeposito.add(newDeposito);
            }

            resultSet.close();
            if (cmd != null) {
                cmd.close();
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
        //System.out.println(" <i> MaeDepositoDao fetchAll " + LocalDateTime.now());
        return lstDeposito;
    }

    @Override
    public ArrayList<MaeDeposito> fetchDashboard(MaeDeposito oMaeDeposito) {
        ArrayList<MaeDeposito> lstDeposito = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_DEPOSITO.SP_BUS_MAEDEPOSITO_DASHBOAR(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaeDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setMaeFondo(newFondo);
                //deposito
                MaeDeposito newDeposito = new MaeDeposito();
                newDeposito.setFBcoDeposito(resultSet.getDate("F_BCO_DEPOSITO"));
                newDeposito.setIBcoDepositado(resultSet.getInt("I_BCO_DEPOSITADO"));
                newDeposito.setMoneda(resultSet.getString("MONEDA"));
                if (resultSet.getString("MONEDA").contains("SOLES")){
                    newDeposito.setSimbolo("S/.");
                }else{
                     newDeposito.setSimbolo("$.");
                }
                newDeposito.setMaeInversion(newInversion);
                lstDeposito.add(newDeposito);
            }
            
            resultSet.close();
            if (cmd != null) {
                cmd.close();
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
        return lstDeposito;
    }

    @Override
    public ArrayList<MaeDeposito> fetchAllMonth(MaeDeposito oMaeDeposito) {
        //System.out.println(" <i> MaeDepositoDao fetchAllMonth " + LocalDateTime.now());
        ArrayList<MaeDeposito> lstDeposito = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_DEPOSITO.SP_BUS_MAEDEPOSITO_MES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busMes(oMaeDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setMaeFondo(newFondo);
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                newPersona.setCTipoDocumento(resultSet.getString("D_DESC_CORTA"));
                //telefono
                MaeTelefono newTelefono = new MaeTelefono();
                newTelefono.setANumero(resultSet.getString("A_NUMERO"));
                List<MaeTelefono> lstTel = new ArrayList<>();
                lstTel.add(newTelefono);
                newPersona.setMaeTelefonoList(lstTel);
                //direccion
                MaeDireccion newDireccion = new MaeDireccion();
                newDireccion.setDDir1(resultSet.getString("D_DIR1"));

                List<MaeDireccion> maeDireccionList = new ArrayList<>();
                maeDireccionList.add(newDireccion);
                newPersona.setMaeDireccionList(maeDireccionList);

                newInversion.setcPersonaId(newPersona);
                //tipo cambio
                MaeTipoCambio newCambio = new MaeTipoCambio();
                //newCambio.setnTipoCambioCom(resultSet.getFloat("N_TIP_CAMBIO_COM"));
                newCambio.setnTipoCambioCom(1);

                //deposito
                MaeDeposito newDeposito = new MaeDeposito();
                newDeposito.setFBcoDeposito(resultSet.getDate("F_BCO_DEPOSITO"));
                newDeposito.setIBcoDepositado(resultSet.getFloat("I_BCO_DEPOSITADO"));
                newDeposito.setiBcoDepositadoD(resultSet.getFloat("I_BCO_DEPOSITADO_D"));
                newDeposito.setMaeInversion(newInversion);
                newDeposito.setMaeTipoCambio(newCambio);
                lstDeposito.add(newDeposito);
            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
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
        //System.out.println(" <f> MaeDepositoDao fetchAllMonth " + LocalDateTime.now());
        return lstDeposito;
    }

    private List<ParameterOracle> listParameters(MaeDeposito oMaeDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_bus(MaeDeposito oMaeDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeDeposito.getMaeInversion().getcMaeInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeDeposito.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", oMaeDeposito.getMaeInversion().getcInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oMaeDeposito.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oMaeDeposito.getMaeInversion().getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
                
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeDeposito.getMaeInversion().getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oMaeDeposito.getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FECINI", oMaeDeposito.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FECFIN", oMaeDeposito.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_BCO_DEPOSITADO_INI", oMaeDeposito.getnIniBusq(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_BCO_DEPOSITADO_FIN", oMaeDeposito.getnFinBusq(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MONEDA_ID", oMaeDeposito.getcMonedaId(), OracleTypes.CHAR, ParameterDirection.Input));

//        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_busMes(MaeDeposito oMaeDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeDeposito.getMaeInversion().getcMaeInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeDeposito.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", oMaeDeposito.getMaeInversion().getcInversionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oMaeDeposito.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oMaeDeposito.getMaeInversion().getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oMaeDeposito.getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FECINI", oMaeDeposito.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FECFIN", oMaeDeposito.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_BCO_DEPOSITADO_INI", oMaeDeposito.getnIniBusq(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_BCO_DEPOSITADO_FIN", oMaeDeposito.getnFinBusq(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MONEDA_ID", oMaeDeposito.getcMonedaId(), OracleTypes.CHAR, ParameterDirection.Input));

//        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    @Override
    public ArrayList<MaeDeposito> fetchBank(MaeDeposito oMaeDeposito) {
        //System.out.println(" <i> MaeDepositoDao fetchBank " + LocalDateTime.now());
        ArrayList<MaeDeposito> lstDeposito = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_DEPOSITO.SP_BUS_MAEDEPOSITO_EXI(?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bank(oMaeDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {

                //deposito
                MaeDeposito newDeposito = new MaeDeposito();
                newDeposito.setBencontrado("S");
                lstDeposito.add(newDeposito);
            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
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
        //System.out.println(" <f> MaeDepositoDao fetchBank " + LocalDateTime.now());
        return lstDeposito;
    }

    private List<ParameterOracle> listParameters_bank(MaeDeposito oMaeDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_D_NRO_CUENTA", oMaeDeposito.getMaeBancoCuenta().getDnroCuenta(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_MONEDA_ID", oMaeDeposito.getMaeBancoCuenta().getCtipoMoneda(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_CUENTA_ID", oMaeDeposito.getMaeBancoCuenta().getCtipoCuenta(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_BCO_DEPOSITO", oMaeDeposito.getFBcoDeposito(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_BCO_NOPERACION", oMaeDeposito.getDBcoNoperacion(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> insParameters(MaeDeposito oMaeDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeDeposito.getMaeInversion().getcMaeInversionId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeDeposito.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION_ID", oMaeDeposito.getMaeInversion().getcInversionId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oMaeDeposito.getMaeInversion().getcTipoInv(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_N_DEPOSITO", 0, OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_CUENTA_ID", oMaeDeposito.getMaeBancoCuenta().getCcuentaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_DOC_DEPOSITO_ID", oMaeDeposito.getcDocDepositoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_BCO_DESCRIPCION", oMaeDeposito.getDBcoDescripcion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_BCO_NOPERACION", oMaeDeposito.getDBcoNoperacion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_BCO_SUCURSAL", oMaeDeposito.getdBcoSucursal(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_BCO_DEPOSITADO", oMaeDeposito.getIBcoDepositado(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_BCO_SALDO", oMaeDeposito.getIBcoSaldo(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_BCO_DEPOSITO", oMaeDeposito.getFBcoDeposito(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_BCO_USUARIO", oMaeDeposito.getDbcoUsuario(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_BCO_UTC", oMaeDeposito.getDbcoUtc(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_BCO_REFERENCIA_B", oMaeDeposito.getDbcoReferenciaB(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_APLICADO", oMaeDeposito.getBAplicado(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_I_SALDO", oMaeDeposito.getISaldo(), OracleTypes.DOUBLE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_RE_FINANCIA", oMaeDeposito.getBReFinancia(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_AMPLIACION", oMaeDeposito.getBAmpliacion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_B_PRONTO_PAGO", oMaeDeposito.getBProntoPago(), OracleTypes.VARCHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PI_E_ESTADO", oMaeDeposito.geteEstado(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaeDeposito.getcUsuarioAdd(), OracleTypes.VARCHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_C_MAE_DEPOSITO_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
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
    public ArrayList<MaeControl> verifyDay(MaeControl oMaeControl) {
        ArrayList<MaeControl> lstControl = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_DEPOSITO.SP_BUS_DIA_PROCESO(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersVeryDay(oMaeControl);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure            
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeControl newControl = new MaeControl();
                MaeFondo newMaeFondo = new MaeFondo();
                newMaeFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newControl.setCtipoProcesoId(resultSet.getString("C_TIPO_PROCESO_ID"));
                newControl.setFfecha(resultSet.getDate("F_FECHA"));
                newControl.setEestadoId(resultSet.getString("E_ESTADO_ID"));
                newControl.setMaeFondo(newMaeFondo);
                lstControl.add(newControl);
            }

            resultSet.close();
            if (cmd != null) {
                cmd.close();
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

        return lstControl;
    }

    private List<ParameterOracle> listParametersVeryDay(MaeControl oMaeControl) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeControl.getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_PROCESO_ID", oMaeControl.getCtipoProcesoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_FECHA", oMaeControl.getFfecha(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_E_ESTADO_ID", oMaeControl.getEestadoId(), OracleTypes.CHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    @Override
    public ArrayList<MaeDeposito> fetchCommitment(MaeDeposito oMaeDeposito) {
        //System.out.println(" <i> MaeDepositoDao fetchCommitment " + LocalDateTime.now());
        ArrayList<MaeDeposito> lstDeposito = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_MAE_DEPOSITO.SP_BUS_DEPOS_COMPROMISO(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersCommitment(oMaeDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            //
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            //
            while (resultSet.next()) {
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setMaeFondo(newFondo);
                //deposito
                MaeDeposito newDeposito = new MaeDeposito();
                newDeposito.setcMaeDepositoId(resultSet.getInt("C_MAE_DEPOSITO_ID"));
                newDeposito.setcDocDepositoId(resultSet.getString("C_DOC_DEPOSITO_ID"));
                newDeposito.setDBcoNoperacion(resultSet.getString("D_BCO_NOPERACION"));
                newDeposito.setDBcoDescripcion(resultSet.getString("D_BCO_DESCRIPCION"));
                newDeposito.setFBcoDeposito(resultSet.getDate("F_BCO_DEPOSITO"));
                newDeposito.setIBcoDepositado(resultSet.getFloat("I_BCO_DEPOSITADO"));
                // banco
                MaeBanco newBanco = new MaeBanco();
                newBanco.setCbancoId(resultSet.getInt("C_BANCO_ID"));
                newBanco.setDdescCorto(resultSet.getString("D_DESC_CORTO"));
                // cuenta banco
                MaeBancoCuenta newBancoCuenta = new MaeBancoCuenta();
                newBancoCuenta.setCcuentaId(resultSet.getInt("C_CUENTA_ID"));
                newBancoCuenta.setDnroCuenta(resultSet.getString("D_NRO_CUENTA"));
                newBancoCuenta.setMaeBanco(newBanco);
                newDeposito.setMaeBancoCuenta(newBancoCuenta);
                // compromiso
                CobCompromiso newCompromiso = new CobCompromiso();
                newCompromiso.setCcodCompromisoId(resultSet.getInt("C_COB_COMPROMISO_ID"));
                newCompromiso.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newDeposito.setCobCompromiso(newCompromiso);
                // persona
                MaePersona newPersona = new MaePersona();
                newPersona.setCPersonaId(resultSet.getInt("C_PERSONA_ID"));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //asesor
                MaeAsesor newAsesor = new MaeAsesor();
                newAsesor.setCusuarioId(resultSet.getString("C_USUARIO_ID"));                
                
                // inversion estado
                List<MaeInversionEstado> maeInversionEstadoList = new ArrayList<>();
                if (resultSet.getString("CANCELADO").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("CANCELADO"));
                    newInversionEstado.setEEstadoId("0002");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                if (resultSet.getString("AMPLIACION").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("AMPLIACION"));
                    newInversionEstado.setEEstadoId("0003");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                if (resultSet.getString("REFINANCIADO").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("REFINANCIADO"));
                    newInversionEstado.setEEstadoId("0004");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                if (resultSet.getString("JUDICIAL").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("JUDICIAL"));
                    newInversionEstado.setEEstadoId("0005");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                if (resultSet.getString("COBRANZA").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("COBRANZA"));
                    newInversionEstado.setEEstadoId("0006");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                if (resultSet.getString("TRANFEND").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("TRANFEND"));
                    newInversionEstado.setEEstadoId("0007");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                if (resultSet.getString("TRANFREF").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("TRANFREF"));
                    newInversionEstado.setEEstadoId("0008");
                    maeInversionEstadoList.add(newInversionEstado);
                }
                if (resultSet.getString("TRANFAMP").equals("01")) {
                    MaeInversionEstado newInversionEstado = new MaeInversionEstado();
                    newInversionEstado.seteEstado(resultSet.getString("TRANFAMP"));
                    newInversionEstado.setEEstadoId("0009");
                    maeInversionEstadoList.add(newInversionEstado);
                }


                newInversion.setMaeInversionEstadoList(maeInversionEstadoList);
                
                //
                newInversion.setcPersonaId(newPersona);
                newInversion.setMaeAsesor(newAsesor);
                newDeposito.setMaeInversion(newInversion);
                newDeposito.setMoneda(resultSet.getString("MONEDA"));
                if(resultSet.getString("MONEDA").contains("SOLES")){
                    newDeposito.setSimbolo("S/.");
                }else{
                  newDeposito.setSimbolo("$.");
                }
                //
                lstDeposito.add(newDeposito);
            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
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
        //System.out.println(" <f> MaeDepositoDao fetchCommitment " + LocalDateTime.now());
        return lstDeposito;
    }

    private List<ParameterOracle> listParametersCommitment(MaeDeposito oMaeDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        
        oListParam.add(new ParameterOracle("PI_FINICIO", oMaeDeposito.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FFIN", oMaeDeposito.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaeDeposito.getCobCompromiso().getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oMaeDeposito.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    
    @Override
    public Integer actualizarnoDepositos(MaeDeposito noDeposito) {
          Integer  newID = 0;
        OracleCallableStatement cmd = null;
        try {
             System.out.println("noDeposito"+ noDeposito.getDBcoNoperacion()+"- "+noDeposito.getMaeInversion().getcInversionId()+ "-" + noDeposito.getDBcoDescripcion());
            // name of procedure
            String sp = "{call PKG_CARGA_AUTOMATICA.Sp_Modificar_No_Deposito(?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters             
            oLis = updParameters(noDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            newID = getOutputParameter("PO_RESPUESTA").getParameterInt();
            
            if (cmd != null) {
                cmd.close();
            }

        } catch (SQLException e) {
            Logger.getLogger(MaeDepositoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e)
        {
            Logger.getLogger(MaeDepositoDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeDepositoDao insertar " + LocalDateTime.now());
        return newID;
    }

    @Override
    public Integer eliminarnoDepositos(MaeDeposito noDeposito) {
         Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_CARGA_AUTOMATICA.Sp_eliminar_No_Deposito(?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = delParameters(noDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_RESPUESTA").getParameterInt();
            
            if (cmd != null) {
                cmd.close();
            }

        } catch (SQLException e) {
            Logger.getLogger(MaeDepositoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(MaeDepositoDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeDepositoDao insertar " + LocalDateTime.now());
        return newID;
    }

    @Override
    public ArrayList<MaeDeposito> fetchAllListar(MaeDeposito noDeposito) {
        ArrayList<MaeDeposito> lstDeposito = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        System.out.println("aa2EEE"+noDeposito.getnIniBusq());
        System.out.println("aa22EEE"+noDeposito.getnIniBusq());
        try {
            // name of procedure
            String sp = "{call PKG_CARGA_AUTOMATICA.SP_LISTA_NO_DEPOSITO(?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busNO(noDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            
            while (resultSet.next()) {
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_Fondo_Id"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_Inversion_Id"));
                newInversion.setCbanco(resultSet.getString("d_desc_corto"));
                newInversion.setMaeFondo(newFondo);
                
                MaeBanco newbanco = new MaeBanco();
                newbanco.setCbancoId(resultSet.getInt("c_banco_id"));
                newbanco.setDdescCorto(resultSet.getString("d_desc_corto"));
                
                MaeBancoCuenta newbancocuenta = new MaeBancoCuenta();
                newbancocuenta.setCcuentaId(resultSet.getInt("c_cuenta_id"));
                
                MaeDeposito newDeposito = new MaeDeposito();
                newDeposito.setFBcoDeposito(resultSet.getDate("F_BCO_DEPOSITO"));
                newDeposito.setIBcoDepositado(resultSet.getFloat("I_BCO_DEPOSITADO"));
                newDeposito.setDbcoUtc(resultSet.getString("D_Bco_Sucursal"));
                newDeposito.setDBcoNoperacion(resultSet.getString("D_Bco_Noperacion"));
                newDeposito.setMaeInversion(newInversion);
                newDeposito.setMaeBancoCuenta(newbancocuenta);
                
                newDeposito.setDBcoDescripcion(resultSet.getString("d_bco_descripcion"));
                lstDeposito.add(newDeposito);
                //noDepositonew.setMaeInversion(resultSet.getString("C_Inversion_Id"));
                
            }
            

            resultSet.close();
            if (cmd != null) {
                cmd.close();
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
        //System.out.println(" <i> MaeDepositoDao fetchAll " + LocalDateTime.now());
        return lstDeposito;
    }

    
    private List<ParameterOracle> listParameters_busNO(MaeDeposito noDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", noDeposito.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.VARCHAR, ParameterDirection.Input));        
        oListParam.add(new ParameterOracle("PI_DESCRIPCION",noDeposito.getMaeInversion().getDescripcion(), OracleTypes.VARCHAR, ParameterDirection.Input));    
        oListParam.add(new ParameterOracle("PI_DESDE",noDeposito.getnIniBusq(), OracleTypes.NUMBER, ParameterDirection.Input));    
        oListParam.add(new ParameterOracle("PI_HASTA",noDeposito.getnFinBusq(), OracleTypes.NUMBER, ParameterDirection.Input));    
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    
     private List<ParameterOracle> updParameters(MaeDeposito noDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("Pi_D_Bco_Operacion", noDeposito.getDBcoNoperacion() ,OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("Pi_C_Inversion_Id", noDeposito.getMaeInversion().getCInversion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_BCO_DESCRIPCION",noDeposito.getDBcoDescripcion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_RESPUESTA", 0 ,OracleTypes.INTEGER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
     
      private List<ParameterOracle> delParameters(MaeDeposito noDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("Pi_D_Bco_Operacion",  noDeposito.getDBcoNoperacion()  ,OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_RESPUESTA", 0,OracleTypes.INTEGER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    @Override
    public String grabarDepositosNI(MaeDeposito noDeposito) {
          String  newID = "0";
        OracleCallableStatement cmd = null;
        System.out.println(noDeposito.getcUsuarioAdd()+"sss");
        try {
            // name of procedure
            String sp = "{call PKG_CARGA_AUTOMATICA.Sp_graba_No_Deposito(?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = gbParameters(noDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            
            newID = getOutputParameter("PO_RESULTADO").getParameterString();
            
            if (cmd != null) {
                cmd.close();
            }

        } catch (SQLException e) {
            Logger.getLogger(MaeDepositoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(MaeDepositoDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeDepositoDao insertar " + LocalDateTime.now());
        return newID;
    }
    
    
     private List<ParameterOracle> gbParameters(MaeDeposito noDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("Pi_C_Usuario_Add", noDeposito.getcUsuarioAdd(),OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }
    
     
    private List<ParameterOracle> bniParameters(MaeDeposito noDeposito) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    } 

    @Override
    public ArrayList<MaeDeposito> fetchAllResumen(MaeDeposito noDeposito) {
         //System.out.println(" <i> MaeDepositoDao fetchCommitment " + LocalDateTime.now());
        ArrayList<MaeDeposito> lstDeposito = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_CARGA_AUTOMATICA.Sp_Bus_Res_Recalculo(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = bniParameters(noDeposito);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            //
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            //
            while (resultSet.next()) {
                //fondo
                 //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("NINVERSION"));
                newInversion.setcInversionId(resultSet.getString("DVALOR_BV"));
                
                MaeDeposito newDeposito = new MaeDeposito();
                newDeposito.setpDPAGO(resultSet.getString("DPAGO"));
                newDeposito.setpNCUOTA(resultSet.getInt("NCUOTA"));
                newDeposito.setnDeposito(resultSet.getInt("NDEPOSITO"));
                newDeposito.setpCAPITAL(resultSet.getDouble("CAPITAL"));
                newDeposito.setpINTERES(resultSet.getDouble("INTERES"));
                newDeposito.setpMORA(resultSet.getDouble("MORA"));
                newDeposito.setpOtros(resultSet.getDouble("OTROS"));
                newDeposito.setMaeInversion(newInversion);
                lstDeposito.add(newDeposito);
            }
            resultSet.close();
            if (cmd != null) {
                cmd.close();
            }

        } catch (Exception e) {
            Logger.getLogger(MaeDepositoDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <f> MaeDepositoDao fetchCommitment " + LocalDateTime.now());
        return lstDeposito;
        
    }
    
}
