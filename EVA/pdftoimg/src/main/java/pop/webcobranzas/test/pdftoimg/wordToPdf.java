/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.test.pdftoimg;


import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author Jyoverar
 */
public class wordToPdf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        long startTime = System.currentTimeMillis();

        try
        {
             //1) Load docx with POI XWPFDocument
            InputStream is = new FileInputStream(new File("C:\\target\\00015.docx"));
            XWPFDocument document = new XWPFDocument( is );

             //2) Convert POI XWPFDocument 2 PDF with iText
            File outFile = new File( "C:\\target\\DocxBigsss.pdf" );
            outFile.getParentFile().mkdirs();

            OutputStream out = new FileOutputStream( outFile );
            PdfOptions options = PdfOptions.create().fontEncoding( "windows-1252" );
            PdfConverter.getInstance().convert( document, out, options );
        }
        catch ( Throwable e )
        {
            e.printStackTrace();
        }

        System.out.println( "Generate DocxBig.pdf with " + ( System.currentTimeMillis() - startTime ) + " ms." );
    }
    
}
