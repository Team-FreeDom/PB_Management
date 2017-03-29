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

import com.base.po.MaintainList;
import com.base.po.MaintenanceList;
import com.base.service.RepairApproveService;

//报修申请审核控制层
@Controller("repairApproveController")
@RequestMapping("/jsp")
public class RepairApproveController {

    @Autowired
    private RepairApproveService repairApproveService;

    // 获取审核中的报修申请记录
    @RequestMapping("/getNoRepair.do")
    public String getNoRepair(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {

	Integer size = Integer.parseInt(request.getParameter("length"));
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));
	String orderDir = request.getParameter("order[0][dir]");
	String searchValue = request.getParameter("search[value]");

	Integer pageindex = (startIndex / size + 1);

	MaintainList str = null;
	str = repairApproveService.getRepairInfo("", "", pageindex, size,
		order, orderDir, searchValue, 13);
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

    // 获取维修中的报修申请记录
    @RequestMapping("/getAgreeRepair.do")
    public String getAgreeRepair(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	Integer size = Integer.parseInt(request.getParameter("length"));
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));
	String orderDir = request.getParameter("order[0][dir]");
	String searchValue = request.getParameter("search[value]");

	//
	Integer pageindex = (startIndex / size + 1);

	MaintainList str = null;
	str = repairApproveService.getRepairInfo("", "", pageindex, size,
		order, orderDir, searchValue, 14);
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

    // 根据刷选条件获取审核中的报修申请记录
    @RequestMapping("/getChooseRepair1.do")
    public String getChooseRepair1(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {

	Integer size = Integer.parseInt(request.getParameter("length"));
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));
	String orderDir = request.getParameter("order[0][dir]");
	String searchValue = request.getParameter("search[value]");

	String baseid = request.getParameter("baseid");
	String username = request.getParameter("userid");

	Integer pageindex = (startIndex / size + 1);

	MaintainList str = null;
	str = repairApproveService.getRepairInfo(baseid, username, pageindex,
		size, order, orderDir, searchValue, 13);
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

    // 根据刷选条件获取维修中的报修申请记录
    @RequestMapping("/getChooseRepair2.do")
    public String getChooseRepair2(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	Integer size = Integer.parseInt(request.getParameter("length"));
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// �к�
	String orderDir = request.getParameter("order[0][dir]");// ���򷽷�
	String searchValue = request.getParameter("search[value]");

	String baseid = request.getParameter("baseid");
	String username = request.getParameter("userid");

	Integer pageindex = (startIndex / size + 1);

	MaintainList str = null;
	str = repairApproveService.getRepairInfo(baseid, username, pageindex,
		size, order, orderDir, searchValue, 14);
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

    // 获取基地名称和申报人集合
    @RequestMapping("/getInfoApply.do")
    public String getInfoApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	List list = new ArrayList();
	list = repairApproveService.getInfoApply();
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

    // 拒绝报修申请
    @RequestMapping("/refuseRepairApply.do")
    public String refuseRepairApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
        String recorddigit=request.getParameter("recorddigit");
        System.out.println(recorddigit+"浏阳河浏阳河浏阳河");
	String refusestr = request.getParameter("recordstr");
	System.out.println(refusestr+"李彩彩");
	String infostr = request.getParameter("infostr");
	int flag=repairApproveService.refuseRepairApply(recorddigit,refusestr, infostr);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(flag);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // ͬ同意报修申请
    @RequestMapping("/agreeRepairApply.do")
    public String agreeRepairApply(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	/*String recorddigit=request.getParameter("recorddigit");
        System.out.println(recorddigit+"浏阳河浏阳河浏阳河");*/
	String agreestr = request.getParameter("agreestr");
	System.out.println(agreestr+"哈哈哈哈");
	String infostr = request.getParameter("infostr");
	System.out.println(agreestr);
	int flag=repairApproveService.agreeRepairApply(agreestr, infostr);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(flag);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 维修完成
    @RequestMapping("/repairFinish.do")
    public String repairFinish(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String storestr = request.getParameter("recordstr");
	String infostr = request.getParameter("infostr");
	repairApproveService.finishRepairApply(storestr, infostr);

	JSONObject getObj = new JSONObject();
	getObj.put("str", "成功确认维修完成");
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
