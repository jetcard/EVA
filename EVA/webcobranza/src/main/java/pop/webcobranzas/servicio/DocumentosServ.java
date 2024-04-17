/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.TabDocumentos;
import pop.webcobranzas.iface.IDocumentosServ;
import pop.webcobranzas.negocio.INegTabDocumentos;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class DocumentosServ implements IDocumentosServ, Serializable {

    INegTabDocumentos iNegDocumentosEJB;
    
    @Override
    public Integer generar(TabDocumentos oTabDocumentos) throws Exception {
        iNegDocumentosEJB = (INegTabDocumentos) Utilidades.getEJBRemote("SessionDocumentos", INegTabDocumentos.class.getName());     
        return iNegDocumentosEJB.generar(oTabDocumentos);
    }

    @Override
    public List<TabDocumentos> listarDocumentos(TabDocumentos oTabDocumentos) throws Exception {
        iNegDocumentosEJB = (INegTabDocumentos) Utilidades.getEJBRemote("SessionDocumentos", INegTabDocumentos.class.getName());     
        return iNegDocumentosEJB.listarDocumentos(oTabDocumentos);
    }

    @Override
    public String banderaGenerar(TabDocumentos oTabDocumentos) throws Exception {
        iNegDocumentosEJB = (INegTabDocumentos) Utilidades.getEJBRemote("SessionDocumentos", INegTabDocumentos.class.getName());     
        return iNegDocumentosEJB.banderaGenerar(oTabDocumentos);
    }

    @Override
    public List<TabDocumentos> listarDocumentosDet(TabDocumentos oTabDocumentos) throws Exception {
        iNegDocumentosEJB = (INegTabDocumentos) Utilidades.getEJBRemote("SessionDocumentos", INegTabDocumentos.class.getName());     
        return iNegDocumentosEJB.listarDocumentosDet(oTabDocumentos);
    }

    @Override
    public Integer generarManual(TabDocumentos oTabDocumentos) throws Exception {
        iNegDocumentosEJB = (INegTabDocumentos) Utilidades.getEJBRemote("SessionDocumentos", INegTabDocumentos.class.getName());     
        return iNegDocumentosEJB.generarManual(oTabDocumentos);
    }
    
}
