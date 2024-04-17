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
import pop.comun.dominio.TabArchivo;
import pop.comun.dominio.TabDocumentosDet;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegTabDocumentosDet;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbDocumentoDet")
public class SessionDocumentosDet implements INegTabDocumentosDet {

    private FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insertar(TabDocumentosDet oTabDocumentosDet) throws Exception {
        Integer newID = 0;
        
        try {
            // open conection
            //ofDao.getConexionDao().ConexionOpen();
            // execute command
            newID = ofDao.getDocumentosDet().insert(oTabDocumentosDet);
        } catch (Exception e) {
            throw e;
        }

        return newID;
    }

    @Override
    public List<TabDocumentosDet> listarDocumentosDet(TabDocumentosDet oTabDocumentosDet) throws Exception {
        List<TabDocumentosDet> lstDocumentosDet;
        
        lstDocumentosDet = ofDao.getDocumentosDet().fetchAll(oTabDocumentosDet);
        
        List<TabDocumentosDet> lstDocumentosDetSend = new ArrayList<>();
        
        for (TabDocumentosDet tdet : lstDocumentosDet) {
            TabArchivo ta = new TabArchivo();
            ta.setTabDocumentosDet(tdet);
            tdet.setTabArchivos(ofDao.getArchivo().fetchAll(ta));
            lstDocumentosDetSend.add(tdet);
        }
        return lstDocumentosDetSend;
    }

    @Override
    public TabDocumentosDet buscarSolo(TabDocumentosDet oTabDocumentosDet) throws Exception {
        TabDocumentosDet documentosDet;
        
        documentosDet = ofDao.getDocumentosDet().fetchOnly(oTabDocumentosDet);
        
        
        return documentosDet;
    }

}
