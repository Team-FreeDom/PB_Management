package com.base.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.ApplyDept;
import com.base.po.BaseInfo;
import com.base.po.CheckList;
import com.base.po.CheckView;
import com.base.po.LandApply;
import com.base.po.UserInfo;
import com.base.service.checkService;
import com.base.serviceImpl.CheckServiceImpl;
import com.base.serviceImpl.LandApplyServiceImpl;
import com.base.utils.CookieUtils;

//审核模块的controller类
@Controller("CheckController")
@RequestMapping("/jsp")
public class CheckController {
    @Autowired
    private checkService checkservice;
    @Autowired
    private LandApplyServiceImpl landApplyServiceImpl;

    @RequestMapping("/getAllInfos.do")
    public String getAllInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// List<BaseInfo> list = checkservice.getBaseInfos();// 基地
	// List<ApplyDept> la = landApplyServiceImpl.getDepts();// 部门
	// List<Map<String,String>> checkDept=checkservice.getCheckDept();
	List list1 = checkservice.getList();

	try {
	    // List<UserInfo> ui = checkservice.getappliInfos();

	    // list1.add(list);
	    // list1.add(checkDept);
	    // list1.add(ui);
	    JSONArray json = JSONArray.fromObject(list1);
	    response.setContentType("text/html;charset=UTF-8");
	    response.getWriter().print(json.toString());

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}// 申请人

