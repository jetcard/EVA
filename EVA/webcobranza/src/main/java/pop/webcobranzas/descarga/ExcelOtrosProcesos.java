package pop.webcobranzas.descarga;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pop.comun.dominio.LegOtroProceso;
import pop.webcobranzas.util.PixelUtil;

public class ExcelOtrosProcesos {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<LegOtroProceso> listaLegOtroProceso;

    public ExcelOtrosProcesos(List<LegOtroProceso> _listaLegOtroProceso) {
        this.listaLegOtroProceso = _listaLegOtroProceso;
        workbook = new XSSFWorkbook();
    }

    public void writeHeaderLine(String fechaProceso) {
        sheet = workbook.createSheet("Hoja "+fechaProceso);
        sheet.setDisplayGridlines(false);
        CellRangeAddress mergedRegion0 = new CellRangeAddress(0,0,1,12);
        sheet.addMergedRegion(mergedRegion0);
        CellRangeAddress mergedRegion1 = new CellRangeAddress(2,3,1,1);
        sheet.addMergedRegion(mergedRegion1);
        CellRangeAddress mergedRegion2 = new CellRangeAddress(2,3,2,2);
        sheet.addMergedRegion(mergedRegion2);
        CellRangeAddress mergedRegion3 = new CellRangeAddress(2,3,3,3);
        sheet.addMergedRegion(mergedRegion3);
        CellRangeAddress mergedRegion4 = new CellRangeAddress(2,3,4,4);
        sheet.addMergedRegion(mergedRegion4);
        CellRangeAddress mergedRegion5 = new CellRangeAddress(2,2,5,7);
        sheet.addMergedRegion(mergedRegion5);      
        CellRangeAddress mergedRegion8 = new CellRangeAddress(2,3,8,8);
        sheet.addMergedRegion(mergedRegion8);
        CellRangeAddress mergedRegion9 = new CellRangeAddress(2,3,9,9);
        sheet.addMergedRegion(mergedRegion9);
        CellRangeAddress mergedRegion10 = new CellRangeAddress(2,3,10,10);
        sheet.addMergedRegion(mergedRegion10);        
        CellRangeAddress mergedRegion11 = new CellRangeAddress(2,3,11,11);
        sheet.addMergedRegion(mergedRegion11);
        CellRangeAddress mergedRegion12 = new CellRangeAddress(2,3,12,12);
        sheet.addMergedRegion(mergedRegion12);
        CellRangeAddress mergedRegion13 = new CellRangeAddress(2,3,13,13);
        sheet.addMergedRegion(mergedRegion13);
        CellRangeAddress mergedRegion14 = new CellRangeAddress(2,3,14,14);
        sheet.addMergedRegion(mergedRegion14);
        
        CellStyle styleTop = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(11);
        styleTop.setFont(font);
        styleTop.setAlignment(HorizontalAlignment.CENTER);
        
        CellStyle styleHeaders = workbook.createCellStyle();
        styleHeaders.setVerticalAlignment(VerticalAlignment.CENTER);
        styleHeaders.setAlignment(HorizontalAlignment.CENTER);
        styleHeaders.setWrapText(true);
        font.setBold(true);
        font.setFontHeight(11);
        styleHeaders.setFont(font);
        styleHeaders.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
        styleHeaders.setFillPattern(FillPatternType.SOLID_FOREGROUND);
               
        Row row0 = sheet.createRow(0);
        createCell(row0, 1, "Reporte de registro de otros procesos judiciales al "+fechaProceso, styleTop);
        
        Row row2 = sheet.createRow(2);
        createCell(row2, 1, "N", styleHeaders);
        createCell(row2, 2, "Fondo", styleHeaders);
        createCell(row2, 3, "Codigo", styleHeaders);
        createCell(row2, 4, "Nro Documento", styleHeaders);
        createCell(row2, 5, "Propietario", styleHeaders);
        createCell(row2, 8, "Estado", styleHeaders);
        createCell(row2, 9, "Materia", styleHeaders);
        createCell(row2, 10, "Tipo", styleHeaders);
        createCell(row2, 11, "Organo", styleHeaders);
        createCell(row2, 12, "Expediente", styleHeaders);
        createCell(row2, 13, "Especialista", styleHeaders);
        createCell(row2, 14, "Fecha", styleHeaders);        

        Row row3 = sheet.createRow(3);
        createCell(row3, 5, "Nombres", styleHeaders);
        createCell(row3, 6, "Apellido paterno", styleHeaders);
        createCell(row3, 7, "Apellido materno", styleHeaders);

        setBordersToMergedCells(sheet, mergedRegion1);
        setBordersToMergedCells(sheet, mergedRegion2);
        setBordersToMergedCells(sheet, mergedRegion3);
        setBordersToMergedCells(sheet, mergedRegion4);
        setBordersToMergedCells(sheet, mergedRegion5);
        setBordersToMergedCells(sheet, mergedRegion8);
        setBordersToMergedCells(sheet, mergedRegion9);
        setBordersToMergedCells(sheet, mergedRegion10);
        setBordersToMergedCells(sheet, mergedRegion11);
        setBordersToMergedCells(sheet, mergedRegion12);
        setBordersToMergedCells(sheet, mergedRegion13);
        setBordersToMergedCells(sheet, mergedRegion14);

        sheet.setColumnWidth(0, PixelUtil.pixel2WidthUnits(15));
        sheet.setColumnWidth(1, PixelUtil.pixel2WidthUnits(20));
        sheet.setColumnWidth(2, PixelUtil.pixel2WidthUnits(180));
        sheet.setColumnWidth(3, PixelUtil.pixel2WidthUnits(80));
        sheet.setColumnWidth(4, PixelUtil.pixel2WidthUnits(80));
        sheet.setColumnWidth(5, PixelUtil.pixel2WidthUnits(120));
        sheet.setColumnWidth(6, PixelUtil.pixel2WidthUnits(120));
        sheet.setColumnWidth(7, PixelUtil.pixel2WidthUnits(120));
        sheet.setColumnWidth(8, PixelUtil.pixel2WidthUnits(80));
        sheet.setColumnWidth(9, PixelUtil.pixel2WidthUnits(95));
        sheet.setColumnWidth(10, PixelUtil.pixel2WidthUnits(150));
        sheet.setColumnWidth(11, PixelUtil.pixel2WidthUnits(200));
        sheet.setColumnWidth(12, PixelUtil.pixel2WidthUnits(200));
        sheet.setColumnWidth(13, PixelUtil.pixel2WidthUnits(150));
        sheet.setColumnWidth(14, PixelUtil.pixel2WidthUnits(100));
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if(value instanceof Date) {
            cell.setCellValue((Date) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public void writeDataLines() {

        int rowCount = 4;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.RIGHT);
        
        CellStyle styleDate = workbook.createCellStyle();
        XSSFCreationHelper createHelper = workbook.getCreationHelper();
        styleDate.setVerticalAlignment(VerticalAlignment.CENTER);
        styleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
        styleDate.setBorderTop(BorderStyle.THIN);
        styleDate.setBorderBottom(BorderStyle.THIN);
        styleDate.setBorderLeft(BorderStyle.THIN);
        styleDate.setBorderRight(BorderStyle.THIN);
        styleDate.setAlignment(HorizontalAlignment.CENTER);
        
        CellStyle styleCenter = workbook.createCellStyle();
        styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        styleCenter.setBorderTop(BorderStyle.THIN);
        styleCenter.setBorderBottom(BorderStyle.THIN);
        styleCenter.setBorderLeft(BorderStyle.THIN);
        styleCenter.setBorderRight(BorderStyle.THIN);
        styleCenter.setWrapText(true);
        font.setBold(false);
        font.setFontHeight(10);
        styleCenter.setFont(font);
        

        int contadorRegistros=0;
        for( LegOtroProceso result: listaLegOtroProceso) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 1;
            createCell(row, columnCount++, ++contadorRegistros, styleCenter);
            createCell(row, columnCount++, result.getDescFondo(), style);
            createCell(row, columnCount++, result.getCodigoTCHN(), style);
            createCell(row, columnCount++, result.getNroDocumento(), styleCenter);
            createCell(row, columnCount++, result.getNombres(), style);
            createCell(row, columnCount++, result.getApellidoPat(), style);
            createCell(row, columnCount++, result.getApellidoMat(), style);
            createCell(row, columnCount++, result.getDescEstado().isEmpty()?" - ":result.getDescEstado(), styleCenter);
            createCell(row, columnCount++, result.getDescripcionMateria().isEmpty()?" - ":result.getDescripcionMateria(), styleCenter);
            createCell(row, columnCount++, result.getDescripcionTipo().isEmpty()?" - ":result.getDescripcionTipo(), styleCenter);
            createCell(row, columnCount++, result.getOrganocompetente().isEmpty()?" - ":result.getOrganocompetente(), styleCenter);
            createCell(row, columnCount++, result.getNroexp().isEmpty()?" - ":result.getNroexp(), styleCenter);
            createCell(row, columnCount++, result.getEspecialista().isEmpty()?" - ":result.getEspecialista(), styleCenter);
            createCell(row, columnCount++, result.getFecha().toString().isEmpty()?" - ":result.getFecha(), styleDate);
        }
    }

    public void export(HttpServletResponse response, String fecha) throws IOException {
        writeHeaderLine(fecha);
        writeDataLines();
        ServletOutputStream servletOutput = response.getOutputStream();
        workbook.write(servletOutput);
        workbook.close();
        servletOutput.close();
    }

    protected void setBordersToMergedCells(Sheet sheet, CellRangeAddress rangeAddress) {
        RegionUtil.setBorderTop(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, rangeAddress, sheet);
    }
}