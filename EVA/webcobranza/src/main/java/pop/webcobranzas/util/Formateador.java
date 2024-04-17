/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author PR154357
 */
public class Formateador {
    //Pone la coma decimal, sin tomar en cuenta las demás comas
    public static String insertarComa(String cadena, int posicion) {
        StringBuilder resultado = new StringBuilder(cadena);
        resultado.insert(posicion, ',');
        return resultado.toString();
    }  
        
    public static boolean validarFormatoCantidad(String cantidad) {
        String patron = "^[1-9]\\d*(,\\d{3})*(,\\d{2})?$|^0(,\\d{2})?$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cantidad);
        return matcher.matches();
    }
}
