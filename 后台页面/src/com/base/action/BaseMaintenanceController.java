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
import java.text.SimpleDateFormat;
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
import com.base.utils.ApplyUtils;
import com.base.utils.CookieUtils;
import com.base.utils.ExcelReport;

/**
 * 鍩哄湴缁存姢
 *
 *
 */
// 实习基地管理控制层
@Controller("baseMaintenance")
@RequestMapping("/jsp")
public class BaseMaintenanceController {

    @Autowired
    private MaintenanceService maintenanceservice;
    @Autowired
    private baseApplyService baseapplyservice;
    @Autowired
    private AdminManageServiceImpl adminManageServiceImpl;

    // 获取实习基地数据信息
    @RequestMapping("/sendBaseinfo.do")
    public String sendBaseinfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {

	Integer size = Integer.parseInt(request.getParameter("length"));

	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));
	String orderDir = request.getParameter("order[0][dir]");
	String searchValue = request.getParameter("search[value]");
	if (searchValue.equals("")) {
	    searchValue = null;
	}

	Integer pageindex = (startIndex / size + 1);

	// 获取该用户的学院，学院为空，则获取所有学院的记录，否则，获取该用户所在学院的记录
	String college = (String) request.getSession().getAttribute("college");

	MaintenanceList str = null;

	str = maintenanceservice.maintenance(pageindex, size, order, orderDir,
		searchValue, college);
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

    // 删除实习基地(批量)
    @RequestMapping("/delBaseinfo.do")
    public String delBaseinfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String str = request.getParameter("recordstr");

	String message=maintenanceservice.delInfo(str);
	if(message.equals("success")){
	    message="操作成功";
	}else if(message.equals("fail")){
	    message="操作失败";
	}
	JSONObject getObj = new JSONObject();
	getObj.put("flag", message);

	response.setContentType("text/html;charset=UTF-8");

	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return null;
    }

    // 获取部门和基地类型等基本信息
    @RequestMapping("/getManyinfo.do")
    public String getManyinfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	List<ApplyDept> list1 = maintenanceservice.getExistDept();
	List<basetype> list2 = baseapplyservice.getBasetype();

	List list = new ArrayList();
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

    // 获取实习记录中所存在的部门
    @RequestMapping("/getExistDept.do")
    public String getExistDept(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	List<ApplyDept> list = maintenanceservice.getExistDept();

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

    // 根据刷选条件获取实习基地数据信息
    @RequestMapping("/getshaiBaseinfo.do")
    public String getshaiBaseinfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	int basetype = Integer.valueOf(request.getParameter("basetype"));

	String dept = request.getParameter("dept");
	if (dept.equals("-1")) {
	    dept = null;
	}
	int star = Integer.valueOf(request.getParameter("star"));
	int size = Integer.parseInt(request.getParameter("length"));

	int startIndex = Integer.parseInt(request.getParameter("start"));
	int draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));
	String orderDir = request.getParameter("order[0][dir]");
	String searchValue = request.getParameter("search[value]");

	if (searchValue.equals("")) {
	    searchValue = null;
	}
	int pageindex = (startIndex / size + 1);

	MaintenanceList str = maintenanceservice.getshaiBaseInfo(basetype,
		dept, star, pageindex, size, order, orderDir, searchValue);

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

    // 修改实习基地信息
    @RequestMapping("/updateBaseInfo.do")
    public String updateBaseInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String baseid = request.getParameter("baseid");
	String basenamed=request.getParameter("basenamed");
	if(basenamed.equals("")){
	    basenamed=null;
	}
	String basetyped=request.getParameter("basetyped");
	if(basetyped.equals("")){
	    basetyped=null;
	}
	String landaread=request.getParameter("landaread");
	if(landaread.equals("")){
	    landaread=null;
	}
	String buildingaread=request.getParameter("buildingaread");
	if(buildingaread.equals("")){
	    buildingaread=null;
	}
	int undertakeCountd=Integer.valueOf(request.getParameter("undertakeCountd"));
	String usernamed=request.getParameter("usernamed");
	if(usernamed.equals("")){
	    usernamed=null;
	}
	String userphoned=request.getParameter("userphoned");
	 if(userphoned.equals("")){
	     userphoned=null;
	 }
	 String linkAddressd=request.getParameter("linkAddressd");
	 if(linkAddressd.equals("")){
	     linkAddressd=null;
	 }
	 String personDuty=request.getParameter("personDuty");
	 if(personDuty.equals("")){
	     personDuty=null;
	 }
	 String collegenamed=request.getParameter("collegenamed");
	 if(collegenamed.equals("")){
		 collegenamed=null;
	 }
	 String collegephoned=request.getParameter("collegephoned");
	 if(collegephoned.equals("")){
		 collegephoned=null;
	 }
	 String cooperativeUnit=request.getParameter("cooperativeUnit");
	 if(cooperativeUnit.equals("")){
		 cooperativeUnit=null;
	 }
	 String majorString = request.getParameter("majorString");
	 if(majorString.equals("")){
		 majorString="-1";
	 }
	 String tag=request.getParameter("tag");

	int star = Integer.valueOf(request.getParameter("star"));
	String date = request.getParameter("adddate");
	String message=maintenanceservice.updateBaseInfo(baseid,basenamed,basetyped,landaread,buildingaread,undertakeCountd,userphoned,usernamed,personDuty, linkAddressd,date,star,collegenamed,collegephoned,cooperativeUnit,majorString,tag);
	if(message=="success"){
	    message="操作成功";
	}else if(message=="fail"){
	    message="操作失败";
	}
	JSONObject getObj = new JSONObject();
	getObj.put("flag", message);

	response.setContentType("text/html;charset=UTF-8");

	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return null;
    }

    // 导出实习基地信息
    @RequestMapping("/exportThisInfo.do")
    public String exportThisInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {

	int basetype = Integer.valueOf(request.getParameter("basetype"));
	String dept = request.getParameter("applydept");
	if (dept == null) {
	    dept = (String) request.getSession().getAttribute("college");
	}
	int star = Integer.valueOf(request.getParameter("star"));
	int tage_0=200;
	List<ExportBase> list = maintenanceservice.getExportBaseInfo(basetype,
		dept, star);
	if(list.size()==0){
		tage_0=0;//没有数据
	}
	if (CollectionUtils.isNotEmpty(list)) {

	    String path = ExcelReport.getWebRootUrl(request, "/upload/");
	    String fullFileName = path + "/BaseInfo.xlsx";
	    ExcelReport export = new ExcelReport();
	    export.exportBaseInfo(list, fullFileName);
	    String filename = "基地信息表.xlsx";

	    response.setContentType("application/octet-stream;charset=UTF-8");
	    try {
		response.setContentType("application/octet-stream");
		boolean isMSIE = ExcelReport.isMSBrowser(request);
		if (isMSIE) {
		    filename = URLEncoder.encode(filename, "UTF-8");
		} else {
		    filename = new String(filename.getBytes("UTF-8"),
			    "ISO-8859-1");
		}
		response.setHeader("Content-disposition",
			"attachment;filename=\"" + filename + "\"");

	    } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    //
	    InputStream in = null;
	    OutputStream out = null;
	    try {
		in = new FileInputStream(fullFileName);
		out = response.getOutputStream();
		int b = 0;
		while ((b = in.read()) != -1) {
		    out.write(b);
		}
		in.close();
		out.close();

	    }catch (Exception e) {
		// TODO Auto-generated catch block
	    	tage_0=500;
		e.printStackTrace();
	    }
	    return null;
	}
	map.addAttribute("tage_0", tage_0);
	return "baseMaintain";
    }

    // 批量导入实习基地信息
    @RequestMapping("/importBaseExcel.do")
    public String importBaseInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {

	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

	MultipartFile mFile = multipartRequest.getFile("fileResource");

	String path = ExcelReport.getWebRootUrl(request, "/upload/");
	String fileName = mFile.getOriginalFilename();
	String filename = "";
	boolean flag = true;
	boolean flag_0 = true;
	int tag = 0;
	try {
	    if (!fileName.isEmpty()) {
		filename = new Date().getTime() + "$" + fileName;
		InputStream inputStream = mFile.getInputStream();
		byte[] b = new byte[1048576];
		int length = inputStream.read(b);
		path += "\\" + filename;

		File tempFile = new File(path);
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		outputStream.write(b, 0, length);

		filename = path;

		Workbook wb = (Workbook) InputExcelServiceImpl.getWb(path);
		List<List<String>> list = InputExcelServiceImpl
			.getExcelBaseRows(
				InputExcelServiceImpl.getSheet(wb, 0), -1, -1);

		if (CollectionUtils.isNotEmpty(list)) {
		    String userid = CookieUtils.getUserid(request);
		    StringBuffer suffix = new StringBuffer();
		    String resultStr2 = "";
		    String resultStr3 = "(";
		    String resultStr4 = "";
		    int showCount = 0;
		    SimpleDateFormat format0 = new SimpleDateFormat(
			    "yyyy-MM-dd");
		    Calendar c = Calendar.getInstance();
		    String starttime = format0.format(c.getTime());
		    c.add(Calendar.YEAR, 1);
		    String endtime = format0.format(c.getTime());
		    String[] str = null;
		    for (int i = 1; i < list.size(); i++) {
			String resultStr1 = "";
			List<String> row = list.get(i);
			if (row != null && row.size() > 0) {


				//将导入的数据中,如果基地编号是1,则转为首字母+时间   jimao
			   String applyTime = null;
			   // for (int k = 0; k < row.size(); k++) {
			    	if(row.get(1).equals("")){
			    		continue;
			    	}else{
			    		 if(Integer.parseInt(row.get(1))== 1){

				    		 	String applyName =	maintenanceservice.getDeptsId(Integer.parseInt(row.get(2)));
								String firstspell = ApplyUtils.getFirstSpell(applyName);
								SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
								applyTime = firstspell+df.format(new Date())+'A'+i;

						  }else{
							  applyTime = String.valueOf(new Date().getTime() + i);
						  }
			    	}
			  //  }
			     String bid = applyTime;
			     //String bid = String.valueOf(new Date().getTime() + i);

			    resultStr1 = "('" + bid + "',";
			    for (int j = 0; j < row.size(); j++) {
				// 判断必填字段是否是空数据
				if (j >= 0 && j <= 2) {
				    if (row.get(j).equals("")) {
					flag_0 = false;
					break;
				    }
				}
				if (j == 0) {
				    resultStr3 = resultStr3 + "'" + row.get(j)
					    + "',";
				    resultStr4 = resultStr4 + row.get(j) + ",";
				    showCount++;
				} else if (j == 3) {
				    str = row.get(j).split(",|，");
				    for (int k = 0; k < str.length; k++) {
					resultStr2 = resultStr2 + "('" + bid
						+ "','" + str[k] + "'),"; //
				    }
				    continue;
				} else if (j == 4) {
				    if (row.get(j).equals("")) {
					resultStr1 = resultStr1 + "'" + 0 + "'"
						+ ',';
					continue;
				    }
				}
				resultStr1 = resultStr1 + "'" + row.get(j)
					+ "'" + ',';
			    }
			}
			if (flag_0) {
			    resultStr1 = resultStr1 + "'" + starttime + "','"
				    + endtime + "')";
			    suffix.append(resultStr1 + ",");
			} else {
			    flag_0 = true;
			}
		    }

		    resultStr2 = resultStr2.substring(0,
			    resultStr2.length() - 1);
		    resultStr3 = resultStr3.substring(0,
			    resultStr3.length() - 1) + ")";
		    resultStr4 = resultStr4.substring(0,
			    resultStr4.length() - 1);
		    tag = maintenanceservice.judge_insert_base(userid,
			    resultStr3,
			    suffix.substring(0, suffix.length() - 1),
			    resultStr2, showCount - 1, resultStr4);
		}

		wb.close();
		outputStream.close();
		inputStream.close();
		tempFile.delete(); //
	    }
	} catch (Exception e) {
	    flag = false;
	    e.printStackTrace();
	}

	map.addAttribute("flag", flag);
	map.addAttribute("tag", tag);
	return "baseMaintain";
    }

    // 增加实习基地记录
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
		String type = request.getParameter("typeid");// 基地类型id
		String landarea = request.getParameter("landarea");// 基地面积
		String constructionarea = request
			.getParameter("constructionarea");// 建筑面积
		String undertake = request.getParameter("undertake");// 可承担人数
		if (undertake.equals("")) {
		    undertake = null;
		}
		String applyid = request.getParameter("applyid");// 申报部门id
		String land_address = request.getParameter("land_addres");// 通讯地址
		String username = request.getParameter("username");// 联系人姓名
		String phone = request.getParameter("phone");// 联系人电话
		String starttime = request.getParameter("start_time");// 创建时间
		String endtime = request.getParameter("end_time");// 截止时间
		String lawPerson = request.getParameter("personDuty");
		String collegeName=request.getParameter("collegeName");
		String collegeTel=request.getParameter("collegeTel");
		String landarea_select=request.getParameter("landarea_select");
		String constructionarea_select=request.getParameter("constructionarea_select");
		if(!landarea.equals("")){
			landarea=landarea+landarea_select;
		}
		if(!constructionarea.equals("")){
			constructionarea=constructionarea+constructionarea_select;
		}
		String unit=request.getParameter("unit");
		// 申请材料保存地址
		// 上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径
		String path = null;
		String filename = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 得到上传的文件
		MultipartFile mFile = multipartRequest.getFile("material_path");// 申请材料保存地址
		if (!mFile.isEmpty()) {
		    // 得到上传服务器的路径
		    path = ExcelReport.getWebRootUrl(request, "/material/");
		    // 得到上传的文件的文件名
		    String fileName = mFile.getOriginalFilename();
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
		    path += filename;
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
			//将获得的baseid自动生成首字母+时间 by jimao
		  String applyTime = null;
		  if(Integer.parseInt(type)== 1){
			  	String applyName = request.getParameter("applyName");
				String firstspell = ApplyUtils.getFirstSpell(applyName);
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
				applyTime = firstspell+df.format(new Date());

		  }else{
			  applyTime = String.valueOf(d.getTime());
		  }

		String Baseid = applyTime;   //获得学院首字母+时间

		str2 += "('" + Baseid + "','" + name + "'," + type + ",'"
			+ landarea + "','" + constructionarea + "'," + undertake
			+ "," + applyid + ",'" + land_address + "','"
			+ username + "','" + phone + "','" + filename + "','"
			+ userid + "','" + starttime + "','" + endtime + "','"
			+ lawPerson+"','"+collegeName+"','"+collegeTel+"','"+unit+"')";

		/*------参数1-----------*/
		String majorid[] = request.getParameterValues("majorid");// 专业id
		String str1 = "";
		StringBuffer sb = new StringBuffer();
		if (majorid == null) {
            sb.append("(");
			    sb.append("'");
			    sb.append(Baseid);
			    sb.append("'");
			    sb.append(",");
			    sb.append("-1");
			    sb.append("),");
		    sb.deleteCharAt(sb.length() - 1);
		    str1 = sb.toString();
		} else {
		    for (String string : majorid) {
			sb.append("(");
				sb.append("'");
				sb.append(Baseid);
				sb.append("'");
				sb.append(",");
				sb.append("'");
				sb.append(string);
				sb.append("'");
				sb.append("),");
		    }
		    sb.deleteCharAt(sb.length() - 1);
		    str1 = sb.toString();
		}
		String message=maintenanceservice.increaseBaseInfo(str1, str2);
		request.setAttribute("index", message);
		String str=(String) request.getAttribute("index");
		response.setContentType("text/html;charset=UTF-8");
	    }
	}
	return "baseMaintain";
    }
}
