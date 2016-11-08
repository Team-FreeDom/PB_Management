package com.base.Test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.daoImpl.CollegeDaoImpl;
import com.base.po.College;

@Controller("test")
@RequestMapping("/jsp")
public class Test {
	
	@Autowired
	private CollegeDaoImpl collegeDaoImpl;
	
	@RequestMapping("/test.do")
	public String test(ModelMap map)
	{
		List<College> list=collegeDaoImpl.getColleges();
		map.addAttribute("list", list);
		return "index1";
	}


}
