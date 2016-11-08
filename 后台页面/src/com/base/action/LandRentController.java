package com.base.action;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

//使用模块的控制层
public class LandRentController {
	
	//土地使用情况查询
	@RequestMapping("/landUseInfo.do")
	public String selectBase(ModelMap map)
	{
		return null;		
	}
	
	

}
