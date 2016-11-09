package com.base.action;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.po.BaseInfo;
import com.base.po.LandInfo;
import com.base.po.LandLayout;
import com.base.po.Land_Planting;
import com.base.po.Land_base;
import com.base.serviceImpl.LandApplyServiceImpl;
import com.base.utils.CookieUtils;

//申请模块的控制层
@Controller("landApplyController")
@RequestMapping("/jsp1")
public class LandApplyController {
	
	@Autowired
	private LandApplyServiceImpl landApplyServiceImpl;

	//基地查询
	@RequestMapping("/baseInfo.do")
	public String selectBase(ModelMap map)
	{
		return null;		
	}
	
	//跳到具体租赁页面
	@RequestMapping("/mainRent.do")
	public String mainRent(HttpServletRequest request,ModelMap map,HttpServletResponse response)
	{			
		boolean flag=CookieUtils.addCookie(request, response);
		map.addAttribute("notimeout", flag);
		if(!flag)
		{
			return "index";
		}
		List<BaseInfo> base=landApplyServiceImpl.getBaseInfos(1);
		
		map.addAttribute("base", base);
		return "mainRent";
		
	}
	
	@RequestMapping("/getContent.do")	
	public String getContent(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws IOException
	{
		boolean flag=CookieUtils.addCookie(request, response);
		int bid = Integer.valueOf(request.getParameter("base"));
		String plant=request.getParameter("planting");	
		map.addAttribute("notimeout", flag);
		if(!plant.equals("-1"))
		{
			System.out.println(plant);
			List<String> str=landApplyServiceImpl.getLandLayout(bid, plant);
			map.addAttribute("str", str);
			map.addAttribute("flag2", plant);
		}		
		List<BaseInfo> base=landApplyServiceImpl.getBaseInfos(1);
		List<Land_Planting> planting=landApplyServiceImpl.getPlanting(bid);
		List<LandLayout> layout=landApplyServiceImpl.getLandLayout(bid);
		List<Land_base>  landInfo=landApplyServiceImpl.getLand_base(bid);
		map.addAttribute("landInfo", landInfo);
		map.addAttribute("base", base);
		map.addAttribute("planting", planting);
    	map.addAttribute("layout", layout);
    	map.addAttribute("flag1", bid);
    	return "mainRent";
	}
	
	
	@RequestMapping("/getInfo.do")	
	public String getInfo(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws IOException
	{			
		int lid = Integer.valueOf(request.getParameter("lid"));
		List<Land_base> li=landApplyServiceImpl.getLand_baseView(lid);
		JSONArray json = JSONArray.fromObject(li);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(json.toString());
		return null;
		
	}
	
	@RequestMapping("/getPlanting.do")	
	public String getPlanting(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws IOException
	{		
		return null;
	}
	
	@RequestMapping("/getLayout.do")	
	public String getLayout(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws IOException
	{
		int bid = Integer.valueOf(request.getParameter("bid"));		
		List<LandLayout> layout=landApplyServiceImpl.getLandLayout(bid);			
		JSONArray json = JSONArray.fromObject(layout);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(json.toString());
		return null;
	}
	
	//土地布局查询
	@RequestMapping("/layout.do")
	public String selectLandLayout(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//土地信息查询
	@RequestMapping("/landInfo.do")
	public String selectLandInfo(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//月份空闲查询
	@RequestMapping("/timeSpare.do")
	public String timeSpare(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	
	//获取申请信息(点击租赁按钮，获取教师+土地+个人信息)
	@RequestMapping("/AllApplyInfo.do")
	public String getAllApplyInfo(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//提交申请
	@RequestMapping("/submitApply.do")
	public String submitApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//修改申请
	@RequestMapping("/updateApply.do")
	public String updateApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//查看申请
	@RequestMapping("/checkApply.do")
	public String checkApply(ModelMap map)
	{
		return null;		
	}
	
	//取消申请
	@RequestMapping("/cancelApply.do")
	public String cancelApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
}
