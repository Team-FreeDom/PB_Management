package com.base.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

//土地布局模块的controller类
public class LayoutController {
	
	//土地布局提交
	@RequestMapping("/submitLayout.do")
	public String submitLayout(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//修改土地布局
	@RequestMapping("/updateLayout.do")
	public String updateLayout(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//删除土地
	@RequestMapping("/deleteLayout.do")
	public String deleteLayout(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}

}
