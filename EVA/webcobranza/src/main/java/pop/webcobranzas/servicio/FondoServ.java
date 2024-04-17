/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.util.List;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeAsesor;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.negocio.INegFondo;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class FondoServ implements IFondoServ {

    INegFondo iNegFondoEJB;

    @Override
    public List<MaeFondo> listarFondos(MaeFondo oMaeFondo) throws Exception {
         iNegFondoEJB = (INegFondo) Utilidades.getEJBRemote("SessionFondo", INegFondo.class.getName());
        return iNegFondoEJB.listarFondos(oMaeFondo);
    }
//listarFondosUser
    
    @Override
    public List<MaeFondo> listarFondosUser(MaeAsesor oMaeAsesor) throws Exception {
         iNegFondoEJB = (INegFondo) Utilidades.getEJBRemote("SessionFondo", INegFondo.class.getName());
        return iNegFondoEJB.listarFondosUser(oMaeAsesor);
    }
}
