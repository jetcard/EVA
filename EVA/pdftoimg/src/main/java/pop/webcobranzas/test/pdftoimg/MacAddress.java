/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.test.pdftoimg;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jyoverar
 */
public class MacAddress {

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        try {
            // TODO code application logic here
            InetAddress ip = InetAddress.getLOCALHOST();
            NetworkInterface netInt =  NetworkInterface.getByInetAddress(ip);
            byte[] macAdd = netInt.getHardwareAddress();
            //
            StringBuilder macAddBuil = new StringBuilder();
            //
            for (int macAdByInd = 0; macAdByInd < macAdd.length; macAdByInd++) {
                String macAddByHex = String.format("%02X", macAdd[macAdByInd]);
                macAddBuil.append(macAddByHex);
                if(macAdByInd!=macAdd.length-1){
                    macAddBuil.append(":");
                }
            }
            
            System.out.println("-->" + macAddBuil.toString());            
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(MacAddress.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(MacAddress.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }*/
    
}
