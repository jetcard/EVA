/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeAsesor;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegAsesor;


/**
 *
 * @author EC23329
 */
@Stateless(mappedName = "ejbAsesor")
//public class SessionFondo implements INegFondo {
public class SessionAsesor implements INegAsesor{
    
    FactoryDao ofDao = new FactoryDao();

    @Override
    public List<MaeAsesor> listarAsesor(MaeAsesor oMaeAsesor) throws Exception {
        List<MaeAsesor> oListAsesor;
        
        oListAsesor = ofDao.getAsesor().fetchAll(oMaeAsesor);
        return oListAsesor;
    }
    
    @Override
    public List<MaeAsesor> listarAsesorUser(MaeAsesor oMaeAsesor) throws Exception {
        List<MaeAsesor> oListAsesor;
        
        oListAsesor = ofDao.getAsesor().fetchAllUser(oMaeAsesor);
        return oListAsesor;
    }
}
