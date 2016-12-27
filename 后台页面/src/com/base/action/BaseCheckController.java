package com.base.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;
import com.base.po.CheckList;
import com.base.service.BaseCheckService;

/**
 * 基地审批模块
 * 
 * @author 梦醒何处
 * 
 */
@Controller("BaseCheckController")
@RequestMapping("/jsp")
public class BaseCheckController {
    
    @Autowired
    private BaseCheckService basecheckservice;

    /**
     * 得到基地申请中的基本信息
     * 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("/getBaseCheck.do")
    public String getBaseCheck(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	System.out.println("进来没哟普");
	// 获取申请部门id
	int applydpid=-1;
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	System.out.println(size+"1");
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));

	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	System.out.println(order+"2");
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
	System.out.println(orderDir+"3");			// desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	System.out.println(pageindex+"4");
	BaseCheckList str = null;
	str = basecheckservice.getBaseCheck(applydpid, pageindex,size,order,
		orderDir);
	List<BaseCheck> list=str.getData();
	for (BaseCheck baseCheck : list) {
	    System.out.println(baseCheck.getUndertake()+"是的");
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
    //获取刷选部门
    @RequestMapping("/getApplyDept.do")
    public String getApplyDept(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map){
	List<BaseCheck> list = null; 
	list = basecheckservice.getDept();
	for (BaseCheck baseCheck : list) {
	    System.out.println(baseCheck.getAid()+"申请部门id");
	    System.out.println(baseCheck.getApplydp()+"申请部门");
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
    //刷选功能（根据部门id刷选）
    @RequestMapping("/getXUBaseCheck.do")
    public String getXUBaseCheck(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	System.out.println("这是刷选模块");
	// 获取申请部门id
	Integer applydpid=Integer.parseInt(request.getParameter("dept"));
	System.out.println(applydpid+"这是哪个部门id");
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or			// desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	BaseCheckList str = null;
	str = basecheckservice.getBaseCheck(applydpid, pageindex,size,order,
		orderDir);
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
}
