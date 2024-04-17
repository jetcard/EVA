/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Useful;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;

/**
 *
 * @author PR154357
 */
public class Util {
    
    private File targetFile;
    private File workFile;

    private int[] lineInBytes;
    private OutputStream targetStream;
    private RandomAccessFile workRndAccFile;
    private boolean firstColWritten;  
    
    public static void main(String...args){
        int x=1;
        //while(x<=2000000) {
        while(x<=200000) {
            List<Double> lista = new ArrayList<>();
            DoubleSummaryStatistics doubleList = new DoubleSummaryStatistics();
            double avg = 0.0D;
            for (int j = 0; j < 130; j++) {
                lista.add((double) getRandomNumberUsingInts(3, 6));
                ///int[] c={1,3,5};
                ///lista.add((double)getRandomNumberWithExclusionUsingNextInt(1,5,c));
            }
            for (double d : lista) {
                doubleList.accept(d);
            }
            avg = doubleList.getAverage();
            double avgd=Math.round(avg*Math.pow(10,3))/Math.pow(10,3);
            
            try{
                //File f=new File("C:\\Users\\PR154357\\Documents\\esan\\cronbach.txt");
                
                Util csvWriter = new Util(new File("C:\\Users\\PR154357\\Documents\\esan\\cronbach.txt"), new File("d:\\csv.work.txt"), 3);

        
        
                /*if (f.createNewFile()) 
                    System.out.println("File created");
                else
                    System.out.println("File already exists");*/
                if(avgd==2.527D||avgd==3.078D||avgd==3.279D||avgd==3.333D||avgd==3.395D||avgd==3.512D||avgd==3.519D||avgd==3.535D||avgd==3.651D||avgd==3.744D||
                        avgd==3.752D||avgd==3.767D||avgd==3.783D||avgd==3.798D||avgd==3.853D||avgd==3.946D||avgd==3.984D||avgd==4.023D||
                        avgd==4.054D||avgd==4.093D||avgd==4.093D||avgd==4.124D||avgd==4.155D||avgd==4.186D||avgd==4.194D||avgd==4.24D){
                        try{
                            csvWriter.writeNextCol(lista);
                            /*
                            FileWriter fw=new FileWriter(f);
                            String s="";
                            for (double d : lista) {
                                
                                
                                //csvWriter.writeNextCol(Arrays.asList(new String[]{"second0", "second1", "second2"}));
                                //csvWriter.writeNextCol(Arrays.asList(new String[]{"third0", "third1", "third2"}));
                                fw.append(String.valueOf((int) d));
                                fw.append("\n");
                            }
                            fw.close();*/
                            System.out.printf(" m=%.3f%n", avg);
                            break;     
                         }catch(Exception e){System.out.println(e);}   

                }
            }catch(Exception e){}
            System.out.println(x+":\t "+avg+" : \t"+avgd);
            x++;
        }
    }
    static public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    static int getRandomNumberWithExclusionUsingNextInt(int min, int max, int [] exclude) {
        Random rnd = new Random();
        Arrays.sort(exclude);
        int random = min + rnd.nextInt(max - min + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }

    public Util(File targetFile, File workFile, int lines) throws Exception{
        this.targetFile = targetFile;
        this.workFile = workFile;

        workFile.createNewFile();

        this.workRndAccFile = new RandomAccessFile(workFile, "rw");

        lineInBytes = new int[lines];
        for(int i = 0; i < lines; i++)
            lineInBytes[i] = 0;

        firstColWritten = false;
    }

    public void writeNextCol(List<Double> colOfValues) throws IOException{
        // we are going to create a new target file so we have to first 
        // create a duplicated version
        copyFile(targetFile, workFile);

        this.targetStream = new BufferedOutputStream(new FileOutputStream(targetFile));

        int lineNo = 0;

        for(Double nextColValue: colOfValues){

            String nextChunk = String.valueOf(nextColValue) + ",";

            // before we add the next chunk to the current line, 
            // we must retrieve the line from the duplicated file based on its the ofset and length 
            int lineOfset = findLineOfset(lineNo);  

            workRndAccFile.seek(lineOfset);

            int bytesToRead = lineInBytes[lineNo];
            byte[] curLineBytes = new byte[bytesToRead];
            workRndAccFile.read(curLineBytes);

            // now, we write the previous version of the line fetched from the
            // duplicated file plus the new chunk plus a 'new line' character
            targetStream.write(curLineBytes);
            targetStream.write(nextChunk.getBytes());
            targetStream.write("\n".getBytes());

            // update the length of the line
            lineInBytes[lineNo] += nextChunk.getBytes().length; 

            lineNo++;
        }

        // Though I have not done it myself but obviously some code should be added here to care for the cases where 
        // less column values have been provided in this method than the total number of lines

        targetStream.flush();
        workFile.delete();

        firstColWritten = true;
    }

    // finds the byte ofset of the given line in the duplicated file
    private int findLineOfset(int lineNo) {  
        int ofset = 0;
        for(int i = 0; i < lineNo; i++)
            ofset += lineInBytes[lineNo] + 
                (firstColWritten? 1:0); // 1 byte is added for '\n' if at least one column has been written
        return ofset;
    }

    // helper method for file copy operation
    public static void copyFile( File from, File to ) throws IOException {
            FileChannel in = new FileInputStream( from ).getChannel();
            FileChannel out = new FileOutputStream( to ).getChannel();
            out.transferFrom( in, 0, in.size() );
    }

    

    
}
