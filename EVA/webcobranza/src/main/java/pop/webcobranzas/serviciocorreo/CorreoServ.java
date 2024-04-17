/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.serviciocorreo;


import java.util.List;
import pop.comun.dominio.MaeCorreo;
import pop.webcobranzas.correo.IEnvioCorreo;
import pop.webcobranzas.ifacecorreo.ICorreoServ;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class CorreoServ implements ICorreoServ{
    
    IEnvioCorreo iEnvioCorreoEJB;

    @Override
    public List<MaeCorreo> enviar(List<MaeCorreo> oMaeCorreos) throws Exception {
        iEnvioCorreoEJB = (IEnvioCorreo) Utilidades.getEJBRemoteCorreo("SessionCorreo", IEnvioCorreo.class.getName());
        return iEnvioCorreoEJB.enviar(oMaeCorreos);
        
    }

    
    
}
