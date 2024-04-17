/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.TabArchivo;
import pop.webcobranzas.iface.IArchivoServ;
import pop.webcobranzas.negocio.INegArchivo;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class ArchivoServ implements IArchivoServ, Serializable {
    
     INegArchivo iNegArchivoEJB;


    @Override
    public Integer insertar(TabArchivo oTabArchivo) throws Exception {
        iNegArchivoEJB = (INegArchivo) Utilidades.getEJBRemote("SessionArchivo", INegArchivo.class.getName());     
        return iNegArchivoEJB.insertar(oTabArchivo);
    }

    @Override
    public List<TabArchivo> listar(TabArchivo oTabArchivo) throws Exception {
        iNegArchivoEJB = (INegArchivo) Utilidades.getEJBRemote("SessionArchivo", INegArchivo.class.getName());     
        return iNegArchivoEJB.listar(oTabArchivo);
    }
    
}
