package com.base.action;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

//����ģ���controller��
public class PayForController {

	//�ܾ�����
	@RequestMapping("/refusePay.do")
	public String refusePay(ModelMap map)
	{
		return null;		
	}
	
	//���ѳɹ�
	@RequestMapping("/paySuccess.do")
	public String paySuccess(ModelMap map)
	{
		return null;		
	}
}
