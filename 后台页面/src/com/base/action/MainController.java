package com.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.serviceImpl.MainServiceImpl;


@Controller("mainController")
@RequestMapping("/jsp")
public class MainController {
	@Autowired
	private MainServiceImpl mainServiceImpl;
	
	@RequestMapping("/getCount.do")
	public String getCount(HttpServletRequest request, ModelMap map,
			HttpServletResponse response)
	{
		String str=mainServiceImpl.getCount();
		JSONArray json = JSONArray.fromObject(str);
		
    try{
		response.getWriter().print(json.toString());
	} catch (Exception e) {
		System.out.println(e);
	}

	return null;
	}

}
