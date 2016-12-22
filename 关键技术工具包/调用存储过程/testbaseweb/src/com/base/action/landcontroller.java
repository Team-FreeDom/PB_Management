package com.base.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.dao.land;
import com.base.po.CheckView;
import com.base.po.landprocedure;
import com.base.po.song;
import com.base.service.landservice;
import com.base.service.songservice;

@Controller("landcontroller")
@RequestMapping("/jsp")

public class landcontroller
{
	@Autowired
	private landservice lanservice;
	@Autowired
	private songservice soservice;
	@RequestMapping("/query.do")
	public String getland(HttpServletRequest request,ModelMap map)
	{
		
		
/*		List<landprocedure> list=lanservice.getland();
		if(list==null)
			return null;*/		
		List<CheckView> list1=lanservice.getall();
		Iterator<CheckView> it=list1.iterator();
		
		list1=null;
/*		Iterator<landprocedure> it=list.iterator();
		while(it.hasNext())
		{
			System.out.print(it.next().getStartTime());
			System.out.print(it.next().getApplicantId());
			System.out.println(it.next().getBname());
		}*/
		map.addAttribute("list", list1);
		return "index";
	}
/*	public String getland(HttpServletRequest request,ModelMap map)
	{
		song so=soservice.update();
		System.out.println(so.getMoney()+"controller");
		map.addAttribute("so", so);
		return "index";
	}*/
/*	public String getland(HttpServletRequest request,ModelMap map)
	{
		List<song> list=soservice.update();
		map.addAttribute("list", list);
		return "index";
	}*/
}
