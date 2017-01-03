package com.base.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.base.po.ApplyDept;
import com.base.po.ExportBase;
import com.base.po.Prabaseinfo;
import com.base.po.MaintenanceList;
import com.base.po.basetype;
import com.base.service.MaintenanceService;
import com.base.service.baseApplyService;
import com.base.serviceImpl.AdminManageServiceImpl;
import com.base.serviceImpl.InputExcelServiceImpl;
import com.base.utils.ExcelReport;

/**
 * 基地维护
 * 
 *
 */
@Controller("baseMaintenance")
@RequestMapping("/jsp")
public class BaseMaintenanceController {
    
    @Autowired
    private MaintenanceService maintenanceservice;
    @Autowired
    private baseApplyService baseapplyservice;
    @Autowired
	private AdminManageServiceImpl adminManageServiceImpl;

    
    @RequestMapping("/sendBaseinfo.do")
    public String sendBaseinfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map){	
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));	
	int order = Integer.valueOf(request.getParameter("order[0][column]"));//排序的列号  
    String orderDir = request.getParameter("order[0][dir]");//排序的顺序asc or desc 
    String searchValue = request.getParameter("search[value]");
	if (searchValue.equals("")) {
		searchValue = null;
	}
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	
	MaintenanceList str=null;
	/*此处写str接受的方法如：str = userinfoservice.manger(pageindex, size);*/
	str=maintenanceservice.maintenance(pageindex, size,order,orderDir,searchValue);
	
	JSONObject getObj = new JSONObject();
	getObj.put("draw", draw);
	getObj.put("recordsFiltered", str.getRecordsTotal());
	getObj.put("recordsTotal", str.getRecordsTotal());
	getObj.put("data", str.getData());
	response.setContentType("text/html;charset=UTF-8");

	try {
		response.getWriter().print(getObj.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;	
    }
    
    //删除基地信息
    @RequestMapping("/delBaseinfo.do")
    public String delBaseinfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map){
    	String str=request.getParameter("recordstr");
    	
    	maintenanceservice.delInfo(str);
    	JSONObject getObj = new JSONObject();
    	getObj.put("flag", true);
    	
    	response.setContentType("text/html;charset=UTF-8");

    	try {
    		response.getWriter().print(getObj.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}   	
    	
    	return null;    	
    }
    
    //获得基地类型和申报部门
    @RequestMapping("/getManyinfo.do")
    public String getManyinfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map){
    	List<ApplyDept> list1=maintenanceservice.getExistDept();
    	List<basetype> list2 = baseapplyservice.getBasetype();
    	
    	List list=new ArrayList();
    	list.add(list1);
    	list.add(list2);
    	response.setContentType("text/html;charset=UTF-8");
    	JSONArray json = JSONArray.fromObject(list);
    	try {
    	    response.getWriter().print(json.toString());

    	} catch (IOException e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	}
    	return null;    	
    }
    
    @RequestMapping("/getshaiBaseinfo.do")
    public String getshaiBaseinfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map){
    	int basetype=Integer.valueOf(request.getParameter("basetype"));
    	int dept=Integer.valueOf(request.getParameter("dept"));
    	int star=Integer.valueOf(request.getParameter("star"));
    	
    	// 获取当前页面的传输几条记录
    	int size = Integer.parseInt(request.getParameter("length"));    	
    	// 数据起始位置
    	int startIndex = Integer.parseInt(request.getParameter("start"));
    	int draw = Integer.parseInt(request.getParameter("draw"));
    	int order = Integer.valueOf(request.getParameter("order[0][column]"));//排序的列号  
        String orderDir = request.getParameter("order[0][dir]");//排序的顺序asc or desc 
        String searchValue = request.getParameter("search[value]");
    	if (searchValue.equals("")) {
    		searchValue = null;
    	}
    	// 通过计算求出当前页面为第几页
    	int pageindex = (startIndex / size + 1);
    	
    	MaintenanceList str=maintenanceservice.getshaiBaseInfo(basetype,dept,star,pageindex, size,order,orderDir,searchValue);
    	
    	JSONObject getObj = new JSONObject();
    	getObj.put("draw", draw);
    	getObj.put("recordsFiltered", str.getRecordsTotal());
    	getObj.put("recordsTotal", str.getRecordsTotal());
    	getObj.put("data", str.getData());
    	response.setContentType("text/html;charset=UTF-8");

    	try {
    		response.getWriter().print(getObj.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return null;	
    }
    
    //修改基地信息
    @RequestMapping("/updateBaseInfo.do")
    public String updateBaseInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map){
    	String baseid=request.getParameter("baseid");    	
    	int star=Integer.valueOf(request.getParameter("star"));
    	String date=request.getParameter("adddate");
    	int adddate=0;
    	if(!date.equals("")){
    		 adddate=Integer.valueOf(date);
    	}    	
    	maintenanceservice.updateBaseInfo(baseid,star,adddate);
    	JSONObject getObj = new JSONObject();
    	getObj.put("flag", true);
    	
    	response.setContentType("text/html;charset=UTF-8");

    	try {
    		response.getWriter().print(getObj.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}   	
    	
    	return null;    	
    }
    
    @RequestMapping("/exportThisInfo.do")   
    public String exportThisInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map){
    	
    	int basetype=Integer.valueOf(request.getParameter("basetype"));
    	int dept=Integer.valueOf(request.getParameter("applydept"));
    	int star=Integer.valueOf(request.getParameter("star"));
    	System.out.println(basetype+"  "+dept+"  "+star);
    	List<ExportBase> list=maintenanceservice.getExportBaseInfo(basetype,dept,star);
    	
    	if (CollectionUtils.isNotEmpty(list)) {         
			String path = request.getSession().getServletContext()
					.getRealPath("/upload/");
			/*String path = ExcelReport.getWebRootUrl(request,"/upload/");*/
			String fullFileName = path + "/BaseInfo.xlsx";
			ExcelReport export = new ExcelReport();
			export.exportBaseInfo(list, fullFileName);
			String filename = "实习基地信息表.xlsx";			

			// 显示中文文件名
			response.setContentType("application/octet-stream;charset=UTF-8");
			try {
				response.setContentType("application/octet-stream");  
				 boolean isMSIE = ExcelReport.isMSBrowser(request);  
				  if (isMSIE) {  
					  filename = URLEncoder.encode(filename, "UTF-8");  
				 } else {  
				       filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");  
				 }  
				 response.setHeader("Content-disposition", "attachment;filename=\"" + filename + "\"");  
				

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 读取文件
			InputStream in=null;
			OutputStream out = null;
			try {
				in = new FileInputStream(fullFileName);
				out = response.getOutputStream();
				int b=0;
				while ((b = in.read()) != -1) {
					out.write(b);
				}
				in.close();
				out.close();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return null;		
    }
    	return "baseMaintain";  	
}
    
    @RequestMapping("/importBaseExcel.do")   
    public String importBaseInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) throws IOException{
    	
    	// 上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径
    			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    			// 得到上传的文件
    			MultipartFile mFile = multipartRequest.getFile("fileResource");
    			// 得到上传服务器的路径
    			String path = request.getSession().getServletContext()
    					.getRealPath("/upload/");
    			// 得到上传的文件的文件名
    			String fileName = mFile.getOriginalFilename();
    			String filename = "";
    			if (!fileName.isEmpty()) {
    				filename = new Date().getTime() + "$" + fileName;
    				InputStream inputStream = mFile.getInputStream();
    				byte[] b = new byte[1048576];
    				int length = inputStream.read(b);
    				path += "\\" + filename;
    				// 文件流写到服务器端
    				File tempFile = new File(path);
    				FileOutputStream outputStream = new FileOutputStream(tempFile);
    				outputStream.write(b, 0, length);
    				
    				filename = path;   //这是文件在服务器的绝对路径
    				//遍历文件中的数据：下面的list为读出的数据
    				Workbook wb = (Workbook) InputExcelServiceImpl.getWb(path);
    				List<List<String>> list = InputExcelServiceImpl.getExcelBaseRows(
    						InputExcelServiceImpl.getSheet(wb, 0), -1, -1);
    				//System.out.println("获得数据啦！！！！！！！！！");
    				// ！！！！！！注意此处是遍历list，可在下面写插入数据库的语句
    				
    				if(CollectionUtils.isNotEmpty(list)){
    				//实现批量插入
    				String prefix  = "INSERT IGNORE INTO baseweb.prabaseinfo(id,name,type,applydp,land_address,"
    						+ "undertake) values";
    				String prefix2="INSERT IGNORE INTO baseweb.basemajor(pid,maid) values";
    						
    				
    				 StringBuffer suffix = new StringBuffer(); 
    				 StringBuffer suffix2 = new StringBuffer(); 
    				// 遍历行（下面当i为0时可看到列头名）
    				for (int i = 1; i < list.size(); i++) {
    					String resultStr = "";
    					String resultStr2 = "";
    					// 循环每一个sheet中的每一行
    					List<String> row = list.get(i);    					
    					
    					// 遍历列
    					if (row != null && row.size() > 0) {
    						String bid=String.valueOf(new Date().getTime());
    						resultStr="'"+bid+"',";
    						resultStr2="'"+bid+"',";
    						for (int j = 0; j < row.size(); j++) { 
    							if(j==3){
        							resultStr2 = resultStr2 + "'" + row.get(j) +"'" + ',';	//设定专业为第四个数据
        							continue;
    							}        						
    							resultStr = resultStr + "'" + row.get(j) +"'" + ',';
    							
    						}    						
    						resultStr = resultStr.substring(0, resultStr.length() - 1);
    						resultStr2 = resultStr2.substring(0, resultStr2.length() - 1);
    						suffix.append( "(" + resultStr + "),");
    						suffix2.append( "(" + resultStr2 + "),");
    								
    					}
    				}
    				// 构建完整sql  
    	            String sql = prefix + suffix.substring(0, suffix.length() - 1) + " on duplicate key update id=values(id),name=values(name),type=values(type),applydp=values(applydp)" +
    	            		",land_address=values(land_address),undertake=values(undertake)";  
    	            String sql2 = prefix2 + suffix2.substring(0, suffix2.length() - 1) + " on duplicate key update pid=values(pid),maid=values(maid)";     	
    				System.out.println(sql);
    				System.out.println(sql2);
    	            adminManageServiceImpl.setAdminFunction(sql);
    				adminManageServiceImpl.setAdminFunction(sql2);
    				}
    				
    				wb.close();
    				outputStream.close();
    				inputStream.close();
    				tempFile.delete(); //删除临时文件
    				
    			}
    			return "redirect:baseMaintain.jsp";   	
    }
}
