/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Useful;

/**
 *
 * @author dpisco
 */
public enum EnArchivo {
        ControllerUsuario("ControllerUsuario"), 
        ControllerRegistroPersona("ControllerRegistroPersona"), 
        ControllerAsterisk("ControllerAsterisk"), 
        DAAsterisk("DAAsterisk"), 
        
        FRONTOFFICE("FRONTOFFICE"), LEGAL("LEGAL");
    private String attr;

    EnArchivo(String attr) {
        this.attr = attr;
    }
}
