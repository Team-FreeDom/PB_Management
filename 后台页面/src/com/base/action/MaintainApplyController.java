package com.base.action;

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
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.base.po.MaintainApply;
import com.base.po.MaintainApplys;
import com.base.po.MaintainList;
import com.base.po.Prabaseinfo;
import com.base.service.MaintainApplyService;
import com.base.utils.CookieUtils;
import com.base.utils.ExcelReport;

//报修申请控制层
@Controller("MaintainApplyController")
@RequestMapping("/jsp")
public class MaintainApplyController {
    @Autowired
    private MaintainApplyService applyservice;

    // 校内基地名查询
    @RequestMapping("/basename.do")
    public String find_basename(HttpServletRequest request, ModelMap map,
	    HttpServletResponse response) {
    String year = request.getParameter("year"); 
    if(year.equals("-1")){
    	year=null;
    }
	List list = applyservice.find_basename(year);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    JSONArray json = JSONArray.fromObject(list);
	    response.getWriter().print(json.toString());
	} catch (Exception e) {
	    System.out.println(e);
	}

	return null;
    }
    
   // 校内基地名查询(包括土地基地和校内基地)
    @RequestMapping("/baseNeiName.do")
    public String baseNeiName(HttpServletRequest request, ModelMap map,
	    HttpServletResponse response) {
	List list = applyservice.find_basenamenei();
	response.setContentType("text/html;charset=UTF-8");
	try {
	    JSONArray json = JSONArray.fromObject(list);
	    response.getWriter().print(json.toString());
	} catch (Exception e) {
	    System.out.println(e);
	}

	return null;
    }

    // 提交维修申请
    @RequestMapping("/insertmaintain.do")
    public String insert_maintain(HttpServletRequest request, ModelMap map,
	    HttpServletResponse response) {

	// 申请材料保存地址
	// 上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径
	String path = null;
	String filename = null;
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	// 得到上传的文件
	MultipartFile mFile = multipartRequest.getFile("applyfile");// 申请材料保存地址
	System.out.println("11" + mFile);
	if (!mFile.isEmpty()) {
	    // 得到上传服务器的路径
	    /*path = request.getSession().getServletContext()
		    .getRealPath("/maintainfile/");*/
	    path = ExcelReport.getWebRootUrl(request,"/maintainfile/");
	    // 得到上传的文件的文件名
	    String fileName = mFile.getOriginalFilename();
	    String fileType = fileName.substring(fileName.lastIndexOf("."));
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
	    path +=filename;
	    // 文件流写到服务器端
	    try {
		FileOutputStream outputStream = new FileOutputStream(path);
		outputStream.write(b, 0, length);
		inputStream.close();
		outputStream.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    filename = "../maintainfile/" + filename;
	} else {
	    filename = null;
	}
	String userid = CookieUtils.getUserid(request);
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
	String apply_time = df.format(new Date());// new Date()为获取当前系统时间
	String pro_name = request.getParameter("projectname");
	String username = request.getParameter("name");
	String address = request.getParameter("address");
	double money = Double.valueOf(request.getParameter("budget"));
	String bid = request.getParameter("baselist");
	String reason = request.getParameter("reason");
	String str = "(" + "'" + pro_name + "'," + "'" + bid + "'," + "'"
		+ username + "'," + "'" + address + "'," + "'" + reason + "',"
		+ "'" + filename + "'," + money + ",'" + apply_time + "',"
		+ "'" + userid + "')";
	// 获得当前用户id
	String applicantId = CookieUtils.getUserid(request);
	map.addAttribute("userid", userid);
	map.addAttribute("basename", "");
	String infostr = JSONArray.fromObject(map).toString();
	applyservice.insert_maintain(str, infostr);
	request.setAttribute("index", 1);
	response.setContentType("text/html;charset=UTF-8");
	return "Repairpply";
    }

    // 获取维修信息
    @RequestMapping("/query_maintainapply.do")
    public String query_maintainapply(HttpServletRequest request, ModelMap map,
	    HttpServletResponse response) {
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));

	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
								// desc
	String searchValue = request.getParameter("search[value]");
	System.out.println("search[value]:"+searchValue);
	if (searchValue.equals("")) {
	    searchValue = null;
	}
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	MaintainList list = new MaintainList();
	list = applyservice.query_maintainapply(size, pageindex, order,
		orderDir, searchValue);
	JSONObject getObj = new JSONObject();
	getObj.put("draw", draw);
	getObj.put("recordsFiltered", list.getRecordsTotal());
	getObj.put("recordsTotal", list.getRecordsTotal());
	getObj.put("data", list.getData());
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 删除维修信息记录
    @RequestMapping("/delmaintainapply.do")
    public String delmaintainapply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String str = request.getParameter("deletstr");
	applyservice.delete_maintainapply(str);
	JSONObject getObj = new JSONObject();
	getObj.put("str", "成功删除");
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 增加维修信息记录
    @RequestMapping("/addmaintainapply.do")
    public String add_maintainhistory(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 申请材料保存地址
	// 上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径
	String path = null;
	String filename = null;
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	// 得到上传的文件
	MultipartFile mFile = multipartRequest.getFile("file");// 申请材料保存地址
	System.out.println("11" + mFile);
	if (!mFile.isEmpty()) {
	    // 得到上传服务器的路径
		path = ExcelReport.getWebRootUrl(request,"/maintainfile/");
	   /* path = request.getSession().getServletContext()
		    .getRealPath("/maintainfile/");*/
	    // 得到上传的文件的文件名
	    String fileName = mFile.getOriginalFilename();
	    String fileType = fileName.substring(fileName.lastIndexOf("."));
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
	    path +=filename;
	    // 文件流写到服务器端
	    try {
		FileOutputStream outputStream = new FileOutputStream(path);
		outputStream.write(b, 0, length);
		inputStream.close();
		outputStream.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    filename = "../maintainfile/" + filename;
	} else {
	    filename = null;
	}

	String pronames = request.getParameter("Aprojectname");
	String bids = request.getParameter("Abasename");
	String usernames = request.getParameter("Aname");
	String time = request.getParameter("Atime");
	double budget = Double.valueOf(request.getParameter("Abudget"));
	double actualMoney = Double
		.valueOf(request.getParameter("ActualMoney"));
	String reasons = request.getParameter("Areason");
	String address = request.getParameter("Aaddress");
	MaintainApply ma = new MaintainApply(pronames, bids, usernames,
		address, reasons, filename, budget, time, 15, actualMoney);
	applyservice.insert_maintainhistory(ma);

	return "redirect:Repairmanage.jsp";
    }

    //获取年份
    @RequestMapping("/getThoseYear.do")
    public String getThoseYear(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
    List list=new ArrayList();
	List<String> list1 = applyservice.getThoseYear();
	List list2 = applyservice.find_basename(null);
	list.add(list1);
	list.add(list2);
	JSONObject getObj = new JSONObject();
	getObj.put("list", list);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 导出维修信息记录，参数为筛选条件，第一个基地名字，第二个为年份(如没有，则为-1)
    @RequestMapping("/exportmaintainapply.do")
    public String export_maintainapply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String bname = request.getParameter("basename");
	String date = request.getParameter("year");
	int years = -1;
	if (date != null && !date.equals("")) {
	    years = Integer.valueOf(date);
	}
	if (bname.equals("-1")) {
	    bname = null;
	}
	List<MaintainApplys> list = new ArrayList<MaintainApplys>();
	list = applyservice.export_maintainapply(bname, years);
	if (CollectionUtils.isNotEmpty(list)) {
	    /*String path = request.getSession().getServletContext()
		    .getRealPath("/upload/");*/
	    String path = ExcelReport.getWebRootUrl(request,"/upload/"); 
	    String fullFileName = path + "/BaseRepairInfo.xlsx";
	    ExcelReport export = new ExcelReport();
	    export.exportBaseRepairInfo(list, fullFileName);
	    String filename = "基地维修信息表.xlsx";

	    // 显示中文文件名
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
	    // 读取文件
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

	    } catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return null;
	}
	return "redirect:Repairmanage.jsp";
    }
}
