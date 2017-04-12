package com.base.serviceImpl;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/*
 * 读取Excel文件中的数据信息
 */
public class InputExcelServiceImpl 
{
	//处理读文件的异常
	public static Workbook getWb(String path) 
	{
		try 
		{
			return WorkbookFactory.create(new File(path));
		} catch (Exception e)
		{
			throw new RuntimeException("读取EXCEL文件出错", e);
		}
	}

	//处理读表单的异常
	public static Sheet getSheet(Workbook wb, int sheetIndex)
	{
		if (wb == null) 
		{
			throw new RuntimeException("工作簿对象为空");
		}
		int sheetSize = wb.getNumberOfSheets();
		if (sheetIndex < 0 || sheetIndex > sheetSize - 1) 
		{
			throw new RuntimeException("工作表获取错误");
		}
		return wb.getSheetAt(sheetIndex);
	}

//读取Excel文件的数据用list存
	public static List<List<String>> getExcelRows(Sheet sheet, int startLine,
			int endLine) 
	{
		List<List<String>> list = new ArrayList<List<String>>();
		// 如果开始行号和结束行号都是-1的话，则全表读取
		if (startLine == -1)
		{
			startLine = 0;
		}
		if (endLine == -1)
		{
			endLine = sheet.getLastRowNum() + 1;
		}else{
			endLine += 1;
		}
		//System.out.println(startLine+" "+endLine);
		if(endLine==1)//空表不能导入，否则报错
			return null;
		
		
		for (int i = startLine; i < endLine; i++) 
		{
			//System.out.println("导入表格没有数据 "+endLine);
			Row row = sheet.getRow(i);
			if (row == null) 
			{
				//System.out.println("该行为空，直接跳过");
				continue;
			}
			//int rowSize = row.getLastCellNum();
			//System.out.println(rowSize);
			int rowSize = 18; //固定导入excel表格每行16列
			List<String> rowList = new ArrayList<String>();
			for (int j = 0; j < rowSize; j++)
			{
				Cell cell = row.getCell(j);
				String temp = "";
				if (cell == null) 
				{
					//System.out.println("该列为空，赋值双引号");
					temp = "";
				} 
				else 
				{
					int cellType = cell.getCellType();
					switch (cellType)
					{
					case Cell.CELL_TYPE_STRING:
						temp = cell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						temp = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						temp = String.valueOf(cell.getCellFormula().trim());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (!HSSFDateUtil.isCellDateFormatted(cell)) {
							temp = new DecimalFormat("#.######").format(cell
									.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BLANK:
						temp = "";
						break;
					case Cell.CELL_TYPE_ERROR:
						temp = "ERROR";
						break;
					default:
						temp = cell.toString().trim();
						break;
					}
				}
				rowList.add(temp);
			}
			list.add(rowList);
			
		}
		return list;
	}
	
	//读取Excel文件的数据用list存
		public static List<List<String>> getExcelRows_common(Sheet sheet, int startLine,
				int endLine,int columnCount) 
		{
			List<List<String>> list = new ArrayList<List<String>>();
			// 如果开始行号和结束行号都是-1的话，则全表读取
			if (startLine == -1)
			{
				startLine = 0;
			}
			if (endLine == -1)
			{
				endLine = sheet.getLastRowNum() + 1;
			}else{
				endLine += 1;
			}
			//System.out.println(startLine+" "+endLine);
			if(endLine==1)//空表不能导入，否则报错
				return null;
			
			
			for (int i = startLine; i < endLine; i++) 
			{
				//System.out.println("导入表格没有数据 "+endLine);
				Row row = sheet.getRow(i);
				if (row == null) 
				{
					//System.out.println("该行为空，直接跳过");
					continue;
				}
				//int rowSize = row.getLastCellNum();
				//System.out.println(rowSize);
				int rowSize = columnCount; //固定导入excel表格每行16列
				List<String> rowList = new ArrayList<String>();
				for (int j = 0; j < rowSize; j++)
				{
					Cell cell = row.getCell(j);
					String temp = "";
					if (cell == null) 
					{
						//System.out.println("该列为空，赋值双引号");
						temp = "";
					} 
					else 
					{
						int cellType = cell.getCellType();
						switch (cellType)
						{
						case Cell.CELL_TYPE_STRING:
							temp = cell.getStringCellValue().trim();
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							temp = String.valueOf(cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							temp = String.valueOf(cell.getCellFormula().trim());
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (!HSSFDateUtil.isCellDateFormatted(cell)) {
								temp = new DecimalFormat("#.######").format(cell
										.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_BLANK:
							temp = "";
							break;
						case Cell.CELL_TYPE_ERROR:
							temp = "ERROR";
							break;
						default:
							temp = cell.toString().trim();
							break;
						}
					}
					rowList.add(temp);
				}
				list.add(rowList);
				
			}
			return list;
		}
	
	//读取实习计划Excel文件的数据用list存
		public static List<List<String>> getPlanExcelRows(Sheet sheet, int startLine,
				int endLine) 
		{
			List<List<String>> list = new ArrayList<List<String>>();
			// 如果开始行号和结束行号都是-1的话，则全表读取
			if (startLine == -1)
			{
				startLine = 0;
			}
			if (endLine == -1)
			{
				endLine = sheet.getLastRowNum() + 1;
			}else{
				endLine += 1;
			}
			//System.out.println(startLine+" "+endLine);
			if(endLine==1)//空表不能导入，否则报错
				return null;
			
			
			for (int i = startLine; i < endLine; i++) 
			{
				//System.out.println("导入表格没有数据 "+endLine);
				Row row = sheet.getRow(i);
				if (row == null) 
				{
					//System.out.println("该行为空，直接跳过");
					continue;
				}
				//int rowSize = row.getLastCellNum();
				//System.out.println(rowSize);
				int rowSize = 45; //固定导入excel表格每行19列
				List<String> rowList = new ArrayList<String>();
				for (int j = 0; j < rowSize; j++)
				{
					Cell cell = row.getCell(j);
					String temp = "";
					if (cell == null) 
					{
						//System.out.println("该列为空，赋值双引号");
						temp = "";
					} 
					else 
					{
						int cellType = cell.getCellType();
						switch (cellType)
						{
						case Cell.CELL_TYPE_STRING:
							temp = cell.getStringCellValue().trim();
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							temp = String.valueOf(cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							temp = String.valueOf(cell.getCellFormula().trim());
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (!HSSFDateUtil.isCellDateFormatted(cell)) {
								temp = new DecimalFormat("#.######").format(cell
										.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_BLANK:
							temp = "";
							break;
						case Cell.CELL_TYPE_ERROR:
							temp = "ERROR";
							break;
						default:
							temp = cell.toString().trim();
							break;
						}
					}
					//System.out.println(j+":"+temp);
					rowList.add(temp);
				}
				list.add(rowList);
				
			}
			return list;
		}
	
	//读取基地管理表
	public static List<List<String>> getExcelBaseRows(Sheet sheet, int startLine,
			int endLine) 
	{
		List<List<String>> list = new ArrayList<List<String>>();
		// 如果开始行号和结束行号都是-1的话，则全表读取
		if (startLine == -1)
		{
			startLine = 0;
		}
		if (endLine == -1)
		{
			endLine = sheet.getLastRowNum() + 1;
		}else{
			endLine += 1;
		}		
		if(endLine==1)//空表不能导入，否则报错
			return null;
		
		
		for (int i = startLine; i < endLine; i++) 
		{
			//System.out.println("导入表格没有数据 "+endLine);
			Row row = sheet.getRow(i);
			if (row == null) 
			{
				//System.out.println("该行为空，直接跳过");
				continue;
			}
			//int rowSize = row.getLastCellNum();
			//System.out.println(rowSize);
			int rowSize = 7; //固定导入excel表格每行8列
			List<String> rowList = new ArrayList<String>();
			for (int j = 0; j < rowSize; j++)
			{
				Cell cell = row.getCell(j);
				String temp = "";
				if (cell == null) 
				{
					//System.out.println("该列为空，赋值双引号");
					temp = "";
				} 
				else 
				{
					int cellType = cell.getCellType();
					switch (cellType)
					{
					case Cell.CELL_TYPE_STRING:
						temp = cell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						temp = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						temp = String.valueOf(cell.getCellFormula().trim());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (!HSSFDateUtil.isCellDateFormatted(cell)) {
							temp = new DecimalFormat("#.######").format(cell
									.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BLANK:
						temp = "";
						break;
					case Cell.CELL_TYPE_ERROR:
						temp = "ERROR";
						break;
					default:
						temp = cell.toString().trim();
						break;
					}
				}
				rowList.add(temp);
			}
			list.add(rowList);
			
		}
		return list;
	}
	
	
	/** 
     *  下面方法只能读2003的Excel文件，可删除
     * @param excelFile 读取文件对象 
     * @param rowNum 从第几行开始读，如果有一行表头则从第二行开始读 
     * @return 
     * @throws BiffException 
     * @throws IOException 
     */  
//    public static List<String[]> readExcel(File excelFile,int rowNum) throws BiffException,  
//            IOException {  //rowNum从第几行开始读
//        // 创建一个list 用来存储读取的内容  
//        List<String[]> list = new ArrayList<String[]>();  
//        Workbook rwb = null;  
//        Cell cell = null;  
//        // 创建输入流  
//        InputStream stream = new FileInputStream(excelFile);  
//        // 获取Excel文件对象  
//        rwb = Workbook.getWorkbook(stream);  
//        // 获取文件的指定工作表 默认的第一个  
//        Sheet sheet = rwb.getSheet(0);  
//        // 行数(表头的目录不需要，从1开始)  
//        for (int i = rowNum; i < sheet.getRows(); i++) {  
//            // 创建一个数组 用来存储每一列的值  
//            String[] str = new String[sheet.getColumns()];  
//            // 列数  
//            for (int j = 0; j < sheet.getColumns(); j++) {  
//                // 获取第i行，第j列的值  
//                cell = sheet.getCell(j, i);  
//                str[j] = cell.getContents();  
//            }  
//            // 把刚获取的列存入list  
//            list.add(str);  
//        }  
//        // 返回值集合  
//        return list;  
//    }  

}
