/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

/**
 *
 * @author Jyoverar
 */
public abstract class CobSeguimientoDet extends Base{
    
    private CobSeguimiento cobSeguimiento;

    public CobSeguimiento getCobSeguimiento() {
        return cobSeguimiento;
    }

    public void setCobSeguimiento(CobSeguimiento cobSeguimiento) {
        this.cobSeguimiento = cobSeguimiento;
    }
    
}
