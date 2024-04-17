/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.dominio;

/**
 *
 * @author Jyoverar
 */
public class Telefono {
    private int telId;
    private String telTipo;
    private String telNumero;

    public int getTelId() {
        return telId;
    }

    public void setTelId(int telId) {
        this.telId = telId;
    }

    public String getTelTipo() {
        return telTipo;
    }

    public void setTelTipo(String telTipo) {
        this.telTipo = telTipo;
    }

    public String getTelNumero() {
        return telNumero;
    }

    public void setTelNumero(String telNumero) {
        this.telNumero = telNumero;
    }
    
    
}
