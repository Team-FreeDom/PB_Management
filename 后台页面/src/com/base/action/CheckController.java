package com.base.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

//审核模块的controller类
public class CheckController {

	//查询所有申请记录
	@RequestMapping("/checkApplyRecord.do")
	public String checkApply(ModelMap map)
	{
		return null;		
	}	
	
	//同意申请
	@RequestMapping("/agreeApply.do")
	public String agreeApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//拒绝他人申请
	@RequestMapping("/refuseApply.do")
	public String refuseApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	
	
}
