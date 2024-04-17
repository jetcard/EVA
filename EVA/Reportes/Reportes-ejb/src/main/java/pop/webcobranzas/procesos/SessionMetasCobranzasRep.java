/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.procesos;


import java.io.ByteArrayOutputStream;

import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.List;
import javax.ejb.Stateless;
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.reporte.RepMetaCobranza;
import pop.comun.dominio.reporte.RepMetaRecaudo;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;
import pop.comun.dominio.CobCronogramaRecaudoDetalle;

/**
 *
 * @author EC23329
 */
@Stateless(mappedName = "ejbMetasCobranzasRep")
public class SessionMetasCobranzasRep implements IRepMetasCobranzas{
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    //@Override
    //public byte[] exportartRepMetasCobranzas(List<CobCronogramaMetaDetalle> oListCobCronogramaMetaDetalle) throws Exception {
    
    //final static Logger logger = Logger.getLogger(SessionMetasCobranzasRep.class);
    @Override
    public byte[] exportartRepMetasCobranzas(RepMetaCobranza oRepMetaCobranza) throws Exception {    
        try
        {
        
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();  
        
            
        List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaAgente1List;        
        List<CobCronogramaMetaResumen> cobCronogramaMetaResumenList;
        List<CobCronogramaMetaDetalle> cobCronogramaMetaDetalleList;        
       
            
        List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaAgente1ListJD;        
        List<CobCronogramaMetaResumen> cobCronogramaMetaResumenListJD;
            
        cobCronogramaMetaAgrupxFechaAgente1List = oRepMetaCobranza.getCobCronogramaMetaAgrupxFechaList();
        cobCronogramaMetaResumenList = oRepMetaCobranza.getCobCronogramaMetaResumenList();
        cobCronogramaMetaDetalleList = oRepMetaCobranza.getCobCronogramaMetaDetalleList();
        cobCronogramaMetaAgrupxFechaAgente1ListJD = oRepMetaCobranza.getCobCronogramaMetaAgrupxFechaListJD();
        cobCronogramaMetaResumenListJD = oRepMetaCobranza.getCobCronogramaMetaResumenListJD();
       
        //SXSSFWorkbook wb = new SXSSFWorkbook(-1);
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:\\pop\\webcobranzas\\xlt\\template_reporte_meta.xltx"));           
        FileOutputStream fileOut = new FileOutputStream("C:\\pop\\webcobranzas\\reporte_meta.xls"); 
   
            
        /*
        XSSFSheet sheet1 = wb.getSheet("Data");        
        XSSFRow row = sheet1.getRow(4);
        XSSFCell cell = row.getCell(2);
        cell.setCellValue(30000);
                
        cell = row.getCell(1);
        cell.setCellValue(30000);

        CellRangeAddress cellRangeAddress = new CellRangeAddress(8, oRepMetaCobranza.getCobCronogramaMetaDetalleList().size(), 1, 10);
        */
        
        XSSFSheet sheet1; 
        XSSFRow row;
        XSSFCell cell;
        int inR = 0;
        
        String sFecha;
        sheet1 = wb.getSheet("resumen");
        wb.setActiveSheet(0);
        sFecha = "01" + "/" + cobCronogramaMetaAgrupxFechaAgente1List.get(0).getN_mes() + "/" + cobCronogramaMetaAgrupxFechaAgente1List.get(0).getN_anio();
        row = sheet1.getRow(0);
        cell = row.getCell(4);
        cell.setCellValue(sFecha);
        
        //**********************************************************************************************************//
        //NO JUDICIALES ********************************************************************************************//
        //TOTAL POR FONDO ******************************************************************************************//
        //**********************************************************************************************************//
         //0001: FONDO CAPITAL EMPRENDEDOR
        
        
        // primer registro es soles 
          for (int i = 0; i < cobCronogramaMetaAgrupxFechaAgente1List.size(); ++i) {
              // capital
            if ( cobCronogramaMetaAgrupxFechaAgente1List.get(i).getC_fondo_id().equals("0001") ){
                    if (cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda().contains("SOLES") ){
                      row = sheet1.getRow(5);
                      cell = row.getCell(1);
                      cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda());
                      cell = row.getCell(2);
                      cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getCuotaMes()));
                      cell = row.getCell(3);
                      cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getTotalAtrasado()));
                   }
                  if (cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda().contains("DOLARES") ){
                      row = sheet1.getRow(6);
                      cell = row.getCell(1);
                      cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda());
                      cell = row.getCell(2);
                      cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getCuotaMes()));
                      cell = row.getCell(3);
                      cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getTotalAtrasado()));
                  }
             }
            //0002: FONDO POPULAR
            if ( cobCronogramaMetaAgrupxFechaAgente1List.get(i).getC_fondo_id().equals("0002") ){
                    if (cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda().contains("SOLES") ){
                        row = sheet1.getRow(7);
                        cell = row.getCell(1);
                        cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda());
                        cell = row.getCell(2);
                        cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getCuotaMes()));
                        cell = row.getCell(3);
                        cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getTotalAtrasado()));
                     }

                    if (cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda().contains("DOLARES")){
                        row = sheet1.getRow(8);
                        cell = row.getCell(1);
                        cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda());
                        cell = row.getCell(2);
                        cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getCuotaMes()));
                        cell = row.getCell(3);
                        cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getTotalAtrasado()));
                    }  

            }
            //0003: FONDO MYPE
            
            if ( cobCronogramaMetaAgrupxFechaAgente1List.get(i).getC_fondo_id().equals("0003") ){
                if (cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda().contains("SOLES")){
                    row = sheet1.getRow(9);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getCuotaMes()));
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getTotalAtrasado()));
                 }

                if (cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda().contains("DOLARES")){
                    row = sheet1.getRow(10);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getCuotaMes()));
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getTotalAtrasado()));
                }   
            }
            //0004: FONDO PEREZH
            if ( cobCronogramaMetaAgrupxFechaAgente1List.get(i).getC_fondo_id().equals("0004") ){
                if (cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda().contains("SOLES") ){
                    row = sheet1.getRow(11);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getCuotaMes()));
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getTotalAtrasado()));
                 }

                if (cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda().contains("DOLARES")){
                    row = sheet1.getRow(12);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getCuotaMes()));
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1List.get(i).getTotalAtrasado()));
                }   
            
          } 
          }
         //TOTAL:
            row = sheet1.getRow(13);
            cell = row.getCell(2);
            cell.setCellFormula("SUM(C6:C12)");

            row = sheet1.getRow(13);
            cell = row.getCell(3);
            cell.setCellFormula("SUM(D6:D12)");
             //**********************************************************************************************************//
            //TOTAL RESUMEN ********************************************************************************************//
            //**********************************************************************************************************//
            for (int e = 0; e < cobCronogramaMetaResumenList.size(); ++e) {
                //0001: FONDO CAPITAL EMPRENDEDOR
                // soles primer registro
            if ( cobCronogramaMetaResumenList.get(e).getC_fondo_id().equals("0001")){
                if (cobCronogramaMetaResumenList.get(e).getMoneda().contains("SOLES") ) {
                    row = sheet1.getRow(16);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getC_usuario_id());
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenList.get(e).getN_cant_tchn()));
                    cell = row.getCell(4);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_cuota());
                    cell = row.getCell(5);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_atrasado());
                    cell = row.getCell(6);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_otros());
                    cell = row.getCell(7);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total());
                }
                if (cobCronogramaMetaResumenList.get(e).getMoneda().contains("DOLARES")) {
                    row = sheet1.getRow(17);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getC_usuario_id());
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenList.get(e).getN_cant_tchn()));
                    cell = row.getCell(4);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_cuota());
                    cell = row.getCell(5);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_atrasado());
                    cell = row.getCell(6);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_otros());
                    cell = row.getCell(7);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total());
                }    
            }
             //0002: FONDO POPULAR  
            if ( cobCronogramaMetaResumenList.get(e).getC_fondo_id().equals("0002")){
             // soles PRIMER registro
                if (cobCronogramaMetaResumenList.get(e).getMoneda().contains("SOLES")){
                    row = sheet1.getRow(19);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getC_usuario_id());
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenList.get(e).getN_cant_tchn()));
                    cell = row.getCell(4);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_cuota());
                    cell = row.getCell(5);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_atrasado());
                    cell = row.getCell(6);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_otros());
                    cell = row.getCell(7);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total());
                }
                if (cobCronogramaMetaResumenList.get(e).getMoneda().contains("DOLARES") ){
                    row = sheet1.getRow(20);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getC_usuario_id());
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenList.get(e).getN_cant_tchn()));
                    cell = row.getCell(4);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_cuota());
                    cell = row.getCell(5);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_atrasado());
                    cell = row.getCell(6);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_otros());
                    cell = row.getCell(7);
                    cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total());
                }    
            }   
            if (cobCronogramaMetaResumenList.get(e).getC_fondo_id().equals("0003") ){
                    //0003: FONDO MYPE   
                     // soles PRIMER registro
                   if (cobCronogramaMetaResumenList.get(e).getMoneda().contains("SOLES") ){
                       row = sheet1.getRow(22);
                       cell = row.getCell(1);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getMoneda());
                       cell = row.getCell(2);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getC_usuario_id());
                       cell = row.getCell(3);
                       cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenList.get(e).getN_cant_tchn()));
                       cell = row.getCell(4);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_cuota());
                       cell = row.getCell(5);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_atrasado());
                       cell = row.getCell(6);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_otros());
                       cell = row.getCell(7);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total());
                   }
                   if (cobCronogramaMetaResumenList.get(e).getMoneda().contains("DOLARES")){
                       row = sheet1.getRow(23);
                       cell = row.getCell(1);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getMoneda());
                       cell = row.getCell(2);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getC_usuario_id());
                       cell = row.getCell(3);
                       cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenList.get(e).getN_cant_tchn()));
                       cell = row.getCell(4);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_cuota());
                       cell = row.getCell(5);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_atrasado());
                       cell = row.getCell(6);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_otros());
                       cell = row.getCell(7);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total());
                   }    
            }      
   
            if (cobCronogramaMetaResumenList.get(e).getC_fondo_id().equals("0004") ){
                    //0004: FONDO PEREZH
                     // soles PRIMER registro
                   if (cobCronogramaMetaResumenList.get(e).getMoneda().contains("SOLES") ){
                       row = sheet1.getRow(25);
                       cell = row.getCell(1);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getMoneda());
                       cell = row.getCell(2);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getC_usuario_id());
                       cell = row.getCell(3);
                       cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenList.get(e).getN_cant_tchn()));
                       cell = row.getCell(4);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_cuota());
                       cell = row.getCell(5);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_atrasado());
                       cell = row.getCell(6);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_otros());
                       cell = row.getCell(7);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total());
                   }
                   if (cobCronogramaMetaResumenList.get(e).getMoneda().contains("DOLARES")){
                       row = sheet1.getRow(26);
                       cell = row.getCell(1);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getMoneda());
                       cell = row.getCell(2);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getC_usuario_id());
                       cell = row.getCell(3);
                       cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenList.get(e).getN_cant_tchn()));
                       cell = row.getCell(4);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_cuota());
                       cell = row.getCell(5);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_atrasado());
                       cell = row.getCell(6);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total_otros());
                       cell = row.getCell(7);
                       cell.setCellValue(cobCronogramaMetaResumenList.get(e).getI_total());
                   }    
            }      
   
        }    


            row = sheet1.getRow(18);
            cell = row.getCell(3);
            cell.setCellFormula("SUM(D16:D17)");

            cell = row.getCell(4);
            cell.setCellFormula("SUM(E16:E17)");

            cell = row.getCell(5);
            cell.setCellFormula("SUM(F16:F17)");

            cell = row.getCell(6);
            cell.setCellFormula("SUM(G16:G17)");

            cell = row.getCell(7);
            cell.setCellFormula("SUM(H16:H17)");
            
            
            row = sheet1.getRow(21);
            cell = row.getCell(3);
            cell.setCellFormula("SUM(D19:D20)");

            cell = row.getCell(4);
            cell.setCellFormula("SUM(E19:E20)");

            cell = row.getCell(5);
            cell.setCellFormula("SUM(F19:F20)");

            cell = row.getCell(6);
            cell.setCellFormula("SUM(G19:G20)");

            cell = row.getCell(7);
            cell.setCellFormula("SUM(H19:H20)");


           

            row = sheet1.getRow(24);
            cell = row.getCell(3);
            cell.setCellFormula("SUM(D22:D23)");

            cell = row.getCell(4);
            cell.setCellFormula("SUM(E22:E23)");

            cell = row.getCell(5);
            cell.setCellFormula("SUM(F22:F23)");

            cell = row.getCell(6);
            cell.setCellFormula("SUM(G22:G23)");

            cell = row.getCell(7);
            cell.setCellFormula("SUM(H22:H23)");


            row = sheet1.getRow(27);
            cell = row.getCell(3);
            cell.setCellFormula("SUM(D25:D26)");

            cell = row.getCell(4);
            cell.setCellFormula("SUM(D25:D26)");

            cell = row.getCell(5);
            cell.setCellFormula("SUM(D25:D26)");

            cell = row.getCell(6);
            cell.setCellFormula("SUM(D25:D26)");

            cell = row.getCell(7);
            cell.setCellFormula("SUM(D25:D26)");

      
            //**********************************************************************************************************//
            //JUDICIALES ***********************************************************************************************//
            //TOTAL POR FONDO ******************************************************************************************//
            //**********************************************************************************************************//
        //0001: FONDO CAPITAL EMPRENDEDOR
     
        for (int j = 0; j < cobCronogramaMetaAgrupxFechaAgente1ListJD.size(); ++j) {
            // primero registro
            if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getC_fondo_id().equals("0001")){
                if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes().equals("SOLES")  ){
                    row = sheet1.getRow(34);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes()));
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getTotalAtrasado()));
                }
                if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes().equals("DOLARES")){
                    row = sheet1.getRow(35);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes()));
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getTotalAtrasado()));
                }    
            }
            if ( cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getC_fondo_id().equals("0002")){
                //0002: FONDO POPULAR
                // primero registro
                if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes().equals("SOLES") ){
                    row = sheet1.getRow(36);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes()));
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getTotalAtrasado()));
                }
                if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes().equals("DOLARES")){
                    row = sheet1.getRow(37);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes()));
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getTotalAtrasado()));
                }    
            }
            if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getC_fondo_id().equals("0003")){
                    // segundo registro
                    //0003: FONDO MYP
                    if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes().equals("SOLES")  ){
                        row = sheet1.getRow(38);
                        cell = row.getCell(1);
                        cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getMoneda());
                        cell = row.getCell(2);
                        cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes()));
                        cell = row.getCell(3);
                        cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getTotalAtrasado()));
                    }
                    if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes().equals("DOLARES")){
                        row = sheet1.getRow(39);
                        cell = row.getCell(1);
                        cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getMoneda());
                        cell = row.getCell(2);
                        cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes()));
                        cell = row.getCell(3);
                        cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getTotalAtrasado()));
                    }    
            }
           if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getC_fondo_id().equals("0004")){
            //0004: FONDO PEREZH
            // primero registro
                if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes().equals("SOLES") ){
                    row = sheet1.getRow(40);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes()));
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getTotalAtrasado()));
                }
                if (cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes().equals("DOLARES") ){
                    row = sheet1.getRow(41);
                    cell = row.getCell(1);
                    cell.setCellValue(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getMoneda());
                    cell = row.getCell(2);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getCuotaMes()));
                    cell = row.getCell(3);
                    cell.setCellValue(Double.parseDouble(cobCronogramaMetaAgrupxFechaAgente1ListJD.get(j).getTotalAtrasado()));
                }    
             }   

        }  
            // segundo registro
            //TOTAL:
            row = sheet1.getRow(42);
            cell = row.getCell(1);
            cell.setCellFormula("SUM(C34:C41)");

            row = sheet1.getRow(33);
            cell = row.getCell(2);
            cell.setCellFormula("SUM(D34:D41)");
           
            //**********************************************************************************************************//
            //TOTAL RESUMEN ********************************************************************************************//
            //**********************************************************************************************************//
         for (int i = 0; i < cobCronogramaMetaResumenListJD.size(); ++i) {
            //0001: FONDO CAPITAL EMPRENDEDOR
            if (cobCronogramaMetaResumenListJD.get(i).getC_fondo_id().equals("0001")){
                if (cobCronogramaMetaResumenListJD.get(i).getMoneda().contains("SOLES") ){
                        row = sheet1.getRow(45);
                        cell = row.getCell(1);
                        cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getMoneda());
                        cell = row.getCell(2);
                        cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getC_est_tchn());
                        cell = row.getCell(3);
                        cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenListJD.get(i).getN_cant_tchn()));
                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getI_total());

                }
                if (cobCronogramaMetaResumenListJD.get(i).getMoneda().contains("DOLARES") ){
                        row = sheet1.getRow(46);
                        cell = row.getCell(1);
                        cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getMoneda());
                        cell = row.getCell(2);
                        cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getC_est_tchn()); 
                        cell = row.getCell(3);
                        cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenListJD.get(i).getN_cant_tchn()));
                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getI_total());
                 }
            }
            if (cobCronogramaMetaResumenListJD.get(i).getC_fondo_id().equals("0002")){
                //0002: FONDO POPULAR        
                  if (cobCronogramaMetaResumenListJD.get(i).getMoneda().contains("SOLES") ){
                           row = sheet1.getRow(48);
                           cell = row.getCell(1);
                           cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getMoneda());
                           cell = row.getCell(2);
                           cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getC_est_tchn());
                           cell = row.getCell(3);
                           cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenListJD.get(i).getN_cant_tchn()));
                           cell = row.getCell(4);
                           cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getI_total());
                   }
                  if (cobCronogramaMetaResumenListJD.get(i).getMoneda().contains("DOLARES")){ 
                           row = sheet1.getRow(49);
                           cell = row.getCell(1);
                           cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getMoneda());
                           cell = row.getCell(2);
                           cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getC_est_tchn()); 
                           cell = row.getCell(3);
                           cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenListJD.get(i).getN_cant_tchn()));
                           cell = row.getCell(4);
                           cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getI_total());
                  }
            }
            
            if (cobCronogramaMetaResumenListJD.get(i).getC_fondo_id().equals("0003")){
                        //0003: FONDO MYPE        
                    if (cobCronogramaMetaResumenListJD.get(i).getMoneda().contains("SOLES")  ){
                            row = sheet1.getRow(51);
                            cell = row.getCell(1);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getMoneda());
                            cell = row.getCell(2);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getC_est_tchn());
                            cell = row.getCell(3);
                            cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenListJD.get(i).getN_cant_tchn()));
                            cell = row.getCell(4);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getI_total());
                    }
                    if (cobCronogramaMetaResumenListJD.get(i).getMoneda().contains("DOLARES")){
                            row = sheet1.getRow(52);
                            cell = row.getCell(1);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getMoneda());
                            cell = row.getCell(2);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getC_est_tchn()); 
                            cell = row.getCell(3);
                            cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenListJD.get(i).getN_cant_tchn()));
                            cell = row.getCell(4);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getI_total());
                     }
            }
            if (cobCronogramaMetaResumenListJD.get(i).getC_fondo_id().equals("0004")){
                        //0004: FONDO PEREZ HIDALGO
                    if (cobCronogramaMetaResumenListJD.get(i).getMoneda().contains("SOLES") ){
                            row = sheet1.getRow(54);
                            cell = row.getCell(1);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getMoneda());
                            cell = row.getCell(2);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getC_est_tchn());
                            cell = row.getCell(3);
                            cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenListJD.get(i).getN_cant_tchn()));
                            cell = row.getCell(4);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getI_total());

                    }
                    if (cobCronogramaMetaResumenListJD.get(i).getMoneda().contains("DOLARES") ){
                            row = sheet1.getRow(55);
                            cell = row.getCell(1);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getMoneda());
                            cell = row.getCell(2);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getC_est_tchn()); 
                            cell = row.getCell(3);
                            cell.setCellValue(Double.parseDouble(cobCronogramaMetaResumenListJD.get(i).getN_cant_tchn()));
                            cell = row.getCell(4);
                            cell.setCellValue(cobCronogramaMetaResumenListJD.get(i).getI_total());
                       }
            }         

        }    
           
            row = sheet1.getRow(47);
            cell = row.getCell(3);
            cell.setCellFormula("SUM(D46:D47)");

            cell = row.getCell(4);
            cell.setCellFormula("SUM(E46:E47)");

            row = sheet1.getRow(50);
            cell = row.getCell(3);
            cell.setCellFormula("SUM(D49:D50)");

            cell = row.getCell(4);
            cell.setCellFormula("SUM(E49:E50)");

            row = sheet1.getRow(53);
            cell = row.getCell(3);
            cell.setCellFormula("SUM(D52:D53)");

            cell = row.getCell(4);
            cell.setCellFormula("SUM(E52:E53)");
        
            row = sheet1.getRow(56);
            cell = row.getCell(3);
            cell.setCellFormula("SUM(D55:D56)");

            cell = row.getCell(4);
            cell.setCellFormula("SUM(E55:E56)");

        
        wb.write(byteOutputStream);    
        wb.close();
        
        byteOutputStream.writeTo(fileOut);
        fileOut.close();
        return byteOutputStream.toByteArray();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        catch (Exception e)
        {
            return null;
        }
        
    }
    
    @Override
    public byte[] exportartRepMetasRecaudo(RepMetaRecaudo oRepMetaRecaudo) throws Exception {    
        int inR = 0;
        int inC = 0;
        
        int iPg = 0;
        int iPf = 0;    
        int iPe = 0;  
        int iInc = 0;
        
        int iRow = 0;
        int iCol = 0;   
        int iRows = 0;
        int iCols = 0;  
        int iRowd = 0;
        int iCold = 0;  
        int iFond = 0;
        int iAux = 0;
        
        String sFecha;
        Date fFecha;
                
        try
        {        
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();  

            List<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList;
            cobCronogramaRecaudoDetalleList = oRepMetaRecaudo.getCobCronogramaRecaudoDetalleList();

            List<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleJDList;
            cobCronogramaRecaudoDetalleJDList = oRepMetaRecaudo.getCobCronogramaRecaudoDetalleJDList();
            
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:\\pop\\webcobranzas\\xlt\\template_avance_recaudacion.xltx"));           
            FileOutputStream fileOut = new FileOutputStream("C:\\pop\\webcobranzas\\avance_recaudacion.xls"); 

            XSSFSheet sheet1; 
            XSSFRow row;
            XSSFCell cell;   

            sheet1 = wb.getSheet("Dashboard");
            wb.setActiveSheet(0);
            
            sFecha = "01/" + cobCronogramaRecaudoDetalleList.get(0).getN_mes() + "/" + cobCronogramaRecaudoDetalleList.get(0).getN_anio();
            fFecha = new SimpleDateFormat("dd/MM/yyyy").parse(sFecha); 
            row = sheet1.getRow(1);
            cell = row.getCell(2);
            cell.setCellValue(fFecha);
                        
            row = sheet1.getRow(2);
            cell = row.getCell(2);
            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(0).getF_fecha());
            
            row = sheet1.getRow(1);
            cell = row.getCell(14);
            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(0).getI_dias_avance());
            
            row = sheet1.getRow(2);
            cell = row.getCell(14);
            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(0).getI_dias_recaudo());
            
            row = sheet1.getRow(2);
            cell = row.getCell(15);
            cell.setCellFormula("o2/o3");
            
            row = sheet1.getRow(8);
            cell = row.getCell(4);
            cell.setCellFormula("c3");
            
            row = sheet1.getRow(8);
            cell = row.getCell(5);
            cell.setCellFormula("EDATE(c3,-1)");
            
            row = sheet1.getRow(24);
            cell = row.getCell(4);
            cell.setCellFormula("e9");
            
            row = sheet1.getRow(24);
            cell = row.getCell(5);
            cell.setCellFormula("f9");
            
            /*************************************************************************************/
            //INICIO: NO JUDICIAL
            /*************************************************************************************/

            /************************************************/
            //I. POSICION GLOBAL
            /************************************************/        
            int in =0;
            for (iPg = 0; iPg < cobCronogramaRecaudoDetalleList.size(); ++iPg) {
                if (cobCronogramaRecaudoDetalleList.get(iPg).getI_posicion()== 1)
                {
                    //a.Normal
                  if (cobCronogramaRecaudoDetalleList.get(iPg).getC_fondo_id().equals("0001") && cobCronogramaRecaudoDetalleList.get(iPg).getMoneda().trim().equals("SOLES") )
                    {
                        row = sheet1.getRow(9);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo_ant());    
                    
                    }
                  if (cobCronogramaRecaudoDetalleList.get(iPg).getC_fondo_id().equals("0001") && cobCronogramaRecaudoDetalleList.get(iPg).getMoneda().trim().equals("DOLARES") )
                    {
                        row = sheet1.getRow(10);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo_ant());    

                    }
                  if (cobCronogramaRecaudoDetalleList.get(iPg).getC_fondo_id().equals("0003") && cobCronogramaRecaudoDetalleList.get(iPg).getMoneda().trim().equals("SOLES") )
                    {
                        row = sheet1.getRow(11);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo_ant());    

                    }

                    if (cobCronogramaRecaudoDetalleList.get(iPg).getC_fondo_id().equals("0003") && cobCronogramaRecaudoDetalleList.get(iPg).getMoneda().trim().equals("DOLARES") )
                    {
                        row = sheet1.getRow(12);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo_ant());    

                    }

                    if (cobCronogramaRecaudoDetalleList.get(iPg).getC_fondo_id().equals("0002") && cobCronogramaRecaudoDetalleList.get(iPg).getMoneda().trim().equals("SOLES") )
                    {
                        row = sheet1.getRow(13);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo_ant());    

                    }

                    if (cobCronogramaRecaudoDetalleList.get(iPg).getC_fondo_id().equals("0002") && cobCronogramaRecaudoDetalleList.get(iPg).getMoneda().trim().equals("DOLARES") )
                    {
                        row = sheet1.getRow(14);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo_ant());    

                    }
           
                    if (cobCronogramaRecaudoDetalleList.get(iPg).getC_fondo_id().equals("0004") && cobCronogramaRecaudoDetalleList.get(iPg).getMoneda().trim().equals("SOLES") )
                    {
                        row = sheet1.getRow(15);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo_ant());    

                    }

                    if (cobCronogramaRecaudoDetalleList.get(iPg).getC_fondo_id().equals("0004") && cobCronogramaRecaudoDetalleList.get(iPg).getMoneda().trim().equals("DOLARES") )
                    {
                        row = sheet1.getRow(16);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(iPg).getI_recaudo_ant());    

                    }
       
                }
                
                if (cobCronogramaRecaudoDetalleList.get(iPg).getI_posicion()== 2)
                {
                    
                    break;
                }
                in++;
            }
            
            /************************************************/
            //II. POSICION POR FONDO
            /************************************************/   
            iInc = 2;
               
                for (inC = 1; inC < 3; inC++){            
                    if (inC == 1){
                        iRows = 43;
                        iCols = 9;
                        iRowd = 47;
                        iCold = 9; 
                    }
                    else
                    {
                        iRows = 55;
                        iCols = 7;
                        iRowd = 59;
                        iCold = 7;
                    }
             
                for (iPf = 3; iPf < iCols; iPf += iInc){
                    
                    if (cobCronogramaRecaudoDetalleList.get(in).getI_posicion() == 2)
                    {
                        
                      if (inC== 1 && cobCronogramaRecaudoDetalleList.get(in).getC_fondo_id().equals("0001") )                                                
                      {
                         
                          if (cobCronogramaRecaudoDetalleList.get(in).getMoneda().trim().equals("SOLES")  ){
                            row = sheet1.getRow(iRows+1);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_meta());

                            row = sheet1.getRow(iRows+2);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo());

                            row = sheet1.getRow(iRows+3);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo_ase()); 

                            row = sheet1.getRow(iRows+4);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo_cli());
                        }
                        if ( cobCronogramaRecaudoDetalleList.get(in).getMoneda().trim().equals("DOLARES")  ){
                            row = sheet1.getRow(iRowd+1);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_meta());

                            row = sheet1.getRow(iRowd+2);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo());

                            row = sheet1.getRow(iRowd+3);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo_ase()); 

                            row = sheet1.getRow(iRowd+4);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo_cli());
                          }
                      }
                      if (inC== 2 && cobCronogramaRecaudoDetalleList.get(in).getC_fondo_id().equals("0002") )                                                
                      {
                          if (cobCronogramaRecaudoDetalleList.get(in).getMoneda().trim().equals("SOLES")  ){
                            row = sheet1.getRow(iRows+1);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_meta());

                            row = sheet1.getRow(iRows+2);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo());

                            row = sheet1.getRow(iRows+3);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo_ase()); 

                            row = sheet1.getRow(iRows+4);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo_cli());
                        }
                        if (cobCronogramaRecaudoDetalleList.get(in).getMoneda().trim().equals("DOLARES")  ){
                            row = sheet1.getRow(iRowd+1);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_meta());

                            row = sheet1.getRow(iRowd+2);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo());

                            row = sheet1.getRow(iRowd+3);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo_ase()); 

                            row = sheet1.getRow(iRowd+4);
                            cell = row.getCell(iPf);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(in).getI_recaudo_cli());
                          }
                      }
                    in++;
                    }else{
                        break;
                    }
                    
                }
                
               }
            
               for (int a1 = 0; a1 < cobCronogramaRecaudoDetalleList.size(); a1++){
             
                if (cobCronogramaRecaudoDetalleList.get(a1).getI_posicion() == 2)
                {                
                   if ( cobCronogramaRecaudoDetalleList.get(a1).getC_fondo_id().equals("0003") )                                                
                      {
                   
                        if (cobCronogramaRecaudoDetalleList.get(a1).getMoneda().trim().equals("SOLES") ){

                             row = sheet1.getRow(44);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_meta());

                             row = sheet1.getRow(45);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo());

                             row = sheet1.getRow(46);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo_ase()); 

                             row = sheet1.getRow(47);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo_cli());                
                          }

                        if (cobCronogramaRecaudoDetalleList.get(a1).getMoneda().trim().equals("DOLARES") && cobCronogramaRecaudoDetalleList.get(a1).getC_fondo_id().equals("000"+inC) ){

                             row = sheet1.getRow(48);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_meta());

                             row = sheet1.getRow(49);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo());

                             row = sheet1.getRow(50);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo_ase()); 

                             row = sheet1.getRow(51);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo_cli());                
                          }
                      }
                    if (cobCronogramaRecaudoDetalleList.get(a1).getC_fondo_id().equals("0004") )                                                
                      {
                   
                        if (cobCronogramaRecaudoDetalleList.get(a1).getMoneda().trim().equals("SOLES") ){

                             row = sheet1.getRow(56);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_meta());

                             row = sheet1.getRow(57);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo());

                             row = sheet1.getRow(58);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo_ase()); 

                             row = sheet1.getRow(59);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo_cli());                
                          }

                        if (cobCronogramaRecaudoDetalleList.get(a1).getMoneda().trim().equals("DOLARES") && cobCronogramaRecaudoDetalleList.get(a1).getC_fondo_id().equals("000"+inC) ){

                             row = sheet1.getRow(60);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_meta());

                             row = sheet1.getRow(61);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo());

                             row = sheet1.getRow(62);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo_ase()); 

                             row = sheet1.getRow(63);
                             cell = row.getCell(13);
                             cell.setCellValue(cobCronogramaRecaudoDetalleList.get(a1).getI_recaudo_cli());                
                          }
                      }

                }
                
                       
            }

            
            /************************************************/
            //III. POSICION ESTADO I
            /************************************************/ 
                
            for (iFond = 1; iFond < 5; iFond++){            
               for (int inpos = 0; inpos < cobCronogramaRecaudoDetalleList.size(); inpos ++){
                if (cobCronogramaRecaudoDetalleList.get(inpos).getI_posicion() == 3){
                    if (iFond == 1 && cobCronogramaRecaudoDetalleList.get(inpos).getC_fondo_id().contains("0001")){
                        iPe = 3;
                    }
                    else if (iFond == 2 && cobCronogramaRecaudoDetalleList.get(inpos).getC_fondo_id().contains("0002")){
                        iPe = 7;
                    }
                    else if (iFond == 3 && cobCronogramaRecaudoDetalleList.get(inpos).getC_fondo_id().contains("0003")){
                        iPe = 5;
                    }
                    else if (iFond == 4 && cobCronogramaRecaudoDetalleList.get(inpos).getC_fondo_id().contains("0004")){
                        iPe = 9;
                    }
                    else{
                        iPe = 0;
                    }
                    if (cobCronogramaRecaudoDetalleList.get(inpos).getMoneda().trim().equals("SOLES")){
                        if (iPe > 0){
                            row = sheet1.getRow(83);
                            cell = row.getCell(iPe);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(inpos).getI_cliente());

                            row = sheet1.getRow(83);
                            cell = row.getCell(iPe+1);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(inpos).getI_cobranza());
                        }
                    }
                    if (cobCronogramaRecaudoDetalleList.get(inpos).getMoneda().trim().equals("DOLARES")){
                        if (iPe > 0){
                            row = sheet1.getRow(85);
                            cell = row.getCell(iPe);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(inpos).getI_cliente());

                            row = sheet1.getRow(85);
                            cell = row.getCell(iPe+1);
                            cell.setCellValue(cobCronogramaRecaudoDetalleList.get(inpos).getI_cobranza());
                        
                        }
                    }
                } 
                }

            }            

            /************************************************/
            //III. POSICION ESTADO II
            /************************************************/ 
  
            for (inC = 1; inC < cobCronogramaRecaudoDetalleList.size(); inC++)
            {
              
                if (cobCronogramaRecaudoDetalleList.get(inC).getI_posicion() == 4){

                    if (cobCronogramaRecaudoDetalleList.get(inC).getC_fondo_id().contains("0001")){
                        iRows = 95;
                        iRowd = 97;
                        
                    }
                    if (cobCronogramaRecaudoDetalleList.get(inC).getC_fondo_id().contains("0002")){
                        iRows = 113;
                        iRowd = 115;
                    }
                    if (cobCronogramaRecaudoDetalleList.get(inC).getC_fondo_id().contains("0003")){
                        iRows = 104;
                        iRowd = 106;
                    }
                    
                    if (cobCronogramaRecaudoDetalleList.get(inC).getC_fondo_id().contains("0004")){
                        iRows = 122;
                        iRowd = 124;
                    }

                    if (cobCronogramaRecaudoDetalleList.get(inC).getC_concepto().contains("09")){
                        iPe = 3;
                    }
                    if (cobCronogramaRecaudoDetalleList.get(inC).getC_concepto().contains("10")){
                        iPe = 5;
                    }
                    if (cobCronogramaRecaudoDetalleList.get(inC).getC_concepto().contains("11")){
                        iPe = 7;
                    }
                    if (cobCronogramaRecaudoDetalleList.get(inC).getMoneda().trim().equals("SOLES")){
                        row = sheet1.getRow(iRows);
                        cell = row.getCell(iPe);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(inC).getI_cantidad());

                        row = sheet1.getRow(iRows);
                        cell = row.getCell(iPe+1);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(inC).getI_deposito());
                    }   
                     if (cobCronogramaRecaudoDetalleList.get(inC).getMoneda().trim().equals("DOLARES")){
                        row = sheet1.getRow(iRowd);
                        cell = row.getCell(iPe);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(inC).getI_cantidad());

                        row = sheet1.getRow(iRowd);
                        cell = row.getCell(iPe+1);
                        cell.setCellValue(cobCronogramaRecaudoDetalleList.get(inC).getI_deposito());
                    }   
                }
            }
            /*************************************************************************************/
            //FIN: NO JUDICIAL
            /*************************************************************************************/
            

            inR = 0;
            inC = 0;

            iPg = 0;
            iPf = 0;    
            iPe = 0;  
            iInc = 0;
            iRow = 0;
            iCol = 0;        
            iFond = 0;

            /*************************************************************************************/
            //INICIO: JUDICIAL
            /*************************************************************************************/
    
            /************************************************/
            //I. POSICION GLOBAL
            /************************************************/        
     
            for (iPg = 0; iPg < cobCronogramaRecaudoDetalleJDList.size(); ++iPg) {
                if (cobCronogramaRecaudoDetalleJDList.get(iPg).getI_posicion()== 1)
                {
                    //a.Normal
                  if (cobCronogramaRecaudoDetalleJDList.get(iPg).getC_fondo_id().equals("0003") && cobCronogramaRecaudoDetalleJDList.get(iPg).getMoneda().trim().equals("SOLES") )
                    {
                        row = sheet1.getRow(25);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo_ant());    
                    
                    }
                  if (cobCronogramaRecaudoDetalleJDList.get(iPg).getC_fondo_id().equals("0003") && cobCronogramaRecaudoDetalleJDList.get(iPg).getMoneda().trim().equals("DOLARES") )
                    {
                        row = sheet1.getRow(26);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo_ant());    

                    }
                  if (cobCronogramaRecaudoDetalleJDList.get(iPg).getC_fondo_id().equals("0004") && cobCronogramaRecaudoDetalleJDList.get(iPg).getMoneda().trim().equals("SOLES") )
                    {
                        row = sheet1.getRow(27);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo_ant());    

                    }

                    if (cobCronogramaRecaudoDetalleJDList.get(iPg).getC_fondo_id().equals("0004") && cobCronogramaRecaudoDetalleJDList.get(iPg).getMoneda().trim().equals("DOLARES") )
                    {
                        row = sheet1.getRow(28);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo_ant());    

                    }

                    if (cobCronogramaRecaudoDetalleJDList.get(iPg).getC_fondo_id().equals("0002") && cobCronogramaRecaudoDetalleJDList.get(iPg).getMoneda().trim().equals("SOLES") )
                    {
                        row = sheet1.getRow(29);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo_ant());    

                    }

                    if (cobCronogramaRecaudoDetalleList.get(iPg).getC_fondo_id().equals("0002") && cobCronogramaRecaudoDetalleList.get(iPg).getMoneda().trim().equals("DOLARES") )
                    {
                        row = sheet1.getRow(30);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo_ant());    

                    }
           
                    if (cobCronogramaRecaudoDetalleJDList.get(iPg).getC_fondo_id().equals("0001") && cobCronogramaRecaudoDetalleJDList.get(iPg).getMoneda().trim().equals("SOLES") )
                    {
                        row = sheet1.getRow(31);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo_ant());    

                    }

                    if (cobCronogramaRecaudoDetalleJDList.get(iPg).getC_fondo_id().equals("0001") && cobCronogramaRecaudoDetalleJDList.get(iPg).getMoneda().trim().equals("DOLARES") )
                    {
                        row = sheet1.getRow(32);
                        cell = row.getCell(3);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_meta());

                        cell = row.getCell(4);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo());

                        cell = row.getCell(5);
                        cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(iPg).getI_recaudo_ant());    

                    }

                        
                }
            
            }
            

            /************************************************/
            //II. POSICION POR FONDO
            /************************************************/ 
       for (int jd = 0; jd < cobCronogramaRecaudoDetalleJDList.size(); jd ++){  
             
              
            iRows = 68 ;
            iRowd = 72 ;
            iAux = 0;
            for (iPf = 3; iPf < 7; iPf ++){                
                inR++;
                if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_posicion() == 2)
                {                   
                    if (cobCronogramaRecaudoDetalleJDList.get(jd).getC_fondo_id().contains("0001") && cobCronogramaRecaudoDetalleJDList.get(jd).getMoneda().contains("SOLES"))
                    {
                        row = sheet1.getRow(iRows+1);
                        cell = row.getCell(3);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta() >0) {cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta());}

                        row = sheet1.getRow(iRows+2);
                        cell = row.getCell(3);
                        if(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo() >0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo());}

                        row = sheet1.getRow(iRows+3);
                        cell = row.getCell(3);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase());}                        

                        row = sheet1.getRow(iRows+4);
                        cell = row.getCell(3);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli());}
                  
                    }
                    
                    if (cobCronogramaRecaudoDetalleJDList.get(jd).getC_fondo_id().contains("0001")  && cobCronogramaRecaudoDetalleJDList.get(jd).getMoneda().contains("DOLARES"))
                    { 
                        row = sheet1.getRow(iRowd+1);
                        cell = row.getCell(3);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta() >0) {cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta());}

                        row = sheet1.getRow(iRowd+2);
                        cell = row.getCell(3);
                        if(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo() >0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo());}

                        row = sheet1.getRow(iRowd+3);
                        cell = row.getCell(3);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase());}                        

                        row = sheet1.getRow(iRowd+4);
                        cell = row.getCell(3);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli());}
                   }
                
                  
                      if (cobCronogramaRecaudoDetalleJDList.get(jd).getC_fondo_id().contains("0003") && cobCronogramaRecaudoDetalleJDList.get(jd).getMoneda().contains("SOLES"))
                    {
                        row = sheet1.getRow(iRows+1);
                        cell = row.getCell(4);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta() >0) {cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta());}

                        row = sheet1.getRow(iRows+2);
                        cell = row.getCell(4);
                        if(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo() >0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo());}

                        row = sheet1.getRow(iRows+3);
                        cell = row.getCell(4);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase());}                        

                        row = sheet1.getRow(iRows+4);
                        cell = row.getCell(4);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli());}
                  
                    }
                    
                    if (cobCronogramaRecaudoDetalleJDList.get(jd).getC_fondo_id().contains("0003")  && cobCronogramaRecaudoDetalleJDList.get(jd).getMoneda().contains("DOLARES"))
                    { 
                        row = sheet1.getRow(iRowd+1);
                        cell = row.getCell(4);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta() >0) {cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta());}

                        row = sheet1.getRow(iRowd+2);
                        cell = row.getCell(4);
                        if(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo() >0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo());}

                        row = sheet1.getRow(iRowd+3);
                        cell = row.getCell(4);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase());}                        

                        row = sheet1.getRow(iRowd+4);
                        cell = row.getCell(4);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli());}
                   }
                
                    
                      if (cobCronogramaRecaudoDetalleJDList.get(jd).getC_fondo_id().contains("0002") && cobCronogramaRecaudoDetalleJDList.get(jd).getMoneda().contains("SOLES"))
                    {
                        row = sheet1.getRow(iRows+1);
                        cell = row.getCell(5);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta() >0) {cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta());}

                        row = sheet1.getRow(iRows+2);
                        cell = row.getCell(5);
                        if(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo() >0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo());}

                        row = sheet1.getRow(iRows+3);
                        cell = row.getCell(5);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase());}                        

                        row = sheet1.getRow(iRows+4);
                        cell = row.getCell(5);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli());}
                  
                    }
                    
                    if (cobCronogramaRecaudoDetalleJDList.get(jd).getC_fondo_id().contains("0002")  && cobCronogramaRecaudoDetalleJDList.get(jd).getMoneda().contains("DOLARES"))
                    { 
                        row = sheet1.getRow(iRowd+1);
                        cell = row.getCell(5);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta() >0) {cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta());}

                        row = sheet1.getRow(iRowd+2);
                        cell = row.getCell(5);
                        if(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo() >0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo());}

                        row = sheet1.getRow(iRowd+3);
                        cell = row.getCell(5);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase());}                        

                        row = sheet1.getRow(iRowd+4);
                        cell = row.getCell(5);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli());}
                   }
                    
                      if (cobCronogramaRecaudoDetalleJDList.get(jd).getC_fondo_id().contains("0004") && cobCronogramaRecaudoDetalleJDList.get(jd).getMoneda().contains("SOLES"))
                    {
                        row = sheet1.getRow(iRows+1);
                        cell = row.getCell(6);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta() >0) {cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta());}

                        row = sheet1.getRow(iRows+2);
                        cell = row.getCell(6);
                        if(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo() >0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo());}

                        row = sheet1.getRow(iRows+3);
                        cell = row.getCell(6);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase());}                        

                        row = sheet1.getRow(iRows+4);
                        cell = row.getCell(6);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli());}
                  
                    }
                    
                    if (cobCronogramaRecaudoDetalleJDList.get(jd).getC_fondo_id().contains("0004")  && cobCronogramaRecaudoDetalleJDList.get(jd).getMoneda().contains("DOLARES"))
                    { 
                        row = sheet1.getRow(iRowd+1);
                        cell = row.getCell(6);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta() >0) {cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_meta());}

                        row = sheet1.getRow(iRowd+2);
                        cell = row.getCell(6);
                        if(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo() >0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo());}

                        row = sheet1.getRow(iRowd+3);
                        cell = row.getCell(6);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_ase());}                        

                        row = sheet1.getRow(iRowd+4);
                        cell = row.getCell(6);
                        if (cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli() > 0){cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(jd).getI_recaudo_cli());}
                   }
                  }
            }            
               

            }
     
            
            /************************************************/
            //III. POSICION ESTADO I
            /************************************************/ 
     
            for (int al = 1; al < cobCronogramaRecaudoDetalleJDList.size(); al++){           
                if (cobCronogramaRecaudoDetalleJDList.get(al).getI_posicion() == 3){
                    if (cobCronogramaRecaudoDetalleJDList.get(al).getC_fondo_id().contains("0001")){
                        iPe = 3;
                    }
                    else if ( cobCronogramaRecaudoDetalleJDList.get(al).getC_fondo_id().contains("0002")){
                        iPe = 7;
                    }
                    else if (cobCronogramaRecaudoDetalleJDList.get(al).getC_fondo_id().contains("0003")){
                        iPe = 5;
                    }
                    else if ( cobCronogramaRecaudoDetalleJDList.get(al).getC_fondo_id().contains("0004")){
                        iPe = 9;
                    }
                    else{
                        iPe = 0;
                    }
                    if (cobCronogramaRecaudoDetalleJDList.get(al).getMoneda().contains("SOLES")){
                        if (iPe > 0){
                            row = sheet1.getRow(84);
                            cell = row.getCell(iPe);
                            cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(al).getI_cliente());

                            row = sheet1.getRow(84);
                            cell = row.getCell(iPe+1);
                            cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(al).getI_cobranza());
                        }
                    }
                     if (cobCronogramaRecaudoDetalleJDList.get(al).getMoneda().contains("DOLARES")){
                           if (iPe > 0){
                            row = sheet1.getRow(86);
                            cell = row.getCell(iPe);
                            cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(al).getI_cliente());

                            row = sheet1.getRow(86);
                            cell = row.getCell(iPe+1);
                            cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(al).getI_cobranza());
                        }
                     
                    }   
                }   
     
           
            }
          
            /************************************************/
            //III. POSICION ESTADO II
            /************************************************/ 
     
            for (inC = 1; inC < 10; inC++)
            {
                if (inR >= cobCronogramaRecaudoDetalleJDList.size()){
                    break;
                }
                if (cobCronogramaRecaudoDetalleJDList.get(inR).getI_posicion() == 4){

                    if (cobCronogramaRecaudoDetalleJDList.get(inR).getC_fondo_id().contains("0001")){
                        iRow = 68;
                    }
                    if (cobCronogramaRecaudoDetalleJDList.get(inR).getC_fondo_id().contains("0002")){
                        iRow = 80;
                    }
                    if (cobCronogramaRecaudoDetalleJDList.get(inR).getC_fondo_id().contains("0003")){
                        iRow = 74;
                    }

                    if (cobCronogramaRecaudoDetalleJDList.get(inR).getC_concepto().contains("09")){
                        iPe = 2;
                    }
                    if (cobCronogramaRecaudoDetalleJDList.get(inR).getC_concepto().contains("10")){
                        iPe = 4;
                    }
                    if (cobCronogramaRecaudoDetalleJDList.get(inR).getC_concepto().contains("11")){
                        iPe = 6;
                    }
                    row = sheet1.getRow(iRow);
                    cell = row.getCell(iPe);
                    cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(inR).getI_cantidad());

                    row = sheet1.getRow(iRow);
                    cell = row.getCell(iPe+1);
                    cell.setCellValue(cobCronogramaRecaudoDetalleJDList.get(inR).getI_deposito());
                }
            }

            /*************************************************************************************/
            //FIN: JUDICIAL
            /*************************************************************************************/
            
            /*************************************************************************************/
            //INICIO FORMULAS
            /*************************************************************************************/
            
            /****************************/
            //I.POSICION GLOBAL
            /****************************/
            
            //a.Normal
            //Falta(-) Excede(+)
            
            row = sheet1.getRow(9);
            cell = row.getCell(6);
            cell.setCellFormula("e10-d10");
            
            row = sheet1.getRow(10);
            cell = row.getCell(6);
            cell.setCellFormula("e11-d11");
            
            row = sheet1.getRow(11);
            cell = row.getCell(6);
            cell.setCellFormula("e12-d12");
            
            row = sheet1.getRow(12);
            cell = row.getCell(6);
            cell.setCellFormula("e13-d13");
            
            row = sheet1.getRow(13);
            cell = row.getCell(6);
            cell.setCellFormula("e14-d10");
            
            row = sheet1.getRow(14);
            cell = row.getCell(6);
            cell.setCellFormula("e11-d11");
            
            row = sheet1.getRow(15);
            cell = row.getCell(6);
            cell.setCellFormula("e12-d12");
            
            row = sheet1.getRow(16);
            cell = row.getCell(6);
            cell.setCellFormula("e13-d13");
            
            //% Var mmaa
            row = sheet1.getRow(9);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e10/f10-1,0)");
            
            row = sheet1.getRow(10);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e11/f11-1,0)");
            
            row = sheet1.getRow(11);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e12/f12-1,0)");
            
            row = sheet1.getRow(12);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e13/f13-1,0)");
            
            
            
            row = sheet1.getRow(13);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e14/f14-1,0)");
            
            row = sheet1.getRow(14);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e15/f15-1,0)");
            
            row = sheet1.getRow(15);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e16/f16-1,0)");
            
            row = sheet1.getRow(16);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e17/f17-1,0)");
            
            //Proy cierre %
            row = sheet1.getRow(9);
            cell = row.getCell(8);
            cell.setCellFormula("IFERROR(e10/$o$2*$o$3,0)");
            
            row = sheet1.getRow(10);
            cell = row.getCell(8);
            cell.setCellFormula("IFERROR(e11/$o$2*$o$3,0)");
        
            row = sheet1.getRow(11);
            cell = row.getCell(8);
            cell.setCellFormula("IFERROR(e12/$o$2*$o$3,0)");
            
            row = sheet1.getRow(12);
            cell = row.getCell(8);
            cell.setCellFormula("IF(OR(e13=0,$o$2*$o$3=0),0,e13/$o$2*$o$3)");
            
            row = sheet1.getRow(13);
            cell = row.getCell(8);
            cell.setCellFormula("IF(e14=0,0, e14/$o$2*$o$3)");
            
            row = sheet1.getRow(14);
            cell = row.getCell(8);
           cell.setCellFormula("IF(OR(e15=0,$o$2*$o$3=0),0, e15/$o$2*$o$3)");
           
            row = sheet1.getRow(15);
            cell = row.getCell(8);
           cell.setCellFormula("IF(OR(e16=0,$o$2*$o$3=0),0, e16/$o$2*$o$3)");
            
            row = sheet1.getRow(16);
            cell = row.getCell(8);
             cell.setCellFormula("IF(OR(e17=0,$o$2*$o$3=0),0, e17/$o$2*$o$3)");
          
            row = sheet1.getRow(9);
            cell = row.getCell(9);
            cell.setCellFormula("IF(OR(i10=0,d10=0),0,i10/d10)");
            
            row = sheet1.getRow(10);
            cell = row.getCell(9);
            cell.setCellFormula("IF(OR(i11=0,d11=0),0,i11/d11)");
            
           
            row = sheet1.getRow(11);
            cell = row.getCell(9);
            cell.setCellFormula("IF(OR(i12=0,d12=0),0,i12/d12)");
            
            
            row = sheet1.getRow(12);
            cell = row.getCell(9);
            cell.setCellFormula("IF(OR(i13=0,d13=0),0,i13/d13)");
           
          
              row = sheet1.getRow(13);
            cell = row.getCell(9);
            cell.setCellFormula("IF(OR(i14=0,d14=0),0,i14/d14)");
           
            
            
            row = sheet1.getRow(14);
            cell = row.getCell(9);
            cell.setCellFormula("IF(OR(i15=0,d15=0),0,i15/d15)");
           
            
            row = sheet1.getRow(15);
            cell = row.getCell(9);
            cell.setCellFormula("IF(OR(i16=0,d16=0),0,i16/d16)");
            
            row = sheet1.getRow(16);
            cell = row.getCell(9);
            cell.setCellFormula("IF(OR(i17=0,d17=0),0,i17/d17)");
            
             
            row = sheet1.getRow(17);
            cell = row.getCell(3);
            cell.setCellFormula("D10+D12+D14+D16");
          
            row = sheet1.getRow(17);
            cell = row.getCell(4);
            cell.setCellFormula("E10+E12+E14+E16");
            
            row = sheet1.getRow(17);
            cell = row.getCell(5);
            cell.setCellFormula("F10+F12+F14+F16");
          
            row = sheet1.getRow(17);
            cell = row.getCell(6);
            cell.setCellFormula("G10+G12+G14+G16");
              
            row = sheet1.getRow(17);
            cell = row.getCell(7);
            
            cell.setCellFormula("IF(OR(e18=0,f18=0),0,e18/f18-1)");
            
            
             row = sheet1.getRow(17);
            cell = row.getCell(8);
            cell.setCellFormula("I10+I12+I14+I16");
            
             row = sheet1.getRow(17);
            cell = row.getCell(9);
            cell.setCellFormula("IF(OR(i18=0,d18=0),0,i18/d18)");
            
                 
        
            row = sheet1.getRow(18);
            cell = row.getCell(3);
            cell.setCellFormula("D11+D13+D15+D17");
          
            row = sheet1.getRow(18);
            cell = row.getCell(4);
            cell.setCellFormula("E11+E13+E15+E17");
            
            row = sheet1.getRow(18);
            cell = row.getCell(5);
            cell.setCellFormula("F11+F13+F15+F17");
          
            row = sheet1.getRow(18);
            cell = row.getCell(6);
            cell.setCellFormula("G11+G13+G15+G17");
            
            row = sheet1.getRow(18);
            cell = row.getCell(7);
            cell.setCellFormula("IF(OR(e19=0,f19=0),0,e19/f19-1)");
            
            
            
             row = sheet1.getRow(18);
            cell = row.getCell(8);
            cell.setCellFormula("I11+I13+I15+I17");
            
             row = sheet1.getRow(18);
            cell = row.getCell(9);
            cell.setCellFormula("IF(OR(i19=0,d19=0),0,i19/d19)");
            
        
            
            //Celda oculta para gráfico
            row = sheet1.getRow(20);
            cell = row.getCell(4);
            cell.setCellFormula("IF(OR(e21=0,d21=0),0,e21/d21)");
            
            row = sheet1.getRow(21);
            cell = row.getCell(4);
            cell.setCellFormula("IF(OR(e22=0,d22=0),0,e22/d22)");
           
            
            
            /****JUDICIAL***********/
            
            
            
            row = sheet1.getRow(25);
            cell = row.getCell(6);
            cell.setCellFormula("e26-d26");
            
            row = sheet1.getRow(26);
            cell = row.getCell(6);
            cell.setCellFormula("e27-d27");
            
            row = sheet1.getRow(27);
            cell = row.getCell(6);
            cell.setCellFormula("e28-d28");
            
            row = sheet1.getRow(28);
            cell = row.getCell(6);
            cell.setCellFormula("e29-d29");
            
            row = sheet1.getRow(29);
            cell = row.getCell(6);
            cell.setCellFormula("e30-d30");
            
            row = sheet1.getRow(30);
            cell = row.getCell(6);
            cell.setCellFormula("e31-d31");
            
            row = sheet1.getRow(31);
            cell = row.getCell(6);
            cell.setCellFormula("e32-d32");
            
            row = sheet1.getRow(32);
            cell = row.getCell(6);
            cell.setCellFormula("e33-d33");
            
            //% Var mmaa
            row = sheet1.getRow(25);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e26/f26-1,0)");
            
            row = sheet1.getRow(26);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e27/f27-1,0)");
            
            row = sheet1.getRow(27);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e28/f28-1,0)");
            
            row = sheet1.getRow(28);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e29/f29-1,0)");
            
            
            row = sheet1.getRow(29);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e30/f30-1,0)");
            
            row = sheet1.getRow(30);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e31/f31-1,0)");
            
            row = sheet1.getRow(31);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e32/f32-1,0)");
            
            row = sheet1.getRow(32);
            cell = row.getCell(7);
            cell.setCellFormula("IFERROR(e33/f33-1,0)");
                     
            
            //Proy cierre %
            row = sheet1.getRow(25);
            cell = row.getCell(8);
            cell.setCellFormula("IF(e26=0,0,e26/$o$2*$o$3)");
           
            
            row = sheet1.getRow(26);
            cell = row.getCell(8);
            cell.setCellFormula("IF(e27=0,0,e27/$o$2*$o$3)");
           
            
            row = sheet1.getRow(27);
            cell = row.getCell(8);
            cell.setCellFormula("IF(e28=0,0,e28/$o$2*$o$3)");
           
            
            row = sheet1.getRow(28);
            cell = row.getCell(8);
            cell.setCellFormula("IF(e29=0,0,e29/$o$2*$o$3)");
           
             row = sheet1.getRow(29);
            cell = row.getCell(8);
            cell.setCellFormula("IF(e30=0,0,e30/$o$2*$o$3)");
           
            
            row = sheet1.getRow(30);
            cell = row.getCell(8);
            cell.setCellFormula("IF(e31=0,0,e31/$o$2*$o$3)");
           
            
            row = sheet1.getRow(31);
            cell = row.getCell(8);
            cell.setCellFormula("IF(e32=0,0,e32/$o$2*$o$3)");
           
            row = sheet1.getRow(32);
            cell = row.getCell(8);
            cell.setCellFormula("IF(e33=0,0,e33/$o$2*$o$3)");
           
             
            
            
            //Proy cierre S/
            row = sheet1.getRow(25);
            cell = row.getCell(9);
            cell.setCellFormula("IF(or(i26=0,d26=0),0,i26/d26)");
           
            
            
            row = sheet1.getRow(26);
            cell = row.getCell(9);
            cell.setCellFormula("IF(or(i27=0,d27=0),0,i27/d27)");
            
            row = sheet1.getRow(27);
            cell = row.getCell(9);
             cell.setCellFormula("IF(or(i28=0,d28=0),0,i28/d28)");
           
          
            row = sheet1.getRow(28);
            cell = row.getCell(9);
            cell.setCellFormula("IF(or(i29=0,d29=0),0,i29/d29)");
            
          
              row = sheet1.getRow(29);
            cell = row.getCell(9);
             cell.setCellFormula("IF(or(i30=0,d30=0),0,i30/d30)");
           
            
            row = sheet1.getRow(30);
            cell = row.getCell(9);
            cell.setCellFormula("IF(or(i31=0,d31=0),0,i31/d31)");
           
           
            row = sheet1.getRow(31);
            cell = row.getCell(9);
             cell.setCellFormula("IF(or(i32=0,d32=0),0,i32/d32)");
           
            
            row = sheet1.getRow(32);
            cell = row.getCell(9);
            cell.setCellFormula("IF(or(i33=0,d33=0),0,i33/d33)");
           
          
            row = sheet1.getRow(33);
            cell = row.getCell(3);
            cell.setCellFormula("D26+D28+D30+D32");
          
            row = sheet1.getRow(33);
            cell = row.getCell(4);
            cell.setCellFormula("E26+E28+E30+E32");
            
            row = sheet1.getRow(33);
            cell = row.getCell(5);
            cell.setCellFormula("F26+F28+F30+F32");
          
            row = sheet1.getRow(33);
            cell = row.getCell(6);
            cell.setCellFormula("G26+G28+G30+G32");
            
            row = sheet1.getRow(33);
            cell = row.getCell(7);
            cell.setCellFormula("IF(or(i34=0,f34=0),0,i34/f34-1)");
           
            
             row = sheet1.getRow(33);
            cell = row.getCell(8);
            cell.setCellFormula("I26+I28+I30+I32");
            
             row = sheet1.getRow(33);
            cell = row.getCell(9);
            cell.setCellFormula("IF(or(i34=0,d34=0),0,i34/d34)");
           
            
            
        
            row = sheet1.getRow(34);
            cell = row.getCell(3);
            cell.setCellFormula("D27+D29+D31+D33");
          
            row = sheet1.getRow(34);
            cell = row.getCell(4);
            cell.setCellFormula("E27+E29+E31+E33");
            
            row = sheet1.getRow(34);
            cell = row.getCell(5);
            cell.setCellFormula("F27+F29+F31+F33");
          
            row = sheet1.getRow(34);
            cell = row.getCell(6);
            cell.setCellFormula("G27+G29+G31+G33");
            
            row = sheet1.getRow(34);
            cell = row.getCell(7);
            cell.setCellFormula("IF(or(e35=0,f35=0),0,e35/f35-1)");
           
             row = sheet1.getRow(34);
            cell = row.getCell(8);
            cell.setCellFormula("I27+I29+I31+I33");
            
             row = sheet1.getRow(34);
            cell = row.getCell(9);
            
            cell.setCellFormula("IF(or(i35=0,d35=0),0,i35/d35)");
           
         
            //Celda oculta para gráfico
            row = sheet1.getRow(20);
            cell = row.getCell(4);
            cell.setCellFormula("IF(or(e21=0,d21=0),0,e21/d21)");
        
            
            row = sheet1.getRow(21);
            cell = row.getCell(4);
            cell.setCellFormula("IF(or(e22=0,d22=0),0,e22/d22)");
        
            
            row = sheet1.getRow(36);
            cell = row.getCell(3);
            cell.setCellFormula("D18+D34");
        
            row = sheet1.getRow(36);
            cell = row.getCell(4);
            cell.setCellFormula("E18+E34");
            
             row = sheet1.getRow(37);
            cell = row.getCell(3);
            cell.setCellFormula("D19+D35");
        
            
            
            row = sheet1.getRow(37);
            cell = row.getCell(4);
            cell.setCellFormula("E19+E35");
        
            //II-Posicion Fondo II
            
            row = sheet1.getRow(45);
            cell = row.getCell(4);
            cell.setCellFormula("IF(or(d46=0,d45=0),0,D46/D$45)");
        
            row = sheet1.getRow(49);
            cell = row.getCell(4);
            cell.setCellFormula("IF(or(d50=0,d49=0),0,D50/D49)");
        
          
            row = sheet1.getRow(45);
            cell = row.getCell(6);
            cell.setCellFormula("IF(or(f46=0,d45=0),0,D46/D45)");
        
            
            row = sheet1.getRow(49);
            cell = row.getCell(6);
            cell.setCellFormula("IF(or(f50=0,d49=0),0,D50/D49)");
        
            row = sheet1.getRow(45);
            cell = row.getCell(8);
            cell.setCellFormula("IF(or(h46=0,h45=0),0,h46/$h$45)");
        
        
            row = sheet1.getRow(49);
            cell = row.getCell(8);
            cell.setCellFormula("IF(or(h50=0,h49=0),0,h50/h49)");
        
            
            
            row = sheet1.getRow(45);
            cell = row.getCell(14);
            cell.setCellFormula("IF(or(n46=0,n45=0),0,n46/$n$45)");
        
        
            row = sheet1.getRow(49);
            cell = row.getCell(14);
            cell.setCellFormula("IF(or(n50=0,n49=0),0,n50/n49)");
        
            // TOTAL
            
            
            row = sheet1.getRow(44);
            cell = row.getCell(9);
            cell.setCellFormula("D45+F45+H45");
        
            row = sheet1.getRow(45);
            cell = row.getCell(9);
            cell.setCellFormula("D46+F46+H46");
        
            row = sheet1.getRow(46);
            cell = row.getCell(9);
            cell.setCellFormula("D47+F47+H47");
        
            row = sheet1.getRow(47);
            cell = row.getCell(9);
            cell.setCellFormula("D48+F48+H48");
        
            row = sheet1.getRow(48);
            cell = row.getCell(9);
            cell.setCellFormula("D49+F49+H49");
          
            row = sheet1.getRow(49);
            cell = row.getCell(9);
            cell.setCellFormula("D50+F50+H50");
            
            row = sheet1.getRow(50);
            cell = row.getCell(9);
            cell.setCellFormula("D51+F51+H51");
            
            row = sheet1.getRow(51);
            cell = row.getCell(9);
            cell.setCellFormula("D52+F52+H52");
            
                        
            row = sheet1.getRow(44);
            cell = row.getCell(15);
            cell.setCellFormula("n45");
        
            row = sheet1.getRow(45);
            cell = row.getCell(15);
            cell.setCellFormula("n46");
        
            row = sheet1.getRow(46);
            cell = row.getCell(15);
            cell.setCellFormula("n47");
        
            row = sheet1.getRow(47);
            cell = row.getCell(15);
            cell.setCellFormula("n48");
        
            row = sheet1.getRow(48);
            cell = row.getCell(15);
            cell.setCellFormula("n49");
          
            row = sheet1.getRow(49);
            cell = row.getCell(15);
            cell.setCellFormula("n50");
            
            row = sheet1.getRow(50);
            cell = row.getCell(15);
            cell.setCellFormula("n51");
            
            row = sheet1.getRow(51);
            cell = row.getCell(15);
            cell.setCellFormula("n52");
            
            
            row = sheet1.getRow(57);
            cell = row.getCell(4);
            cell.setCellFormula("IF(or(d58=0,d57=0),0,D58/D57)");
        
          
            row = sheet1.getRow(61);
            cell = row.getCell(4);
            cell.setCellFormula("IF(or(d62=0,d61=0),0,d62/d61)");
        
            
            row = sheet1.getRow(57);
            cell = row.getCell(6);
            cell.setCellFormula("IF(or(f58=0,f57=0),0,F58/F57)");
        
            row = sheet1.getRow(61);
            cell = row.getCell(6);
            cell.setCellFormula("IF(or(f62=0,f61=0),0,F62/F61)");
        
            
            row = sheet1.getRow(57);
            cell = row.getCell(14);
            cell.setCellFormula("IF(or(n58=0,n57=0),0,n58/n57)");
        
        
            row = sheet1.getRow(61);
            cell = row.getCell(14);
            cell.setCellFormula("IF(or(n62=0,n61=0),0,n62/n61)");
        
                    
            row = sheet1.getRow(56);
            cell = row.getCell(7);
            cell.setCellFormula("F57+D57");
        
            row = sheet1.getRow(57);
            cell = row.getCell(7);
            cell.setCellFormula("F58+D58");
        
            row = sheet1.getRow(58);
            cell = row.getCell(7);
            cell.setCellFormula("F59+D59");
        
            row = sheet1.getRow(59);
            cell = row.getCell(7);
            cell.setCellFormula("F60+D60");
        
            row = sheet1.getRow(60);
            cell = row.getCell(7);
            cell.setCellFormula("F61+D61");
          
            row = sheet1.getRow(61);
            cell = row.getCell(7);
            cell.setCellFormula("F62+D62");
            
            row = sheet1.getRow(62);
            cell = row.getCell(7);
            cell.setCellFormula("F63+D63");
            
            row = sheet1.getRow(63);
            cell = row.getCell(7);
            cell.setCellFormula("F64+D64");
            
                  
            row = sheet1.getRow(56);
            cell = row.getCell(15);
            cell.setCellFormula("n57");
        
            row = sheet1.getRow(57);
            cell = row.getCell(15);
            cell.setCellFormula("n58");
        
            row = sheet1.getRow(58);
            cell = row.getCell(15);
            cell.setCellFormula("n59");
        
            row = sheet1.getRow(59);
            cell = row.getCell(15);
            cell.setCellFormula("n60");
        
            row = sheet1.getRow(60);
            cell = row.getCell(15);
            cell.setCellFormula("n61");
          
            row = sheet1.getRow(61);
            cell = row.getCell(15);
            cell.setCellFormula("n62");
            
            row = sheet1.getRow(62);
            cell = row.getCell(15);
            cell.setCellFormula("n63");
            
            row = sheet1.getRow(63);
            cell = row.getCell(15);
            cell.setCellFormula("n64");
            
      
            
            
            // II-POSICION POR FONDO - JUDICIALES
            
            row = sheet1.getRow(69);
            cell = row.getCell(7);
            cell.setCellFormula("D70+E70+F70+G70");
        
            row = sheet1.getRow(70);
            cell = row.getCell(7);
            cell.setCellFormula("D71+E71+F71+G71");
        
            row = sheet1.getRow(71);
            cell = row.getCell(7);
            cell.setCellFormula("D72+E72+F72+G72");
        
            row = sheet1.getRow(72);
            cell = row.getCell(7);
            cell.setCellFormula("D73+E73+F73+G73");
        
            row = sheet1.getRow(73);
            cell = row.getCell(7);
            cell.setCellFormula("D74+E74+F74+G74");
          
            row = sheet1.getRow(74);
            cell = row.getCell(7);
            cell.setCellFormula("D75+E75+F75+G75");
            
            row = sheet1.getRow(75);
            cell = row.getCell(7);
            cell.setCellFormula("D76+E76+F76+G76");
            
            row = sheet1.getRow(76);
            cell = row.getCell(7);
            cell.setCellFormula("D77+E77+F77+G77");
            
            
            
            
            row = sheet1.getRow(70);
            cell = row.getCell(8);
            cell.setCellFormula("IF(or(h70=0,h71=0),0,H71/H70)");
        
            row = sheet1.getRow(71);
            cell = row.getCell(8);
            cell.setCellFormula("0");
        
             row = sheet1.getRow(72);
            cell = row.getCell(8);
            cell.setCellFormula("0");
        
             row = sheet1.getRow(73);
            cell = row.getCell(8);
            cell.setCellFormula("0");
        
            
            row = sheet1.getRow(74);
            cell = row.getCell(8);
            cell.setCellFormula("IF(or(h74=0,h75=0),0,H75/H74)");
            
            
            row = sheet1.getRow(75);
            cell = row.getCell(8);
            cell.setCellFormula("0");
        
            row = sheet1.getRow(76);
            cell = row.getCell(8);
            cell.setCellFormula("0");
        
            
            //III- POSECION POR ESTADO - ACTIVOS   
            row = sheet1.getRow(83);
            cell = row.getCell(11);
            cell.setCellFormula("D84+E84+F84+G84+H84+I84+J84+K84");
            
            row = sheet1.getRow(84);
            cell = row.getCell(11);
            cell.setCellFormula("D85+E85+F85+G85+H85+I85+J85+K85");
            
            row = sheet1.getRow(85);
            cell = row.getCell(11);
            cell.setCellFormula("D86+E86+F86+G86+H86+I86+J86+K86");
            
            row = sheet1.getRow(86);
            cell = row.getCell(11);
            cell.setCellFormula("D87+E87+F87+G87+H87+I87+J87+K87");
            
            row = sheet1.getRow(87);
            cell = row.getCell(11);
            cell.setCellFormula("D88+E88+F88+G88+H88+I88+J88+K88");
            
            row = sheet1.getRow(88);
            cell = row.getCell(11);
            cell.setCellFormula("D89+E89+F89+G89+H89+I89+J89+K89");
            
            row = sheet1.getRow(89);
            cell = row.getCell(11);
            cell.setCellFormula("D90+E90+F90+G90+H90+I90+J90+K90");
            
            //fin activos
            row = sheet1.getRow(87);
            cell = row.getCell(3);
            cell.setCellFormula("D84+D85");
            
            row = sheet1.getRow(87);
            cell = row.getCell(4);
            cell.setCellFormula("E84+E85");
            
            row = sheet1.getRow(87);
            cell = row.getCell(5);
            cell.setCellFormula("F84+F85");
            
            row = sheet1.getRow(87);
            cell = row.getCell(6);
            cell.setCellFormula("G84+G85");
            
            row = sheet1.getRow(87);
            cell = row.getCell(7);
            cell.setCellFormula("H84+H85");
            
            row = sheet1.getRow(87);
            cell = row.getCell(8);
            cell.setCellFormula("I84+I85");
            
            
            row = sheet1.getRow(87);
            cell = row.getCell(9);
            cell.setCellFormula("J84+J85");
            
            
            
            row = sheet1.getRow(87);
            cell = row.getCell(10);
            cell.setCellFormula("K84+K85");
            
            
            row = sheet1.getRow(87);
            cell = row.getCell(11);
            cell.setCellFormula("L84+L85");
            
            
            
            
            
            row = sheet1.getRow(88);
            cell = row.getCell(3);
            cell.setCellFormula("D86+D87");
            
            row = sheet1.getRow(88);
            cell = row.getCell(4);
            cell.setCellFormula("E86+E87");
            
            row = sheet1.getRow(88);
            cell = row.getCell(5);
            cell.setCellFormula("F86+F87");
            
            row = sheet1.getRow(88);
            cell = row.getCell(6);
            cell.setCellFormula("G86+G87");
            
            row = sheet1.getRow(88);
            cell = row.getCell(7);
            cell.setCellFormula("H86+H87");
            
            row = sheet1.getRow(88);
            cell = row.getCell(8);
            cell.setCellFormula("I86+I87");
            
            
            row = sheet1.getRow(88);
            cell = row.getCell(9);
            cell.setCellFormula("J86+J87");
            
            
            
            row = sheet1.getRow(88);
            cell = row.getCell(10);
            cell.setCellFormula("K86+K87");
            
            
            row = sheet1.getRow(88);
            cell = row.getCell(11);
            cell.setCellFormula("L86+L87");
            
                    
                    
            row = sheet1.getRow(99);
            cell = row.getCell(3);
            cell.setCellFormula("D96+D97");
            
            row = sheet1.getRow(99);
            cell = row.getCell(4);
            cell.setCellFormula("e96+e97");
            
            row = sheet1.getRow(99);
            cell = row.getCell(5);
            cell.setCellFormula("f96+f97");
            
            row = sheet1.getRow(99);
            cell = row.getCell(6);
            cell.setCellFormula("g96+g97");
            
            row = sheet1.getRow(99);
            cell = row.getCell(7);
            cell.setCellFormula("h96+h97");
            
            row = sheet1.getRow(99);
            cell = row.getCell(8);
            cell.setCellFormula("i96+i97");
            
            
            
            row = sheet1.getRow(99);
            cell = row.getCell(9);
            cell.setCellFormula("D100+F100+H100");
            
            
            row = sheet1.getRow(99);
            cell = row.getCell(10);
            cell.setCellFormula("E100+G100+I100");
            
            
            
            
            
            row = sheet1.getRow(100);
            cell = row.getCell(3);
            cell.setCellFormula("D98+D99");
            
            row = sheet1.getRow(100);
            cell = row.getCell(4);
            cell.setCellFormula("E98+E99");
            
            row = sheet1.getRow(100);
            cell = row.getCell(5);
            cell.setCellFormula("F98+F99");
            
            row = sheet1.getRow(100);
            cell = row.getCell(6);
            cell.setCellFormula("G98+G99");
            
            row = sheet1.getRow(100);
            cell = row.getCell(7);
            cell.setCellFormula("H98+H99");
            
            row = sheet1.getRow(100);
            cell = row.getCell(8);
            cell.setCellFormula("I98+I99");
            
            
            row = sheet1.getRow(100);
            cell = row.getCell(9);
            cell.setCellFormula("D101+F101+H101");
            
            
            
            row = sheet1.getRow(100);
            cell = row.getCell(10);
            cell.setCellFormula("E101+G101+I101");
            
            
            
            
            row = sheet1.getRow(108);
            cell = row.getCell(3);
            cell.setCellFormula("D105+D106");
            
            row = sheet1.getRow(108);
            cell = row.getCell(4);
            cell.setCellFormula("e105+e106");
            
            row = sheet1.getRow(108);
            cell = row.getCell(5);
            cell.setCellFormula("f105+f106");
            
            row = sheet1.getRow(108);
            cell = row.getCell(6);
            cell.setCellFormula("g105+g106");
            
            row = sheet1.getRow(108);
            cell = row.getCell(7);
            cell.setCellFormula("h105+h106");
            
            row = sheet1.getRow(108);
            cell = row.getCell(8);
            cell.setCellFormula("i105+i106");
            
            
            
            row = sheet1.getRow(108);
            cell = row.getCell(9);
            cell.setCellFormula("D109+F109+H109");
            
            
            row = sheet1.getRow(108);
            cell = row.getCell(10);
            cell.setCellFormula("E109+G109+I109");
            
            
            
            
            row = sheet1.getRow(109);
            cell = row.getCell(3);
            cell.setCellFormula("D86+D87");
            
            row = sheet1.getRow(109);
            cell = row.getCell(4);
            cell.setCellFormula("E86+E87");
            
            row = sheet1.getRow(109);
            cell = row.getCell(5);
            cell.setCellFormula("F86+F87");
            
            row = sheet1.getRow(109);
            cell = row.getCell(6);
            cell.setCellFormula("G86+G87");
            
            row = sheet1.getRow(109);
            cell = row.getCell(7);
            cell.setCellFormula("H86+H87");
            
            row = sheet1.getRow(109);
            cell = row.getCell(8);
            cell.setCellFormula("I86+I87");
            
            
            row = sheet1.getRow(109);
            cell = row.getCell(9);
            cell.setCellFormula("D110+F110+H110");
            
            
            
            row = sheet1.getRow(109);
            cell = row.getCell(10);
            cell.setCellFormula("E110+G110+I110");
            
            
            row = sheet1.getRow(117);
            cell = row.getCell(3);
            cell.setCellFormula("D114+D115");
            
            row = sheet1.getRow(117);
            cell = row.getCell(4);
            cell.setCellFormula("e114+e115");
            
            row = sheet1.getRow(117);
            cell = row.getCell(5);
            cell.setCellFormula("f114+f115");
            
            row = sheet1.getRow(117);
            cell = row.getCell(6);
            cell.setCellFormula("g114+g115");
            
            row = sheet1.getRow(117);
            cell = row.getCell(7);
            cell.setCellFormula("h114+h115");
            
            row = sheet1.getRow(117);
            cell = row.getCell(8);
            cell.setCellFormula("i114+i115");
            
            
            
            row = sheet1.getRow(117);
            cell = row.getCell(9);
            cell.setCellFormula("D118+F118+H118");
            
            
            row = sheet1.getRow(117);
            cell = row.getCell(10);
            cell.setCellFormula("E118+G118+I118");
            
            
            
            
            row = sheet1.getRow(118);
            cell = row.getCell(3);
            cell.setCellFormula("D116+D117");
            
            row = sheet1.getRow(118);
            cell = row.getCell(4);
            cell.setCellFormula("E116+E117");
            
            row = sheet1.getRow(118);
            cell = row.getCell(5);
            cell.setCellFormula("F116+F117");
            
            row = sheet1.getRow(118);
            cell = row.getCell(6);
            cell.setCellFormula("G116+G117");
            
            row = sheet1.getRow(118);
            cell = row.getCell(7);
            cell.setCellFormula("H116+H117");
            
            row = sheet1.getRow(118);
            cell = row.getCell(8);
            cell.setCellFormula("I116+I117");
            
            
            row = sheet1.getRow(118);
            cell = row.getCell(9);
            cell.setCellFormula("D119+F119+H119");
            
            
            
            row = sheet1.getRow(118);
            cell = row.getCell(10);
            cell.setCellFormula("E119+G119+I119");
            
                
            
            
            
            
            row = sheet1.getRow(126);
            cell = row.getCell(3);
            cell.setCellFormula("D123+D124");
            
            row = sheet1.getRow(126);
            cell = row.getCell(4);
            cell.setCellFormula("E123+E124");
            
            row = sheet1.getRow(126);
            cell = row.getCell(5);
            cell.setCellFormula("F123+F124");
            
            row = sheet1.getRow(126);
            cell = row.getCell(6);
            cell.setCellFormula("G123+G124");
            
            row = sheet1.getRow(126);
            cell = row.getCell(7);
            cell.setCellFormula("H123+H124");
            
            row = sheet1.getRow(126);
            cell = row.getCell(8);
            cell.setCellFormula("I123+I124");
            
            
            
            
            
            row = sheet1.getRow(127);
            cell = row.getCell(3);
            cell.setCellFormula("D125+D126");
            
            row = sheet1.getRow(118);
            cell = row.getCell(4);
            cell.setCellFormula("E125+E126");
            
            row = sheet1.getRow(118);
            cell = row.getCell(5);
            cell.setCellFormula("F125+F126");
            
            row = sheet1.getRow(118);
            cell = row.getCell(6);
            cell.setCellFormula("G125+G126");
            
            row = sheet1.getRow(118);
            cell = row.getCell(7);
            cell.setCellFormula("H125+H126");
            
            row = sheet1.getRow(118);
            cell = row.getCell(8);
            cell.setCellFormula("I125+I126");
            
            
            
            
            row = sheet1.getRow(95);
            cell = row.getCell(9);
            cell.setCellFormula("D96+F96+H96");
            
            row = sheet1.getRow(95);
            cell = row.getCell(10);
            cell.setCellFormula("E96+G96+I96");
            
            
            
            row = sheet1.getRow(96);
            cell = row.getCell(9);
            cell.setCellFormula("D97+F97+H97");
            
            row = sheet1.getRow(96);
            cell = row.getCell(10);
            cell.setCellFormula("E97+G97+I97");
            
            
            
            row = sheet1.getRow(97);
            cell = row.getCell(9);
            cell.setCellFormula("D98+F98+H98");
            
            row = sheet1.getRow(97);
            cell = row.getCell(10);
            cell.setCellFormula("E98+G98+I98");
            
            
            
            row = sheet1.getRow(98);
            cell = row.getCell(9);
            cell.setCellFormula("D99+F99+H99");
            
            row = sheet1.getRow(98);
            cell = row.getCell(10);
            cell.setCellFormula("E99+G99+I99");
            
            
            
           
            
            row = sheet1.getRow(104);
            cell = row.getCell(9);
            cell.setCellFormula("D105+F105+H105");
            
            row = sheet1.getRow(104);
            cell = row.getCell(10);
            cell.setCellFormula("E105+G105+I105");
            
            
            
            row = sheet1.getRow(105);
            cell = row.getCell(9);
            cell.setCellFormula("D106+F106+H106");
            
            row = sheet1.getRow(105);
            cell = row.getCell(10);
            cell.setCellFormula("E106+G106+I106");
            
            
            
            row = sheet1.getRow(106);
            cell = row.getCell(9);
            cell.setCellFormula("D107+F107+H107");
            
            row = sheet1.getRow(106);
            cell = row.getCell(10);
            cell.setCellFormula("E107+G107+I107");
            
            
            
            row = sheet1.getRow(107);
            cell = row.getCell(9);
            cell.setCellFormula("D108+F108+H108");
            
            row = sheet1.getRow(107);
            cell = row.getCell(10);
            cell.setCellFormula("E108+G108+I108");
            
            
            
                    
                    
            
            
            row = sheet1.getRow(113);
            cell = row.getCell(9);
            cell.setCellFormula("D114+F114+H114");
            
            row = sheet1.getRow(113);
            cell = row.getCell(10);
            cell.setCellFormula("E114+E114+E114");
            
            
            
            row = sheet1.getRow(114);
            cell = row.getCell(9);
            cell.setCellFormula("D115+F115+H115");
            
            row = sheet1.getRow(114);
            cell = row.getCell(10);
            cell.setCellFormula("E115+E115+E115");
            
            
            
            row = sheet1.getRow(115);
            cell = row.getCell(9);
            cell.setCellFormula("D116+F116+H116");
            
            row = sheet1.getRow(115);
            cell = row.getCell(10);
            cell.setCellFormula("E116+E116+E116");
            
            
            
            row = sheet1.getRow(116);
            cell = row.getCell(9);
            cell.setCellFormula("D117+F117+H117");
            
            row = sheet1.getRow(116);
            cell = row.getCell(10);
            cell.setCellFormula("E114+E114+E114");
            
                             
            
            row = sheet1.getRow(122);
            cell = row.getCell(7);
            cell.setCellFormula("D123+F123");
            
            row = sheet1.getRow(122);
            cell = row.getCell(8);
            cell.setCellFormula("E123+G123");
            
            
            
            row = sheet1.getRow(123);
            cell = row.getCell(7);
            cell.setCellFormula("D124+F124");
            
            row = sheet1.getRow(123);
            cell = row.getCell(8);
            cell.setCellFormula("E124+G124");
            
            
            
            row = sheet1.getRow(124);
            cell = row.getCell(7);
            cell.setCellFormula("D125+F125");
            
            row = sheet1.getRow(124);
            cell = row.getCell(8);
            cell.setCellFormula("E125+G125");
            
            
            
            row = sheet1.getRow(125);
            cell = row.getCell(7);
            cell.setCellFormula("D126+F126");
            
            row = sheet1.getRow(125);
            cell = row.getCell(8);
            cell.setCellFormula("E126+G126");
            
            
            
            
            
            
            
            row = sheet1.getRow(95);
            cell = row.getCell(14);
            cell.setCellFormula("J96+J105+J114");
            
            row = sheet1.getRow(95);
            cell = row.getCell(15);
            cell.setCellFormula("K96+K105+K114");
            
            
            
            row = sheet1.getRow(96);
            cell = row.getCell(14);
            cell.setCellFormula("J97+J106+J115");
            
            row = sheet1.getRow(96);
            cell = row.getCell(15);
            cell.setCellFormula("K97+K106+K115");            
            
            
            
            row = sheet1.getRow(97);
            cell = row.getCell(14);
            cell.setCellFormula("J98+J107+J116");
            
            row = sheet1.getRow(97);
            cell = row.getCell(15);
            cell.setCellFormula("K98+K107+K116");            
            
            
            
            row = sheet1.getRow(98);
            cell = row.getCell(14);
            cell.setCellFormula("J99+J108+J117");
            
            row = sheet1.getRow(98);
            cell = row.getCell(15);
            cell.setCellFormula("K99+K108+K117");


            row = sheet1.getRow(99);
            cell = row.getCell(14);
            cell.setCellFormula("O96+O98");
            
            row = sheet1.getRow(99);
            cell = row.getCell(15);
            cell.setCellFormula("P96+P97");    
            
            
            
            
            
            
            row = sheet1.getRow(100);
            cell = row.getCell(14);
            cell.setCellFormula("O97+O99");
            
            row = sheet1.getRow(100);
            cell = row.getCell(15);
            cell.setCellFormula("P97+P98");   
            
            row = sheet1.getRow(90);
            cell = row.getCell(2);
            cell.setCellFormula("L88");
            
            
            row = sheet1.getRow(90);
            cell = row.getCell(3);
            cell.setCellFormula("L89");
            
            
            /*************************************************************************************/
            //FIN FORMULAS
            /*************************************************************************************/
            
            wb.write(byteOutputStream);    
            wb.close();

            byteOutputStream.writeTo(fileOut);
            fileOut.close();
            return byteOutputStream.toByteArray();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        catch (Exception e)
        {
            return null;
        }
        
    }
}
