package com.base.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.base.po.LandApply_view;
import com.base.po.Manger;
import com.base.po.RentMaintain;
import com.base.po.UserInfo;


public class ExcelReport{

	/*
	 * 一:土地租赁维护的Excel文件导出 (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#landRentPreserveReport()
	 */	
	public void landRentPreserveReport(List<RentMaintain> list,String filename) { // 列头信息
		String[] col_title = { "序号", "开始日期", "结束日期", "基地名", "土地编号", "租赁人",
				"申报部门", "已租用次数", "土地名称", "土地面积", "适宜从事内容", "计划从事内容",
				"租赁费用", "交费日期" };// 15个列头
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// 创建一个Excel文档对象
		Sheet sheet = workbook.createSheet();// 在上面的文档中创建Excel表单
		// 设置列的宽度
		sheet.setColumnWidth(0, 1300);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 2800);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 2000);
		sheet.setColumnWidth(8, 4500);
		sheet.setColumnWidth(9, 4500);
		sheet.setColumnWidth(10, 2500);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 2500);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 2000);

		/*
		 * 设置样式
		 */
		// 1、设置字体// 设置标题字体
		Font font_title = workbook.createFont();
		font_title.setFontName("宋体");
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
		font_title.setFontHeightInPoints((short) 18);// 设置字体大小
		// 设置列头字体
		Font font1 = workbook.createFont();
		font1.setFontName("宋体");
		font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
		font1.setFontHeightInPoints((short) 11);// 设置字体大小
		// 设置正文字体1
		Font font2 = workbook.createFont();
		font2.setFontName("宋体");
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// 设置字体粗细
		font2.setFontHeightInPoints((short) 11);// 设置字体大小
		// 2、设置样式
		// 设置标题样式
		XSSFCellStyle cs_title = workbook.createCellStyle();
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_title.setWrapText(true);// 设置使得单元格的文字按照单元格的列宽来自动！换行！显示
		cs_title.setFont(font_title);
		// 设置列头样式
		XSSFCellStyle cs1 = workbook.createCellStyle();
		cs1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs1.setWrapText(true);// 设置宽度自适应
		cs1.setFont(font1);
		// 设置正文居中样式
		XSSFCellStyle cs2 = workbook.createCellStyle();
		cs2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs2.setWrapText(true);// 设置宽度自适应
		cs2.setFont(font2);
		// 合并列 （第一行，最后一行，第一列，最后一列）
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));// 合并列-标题

		/*
		 * 插入数据操作
		 */
		// 第一排表名
		Row row1 = sheet.createRow(0);
		row1.setHeight((short) 1000);// 设置行的高度
		Cell cell_title = row1.createCell(0);
		cell_title.setAsActiveCell();
		cell_title.setCellValue("湖南农业大学土地租赁信息表");
		cell_title.setCellStyle(cs_title);
		// 第二排填相应列头名
		Row row2 = sheet.createRow(1);
		for (int i = 0; i < col_title.length; i++) {
			Cell cell_line = row2.createCell(i);
			cell_line.setCellValue(col_title[i]);
			cell_line.setCellStyle(cs1);
		}
		// 三排之后
		/*
		 * !!!!!!李彩注意这里是需要土地租赁的数据list的类名和方法名，然后后面还有每条数据中获取单个数值的方法!!!!!!!!!!!!!!!!
		 * !!!!!!
		 */
		// 用户ID，以后要从后台获取的！！！！

		/*
		 * 遍历list中取得的数据
		 */
		for (int i = 0; i < list.size(); i++) {
			RentMaintain line_data = list.get(i);// 取得list集合中一条数据
			int line = i + 2;// 多少行
			Row row_line = sheet.createRow(line);// 创建第line行
			// 第一列序号 自动增加1
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(cs2);
			// 第二列开始日期
			Cell cell1 = row_line.createCell(1);
			// !!!!!!!!!!下面这行代码的line_data.getStartTime()方法获取的值是要对应上面表格顺序的哈李彩，
			// !!!!!!!!!!!然后后面每列都是同此更改!!!!!!!!!!!!!!!
			cell1.setCellValue(line_data.getStartTime());
			cell1.setCellStyle(cs2);
			// 第三列结束日期
			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue(line_data.getEndTime());
			cell2.setCellStyle(cs2);
			// 第四列基地名
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue(line_data.getBname());
			cell3.setCellStyle(cs2);
			// 第五列土地编号
			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue(line_data.getLid());
			cell4.setCellStyle(cs2);
			// 第六列租赁人
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue(line_data.getName());
			cell5.setCellStyle(cs2);
			// 第七列申报部门
			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue(line_data.getDeptName());
			cell6.setCellStyle(cs2);
			// 第八列已租用次数
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue(line_data.getTimes());
			cell7.setCellStyle(cs2);
			
			/*// 第九列种植内容
			Cell cell8 = row_line.createCell(8);
			cell8.setCellValue(line_data.getPlanting());
			cell8.setCellStyle(cs2);*/
			
			// 第十列土地名称
			Cell cell8 = row_line.createCell(8);
			cell8.setCellValue(line_data.getLandname());
			cell8.setCellStyle(cs2);
			// 第十一列土地面积
			Cell cell9 = row_line.createCell(9);
			cell9.setCellValue(line_data.getLandArea());
			cell9.setCellStyle(cs2);
			// 第十二列适宜从事内容
			Cell cell10 = row_line.createCell(10);
			cell10.setCellValue(line_data.getAptplanting());
			cell10.setCellStyle(cs2);
			// 第十三列计划从事内容
			Cell cell11 = row_line.createCell(11);
			cell11.setCellValue(line_data.getPlanting());
			cell11.setCellStyle(cs2);
			// 第十四列租赁费用
			Cell cell12 = row_line.createCell(12);
			cell12.setCellValue(line_data.getRentMoney());
			cell12.setCellStyle(cs2);
			// 第十五列交费日期
			Cell cell13 = row_line.createCell(13);
			cell13.setCellValue(line_data.getChargeDate());
			cell13.setCellStyle(cs2);
		}
		// 建立Excel文件
		// !!!!!!!!!李彩啊~~下面的路径是将生成的文件放到服务器的路径!!!!!!!!!!!!!!!!!!!
		File file = new File(filename);
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 二:实习基地维护的Excel文件导出 (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#workLandPreserveReport()
	 */
	/*@Override
	public void workLandPreserveReport() {
		// 列头信息！！！！李彩这是我按心雨的设计弄的，如果你要改什么就改！！！！！！！
		String[] col_title = { "序号", "基地名称", "基地类型", "申报部门", "一次性可承担实习学生人数",
				"土地面积/亩", "建筑面积/平方米", "申请结果" };// 8个列头
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// 创建一个Excel文档对象
		Sheet sheet = workbook.createSheet();// 在上面的文档中创建Excel表单
		// 设置列的宽度
		sheet.setColumnWidth(0, 1000);
		sheet.setColumnWidth(1, 3500);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3500);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 3000);
		
		 * 设置样式
		 
		// 1、设置字体// 设置标题字体
		Font font_title = workbook.createFont();
		font_title.setFontName("宋体");
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
		font_title.setFontHeightInPoints((short) 18);// 设置字体大小
		// 设置列头字体
		Font font1 = workbook.createFont();
		font1.setFontName("宋体");
		font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
		font1.setFontHeightInPoints((short) 11);// 设置字体大小
		// 设置正文字体1
		Font font2 = workbook.createFont();
		font2.setFontName("宋体");
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// 设置字体粗细
		font2.setFontHeightInPoints((short) 11);// 设置字体大小
		// 2、设置样式
		// 设置标题样式
		XSSFCellStyle cs_title = workbook.createCellStyle();
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_title.setWrapText(true);// 设置使得单元格的文字按照单元格的列宽来自动！换行！显示
		cs_title.setFont(font_title);
		// 设置列头样式
		XSSFCellStyle cs1 = workbook.createCellStyle();
		cs1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs1.setWrapText(true);// 设置宽度自适应
		cs1.setFont(font1);
		// 设置正文居中样式
		XSSFCellStyle cs2 = workbook.createCellStyle();
		cs2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs2.setWrapText(true);// 设置宽度自适应
		cs2.setFont(font2);
		// 合并列 （第一行，最后一行，第一列，最后一列）
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));// 合并列-标题
		
		 * 插入数据操作
		 
		// 表名（第一排占三行）
		Row row1 = sheet.createRow(0);
		row1.setHeight((short) 1000);
		Cell cell_title = row1.createCell(0);
		cell_title.setAsActiveCell();
		cell_title.setCellValue("湖南农业大学实习基地信息表");
		cell_title.setCellStyle(cs_title);
		// 第二排填相应列头名
		Row row2 = sheet.createRow(1);
		for (int i = 0; i < col_title.length; i++) {
			Cell cell_line = row2.createCell(i);
			cell_line.setCellValue(col_title[i]);
			cell_line.setCellStyle(cs1);
		}
		// 三排之后
		
		 * !!!!!!李彩注意这里是需要土地租赁的数据list的类名和方法名，然后后面还有每条数据中获取单个数值的方法!!!!!!!!!!!!!!!!
		 * !!!!!!
		 
		// 用户ID，以后要从后台获取的！！！！
		@SuppressWarnings("rawtypes")
		String applicantId = "201440509";
		@SuppressWarnings("unchecked")
		List<LandApply_view> list = landApplyServiceImpl
				.getselfApply(applicantId);
		
		 * 遍历list中取得的数据
		 
		for (int i = 0; i < list.size(); i++) {
			LandApply_view line_data = list.get(i);// 取得list集合中一条数据
			int line = i + 2;// 多少行
			Row row_line = sheet.createRow(line);// 创建第line行
			// 第一列序号自动增加1
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(cs2);
			// String[] col_title = { "序号","基地名称","基地类型","申报部门","一次性可承担实习学生人数",
			// "土地面积/亩","建筑面积/平方米","申请结果"};// 8个列头
			// 第二列基地名称
			Cell cell1 = row_line.createCell(1);
			// !!!!!!!!!!下面这行代码的line_data.getStartTime()方法获取的值是要对应上面表格顺序的哈李彩，
			// !!!!!!!!!!!然后后面每列都是同此更改!!!!!!!!!!!!!!!
			cell1.setCellValue(line_data.getStartTime());
			cell1.setCellStyle(cs2);
			// 第三列基地类型
			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue(line_data.getEndTime());
			cell2.setCellStyle(cs2);
			// 第四列申报部门
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue(line_data.getBname());
			cell3.setCellStyle(cs2);
			// 第五列一次性可承担实习学生人数
			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue(line_data.getLid());
			cell4.setCellStyle(cs2);
			// 第六列土地面积/亩
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue(line_data.getName());
			cell5.setCellStyle(cs2);
			// 第七列建筑面积/平方米
			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue(line_data.getCollege());
			cell6.setCellStyle(cs2);
			// 第八列申请结果
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue(line_data.getMajor_oriented());
			cell7.setCellStyle(cs2);
		}
		// 建立Excel文件
		// !!!!!!!!!李彩啊~~下面的路径是将生成的文件放到服务器的路径!!!!!!!!!!!!!!!!!!!
		File file = new File("E://workLandPreserveReport.xlsx");
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	 * 三:实习计划维护的Excel文件导出 (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#workPlanPreserveReport()
	 
	@Override
	public void workPlanPreserveReport() {// 列头信息
		String[] col_title = { "序号", "实习基地", "联系电话", "实习开始时间", "实习结束时间",
				"实习类型", "实习学院", "实习地点", "实习指导老师", "实习项目名称", "年级专业班级", "实习人数",
				"实习内容摘要及进程安排" };
		// 13个
		// 14个列头
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// 创建一个Excel文档对象
		Sheet sheet = workbook.createSheet();// 在上面的文档中创建Excel表单
		// 设置列的宽度
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 4500);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 3500);
		sheet.setColumnWidth(4, 3500);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 4500);
		sheet.setColumnWidth(8, 3500);
		sheet.setColumnWidth(9, 4500);
		sheet.setColumnWidth(10, 4500);
		sheet.setColumnWidth(11, 2500);
		sheet.setColumnWidth(12, 7000);
		
		 * 设置样式
		 
		// 1、设置字体// 设置标题字体
		Font font_title = workbook.createFont();
		font_title.setFontName("宋体");
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
		font_title.setFontHeightInPoints((short) 18);// 设置字体大小
		// 设置列头字体
		Font font1 = workbook.createFont();
		font1.setFontName("宋体");
		font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
		font1.setFontHeightInPoints((short) 11);// 设置字体大小
		// 设置正文字体1
		Font font2 = workbook.createFont();
		font2.setFontName("宋体");
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// 设置字体粗细
		font2.setFontHeightInPoints((short) 11);// 设置字体大小
		// 2、设置样式
		// 设置标题样式
		XSSFCellStyle cs_title = workbook.createCellStyle();
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_title.setWrapText(true);// 设置使得单元格的文字按照单元格的列宽来自动！换行！显示
		cs_title.setFont(font_title);
		// 设置列头样式
		XSSFCellStyle cs1 = workbook.createCellStyle();
		cs1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs1.setWrapText(true);// 设置宽度自适应
		cs1.setFont(font1);
		// 设置正文居中样式
		XSSFCellStyle cs2 = workbook.createCellStyle();
		cs2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs2.setWrapText(true);// 设置宽度自适应
		cs2.setFont(font2);
		// 合并列 （第一行，最后一行，第一列，最后一列）
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));// 合并列-标题
		
		 * 插入数据操作
		 
		// 表名（第一排占三行）
		Row row1 = sheet.createRow(0);
		row1.setHeight((short) 1000);
		Cell cell_title = row1.createCell(0);
		cell_title.setAsActiveCell();
		cell_title.setCellValue("湖南农业大学实习计划信息表");
		cell_title.setCellStyle(cs_title);
		// 第二排填相应列头名
		Row row2 = sheet.createRow(1);
		for (int i = 0; i < col_title.length; i++) {
			Cell cell_line = row2.createCell(i);
			cell_line.setCellValue(col_title[i]);
			cell_line.setCellStyle(cs1);
		}
		// 三排之后
		
		 * !!!!!!李彩注意这里是需要土地租赁的数据list的类名和方法名，然后后面还有每条数据中获取单个数值的方法!!!!!!!!!!!!!!!!
		 * !!!!!!
		 
		// 用户ID，以后要从后台获取的！！！！
		@SuppressWarnings("rawtypes")
		String applicantId = "201440509";
		@SuppressWarnings("unchecked")
		List<LandApply_view> list = landApplyServiceImpl
				.getselfApply(applicantId);
		
		 * 遍历list中取得的数据
		 
		for (int i = 0; i < list.size(); i++) {
			LandApply_view line_data = list.get(i);// 取得list集合中一条数据
			int line = i + 2;// 多少行
			Row row_line = sheet.createRow(line);// 创建第line行
			// 第一列序号自动增加1
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(cs2);
			// String[] col_title = { "序号", "实习基地", "联系电话", "实习开始时间", "实习结束时间",
			// "实习类型", "实习学院", "实习地点", "实习指导老师", "实习项目名称",
			// "年级专业班级", "实习人数","实习内容摘要及进程安排" };
			// // 13个列头
			// 第二列实习基地
			Cell cell1 = row_line.createCell(1);
			// !!!!!!!!!!下面这行代码的line_data.getStartTime()方法获取的值是要对应上面表格顺序的哈李彩，
			// !!!!!!!!!!!然后后面每列都是同此更改!!!!!!!!!!!!!!!
			cell1.setCellValue(line_data.getStartTime());
			cell1.setCellStyle(cs2);
			// 第三列联系电话
			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue(line_data.getEndTime());
			cell2.setCellStyle(cs2);
			// 第四实习开始时间
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue(line_data.getBname());
			cell3.setCellStyle(cs2);
			// 第五列实习结束时间
			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue(line_data.getLid());
			cell4.setCellStyle(cs2);
			// 第六列实习类型
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue(line_data.getName());
			cell5.setCellStyle(cs2);
			// 第七列实习学院
			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue(line_data.getCollege());
			cell6.setCellStyle(cs2);
			// 第八列实习地点
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue(line_data.getMajor_oriented());
			cell7.setCellStyle(cs2);
			// 第九列实习指导老师
			Cell cell8 = row_line.createCell(8);
			cell8.setCellValue(line_data.getBuildingArea());
			cell8.setCellStyle(cs2);
			// 第十列实习项目名称
			Cell cell9 = row_line.createCell(9);
			cell9.setCellValue(line_data.getLandArea());
			cell9.setCellStyle(cs2);
			// 第十一列年级专业班级
			Cell cell10 = row_line.createCell(10);
			cell10.setCellValue(line_data.getAptPlanting());
			cell10.setCellStyle(cs2);
			// 第十二列实习人数
			Cell cell11 = row_line.createCell(11);
			cell11.setCellValue(line_data.getAfford());
			cell11.setCellStyle(cs2);
			// 第十三列实习内容摘要及进程安排
			Cell cell12 = row_line.createCell(12);
			cell12.setCellValue(line_data.getTenancy());
			cell12.setCellStyle(cs2);
		}
		// 建立Excel文件
		// !!!!!!!!!李彩啊~~下面的路径是将生成的文件放到服务器的路径!!!!!!!!!!!!!!!!!!!
		File file = new File("E://workPlanPreserveReport.xlsx");
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
*/
	/*
	 * 四:系统用户维护的Excel文件导出 (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#systemUsersPreserveReport()
	 */
	public void exportPersonInfo(List<Manger> list,String fileName) {
		// 列头信息
		/*"序号", "员工编号", "身份属性", "姓名", "性别", "员工类型",
		"出生日期", "身份证号码", "联系电话", "部门", "原工作单位", "来校工作时间", "参加工作时间",
		"用工形式", "户口性质", "密码" */
          String[] col_title = { "序号", "员工编号", "身份属性", "姓名", "性别", "员工类型",
				"出生日期", "身份证号码", "联系电话", "部门"};// 16
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// 创建一个Excel文档对象
		Sheet sheet = workbook.createSheet();// 在上面的文档中创建Excel表单
		// 设置列的宽度
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 2500);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 2500);
		sheet.setColumnWidth(4, 1500);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 5500);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		
		// * 设置样式
		 
		// 1、设置字体// 设置标题字体
		Font font_title = workbook.createFont();
		font_title.setFontName("宋体");
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
		font_title.setFontHeightInPoints((short) 18);// 设置字体大小
		// 设置列头字体
		Font font1 = workbook.createFont();
		font1.setFontName("宋体");
		font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
		font1.setFontHeightInPoints((short) 11);// 设置字体大小
		// 设置正文字体1
		Font font2 = workbook.createFont();
		font2.setFontName("宋体");
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// 设置字体粗细
		font2.setFontHeightInPoints((short) 11);// 设置字体大小
		// 2、设置样式
		// 设置标题样式
		XSSFCellStyle cs_title = workbook.createCellStyle();
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_title.setWrapText(true);// 设置使得单元格的文字按照单元格的列宽来自动！换行！显示
		cs_title.setFont(font_title);
		// 设置列头样式
		XSSFCellStyle cs1 = workbook.createCellStyle();
		cs1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs1.setWrapText(true);// 设置宽度自适应
		cs1.setFont(font1);
		// 设置正文居中样式
		XSSFCellStyle cs2 = workbook.createCellStyle();
		cs2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
		cs2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs2.setWrapText(true);// 设置宽度自适应
		cs2.setFont(font2);
		// 合并列 （第一行，最后一行，第一列，最后一列）
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));// 合并列-标题
		
		// * 插入数据操作
		 
		// 表名（第一排占三行）
		Row row1 = sheet.createRow(0);
		row1.setHeight((short) 1000);
		Cell cell_title = row1.createCell(0);
		cell_title.setAsActiveCell();
		cell_title.setCellValue("湖南农业大学系统用户信息表");
		cell_title.setCellStyle(cs_title);
		// 第二排填相应列头名
		Row row2 = sheet.createRow(1);
		for (int i = 0; i < col_title.length; i++) {
			Cell cell_line = row2.createCell(i);
			cell_line.setCellValue(col_title[i]);
			cell_line.setCellStyle(cs1);
		}
		/*// 三排之后
		
		 * !!!!!!李彩注意这里是需要土地租赁的数据list的类名和方法名，然后后面还有每条数据中获取单个数值的方法!!!!!!!!!!!!!!!!
		 * !!!!!!
		 
		// 用户ID，以后要从后台获取的！！！！*/		
		 //* 遍历list中取得的数据
		 
		for (int i = 0; i < list.size(); i++) {
			Manger line_data = list.get(i);// 取得list集合中一条数据
			int line = i + 2;// 多少行
			Row row_line = sheet.createRow(line);// 创建第line行
			// 第一列序号自动增加1
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(cs2);
			// String[] col_title = { "序号","员工编号","身份属性","姓名","性别","员工类型",
			// "出生日期","身份证号码","联系电话","部门","原工作单位",
			// "来校工作时间","参加工作时间","用工形式","户口性质","密码"};//16
			// 第二列员工编号
			Cell cell1 = row_line.createCell(1);
			// !!!!!!!!!!下面这行代码的line_data.getStartTime()方法获取的值是要对应上面表格顺序的哈李彩，
			// !!!!!!!!!!!然后后面每列都是同此更改!!!!!!!!!!!!!!!
			cell1.setCellValue(line_data.getId());
			cell1.setCellStyle(cs2);
			// 第三列身份属性
			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue(line_data.getAttritube());
			cell2.setCellStyle(cs2);
			// 第四列姓名
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue(line_data.getUsername());
			cell3.setCellStyle(cs2);
			// 第五列性别
			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue(line_data.getSex());
			cell4.setCellStyle(cs2);
			// 第六列员工类型
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue(line_data.getCategory());
			cell5.setCellStyle(cs2);
			// 第七列出生日期
			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue(line_data.getBirth());
			cell6.setCellStyle(cs2);
			// 第八列身份证号码
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue(line_data.getIdcard());
			cell7.setCellStyle(cs2);
			// 第九列联系电话
			Cell cell8 = row_line.createCell(8);
			cell8.setCellValue(line_data.getTelephone());
			cell8.setCellStyle(cs2);
			// 第十列部门
			Cell cell9 = row_line.createCell(9);
			cell9.setCellValue(line_data.getDept());
			cell9.setCellStyle(cs2);
			/*// 第十一列原工作单位
			Cell cell10 = row_line.createCell(10);
			cell10.setCellValue(line_data.getAptPlanting());
			cell10.setCellStyle(cs2);
			// 第十二列来校工作时间
			Cell cell11 = row_line.createCell(11);
			cell11.setCellValue(line_data.getAfford());
			cell11.setCellStyle(cs2);
			// 第十三列参加工作时间
			Cell cell12 = row_line.createCell(12);
			cell12.setCellValue(line_data.getDescp());
			cell12.setCellStyle(cs2);
			// 第十四列用工形式
			Cell cell13 = row_line.createCell(13);
			cell13.setCellValue(line_data.getTenancy());
			cell13.setCellStyle(cs2);
			// 第十五列户口性质
			Cell cell14 = row_line.createCell(14);
			cell14.setCellValue(line_data.getPlanting());
			cell14.setCellStyle(cs2);
			// 第十六列密码
			Cell cell15 = row_line.createCell(15);
			cell15.setCellValue(line_data.getPlanting());
			cell15.setCellStyle(cs2);*/
		}
		// 建立Excel文件
		// !!!!!!!!!李彩啊~~下面的路径是将生成的文件放到服务器的路径!!!!!!!!!!!!!!!!!!!
		File file = new File(fileName);
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	 /*
     * 获取项目的根目录
     * 因为tomcat和weblogic获取的根目录不一致，所以需要此方法
     */
    public static String getWebRootUrl(HttpServletRequest request,String filename){
        String fileDirPath = request.getSession().getServletContext().getRealPath(filename);
        if(fileDirPath == null){
            //如果返回为空，则表示服务器为weblogic，则需要使用另外的方法
            try{
                return request.getSession().getServletContext().getResource(filename).getFile();
            }catch(MalformedURLException e){
                e.printStackTrace();
            }
        }
            
        return fileDirPath;
        
    }
    
    
    //方法功能描述:       判断是否是IE浏览器   
    public static boolean isMSBrowser(HttpServletRequest request) {  
        String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};  
        String userAgent = request.getHeader("User-Agent");  
        for (String signal : IEBrowserSignals) {  
            if (userAgent.contains(signal)){  
                return true;  
            }  
        }  
        return false;  
    }  

}
