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
public class TipoTransaccion extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cTipoTransaccionId;
    private String dTransaccion;
    private String dTransaccionCorta;
    private String cTipoTransAsocId;

    public Number getcTipoTransaccionId() {
        return cTipoTransaccionId;
    }

    public void setcTipoTransaccionId(Number cTipoTransaccionId) {
        this.cTipoTransaccionId = cTipoTransaccionId;
    }

    public String getdTransaccion() {
        return dTransaccion;
    }

    public void setdTransaccion(String dTransaccion) {
        this.dTransaccion = dTransaccion;
    }

    public String getdTransaccionCorta() {
        return dTransaccionCorta;
    }

    public void setdTransaccionCorta(String dTransaccionCorta) {
        this.dTransaccionCorta = dTransaccionCorta;
    }

    public String getcTipoTransAsocId() {
        return cTipoTransAsocId;
    }

    public void setcTipoTransAsocId(String cTipoTransAsocId) {
        this.cTipoTransAsocId = cTipoTransAsocId;
    }
    
    
    
}
