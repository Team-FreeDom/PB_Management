package com.base.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

//���ģ���controller��
public class CheckController {

	//��ѯ���������¼
	@RequestMapping("/checkApplyRecord.do")
	public String checkApply(ModelMap map)
	{
		return null;		
	}	
	
	//ͬ������
	@RequestMapping("/agreeApply.do")
	public String agreeApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//�ܾ���������
	@RequestMapping("/refuseApply.do")
	public String refuseApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	
	
}
