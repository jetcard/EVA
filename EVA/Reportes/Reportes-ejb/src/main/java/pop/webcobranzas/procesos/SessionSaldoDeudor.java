/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.procesos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import javax.ejb.Stateless;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pop.comun.dominio.MaeCronograma;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.reporte.RepSaldoDeudor;
import pop.webcobranzas.util.HeaderFooter;
import pop.webcobranzas.util.HeaderFooter1;
/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbSaldoDeudorRep")
public class SessionSaldoDeudor implements IRepSaldoDeudor {
    @Override
    public byte[] imprimirSaldoDeudor(RepSaldoDeudor oSaldoDeudor, MaeReporte oMaeReporte ) throws Exception {

        //System.out.println("pop.webcobranzas.procesos.SessionSaldoDeudor.imprimirSaldoDeudor() i");
        // para los numeros 
        Locale.setDefault(new Locale("en", "US"));
        // configurando la pagina
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
      
        Document   document;
          DecimalFormat formatterNum = new DecimalFormat("###,###,##0.00");
          DecimalFormat formatterNum1 = new DecimalFormat("##0.00");
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                
        if (oSaldoDeudor.getxFLGNew().equals("S")){
                 document = new Document(PageSize.A4 , 18   , 36, 210, 16);
            

                PdfWriter writer = PdfWriter.getInstance(document, baos);
                // obteniendo las fuentes
                String fontCalibriPath = Paths.get("C:\\pop/webcobranzas/resources/font", "calibri.ttf").toString();
                String fontCalibriPathB = Paths.get("C:\\pop/webcobranzas/resources/font", "calibrib.ttf").toString();

                BaseFont bf = BaseFont.createFont(fontCalibriPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                BaseFont bfb = BaseFont.createFont(fontCalibriPathB, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                Font font;
                Font fontB;
                BaseColor bColor = new BaseColor(28, 50, 93);
                BaseColor bColorBorde;
                // para los formatos de los numero y fechas
              
            
                // celda universal
                PdfPCell cell;
                // primera tabla de cabecera ( 3 columnas)
                PdfPTable table = new PdfPTable(5);
                table.setTotalWidth(525);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{50, 1,30,1, 40});

       
                PdfPTable tableCab = new PdfPTable(2);
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                tableCab.setWidths(new float[]{50, 20});        
                //bColor = new BaseColor(64, 224, 208);
                bColor = new BaseColor(1, 200, 203);
                cell = new PdfPCell(new Paragraph(12, "Datos del Financiamiento",font));
                cell.setBorder(0);
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                cell.setBackgroundColor(bColor);
                tableCab.addCell(cell);

                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 8, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                tableCab.addCell(cell);

                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 8, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                tableCab.addCell(cell);



                // codigo
                cell = new PdfPCell(new Paragraph(12, "Código                                      ", fontB));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(28, 50, 93);
                cell.setBackgroundColor(bColor);
                cell.setBorder(1);
                tableCab.addCell(cell);

                font = new Font(bf, 8, Font.NORMAL, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(12,   oSaldoDeudor.getMaeInversion().getCInversion().trim(), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth(new Float(0.5));
                cell.setBackgroundColor(BaseColor.WHITE);
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderColor(bColor);
                cell.setBorder(1);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCab.addCell(cell);



                cell = new PdfPCell(new Paragraph(12, "Monto    ", fontB));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(28, 50, 93);
                cell.setBackgroundColor(bColor);
                cell.setBorder(1);
                tableCab.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, formatterNum.format((Double) oSaldoDeudor.getMaeInversion().getIInversion()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderWidth(new Float(0.5));
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderColor(bColor);
                cell.setBorder(1);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCab.addCell(cell);


                cell = new PdfPCell(new Paragraph(12, "Moneda    ", fontB));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(28, 50, 93);
                cell.setBackgroundColor(bColor);
                cell.setBorder(1);
                tableCab.addCell(cell);

                cell = new PdfPCell(new Paragraph(12,oSaldoDeudor.getMaeInversion().getCmoneda(), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderWidth(new Float(0.5));
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderColor(bColor);        
                cell.setBorder(1);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCab.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Tasa de interés compensatoria efectiva anual", fontB));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                bColor = new BaseColor(28, 50, 93);
                cell.setBorder(1);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(bColor);
                tableCab.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, Double.toString((Double) oSaldoDeudor.getMaeInversion().getPTasa() * 100) + "%", font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth(new Float(0.5));
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderColor(bColor);        
                cell.setBorder(1);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                tableCab.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Tasa de interés moratoria efectiva anual", fontB));
                bColor = new BaseColor(28, 50, 93);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(1);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(bColor);
                tableCab.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, formatterNum1.format((Double) oSaldoDeudor.getMaeInversion().getPTasa2()) + "%", font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderColor(bColor);
                cell.setBorderWidth(new Float(0.5));
                cell.setBorder(1); 
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                tableCab.addCell(cell);


                cell = new PdfPCell(new Paragraph(12, "Días de pago", fontB));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderWidth(new Float(0.5));
                cell.setBorder(1); 
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(bColor);
                tableCab.addCell(cell);

                cell = new PdfPCell(new Paragraph(12,  oSaldoDeudor.getnDiasPago().toString()  , font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth(new Float(0.5));
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderColor(bColor);        
                cell.setBorder(1); 
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                tableCab.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Cuotas", fontB));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderWidth(new Float(0.5));
                cell.setBorder(1); 
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(bColor);
                tableCab.addCell(cell);

                cell = new PdfPCell(new Paragraph(12,  oSaldoDeudor.getnCuotas().toString()  , font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderColor(bColor);        
                cell.setBorder(1); 
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                tableCab.addCell(cell);


                cell = new PdfPCell(new Paragraph(12, "Estado Cronograma", fontB));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderWidth(new Float(0.5));
                cell.setBorder(1); 
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(bColor);
                tableCab.addCell(cell);

                cell = new PdfPCell(new Paragraph(12,  oSaldoDeudor.getLs_estadoCrono()  , font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth(new Float(0.5));
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderColor(bColor);        
                cell.setBorder(1);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                tableCab.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(12, "Fecha Desembolso", fontB));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderWidth(new Float(0.5));
                cell.setBorder(1); 
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(bColor);
                tableCab.addCell(cell);   
                
                cell = new PdfPCell(new Paragraph(12,  formatter.format(oSaldoDeudor.getMaeInversion().getFEmision()) , font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth(new Float(0.5));
                bColor = new BaseColor(28, 50, 93);
                cell.setBorderColor(bColor);        
                cell.setBorder(1);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                tableCab.addCell(cell);                
                
                // quitando los bordes
                PdfPCell wrongCell = new PdfPCell(tableCab);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                // adicionando la primera tabla convertida en wrong a la tabla Columna 1

                table.addCell(wrongCell);
                // adicionando la primera tabla convertida en wrong a la tabla Columna 2 en blanco
                wrongCell = new PdfPCell(new Phrase(""));
                wrongCell.setBorder(Rectangle.NO_BORDER);
                table.addCell(wrongCell);


                //

                PdfPTable tableCab1 = new PdfPTable(3);
                font = new Font(bf, 12, Font.NORMAL, BaseColor.WHITE);
                tableCab1.setWidths(new float[]{10,6,6});        


                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);

                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);

                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);


                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);


                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);


                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);

                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);


                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);


                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);

                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);


                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);


                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                tableCab1.addCell(cell);


                // codigo
                font = new Font(bf, 8, Font.NORMAL, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(12, "Tasa", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(64, 224, 208);
                cell.setBorderColor(bColor);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(1);
                tableCab1.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Diaria ", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(64, 224, 208);
                cell.setBorderColor(bColor);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(1);
                tableCab1.addCell(cell);



                cell = new PdfPCell(new Paragraph(12, "Anual ", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(64, 224, 208);
                cell.setBorderColor(bColor);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(1);
                tableCab1.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Compens.", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(64, 224, 208);
                cell.setBorderColor(bColor);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(1);
                tableCab1.addCell(cell);




                cell = new PdfPCell(new Paragraph(12, Double.toString((Double)oSaldoDeudor.getnTasaDia()) + "%" , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(64, 224, 208);
                cell.setBorderColor(bColor);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(1);
                tableCab1.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, formatterNum1.format((Double) oSaldoDeudor.getMaeInversion().getPTasa() * 100) + "%" , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(64, 224, 208);
                cell.setBorderColor(bColor);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(1);
                tableCab1.addCell(cell);



                cell = new PdfPCell(new Paragraph(12, "Moratorio.", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(64, 224, 208);
                cell.setBorderColor(bColor);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(1);
                tableCab1.addCell(cell);


                cell = new PdfPCell(new Paragraph(12, Double.toString((Double) oSaldoDeudor.getnTasa15Dia())+ "%", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(64, 224, 208);
                cell.setBorderColor(bColor);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(1);
                tableCab1.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, formatterNum1.format((Double) oSaldoDeudor.getMaeInversion().getPTasa2() )  + "%" , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(0.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bColor = new BaseColor(64, 224, 208);
                cell.setBorderColor(bColor);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(1);
                tableCab1.addCell(cell);




                // quitando los bordes
                wrongCell = new PdfPCell(tableCab1);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                // adicionando la primera tabla convertida en wrong a la tabla Columna 1

                table.addCell(wrongCell);
                // adicionando la primera tabla convertida en wrong a la tabla Columna 2 en blanco
                wrongCell = new PdfPCell(new Phrase(""));
                wrongCell.setBorder(Rectangle.NO_BORDER);
                table.addCell(wrongCell);


                //


                // creando la tabla de la columna 3 con 2 columnas 
                PdfPTable tableB = new PdfPTable(2);
                tableB.setWidths(new float[]{23, 12});
                //right.setIndentationLeft(10);

                font = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                tableB.addCell(cell);

                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                tableB.addCell(cell);

                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                tableB.addCell(cell);

                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                tableB.addCell(cell);

                 bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                tableB.addCell(cell);

                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                tableB.addCell(cell);

                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(1, 200, 203);
                cell = new PdfPCell(new Paragraph(12, "Pago al Contado",font));
                cell.setBorder(0);
                cell.setRowspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBackgroundColor(bColor);
                tableB.addCell(cell);

                font = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(12,formatterNum.format(oSaldoDeudor.getNtotDebe() + oSaldoDeudor.getIgastLegalFut() + oSaldoDeudor.getIgastAdmin() - oSaldoDeudor.getIsaldoFavor() ) , font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderColor(bColor);
                cell.setRowspan(2);
                cell.setBorder(0); 
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(bColor);
                tableB.addCell(cell);


                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 8, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                cell.setBorderWidth(new Float(1.5));
                tableB.addCell(cell);

                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 8, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorderWidth(new Float(1.5));
                cell.setBorder(0);
                tableB.addCell(cell);

                 bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 8, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                tableB.addCell(cell);

                bColor = new BaseColor(28, 50, 93);
                fontB = new Font(bf, 8, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, "                                     " , fontB));
                cell.setBorder(0);
                tableB.addCell(cell);

                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(1, 200, 203);
                cell = new PdfPCell(new Paragraph(12, "F.compromiso de pago: ",font));
                cell.setBorder(0);
                cell.setRowspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth(new Float(1.5));
                cell.setBackgroundColor(bColor);
                tableB.addCell(cell);

                bColor = new BaseColor(1, 200, 203);
                fontB = new Font(bf, 10, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(12, formatter.format(oSaldoDeudor.getFfutura()) , fontB));
                cell.setBorder(0);
                cell.setRowspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBackgroundColor(bColor);
                tableB.addCell(cell);

                fontB = new Font(bf, 8, Font.NORMAL, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(12, oSaldoDeudor.getMaeInversion().getcPersonaId().getDApePat() + " "
                        + oSaldoDeudor.getMaeInversion().getcPersonaId().getDApeMat() + " "
                        + oSaldoDeudor.getMaeInversion().getcPersonaId().getDNombres()  , fontB));
                cell.setColspan(2);
                cell.setBorder(0);
                tableB.addCell(cell);

                fontB = new Font(bf, 8, Font.NORMAL, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(12,   oSaldoDeudor.getMaeInversion().getcPersonaId().getMaeDireccionList().get(0).getADir1()
                        + "  " + oSaldoDeudor.getMaeInversion().getcPersonaId().getMaeDireccionList().get(0).getMaeUbigeo().getDDUbigeoProv().trim()
                        + " - " + oSaldoDeudor.getMaeInversion().getcPersonaId().getMaeDireccionList().get(0).getMaeUbigeo().getDDUbigeoDist().trim(), fontB));
                cell.setColspan(2);
                cell.setBorder(0);
                tableB.addCell(cell);


                wrongCell = new PdfPCell(tableB);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                table.addCell(wrongCell);



                // creando la cabecera y el pie de pagina
                HeaderFooter headerFooter = new HeaderFooter(table);
                if (oSaldoDeudor.getMaeInversion().getMaeFondo().getCFondoId().equals("0001")) {
                    //headerFooter.setNameLogo("logoemprendedor.png");
                    oMaeReporte.setNameLogo("logoemprendedor.png");
                } else if (oSaldoDeudor.getMaeInversion().getMaeFondo().getCFondoId().equals("0002")) {
                    //headerFooter.setNameLogo("logopopular.png");
                    oMaeReporte.setNameLogo("logopopular.png");
                } else if (oSaldoDeudor.getMaeInversion().getMaeFondo().getCFondoId().equals("0003")) {
                    //headerFooter.setNameLogo("logomype.png");
                    oMaeReporte.setNameLogo("logomype.png");
                } else {
                    //headerFooter.setNameLogo("logosafi.png");
                    oMaeReporte.setNameLogo("logosafi.png");
                }
                // datos del reporte
                //oMaeReporte.setNameReport("  ESTADO DE CUENTA A LA FECHA " + formatter.format(oMaeReporte.getfIniBusq()));
                 oMaeReporte.setNameReport("SALDO DEUDOR");
                //headerFooter.setNameReport("SALDO DEUDOR DEL " + formatter.format(oSaldoDeudor.getFactual()) + "  AL " + formatter.format(oSaldoDeudor.getFfutura()));
                //headerFooter.setFecha(formatter.format(oMaeReporte.getfIniBusq()));
                //headerFooter.setUserName(oMaeReporte.getcUsuarioAdd());
                headerFooter.setMaeReporte(oMaeReporte);

                // aumentando la cabecera y detalle
                writer.setPageEvent(headerFooter);
                document.open();
                //document.add(table);
                //font = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);

                document.add(new Paragraph(" ", font));
                document.add(new Paragraph(" ", font));

                PdfPTable tableDetalle1 = new PdfPTable(1);
                tableDetalle1.setWidthPercentage(97);
                tableDetalle1.setWidths(new float[]{500});
                font = new Font(bf, 8, Font.NORMAL, BaseColor.WHITE);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "RESUMEN DE SALDO", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableDetalle1.addCell(cell);

                document.add(tableDetalle1);
                document.add(new Paragraph(" ", font));

                PdfPTable tableDetalle2 = new PdfPTable(1);
                tableDetalle2.setWidthPercentage(97);
                tableDetalle2.setWidths(new float[]{500});
                font = new Font(bf, 8, Font.NORMAL, BaseColor.WHITE);
                //bColor = new BaseColor(205,214,215);
                bColor = new BaseColor(185,185,185);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "1. COMPOSICIÓN DE LA DEUDA AL " + formatter.format(oSaldoDeudor.getFfutura()), font));
                cell.setBackgroundColor(bColor);
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle2.addCell(cell);

                document.add(tableDetalle2);
                document.add(new Paragraph(" ", font));

                PdfPTable tableDetalle = new PdfPTable(3);

                tableDetalle.setWidthPercentage(97);
                tableDetalle.setWidths(new float[]{60, 2, 38});
                // tabla de cuotas vencidas
                PdfPTable tableCv = new PdfPTable(7);
                tableCv.setWidthPercentage(95);
                tableCv.setWidths(new float[]{40, 30, 30, 30, 30 ,30,40});

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(1, 200, 203);
                bColorBorde = new BaseColor(1, 200, 203);
                cell = new PdfPCell(new Paragraph(12, "a. Cargos pendientes de pago", font));
                //cell.setColspan(5);
                cell.setColspan(7);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv.addCell(cell);

                font = new Font(bf, 8, Font.NORMAL, BaseColor.BLACK);

                cell = new PdfPCell(new Paragraph(12, "Concepto", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setRowspan(2);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, "Capital", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setRowspan(2);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, "Int. Compensatorio", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setRowspan(2);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Cuota", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setRowspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "IntComp. Atrasado", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setRowspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
                tableCv.setHeaderRows(1);

                cell = new PdfPCell(new Paragraph(12, "Int Moratorio", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setRowspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
                tableCv.setHeaderRows(1);

                cell = new PdfPCell(new Paragraph(12, "Total", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setRowspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
                tableCv.setHeaderRows(1);

                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(200, 200, 200);

                String flColor = "0";
                for (MaeCronograma mc : oSaldoDeudor.getMaeCronogramaList()) {
                    if (flColor.equals("0")) {
                        bColor = new BaseColor(240, 240, 240);
                    } else {
                        bColor = new BaseColor(255, 255, 255);
                    }


                    bColorBorde = new BaseColor(64, 224, 208);

                    cell = new PdfPCell(new Paragraph(12, "Cuota N° " + mc.getnSecuencia(), font));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);

                    cell = new PdfPCell(new Paragraph(12, formatterNum.format(mc.getIcapital()), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);

                    cell = new PdfPCell(new Paragraph(12, formatterNum.format(mc.getIinteres()), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);



                    cell = new PdfPCell(new Paragraph(12, formatterNum.format((Double) mc.getIcuota()), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);


                    double dIntMoratorio = mc.getImora().doubleValue();
                    double dIntCompVencido = 00;

                    double dICA=  mc.getIca().doubleValue();
                    double dIntMoratoriov2 =00;
                      // se modifico 20200902 YROBLES  
                   // if (dIntMoratorio==0){
                     //   dIntCompVencido = dICA*0.85;
                       // dIntMoratoriov2 = dICA*0.15;
                    //}else{
                        dIntCompVencido = dICA;
                        dIntMoratoriov2 = dIntMoratorio;
                    //}

                    Number nIntCompVencido = (Number)dIntCompVencido;
                    cell = new PdfPCell(new Paragraph(12, formatterNum.format(nIntCompVencido), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);

                    Number nIntMoratoriov2 = (Number)dIntMoratoriov2;

                    cell = new PdfPCell(new Paragraph(12, formatterNum.format(nIntMoratoriov2), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);            

                    cell = new PdfPCell(new Paragraph(12, formatterNum.format(((Double) mc.getIcapital()) + ((Double) mc.getIinteres()) +  ((Double) nIntCompVencido)  +  ((Double) nIntMoratoriov2)  ), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);
                    if (flColor.equals("0")) {
                        flColor = "1";
                    } else {
                        flColor = "0";
                    }
                }

                // totales
                cell = new PdfPCell(new Paragraph(12, "Total (a)", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                //cell.setColspan(1);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIcapAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIicAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(12, "", font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIicAAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIimAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getNtotAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                // primera columna
                wrongCell = new PdfPCell(tableCv);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                tableDetalle.addCell(wrongCell);
                // en blanco -- 2 columna
                wrongCell = new PdfPCell(new Phrase(""));
                wrongCell.setBorder(Rectangle.NO_BORDER);
                tableDetalle.addCell(wrongCell);

                // cuotas por vencer
                tableCv = new PdfPTable(2);
                tableCv.setWidthPercentage(97);
                tableCv.setWidths(new float[]{70, 30});
                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(1, 200, 203);
                bColorBorde = new BaseColor(1, 200, 203);
                cell = new PdfPCell(new Paragraph(12, "b. Cargos por vencer ", font));
                cell.setColspan(2);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv.addCell(cell);
                //
                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(64, 224, 208);
                cell = new PdfPCell(new Paragraph(12, "Capital ", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIcapFut()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, "Interes compensatorio del " + formatter.format(oSaldoDeudor.getFultCuota()) + " al " + formatter.format(oSaldoDeudor.getFfutura()), font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIicFut()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Total(b)", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(((Double) oSaldoDeudor.getIcapFut()) + ((Double) oSaldoDeudor.getIicFut())), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                // ultima columna 3
                wrongCell = new PdfPCell(tableCv);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                tableDetalle.addCell(wrongCell);
                //document.add(tableDetalle); 
                //document.add(new Paragraph(" ", font));

                ////


                PdfPTable tableCv2 = new PdfPTable(2);
                tableCv2.setWidthPercentage(97);
                tableCv2.setWidths(new float[]{40, 30});

                bColorBorde = new BaseColor(64, 224, 208);
                font = new Font(bf, 10, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, " ", font));
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv2.addCell(cell);

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(1, 200, 203);
                bColorBorde = new BaseColor(1, 200, 203);
                cell = new PdfPCell(new Paragraph(12, "c. Otros cargos", font));

                cell.setColspan(2);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv2.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Concepto", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv2.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Total", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv2.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Sobrecargo bancario", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv2.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIcargCuoAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv2.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Gastos legales", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv2.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIgastLegalFut()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv2.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Gastos administrativos", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv2.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIgastAdmin()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv2.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Total (c) ", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv2.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIgastAdmin()+oSaldoDeudor.getIgastLegalFut()+oSaldoDeudor.getIcargCuoAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv2.addCell(cell);

                // primera columna
                wrongCell = new PdfPCell(tableCv2);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                tableDetalle.addCell(wrongCell);
                // en blanco -- 2 columna
                cell.setBorder(0);
                wrongCell = new PdfPCell(new Phrase(""));
                wrongCell.setBorder(Rectangle.NO_BORDER);
                tableDetalle.addCell(wrongCell);

                // cuotas por vencer

                PdfPTable tableCv3 = new PdfPTable(2);
                tableCv3.setWidthPercentage(97);
                tableCv3.setWidths(new float[]{70, 30});
                bColorBorde = new BaseColor(64, 224, 208);
                font = new Font(bf, 10, Font.NORMAL, BaseColor.WHITE);
                bColor = new BaseColor(64, 224, 208);

                font = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                cell = new PdfPCell(new Paragraph(12, " ", font));
                cell.setColspan(2);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv3.addCell(cell);

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                cell = new PdfPCell(new Paragraph(12, "d. Saldos a favor del cliente", font));
                bColor = new BaseColor(1, 200, 203);
                bColorBorde = new BaseColor(1, 200, 203);
                cell.setColspan(2);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv3.addCell(cell);

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColorBorde = new BaseColor(64, 224, 208);
                cell = new PdfPCell(new Paragraph(12, "Total (d)", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv3.addCell(cell);

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(64, 224, 208);
                bColorBorde = new BaseColor(64, 224, 208);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIsaldoFavor()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv3.addCell(cell);

                // ultima columna 3
                wrongCell = new PdfPCell(tableCv3);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                tableDetalle.addCell(wrongCell);


                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
                //  total deuda
                tableDetalle = new PdfPTable(2);
                tableDetalle.setWidthPercentage(97);
                tableDetalle.setWidths(new float[]{30, 70});

                font = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                bColor = new BaseColor(153, 0, 0);
                bColorBorde = new BaseColor(153, 0, 0);
                cell = new PdfPCell(new Paragraph(12, "Subtotal ( a+b+c ) :", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle.addCell(cell);

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(200, 200, 200);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getNtotDebe() + oSaldoDeudor.getIgastLegalFut() + oSaldoDeudor.getIgastAdmin()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableDetalle.addCell(cell);


                font = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                bColor = new BaseColor(153, 0, 0);
                bColorBorde = new BaseColor(153, 0, 0);
                cell = new PdfPCell(new Paragraph(12, "Saldos a favor (-d) :", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle.addCell(cell);

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(200, 200, 200);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIsaldoFavor()*-1), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableDetalle.addCell(cell);



                font = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                bColor = new BaseColor(153, 0, 0);
                bColorBorde = new BaseColor(153, 0, 0);
                cell = new PdfPCell(new Paragraph(12, "Total a Pagar", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle.addCell(cell);

                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(200, 200, 200);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getNtotDebe() + oSaldoDeudor.getIgastLegalFut() + oSaldoDeudor.getIgastAdmin() - oSaldoDeudor.getIsaldoFavor()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableDetalle.addCell(cell);

                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));

                PdfPTable tableDetalle3 = new PdfPTable(1);
                tableDetalle3.setWidthPercentage(97);
                tableDetalle3.setWidths(new float[]{500});
                font = new Font(bf, 8, Font.NORMAL, BaseColor.WHITE);
                //bColor = new BaseColor(205,214,215);
                bColor = new BaseColor(185,185,185);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "2. REFERENCIA DE PAGO", font));
                cell.setBackgroundColor(bColor);
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle3.addCell(cell);
                document.add(tableDetalle3);

                PdfPTable tableCvb = new PdfPTable(2);
                tableCvb.setWidthPercentage(90);
                tableCvb.setWidths(new float[]{13, 30});
                font = new Font(bf, 8, Font.NORMAL, BaseColor.BLACK);
                bColorBorde = new BaseColor(64, 224, 208);


                cell = new PdfPCell(new Paragraph(12, "REALIZAR LOS DEPOSITOS A NOMBRE DE: ", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCvb.addCell(cell);

                cell = new PdfPCell(new Paragraph(12,  oSaldoDeudor.getMaeInversionList().get(0).getcFONDO() , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCvb.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "CODIGO", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCvb.addCell(cell);
                cell = new PdfPCell(new Paragraph(12,  oSaldoDeudor.getMaeInversion().getCInversion().trim().substring(4 ) , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCvb.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "BANCO", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCvb.addCell(cell);
                cell = new PdfPCell(new Paragraph(12,  oSaldoDeudor.getMaeInversionList().get(0).getCbanco() , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCvb.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "CUENTA CORRIENTE N°", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCvb.addCell(cell);
                cell = new PdfPCell(new Paragraph(12,  oSaldoDeudor.getMaeInversionList().get(0).getCgeneraDoc() , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCvb.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "MONEDA", font));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCvb.addCell(cell);
                cell = new PdfPCell(new Paragraph(12,  oSaldoDeudor.getMaeInversionList().get(0).getCmoneda() , font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCvb.addCell(cell);
                tableDetalle3 = new PdfPTable(1);
                tableDetalle3.setWidthPercentage(97);
                tableDetalle3.setWidths(new float[]{500});
                wrongCell = new PdfPCell(tableCvb);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                tableDetalle3.addCell(wrongCell); 
                document.add(tableDetalle3);
                document.add(new Paragraph(" ", font));
        }else{      // formato antiguo
            
                 document = new Document(PageSize.A4, 36, 36, 170, 36);
                PdfWriter writer = PdfWriter.getInstance(document, baos);
                // obteniendo las fuentes
                String fontCalibriPath = Paths.get("/pop/webcobranzas/resources/font", "calibri.ttf").toString();
                String fontCalibriPathB = Paths.get("/pop/webcobranzas/resources/font", "calibrib.ttf").toString();
                System.out.println("aca esta la ruta"+fontCalibriPath);
                BaseFont bf = BaseFont.createFont(fontCalibriPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                BaseFont bfb = BaseFont.createFont(fontCalibriPathB, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                Font font;
                Font fontB;
                BaseColor bColor = new BaseColor(28, 50, 93);
                BaseColor bColorBorde;
         
                // celda universal
                PdfPCell cell;
                // primera tabla de cabecera ( 3 columnas)
                PdfPTable table = new PdfPTable(3);
                table.setTotalWidth(500);
                table.setWidths(new float[]{50, 10, 40});

                PdfPTable tableCab = new PdfPTable(1);
                // codigo
                fontB = new Font(bfb, 12);
                cell = new PdfPCell(new Paragraph(12, "CÓDIGO: " + oSaldoDeudor.getMaeInversion().getCInversion().trim(), fontB));
                cell.setBorder(0);
                tableCab.addCell(cell);
                // dni
                fontB = new Font(bfb, 10);
                cell = new PdfPCell(new Paragraph(10, "DNI: " + oSaldoDeudor.getMaeInversion().getcPersonaId().getANroDocumento(), fontB));
                cell.setBorder(0);
                tableCab.addCell(cell);
                // datos de la persona
                cell = new PdfPCell(new Paragraph(10, oSaldoDeudor.getMaeInversion().getcPersonaId().getDApePat() + " "
                        + oSaldoDeudor.getMaeInversion().getcPersonaId().getDApeMat() + " "
                        + oSaldoDeudor.getMaeInversion().getcPersonaId().getDNombres(), fontB));
                cell.setBorder(0);
                tableCab.addCell(cell);
                font = new Font(bf, 8);
                // direccion
                cell = new PdfPCell(new Paragraph(12, "PREDIO: " + oSaldoDeudor.getMaeInversion().getcPersonaId().getMaeDireccionList().get(0).getADir1()
                        + "  " + oSaldoDeudor.getMaeInversion().getcPersonaId().getMaeDireccionList().get(0).getMaeUbigeo().getDDUbigeoProv().trim()
                        + " - " + oSaldoDeudor.getMaeInversion().getcPersonaId().getMaeDireccionList().get(0).getMaeUbigeo().getDDUbigeoDist().trim(), font));
                cell.setBorder(0);
                tableCab.addCell(cell);
                // quitando los bordes
                PdfPCell wrongCell = new PdfPCell(tableCab);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                // adicionando la primera tabla convertida en wrong a la tabla Columna 1
                table.addCell(wrongCell);
                // adicionando la primera tabla convertida en wrong a la tabla Columna 2 en blanco
                wrongCell = new PdfPCell(new Phrase(""));
                wrongCell.setBorder(Rectangle.NO_BORDER);
                table.addCell(wrongCell);
                // creando la tabla de la columna 3 con 2 columnas 
                PdfPTable tableB = new PdfPTable(2);
                tableB.setWidths(new float[]{70, 30});
                //right.setIndentationLeft(10);
                font = new Font(bf, 10);
                bColorBorde = new BaseColor(28, 50, 93);

                cell = new PdfPCell(new Paragraph(12, "Fecha inico de la operación:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(bColorBorde);
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableB.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatter.format(oSaldoDeudor.getMaeInversion().getFEmision()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderColor(bColorBorde);
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableB.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Fecha fin de la operación:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(bColorBorde);
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableB.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatter.format(oSaldoDeudor.getMaeInversion().getFVencimiento()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderColor(bColorBorde);
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableB.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(12, "Moneda:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(bColorBorde);
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableB.addCell(cell);
                cell = new PdfPCell(new Paragraph(12,oSaldoDeudor.getMaeInversion().getCmoneda() , font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderColor(bColorBorde);
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableB.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(12, "Tasa de la operacion:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(bColorBorde);
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableB.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, Double.toString((Double) oSaldoDeudor.getMaeInversion().getPTasa() * 100) + "%", font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderColor(bColorBorde);
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableB.addCell(cell);



                cell = new PdfPCell(new Paragraph(12, "Monto de la operación:", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(bColorBorde);
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableB.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format((Double) oSaldoDeudor.getMaeInversion().getIInversion()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderColor(bColorBorde);
                cell.setBorderWidth(new Float(1.5));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableB.addCell(cell);

                wrongCell = new PdfPCell(tableB);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                table.addCell(wrongCell);

                // creando la cabecera y el pie de pagina
                HeaderFooter1 headerFooter = new HeaderFooter1(table);
                if (oSaldoDeudor.getMaeInversion().getMaeFondo().getCFondoId().equals("0001")) {
                    //headerFooter.setNameLogo("logoemprendedor.png");
                    oMaeReporte.setNameLogo("logoemprendedor.png");
                } else if (oSaldoDeudor.getMaeInversion().getMaeFondo().getCFondoId().equals("0002")) {
                    //headerFooter.setNameLogo("logopopular.png");
                    oMaeReporte.setNameLogo("logopopular.png");
                } else if (oSaldoDeudor.getMaeInversion().getMaeFondo().getCFondoId().equals("0003")) {
                    //headerFooter.setNameLogo("logomype.png");
                    oMaeReporte.setNameLogo("logomype.png");
                } else {
                    //headerFooter.setNameLogo("logosafi.png");
                    oMaeReporte.setNameLogo("logosafi.png");
                }
                // datos del reporte
                //oMaeReporte.setNameReport("  ESTADO DE CUENTA A LA FECHA " + formatter.format(oMaeReporte.getfIniBusq()));
                oMaeReporte.setNameReport("SALDO DEUDOR DEL " + formatter.format(oSaldoDeudor.getFactual()) + "  AL " + formatter.format(oSaldoDeudor.getFfutura()));
                //headerFooter.setNameReport("SALDO DEUDOR DEL " + formatter.format(oSaldoDeudor.getFactual()) + "  AL " + formatter.format(oSaldoDeudor.getFfutura()));
                //headerFooter.setFecha(formatter.format(oMaeReporte.getfIniBusq()));
                //headerFooter.setUserName(oMaeReporte.getcUsuarioAdd());
                headerFooter.setMaeReporte(oMaeReporte);

                // aumentando la cabecera y detalle
                writer.setPageEvent(headerFooter);
                document.open();
                //document.add(table);
                //font = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);

                document.add(new Paragraph(" ", font));

                PdfPTable tableDetalle = new PdfPTable(3);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{30, 40, 30});

                font = new Font(bf, 9, Font.NORMAL, BaseColor.WHITE);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(11, "Fecha de emisión del reporte", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(11, "Fecha de cumplimiento de compromiso", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(11, "Fecha de la última cuota generada", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableDetalle.addCell(cell);

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(200, 200, 200);
                cell = new PdfPCell(new Paragraph(11, formatter.format(oSaldoDeudor.getFactual()), font));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                tableDetalle.addCell(cell);
                //
                cell = new PdfPCell(new Paragraph(11, formatter.format(oSaldoDeudor.getFfutura()), font));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                tableDetalle.addCell(cell);
                //
                if (oSaldoDeudor.getFultCuota() != null) {
                    cell = new PdfPCell(new Paragraph(11, formatter.format(oSaldoDeudor.getFultCuota()), font));
                }else{
                    cell = new PdfPCell(new Paragraph(11, "No Generada", font));
                }

                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                tableDetalle.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableDetalle.addCell(cell);

                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));

                //------------------------------------------------- detalle
                tableDetalle = new PdfPTable(2);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{30, 70});
                // a favor del fondo
                font = new Font(bf, 10, Font.NORMAL, BaseColor.WHITE);
                bColor = new BaseColor(28, 50, 93);
                bColorBorde = new BaseColor(28, 50, 93);
                cell = new PdfPCell(new Paragraph(12, "A favor del fondo:", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle.addCell(cell);
                //
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(200, 200, 200);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getNfavorFondo()), font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableDetalle.addCell(cell);

                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));

                // -- tabla 
                tableDetalle = new PdfPTable(3);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{60, 2, 38});
                // tabla de cuotas vencidas
                PdfPTable tableCv = new PdfPTable(5);
                tableCv.setWidthPercentage(100);
                tableCv.setWidths(new float[]{10, 15, 21, 21, 17 });

                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(182, 221, 240);
                bColorBorde = new BaseColor(200, 200, 200);
                cell = new PdfPCell(new Paragraph(12, "Cuotas vencidas", font));
                //cell.setColspan(5);
                cell.setColspan(6);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);

                font = new Font(bf, 8, Font.NORMAL, BaseColor.BLACK);

                cell = new PdfPCell(new Paragraph(12, "N.Cta", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, "Capital", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, "Interes Compensatorio", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, "Interes Compensatorio por Atraso", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
               /*  cell = new PdfPCell(new Paragraph(12, "Interes Moratorio", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                tableCv.addCell(cell);   */     
                cell = new PdfPCell(new Paragraph(12, "Total", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
                tableCv.setHeaderRows(1);

                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(200, 200, 200);

                String flColor = "0";
                for (MaeCronograma mc : oSaldoDeudor.getMaeCronogramaList()) {
                    if (flColor.equals("0")) {
                        bColor = new BaseColor(240, 240, 240);
                    } else {
                        bColor = new BaseColor(255, 255, 255);
                    }

                    cell = new PdfPCell(new Paragraph(12, "" + mc.getnSecuencia(), font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);

                    cell = new PdfPCell(new Paragraph(12, formatterNum.format(mc.getIcapital()), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);

                    cell = new PdfPCell(new Paragraph(12, formatterNum.format(mc.getIinteres()), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);

                    double dIntMoratorio = mc.getImora().doubleValue();
                    double dIntCompVencido = dIntMoratorio;
                    Number nIntCompVencido = (Number)dIntCompVencido;
                    cell = new PdfPCell(new Paragraph(12, formatterNum.format(nIntCompVencido), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);

                    /*double dIntMoratoriov2 = 0;
                    Number nIntMoratoriov2 = (Number)dIntMoratoriov2;
                    cell = new PdfPCell(new Paragraph(12, formatterNum.format(nIntMoratoriov2), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);   */         

                    cell = new PdfPCell(new Paragraph(12, formatterNum.format(((Double) mc.getIcapital()) + ((Double) mc.getIinteres()) + ((Double) nIntCompVencido)), font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBackgroundColor(bColor);
                    cell.setBorderColor(bColorBorde);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableCv.addCell(cell);
                    if (flColor.equals("0")) {
                        flColor = "1";
                    } else {
                        flColor = "0";
                    }
                }
                // totales
                cell = new PdfPCell(new Paragraph(12, "Total cuotas vencidas", font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIcapAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIicAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);

                double dTotIntMoratorio = oSaldoDeudor.getIimAtra();
                double dTotIntCompVencido = dTotIntMoratorio;
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(dTotIntCompVencido), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                /*
                double dTotIntMoratoriov2 = 0;
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(dTotIntMoratoriov2), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);*/

                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getNtotAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                // primera columna
                wrongCell = new PdfPCell(tableCv);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                tableDetalle.addCell(wrongCell);
                // en blanco -- 2 columna
                wrongCell = new PdfPCell(new Phrase(""));
                wrongCell.setBorder(Rectangle.NO_BORDER);
                tableDetalle.addCell(wrongCell);

                // cuotas por vencer
                tableCv = new PdfPTable(2);
                tableCv.setWidthPercentage(100);
                tableCv.setWidths(new float[]{70, 30});
                font = new Font(bf, 9, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(182, 221, 240);
                bColorBorde = new BaseColor(200, 200, 200);

                cell = new PdfPCell(new Paragraph(12, "Cuotas por vencer ", font));
                cell.setColspan(2);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCv.addCell(cell);
                //
                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(200, 200, 200);
                cell = new PdfPCell(new Paragraph(12, "Capital ", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIcapFut()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, "Interes compensatorio del " + formatter.format(oSaldoDeudor.getFultCuota()) + " al " + formatter.format(oSaldoDeudor.getFfutura()), font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIicFut()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);

                cell = new PdfPCell(new Paragraph(12, "Total por vencer ", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(((Double) oSaldoDeudor.getIcapFut()) + ((Double) oSaldoDeudor.getIicFut())), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableCv.addCell(cell);
                // ultima columna 3
                wrongCell = new PdfPCell(tableCv);
                wrongCell.setBorder(Rectangle.NO_BORDER);
                tableDetalle.addCell(wrongCell);
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
                // sobrecargo bancario
                tableDetalle = new PdfPTable(2);
                tableDetalle.setWidthPercentage(80);
                tableDetalle.setWidths(new float[]{50, 50});

                cell = new PdfPCell(new Paragraph(12, "Sobrecargo bancario", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIcargCuoAtra()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, "Gastos legales", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIgastLegalFut()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, "Gastos administrativos", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle.addCell(cell);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIgastAdmin()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableDetalle.addCell(cell);

                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));

                tableDetalle = new PdfPTable(2);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{30, 70});
                font = new Font(bf, 10, Font.NORMAL, BaseColor.WHITE);
                bColor = new BaseColor(0, 102, 51);
                bColorBorde = new BaseColor(0, 102, 51);
                cell = new PdfPCell(new Paragraph(12, "A favor del cliente:", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle.addCell(cell);
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(200, 200, 200);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getIsaldoFavor()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableDetalle.addCell(cell);
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
                //  total deuda
                tableDetalle = new PdfPTable(2);
                tableDetalle.setWidthPercentage(90);
                tableDetalle.setWidths(new float[]{30, 70});
                font = new Font(bf, 10, Font.NORMAL, BaseColor.WHITE);
                bColor = new BaseColor(153, 0, 0);
                bColorBorde = new BaseColor(153, 0, 0);
                cell = new PdfPCell(new Paragraph(12, "Total de la deuda:", font));
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableDetalle.addCell(cell);
                font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
                bColor = new BaseColor(255, 255, 255);
                bColorBorde = new BaseColor(200, 200, 200);
                cell = new PdfPCell(new Paragraph(12, formatterNum.format(oSaldoDeudor.getNtotDebe() + oSaldoDeudor.getIgastLegalFut() + oSaldoDeudor.getIgastAdmin()), font));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBackgroundColor(bColor);
                cell.setBorderColor(bColorBorde);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableDetalle.addCell(cell);
                document.add(tableDetalle);
                document.add(new Paragraph(" ", font));
        }
        
  
        
        document.addAuthor(oMaeReporte.getUserName());
        document.addCreationDate();
        document.addCreator("popular-safi.com");
        document.addTitle("SALDO DEUDOR DEL " + formatter.format(oSaldoDeudor.getFactual()) + "  AL " + formatter.format(oSaldoDeudor.getFfutura()));
        
        // grabando archivo para autidoria
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String ruta;
        String uuid;
        uuid = oSaldoDeudor.getMaeInversion().getCInversion().trim() + "_" + UUID.randomUUID().toString() + ".pdf";
        //ruta = "E:\\webcobranzas\\files\\debit_balance\\" + localDate.getYear()
        ruta = "C:\\webcobranzas\\files\\debit_balance\\" + localDate.getYear()
                + "\\" + localDate.getMonthValue()
                + "\\" + localDate.getDayOfMonth()
                + "\\" + uuid;
        File fileO = new File(ruta);
        fileO.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(fileO);
        
        document.close();
        baos.writeTo(fos);
        fos.close();
        //System.out.println("pop.webcobranzas.procesos.SessionSaldoDeudor.imprimirSaldoDeudor() f");
        return baos.toByteArray();

    }

   @Override
   public byte[] exportarSaldoDeudor(RepSaldoDeudor oSaldoDeudor, MaeReporte oMaeReporte) throws Exception {
        //System.out.println(" <i> pop.webcobranzas.procesos.SessionSaldoDeudor.exportarSaldoDeudor() ");
        // logo
      
        Double lnCapital=0.00;
        Double lnInteres=0.00;
        Double nIntCompVencido=0.00;
        Double dIntMoratorio=0.00;
        Double dICA=0.00;
        for (MaeCronograma mc : oSaldoDeudor.getMaeCronogramaList()) {
             lnCapital = lnCapital +  mc.getIcapital().doubleValue();
             lnInteres = lnInteres +  mc.getIinteres().doubleValue();
             dIntMoratorio = dIntMoratorio + mc.getImora().doubleValue();
             dICA= dICA + mc.getIca().doubleValue();
         }
        
        
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        String nameLogo = "";
        String nameFondo = "";
        MaeInversion inversion = oSaldoDeudor.getMaeInversion();
       
        System.out.println("SaldoDeudorResumen.xlsx");
            
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat formatterNum = new DecimalFormat("###,###,##0.00");
        DecimalFormat formatterNum1 = new DecimalFormat("##0.00");
        if (oSaldoDeudor.getxFLGNew().equals("S")){
              
                        String ruta_conf="C:\\webcobranzas\\";
                        String nombreArchivo = "SaldoDeudorResumen.xlsx";
                        String nombreArchivo1 = "SaldoDeudorResumen.xlsx";
                        String rutaArchivo = ruta_conf+ nombreArchivo;
                        String rutaArchivo1 = ruta_conf+ nombreArchivo1;
                        int newreg=0;
                        int inn1,inn2,inn3,inn4,inn5;
                        int inn6,inn7,inn8,inn9,inn10;
                        int inn11,inn12,inn13,inn14,inn15;
                        int inn16,inn17,inn18,inn19,inn20;
                        int inn21,inn22,inn23,inn29;
                        File archivo = new File(ruta_conf+nombreArchivo1);
                        OutputStream excelNewOutputStream = null;


                        try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {    

                            XSSFWorkbook worbook = new XSSFWorkbook(file);
                            worbook.setForceFormulaRecalculation(true);                           
                            //obtener la hoja que se va leer
                            XSSFSheet sheet = worbook.getSheetAt(0);
                            //obtener todas las filas de la hoja excel
                            Iterator<Row> rowIterator = sheet.iterator();

                            Row row;
                            // se recorre cada fila hasta el final
                            while (rowIterator.hasNext()) {
                                    newreg ++;
                                    row = rowIterator.next();
                                    if (newreg==4){
                                        if (row.getCell(10) != null){
                                             Date date = new Date();
                                              LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                                row.getCell(9).setCellValue("SALDO DEUDOR " + oSaldoDeudor.getImpresionSD() + " - " + localDate.getYear() );
                                        }
                                    }
                                    if (newreg==6){
                                        if (row.getCell(4) != null){
                                                row.getCell(4).setCellValue(inversion.getcPersonaId().getDApePat() + " " + inversion.getcPersonaId().getDApeMat() + " " + inversion.getcPersonaId().getDNombres());
                                                row.getCell(10).setCellValue(oSaldoDeudor.getMaeInversionList().get(0).getcFONDO());
                                        }
                                    }
                                    if (newreg==7){    
                                        if (row.getCell(4) != null){
                                            row.getCell(4).setCellValue(inversion.getCInversion().trim());
                                            row.getCell(11).setCellValue(oSaldoDeudor.getFactual());
                                       }
                                    }    
                                    if (newreg==8){    
                                       if (row.getCell(4) != null){
                                          row.getCell(4).setCellValue( (Double) inversion.getIInversion()  );
                                           row.getCell(11).setCellValue(oSaldoDeudor.getFproxcuota());
                                       }
                                    } 
                                    if (newreg==9){    
                                       if (row.getCell(4) != null){
                                          row.getCell(11).setCellValue(oSaldoDeudor.getLs_estadoCrono());
                                       }
                                    } 
                                    if (newreg==10){    
                                       if (row.getCell(4) != null){
                                          row.getCell(4).setCellValue(oSaldoDeudor.getMaeInversion().getCmoneda() );
                                          row.getCell(11).setCellValue(oSaldoDeudor.getUltcuota());
                                       }
                                    } 
                                    
                                    if (newreg==15){    
                                       if (row.getCell(5) != null){
                                          row.getCell(5).setCellValue(formatter.format(oSaldoDeudor.getFfutura()));
                                       }
                                        row.getCell(12).setCellValue(oSaldoDeudor.getNtotDebe());
                                    } 
                                  
                                   if (newreg==19){
                                       if (row.getCell(5) != null){
                                           row.getCell(5).setCellValue(lnCapital); 
                                           row.getCell(6).setCellValue(oSaldoDeudor.getIcapFut()); 
                                           row.getCell(7).setCellValue(lnCapital+oSaldoDeudor.getIcapFut()); 
                                           row.getCell(10).setCellValue(Double.toString((Double)oSaldoDeudor.getnTasaDia()) + "%");
                                       }
                                       if (row.getCell(11) != null){
                                           //row.getCell(11).setCellValue(formatterNum1.format((Double) oSaldoDeudor.getMaeInversion().getPTasa() * 100) ); 
                                           row.getCell(11).setCellValue(oSaldoDeudor.getMaeInversion().getPTasa().toString().replace(',', '.')); 
                                       }
                                   }                         
                                    if (newreg==20){
                                       if (row.getCell(5) != null){
                                           row.getCell(5).setCellValue(lnInteres); 
                                           row.getCell(6).setCellValue(oSaldoDeudor.getIicFut()); 
                                           row.getCell(7).setCellValue(lnInteres+oSaldoDeudor.getIicFut()); 
                                           row.getCell(10).setCellValue(formatterNum1.format((Double) oSaldoDeudor.getnTasa15Dia())+ "%");
                                       }
                                       if (row.getCell(11) != null){
                                           //row.getCell(11).setCellValue(formatterNum1.format((Double) oSaldoDeudor.getMaeInversion().getPTasa() * 15) ); 
                                           row.getCell(11).setCellValue(oSaldoDeudor.getMaeInversion().getPTasa().toString().replace(',', '.') ); 
                                       }
                                   }  
                                   if (newreg==21){
                                       if (row.getCell(5) != null){
                                           row.getCell(5).setCellValue(dICA); 
                                           row.getCell(7).setCellValue(dICA);
                                       }
                                   }                                        
                                   if (newreg==22){
                                       if (row.getCell(5) != null){
                                           row.getCell(5).setCellValue(dIntMoratorio); 
                                           row.getCell(7).setCellValue(dIntMoratorio);
                                       }
                                   }  
                                   if (newreg==23){
                                       if (row.getCell(5) != null){
                                            row.getCell(5).setCellValue(lnCapital+lnInteres+dICA+dIntMoratorio); 
                                       }
                                       if (row.getCell(6) != null){
                                           row.getCell(6).setCellValue(oSaldoDeudor.getIcapFut()+oSaldoDeudor.getIicFut()); 
                                           row.getCell(7).setCellValue(lnCapital+oSaldoDeudor.getIcapFut()+lnInteres+oSaldoDeudor.getIicFut()+dICA+dIntMoratorio); 
                                       }
                                   }  
                                   
                                    if (newreg==25){
                                       if (row.getCell(7) != null){
                                           row.getCell(7).setCellValue(oSaldoDeudor.getIsaldoFavor()); 
                                       }
                                   } 
                                   if (newreg==28){
                                       if (row.getCell(7) != null){
                                           row.getCell(7).setCellValue(oSaldoDeudor.getIgastLegalFut() ); 
                                       }
                                   } 
                                   if (newreg==29){
                                       if (row.getCell(7) != null){
                                           row.getCell(7).setCellValue(oSaldoDeudor.getIgastAdmin()); 
                                       }
                                   } 
                                   if (newreg==30){
                                       if (row.getCell(7) != null){
                                           row.getCell(7).setCellValue(oSaldoDeudor.getIcargCuoAtra()); 
                                       }
                                   } 
                                   if (newreg==31){
                                       if (row.getCell(3) != null){
                                           row.getCell(3).setCellValue("Total saldo al " + formatter.format(oSaldoDeudor.getFfutura()));
                                           row.getCell(7).setCellValue(oSaldoDeudor.getIgastLegalFut()+ oSaldoDeudor.getIgastAdmin()+oSaldoDeudor.getIcargCuoAtra()); 
                                       }
                                   }
                               
                                   if (newreg==35){
                                       if (row.getCell(2) != null){
                                           Date date = new Date();
                                            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                            LocalTime time = LocalTime.now();
                                           row.getCell(2).setCellValue("Usuario " +oMaeReporte.getcUsuarioAdd()  + " ,Fecha " + localDate.getDayOfMonth()+ "/" + localDate.getMonthValue() + "/" + localDate.getYear()  + " , Hora " +   time  );
                                       }
                                   }
                                     
                            }
                            
                            worbook.setForceFormulaRecalculation(true);
                            
                            FileOutputStream salida = new FileOutputStream(archivo);
                            worbook.write(salida);
                            worbook.close();

                            byte[] asss = Files.readAllBytes(new File(rutaArchivo1).toPath());
                            byteOutputStream.write(asss);
                             } catch (FileNotFoundException fileNotFoundException) {
                            System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
                        } catch (IOException ex) {
                            System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
                        } finally {

                      }   
           }else{
                     
        int xLine = 3;

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        
        // font negrilla
        org.apache.poi.ss.usermodel.Font fontNeg = workbook.createFont();
        fontNeg.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
        byte[] rgb = new byte[3];
        rgb[0] = (byte) 166; // red
        rgb[1] = (byte) 112; // green
        rgb[2] = (byte) 12; // blue

        XSSFColor myColor = new XSSFColor(rgb);

        // style black
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(fontNeg);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        CellStyle headerStyleRigth = workbook.createCellStyle();
        headerStyleRigth.setAlignment(CellStyle.ALIGN_RIGHT);
        headerStyleRigth.setFont(fontNeg);
        headerStyleRigth.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyleRigth.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyleRigth.setDataFormat((short) 7);

        CellStyle bodyStyle = workbook.createCellStyle();

        CellStyle bodyStyleRigth = workbook.createCellStyle();
        bodyStyleRigth.setAlignment(CellStyle.ALIGN_RIGHT);

        DataFormat datafrmt = workbook.createDataFormat();
        //bodyStyleRigth.setDataFormat(datafrmt.getFormat("_ S/. * #,##0_ ;_ S/. * -#,##0_ ;_ S/. * \"-\"_ ;_ @_ "));
        bodyStyleRigth.setDataFormat((short) 7);

        

        if (oSaldoDeudor == null) {
            return byteOutputStream.toByteArray();
        }

        switch (oSaldoDeudor.getMaeInversion().getMaeFondo().getCFondoId()) {
            case "0001":
                nameLogo = "logoemprendedor.png";
                nameFondo = "Fondo Capital Emprendedor";
                break;
            case "0002":
                nameLogo = "logopopular.png";
                nameFondo = "Fondo Popular";
                break;
            case "0003":
                nameLogo = "logomype.png";
                nameFondo = "Fondo MYPE";
                break;
            case "0004":
                nameLogo = "logosafi.png";
                nameFondo = "Fondo Perez Hidalgo";
                break;
            default:
                break;
        }

        String imagenPath = Paths.get("/pop/webcobranzas/resources/jpg", nameLogo).toString();
        InputStream inputStream = new FileInputStream(imagenPath);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        int pictureIdx = workbook.addPicture(bytes, workbook.PICTURE_TYPE_PNG);
        inputStream.close();

        CreationHelper helper = workbook.getCreationHelper();

        Drawing drawing = sheet.createDrawingPatriarch();

        ClientAnchor anchor = helper.createClientAnchor();

        anchor.setCol1(1);
        anchor.setRow1(0);
        anchor.setCol2(2); //Column C
        anchor.setRow2(1); //Row 4

        //Creates a picture
        Picture pict = drawing.createPicture(anchor, pictureIdx);
        //Reset the image to the original size
        pict.resize();
        pict.resize(0.3);

        // obteniendo la inversion
        inversion = oSaldoDeudor.getMaeInversion();
        // nombre de la hoja del excel
        workbook.setSheetName(0, inversion.getCInversion().trim());

        XSSFCell cell;

        XSSFRow rowHeader;

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Código");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(headerStyle);
        cell = rowHeader.createCell(3);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(inversion.getCInversion().trim());
        cell = rowHeader.createCell(4);
        cell.setCellStyle(headerStyle);

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Cliente");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(headerStyle);
        cell = rowHeader.createCell(3);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(inversion.getcPersonaId().getDApePat() + " "
                + inversion.getcPersonaId().getDApeMat() + " "
                + inversion.getcPersonaId().getDNombres());
        cell = rowHeader.createCell(4);
        cell.setCellStyle(headerStyle);

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Fondo");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(headerStyle);
        cell = rowHeader.createCell(3);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(nameFondo.toUpperCase());
        cell = rowHeader.createCell(4);
        cell.setCellStyle(headerStyle);

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Fecha de solicitud");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(headerStyle);
        cell = rowHeader.createCell(3);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(formatter.format(oSaldoDeudor.getFactual()));
        cell = rowHeader.createCell(4);
        cell.setCellStyle(headerStyle);

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Fecha del Estado deudor");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(headerStyle);
        cell = rowHeader.createCell(3);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(formatter.format(oSaldoDeudor.getFfutura()));
        cell = rowHeader.createCell(4);
        cell.setCellStyle(headerStyle);

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("Saldo de Capital  de Cronograma al");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(formatter.format(oSaldoDeudor.getFfutura()));
        cell = rowHeader.createCell(4);
        cell.setCellStyle(bodyStyleRigth);
        cell.setCellValue(oSaldoDeudor.getIcapFut());

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("Compensatorio de la cuota  al");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(formatter.format(oSaldoDeudor.getFfutura()));
        cell = rowHeader.createCell(4);
        cell.setCellStyle(bodyStyleRigth);
        cell.setCellValue(oSaldoDeudor.getIicFut());

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Total saldo Cronograma");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(headerStyle);
        cell = rowHeader.createCell(3);
        cell.setCellStyle(headerStyle);
        cell = rowHeader.createCell(4);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(((Double) oSaldoDeudor.getIcapFut()) + ((Double) oSaldoDeudor.getIicFut()));

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("Saldo de capital atrasado al");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(formatter.format(oSaldoDeudor.getFfutura()));
        cell = rowHeader.createCell(4);
        cell.setCellStyle(bodyStyleRigth);
        cell.setCellValue((double) oSaldoDeudor.getIcapAtra());

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("Saldos de interés compensatorio atrasado al");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(formatter.format(oSaldoDeudor.getFfutura()));
        cell = rowHeader.createCell(4);
        cell.setCellStyle(bodyStyleRigth);
        cell.setCellValue((double) oSaldoDeudor.getIicAtra());

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("Saldo a favor en EECC");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(formatter.format(oSaldoDeudor.getFfutura()));
        cell = rowHeader.createCell(4);
        cell.setCellStyle(bodyStyleRigth);
        cell.setCellValue((double) oSaldoDeudor.getIsaldoFavor());

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("Saldos de interés moratorio");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(formatter.format(oSaldoDeudor.getFfutura()));
        cell = rowHeader.createCell(4);
        cell.setCellStyle(bodyStyleRigth);
        cell.setCellValue((double) oSaldoDeudor.getIimAtra());

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Total saldo Estado de Cuenta");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(headerStyle);
        cell = rowHeader.createCell(3);
        cell.setCellStyle(headerStyle);
        cell = rowHeader.createCell(4);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(((Double) oSaldoDeudor.getIcapAtra())
                + ((Double) oSaldoDeudor.getIicAtra())
                + ((Double) oSaldoDeudor.getIimAtra())
                - ((Double) oSaldoDeudor.getIsaldoFavor()));

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("Gastos de cobranza (c.notariales, protesto, otros)");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell = rowHeader.createCell(4);
        cell.setCellStyle(bodyStyleRigth);
        cell.setCellValue((double) ((Double) oSaldoDeudor.getIcargCuoAtra())
                + ((Double) oSaldoDeudor.getIgastLegalFut())
                + ((Double) oSaldoDeudor.getIgastAdmin())
        );
        xLine++;

        rowHeader = sheet.createRow((short) xLine++);
        cell = rowHeader.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Deuda Total");
        cell = rowHeader.createCell(2);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(formatter.format(oSaldoDeudor.getFfutura()));
        cell = rowHeader.createCell(3);
        cell.setCellStyle(headerStyle);
        cell = rowHeader.createCell(4);
        cell.setCellStyle(headerStyleRigth);
        cell.setCellValue(oSaldoDeudor.getNtotDebe() + oSaldoDeudor.getIgastLegalFut() + oSaldoDeudor.getIgastAdmin());

        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);

        workbook.write(byteOutputStream);
        
        // grabando archivo para autidoria
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String ruta;
        String uuid;
        uuid = inversion.getCInversion().trim() + "_" + UUID.randomUUID().toString() + ".xlsx";
        ruta = "C:\\webcobranzas\\files\\debit_balance\\" + localDate.getYear()
                + "\\" + localDate.getMonthValue()
                + "\\" + localDate.getDayOfMonth()
                + "\\" + uuid;
        File fileO = new File(ruta);
        fileO.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(fileO);
        byteOutputStream.writeTo(fos);
        fos.close();
                            
       }                    
        //System.out.println(" <f> pop.webcobranzas.procesos.SessionSaldoDeudor.exportarSaldoDeudor() ");
        return byteOutputStream.toByteArray();
                                
        
    }

//    public byte[] exportarSaldoDeudorNo(RepSaldoDeudor oSaldoDeudor, MaeReporte oMaeReporte) throws Exception {
//        System.out.println(" <i> pop.webcobranzas.procesos.SessionSaldoDeudor.exportarSaldoDeudor() ");
//
//        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
//        int xLine = 1;
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet();
//        // font negrilla
//        org.apache.poi.ss.usermodel.Font fontNeg = workbook.createFont();
//        fontNeg.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
//        // style black
//        CellStyle headerStyle = workbook.createCellStyle();
//        headerStyle.setFont(fontNeg);
//        // style
//        CellStyle style = workbook.createCellStyle();
//
//        //DecimalFormat formatterNum = new DecimalFormat("###,###,###.00");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//
//        if (oSaldoDeudor == null) {
//            return byteOutputStream.toByteArray();
//        }
//        // obteniendo la inversion
//        MaeInversion inversion = oSaldoDeudor.getMaeInversion();
//        // nombre de la hoja del excel
//        workbook.setSheetName(0, inversion.getCInversion().trim());
//
//        XSSFCell cell;
//
//        // codigo
//        XSSFRow rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue("CÓDIGO:");
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue(inversion.getCInversion().trim());
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue("Fecha inico de la operación:");
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue(formatter.format(inversion.getFEmision()));
//        // dni
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue("DNI:");
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue(inversion.getcPersonaId().getANroDocumento());
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue("Fecha fin de la operación:");
//        rowHeader.createCell(9).setCellValue(formatter.format(inversion.getFVencimiento()));
//        // persona
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue("APELLIDOS Y NOMBRES:");
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue(inversion.getcPersonaId().getDApePat() + " "
//                + inversion.getcPersonaId().getDApeMat() + " "
//                + inversion.getcPersonaId().getDNombres());
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue("Tasa de la operación:");
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue((double) inversion.getPTasa() * 100 + "%");
//        // direccion
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue("PREDIO:");
//        if (inversion.getcPersonaId().getMaeDireccionList().get(0).getMaeUbigeo().getDDUbigeoDist() != null) {
//            cell = rowHeader.createCell(2);
//            cell.setCellStyle(headerStyle);
//            cell.setCellValue(inversion.getcPersonaId().getMaeDireccionList().get(0).getADir1()
//                    + "  " + inversion.getcPersonaId().getMaeDireccionList().get(0).getMaeUbigeo().getDDUbigeoProv().trim()
//                    + " - " + inversion.getcPersonaId().getMaeDireccionList().get(0).getMaeUbigeo().getDDUbigeoDist().trim());
//        } else {
//            rowHeader.createCell(1).setCellValue(" ");
//        }
//        
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue("Monto de la operación:");
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(headerStyle);
//        cell.setCellValue((double) inversion.getIInversion());
//
//        sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 7));
//        
//        xLine++;
//
//        // style black
//        CellStyle headerStyleCenter = workbook.createCellStyle();
//        headerStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);
//        headerStyleCenter.setBorderBottom(CellStyle.BORDER_THIN);
//        headerStyleCenter.setBorderLeft(CellStyle.BORDER_THIN);
//        headerStyleCenter.setBorderRight(CellStyle.BORDER_THIN);
//        headerStyleCenter.setBorderTop(CellStyle.BORDER_THIN);
//        headerStyleCenter.setFont(fontNeg);
//
//        // fechas de informacion
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(headerStyleCenter);
//        cell.setCellValue("Fecha de emisión del reporte");
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(3);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(4);
//        cell.setCellStyle(headerStyleCenter);
//        cell.setCellValue("Fecha de cumplimiento de compromiso");
//        cell = rowHeader.createCell(5);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(6);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(7);
//        cell.setCellStyle(headerStyleCenter);
//        cell.setCellValue("Fecha de la última cuota generada");
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(headerStyleCenter);
//
//        rowHeader = sheet.createRow((short) xLine++);
//
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(headerStyleCenter);
//        cell.setCellValue(formatter.format(oSaldoDeudor.getFactual()));
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(3);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(4);
//        cell.setCellStyle(headerStyleCenter);
//        cell.setCellValue(formatter.format(oSaldoDeudor.getFfutura()));
//        cell = rowHeader.createCell(5);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(6);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(7);
//        cell.setCellStyle(headerStyleCenter);
//        cell.setCellValue(formatter.format(oSaldoDeudor.getFultCuota()));
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(headerStyleCenter);
//
//        sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 3));
//        sheet.addMergedRegion(new CellRangeAddress(6, 6, 4, 6));
//        sheet.addMergedRegion(new CellRangeAddress(6, 6, 7, 9));
//
//        sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 3));
//        sheet.addMergedRegion(new CellRangeAddress(7, 7, 4, 6));
//        sheet.addMergedRegion(new CellRangeAddress(7, 7, 7, 9));
//
//        xLine++;
//        // style black
//        CellStyle headerStyleRigth = workbook.createCellStyle();
//        headerStyleRigth.setAlignment(CellStyle.ALIGN_RIGHT);
//        headerStyleRigth.setBorderBottom(CellStyle.BORDER_THIN);
//        headerStyleRigth.setBorderLeft(CellStyle.BORDER_THIN);
//        headerStyleRigth.setBorderRight(CellStyle.BORDER_THIN);
//        headerStyleRigth.setBorderTop(CellStyle.BORDER_THIN);
//        headerStyleRigth.setFont(fontNeg);
//
//        CellStyle bodyStyle = workbook.createCellStyle();
//        bodyStyle.setBorderBottom(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderLeft(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderRight(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderTop(CellStyle.BORDER_THIN);
//
//        CellStyle bodyStyleCenter = workbook.createCellStyle();
//        bodyStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);
//        bodyStyleCenter.setBorderBottom(CellStyle.BORDER_THIN);
//        bodyStyleCenter.setBorderLeft(CellStyle.BORDER_THIN);
//        bodyStyleCenter.setBorderRight(CellStyle.BORDER_THIN);
//        bodyStyleCenter.setBorderTop(CellStyle.BORDER_THIN);
//
//        CellStyle bodyStyleRigth = workbook.createCellStyle();
//        bodyStyleRigth.setAlignment(CellStyle.ALIGN_RIGHT);
//        bodyStyleRigth.setBorderBottom(CellStyle.BORDER_THIN);
//        bodyStyleRigth.setBorderLeft(CellStyle.BORDER_THIN);
//        bodyStyleRigth.setBorderRight(CellStyle.BORDER_THIN);
//        bodyStyleRigth.setBorderTop(CellStyle.BORDER_THIN);
//
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(headerStyleCenter);
//        cell.setCellValue("A favor del fondo");
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(headerStyleRigth);
//        cell.setCellValue(oSaldoDeudor.getNfavorFondo());
//        cell = rowHeader.createCell(3);
//        cell.setCellStyle(headerStyleRigth);
//        cell = rowHeader.createCell(4);
//        cell.setCellStyle(headerStyleRigth);
//        cell = rowHeader.createCell(5);
//        cell.setCellStyle(headerStyleRigth);
//        cell = rowHeader.createCell(6);
//        cell.setCellStyle(headerStyleRigth);
//        cell = rowHeader.createCell(7);
//        cell.setCellStyle(headerStyleRigth);
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(headerStyleRigth);
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(headerStyleRigth);
//        sheet.addMergedRegion(new CellRangeAddress(9, 9, 1, 8));
//
//        xLine++;
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(headerStyleCenter);
//        cell.setCellValue("Cuotas vencidas adeudadas");
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(3);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(4);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(5);
//        cell.setCellStyle(headerStyleCenter);
//
//        cell = rowHeader.createCell(7);
//        cell.setCellStyle(headerStyleCenter);
//        cell.setCellValue("Cuotas por vencer");
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(headerStyleCenter);
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(headerStyleCenter);
//
//        sheet.addMergedRegion(new CellRangeAddress(11, 11, 1, 5));
//        sheet.addMergedRegion(new CellRangeAddress(11, 11, 7, 9));
//
//        String[] headers = new String[]{
//            "N° Cuota", "Capital", "Interes Compensatorio", "Interes moratorio", "Total"
//        };
//
//        rowHeader = sheet.createRow((short) xLine++);
//        for (int i = 0; i < headers.length; ++i) {
//            String header = headers[i];
//            cell = rowHeader.createCell(i + 1);
//            cell.setCellStyle(headerStyleCenter);
//            cell.setCellValue(header);
//        }
//
//        cell = rowHeader.createCell(7);
//        cell.setCellStyle(bodyStyle);
//        cell.setCellValue("Capital");
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(bodyStyleRigth);
//        cell.setCellValue(oSaldoDeudor.getIcapFut());
//
//        int xLineB = xLine - 1;
//        System.out.println("pop.webcobranzas.procesos.SessionSaldoDeudor.exportarSaldoDeudor()---" + xLineB);
//
//        int flg = 0;
//
//        for (MaeCronograma mc : oSaldoDeudor.getMaeCronogramaList()) {
//            rowHeader = sheet.createRow((short) xLine++);
//            // N° Cuota
//            cell = rowHeader.createCell(1);
//            cell.setCellStyle(bodyStyleCenter);
//            cell.setCellValue((int) mc.getnSecuencia());
//            // Capital
//            cell = rowHeader.createCell(2);
//            cell.setCellStyle(bodyStyleRigth);
//            cell.setCellValue((double) mc.getIcapital());
//            // Interes Compensatorio
//            cell = rowHeader.createCell(3);
//            cell.setCellStyle(bodyStyleRigth);
//            cell.setCellValue((double) mc.getIinteres());
//            // Interes moratorio
//            cell = rowHeader.createCell(4);
//            cell.setCellStyle(bodyStyleRigth);
//            cell.setCellValue((double) mc.getImora());
//            // Total
//            cell = rowHeader.createCell(5);
//            cell.setCellStyle(bodyStyleRigth);
//            cell.setCellValue((double) ((Double) mc.getIcapital()) + ((Double) mc.getIinteres()) + ((Double) mc.getImora()));
//
//            if (flg == 0) {
//                cell = rowHeader.createCell(7);
//                cell.setCellStyle(bodyStyle);
//                cell.setCellValue("Interes compensatorio del " + formatter.format(oSaldoDeudor.getFultCuota()) + " al " + formatter.format(oSaldoDeudor.getFfutura()));
//                cell = rowHeader.createCell(8);
//                cell.setCellStyle(bodyStyle);
//                cell = rowHeader.createCell(9);
//                cell.setCellStyle(bodyStyleRigth);
//                cell.setCellValue(oSaldoDeudor.getIicFut());
//            }
//
//            if (flg == 1) {
//                cell = rowHeader.createCell(7);
//                cell.setCellStyle(bodyStyle);
//                cell.setCellValue("Total por vencer");
//                cell = rowHeader.createCell(8);
//                cell.setCellStyle(bodyStyle);
//                cell = rowHeader.createCell(9);
//                cell.setCellStyle(bodyStyleRigth);
//                cell.setCellValue(((Double) oSaldoDeudor.getIcapFut()) + ((Double) oSaldoDeudor.getIicFut()));
//            }
//            flg++;
//        }
//
//        sheet.addMergedRegion(new CellRangeAddress(12, 12, 7, 8));
//        sheet.addMergedRegion(new CellRangeAddress(13, 13, 7, 8));
//        sheet.addMergedRegion(new CellRangeAddress(14, 14, 7, 8));
//
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(bodyStyleCenter);
//        cell.setCellValue("Total cuotas vencidas");
//
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(bodyStyleRigth);
//        cell.setCellValue((double) oSaldoDeudor.getIcapAtra());
//
//        cell = rowHeader.createCell(3);
//        cell.setCellStyle(bodyStyleRigth);
//        cell.setCellValue((double) oSaldoDeudor.getIicAtra());
//
//        cell = rowHeader.createCell(4);
//        cell.setCellStyle(bodyStyleRigth);
//        cell.setCellValue((double) oSaldoDeudor.getIimAtra());
//
//        cell = rowHeader.createCell(5);
//        cell.setCellStyle(bodyStyleRigth);
//        cell.setCellValue((double) oSaldoDeudor.getNtotAtra());
//
//        xLine++;
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(bodyStyle);
//        cell.setCellValue("Sobracargo bancario");
//        cell = rowHeader.createCell(3);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(4);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(5);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(6);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(7);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(bodyStyleRigth);
//        cell.setCellValue((double) oSaldoDeudor.getIcargCuoAtra());
//        
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(bodyStyle);
//        cell.setCellValue("Gatos legales");
//        cell = rowHeader.createCell(3);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(4);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(5);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(6);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(7);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(bodyStyleRigth);
//        cell.setCellValue((double) oSaldoDeudor.getIgastLegalFut());
//        
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(bodyStyle);
//        cell.setCellValue("Gastos administrativos");
//        cell = rowHeader.createCell(3);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(4);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(5);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(6);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(7);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(bodyStyleRigth);
//        cell.setCellValue((double) oSaldoDeudor.getIgastAdmin());
//
//        sheet.addMergedRegion(new CellRangeAddress(18, 18, 2, 8));
//        sheet.addMergedRegion(new CellRangeAddress(19, 19, 2, 8));
//        sheet.addMergedRegion(new CellRangeAddress(20, 20, 2, 8));
//        
//        xLine++;
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(bodyStyle);
//        cell.setCellValue("A favor del cliente");
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(3);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(4);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(5);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(6);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(7);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(bodyStyleRigth);
//        cell.setCellValue((double)oSaldoDeudor.getIsaldoFavor());
//        
//        xLine++;
//        rowHeader = sheet.createRow((short) xLine++);
//        cell = rowHeader.createCell(1);
//        cell.setCellStyle(bodyStyle);
//        cell.setCellValue("Total de la deuda");
//        cell = rowHeader.createCell(2);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(3);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(4);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(5);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(6);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(7);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(8);
//        cell.setCellStyle(bodyStyle);
//        cell = rowHeader.createCell(9);
//        cell.setCellStyle(bodyStyleRigth);
//        cell.setCellValue((double)oSaldoDeudor.getNtotDebe() + oSaldoDeudor.getIgastLegalFut() + oSaldoDeudor.getIgastAdmin());
//        
//        sheet.addMergedRegion(new CellRangeAddress(22, 22, 1, 8));
//        sheet.addMergedRegion(new CellRangeAddress(24, 24, 1, 8));
//        
//        workbook.write(byteOutputStream);
//        System.out.println(" <f> pop.webcobranzas.procesos.SessionSaldoDeudor.exportarSaldoDeudor() ");
//        return byteOutputStream.toByteArray();
//    }
//
//    
}
