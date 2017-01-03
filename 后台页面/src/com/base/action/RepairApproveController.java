package com.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("repairApproveController")
@RequestMapping("/jsp")
public class RepairApproveController {
	
	//获得审核中的报修信息
	   @RequestMapping("/getNoRepair.do")
	    public String getNoRepair(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }
	   
   //获得审核同意后的报修信息
	   @RequestMapping("/getAgreeRepair.do")
	    public String getAgreeRepair(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }	   
	 
	   
	 //获得筛选后的审核中的报修信息
	   @RequestMapping("/getChooseRepair1.do")
	    public String getChooseRepair1(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }
	   
	 //获得筛选后的审核同意的报修信息
	   @RequestMapping("/getChooseRepair2.do")
	    public String getChooseRepair2(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
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
