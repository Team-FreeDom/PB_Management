package com.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.AllPlan;
import com.base.po.ApplyDept;
import com.base.po.BaseInfo;
import com.base.po.Classcourse;
import com.base.po.Majoraim;
import com.base.po.PlanList;
import com.base.po.UserInfo;
import com.base.service.PlanService;
import com.base.service.baseApplyService;
import com.base.utils.CookieUtils;

@Controller("planController")
@RequestMapping("/jsp")
public class PlanController {
    @Autowired
    private PlanService planservice;
    @Autowired
    private baseApplyService baseapplyservice;

    // 显示用户所属学院的所有实习计划
    @RequestMapping("/displayThisCollegePlan.do")
    public String displayThisCollegePlan(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取用户登录的id
	String userid = CookieUtils.getUserid(request);
	System.out.println("能不能进来了======");
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
	List<AllPlan> list = new ArrayList<AllPlan>();
	PlanList pl = null;
	pl = planservice.getThisCollegePlan(userid, pageindex, size, order,
		orderDir, searchValue);
	list = pl.getData();
	recordsTotal = pl.getRecordsTotal();

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

    // 获取弹出框里的数据
    @RequestMapping("/getplandata.do")
    public String getplandata(HttpServletRequest request,
	    HttpServletResponse response) {
	// 前台传过来的课程表id
	int id = Integer.valueOf(request.getParameter("mid"));
	List<Classcourse> list = null;
	list = planservice.plandata(id);
	JSONArray json = JSONArray.fromObject(list);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(json.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
  
    // 保存对实习计划的修改
    @RequestMapping("/savePlanModify.do")
    public String savePlanModify(HttpServletRequest request,
	    HttpServletResponse response) {

	// 获取课程表记录编号
	int id = Integer.valueOf(request.getParameter("courseID"));
	System.out.println(id+"=====haha");

	String plandata = request.getParameter("str");
	System.out.println(plandata+"liiiiiii");
	planservice.savePlanModify(id, plandata);
	JSONObject getObj = new JSONObject();
	getObj.put("msg", "此申请处理成功");
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 功能:1.专业所对应的培训目的(通过参数课程代码)2.获取学院3.获取基地类型
    @SuppressWarnings("rawtypes")
    @RequestMapping("/getPlanAim.do")
    public String getPlanAim(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取基地集合
	List<BaseInfo> list1 = planservice.getBaseInfo();	
	// 获取课程代码
	String cid = request.getParameter("mid");
	//System.out.println("目的:" + cid);
	// 获取获取专业所对应的培训目的
	List<Majoraim> list3 = planservice.getPlanAim(cid);
	try {
	    List list4 = new ArrayList();
	    list4.add(list1);
	    list4.add(list3);
	    JSONArray json = JSONArray.fromObject(list4);
	    response.setContentType("text/html;charset=UTF-8");
	    response.getWriter().print(json.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 获取所有学院
    @RequestMapping("/getCollege.do")
    public String getCollege(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取学院
	List<ApplyDept> list = baseapplyservice.getDept(1);
	JSONArray json = JSONArray.fromObject(list);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(json.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 获取学院所对应的教师
    @RequestMapping("/getCollege_Teacher.do")
    public String getCollege_Teacher(HttpServletRequest request,
	    HttpServletResponse response) {

	// 获取前台传过来的学院
	String college = request.getParameter("college");
	// 获得userinfo表里的教师集合
	List<UserInfo> list = planservice.getCollege_Teacher(college);
	JSONArray json = JSONArray.fromObject(list);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(json.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return null;
    }

    // 删除班级安排记录(单条)
    @RequestMapping("/deleteClassRecord.do")
    public String deleteClassRecord(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取每条班级安排记录的id
	int planid = Integer.parseInt(request.getParameter("planid"));
	planservice.deleteClassRecord(planid);
	JSONObject getObj = new JSONObject();
	getObj.put("msg", "此申请处理成功");
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

}
