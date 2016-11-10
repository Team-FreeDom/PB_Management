package com.fendo.Test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fendo.model.Ceshi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/datatables")
@Controller
public class TestController {
	
	
	@RequestMapping(value="/listceshi")
	@ResponseBody
	public Map<String, Object>  listjsonceshi(){
		 Map<String, Object> map = new HashMap<String, Object>();
		int count=20;
		List<Ceshi> list=new ArrayList<Ceshi>();
		for (int i = 0; i < 20; i++) {
			Ceshi ceshi=new Ceshi();
			ceshi.setId(i);
			ceshi.setUsername("С"+i);
			list.add(ceshi);
		}
		map.put("recordsTotal", count);
	    map.put("recordsFiltered", count);
	    map.put("aaData", list);
		return map;
	}
	
	
}
