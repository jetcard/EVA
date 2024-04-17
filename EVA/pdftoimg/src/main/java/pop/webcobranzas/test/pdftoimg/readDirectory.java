/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.test.pdftoimg;

/**
 *
 * @author Jyoverar
 */
public class readDirectory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ListFilesUtil listFilesUtil = new ListFilesUtil();
        //final String directoryLinuxMac = "/Users/loiane/test";
        //Windows directory example
        //final String directoryWindows = "\\\\192.168.70.7\\fondo capital emprendedor - fondo publico\\10145";
        final String directoryWindows = "\\\\192.168.70.7\\fondo capital emprendedor - fondo publico\\5369";
//        System.out.println("<i> listFiles------------------------------");
//        listFilesUtil.listFiles(directoryWindows);
//        System.out.println("<f> listFiles------------------------------");
        
        System.out.println("<i> listFilesAndFilesSubDirectories------------------------------");
        listFilesUtil.listFilesAndFilesSubDirectories(directoryWindows);
        System.out.println("<f> listFilesAndFilesSubDirectories------------------------------");
    }

}
