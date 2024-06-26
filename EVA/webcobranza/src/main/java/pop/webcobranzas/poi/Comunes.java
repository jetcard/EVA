/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.poi;

import java.math.BigInteger;
import java.util.List;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

/**
 *
 * @author PR154357
 */
public class Comunes {

    public static void parrafoDesdeCero(XWPFDocument document, String texto) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        run.setFontFamily("Arial");
        run.setFontSize(10);
        run.setText(texto);
        run.setBold(false);
        paragraph.setSpacingAfterLines(120);
    }
   
    public static void parrafoVignetasLetras(XWPFDocument document, String texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        int subIndentation = (int) (.8 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        int subIndentationHanging = (int) (0.3 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);

        XWPFRun run = subParagraph.createRun();
        run.setBold(false);
        run.setText(texto);
        run.setFontFamily("Arial");
        run.setFontSize(10);
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        subParagraph.setSpacingAfterLines(80);
    }

    public static void parrafoVignetasCorto(XWPFDocument document, String texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        int subIndentation = (int) (.8 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        int subIndentationHanging = (int) (0.175 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);

            XWPFRun run = subParagraph.createRun();
            run.setBold(false);
            run.setText(texto);
            run.setFontFamily("Arial");
            run.setFontSize(10);
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        subParagraph.setSpacingAfterLines(80);
    }

    public static void parrafoVignetas(XWPFDocument document, String[] texto) {
       
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        int subIndentation = (int) (.85 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        int subIndentationHanging = (int) (0.375 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);

        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setBold(i % 2 != 0);
            run.setText(texto[i]);
            run.setFontFamily("Arial");
            run.setFontSize(10);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        subParagraph.setSpacingAfterLines(80);
    }

    public static void porTantoBold(XWPFDocument document, String texto) {
       
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        int subIndentation = (int) (.5 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        XWPFRun run = subParagraph.createRun();
        //run.addTab();
        run.setBold(true);
        run.setFontFamily("Arial");
        run.setFontSize(10);
        run.setText(texto);
        run.setUnderline(UnderlinePatterns.SINGLE);
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }
   
    public static void porTanto(XWPFDocument document, String[] texto) {
       
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);

        int indentation = (int) (.5 * 1440);
        paragraph.setIndentationLeft(indentation);
        paragraph.setIndentFromLeft(indentation);

        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = paragraph.createRun();
           
            run.setBold(i % 2 == 0);
            run.setText(texto[i]);
            run.setFontFamily("Arial");
            run.setFontSize(10);
        }
        paragraph.setSpacingAfterLines(240);
       
       
    }
   
    public static void titulo(XWPFDocument document, String titulo) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setSpacingAfterLines(80);
        int subIndentation = (int) (0);
        paragraph.setIndentFromLeft(subIndentation);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily("Arial");
        run.setFontSize(10);
        run.setBold(true);
        run.setText(titulo);
        run.setUnderline(UnderlinePatterns.SINGLE);
    }
   
    public static void otrosidigo(XWPFDocument document, String subtitulo, String contenido) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setSpacingAfterLines(240);
        int subIndentation = (int) (0);
        paragraph.setIndentFromLeft(subIndentation);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily("Arial");
        run.setFontSize(10);
        run.setBold(true);
        run.setText(subtitulo);
        run.setUnderline(UnderlinePatterns.SINGLE);
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        XWPFRun run1 = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        run1.setFontFamily("Arial");
        run1.setFontSize(10);
        run1.setText(contenido);
        run1.setBold(false);
    }

    public static void otrosidigoBold(XWPFDocument document, String subtitulo, String[] contenido) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setSpacingAfterLines(120);
        int subIndentation = (int) (0);
        paragraph.setIndentFromLeft(subIndentation);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily("Arial");
        run.setFontSize(10);
        run.setBold(true);
        run.setText(subtitulo);
        run.setUnderline(UnderlinePatterns.SINGLE);
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        for (int i = 0; i < contenido.length; i++) {
            XWPFRun run1 = paragraph.createRun();
            run1.setFontFamily("Arial");
            run1.setFontSize(10);
            run1.setBold(false);
            run1.setBold(i % 2 != 0);
            run1.setText(contenido[i]);
        }
    }

    public static void otrosidigoBold2(XWPFDocument document, String subtitulo, String[] contenido) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        int subIndentation = (int) (0);
        paragraph.setIndentFromLeft(subIndentation);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily("Arial");
        run.setFontSize(10);
        run.setBold(true);
        run.setText(subtitulo);
        run.setUnderline(UnderlinePatterns.SINGLE);
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        for (int i = 0; i < contenido.length; i++) {
            XWPFRun run1 = paragraph.createRun();
            run1.setFontFamily("Arial");
            run1.setFontSize(10);
            run1.setBold(false);
            run1.setBold(i % 2 != 0);
            run1.setText(contenido[i]);
        }
        paragraph.setSpacingAfterLines(120);
    }
   
   
    public static void otrosidigo5(XWPFDocument document, String subtitulo, String[] contenido) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setSpacingAfterLines(240);
        int subIndentation = (int) (0);
        paragraph.setIndentFromLeft(subIndentation);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily("Arial");
        run.setFontSize(10);
        run.setBold(true);
        run.setText(subtitulo);
        run.setUnderline(UnderlinePatterns.SINGLE);
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        for (int i = 0; i < contenido.length; i++) {
            XWPFRun run1 = paragraph.createRun();
            run1.setFontFamily("Arial");
            run1.setFontSize(10);
            if(i % 2 == 0){
                run1.setBold(false);
            }else{
                run1.setBold(true);
                run1.setUnderline(UnderlinePatterns.SINGLE);
            }
            run1.setText(contenido[i]);
        }
    }

    public static void lineParagraphCapital(XWPFDocument document, String[] texto) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        paragraph.setSpacingAfterLines(120);
        int indentationFirstLineFifth = (int) (1.5 * 1440);
        paragraph.setIndentationLeft(indentationFirstLineFifth);
        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = paragraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);
            run.setBold(i % 2 != 0);
            run.setText(texto[i]);
        }
    }
    
    public static void fifthLineParagraph(XWPFDocument document, String[] texto) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        paragraph.setSpacingAfterLines(120);
        int indentationFirstLineFifth = (int) (1.5 * 1440);
        paragraph.setIndentationLeft(indentationFirstLineFifth);
        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = paragraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);
            run.setBold(i % 2 == 0);
            run.setText(texto[i]);
        }
    }
   
    public static void agregarParrafoLetra(XWPFDocument document, String... texto) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setSpacingAfterLines(120);
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        int subIndentation = (int) (0.225 * 1440);
        paragraph.setIndentationLeft(subIndentation);
        paragraph.setIndentFromLeft(subIndentation);

        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = paragraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);            
            run.setBold(i % 2 == 0);
            run.setText(texto[i]);
        }
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }
   
    public static void agregarParrafoLetraFrancesa(XWPFDocument document, String... texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(120);
        int subIndentation = (int) (.425 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);       
        int subIndentationHanging = (int) (0.1875 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);
        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);           
            run.setBold(i % 2 != 0);
            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        // Configurar numeración automática
