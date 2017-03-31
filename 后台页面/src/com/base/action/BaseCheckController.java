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
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;
import com.base.service.BaseCheckService;

/**
 * 基地审批模块
 * 
 * @author 梦醒何处
 * 
 */
// 实习基地申请审核控制层
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

    // 获取申请中的实习基地申请记录
    @RequestMapping("/getBaseCheck.do")
    public String getBaseCheck(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 获取申请部门id
	int applydpid = -1;
	// 获取用户过滤框里的字符
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
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
								// // desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	BaseCheckList str = null;
	str = basecheckservice.getBaseCheck(applydpid, pageindex, size, order,
		orderDir, searchValue);
	JSONObject getObj = new JSONObject();
	getObj.put("draw", draw);
	getObj.put("recordsFiltered", str.getRecordsTotal());
	getObj.put("recordsTotal", str.getRecordsTotal());
	getObj.put("data", str.getData());
	response.setContentType("text/html;charset=UTF-8");

	try {
	    response.getWriter().print(getObj.toString());
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 获取续期中的实习基地申请记录
    @RequestMapping("/getaddCheck.do")
    public String getaddCheck(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 获取申请部门id
	int applydpid = -1;
	// 获取用户过滤框里的字符
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
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
								// // desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	BaseCheckList str = null;
	str = basecheckservice.getaddCheck(applydpid, pageindex, size, order,
		orderDir, searchValue);
	JSONObject getObj = new JSONObject();
	getObj.put("draw", draw);
	getObj.put("recordsFiltered", str.getRecordsTotal());
	getObj.put("recordsTotal", str.getRecordsTotal());
	getObj.put("data", str.getData());
	response.setContentType("text/html;charset=UTF-8");

	try {
	    response.getWriter().print(getObj.toString());
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 获取续期中的实习基地申请记录（刷选）
    @RequestMapping("/getBushaddCheck.do")
    public String getBushaddCheck(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 获取申请部门id
	String Str = request.getParameter("dept");
	Integer applydpid;
	if (Str.equals("")) {
	    applydpid = -1;
	} else {
	    applydpid = Integer.parseInt(Str);
	}
	// 获取用户过滤框里的字符
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
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
								// // desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	BaseCheckList str = null;
	str = basecheckservice.getaddCheck(applydpid, pageindex, size, order,
		orderDir, searchValue);
	JSONObject getObj = new JSONObject();
	getObj.put("draw", draw);
	getObj.put("recordsFiltered", str.getRecordsTotal());
	getObj.put("recordsTotal", str.getRecordsTotal());
	getObj.put("data", str.getData());
	response.setContentType("text/html;charset=UTF-8");

	try {
	    response.getWriter().print(getObj.toString());
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 获取刷选的部门
    @RequestMapping("/getApplyDept.do")
    public String getApplyDept(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {

	List list = basecheckservice.getDept();
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
	// 获取用户过滤框里的字符
	String searchValue = request.getParameter("search[value]");
	if (searchValue.equals("")) {
	    searchValue = null;
	}
	// 获取申请部门id
	String Str = request.getParameter("dept");
	Integer applydpid;
	if (Str.equals("")) {
	    applydpid = -1;
	} else {
	    applydpid = Integer.parseInt(Str);
	}
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
								// // // desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);

	BaseCheckList str = null;
	str = basecheckservice.getBaseCheck(applydpid, pageindex, size, order,
		orderDir, searchValue);
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

    // 拒绝实习基地申请
    @RequestMapping("/BaserefuseApply.do")
    public String refuseApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 包装单选框的id信息
	String recordstr = request.getParameter("recordstr");
	// 获取前台json消息数据
	String infostr = request.getParameter("infostr");
	// 获取基地打包id
	String recorddigit = request.getParameter("recorddigit");
	// 获取拒绝理由
	// String reason=request.getParameter("reason");
	int flag = basecheckservice
		.refuseapply(recorddigit, recordstr, infostr);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(flag);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 拒绝续期申请
    @RequestMapping("/refuseAddApply.do")
    public String refuseAddApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 包装单选框的id信息
	String recordstr = request.getParameter("recordstr");
	// 获取前台json消息数据
	String infostr = request.getParameter("infostr");
	System.out.println("controller->infostr:" + infostr);
	// 获取基地打包id
	String recorddigit = request.getParameter("recorddigit");
	int flag = basecheckservice.refuseDateApply(recorddigit, recordstr,
		infostr);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(flag);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 同意实习基地申请
    @RequestMapping("/BasereAgreeApply.do")
    public String agreeApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	int index = Integer.parseInt(request.getParameter("index"));
	System.out.println(index + "李彩缺根弦");
	if (index == 0) {
	    // 封装的记录id和申请年限
	    String recordstr = request.getParameter("recordstr");
	    System.out.println(recordstr + "songspng");
	    // 获取前台json消息数据
	    String infostr = request.getParameter("infostr");
	    System.out.println(infostr + "panpan");
	    // 获取单选id
	    String recorddigit = request.getParameter("recorddigit");
	    System.out.println(recorddigit + "lilili");
	    int flag = basecheckservice.agreeApply(recorddigit, infostr,
		    recordstr);
	    response.setContentType("text/html;charset=UTF-8");
	    try {
		response.getWriter().print(flag);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    // 获取单选id
	    String recorddigit = request.getParameter("recorddigit");
	    int flag = basecheckservice.checkBaseName(recorddigit);	 
	    response.setContentType("text/html;charset=UTF-8");
	    try {
		response.getWriter().print(flag);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	return null;
    }

    // 同意续期申请
    @RequestMapping("/addAgreeApply.do")
    public String addagreeApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	System.out.println("hahahahahha");
	// 封装的记录id
	String recordstr = request.getParameter("recordstr");
	// 获取前台json消息数据
	String infostr = request.getParameter("infostr");
	// 获取单选id
	System.out.println("controller->infostr:" + infostr);
	int flag = basecheckservice.addDateApply(infostr, recordstr);
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
