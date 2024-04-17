/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.serviciomsj;

import java.util.List;
import pop.comun.dominio.MaeTelefono;
import pop.webcobranzas.ifacemsj.IMensajeTextoServ;
import pop.webcobranzas.msj.IEnvioMensajeTexto;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class MensajeTextoServ implements IMensajeTextoServ {

    IEnvioMensajeTexto iEnvioMensajeTextpEJB;

    @Override
    public List<MaeTelefono> insertar(List<MaeTelefono> oMaeTelefonos) throws Exception {
        iEnvioMensajeTextpEJB = (IEnvioMensajeTexto) Utilidades.getEJBRemoteMsj("SessionMensajeText", IEnvioMensajeTexto.class.getName());
        return iEnvioMensajeTextpEJB.insertar(oMaeTelefonos);
    }
    
}
