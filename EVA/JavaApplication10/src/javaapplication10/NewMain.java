/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication10;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        //Mon Jan 02 16:13:06 COT 2017
        SimpleDateFormat ftB = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);
        System.out.println(" -- >  " + ftB.format(new Date()));
        
        int ss= 0;
        String test ="";
        if (ss>10){
            test="demo de solidos";
        }else{
            test="otros";
        }
        for (int i = 0; i < 10; i++) {
            test="otros" + i;
            i++;
        }
        
        System.out.println("<i> TEXT -- >  " + ftB.format(new Date()));
        String text="";
        
        float i=0;
        while (i<999999999) {            
            text=text+"1";
        }
        
        System.out.println("<f> TEXT -- >  " + ftB.format(new Date()));
        
        String nn = "75608949";
        System.out.println(nn.substring(1, nn.length()));
        
    }
    
}
