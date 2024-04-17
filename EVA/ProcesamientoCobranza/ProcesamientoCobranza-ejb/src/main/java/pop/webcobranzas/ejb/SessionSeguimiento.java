/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobCompromiso;
import pop.comun.dominio.CobLlamadas;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobSeguimiento;
import pop.comun.dominio.CobSeguimientoDet;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaePersona;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.conn.ConexionMySql;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCobMaeSeguimiento;


/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbSeguimiento")
public class SessionSeguimiento implements INegCobMaeSeguimiento {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public CobMaeSeguimiento listarSeguimiento(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        CobMaeSeguimiento cobMaeSegui ;
        
        //roger ofDao.getConexionDaoMySql().ConexionOpen();
        //System.out.println("[u0000] " + " 1111111111111111 "+ofDao.getConexionDaoMySql() );
        cobMaeSegui = ofDao.getCobMaeSeguimiento().fetchingSingle(oCobMaeSeguimiento);
        //System.out.println("[u0000] " + " 2222222222222222 "+ofDao.getConexionDaoMySql() );
        //ofDao.getConexionDaoMySql().ConexionClose();
        //System.out.println("[u000R] " + " 3333333333333333 "+cobMaeSegui );
        return cobMaeSegui;
    }

    @Override
    public Integer insertar(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        Integer newID = 0;

        try {
            // open conection
            //ofDao.getConexionDao().ConexionOpen();
            // execute command
            newID = ofDao.getCobMaeSeguimiento().insert(oCobMaeSeguimiento);
        } catch (Exception e) {
            throw e;
        } 
        return newID;
    }

    @Override
    public CobMaeSeguimiento buscar(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        CobMaeSeguimiento cobMaeSeguimiento;
        
        cobMaeSeguimiento = ofDao.getCobMaeSeguimiento().fetch(oCobMaeSeguimiento);
        return cobMaeSeguimiento;
    }

    @Override
    public List<CobMaeSeguimiento> listarSeguimientoLlamada(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        List<CobMaeSeguimiento> cobMaeSeguimientoList = new ArrayList<>();
        List<CobMaeSeguimiento> cobMaeSeguimientoListB = new ArrayList<>();
       
        //ofDao.getConexionDaoMySql().ConexionOpen();
        cobMaeSeguimientoList = ofDao.getCobMaeSeguimiento().fetchCall(oCobMaeSeguimiento);

        CobCompromiso cc = new CobCompromiso();
        if (cobMaeSeguimientoList != null && !cobMaeSeguimientoList.isEmpty()) {
            // buscamos los compromisos
            for (CobMaeSeguimiento cobMaeSeguimiento : cobMaeSeguimientoList) {

                CobMaeSeguimiento cobMaeSeg = new CobMaeSeguimiento();
                cobMaeSeg.setCmaeSeguimientoId(cobMaeSeguimiento.getCmaeSeguimientoId());
                cobMaeSeg.setMaeInversion(cobMaeSeguimiento.getMaeInversion());
                cobMaeSeg.setEestadoId(cobMaeSeguimiento.getEestadoId());
                cobMaeSeguimientoListB.add(cobMaeSeg);

                cobMaeSeguimiento.getMaeInversion().setcPersonaId(new MaePersona());

                ArrayList<CobSeguimiento> segList = new ArrayList<>();
                cobMaeSeg.setCobSeguimientoList(segList);
                ArrayList<String> telList = ofDao.getTelefono().listPhone((int) cobMaeSeguimiento.getMaeInversion().getcMaeInversionId());
                for (CobSeguimiento cobSeguimiento : cobMaeSeguimiento.getCobSeguimientoList()) {
                    CobSeguimiento seg = new CobSeguimiento();
                    seg.setCcobSeguimientoId(cobSeguimiento.getCcobSeguimientoId());
                    seg.setFfecIni(cobSeguimiento.getFfecIni());
                    seg.setFfecFin(cobSeguimiento.getFfecFin());
                    segList.add(seg);
                    ArrayList<CobSeguimientoDet> segDet = new ArrayList<>();
                    seg.setCobSeguimientoDetList(segDet);
                    for (CobSeguimientoDet cobSeguimientoDet : cobSeguimiento.getCobSeguimientoDetList()) {

                        System.out.println("Seguimiento -====================================== > " + cobMaeSeguimiento.getCmaeSeguimientoId() + " " + cobSeguimiento.getCcobSeguimientoId());

                        if (cobMaeSeguimiento.getCmaeSeguimientoId() == 4074) {
                            int iii = 0;
                            System.out.println("Seguimiento");
                        }

                        if (cobSeguimientoDet instanceof CobLlamadas) {
                            CobLlamadas ll = (CobLlamadas) cobSeguimientoDet;
                            cc.setCnexoId(ll.getCcodLlamadaId());
                            cc.setCtipoNexoId("0001");
                            ll.setCobCompromiso(ofDao.getCobCompromiso().fetchCall(cc));
                            segDet.add(ll);

                            if (ll.getCobCompromiso() != null) {
                                if (ll.getCobCompromiso().getfUsuarioAdd() != null) {
                                    ArrayList<MaeDeposito> depoList;
                                    MaeDeposito deposito = new MaeDeposito();
                                    deposito.setcMonedaId("0001");// soles
                                    deposito.setMaeInversion(cobMaeSeguimiento.getMaeInversion());
                                    deposito.setfIniBusq(ll.getCobCompromiso().getfUsuarioAdd());
                                    deposito.setfFinBusq(ll.getCobCompromiso().getFfecha());
                                    depoList = ofDao.getMaeDepositoDao().fetchAll(deposito);
                                    ll.getCobCompromiso().setMaeDepositos(depoList);
                                }
                            }

                            if (telList != null && !telList.isEmpty()) {
                                ArrayList<CobCdr> CobCdrList = new ArrayList<>();
                                for (String tel : telList) {
                                    if (tel == null) {
                                        tel = "NONE";
                                    }
                                    CobCdrList = ofDao.getCdr().fetchAllNum(tel, ll.getfUsuarioAdd());
                                }
                                ll.setCobCdrLits(CobCdrList);
                            }
                        }
                    }
                }
            }
        }

        return cobMaeSeguimientoListB;
    }

    @Override
    public boolean borrar(String cobSeguimientoId,String codLlamadaId,String codLlamada,String usuario) throws Exception {
        boolean res=false;
        try {
            res=ofDao.getCobMaeSeguimiento().delete(cobSeguimientoId,codLlamadaId,codLlamada,usuario);
        } catch (Exception e) {
            throw e;
        } 
        return res;
    }    
}
