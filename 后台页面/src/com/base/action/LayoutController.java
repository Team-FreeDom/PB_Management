package com.base.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

//���ز���ģ���controller��
public class LayoutController {
	
	//���ز����ύ
	@RequestMapping("/submitLayout.do")
	public String submitLayout(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//�޸����ز���
	@RequestMapping("/updateLayout.do")
	public String updateLayout(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//ɾ������
	@RequestMapping("/deleteLayout.do")
	public String deleteLayout(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}

}
