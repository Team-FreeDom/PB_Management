package com.base.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;
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
	// 获取申请部门id
	int applydpid = -1;
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or // desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	BaseCheckList str = null;
	str = basecheckservice.getBaseCheck(applydpid, pageindex, size, order,
		orderDir);
	/*List<BaseCheck> li = new ArrayList();
	int aid = 0,undertake=0,id=0;
	String name = null,bid=null,type=null,landarea=null,constructionarea=null,applydp=null,land_address=null,username=null,phone=null,material_path=null,userid=null;
	List<BaseCheck> list=str.getData();
	for (int i = 0; i <list.size(); i++) {	
	    id=list.get(i).getId();	   
	    aid=list.get(i).getAid();
	    bid=list.get(i).getBid();
	    name=list.get(i).getName();
	    type=list.get(i).getType();
	    landarea=list.get(i).getLandarea();
	    constructionarea=list.get(i).getConstructionarea();
	    undertake=list.get(i).getUndertake();
	    applydp=list.get(i).getApplydp();
	    land_address=list.get(i).getLand_address();
	    username=list.get(i).getUsername();
	    phone=list.get(i).getPhone();
	    material_path=list.get(i).getMaterial_path();
	    userid=list.get(i).getUserid();
	}
	   BaseCheck ch=new BaseCheck();
	   ch.setId(id);
	    ch.setAid(aid);
	    ch.setBid(bid);
	    ch.setName(name);
	    ch.setType(type);
	    ch.setApplydp(applydp);
	    ch.setLandarea(landarea);
	    ch.setConstructionarea(constructionarea);
	    ch.setUndertake(undertake);	
	    ch.setLand_address(land_address);
	    ch.setUsername(username);
	    ch.setPhone(phone);	 	    
	    ch.setMaterial_path( material_path);
	   // ch.setMmajor(baseCheck.getMmajor());       
	    ch.setUserid(userid);
	   li.add(ch);*/
	JSONObject getObj = new JSONObject();
	getObj.put("draw", draw);
	getObj.put("recordsFiltered", str.getRecordsTotal());
	getObj.put("recordsTotal", str.getRecordsTotal());
	getObj.put("data",str.getData());
	response.setContentType("text/html;charset=UTF-8");

	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 获取刷选部门
    @RequestMapping("/getApplyDept.do")
    public String getApplyDept(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	List<BaseCheck> list = null;
	list = basecheckservice.getDept();
	for (BaseCheck baseCheck : list) {
	    System.out.println(baseCheck.getAid() + "申请部门id");
	    System.out.println(baseCheck.getApplydp() + "申请部门");
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

    // 刷选功能（根据部门id刷选）
    @RequestMapping("/getXUBaseCheck.do")
    public String getXUBaseCheck(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 获取申请部门id
	String Str=request.getParameter("dept");
	Integer applydpid;
	if(Str.equals("")){
	    applydpid=-1;
	}else{
	    applydpid= Integer.parseInt(Str);   
	}
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or								// // desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	
	BaseCheckList str = null;
	str = basecheckservice.getBaseCheck(applydpid, pageindex, size, order,orderDir);
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

    // 拒绝申请
    @RequestMapping("/BaserefuseApply.do")
    public String refuseApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	//包装单选框的id信息
	String recordstr = request.getParameter("recordstr");
	//获取前台json消息数据
	String infostr = request.getParameter("infostr");
	//获取拒绝理由
	String reason=request.getParameter("reason");
	basecheckservice.refuseapply(recordstr,infostr);
	JSONObject getObj = new JSONObject();
	getObj.put("str", "此申请处理失败");
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
 // 同意申请(并且发送消息给用户)
    @RequestMapping("/BasereAgreeApply.do")
    public String agreeApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	System.out.println("哈哈哈哈哈哈");
	//获取单选id
	String recordstr = request.getParameter("recordstr");
	//获取前台json消息数据
	String infostr = request.getParameter("infostr");
	//获取有效期
	Integer date=Integer.parseInt(request.getParameter("date"));
	basecheckservice.agreeApply(recordstr,infostr,date);
	JSONObject getObj = new JSONObject();
	getObj.put("str", "此申请处理成功");
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
