package com.base.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.base.po.ApplyDept;
import com.base.po.Major;
import com.base.po.basetype;
import com.base.service.baseApplyService;
import com.base.utils.ExcelReport;

/**
 * 基地申请
 * 
 * @author 梦醒何处
 * 
 */
//实习申请控制层
@Controller("BaseApplyController")
@RequestMapping("/jsp")
public class BaseApplyController {
    @Autowired
    private baseApplyService baseapplyservice;
    
    //提交用户的实习基地申请
    @RequestMapping("/getRequestBaseInfo.do")
    public String getRequestBaseInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	System.out.println("===================");
	Cookie[] cookies = request.getCookies();// 获得所有cookie对象
	for (Cookie co : cookies) {
	    if (co.getName().equals("username")) {
		String userid = co.getValue();		
		Date d = new Date();
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
		String lawPerson = request.getParameter("lawPerson");// 联系人电话
		// String material_path =
		// request.getParameter("material_path");//
		// 申请材料保存地址
		// 上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径
		String path = null;
		String filename = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 得到上传的文件
		MultipartFile mFile = multipartRequest.getFile("material_path");// 申请材料保存地址		
		if (!mFile.isEmpty()) {
		    // 得到上传服务器的路径
		   /* path = request.getSession().getServletContext()
			    .getRealPath("/material/");*/
		   path = ExcelReport.getWebRootUrl(request,"/material/");
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
		    path +=filename;
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
		//获取当前		
		  DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		  String time=format.format(d);
		String Baseid = String.valueOf(d.getTime());		
		str2 += "('" + Baseid + "','" + name + "'," + type + ","
			+ landarea + "," + constructionarea + "," + undertake
			+ "," + applyid + ",'" + land_address + "','"
			+ username + "','" + phone + "','" + filename + "','"
			+ userid +"','"+ time + "','"+lawPerson+"')";
		System.out.println(str2+"拼装好的数据");

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
			System.out.println(string);
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
		//[{userid:"1200001",basename:"李彩彩基地"}]
		map.addAttribute("userid", userid);
		map.addAttribute("basename", name);
		//String infostr="";
		String infostr=JSONArray.fromObject(map).toString();		
		baseapplyservice.getRequestBaseInfo(str1, str2,infostr);
		request.setAttribute("index", 1);
		response.setContentType("text/html;charset=UTF-8");
		
	    }
	}
	//return "redirect:index.jsp";
	return "baseApply";

    }
    
    //获取学院和基地类型
    @RequestMapping("/BaseApplyAllInfo.do")
    public String BaseApplyInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 获取学院
	List<ApplyDept> list1 = baseapplyservice.getDept(1);
	// 获取基地类型
	List<basetype> list2 = baseapplyservice.getBasetype();

	try {
	    List list4 = new ArrayList();
	    list4.add(list1);
	    list4.add(list2);
	    JSONArray json = JSONArray.fromObject(list4);
	    response.setContentType("text/html;charset=UTF-8");
	    response.getWriter().print(json.toString());

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return null;
    }
    
    //根据部门类型获取部门
    @RequestMapping("/getBaseSingleDept.do")
    public String getBaseSingleDept(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	int typeid = Integer.parseInt(request.getParameter("typeid"));
	// 获取部门
	List<ApplyDept> list1 = baseapplyservice.getDept(typeid);

	try {
	    JSONArray json = JSONArray.fromObject(list1);
	    response.setContentType("text/html;charset=UTF-8");
	    response.getWriter().print(json.toString());

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return null;
    }
    
    //根据学院编号获取专业
    @RequestMapping("/getMajor.do")
    public String getMajor(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 根据学院id获取专业
	int aid = Integer.parseInt(request.getParameter("aid"));
	List<Major> list = baseapplyservice.getMajor(aid);
	try {
	    JSONArray json = JSONArray.fromObject(list);
	    response.setContentType("text/html;charset=UTF-8");
	    response.getWriter().print(json.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
    
    //检测用户输入的基地名称是否存在
    @RequestMapping("/CheckName.do")
    public String CheckName(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	boolean flag=false;
	//获得用户输入的基地名称
	String name=request.getParameter("name");	
	//获得是否存在
	int a=baseapplyservice.CheckName(name);	
	if(a==0){
	    flag=false;
	}else{
	    flag=true;
	}	
	response.setContentType("text/html;charset=UTF-8");
	try {
	   // JSONObject json=new JSONObject();	   
	    response.getWriter().print(flag);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

}
