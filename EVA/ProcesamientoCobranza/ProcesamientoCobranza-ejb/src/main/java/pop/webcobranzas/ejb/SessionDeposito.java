/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeBancoCuenta;
import pop.comun.dominio.MaeControl;
import pop.comun.dominio.MaeDeposito;
import pop.webcobranzas.dao.FactoryDao;
import pop.comun.dominio.MaeInversion;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.negocio.INegDeposito;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbDeposito")
public class SessionDeposito implements INegDeposito {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public List<MaeDeposito> listarDepositosUlt() throws Exception {

        List<MaeDeposito> oDepoList = null;

        MaeDeposito oMaeDeposito = new MaeDeposito();
        oDepoList = ofDao.getMaeDepositoDao().fetchDashboard(oMaeDeposito);
        return oDepoList;
    }

    @Override
    public List<MaeDeposito> listarDepositos(MaeDeposito oMaeDeposito) throws Exception {
        List<MaeDeposito> oDepoList = null;
        oDepoList = ofDao.getMaeDepositoDao().fetchAll(oMaeDeposito);
        return oDepoList;
    }

    @Override
    public List<MaeDeposito> listarDepositosMes(MaeDeposito oMaeDeposito) throws Exception {
        List<MaeDeposito> oDepoList = null;
        oDepoList = ofDao.getMaeDepositoDao().fetchAllMonth(oMaeDeposito);
        return oDepoList;
    }

    @Override
    public List<MaeDeposito> listarDepositosBank(List<MaeDeposito> oMaeDeposito) throws Exception {
        List<MaeDeposito> oDepoList = new ArrayList<>();
        List<MaeBancoCuenta> oBancoCuentaList = new ArrayList<>();
        List<MaeInversion> oInversionList = new ArrayList<>();

        //ofDao.getConexionDao().ConexionOpen();
        // validando que existan valores
        if (oMaeDeposito.size() > 0) {
            // valido nro de la cuenta
            oBancoCuentaList = ofDao.getBanco().fetchAllCuenta(oMaeDeposito.get(0).getMaeBancoCuenta());
            if (oBancoCuentaList.size() == 1) {
                // obtengo el banco la cuenta
                MaeBancoCuenta maeBancoCuenta = oBancoCuentaList.get(0);

                for (MaeDeposito maeDeposito : oMaeDeposito) {

                    // preparando para obtener dia no este cerrado
                    List<MaeControl> oControlList = new ArrayList<>();
                    MaeControl oMaeControl = new MaeControl();
                    oMaeControl.setMaeFondo(oBancoCuentaList.get(0).getMaeFondo());
                    oMaeControl.setCtipoProcesoId("0001"); // tipo de cierre de dia
                    oMaeControl.setFfecha(maeDeposito.getFBcoDeposito());
                    oMaeControl.setEestadoId("0");
                    oControlList = ofDao.getMaeDepositoDao().verifyDay(oMaeControl);

                    // agrego la cuenta
                    maeDeposito.setMaeBancoCuenta(maeBancoCuenta);
                    // validando dia cerrado
                    if (!oControlList.isEmpty()) {

                        // validar si exite el deposito (fecha y nro)
                        if (ofDao.getMaeDepositoDao().fetchBank(maeDeposito).size() > 0) {
                            maeDeposito.setBencontrado("1");
                            maeDeposito.setDmsjIndividual("Duplicado");
                        } else if (maeBancoCuenta.getMaeBanco().getDdescCorto().contains("BCP")) {
                            // si es BCP
                            // validar si es efectivo
                            System.out.println(maeDeposito.getDBcoDescripcion());
                            String tipo="";
                            if (maeDeposito.getDBcoDescripcion().length() > 8) {
                                tipo = maeDeposito.getDBcoDescripcion().substring(0, 8);
                            }else{
                                tipo = maeDeposito.getDBcoDescripcion();
                            }

                            if (tipo.toUpperCase().equals("EFECTIVO")) {
                                // creando la inversion
                                MaeInversion inversion = new MaeInversion();
                                // asigando el fondo
                                inversion.setMaeFondo(maeBancoCuenta.getMaeFondo());
                                // obteniendo el codigo asignado de la alternativa de inversion 
                                String codigo = Integer.toString(Integer.parseInt(maeDeposito.getDBcoDescripcion().substring(9, 22)));
                                // asignar el codigo
                                inversion.setCInversion(codigo);
                                // validando si existe la inversion en el fondo
                                oInversionList = ofDao.getMaeInversionDao().fetchAll(inversion);
                                if (oInversionList.size() == 1) {
                                    // validando si esta cancelado
                                    if (oInversionList.get(0).getBcancelado().equals("N")) {
                                        maeDeposito.setBencontrado("0");
                                        maeDeposito.setMaeInversion(oInversionList.get(0));
                                        maeDeposito.setDmsjIndividual("OK");
                                    } else {
                                        maeDeposito.setBencontrado("1");
                                        maeDeposito.setDmsjIndividual("Cancelado");
                                    }
                                } else {
                                    maeDeposito.setBencontrado("1");
                                    maeDeposito.setDmsjIndividual("No Encontrado");
                                }
                            } else {
                                maeDeposito.setBencontrado("1");
                                maeDeposito.setDmsjIndividual("No encontrado");
                            }
                        } else if (maeBancoCuenta.getMaeBanco().getDdescCorto().contains("BBVA")) {
                            // si es BBVA
                            MaeInversion inversion = new MaeInversion();
                            // asigando el fondo
                            inversion.setMaeFondo(maeBancoCuenta.getMaeFondo());
                            // obteniendo el codigo asignado de la alternativa de inversion 
                            String codigo = Integer.toString(Integer.parseInt(maeDeposito.getDBcoDescripcion().substring(0, 8).trim()));
                            // asignar el codigo
                            inversion.setCInversion(codigo);
                            // validando si existe la inversion en el fondo
                            oInversionList = ofDao.getMaeInversionDao().fetchAll(inversion);
                            if (oInversionList.size() == 1) {
                                // validando si esta cancelado
                                if (oInversionList.get(0).getBcancelado().equals("N")) {
                                    maeDeposito.setBencontrado("0");
                                    maeDeposito.setMaeInversion(oInversionList.get(0));
                                    maeDeposito.setDmsjIndividual("OK");
                                } else {
                                    maeDeposito.setBencontrado("1");
                                    maeDeposito.setDmsjIndividual("Cancelado");
                                }
                            } else {
                                maeDeposito.setBencontrado("1");
                                maeDeposito.setDmsjIndividual("No Encontrado");
                            }

                        }
                    } else {
                        maeDeposito.setBencontrado("1");
                        maeDeposito.setDmsjIndividual("Dia Cerrado");
                    }

                    oDepoList.add(maeDeposito);
                }
                //ofDao.getConexionDao().ConexionClose();
            }
        }
        return oDepoList;
    }

