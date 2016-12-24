package com.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.ApplyDept;
import com.base.po.Major;
import com.base.po.basetype;
import com.base.service.baseApplyService;

/**
 * 基地申请
 * @author 梦醒何处
 *
 */
@Controller("baseApplyController")
@RequestMapping("/jsp")
public class BaseApplyController {
    @Autowired
    private baseApplyService baseapplyservice;

    @RequestMapping("/getRequestBaseInfo.do")
    public String getRequestBaseInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	/*------参数1-----------*/
	String str1 = "";
	
	// int id=Integer.parseInt(request.getParameter("id"));//id
	String id = request.getParameter("id");
	String name = request.getParameter("name");// 基地名称
	// int type=Integer.parseInt(request.getParameter("typeid"));//基地类型id
	String type = request.getParameter("type");
	// int landarea=Integer.parseInt(request.getParameter("landarea"));//基地面积
	String landarea = request.getParameter("landarea");
	if (landarea.equals("")) {
	    landarea = null;
	}
	// int
	// constructionarea=Integer.parseInt(request.getParameter("constructionarea"));//建筑面积
	String constructionarea = request.getParameter("constructionarea");
	if (constructionarea.equals("")) {
	    constructionarea = null;
	}
	// int
	// undertake=Integer.parseInt(request.getParameter("undertake"));//可承担人数
	String undertake = request.getParameter("undertake");
	if (undertake.equals("")) {
	    undertake = null;
	}
	// int
	// applyid=Integer.parseInt(request.getParameter("applydpid"));//申报部门id
	String applyid = request.getParameter("applyid");
	String land_address = request.getParameter("land_addres");//通讯地址
	String username = request.getParameter("username");// 联系人姓名
	String phone = request.getParameter("phone");// 联系人电话
	String material_path = request.getParameter("material_path");// 申请材料保存地址
	str1 += id + name + type + landarea + constructionarea + undertake
		+ applyid + land_address + username + material_path;
	System.out.println(str1);

	/*------参数2-----------*/
	//int BaseId = Integer.parseInt(request.getParameter("baseId"));//基地类型id
	String BaseId=request.getParameter("baseId");
	String majorid[] = request.getParameterValues("majorid");//专业id
	StringBuffer sb=new StringBuffer();
	for (String string : majorid) {
	    sb.append("(");
	    sb.append(BaseId);
	    sb.append(",");
	    sb.append(string);
	    sb.append("),");
	}
	sb.deleteCharAt(sb.length()-1);
	String str2=sb.toString();
	
	
	
	/*String str = "";
	if (majorid != null) {
	    for (int i = 0; i < majorid.length; i++) {
		str += majorid[i] + ",";
	    }
	}
	List list=new ArrayList();
	list.add(BaseId);
	list.add(str);	
	JSONArray json = JSONArray.fromObject(list);
        response.setContentType("text/html;charset=UTF-8");
        String str2=json.toString();*/
	baseapplyservice.getRequestBaseInfo(str1, str2);

	return null;

    }

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
    
    @RequestMapping("/getBaseSingleDept.do")
    public String getBaseSingleDept(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	 int typeid=Integer.parseInt(request.getParameter("typeid"));
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

    @RequestMapping("/getMajor.do")
    public String getMajor(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 根据学院id获取专业
	 int aid=Integer.parseInt(request.getParameter("aid"));
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

}
