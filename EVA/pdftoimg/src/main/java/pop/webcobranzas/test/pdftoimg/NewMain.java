/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.test.pdftoimg;

import java.text.SimpleDateFormat;

/**
 *
 * @author Jyoverar
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String letra = "Z";
        int num = letra.codePointAt(0);
        //System.out.println("num " + num);
        SimpleDateFormat ftD = new SimpleDateFormat("yyyy/MM/dd");

        //String cadena = "out-8980806767-5004-20170710-150722-1499717242.564.wav";  
        String cadena = "/var/spool/asterisk/monitor/2017/07/10/rg-600-982736831-20170710-150138-1499716870.554.wav";

        if (cadena != null) {

            if (cadena.trim().length() > 0) {
                if (cadena.indexOf("asterisk", 1) > 0) {
                    System.out.println(" cortar ruta ");
                } else {
                    System.out.println(" agregar ruta ");
                }
            }
        } else {
            System.out.println(" no archivo ");
        }

    }

}