    @Override
    public List<MaeDeposito> insertar(List<MaeDeposito> oMaeDeposito) throws Exception {
        List<MaeDeposito> oDepoList = new ArrayList<>();
        Integer newID = 0;
        
        try {

            for (MaeDeposito maeDeposito : oMaeDeposito) {
                // validar si exite el deposito (fecha y nro)
                if (ofDao.getMaeDepositoDao().fetchBank(maeDeposito).size() > 0) {
                    maeDeposito.setBencontrado("1");
                    maeDeposito.setDmsjIndividual("Duplicado");
                }
                if (maeDeposito.getBencontrado().equals("0")) {
                    newID = 0;
                    newID = ofDao.getMaeDepositoDao().insert(maeDeposito);
                    if (newID > 0) {
                        maeDeposito.setBgrabado("1");
                        maeDeposito.setcMaeDepositoId(newID);
                    } else {
                        maeDeposito.setBgrabado("0");
                    }
                    oDepoList.add(maeDeposito);
                }
            }

        } catch (Exception e) {
            throw e;
        }
        return oDepoList;
    }

    @Override
    public List<MaeDeposito> listarDepositoCompromiso(MaeDeposito oMaeDeposito) throws Exception {
        List<MaeDeposito> oDepoList = null;
        oDepoList = ofDao.getMaeDepositoDao().fetchCommitment(oMaeDeposito);
        return oDepoList;
    }    
    
    @Override
    public Integer eliminarnoDepositos(MaeDeposito oNodeposito) throws Exception {
            Integer newID = 0;
            
            try {
                    newID = ofDao.getMaeDepositoDao().eliminarnoDepositos(oNodeposito);

                } 
            catch (Exception e) {
                throw e;
            } 
            return newID;
    }     

    @Override
    public Integer actualizarnoDepositos(MaeDeposito oNodeposito) throws Exception {
            Integer newID = 0;           
            
            try {
                        newID = ofDao.getMaeDepositoDao().actualizarnoDepositos(oNodeposito);

                } 
            catch (Exception e) {
                throw e;
            } 
            return newID;
    }

    @Override
    public List<MaeDeposito> listarNoDeposito(MaeDeposito oNodeposito) throws Exception {
        List<MaeDeposito> oNoDepositoList = null;        
        oNoDepositoList = ofDao.getMaeDepositoDao().fetchAllListar(oNodeposito);
        return oNoDepositoList;
    }

    @Override
    public String grabarDepositosNI(MaeDeposito oNodeposito) throws Exception {
        String newID = "0";
        newID = ofDao.getMaeDepositoDao().grabarDepositosNI(oNodeposito);
        return newID;
    }

    @Override
    public List<MaeDeposito> fetchAllResumen(MaeDeposito oNodeposito) throws Exception {
         List<MaeDeposito> oNoDepositoList = null;
        oNoDepositoList = ofDao.getMaeDepositoDao().fetchAllResumen(oNodeposito);
        return oNoDepositoList;        
    }
}
