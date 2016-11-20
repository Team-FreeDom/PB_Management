package com.base.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.RentMaintain;
import com.base.serviceImpl.LandRentServiceImpl;

//使用模块的控制层
@Controller("landRentController")
@RequestMapping("/jsp")
public class LandRentController {
	
	@Autowired
	private LandRentServiceImpl landRentServiceImpl;
	
	//土地租赁记录
	@RequestMapping("/landRentInfo.do")
	public String selectBase(HttpServletRequest request,HttpServletResponse response, ModelMap map)
	{		
		System.out.println("展示土地租赁信息");
		
		List<RentMaintain> list=landRentServiceImpl.getLandRentInfos(null, null, null, null);		
		
		JSONObject getObj = new JSONObject();
		getObj.put("data", list);
	
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
	
	
	

}
