/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeHipoteca;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.TabArchivo;
import pop.comun.dominio.TabDocumentos;
import pop.comun.dominio.TabDocumentosDet;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegTabDocumentos;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbDocumento")
public class SessionDocumentos implements INegTabDocumentos {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer generar(TabDocumentos oTabDocumentos) throws Exception {
        Integer newID = 0;
        

        try {
            newID = ofDao.getDocumentos().generar(oTabDocumentos);
        } catch (Exception e) {
            throw e;
        } 

        return newID;
    }

    @Override
    public List<TabDocumentos> listarDocumentos(TabDocumentos oTabDocumentos) throws Exception {
        List<TabDocumentos> oDocList = null;

        oDocList = ofDao.getDocumentos().fetchAll(oTabDocumentos);
        return oDocList;
    }

    @Override
    public String banderaGenerar(TabDocumentos oTabDocumentos) throws Exception {
        String flag = "";
       
        try {
            
            flag = ofDao.getDocumentos().flagGenerate(oTabDocumentos);
        } catch (Exception e) {
            throw e;
        } 
        return flag;
    }

    @Override
    public List<TabDocumentos> listarDocumentosDet(TabDocumentos oTabDocumentos) throws Exception {
        List<TabDocumentos> oDocList = null;
        List<TabDocumentos> oDocReturnList = new ArrayList<>();

        
        oDocList = ofDao.getDocumentos().fetchAll(oTabDocumentos);
        // obteniendo la hipoteca
        MaeHipoteca hipoteca = new MaeHipoteca();
        if (!oDocList.isEmpty()) {
            // obtener el inmueble
            MaeInmueble inmueble = oDocList.get(0).getMaeInversion().getMaeInmueble();
            hipoteca.setMaeInmueble(inmueble);
            hipoteca = ofDao.getHipoteca().fetch(hipoteca);
        }

        for (TabDocumentos tabDocumentos : oDocList) {
            // agregando hipoteca
            tabDocumentos.getMaeInversion().getMaeInmueble().setMaeHipoteca(hipoteca);
            //
            TabDocumentosDet oTabDocumentosDet = new TabDocumentosDet();
            oTabDocumentosDet.setTabDocumentos(tabDocumentos);
            List<TabDocumentosDet> lstDocumentosDet = ofDao.getDocumentosDet().fetchAll(oTabDocumentosDet);
            List<TabDocumentosDet> lstDocumentosDetSend = new ArrayList<>();
            for (TabDocumentosDet tdet : lstDocumentosDet) {
                TabArchivo ta = new TabArchivo();
                ta.setTabDocumentosDet(tdet);
                tdet.setTabArchivos(ofDao.getArchivo().fetchAll(ta));
                if (tdet.getCtablaDetId() != null) {
                    CobTablas ct = new CobTablas();
                    ct.setCtablaId("0610");
                    ct.setCtablaDetId(tdet.getCtablaDetId());
                    List<CobTablas> lsTab = ofDao.getCobTablas().fetchAll(ct);
                    tdet.setDtablaDet(lsTab.get(0).getDdescripcion());
                }
                lstDocumentosDetSend.add(tdet);
            }
            tabDocumentos.setTabDocumentosDets(lstDocumentosDetSend);
            oDocReturnList.add(tabDocumentos);
        }
        return oDocReturnList;
    }

    @Override
    public Integer generarManual(TabDocumentos oTabDocumentos) throws Exception {
        Integer newID = 0;
        try {
            newID = ofDao.getDocumentos().generateManual(oTabDocumentos);
        } catch (Exception e) {
            throw e;
        } 
        return newID;
    }

}
