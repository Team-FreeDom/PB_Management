package com.report.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class POIExcelTest {  
    
    public static void main(String[] args) throws Exception,IOException {  
        OutputStream os = new FileOutputStream("F:\\test.xls");  
        Workbook wb = new HSSFWorkbook();  
        POIExcelTest test = new POIExcelTest();  
        test.createFile(os, wb);  
    }  
      
    private void createFile(OutputStream os,Workbook wb) throws IOException{  
        int i = 0;  
        int j = 0;  
        double trans_amt = 0.00;  
        double ref_amt = 0.00;  
        String[] refundLogs = new String[2];  
        String str1 = "20110812|34234234242432|345.00|323.00";  
        String str2 = "20110504|45656464535345|231.34|231.34";  
        refundLogs[0] = str1;  
        refundLogs[1] = str2;  
        Sheet sheet = wb.createSheet("T建行退款文件");  
        Row row = sheet.createRow(0);  
        for(i=1;i<=3;i++){  
            sheet.createRow(i);  
        }  
          
        for(i=0;i<4;i++)  
            row.createCell(i);  
          
        sheet.addMergedRegion(new CellRangeAddress(0, 3, 0, 3));  
          
        Font font = wb.createFont();  
        font.setFontName("黑体");  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
          
        CellStyle cs1 = wb.createCellStyle();  
        cs1.setAlignment(CellStyle.ALIGN_CENTER);  
        cs1.setDataFormat(wb.createDataFormat().getFormat("yyyyMMdd"));  
        cs1.setFont(font);  
          
        CellStyle cs2 = wb.createCellStyle();  
        cs2.setAlignment(CellStyle.ALIGN_CENTER);  
        cs2.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));  
        cs2.setFont(font);  
          
        CellStyle cs3 = wb.createCellStyle();  
        cs3.setAlignment(CellStyle.ALIGN_CENTER);  
        cs3.setFont(font);  
          
        row = sheet.getRow(0);  
        Cell cell = row.getCell(0);  
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
        cell.setCellValue("建行运行中心：\n\t"+"现有"+refundLogs.length+"表退款交易，请配合汇付天下公司进行审核");  
          
        sheet.createRow(4);  
        row = sheet.createRow(5);  
          
        cell = row.createCell(0);  
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
        cell.setCellValue("商户编号：");  
        cell = row.createCell(1);  
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
        cell.setCellValue("45433242");  
          
        row = sheet.createRow(6);  
        cell = row.createCell(0);  
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
        cell.setCellValue("交易明细：");  
          
        row = sheet.createRow(7);  
        row.createCell(0).setCellValue("退款日期");  
        row.createCell(1).setCellValue("消费卡号");  
        row.createCell(2).setCellValue("消费金额");  
        row.createCell(3).setCellValue("退款金额");  
        for(i=0;i<4;i++)  
            row.getCell(i).setCellStyle(cs3);  
          
          
        for(i=8;i<=7+refundLogs.length;i++)  
        {  
            sheet.createRow(i);  
            for(j=0;j<4;j++)  
                sheet.getRow(i).createCell(j);  
        }  
        for(i=0;i<refundLogs.length;i++){  
            row = sheet.getRow(8+i);  
            String[] refundLog = refundLogs[i].split("\\|");  
            cell = row.getCell(0);  
            cell.setCellStyle(cs1);  
            cell.setCellValue(refundLog[0]);  
              
            cell = row.getCell(1);  
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
            cell.setCellStyle(cs3);  
            cell.setCellValue(refundLog[1]);  
              
            cell = row.getCell(2);  
            cell.setCellStyle(cs2);  
            cell.setCellValue(refundLog[2]);  
            trans_amt += Double.parseDouble(refundLog[2]);  
              
            cell = row.getCell(3);  
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);  
            cell.setCellStyle(cs2);  
            cell.setCellValue(refundLog[3]);  
            ref_amt += Double.parseDouble(refundLog[3]);  
        }  
          
        row = sheet.createRow(9+i);  
        for(i=0;i<4;i++)  
            row.createCell(i);  
        row.getCell(0).setCellValue("总计：");  
        row.getCell(2).setCellValue(trans_amt);  
        row.getCell(3).setCellValue(ref_amt);  
          
        sheet.autoSizeColumn(0);  
        sheet.autoSizeColumn(1);  
        sheet.autoSizeColumn(2);  
        sheet.autoSizeColumn(3);  
          
        wb.write(os);  
    }  
}  