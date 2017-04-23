package com.base.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.Admin;
import com.base.po.AdminFunction;
import com.base.serviceImpl.AdminManageServiceImpl;
import com.base.utils.CookieUtils;


@Controller("adminController")
public class AdminController {

	    @Autowired
	    private AdminManageServiceImpl adminManageServiceImpl;
	
	    //获取所有角色的权限功能值
		@RequestMapping("jsp/getAdminFunction.do")
		public String getAdminFunction(HttpServletRequest request, ModelMap map,
				HttpServletResponse response)
		{
			try {
				
				List<AdminFunction> adminFunctionList = adminManageServiceImpl.getAdminFunctionInfos();
				response.setContentType("text/html;charset=UTF-8");
				List<Admin> adminList = adminManageServiceImpl.getAdminInfos();
				
				JSONObject getObj = new JSONObject();
				getObj.put("sf", adminList);
				getObj.put("qxm", adminFunctionList);	
				response.getWriter().print(getObj.toString());
				//response.getWriter().print("['sf':[{id:1,name:'系统管理员',upow:3},{id:2,name:'普通教师',upow:7},{id:3,name:'租赁业务专员',upow:31},{id:4,name:'实习业务专员',upow:63}],'qxm':[{qid:11,value:1,pname:'土地租赁审批'},{qid:12,value:2,pname:'实习审批'},{qid:13,value:4,pname:'实习基地审批'},{qid:21,value:8,pname:'基地维护审批'},{qid:22,value:16,pname:'土地租赁维护'},{qid:31,value:32,pname:'租赁分析'}]]");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;		
		}	
		
		//设置所有角色的权限功能值
		@RequestMapping("jsp/setAdminFunction.do")
		public String setAdminFunction(HttpServletRequest request, ModelMap map,
						HttpServletResponse response)
		{
			String values = request.getParameter("data");		
			String insertSql = "insert into Admin(id,upow,name) values"+values.trim()+" on duplicate key update upow=values(upow),name=values(name)";
			//System.out.println(insertSql);
			adminManageServiceImpl.setAdminFunction(insertSql);
			
			//获取当前用户的权限值，更新cookie值
			String userid = CookieUtils.getCookieUsername(request, response);
			System.out.println(userid);
			long adminValue = adminManageServiceImpl.getAdminValue(userid);
			CookieUtils.addCookie("adminValue", String.valueOf(adminValue),response);
			
			//更改页面的权限值
			Properties prop = new Properties();
			try {
				prop.load(request.getSession().getServletContext().getResourceAsStream("/WEB-INF/admin.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String pagename[]={"rent-approve.jsp","repairApprove.jsp","baseCheck.jsp","notification.do","land_modle.jsp","baseMaintain.jsp","fieldRent_maintain.jsp","practicePlanMaintain.jsp","mangeruser.jsp","system_power.jsp","start.jsp","Repairmanage.jsp","statisticData.jsp"};
			int pageValue[]=new int[pagename.length];
			HttpSession session=request.getSession();				
			for(int i=0;i<pagename.length;i++){			
				  long a = (long)Math.pow(2,Integer.valueOf(prop.getProperty(pagename[i])));
				pageValue[i]=(int) (a&adminValue);
			}
			session.setAttribute("visitRight", pageValue);
		
			return null;
		}
	
}
