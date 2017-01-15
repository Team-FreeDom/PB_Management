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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
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
 * 鍩哄湴缁存姢
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
	// 鑾峰彇褰撳墠椤甸潰鐨勪紶杈撳嚑鏉¤褰�      
	Integer size = Integer.parseInt(request.getParameter("length"));
	
	// 鏁版嵁璧峰浣嶇疆
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));	
	int order = Integer.valueOf(request.getParameter("order[0][column]"));//鎺掑簭鐨勫垪鍙� 
    String orderDir = request.getParameter("order[0][dir]");//鎺掑簭鐨勯『搴廰sc or desc 
    String searchValue = request.getParameter("search[value]");
	if (searchValue.equals("")) {
		searchValue = null;
	}
	// 閫氳繃璁＄畻姹傚嚭褰撳墠椤甸潰涓虹鍑犻〉
	Integer pageindex = (startIndex / size + 1);
	
	MaintenanceList str=null;
	/*姝ゅ鍐檚tr鎺ュ彈鐨勬柟娉曞锛歴tr = userinfoservice.manger(pageindex, size);*/
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
    
    //鍒犻櫎鍩哄湴淇℃伅
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
    
    //鑾峰緱鍩哄湴绫诲瀷鍜岀敵鎶ラ儴闂�
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
    
    @RequestMapping("/getExistDept.do")
    public String getExistDept(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map){
    	List<ApplyDept> list=maintenanceservice.getExistDept();
    	
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
    	System.out.println("筛选");
    	int basetype=Integer.valueOf(request.getParameter("basetype"));
    	int dept=Integer.valueOf(request.getParameter("dept"));
    	int star=Integer.valueOf(request.getParameter("star"));
    	
    	// 鑾峰彇褰撳墠椤甸潰鐨勪紶杈撳嚑鏉¤褰�
    	int size = Integer.parseInt(request.getParameter("length"));    	
    	// 鏁版嵁璧峰浣嶇疆
    	int startIndex = Integer.parseInt(request.getParameter("start"));
    	int draw = Integer.parseInt(request.getParameter("draw"));
    	int order = Integer.valueOf(request.getParameter("order[0][column]"));//鎺掑簭鐨勫垪鍙� 
        String orderDir = request.getParameter("order[0][dir]");//鎺掑簭鐨勯『搴廰sc or desc 
        String searchValue = request.getParameter("search[value]");
    	if (searchValue.equals("")) {
    		searchValue = null;
    	}
    	// 閫氳繃璁＄畻姹傚嚭褰撳墠椤甸潰涓虹鍑犻〉
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
    
    //淇敼鍩哄湴淇℃伅
    @RequestMapping("/updateBaseInfo.do")
    public String updateBaseInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map){
    	String baseid=request.getParameter("baseid");    	
    	int star=Integer.valueOf(request.getParameter("star"));
    	String date=request.getParameter("adddate");    
    	maintenanceservice.updateBaseInfo(baseid,star,date);
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
			String filename = "瀹炰範鍩哄湴淇℃伅琛�xlsx";			

			// 鏄剧ず涓枃鏂囦欢鍚�
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
			// 璇诲彇鏂囦欢
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
    	
    	// 涓婁紶鏂囦欢锛堝浘鐗囷級锛屽皢鏂囦欢瀛樺叆鏈嶅姟鍣ㄦ寚瀹氳矾寰勪笅锛屽苟鑾峰緱鏂囦欢鐨勭浉瀵硅矾寰�
    			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    			// 寰楀埌涓婁紶鐨勬枃浠�
    			MultipartFile mFile = multipartRequest.getFile("fileResource");
    			// 寰楀埌涓婁紶鏈嶅姟鍣ㄧ殑璺緞
    			String path = request.getSession().getServletContext()
    					.getRealPath("/upload/");
    			// 寰楀埌涓婁紶鐨勬枃浠剁殑鏂囦欢鍚�
    			String fileName = mFile.getOriginalFilename();
    			String filename = "";
    			if (!fileName.isEmpty()) {
    				filename = new Date().getTime() + "$" + fileName;
    				InputStream inputStream = mFile.getInputStream();
    				byte[] b = new byte[1048576];
    				int length = inputStream.read(b);
    				path += "\\" + filename;
    				// 鏂囦欢娴佸啓鍒版湇鍔″櫒绔�
    				File tempFile = new File(path);
    				FileOutputStream outputStream = new FileOutputStream(tempFile);
    				outputStream.write(b, 0, length);
    				
    				filename = path;   //杩欐槸鏂囦欢鍦ㄦ湇鍔″櫒鐨勭粷瀵硅矾寰�
    				//閬嶅巻鏂囦欢涓殑鏁版嵁锛氫笅闈㈢殑list涓鸿鍑虹殑鏁版嵁
    				Workbook wb = (Workbook) InputExcelServiceImpl.getWb(path);
    				List<List<String>> list = InputExcelServiceImpl.getExcelBaseRows(
    						InputExcelServiceImpl.getSheet(wb, 0), -1, -1);
    				//System.out.println("鑾峰緱鏁版嵁鍟︼紒锛侊紒锛侊紒锛侊紒锛侊紒");
    				// 锛侊紒锛侊紒锛侊紒娉ㄦ剰姝ゅ鏄亶鍘唋ist锛屽彲鍦ㄤ笅闈㈠啓鎻掑叆鏁版嵁搴撶殑璇彞
    				
    				if(CollectionUtils.isNotEmpty(list)){
    				//瀹炵幇鎵归噺鎻掑叆
    				String prefix  = "INSERT IGNORE INTO baseweb.prabaseinfo(id,name,type,applydp,land_address,"
    						+ "undertake) values";
    				String prefix2="INSERT IGNORE INTO baseweb.basemajor(pid,maid) values";
    						
    				
    				 StringBuffer suffix = new StringBuffer(); 
    				 StringBuffer suffix2 = new StringBuffer(); 
    				// 閬嶅巻琛岋紙涓嬮潰褰搃涓�鏃跺彲鐪嬪埌鍒楀ご鍚嶏級
    				for (int i = 1; i < list.size(); i++) {
    					String resultStr = "";
    					String resultStr2 = "";
    					// 寰幆姣忎竴涓猻heet涓殑姣忎竴琛�
    					List<String> row = list.get(i);    					
    					
    					// 閬嶅巻鍒�
    					if (row != null && row.size() > 0) {
    						String bid=String.valueOf(new Date().getTime());
    						resultStr="'"+bid+"',";
    						resultStr2="'"+bid+"',";
    						for (int j = 0; j < row.size(); j++) { 
    							if(j==3){
        							resultStr2 = resultStr2 + "'" + row.get(j) +"'" + ',';	//璁惧畾涓撲笟涓虹鍥涗釜鏁版嵁
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
    				// 鏋勫缓瀹屾暣sql  
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
    				tempFile.delete(); //鍒犻櫎涓存椂鏂囦欢
    				
    			}
    			return "redirect:baseMaintain.jsp";   	
    }
    
    //获得用户输入的数据
    @RequestMapping("/increaseBaseInfo.do")
    public String increaseBaseInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
    	Cookie[] cookies = request.getCookies();// 获得所有cookie对象
    	for (Cookie co : cookies) {
    	    if (co.getName().equals("username")) {
    		String userid = co.getValue();    		
    		
    		/*------参数2-----------*/
    		String str2 = "";
    		String name = request.getParameter("name");// 基地名称    		
    		String type = request.getParameter("typeid");//基地类型id    		
    		String landarea = request.getParameter("landarea");//基地面积    		
    		if (landarea.equals("")) {
    		    landarea = null;
    		}
    		String constructionarea = request.getParameter("constructionarea");//建筑面积    		
    		if (constructionarea.equals("")) {
    		    constructionarea = null;
    		}
    		String undertake = request.getParameter("undertake");//可承担人数    		
    		if (undertake.equals("")) {
    		    undertake = null;
    		}
    		String applyid = request.getParameter("applyid");//申报部门id    		
    		String land_address = request.getParameter("land_addres");// 通讯地址    		
    		String username = request.getParameter("username");// 联系人姓名    		
    		String phone = request.getParameter("phone");// 联系人电话  	
    		String starttime = request.getParameter("start_time");// 创建时间
    		String endtime = request.getParameter("end_time");//截止时间
    		String lawPerson = request.getParameter("personDuty");
    		// 申请材料保存地址
    		// 上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径
    		String path = null;
    		String filename = null;
    		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    		// 得到上传的文件
    		MultipartFile mFile = multipartRequest.getFile("material_path");// 申请材料保存地址    		
    		if (!mFile.isEmpty()) {
    		    // 得到上传服务器的路径
    		    path = request.getSession().getServletContext()
    			    .getRealPath("/material/");
    		    // 得到上传的文件的文件名
    		    String fileName = mFile.getOriginalFilename();
    		    System.out.println(fileName);
    		    String fileType = fileName.substring(fileName
    			    .lastIndexOf("."));
    		    filename = new Date().getTime() + fileType;
    		    InputStream inputStream = null;
    		    try {
    			inputStream = mFile.getInputStream();
    		    } catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		    }
    		    byte[] b = new byte[1048576];
    		    int length = 0;
    		    try {
    			length = inputStream.read(b);
    		    } catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		    }
    		    path += "\\" + filename;
    		    // 文件流写到服务器端
    		    try {
    			FileOutputStream outputStream = new FileOutputStream(
    				path);
    			outputStream.write(b, 0, length);
    			inputStream.close();
    			outputStream.close();
    		    } catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		    }
    		    filename = "../material/" + filename;
    		} else {
    		    filename = null;
    		}
    		Date d = new Date();
    		String Baseid = String.valueOf(d.getTime());
    		str2 += "('" + Baseid + "','" + name + "'," + type + ","
    			+ landarea + "," + constructionarea + "," + undertake
    			+ "," + applyid + ",'" + land_address + "','"
    			+ username + "','" + phone + "','" + filename + "','"
    			+ userid +"','"+starttime + "',"+endtime+",'"+lawPerson+"')";
    		

    		/*------参数1-----------*/
    		String majorid[] = request.getParameterValues("majorid");// 专业id
    		String str1 = "";
    		StringBuffer sb = new StringBuffer();
    		if (majorid == null) {
    		    sb.append("(");
    		    sb.append(Baseid);
    		    sb.append(",");
    		    sb.append(majorid);
    		    sb.append("),");
    		    sb.deleteCharAt(sb.length() - 1);
    		    str1 = sb.toString();
    		} else {
    		    for (String string : majorid) {
    			System.out.println(string);
    			sb.append("(");
    			sb.append(Baseid);
    			sb.append(",");
    			sb.append(string);
    			sb.append("),");
    		    }
    		    sb.deleteCharAt(sb.length() - 1);
    		    str1 = sb.toString();
    		}    		
    		maintenanceservice.increaseBaseInfo(str1, str2);
    	    }
    	}
    	return "redirect:baseMaintain.jsp";
    }
}
