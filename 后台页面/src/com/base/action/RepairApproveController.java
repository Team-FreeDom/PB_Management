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

@Controller("repairApproveController")
@RequestMapping("/jsp")   //状态：申请为13，维修为14
public class RepairApproveController {
	
	@Autowired
	private RepairApproveService repairApproveService;
	
	//获得审核中的报修信息
	   @RequestMapping("/getNoRepair.do")
	    public String getNoRepair(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   Integer size = Integer.parseInt(request.getParameter("length"));		
			Integer startIndex = Integer.parseInt(request.getParameter("start"));
			Integer draw = Integer.parseInt(request.getParameter("draw"));	
			int order = Integer.valueOf(request.getParameter("order[0][column]"));//列号
		    String orderDir = request.getParameter("order[0][dir]");//排序方法
		    String searchValue = request.getParameter("search[value]");
		
			// 获得当前页码
			Integer pageindex = (startIndex / size + 1);
			
			MaintainList str=null;		
			str=repairApproveService.getRepairInfo("", "", pageindex, size, order, orderDir, searchValue, 13);
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
	   
   //获得审核同意后的报修信息
	   @RequestMapping("/getAgreeRepair.do")
	    public String getAgreeRepair(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   Integer size = Integer.parseInt(request.getParameter("length"));		
			Integer startIndex = Integer.parseInt(request.getParameter("start"));
			Integer draw = Integer.parseInt(request.getParameter("draw"));	
			int order = Integer.valueOf(request.getParameter("order[0][column]"));//列号
		    String orderDir = request.getParameter("order[0][dir]");//排序方法
		    String searchValue = request.getParameter("search[value]");
		
			// 获得当前页码
			Integer pageindex = (startIndex / size + 1);
			
			MaintainList str=null;		
			str=repairApproveService.getRepairInfo("", "", pageindex, size, order, orderDir, searchValue, 14);
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
	 
	   
	 //获得筛选后的审核中的报修信息
	   @RequestMapping("/getChooseRepair1.do")
	    public String getChooseRepair1(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   Integer size = Integer.parseInt(request.getParameter("length"));		
			Integer startIndex = Integer.parseInt(request.getParameter("start"));
			Integer draw = Integer.parseInt(request.getParameter("draw"));	
			int order = Integer.valueOf(request.getParameter("order[0][column]"));//列号
		    String orderDir = request.getParameter("order[0][dir]");//排序方法
		    String searchValue = request.getParameter("search[value]");
		   
		    String baseid = request.getParameter("baseid");
		    String username = request.getParameter("username");
		    
			// 获得当前页码
			Integer pageindex = (startIndex / size + 1);
			
			MaintainList str=null;		
			str=repairApproveService.getRepairInfo(baseid, username, pageindex, size, order, orderDir, searchValue, 13);
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
	   
	 //获得筛选后的审核同意的报修信息
	   @RequestMapping("/getChooseRepair2.do")
	    public String getChooseRepair2(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   Integer size = Integer.parseInt(request.getParameter("length"));		
			Integer startIndex = Integer.parseInt(request.getParameter("start"));
			Integer draw = Integer.parseInt(request.getParameter("draw"));	
			int order = Integer.valueOf(request.getParameter("order[0][column]"));//列号
		    String orderDir = request.getParameter("order[0][dir]");//排序方法
		    String searchValue = request.getParameter("search[value]");
		   
		    String baseid = request.getParameter("baseid");
		    String username = request.getParameter("username");
		    
			// 获得当前页码
			Integer pageindex = (startIndex / size + 1);
			
			MaintainList str=null;		
			str=repairApproveService.getRepairInfo(baseid, username, pageindex, size, order, orderDir, searchValue, 14);
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
	   
	   //获得申报人和基地名称
	   @RequestMapping("/getInfoApply.do")
	    public String getInfoApply(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   List list=new ArrayList();
		   list=repairApproveService.getInfoApply();
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
	   
	   //拒绝申请
	   @RequestMapping("/refuseRepairApply.do")
	    public String refuseRepairApply(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }
	   
	   //同意申请
	   @RequestMapping("/agreeRepairApply.do")
	    public String agreeRepairApply(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		  
		   return null;		   
	   }
	   
	   //维修完成
	   @RequestMapping("/repairFinish.do")
	    public String repairFinish(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }
	   
}