///        subParagraph.setNumID(addNumberingToDocument(document));
    }
    
    public static void agregarParrafoLetraFrancesa2(XWPFDocument document, String... texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(55);
        int subIndentation = (int) (.425 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);       
        int subIndentationHanging = (int) (0.1875 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);
        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);           
            run.setBold(i % 2 != 0);
            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }
    
    public static void agregarParrafoLetraFrancesa3(XWPFDocument document, String... texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(144);
        int subIndentation = (int) (.425 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);       
        int subIndentationHanging = (int) (0.1875 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);
        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);
            run.setBold(i % 2 != 0);
            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }    
    
    public static void agregarParrafoLetraFrancesaSeguido2(XWPFDocument document, String... texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(400);
        int subIndentation = (int) (.425 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        int subIndentationHanging = (int) (0.1875 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);

        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);

           
            run.setBold(i % 2 != 0);

            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        // Configurar numeración automática
///        subParagraph.setNumID(addNumberingToDocument(document));
    }
    
    public static void agregarParrafoLetraFrancesaSeguido3(XWPFDocument document, String... texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(144);
        int subIndentation = (int) (.425 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        int subIndentationHanging = (int) (0.1875 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);

        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);

           
            run.setBold(i % 2 != 0);

            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        // Configurar numeración automática
///        subParagraph.setNumID(addNumberingToDocument(document));
    }    
   
    public static void agregarParrafo(XWPFDocument document, String... texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(120);
        int subIndentation = (int) (.425 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);

        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);

           
            run.setBold(i % 2 != 0);

            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }
   
    public static void agregarEspacioEnBlanco(XWPFDocument document) {
        XWPFParagraph space = document.createParagraph();
        space.createRun().setText("\n");
    }

    public static void agregarParrafoEspecial(XWPFDocument document, String texto) {
        XWPFParagraph paragraph = document.createParagraph();
        int subIndentation = (int) (1.125 * 1440);
        paragraph.setIndentFromLeft(subIndentation);
        paragraph.setIndentationRight((int) (.225 * 1440));
        paragraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily("Arial");
        run.setFontSize(10);
        run.setBold(true);
        run.setItalic(true);
        run.setText(texto);
    }
   
    public static void agregarParrafo(XWPFDocument document, String texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setSpacingAfterLines(120);

        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        int subIndentation = (int) (.225 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);

        XWPFRun subRun = subParagraph.createRun();
        subRun.setBold(false);
        subRun.setFontFamily("Arial");
        subRun.setFontSize(10);
        subRun.setText(texto);
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }

    public static void agregarParrafo2(XWPFDocument document, String texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setSpacingAfterLines(120);
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        int subIndentation = (int) (.375 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
        XWPFRun subRun = subParagraph.createRun();
        subRun.setBold(false);
        subRun.setFontFamily("Arial");
        subRun.setFontSize(10);
        subRun.setText(texto);
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }
    
    public static void agregarParrafo3(XWPFDocument document, String texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setSpacingAfterLines(55);
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        int subIndentation = (int) (.375 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
        XWPFRun subRun = subParagraph.createRun();
        subRun.setBold(false);
        subRun.setFontFamily("Arial");
        subRun.setFontSize(10);
        subRun.setText(texto);
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }    
   
    public static void subparrfosFrancesaUnderline(XWPFDocument document, String texto) {        
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        int subIndentation = (int) (.85 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
               
        int subIndentationHanging = (int) (0.375 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);

        XWPFRun subRun = subParagraph.createRun();
        subRun.setBold(true);
        subRun.setFontFamily("Arial");
        subRun.setFontSize(10);
        subRun.setText(texto);
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        subRun.setUnderline(UnderlinePatterns.SINGLE);
    }
   
    public static void agregarSubparrfosFrancesaIV(XWPFDocument document, String texto) {
       
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        int subIndentation = (int) (.85 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        int subIndentationHanging = (int) (0.375 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);
       
        XWPFRun subRun = subParagraph.createRun();
        subRun.setBold(true);
        subRun.setFontFamily("Arial");
        subRun.setFontSize(10);
        subRun.setText(texto);
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }
    
    public static void lineParagraphCapitalNuevo(XWPFDocument document, String[] texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        subParagraph.setSpacingAfterLines(120);
        int indentationFirstLineFifth = (int) (1.5 * 1440);
        subParagraph.setIndentationLeft(indentationFirstLineFifth);
        
        XWPFRun run0 = subParagraph.createRun();
        run0.setFontFamily("Arial");
        run0.setFontSize(10);
        run0.setBold(true);
        run0.setText(texto[0]);

        XWPFRun run1 = subParagraph.createRun();
        run1.setFontFamily("Arial");
        run1.setFontSize(10);
        run1.setBold(true);
        run1.setText(texto[1]);
        
        XWPFRun run2 = subParagraph.createRun();
        run2.setFontFamily("Arial");
        run2.setFontSize(10);
        run2.setBold(false);
        run2.setText(texto[2]);

        XWPFRun run3 = subParagraph.createRun();
        run3.setFontFamily("Arial");
        run3.setFontSize(10);
        run3.setBold(true);
        run3.setText(texto[3]);

        XWPFRun run4 = subParagraph.createRun();
        run4.setFontFamily("Arial");
        run4.setFontSize(10);
        run4.setBold(false);
        run4.setText(texto[4]);
        
       
        XWPFRun run5 = subParagraph.createRun();
        run5.setFontFamily("Arial");
        run5.setFontSize(10);
        run5.setBold(false);
        run5.setText(texto[5]);
        
        XWPFRun run6 = subParagraph.createRun();
        run6.setFontFamily("Arial");
        run6.setFontSize(10);
        run6.setBold(false);
        run6.setText(texto[6]);

        XWPFRun run7 = subParagraph.createRun();
        run7.setFontFamily("Arial");
        run7.setFontSize(10);
        run7.setBold(true);
        run7.setText(texto[7]);

        XWPFRun run8 = subParagraph.createRun();
        run8.setFontFamily("Arial");
        run8.setFontSize(10);
        run8.setBold(false);
        run8.setText(texto[8]);

        XWPFRun run9 = subParagraph.createRun();
        run9.setFontFamily("Arial");
        run9.setFontSize(10);
        run9.setBold(true);
        run9.setText(texto[9]);
        //run9.setItalic(true);
       
        XWPFRun run10 = subParagraph.createRun();
        run10.setFontFamily("Arial");
        run10.setFontSize(10);
        run10.setBold(false);
        run10.setText(texto[10]);

        XWPFRun run11 = subParagraph.createRun();
        run11.setFontFamily("Arial");
        run11.setFontSize(10);
        run11.setBold(false);
        run11.setText(texto[11]);

        XWPFRun run12 = subParagraph.createRun();
        run12.setFontFamily("Arial");
        run12.setFontSize(10);
        run12.setBold(false);
        run12.setText(texto[12]);

        XWPFRun run13 = subParagraph.createRun();
        run13.setFontFamily("Arial");
        run13.setFontSize(10);
        run13.setBold(true);
        run13.setText(texto[13]);

        XWPFRun run14 = subParagraph.createRun();
        run14.setFontFamily("Arial");
        run14.setFontSize(10);
        run14.setBold(false);
        run14.setText(texto[14]);
        //run14.setItalic(true);
       
        XWPFRun run15 = subParagraph.createRun();
        run15.setFontFamily("Arial");
        run15.setFontSize(10);
        run15.setBold(true);
        run15.setText(texto[15]);
        
        XWPFRun run16 = subParagraph.createRun();
        run16.setFontFamily("Arial");
        run16.setFontSize(10);
        run16.setBold(false);
        run16.setText(texto[16]);
        
        XWPFRun run17 = subParagraph.createRun();
        run17.setFontFamily("Arial");
        run17.setFontSize(10);
        run17.setBold(true);
        run17.setText(texto[17]);

        XWPFRun run18 = subParagraph.createRun();
        run18.setFontFamily("Arial");
        run18.setFontSize(10);
        run18.setBold(false);
        run18.setText(texto[18]);

        XWPFRun run19 = subParagraph.createRun();
        run19.setFontFamily("Arial");
        run19.setFontSize(10);
        run19.setBold(true);
        run19.setText(texto[19]);
        //run19.setItalic(true);
       
        XWPFRun run20 = subParagraph.createRun();
        run20.setFontFamily("Arial");
        run20.setFontSize(10);
        run20.setBold(false);
        run20.setText(texto[20]);

        XWPFRun run21 = subParagraph.createRun();
        run21.setFontFamily("Arial");
        run21.setFontSize(10);
        run21.setBold(false);
        run21.setText(texto[21]);
        run21.setUnderline(UnderlinePatterns.SINGLE);

        XWPFRun run22 = subParagraph.createRun();
        run22.setFontFamily("Arial");
        run22.setFontSize(10);
        run22.setBold(false);
        run22.setText(texto[22]);

        XWPFRun run23 = subParagraph.createRun();
        run23.setFontFamily("Arial");
        run23.setFontSize(10);
        run23.setBold(false);
        run23.setText(texto[23]);
        run23.setUnderline(UnderlinePatterns.SINGLE);

        XWPFRun run24 = subParagraph.createRun();
        run24.setFontFamily("Arial");
        run24.setFontSize(10);
        run24.setBold(false);
        run24.setText(texto[24]);
        run24.setItalic(false);
       
        XWPFRun run25 = subParagraph.createRun();
        run25.setFontFamily("Arial");
        run25.setFontSize(10);
        run25.setBold(false);
        run25.setText(texto[25]);
        run25.setUnderline(UnderlinePatterns.SINGLE);
        
        XWPFRun run26 = subParagraph.createRun();
        run26.setFontFamily("Arial");
        run26.setFontSize(10);
        run26.setBold(false);
        run26.setText(texto[26]);         
    }

    public static void agregarSubparrafoCombinado(XWPFDocument document, String[] texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(120);
        int subIndentation = (int) (.425 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
               
        int subIndentationHanging = (int) (0.1875 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);

        XWPFRun run0 = subParagraph.createRun();
        run0.setFontFamily("Arial");
        run0.setFontSize(10);
        run0.setBold(false);
        run0.setText(texto[0]);

        XWPFRun run1 = subParagraph.createRun();
        run1.setFontFamily("Arial");
        run1.setFontSize(10);
        run1.setBold(false);
        run1.setText(texto[1]);
        run1.setUnderline(UnderlinePatterns.SINGLE);

        XWPFRun run2 = subParagraph.createRun();
        run2.setFontFamily("Arial");
        run2.setFontSize(10);
        run2.setBold(false);
        run2.setText(texto[2]);

        XWPFRun run3 = subParagraph.createRun();
        run3.setFontFamily("Arial");
        run3.setFontSize(10);
        run3.setBold(true);
        run3.setText(texto[3]);

        XWPFRun run4 = subParagraph.createRun();
        run4.setFontFamily("Arial");
        run4.setFontSize(10);
        run4.setBold(false);
        run4.setText(texto[4]);
        run4.setItalic(true);
       
        XWPFRun run5 = subParagraph.createRun();
        run5.setFontFamily("Arial");
        run5.setFontSize(10);
        run5.setBold(false);
        run5.setText(texto[5]);

        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }

    public static void agregarSubparrafos(XWPFDocument document, String[] texto) {
       
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(240); // 1.5 líneas en unidades TWIP
        int subIndentation = (int) (.85 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);
            run.setBold(i % 2 != 0);
            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }
    
    public static void agregarSubparrafosSeguido(XWPFDocument document, String[] texto) {
       
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(120); // 1.5 líneas en unidades TWIP
        int subIndentation = (int) (.85 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);
            run.setBold(i % 2 != 0);
            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }
    
        public static void agregarSubparrafosSeguido2(XWPFDocument document, String[] texto) {
       
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(400); // 1.5 líneas en unidades TWIP
        int subIndentation = (int) (.85 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);
            run.setBold(i % 2 != 0);
            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }
        
    public static void agregarSubparrafosSeguido3(XWPFDocument document, String[] texto) {       
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(144);
        int subIndentation = (int) (.85 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);
            run.setBold(i % 2 != 0);
            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
    }        
        
    /*public static void fdgdgdgd(XWPFDocument document, String... texto) {
        XWPFParagraph subParagraph = document.createParagraph();
        subParagraph.setAlignment(ParagraphAlignment.LEFT);
        subParagraph.setSpacingAfterLines(120);
        int subIndentation = (int) (.425 * 1440);
        subParagraph.setIndentationLeft(subIndentation);
        subParagraph.setIndentFromLeft(subIndentation);
       
        int subIndentationHanging = (int) (0.1875 * 1440);
        subParagraph.setIndentationHanging(subIndentationHanging);

        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = subParagraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);

           
            run.setBold(i % 2 != 0);

            run.setText(texto[i]);
        }
        subParagraph.setAlignment(ParagraphAlignment.MEDIUM_KASHIDA);
        // Configurar numeración automática
///        subParagraph.setNumID(addNumberingToDocument(document));
    }  */  
    
   
    private static BigInteger addNumberingToDocument3(XWPFDocument document) {
        XWPFNumbering numbering = document.getNumbering();
        if (numbering == null) {
            // Si no existe una instancia de XWPFNumbering, la creamos y la asignamos al documento
            numbering = document.createNumbering();
        }

        CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
        cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));

        CTLvl cTLvl = cTAbstractNum.addNewLvl();
        cTLvl.addNewNumFmt().setVal(STNumberFormat.DECIMAL);
        cTLvl.addNewLvlText().setVal("%1.");
        cTLvl.addNewStart().setVal(BigInteger.valueOf(1));

        XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
        BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);

        return abstractNumID;
    }

    private static BigInteger addNumberingToDocument(XWPFDocument document) {
        XWPFNumbering numbering = document.getNumbering();
        if (numbering == null) {
            numbering = document.createNumbering();
        }
        CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
        cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));

        CTLvl cTLvl = cTAbstractNum.addNewLvl();
        cTLvl.addNewNumFmt().setVal(STNumberFormat.DECIMAL);
        cTLvl.addNewLvlText().setVal("%1.");
        cTLvl.addNewStart().setVal(BigInteger.valueOf(1));

        XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
        BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);

        return abstractNumID;
    }

    public static void anexos(XWPFDocument document, String[] texto) {
        XWPFParagraph paragraph = document.createParagraph();
        BigInteger abstractNumID = addNumberingToDocument(document);

        paragraph.setNumID(abstractNumID);

        paragraph.setAlignment(ParagraphAlignment.LEFT);
        paragraph.setSpacingAfterLines(10);
        XWPFRun tabRun = paragraph.createRun();
        tabRun.addTab();

        int indentationAnexos = (int) (0.0 * 1440);
        paragraph.setIndentationLeft(indentationAnexos);

        for (int i = 0; i < texto.length; i++) {
            XWPFRun run = paragraph.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);
            run.setBold(i % 2 != 0);
            run.setText(texto[i]);
        }
    }

    public static void lugarYFecha(XWPFDocument document, String texto){
        XWPFParagraph fechaParagraph = document.createParagraph();
        fechaParagraph.setAlignment(ParagraphAlignment.RIGHT);
        fechaParagraph.setSpacingAfterLines(33);
       
        XWPFRun fechaRun = fechaParagraph.createRun();
        fechaRun.addBreak();
        fechaRun.setFontFamily("Arial");
        fechaRun.setFontSize(10);
        fechaRun.setText(texto);
    }
   
    public static void agregarFirma1(XWPFTable table, String nombre, String cargo, String registro) {        
        XWPFParagraph paragraph = table.getRow(0).getCell(0).getParagraphs().get(0);
        XWPFTableCell cell = table.getRow(0).getCell(0);
        cell = table.getRow(0).getCell(0);
        XWPFRun run = paragraph.createRun();
        run.addBreak();run.addBreak();run.addBreak();run.addBreak();run.addBreak();run.addBreak();
        run.setBold(true);
        run.setFontFamily("Calibri");
        run.setFontSize(11);
        run.setText("____________________________");
        run.addBreak();
        run.setText(nombre);
        run.addBreak();
        run.setText(cargo);
        run.addBreak();
        run.setText(registro);
        run.addBreak();run.addBreak();run.addBreak();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.createRun().addBreak();
    }
   
    public static void agregarFirma2(XWPFTable table, String nombre, String cargo, String registro) {
        XWPFParagraph paragraph = table.getRow(0).getCell(1).getParagraphs().get(0);
        XWPFTableCell cell = table.getRow(0).getCell(0);
        cell = table.getRow(0).getCell(1);
        XWPFRun run = paragraph.createRun();
        run.addBreak();run.addBreak();run.addBreak();run.addBreak();run.addBreak();run.addBreak();
        run.setBold(true);
        run.setFontFamily("Calibri");
        run.setFontSize(11);
        run.setText("____________________________");
        run.addBreak();
        run.setText(nombre);
        run.addBreak();
        run.setText(cargo);
        run.addBreak();
        run.setText(registro);
        run.addBreak();run.addBreak();run.addBreak();
        //paragraph.setSpacingAfterLines(120);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.createRun().addBreak();
    }
   
    public static void agregarFirmaCentro(XWPFTable table, String nombre, String cargo, String registro){
        XWPFTableCell cell = table.getRow(0).getCell(0);
        cell = table.getRow(1).getCell(0);      
        XWPFParagraph paragraph = table.getRow(1).getCell(0).getParagraphs().get(0);
        cell = table.getRow(0).getCell(1);
        XWPFRun run = paragraph.createRun();
        run.addBreak();run.addBreak();run.addBreak();run.addBreak();run.addBreak();run.addBreak();
        run.setBold(true);
        run.setFontFamily("Calibri");
        run.setFontSize(11);
        run.setText("____________________________");
        run.addBreak();
        run.setText(nombre);
        run.addBreak();
        run.setText(cargo);
        run.addBreak();
        run.setText(registro);
        run.addBreak();run.addBreak();run.addBreak();run.addBreak();run.addBreak();
        // Espaciado
        paragraph.setSpacingBefore(10);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.createRun().addBreak();
    }
   
    public static void configurarInterlineado(XWPFDocument document) {
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            CTPPr ppr = paragraph.getCTP().getPPr();
            if (ppr == null) {
                ppr = paragraph.getCTP().addNewPPr();
            }  
            CTSpacing spacing = ppr.isSetSpacing() ? ppr.getSpacing() : ppr.addNewSpacing();
            spacing.setLineRule(STLineSpacingRule.AUTO);
            spacing.setLine(BigInteger.valueOf(360));
        }
    }
   
   public void hideTableInnerBorders(XWPFTable table){
        List<XWPFTableRow> rows = table.getRows();
        for( int rowIndex = 4; rowIndex < rows.size(); rowIndex++){ //  rowIndex:{4,5} - last two lines
            List<XWPFTableCell> cells = rows.get(rowIndex).getTableCells();
            for(int cellIndex = 1; cellIndex < cells.size(); cellIndex++){ //  cellIndex:{1,2,3,4}
                XWPFTableCell cell = cells.get(cellIndex);
                CTTcBorders tblBorders = cell.getCTTc().getTcPr().addNewTcBorders();
                // remove the right border for the indexes 1 2 3
                if( cellIndex == 1 || cellIndex == 2 || cellIndex == 3 ){
                    tblBorders.addNewRight().setVal(STBorder.NIL);
                }
                // remove the left border for the indexes 2,3,4
                if ( cellIndex == 2 || cellIndex == 3 || cellIndex == 4 ) {
                    tblBorders.addNewLeft().setVal(STBorder.NIL);
                }
            }
        }
    }
   
    public void setTableAlign(XWPFTable table, STJc.Enum align) {
        CTTbl cttblp = table.getCTTbl();
        CTTblPr cttblpr = (cttblp.getTblPr() == null ? cttblp.addNewTblPr() : cttblp.getTblPr());
        CTJc ctjc = (cttblpr.isSetJc() ? cttblpr.getJc() : cttblpr.addNewJc());
        ctjc.setVal(align);
    }
   
    public void setColumnWidth(XWPFTable table, int row, int col, int width) {
        CTTblWidth tblWidth = CTTblWidth.Factory.newInstance();
        tblWidth.setW(BigInteger.valueOf(width));
        tblWidth.setType(STTblWidth.DXA);
        CTTcPr tcPr = table.getRow(row).getCell(col).getCTTc().getTcPr();
        if (tcPr != null) {
            tcPr.setTcW(tblWidth);
        } else {
            tcPr = CTTcPr.Factory.newInstance();
            tcPr.setTcW(tblWidth);
            table.getRow(row).getCell(col).getCTTc().setTcPr(tcPr);
        }
    }
   
    public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if ( cellIndex == fromCell ) {
            cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
            cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
     }    
   
    public void tableSetBorders(
        XWPFTable table,
        STBorder.Enum borderType,
        int size,
        int space,
        String hexColor) {
        table.getCTTbl().getTblPr().getTblBorders().getBottom().setColor(hexColor);
        table.getCTTbl().getTblPr().getTblBorders().getTop().setColor(hexColor);
        table.getCTTbl().getTblPr().getTblBorders().getLeft().setColor(hexColor);
        table.getCTTbl().getTblPr().getTblBorders().getRight().setColor(hexColor);
        table.getCTTbl().getTblPr().getTblBorders().getInsideH().setColor(hexColor);
        table.getCTTbl().getTblPr().getTblBorders().getInsideV().setColor(hexColor);

        table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(size));
        table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(size));
        table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(size));
        table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(size));
        table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(size));
        table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(size));

        table.getCTTbl().getTblPr().getTblBorders().getBottom().setVal(borderType);
        table.getCTTbl().getTblPr().getTblBorders().getTop().setVal(borderType);
        table.getCTTbl().getTblPr().getTblBorders().getLeft().setVal(borderType);
        table.getCTTbl().getTblPr().getTblBorders().getRight().setVal(borderType);
        table.getCTTbl().getTblPr().getTblBorders().getInsideH().setVal(borderType);
        table.getCTTbl().getTblPr().getTblBorders().getInsideV().setVal(borderType);
    }
    
}
