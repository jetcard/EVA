/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.sql.SQLException;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeTelefono;

/**
 *
 * @author YR19155
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, Exception {
        SessionTelefono onew =new SessionTelefono();
        MaeTelefono oMaeTelefono =new MaeTelefono();
        MaePersona maePersonanew = new MaePersona();
        maePersonanew.setANroDocumento("09685507");
        oMaeTelefono.setMaePersona(maePersonanew);
        oMaeTelefono.setANumero("4048725");
        oMaeTelefono.setCTipoTel("0001");
        oMaeTelefono.setSEstado("0001");
        oMaeTelefono.setNPrede(0);
        onew.insertarPresta(oMaeTelefono);
    }
    
}
