package com.base.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

//����ģ��Ŀ��Ʋ�
@Controller("landApplyController")
public class LandApplyController {

	//���ز�ѯ
	@RequestMapping("/baseInfo.do")
	public String selectBase(ModelMap map)
	{
		return null;		
	}
	
	//���ز��ֲ�ѯ
	@RequestMapping("/layout.do")
	public String selectLandLayout(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//������Ϣ��ѯ
	@RequestMapping("/landInfo.do")
	public String selectLandInfo(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//�·ݿ��в�ѯ
	@RequestMapping("/timeSpare.do")
	public String timeSpare(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	
	//��ȡ������Ϣ(������ް�ť����ȡ��ʦ+����+������Ϣ)
	@RequestMapping("/AllApplyInfo.do")
	public String getAllApplyInfo(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//�ύ����
	@RequestMapping("/submitApply.do")
	public String submitApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//�޸�����
	@RequestMapping("/updateApply.do")
	public String updateApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//�鿴����
	@RequestMapping("/checkApply.do")
	public String checkApply(ModelMap map)
	{
		return null;		
	}
	
	//ȡ������
	@RequestMapping("/cancelApply.do")
	public String cancelApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
}
