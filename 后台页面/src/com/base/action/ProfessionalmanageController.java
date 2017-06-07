package com.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.Professionalmanage;
import com.base.po.ProfessionalmanageList;
import com.base.service.ProfessionalmanageService;

@Controller("ProfessionalmanageController")
@RequestMapping("/jsp")
public class ProfessionalmanageController {

    @Autowired
    private ProfessionalmanageService ProfessionalmanageService;

    // 提交用户的实习基地申请
    @RequestMapping("/addmajor.do")
    public String insertMajor(HttpServletRequest request,
	    HttpServletResponse response) {

	int aid = Integer.parseInt(request.getParameter("deptSelectOne1"));// 新增专业所属学院
	String mid = request.getParameter("mid");// 新增专业标号
	String mname = request.getParameter("mname");// 新增专业的名称
	int flag = ProfessionalmanageService.insertMajor(mid, mname, aid);
	request.setAttribute("flag", flag);
	return "Professionalmanage";

    };

    // 展示专业页面
    @RequestMapping("/query_majors.do")
    public String query_majors(HttpServletRequest request,
	    HttpServletResponse response) {
	String searchValue = request.getParameter("search[value]");
	if (searchValue.equals("")) {
	    searchValue = null;
	}
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	// String orderDir = request.getParameter("order[0][dir]");//
	// 排序的顺序asc or
	String orderDir = "desc"; // // desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	int recordsTotal = 0;
	List<Professionalmanage> list = new ArrayList<Professionalmanage>();
	ProfessionalmanageList pr = null;
	pr = ProfessionalmanageService.query_majors(size, pageindex, order,
		orderDir, searchValue);
	list = pr.getData();
	recordsTotal = pr.getRecordsTotal();
	JSONObject getObj = new JSONObject();
	getObj.put("draw", draw);
	getObj.put("recordsFiltered", recordsTotal);
	getObj.put("recordsTotal", recordsTotal);
	getObj.put("data", list);
	response.setContentType("text/html;charset=UTF-8");

	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 删除操作
    @RequestMapping("/delmajor.do")
    public String deleteMajor(HttpServletRequest request,
	    HttpServletResponse response) {
	String str = request.getParameter("deletstr");
	String message = ProfessionalmanageService.deleteMajor(str);
	if (message.equals("success")) {
	    message = "操作成功";
	} else if (message.equals("fail")) {
	    message = "操作失败";
	}
	JSONObject getObj = new JSONObject();
	getObj.put("str", message);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 修改操作
    @RequestMapping("/updatemajor.do")
    public String updatemajor(HttpServletRequest request,
	    HttpServletResponse response) {
	String mid = request.getParameter("Mid");
	String mname = request.getParameter("Mname");
	ProfessionalmanageService.updatemajor(mid, mname);
	return "Professionalmanage";
	
    }

    // 检查专业id是否已存在
    @RequestMapping("/CheckmName.do")
    public String CheckmName(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	boolean flag = false;
	// 获得用户输入的基地名称
	String mid = request.getParameter("mid");
	// 获得是否存在
	int a = ProfessionalmanageService.CheckmName(mid);
	if (a == 0) {
	    flag = false;
	} else {
	    flag = true;
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

    // 检查专业名字
    @RequestMapping("/CheckmName1.do")
    public String CheckmName1(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	boolean flag = false;
	// 获得用户输入的基地名称
	String mname = request.getParameter("mname1");
	// 获得是否存在
	int a = ProfessionalmanageService.CheckmName1(mname);
	if (a == 0) {
	    flag = false;
	} else {
	    flag = true;
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