	return null;
    }

    // 查询所有未审核申请记录
    @RequestMapping("/checkApplyRecord.do")
    public String checkApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	Integer flag = Integer.valueOf(request.getParameter("flag"));
	System.out.println(flag + "-------------");
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));

	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));

	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
								// desc

	/*
	 * int flag=0; int draw=1; int size=4; int startIndex=0;
	 */

	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	CheckList str = null;
	try {
	    str = checkservice.getLandApply(flag, pageindex, size, order,
		    orderDir);
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

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

    // 查询所有逾期记录
    @RequestMapping("/overdue.do")
    public String overdue(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	Integer flag = Integer.valueOf(request.getParameter("flag"));
	System.out.println(flag + "==========");
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));

	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));

	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
								// desc

	/*
	 * int flag=0; int draw=1; int size=4; int startIndex=0;
	 */

	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	CheckList str = null;
	try {
	    str = checkservice.getLandApply(flag, pageindex, size, order,
		    orderDir);
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

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

    // 查询所有交费中的记录
    @RequestMapping("/agApply.do")
    public String agApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	Integer flag = Integer.valueOf(request.getParameter("flag"));
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));

	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
								// desc

	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	CheckList str = null;
	try {
	    str = checkservice.getLandApply(flag, pageindex, size, order,
		    orderDir);
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

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

    // 逾期恢复
    @RequestMapping("/overduerecovery.do")
    public String overduerecovery(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String recordstr = request.getParameter("recordstr");
	System.out.println(recordstr+"ppppppp");
	String infostr = request.getParameter("infostr");
	System.out.println(infostr+"qqqqqqqq");
	checkservice.overduerecovery(recordstr, infostr);

	JSONObject getObj = new JSONObject();
	getObj.put("str", " 逾期恢复成功");

	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 同意申请
    @RequestMapping("/agreeApply.do")
    public String agreeApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String recordstr = request.getParameter("recordstr");
	String infostr = request.getParameter("infostr");
	String landstr = request.getParameter("landstr");

	int tag = checkservice.agreeApply(landstr, recordstr, infostr);

	JSONObject getObj = new JSONObject();	
	getObj.put("tag", tag);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 拒绝申请
    @RequestMapping("/refuseApply.do")
    public String refuseApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String recordstr = request.getParameter("recordstr");
	String infostr = request.getParameter("infostr");

	int flag=checkservice.refuseapply(recordstr, infostr);

	JSONObject getObj = new JSONObject();
	getObj.put("str", flag);

	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 确认交费
    @RequestMapping("/confirm.do")
    public String confirm(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {

	String recordstr = request.getParameter("recordstr");
	String infostr = request.getParameter("infostr");
	String landstr = request.getParameter("landstr");

	int flag=checkservice.confirmPayFor(landstr, recordstr, infostr);

	JSONObject getObj = new JSONObject();
	getObj.put("str", flag);

	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;

    }

    // 取消交费
    @RequestMapping("/cancel.do")
    public String cancel(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String recordstr = request.getParameter("recordstr");
	String infostr = request.getParameter("infostr");
	String landstr = request.getParameter("landstr");

	int flag=checkservice.cancelPayFor(landstr, recordstr, infostr);

	JSONObject getObj = new JSONObject();
	getObj.put("str", flag);

	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;

    }

    // 土地租赁详情查看
    @RequestMapping("/detail.do")
    public String Rentdetail(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	Integer la_id = Integer.parseInt(request.getParameter("la_id"));
	// System.out.println(la_id);
	List<CheckView> list = null;
	try {
	    list = checkservice.Rentdetail(la_id);
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

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

    // 土地租赁详情查看2
    @RequestMapping("/detail2.do")
    public String Rentdetail2(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	Integer la_id = Integer.parseInt(request.getParameter("la_id"));
	List<CheckView> list = null;
	try {
	    list = checkservice.Rentdetail2(la_id);
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

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

    // 刷选功能
    @SuppressWarnings("unchecked")
    @RequestMapping("/Selet.do")
    public String Select(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) throws IOException {
	Integer flag = Integer.valueOf(request.getParameter("flag"));
	System.out.println("刷选的flag:"+flag);
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));

	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
								// desc

	// System.out.println(startIndex);
	// System.out.println(size);
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	String basename = request.getParameter("basename");
	if (basename.equals("")) {
	    basename = null;
	}
	String username = request.getParameter("username");

	if (username.equals("")) {
	    username = null;
	}
	String usercollage = request.getParameter("usercollage");

	if (usercollage.equals("")) {
	    usercollage = null;
	}
	
	CheckList list = new CheckList();
	try {
	    list = checkservice.getInfo(flag, pageindex, size, basename,
		    username, usercollage, order, orderDir);
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	JSONObject getObj = new JSONObject();
	getObj.put("draw", draw);
	getObj.put("recordsFiltered", list.getRecordsTotal());
	getObj.put("recordsTotal", list.getRecordsTotal());
	getObj.put("data", list.getData());
	System.out.println("数据条数:"+list.getRecordsTotal());
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;

    }

    // 刷选功能2
    @SuppressWarnings("unchecked")
    @RequestMapping("/Select2.do")
    public String Select2(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) throws IOException {
	Integer flag = Integer.valueOf(request.getParameter("flag"));
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
								// desc

	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	String basename = request.getParameter("basename");
	if (basename.equals("")) {
	    basename = null;
	}
	String username = request.getParameter("username");
	if (username.equals("")) {
	    username = null;
	}
	String usercollage = request.getParameter("usercollage");

	if (usercollage.equals("")) {
	    usercollage = null;
	}
	CheckList list = new CheckList();
	try {
	    list = checkservice.getInfo(flag, pageindex, size, basename,
		    username, usercollage, order, orderDir);
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
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

    // 基地查询
    @RequestMapping("/BaseInfoR.do")
    public String selectBase(HttpServletRequest request, ModelMap map,
	    HttpServletResponse response) {
	List<BaseInfo> list = checkservice.getBaseInfos();
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

    // 申请人查询
    @SuppressWarnings("rawtypes")
    @RequestMapping("/selectal.do")
    public String selectal(HttpServletRequest request, ModelMap map,
	    HttpServletResponse response) {
	List<UserInfo> list = null;
	try {
	    list = checkservice.getappliInfos();
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
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

    // 申请学院查询
    @RequestMapping("/selectCo.do")
    public String selectCo(HttpServletRequest request, ModelMap map,
	    HttpServletResponse response) {
	List<UserInfo> list = null;
	try {
	    list = checkservice.getDept();
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
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
}
