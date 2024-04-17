/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeTelefono;
import pop.webcobranzas.iface.ITelefonoServ;
import pop.webcobranzas.negocio.INegTelefono;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class TelefonoServ implements ITelefonoServ, Serializable {

    INegTelefono iNegTelefonoEJB;

    @Override
    public List<MaeTelefono> listarTelfonoInver(MaeInversion oMaeInversion) throws Exception {
        iNegTelefonoEJB = (INegTelefono) Utilidades.getEJBRemote("SessionTelefono", INegTelefono.class.getName());
        System.out.println("pasando por session negocio");
        return iNegTelefonoEJB.listarTelfonoInver(oMaeInversion);
    }

    @Override
    public Integer insertar(MaeTelefono oMaeTelefono) throws Exception {
        iNegTelefonoEJB = (INegTelefono) Utilidades.getEJBRemote("SessionTelefono", INegTelefono.class.getName());
        return iNegTelefonoEJB.insertar(oMaeTelefono);
    }

    @Override
    public Integer insertarPresta(MaeTelefono oMaeTelefono) throws SQLException,Exception {
        iNegTelefonoEJB = (INegTelefono) Utilidades.getEJBRemote("SessionTelefono", INegTelefono.class.getName());
        return iNegTelefonoEJB.insertarPresta(oMaeTelefono);
    }

    
    @Override
    public boolean actualizar(MaeTelefono oMaeTelefono) throws Exception {
        iNegTelefonoEJB = (INegTelefono) Utilidades.getEJBRemote("SessionTelefono", INegTelefono.class.getName());
        return iNegTelefonoEJB.actualizar(oMaeTelefono);
    }

    @Override
    public boolean modificar(MaeTelefono oMaeTelefono) throws Exception {
        iNegTelefonoEJB = (INegTelefono) Utilidades.getEJBRemote("SessionTelefono", INegTelefono.class.getName());
        return iNegTelefonoEJB.modificar(oMaeTelefono);
    }

    
    @Override
    public void borrar(MaeTelefono oMaeTelefono) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeTelefono> listarTelefono(MaeTelefono oMaeTelefono) throws Exception {
        iNegTelefonoEJB = (INegTelefono) Utilidades.getEJBRemote("SessionTelefono", INegTelefono.class.getName());
        return iNegTelefonoEJB.listarTelefono(oMaeTelefono);
    }

    @Override
    public List<MaeTelefono> listarTelefonoEnvioMsj(MaeInversion oMaeInversion) throws Exception {
        iNegTelefonoEJB = (INegTelefono) Utilidades.getEJBRemote("SessionTelefono", INegTelefono.class.getName());
        return iNegTelefonoEJB.listarTelefonoEnvioMsj(oMaeInversion);
    }

}
