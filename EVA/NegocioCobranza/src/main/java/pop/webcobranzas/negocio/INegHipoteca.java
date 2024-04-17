/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.ArrayList;
import javax.ejb.Remote;
import pop.comun.dominio.MaeHipoteca;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegHipoteca {

    Integer insertar(MaeHipoteca oMaeHipoteca)  throws Exception;

    void actualizar(MaeHipoteca oMaeHipoteca)  throws Exception;

    void borrar(MaeHipoteca oMaeHipoteca)  throws Exception;

    ArrayList<MaeHipoteca> listar(MaeHipoteca oMaeHipoteca)  throws Exception;

    MaeHipoteca listarHipoteca(MaeHipoteca oMaeHipoteca)  throws Exception;

}
