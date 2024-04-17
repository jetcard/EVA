/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobSeguimiento;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCobSeguimiento;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbCobSeguimiento")
public class SessionCobSeguimiento implements INegCobSeguimiento{
    
    FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insertar(CobSeguimiento oCobSeguimiento) throws Exception {
        Integer newID = 0;

        try {
            // open conection
            //ofDao.getConexionDao().ConexionOpen();
            // execute command
            newID = ofDao.getCobSeguimiento().insert(oCobSeguimiento);
        } catch (Exception e) {
            throw e;
        } 
        return newID;
    }

    @Override
    public CobSeguimiento buscar(CobSeguimiento oCobSeguimiento) throws Exception {
        CobSeguimiento cobSeguimiento;
        
        cobSeguimiento = ofDao.getCobSeguimiento().fetch(oCobSeguimiento);       
        return cobSeguimiento;
    }

   
}
