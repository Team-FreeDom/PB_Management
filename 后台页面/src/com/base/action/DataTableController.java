package com.base.action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.po.Employee;

@RequestMapping("/jsp1")
@Controller
public class DataTableController {
	
	
	
	
	@RequestMapping("/selfApply.do")
	public String selfApply(HttpServletRequest request,ModelMap map)
	{
		return null;
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/tableDemoAjax")  
	@ResponseBody  
	public String tableDemoAjax(HttpServletRequest request,HttpServletResponse response,ModelMap map) {  
		 //System.out.println("enter tableDemoAjax");
		  //Map<String, Object> map = new HashMap<String, Object>();
			int count=20;
			List<Employee> list=new ArrayList<Employee>();
			for (int i = 0; i < 100; i++) {
				Employee employee=new Employee();
				employee.setName("Sakura Yamamoto");
				employee.setPosition("Support Engineer");
				employee.setOffice("Tokyo");
			
				list.add(employee);
			}
			 
		    JSONObject getObj = new JSONObject(); 
			response.setContentType("text/html;charset=UTF-8");
			getObj.put("recordsTotal", 100);
			getObj.put("recordsFiltered", 10);
			getObj.put("data", list);
			try {
				response.getWriter().print(getObj.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //return map;  
			return null;
	}  
}
