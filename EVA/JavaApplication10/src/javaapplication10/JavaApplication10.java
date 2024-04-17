/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication10;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jyoverar
 */
public class JavaApplication10 {

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        // TODO code application logic here

        Usuario oUsuario = new Usuario();

        oUsuario.setUsuarioId("YROBLES");
        oUsuario.setContrasenia("S1st3m@s2019");

        try {
            oUsuario = JavaApplication10.obtenerUsuario(oUsuario);
        } catch (Exception_Exception ex) {
            Logger.getLogger(JavaApplication10.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(oUsuario.isEstado());
        
         System.out.println(oUsuario.getPerNom());
         for (Menu arg : oUsuario.getMenu()) {
            System.out.println(arg.getMenuA());
        }

    }

    private static Usuario obtenerUsuario(javaapplication10.Usuario oUsuario) throws Exception_Exception {
        javaapplication10.SeguridadWS_Service service = new javaapplication10.SeguridadWS_Service();
        javaapplication10.SeguridadWS port = service.getSeguridadWSPort();
        return port.obtenerUsuario(oUsuario);
    }*/

}
