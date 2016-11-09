package com.report.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class reportExcel {

	/**
	 * POI生成Excel文件
	 * @author David
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		String[] title = {"实习地点",	"学院	","专业",	"人数",
				"带队老师"	,"主要负责人",	"有无研究生",	"实习时间段",	
				"主要从事"};
		
		//创建Excel工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//创建一个工作表sheet
		Sheet sheet = workbook.createSheet();
		//创建第一行
		Row row = sheet.createRow(0);
		
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,8));
		Cell cell =  row.createCell(0);
		cell.setCellValue("2016年度湖南农业大学基地校外实习情况汇总（提前一周告知、汇总）");
	
		XSSFCellStyle cellStyle = workbook.createCellStyle(); 
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
		cell.setCellStyle(cellStyle); 
		row = sheet.createRow(1);
		
		//Cell cell = null;
		//插入第一行数据 id,name,sex
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		//追加数据
		for (int i = 2; i <= 10; i++) {
			Row nextrow = sheet.createRow(i);
			Cell cell2 = nextrow.createCell(0);
			cell2.setCellValue("a" + i);
			cell2 = nextrow.createCell(1);
			cell2.setCellValue("user" + i);
			cell2 = nextrow.createCell(2);
			cell2.setCellValue("男");
		} 
        
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(1, 10, 1,2));
		//创建一个文件
		File file = new File("E:/test.xlsx");
		try {
			file.createNewFile();
			//将Excel内容存盘
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
