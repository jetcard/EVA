/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.serviciocorreo;


import java.util.List;
import pop.comun.dominio.MaeCorreo;
import pop.webcobranzas.correo.IEnvioCorreo;
import pop.webcobranzas.correo.IRecepcionCorreo;
import pop.webcobranzas.ifacecorreo.ICorreoRecibirServ;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class CorreoRecibirServ implements ICorreoRecibirServ{
    
    IRecepcionCorreo iRecepcionCorreoEJB;

    @Override
    public int cantidadCorreos(String file) throws Exception {
        iRecepcionCorreoEJB = (IRecepcionCorreo) Utilidades.getEJBRemoteCorreo("SessionCorreoRecibir", IRecepcionCorreo.class.getName());
        return iRecepcionCorreoEJB.cantidadCorreos(file);
    }

    @Override
    public List<MaeCorreo> recibir(String file) throws Exception {
        iRecepcionCorreoEJB = (IRecepcionCorreo) Utilidades.getEJBRemoteCorreo("SessionCorreoRecibir", IRecepcionCorreo.class.getName());
        return iRecepcionCorreoEJB.recibir(file);
    }

    
    
}
