/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.sql.SQLException;
import java.util.List;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeTelefono;

/**
 *
 * @author Jyoverar
 */
public interface ITelefonoServ {
    
    Integer insertar(MaeTelefono oMaeTelefono) throws Exception;
    Integer insertarPresta(MaeTelefono oMaeTelefono) throws SQLException,Exception;

    boolean actualizar(MaeTelefono oMaeTelefono) throws Exception;
    boolean modificar(MaeTelefono oMaeTelefono) throws Exception;

    void borrar(MaeTelefono oMaeTelefono) throws Exception;

    List<MaeTelefono> listarTelefono(MaeTelefono oMaeTelefono) throws Exception;
    
    List<MaeTelefono> listarTelfonoInver(MaeInversion oMaeInversion) throws Exception;
    
    List<MaeTelefono> listarTelefonoEnvioMsj(MaeInversion oMaeInversion) throws Exception;

}
