package com.base.action;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.BaseCheckList;
import com.base.po.MyBaseList;
import com.base.service.MyBaseInfoService;

/**
 * 我的基地模块
 * 
 * @author 梦醒何处
 * 
 */
// 我的实习申请控制层
@Controller("MybaseController")
@RequestMapping("/jsp")
public class MybaseController {

    @Autowired
    private MyBaseInfoService mybaseinfoservice;

    // 获取用户的当前阶段的实习基地申请
    @RequestMapping("/MybaseInfo.do")
    public String MybaseInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	Cookie[] cookies = request.getCookies();// 获得所有cookie对象
	for (Cookie co : cookies) {
	    if (co.getName().equals("username")) {
		// 获取userid
		String userid = co.getValue();
		// 获取年份
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		// 获取当前页面的传输几条记录
		Integer size = Integer.parseInt(request.getParameter("length"));
		// 数据起始位置
		Integer startIndex = Integer.parseInt(request
			.getParameter("start"));
		Integer draw = Integer.parseInt(request.getParameter("draw"));
		int order = Integer.valueOf(request
			.getParameter("order[0][column]"));// 排序的列号
		System.out.println("order:" + order);
		// String orderDir = request.getParameter("order[0][dir]");//
		// 排序的顺序asc or
		String orderDir = "desc"; // // desc
		// 通过计算求出当前页面为第几页
		Integer pageindex = (startIndex / size + 1);
		// 打包状态的id（status）2-待审核 6-申请成功 11-失效 12-申请失败

		// -------start-------
		/*
		 * String status = ""; String st[] = { "2", "6", "11", "12" };
		 * StringBuffer sb = new StringBuffer(); for (String string :
		 * st) { sb.append(string); sb.append(","); }
		 * sb.deleteCharAt(sb.length() - 1); status = sb.toString();
		 * status = "(" + st + ")";
		 */
		// -------end-------
		// -1代表获取4个状态值
		int status = -1;

		MyBaseList str = null;
		String columnName = "id";
		str = mybaseinfoservice.MybaseInfo(pageindex, size, columnName,
			orderDir, year, status, userid);
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
	    }
	}
	return null;

    }

    // 获取用户的所有实习基地申请
    @RequestMapping("/MybaseInfo2.do")
    public String MybaseInfo2(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	Cookie[] cookies = request.getCookies();// 获得所有cookie对象
	for (Cookie co : cookies) {
	    if (co.getName().equals("username")) {
		// 获取userid
		String userid = co.getValue();
		// 获取当前页面的传输几条记录
		Integer size = Integer.parseInt(request.getParameter("length"));
		// 数据起始位置
		Integer startIndex = Integer.parseInt(request
			.getParameter("start"));
		Integer draw = Integer.parseInt(request.getParameter("draw"));
		int order = Integer.valueOf(request
			.getParameter("order[0][column]"));// 排序的列号
		String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc

		// or //
		// //
		// desc
		// 通过计算求出当前页面为第几页
		Integer pageindex = (startIndex / size + 1);
		// 打包状态的id（status）2-待审核 6-申请成功 11-失效 12-申请失败
		// Integer st =
		// Integer.parseInt(request.getParameter("status"));
		// System.out.println(st+"传来的在哪里aaaaa");
		/*
		 * String status = ""; if (st != null) { status = "(" + st +
		 * ")"; } else { status = "(" + "6,11,12" + ")"; } //
		 * -------end-------
		 */
		MyBaseList str = null;

		str = mybaseinfoservice.MybaseInfo2(pageindex, size, order,
			orderDir, -1, -2, userid);

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
	    }
	}
	return null;
    }

    // 实习基地申请撤回
    @RequestMapping("/recall.do")
    public String recall(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	
	// 获取撤回哪条记录id
	String id = request.getParameter("id");	
	// 获取撤回信息
	String infostr = request.getParameter("infostr");
	System.out.println("tag: "+request.getParameter("tag"));
	int tag = Integer.valueOf(request.getParameter("tag"));
	int flag=mybaseinfoservice.recall(id, infostr,tag);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(flag);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;

    }

    // 实习基地续期
    @RequestMapping("/updateMyBaseDate.do")
    public String updateBaseInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	int id = Integer.valueOf(request.getParameter("id"));
	String date = request.getParameter("adddate");
	mybaseinfoservice.updateDate(id, date);
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

    // 实习记录刷选(根据状态值刷选)
    @RequestMapping("/getStatus.do")
    public String getStatus(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	Cookie[] cookies = request.getCookies();// 获得所有cookie对象
	for (Cookie co : cookies) {
	    if (co.getName().equals("username")) {
		// 获取userid
		String userid = co.getValue();
		// 获取当前页面的传输几条记录
		Integer size = Integer.parseInt(request.getParameter("length"));
		System.out.println(size + "当前几条记录lalalalala");
		// 获取状态值 失败0；成功：1 失效：2 正常情况 失败：12 成功：6 失效：11
		Integer status = Integer.parseInt(request
			.getParameter("status"));

		// 数据起始位置
		Integer startIndex = Integer.parseInt(request
			.getParameter("start"));
		Integer draw = Integer.parseInt(request.getParameter("draw"));
		int order = Integer.valueOf(request
			.getParameter("order[0][column]"));// 排序的列号
		System.out.println(order + "这是第几个列");
		String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc
									// or //
									// //
									// desc
		// 通过计算求出当前页面为第几页
		Integer pageindex = (startIndex / size + 1);
		System.out.println(pageindex + "当前第几页lalalla");
		MyBaseList str = null;

		str = mybaseinfoservice.MybaseInfo2(pageindex, size, order,
			orderDir, -1, status, userid);

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
	    }
	}
	return null;

    }
}
