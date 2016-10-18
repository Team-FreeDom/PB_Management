package com.base.action;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

//交费模块的controller类
public class PayForController {

	//拒绝交费
	@RequestMapping("/refusePay.do")
	public String refusePay(ModelMap map)
	{
		return null;		
	}
	
	//交费成功
	@RequestMapping("/paySuccess.do")
	public String paySuccess(ModelMap map)
	{
		return null;		
	}
}
