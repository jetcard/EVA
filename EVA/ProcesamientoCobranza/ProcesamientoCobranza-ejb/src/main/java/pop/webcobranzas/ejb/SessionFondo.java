/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeAsesor;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegFondo;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbFondo")
public class SessionFondo implements INegFondo {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public List<MaeFondo> listarFondos(MaeFondo oMaeFondo) throws Exception {
        List<MaeFondo> oListFondos;
        
        oListFondos = ofDao.getFondo().fetchAll(oMaeFondo);
        return oListFondos;
    }

    @Override
    public List<MaeFondo> listarFondosUser(MaeAsesor oMaeAsesor) throws Exception {
        List<MaeFondo> oListFondos;
        
        oListFondos = ofDao.getFondo().fetchAllUser(oMaeAsesor);
        return oListFondos;
    }

}
