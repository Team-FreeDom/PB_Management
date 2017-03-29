package com.base.action;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.MymaintainList;
import com.base.service.MymaintainService;
import com.base.utils.CookieUtils;

//我的报修控制层
@Controller("MymaintainController")
@RequestMapping("/jsp")
public class MymaintainController {
    @Autowired
    private MymaintainService mymaintainservice;

    // 获取该用户当年的报修申请记录
    @RequestMapping("/Mymaintain.do")
    public String Mymaintain(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String userid = CookieUtils.getUserid(request);
	// 获取年份
	Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
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
	int status = -1;
	MymaintainList str = null;
	String columnName = "id";
	str = mymaintainservice.Mymaintain(pageindex, size, columnName,
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
	return null;
    }

    // 获取该用户所有的报修申请记录
    @RequestMapping("/Mymaintain2.do")
    public String Mymaintain2(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	System.out.println("哈哈哈哈我进来了");
	String userid = CookieUtils.getUserid(request);
	System.out.println(userid + "这是哪个id");
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// // 排序的顺序asc
								// or desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	int status = -2;
	MymaintainList str = null;
	str = mymaintainservice.Mymaintain2(pageindex, size, order, orderDir,
		-1, status, userid);
	System.out.println(str.getRecordsTotal() + "不知道几条");
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

    // 根据状态值筛选，获取报修信息记录
    @RequestMapping("/screen.do")
    public String screen(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String userid = CookieUtils.getUserid(request);
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// // 排序的顺序asc
								// // or desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	// 获取状态值 失败2；成功：1 维修完成：3 正常情况 失败：12 维修中：14 维修完成：15
	Integer status = Integer.parseInt(request.getParameter("status"));
	/*
	 * if (status == 2) { status = 12; } else if (status == 1) { status =
	 * 14; } else if (status == 3) { status = 15; } else { status = -2; }
	 */
	MymaintainList str = null;
	str = mymaintainservice.Mymaintain2(pageindex, size, order, orderDir,
		-1, status, userid);
	System.out.println(str.getRecordsTotal() + "不知道几条");
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

    // 撤回申请
    @RequestMapping("/recallmymaint.do")
    public String recallmymaint(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	System.out.println(" 进去撤回");
	// 获取撤回哪条记录id
	String id = request.getParameter("id");
	System.out.println(id);
	// 获取撤回信息
	String infostr = request.getParameter("infostr");
	System.out.println(infostr + "什么信息");
	int flag=mymaintainservice.recallmymaint(id, infostr);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(flag);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;

    }

}
