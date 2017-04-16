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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.base.po.AllPlan;
import com.base.po.Classcourse;
import com.base.po.ExportBase;
import com.base.po.LandApply_view;
import com.base.po.MaintainApplys;
import com.base.po.Prabaseinfo;
import com.base.po.Manger;
import com.base.po.PracticeCollection;
import com.base.po.RentMaintain;
import com.base.po.UserInfo;

public class ExcelReport {

	/*
	 * 一:土地租赁维护的Excel文件导出 (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#landRentPreserveReport()
	 */
	public void landRentPreserveReport(List<RentMaintain> list, String filename) { // 列头信息
		String[] col_title = { "序号", "开始日期", "结束日期", "基地名", "土地编号", "租赁人",
				"申报部门", "已租用次数", "土地名称", "土地面积", "适宜从事内容", "计划从事内容", "租赁费用",
				"交费日期" };// 15个列头
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

			/*
			 * // 第九列种植内容 Cell cell8 = row_line.createCell(8);
			 * cell8.setCellValue(line_data.getPlanting());
			 * cell8.setCellStyle(cs2);
			 */

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
	/*
	 * @Override public void workLandPreserveReport() { //
	 * 列头信息！！！！李彩这是我按心雨的设计弄的，如果你要改什么就改！！！！！！！ String[] col_title = { "序号",
	 * "基地名称", "基地类型", "申报部门", "一次性可承担实习学生人数", "土地面积/亩", "建筑面积/平方米", "申请结果" };//
	 * 8个列头
	 * 
	 * @SuppressWarnings("resource") XSSFWorkbook workbook = new
	 * XSSFWorkbook();// 创建一个Excel文档对象 Sheet sheet = workbook.createSheet();//
	 * 在上面的文档中创建Excel表单 // 设置列的宽度 sheet.setColumnWidth(0, 1000);
	 * sheet.setColumnWidth(1, 3500); sheet.setColumnWidth(2, 3000);
	 * sheet.setColumnWidth(3, 3500); sheet.setColumnWidth(4, 4000);
	 * sheet.setColumnWidth(5, 4000); sheet.setColumnWidth(6, 4500);
	 * sheet.setColumnWidth(7, 3000);
	 * 
	 * 设置样式
	 * 
	 * // 1、设置字体// 设置标题字体 Font font_title = workbook.createFont();
	 * font_title.setFontName("宋体");
	 * font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
	 * font_title.setFontHeightInPoints((short) 18);// 设置字体大小 // 设置列头字体 Font
	 * font1 = workbook.createFont(); font1.setFontName("宋体");
	 * font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
	 * font1.setFontHeightInPoints((short) 11);// 设置字体大小 // 设置正文字体1 Font font2 =
	 * workbook.createFont(); font2.setFontName("宋体");
	 * font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// 设置字体粗细
	 * font2.setFontHeightInPoints((short) 11);// 设置字体大小 // 2、设置样式 // 设置标题样式
	 * XSSFCellStyle cs_title = workbook.createCellStyle();
	 * cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
	 * cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
	 * // 依次设置底、左、右、上边框 cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	 * cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	 * cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
	 * cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
	 * cs_title.setWrapText(true);// 设置使得单元格的文字按照单元格的列宽来自动！换行！显示
	 * cs_title.setFont(font_title); // 设置列头样式 XSSFCellStyle cs1 =
	 * workbook.createCellStyle();
	 * cs1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
	 * cs1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中 //
	 * 依次设置底、左、右、上边框 cs1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	 * cs1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	 * cs1.setBorderRight(XSSFCellStyle.BORDER_THIN);
	 * cs1.setBorderTop(XSSFCellStyle.BORDER_THIN); cs1.setWrapText(true);//
	 * 设置宽度自适应 cs1.setFont(font1); // 设置正文居中样式 XSSFCellStyle cs2 =
	 * workbook.createCellStyle();
	 * cs2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
	 * cs2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中 //
	 * 依次设置底、左、右、上边框 cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	 * cs2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	 * cs2.setBorderRight(XSSFCellStyle.BORDER_THIN);
	 * cs2.setBorderTop(XSSFCellStyle.BORDER_THIN); cs2.setWrapText(true);//
	 * 设置宽度自适应 cs2.setFont(font2); // 合并列 （第一行，最后一行，第一列，最后一列）
	 * sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));// 合并列-标题
	 * 
	 * 插入数据操作
	 * 
	 * // 表名（第一排占三行） Row row1 = sheet.createRow(0); row1.setHeight((short)
	 * 1000); Cell cell_title = row1.createCell(0);
	 * cell_title.setAsActiveCell(); cell_title.setCellValue("湖南农业大学实习基地信息表");
	 * cell_title.setCellStyle(cs_title); // 第二排填相应列头名 Row row2 =
	 * sheet.createRow(1); for (int i = 0; i < col_title.length; i++) { Cell
	 * cell_line = row2.createCell(i); cell_line.setCellValue(col_title[i]);
	 * cell_line.setCellStyle(cs1); } // 三排之后
	 * 
	 * !!!!!!李彩注意这里是需要土地租赁的数据list的类名和方法名，然后后面还有每条数据中获取单个数值的方法!!!!!!!!!!!!!!!!
	 * !!!!!!
	 * 
	 * // 用户ID，以后要从后台获取的！！！！
	 * 
	 * @SuppressWarnings("rawtypes") String applicantId = "201440509";
	 * 
	 * @SuppressWarnings("unchecked") List<LandApply_view> list =
	 * landApplyServiceImpl .getselfApply(applicantId);
	 * 
	 * 遍历list中取得的数据
	 * 
	 * for (int i = 0; i < list.size(); i++) { LandApply_view line_data =
	 * list.get(i);// 取得list集合中一条数据 int line = i + 2;// 多少行 Row row_line =
	 * sheet.createRow(line);// 创建第line行 // 第一列序号自动增加1 Cell cell0 =
	 * row_line.createCell(0); cell0.setCellValue(i + 1);
	 * cell0.setCellStyle(cs2); // String[] col_title = {
	 * "序号","基地名称","基地类型","申报部门","一次性可承担实习学生人数", //
	 * "土地面积/亩","建筑面积/平方米","申请结果"};// 8个列头 // 第二列基地名称 Cell cell1 =
	 * row_line.createCell(1); //
	 * !!!!!!!!!!下面这行代码的line_data.getStartTime()方法获取的值是要对应上面表格顺序的哈李彩， //
	 * !!!!!!!!!!!然后后面每列都是同此更改!!!!!!!!!!!!!!!
	 * cell1.setCellValue(line_data.getStartTime()); cell1.setCellStyle(cs2); //
	 * 第三列基地类型 Cell cell2 = row_line.createCell(2);
	 * cell2.setCellValue(line_data.getEndTime()); cell2.setCellStyle(cs2); //
	 * 第四列申报部门 Cell cell3 = row_line.createCell(3);
	 * cell3.setCellValue(line_data.getBname()); cell3.setCellStyle(cs2); //
	 * 第五列一次性可承担实习学生人数 Cell cell4 = row_line.createCell(4);
	 * cell4.setCellValue(line_data.getLid()); cell4.setCellStyle(cs2); //
	 * 第六列土地面积/亩 Cell cell5 = row_line.createCell(5);
	 * cell5.setCellValue(line_data.getName()); cell5.setCellStyle(cs2); //
	 * 第七列建筑面积/平方米 Cell cell6 = row_line.createCell(6);
	 * cell6.setCellValue(line_data.getCollege()); cell6.setCellStyle(cs2); //
	 * 第八列申请结果 Cell cell7 = row_line.createCell(7);
	 * cell7.setCellValue(line_data.getMajor_oriented());
	 * cell7.setCellStyle(cs2); } // 建立Excel文件 //
	 * !!!!!!!!!李彩啊~~下面的路径是将生成的文件放到服务器的路径!!!!!!!!!!!!!!!!!!! File file = new
	 * File("E://workLandPreserveReport.xlsx"); try { file.createNewFile();
	 * FileOutputStream stream = FileUtils.openOutputStream(file);
	 * workbook.write(stream); stream.close(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * 
	 * 三:实习计划维护的Excel文件导出 (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#workPlanPreserveReport()
	 * 
	 * @Override public void workPlanPreserveReport() {// 列头信息 String[]
	 * col_title = { "序号", "实习基地", "联系电话", "实习开始时间", "实习结束时间", "实习类型", "实习学院",
	 * "实习地点", "实习指导老师", "实习项目名称", "年级专业班级", "实习人数", "实习内容摘要及进程安排" }; // 13个 //
	 * 14个列头
	 * 
	 * @SuppressWarnings("resource") XSSFWorkbook workbook = new
	 * XSSFWorkbook();// 创建一个Excel文档对象 Sheet sheet = workbook.createSheet();//
	 * 在上面的文档中创建Excel表单 // 设置列的宽度 sheet.setColumnWidth(0, 1500);
	 * sheet.setColumnWidth(1, 4500); sheet.setColumnWidth(2, 4000);
	 * sheet.setColumnWidth(3, 3500); sheet.setColumnWidth(4, 3500);
	 * sheet.setColumnWidth(5, 3000); sheet.setColumnWidth(6, 4500);
	 * sheet.setColumnWidth(7, 4500); sheet.setColumnWidth(8, 3500);
	 * sheet.setColumnWidth(9, 4500); sheet.setColumnWidth(10, 4500);
	 * sheet.setColumnWidth(11, 2500); sheet.setColumnWidth(12, 7000);
	 * 
	 * 设置样式
	 * 
	 * // 1、设置字体// 设置标题字体 Font font_title = workbook.createFont();
	 * font_title.setFontName("宋体");
	 * font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
	 * font_title.setFontHeightInPoints((short) 18);// 设置字体大小 // 设置列头字体 Font
	 * font1 = workbook.createFont(); font1.setFontName("宋体");
	 * font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
	 * font1.setFontHeightInPoints((short) 11);// 设置字体大小 // 设置正文字体1 Font font2 =
	 * workbook.createFont(); font2.setFontName("宋体");
	 * font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// 设置字体粗细
	 * font2.setFontHeightInPoints((short) 11);// 设置字体大小 // 2、设置样式 // 设置标题样式
	 * XSSFCellStyle cs_title = workbook.createCellStyle();
	 * cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
	 * cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
	 * // 依次设置底、左、右、上边框 cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	 * cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	 * cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
	 * cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
	 * cs_title.setWrapText(true);// 设置使得单元格的文字按照单元格的列宽来自动！换行！显示
	 * cs_title.setFont(font_title); // 设置列头样式 XSSFCellStyle cs1 =
	 * workbook.createCellStyle();
	 * cs1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
	 * cs1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中 //
	 * 依次设置底、左、右、上边框 cs1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	 * cs1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	 * cs1.setBorderRight(XSSFCellStyle.BORDER_THIN);
	 * cs1.setBorderTop(XSSFCellStyle.BORDER_THIN); cs1.setWrapText(true);//
	 * 设置宽度自适应 cs1.setFont(font1); // 设置正文居中样式 XSSFCellStyle cs2 =
	 * workbook.createCellStyle();
	 * cs2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体水平居中
	 * cs2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中 //
	 * 依次设置底、左、右、上边框 cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	 * cs2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	 * cs2.setBorderRight(XSSFCellStyle.BORDER_THIN);
	 * cs2.setBorderTop(XSSFCellStyle.BORDER_THIN); cs2.setWrapText(true);//
	 * 设置宽度自适应 cs2.setFont(font2); // 合并列 （第一行，最后一行，第一列，最后一列）
	 * sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));// 合并列-标题
	 * 
	 * 插入数据操作
	 * 
	 * // 表名（第一排占三行） Row row1 = sheet.createRow(0); row1.setHeight((short)
	 * 1000); Cell cell_title = row1.createCell(0);
	 * cell_title.setAsActiveCell(); cell_title.setCellValue("湖南农业大学实习计划信息表");
	 * cell_title.setCellStyle(cs_title); // 第二排填相应列头名 Row row2 =
	 * sheet.createRow(1); for (int i = 0; i < col_title.length; i++) { Cell
	 * cell_line = row2.createCell(i); cell_line.setCellValue(col_title[i]);
	 * cell_line.setCellStyle(cs1); } // 三排之后
	 * 
	 * !!!!!!李彩注意这里是需要土地租赁的数据list的类名和方法名，然后后面还有每条数据中获取单个数值的方法!!!!!!!!!!!!!!!!
	 * !!!!!!
	 * 
	 * // 用户ID，以后要从后台获取的！！！！
	 * 
	 * @SuppressWarnings("rawtypes") String applicantId = "201440509";
	 * 
	 * @SuppressWarnings("unchecked") List<LandApply_view> list =
	 * landApplyServiceImpl .getselfApply(applicantId);
	 * 
	 * 遍历list中取得的数据
	 * 
	 * for (int i = 0; i < list.size(); i++) { LandApply_view line_data =
	 * list.get(i);// 取得list集合中一条数据 int line = i + 2;// 多少行 Row row_line =
	 * sheet.createRow(line);// 创建第line行 // 第一列序号自动增加1 Cell cell0 =
	 * row_line.createCell(0); cell0.setCellValue(i + 1);
	 * cell0.setCellStyle(cs2); // String[] col_title = { "序号", "实习基地", "联系电话",
	 * "实习开始时间", "实习结束时间", // "实习类型", "实习学院", "实习地点", "实习指导老师", "实习项目名称", //
	 * "年级专业班级", "实习人数","实习内容摘要及进程安排" }; // // 13个列头 // 第二列实习基地 Cell cell1 =
	 * row_line.createCell(1); //
	 * !!!!!!!!!!下面这行代码的line_data.getStartTime()方法获取的值是要对应上面表格顺序的哈李彩， //
	 * !!!!!!!!!!!然后后面每列都是同此更改!!!!!!!!!!!!!!!
	 * cell1.setCellValue(line_data.getStartTime()); cell1.setCellStyle(cs2); //
	 * 第三列联系电话 Cell cell2 = row_line.createCell(2);
	 * cell2.setCellValue(line_data.getEndTime()); cell2.setCellStyle(cs2); //
	 * 第四实习开始时间 Cell cell3 = row_line.createCell(3);
	 * cell3.setCellValue(line_data.getBname()); cell3.setCellStyle(cs2); //
	 * 第五列实习结束时间 Cell cell4 = row_line.createCell(4);
	 * cell4.setCellValue(line_data.getLid()); cell4.setCellStyle(cs2); //
	 * 第六列实习类型 Cell cell5 = row_line.createCell(5);
	 * cell5.setCellValue(line_data.getName()); cell5.setCellStyle(cs2); //
	 * 第七列实习学院 Cell cell6 = row_line.createCell(6);
	 * cell6.setCellValue(line_data.getCollege()); cell6.setCellStyle(cs2); //
	 * 第八列实习地点 Cell cell7 = row_line.createCell(7);
	 * cell7.setCellValue(line_data.getMajor_oriented());
	 * cell7.setCellStyle(cs2); // 第九列实习指导老师 Cell cell8 =
	 * row_line.createCell(8); cell8.setCellValue(line_data.getBuildingArea());
	 * cell8.setCellStyle(cs2); // 第十列实习项目名称 Cell cell9 =
	 * row_line.createCell(9); cell9.setCellValue(line_data.getLandArea());
	 * cell9.setCellStyle(cs2); // 第十一列年级专业班级 Cell cell10 =
	 * row_line.createCell(10); cell10.setCellValue(line_data.getAptPlanting());
	 * cell10.setCellStyle(cs2); // 第十二列实习人数 Cell cell11 =
	 * row_line.createCell(11); cell11.setCellValue(line_data.getAfford());
	 * cell11.setCellStyle(cs2); // 第十三列实习内容摘要及进程安排 Cell cell12 =
	 * row_line.createCell(12); cell12.setCellValue(line_data.getTenancy());
	 * cell12.setCellStyle(cs2); } // 建立Excel文件 //
	 * !!!!!!!!!李彩啊~~下面的路径是将生成的文件放到服务器的路径!!!!!!!!!!!!!!!!!!! File file = new
	 * File("E://workPlanPreserveReport.xlsx"); try { file.createNewFile();
	 * FileOutputStream stream = FileUtils.openOutputStream(file);
	 * workbook.write(stream); stream.close(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
	/*
	 * 四:系统用户维护的Excel文件导出 (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#systemUsersPreserveReport()
	 */
	public void exportPersonInfo(List<Manger> list, String fileName) {
		// 列头信息
		/*
		 * "序号", "员工编号", "身份属性", "姓名", "性别", "员工类型", "出生日期", "身份证号码", "联系电话",
		 * "部门", "原工作单位", "来校工作时间", "参加工作时间", "用工形式", "户口性质", "密码"
		 */
		String[] col_title = { "序号", "员工编号", "身份属性", "姓名", "性别", "员工类型",
				"出生日期", "身份证号码", "联系电话", "部门", "专业", "职称" };// 16
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
		/*
		 * // 三排之后
		 * 
		 * !!!!!!李彩注意这里是需要土地租赁的数据list的类名和方法名，然后后面还有每条数据中获取单个数值的方法!!!!!!!!!!!!!!!!
		 * !!!!!!
		 * 
		 * // 用户ID，以后要从后台获取的！！！！
		 */
		// * 遍历list中取得的数据

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

			// 第十一列专业
			Cell cell10 = row_line.createCell(10);
			cell10.setCellValue(line_data.getMajor());
			cell10.setCellStyle(cs2);

			// 第十二列职称
			Cell cell11 = row_line.createCell(11);
			cell11.setCellValue(line_data.getTitles());
			cell11.setCellStyle(cs2);

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
	 * 四:基地信息维护的Excel文件导出 (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#systemUsersPreserveReport()
	 */
	public void exportBaseInfo(List<ExportBase> list, String fileName) {
		// 列头信息
		/*
		 * "序号", "基地编号", "基地名称", "基地类型", "院系编号", "院系名称", "土地面积", "建筑面积",
		 * "面向专业","地址", "可承担人数"
		 */
		String[] col_title = { "序号", "基地编号", "基地名称", "基地类型", "院系编号", "院系名称",
				"土地面积", "建筑面积", "面向专业", "地址", "可承担人数" };// 16
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
		sheet.setColumnWidth(10, 4000);

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
		/*
		 * // 三排之后
		 * 
		 * !!!!!!李彩注意这里是需要土地租赁的数据list的类名和方法名，然后后面还有每条数据中获取单个数值的方法!!!!!!!!!!!!!!!!
		 * !!!!!!
		 * 
		 * // 用户ID，以后要从后台获取的！！！！
		 */
		// * 遍历list中取得的数据

		for (int i = 0; i < list.size(); i++) {
			ExportBase line_data = list.get(i);// 取得list集合中一条数据
			int line = i + 2;// 多少行
			Row row_line = sheet.createRow(line);// 创建第line行
			// 第一列序号自动增加1
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(cs2);
			// String[] col_title = { "序号","员工编号","身份属性","姓名","性别","员工类型",
			// "出生日期","身份证号码","联系电话","部门","原工作单位",
			// "来校工作时间","参加工作时间","用工形式","户口性质","密码"};//16
			// 第二列基地编号
			Cell cell1 = row_line.createCell(1);
			// !!!!!!!!!!下面这行代码的line_data.getStartTime()方法获取的值是要对应上面表格顺序的哈李彩，
			// !!!!!!!!!!!然后后面每列都是同此更改!!!!!!!!!!!!!!!
			cell1.setCellValue(line_data.getId());
			cell1.setCellStyle(cs2);
			// 第三列基地名称
			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue(line_data.getName());
			cell2.setCellStyle(cs2);
			// 第四列基地类型
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue(line_data.getType());
			cell3.setCellStyle(cs2);
			// 第五列院系编号
			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue(line_data.getDeptId());
			cell4.setCellStyle(cs2);
			// 第六列院系名称
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue(line_data.getApplydp());
			cell5.setCellStyle(cs2);
			// 第七列土地面积
			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue(line_data.getLandarea());
			cell6.setCellStyle(cs2);
			// 第八列建筑面积
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue(line_data.getConstructionarea());
			cell7.setCellStyle(cs2);
			// 第九列面向专业
			Cell cell8 = row_line.createCell(8);
			cell8.setCellValue(line_data.getFacemajor());
			cell8.setCellStyle(cs2);
			// 第十列地址
			Cell cell9 = row_line.createCell(9);
			cell9.setCellValue(line_data.getLand_address());
			cell9.setCellStyle(cs2);
			// 第十一列可承担人数
			Cell cell10 = row_line.createCell(10);
			cell10.setCellValue(line_data.getUndertake());
			cell10.setCellStyle(cs2);
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
	 * 四:基地报修维护的Excel文件导出 (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#systemUsersPreserveReport()
	 */
	public void exportBaseRepairInfo(List<MaintainApplys> list, String fileName) {
		// 列头信息
		/*
		 * "序号", "基地编号", "基地名称", "基地类型", "院系编号", "院系名称", "土地面积", "建筑面积",
		 * "面向专业","地址", "可承担人数"
		 */
		String[] col_title = { "序号", "项目名称", "基地名称", "报修人", "具体地址", "预算金额",
				"实际金额", "报修原因", "报修时间" };// 16
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// 创建一个Excel文档对象
		Sheet sheet = workbook.createSheet();// 在上面的文档中创建Excel表单
		// 设置列的宽度
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 2500);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 2500);
		sheet.setColumnWidth(6, 2500);
		sheet.setColumnWidth(7, 5500);
		sheet.setColumnWidth(8, 4000);

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
		/*
		 * // 三排之后
		 * 
		 * !!!!!!李彩注意这里是需要土地租赁的数据list的类名和方法名，然后后面还有每条数据中获取单个数值的方法!!!!!!!!!!!!!!!!
		 * !!!!!!
		 * 
		 * // 用户ID，以后要从后台获取的！！！！
		 */
		// * 遍历list中取得的数据

		for (int i = 0; i < list.size(); i++) {
			MaintainApplys line_data = list.get(i);// 取得list集合中一条数据
			int line = i + 2;// 多少行
			Row row_line = sheet.createRow(line);// 创建第line行
			// 第一列序号自动增加1
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(cs2);
			// String[] col_title = { "序号","员工编号","身份属性","姓名","性别","员工类型",
			// "出生日期","身份证号码","联系电话","部门","原工作单位",
			// "来校工作时间","参加工作时间","用工形式","户口性质","密码"};//16
			// 第二列项目名称
			Cell cell1 = row_line.createCell(1);
			// !!!!!!!!!!下面这行代码的line_data.getStartTime()方法获取的值是要对应上面表格顺序的哈李彩，
			// !!!!!!!!!!!然后后面每列都是同此更改!!!!!!!!!!!!!!!
			cell1.setCellValue(line_data.getId());
			cell1.setCellStyle(cs2);
			// 第三列基地名称
			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue(line_data.getPro_name());
			cell2.setCellStyle(cs2);
			// 第四列报修人
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue(line_data.getBasename());
			cell3.setCellStyle(cs2);
			// 第五列具体地址
			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue(line_data.getAddress());
			cell4.setCellStyle(cs2);
			// 第六列预算金额
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue(line_data.getMoney());
			cell5.setCellStyle(cs2);
			// 第七列实际金额
			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue(line_data.getActualmoney());
			cell6.setCellStyle(cs2);
			// 第八列报修原因
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue(line_data.getReason());
			cell7.setCellStyle(cs2);
			// 第九列报修时间
			Cell cell8 = row_line.createCell(8);
			cell8.setCellValue(line_data.getApply_time());
			cell8.setCellStyle(cs2);

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
	 * 四:实习计划维护的Excel文件导出 (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#systemUsersPreserveReport()
	 */
	public void exportPracticePlanInfo(List<PracticeCollection> list,
			String fileName) {
		// 列头信息
		/*
		 * "序号", "周次", "开始时间","结束时间", "实习内容", "实习基地来源", "实习地点", "实习类别", "实习形式",
		 * "实习联系人姓名/电话","目的", "经费预算","指导老师","实验员","备注"
		 */
		String[] col_title = { "序号", "课程代码", "周次", "班级", "面向专业", "开始时间",
				"结束时间", "实习内容", "实习基地来源", "实习地点", "实习类别", "实习形式", "实习联系人姓名/电话",
				"目的", "经费预算", "指导老师", "实验员", "备注", "课程名称", "单位" };// 18
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// 创建一个Excel文档对象
		Sheet sheet = workbook.createSheet();// 在上面的文档中创建Excel表单
		// 设置列的宽度
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 2500);
		sheet.setColumnWidth(8, 2500);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 2500);
		sheet.setColumnWidth(12, 3000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 5000);

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

		// 设置说明行的边框
		XSSFCellStyle cs0 = workbook.createCellStyle();
		cs0.setAlignment(XSSFCellStyle.ALIGN_LEFT);// 设置字体水平居左
		cs0.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置字体垂直居中
		// 依次设置底、左、右、上边框
		cs0.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs0.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs0.setWrapText(true);// 设置宽度自适应
		cs0.setFont(font2);
		cs0.setFillForegroundColor(IndexedColors.GOLD.getIndex()); // 设置单元格前景色
		cs0.setFillPattern(CellStyle.SOLID_FOREGROUND);// 设置单元格填充样式，SOLID_FOREGROUND纯色使用前景颜色填充

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
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19));// 合并列-标题

		// * 插入数据操作

		// 表名（第一排占三行）
		Row row1 = sheet.createRow(0);
		row1.setHeight((short) 1000);
		Cell cell_title = row1.createCell(0);
		cell_title.setAsActiveCell();
		cell_title.setCellValue("湖南农业大学系统实习表");
		cell_title.setCellStyle(cs_title);
		// 第二排填相应列头名
		Row row2 = sheet.createRow(1);
		for (int i = 0; i < col_title.length; i++) {
			Cell cell_line = row2.createCell(i);
			cell_line.setCellValue(col_title[i]);
			cell_line.setCellStyle(cs1);
		}

		int line = 1;
		for (int i = 0; i < list.size(); i++) {
			PracticeCollection pc = list.get(i);// 取得list集合中一条数据
			List<Classcourse> lc = pc.getData();
			sheet.addMergedRegion(new CellRangeAddress(line + 1, line + 1, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(line + 1, line + 1, 3, 4));
			sheet.addMergedRegion(new CellRangeAddress(line + 1, line + 1, 5, 6));
			sheet.addMergedRegion(new CellRangeAddress(line + 1, line + 1, 7, 8));			
			sheet.addMergedRegion(new CellRangeAddress(line + 1, line + 1, 9, 10));
			sheet.addMergedRegion(new CellRangeAddress(line + 1, line + 1, 11,12));
			sheet.addMergedRegion(new CellRangeAddress(line + 1, line + 1, 13,14));
			Row row_line = sheet.createRow(++line);// 创建第line行			
			// 第一列空
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue("");
			cell0.setCellStyle(cs0);

			// 课程代码
			Cell cell1 = row_line.createCell(1);
			cell1.setCellValue("课程编码:" + pc.getCourseId());
			cell1.setCellStyle(cs0);

			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue("");
			cell2.setCellStyle(cs0);
			

			// 开课学院
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue("开课学院:" + pc.getDepartment());
			cell3.setCellStyle(cs0);

			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue("");
			cell4.setCellStyle(cs0);

			// 教师职工号
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue("教师职工号:" + pc.getTid());
			cell5.setCellStyle(cs0);

			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue("教师职工号:" + pc.getTid());
			cell6.setCellStyle(cs0);

			// 教师姓名
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue("教师姓名:" + pc.getTname());
			cell7.setCellStyle(cs0);

			Cell cell8 = row_line.createCell(8);
			cell8.setCellValue("");
			cell8.setCellStyle(cs0);			

			// 第四列 学习人数
			Cell cell9 = row_line.createCell(9);
			cell9.setCellValue(pc.getCountPeople());
			cell9.setCellStyle(cs0);

			Cell cell10 = row_line.createCell(10);
			cell10.setCellValue("");
			cell10.setCellStyle(cs0);

			// 第六列 周数
			Cell cell11 = row_line.createCell(11);
			cell11.setCellValue(pc.getWeekCount());
			cell11.setCellStyle(cs0);

			Cell cell12 = row_line.createCell(12);
			cell12.setCellValue("");
			cell12.setCellStyle(cs0);

			// 第二列 学分
			Cell cell13 = row_line.createCell(13);
			cell13.setCellValue(pc.getCredit());
			cell13.setCellStyle(cs0);

			Cell cell14 = row_line.createCell(14);
			cell14.setCellValue("");
			cell14.setCellStyle(cs0);

			int j = 0;
			for (Classcourse cc : lc) {
				Row rowLine = sheet.createRow(++line);// 创建第line行

				// 第一列序号
				Cell cell_0 = rowLine.createCell(0);
				cell_0.setCellValue(++j);
				cell_0.setCellStyle(cs2);

				// 第二列 课程代码
				Cell cell_1 = rowLine.createCell(1);
				cell_1.setCellValue(pc.getCourseId());
				cell_1.setCellStyle(cs2);

				// 第二列 周次
				Cell cell_2 = rowLine.createCell(2);
				cell_2.setCellValue(cc.getWeek());
				cell_2.setCellStyle(cs2);

				// 第六列教学班
				Cell cell_3 = rowLine.createCell(3);
				cell_3.setCellValue(cc.getGrade());
				cell_3.setCellStyle(cs2);

				// 第五列 面向专业
				Cell cell_4 = rowLine.createCell(4);
				cell_4.setCellValue(cc.getMajor_oriented());
				cell_4.setCellStyle(cs2);

				// 第三列 开始时间
				Cell cell_5 = rowLine.createCell(5);
				cell_5.setCellValue(cc.getStarttime());
				cell_5.setCellStyle(cs2);

				// 第四列 结束时间
				Cell cell_6 = rowLine.createCell(6);
				cell_6.setCellValue(cc.getEndtime());
				cell_6.setCellStyle(cs2);

				// 第五列 实习内容
				Cell cell_7 = rowLine.createCell(7);
				cell_7.setCellValue(cc.getContent());
				cell_7.setCellStyle(cs2);

				// 第六列 实习基地来源
				Cell cell_8 = rowLine.createCell(8);
				cell_8.setCellValue(cc.getSource());
				cell_8.setCellStyle(cs2);

				// 第七列 实习地点
				Cell cell_9 = rowLine.createCell(9);
				cell_9.setCellValue(cc.getSite());
				cell_9.setCellStyle(cs2);

				// 第八列 实习类别
				Cell cell_10 = rowLine.createCell(10);
				cell_10.setCellValue(cc.getCategory());
				cell_10.setCellStyle(cs2);

				// 第九列 实习形式
				Cell cell_11 = rowLine.createCell(11);
				cell_11.setCellValue(cc.getForm());
				cell_11.setCellStyle(cs2);

				// 第十列 实习基地联系人/电话
				Cell cell_12 = rowLine.createCell(12);
				cell_12.setCellValue(cc.getTelephone());
				cell_12.setCellStyle(cs2);

				// 第十一列 目的
				Cell cell_13 = rowLine.createCell(13);
				cell_13.setCellValue(cc.getAim());
				cell_13.setCellStyle(cs2);

				// 第十二列 实习经费预算
				Cell cell_14 = rowLine.createCell(14);
				cell_14.setCellValue(cc.getExpense());
				cell_14.setCellStyle(cs2);

				// 第十三列 指导老师
				Cell cell_15 = rowLine.createCell(15);
				cell_15.setCellValue(cc.getGuideTeacher());
				cell_15.setCellStyle(cs2);

				// 第十四列 实验员
				Cell cell_16 = rowLine.createCell(16);
				cell_16.setCellValue(cc.getAssistant());
				cell_16.setCellStyle(cs2);

				// 第十五列 备注
				Cell cell_17 = rowLine.createCell(17);
				cell_17.setCellValue(cc.getRemark());
				cell_17.setCellStyle(cs2);

				// 第四列 课程名称
				Cell cell_18 = rowLine.createCell(18);
				cell_18.setCellValue(pc.getCourseName());
				cell_18.setCellStyle(cs2);

				// 第三列 单位
				Cell cell_19 = rowLine.createCell(19);
				cell_19.setCellValue(pc.getDepartment());
				cell_19.setCellStyle(cs2);

			}
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
	 * 获取项目的根目录 因为tomcat和weblogic获取的根目录不一致，所以需要此方法
	 */
	public static String getWebRootUrl(HttpServletRequest request,
			String filename) {
		String fileDirPath = request.getSession().getServletContext()
				.getRealPath(filename);
		if (fileDirPath == null) {
			// 如果返回为空，则表示服务器为weblogic，则需要使用另外的方法
			try {
				return request.getSession().getServletContext()
						.getResource(filename).getFile();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		return fileDirPath;

	}

	// 方法功能描述: 判断是否是IE浏览器
	public static boolean isMSBrowser(HttpServletRequest request) {
		String[] IEBrowserSignals = { "MSIE", "Trident", "Edge" };
		String userAgent = request.getHeader("User-Agent");
		for (String signal : IEBrowserSignals) {
			if (userAgent.contains(signal)) {
				return true;
			}
		}
		return false;
	}

}
