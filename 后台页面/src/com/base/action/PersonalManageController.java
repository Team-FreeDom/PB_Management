package com.base.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.Admin;
import com.base.po.ApplyDept;
import com.base.po.CheckList;
import com.base.po.CheckView;
import com.base.po.Manger;
import com.base.po.MangerList;
import com.base.po.RentMaintain;
import com.base.po.UserInfo;
import com.base.service.UserInfoService;
import com.base.utils.ExcelReport;

@Controller("personalManageController")
@RequestMapping("/jsp")
public class PersonalManageController {
	@Autowired
	private UserInfoService userinfoservice;
		//用户管理人员信息
		@RequestMapping("/manger.do")
		public String manger(HttpServletRequest request,HttpServletResponse response,ModelMap map)
		{
			//获取用户过滤框里的字符
			 String searchValue = request.getParameter("search[value]");
			if(searchValue.equals("")){
				searchValue=null;
			}
			//获取当前页面的传输几条记录
			Integer size=Integer.parseInt(request.getParameter("length"));;
			//System.out.println(size+"出现了");
			//数据起始位置
			Integer startIndex = Integer.parseInt(request.getParameter("start"));
			Integer draw=Integer.parseInt(request.getParameter("draw"));	
			//通过计算求出当前页面为第几页
		   Integer pageindex=(startIndex/size+1);
		   MangerList str=null;
			str =userinfoservice.manger(pageindex,size,searchValue);	
			JSONObject getObj = new JSONObject();
			getObj.put("draw",draw);
			getObj.put("recordsFiltered",str.getRecordsTotal());		
			getObj.put("recordsTotal",str.getRecordsTotal());
			getObj.put("data",str.getData());	
			response.setContentType("text/html;charset=UTF-8");

			try {
				response.getWriter().print(getObj.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			return null;		
		}	
		//人员信息详情查看
		@RequestMapping("/Mangerdetail.do")
		public String Mangerdetail(HttpServletRequest request,HttpServletResponse response, ModelMap map) {
			String id =request.getParameter("iddetail");
			List<Manger> list = null;	
				list = userinfoservice.Mangerdetail(id);
			JSONArray json = JSONArray.fromObject(list);
			response.setContentType("text/html;charset=UTF-8");

			try {
				response.getWriter().print(json.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		//删除人员信息
		@RequestMapping("/deInfo.do")
		public String deleteInfo(HttpServletRequest request,HttpServletResponse response,ModelMap map)
		{
			String check[] = request.getParameterValues("idname");
			for (String string : check)
			{
				System.out.println(string);
			}
			String str = "";
			int i=0;
			if (check!= null)
			{
				for(String st:check)
				{
					if(i==check.length-1)
					{
					str+=st;
					}else{
						str+=st+",";
					}
					i++;
				}
			}
				
			    userinfoservice.deleteInfo(str);
				response.setContentType("text/html;charset=UTF-8");
				try
				{
					response.getWriter().print("删除成功");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;	
		}
		//修改人员信息
		@RequestMapping("/upInfo.do")
		public String upInfo(HttpServletRequest request,HttpServletResponse response,ModelMap map){
			String id=request.getParameter("EworkerId");//获取人员id
			String name=request.getParameter("Ename");//获取人员名字
			if(name.equals("")){
				name=null;
			}
			String sex=request.getParameter("Esex");//性别
			if(sex.equals("")){
				sex=null;
			}
			String birthdate=request.getParameter("demo");//获取人员出生日期
			if(birthdate.equals("")){
				birthdate=null;
			}
			String category=request.getParameter("Eworkerclass");//获取员工类别 
			if(category.equals("请选择")){
				category=null;
			}
			String attritube=request.getParameter("Estatus");//获取人员身份属性
			if(attritube.equals("请选择")){	
				attritube=null;
			}
			String dept=request.getParameter("Edivision");//获取人员部门
			if(dept.equals("")){
				dept=null;
			}
			String telephone=request.getParameter("Ephone");//获取人员电话
			if(telephone.equals("")){
				telephone=null;
			}
			String idcard=request.getParameter("IDnumber");//身份证
			if(idcard.equals("")){
				idcard=null;
			}
			String password=request.getParameter("Epassword");//密码
			if(password.equals("")){
				password=null;
			}
			userinfoservice.upInfo(id,name,sex,birthdate,category,attritube,dept,telephone,idcard,password);
			
			return "mangeruser";
		}
		//增加人员信息
		@RequestMapping("/addInfo.do")
		public String addInfo(HttpServletRequest request,HttpServletResponse response,ModelMap map){
			int flag=0;
			String id=request.getParameter("workerId");//获取人员id
			UserInfo Info=new UserInfo();
			//System.out.println(id);
			String name=request.getParameter("name");//获取人员名字
			if(name.equals("")){
				name=null;
			}
			//System.out.println(name);
			String sex=request.getParameter("sex");//性别
			if(sex.equals("")){
				sex=null;
			}
			String birthdate=request.getParameter("demo2");//获取人员出生日期
			if(birthdate.equals("")){
				birthdate=null;
			}
			//System.out.println(birthdate);
			String category=request.getParameter("Awkclass");//获取员工类别 
					//System.out.println(category);
			if(category.equals("")){
				category=null;
			}
			//System.out.println(category);
			String attritube=request.getParameter("Astatus");//获取人员身份属性
			if(attritube.equals("")){
				attritube=null;
			}
			//System.out.println(attritube);
			String dept=request.getParameter("Adivision");//获取人员部门
			if(dept.equals("")){
						dept=null;
			}
			//System.out.println(dept);
			String telephone=request.getParameter("phone");//获取人员电话
			if(telephone.equals("")){
				telephone=null;
			}
			String idcard=request.getParameter("IDnumber1");//身份证
			//		System.out.println(idcard);
			if(idcard.equals("")){
				idcard=null;
			}
			String password=request.getParameter("password");//密码
			if(password.equals("")){
				password=null;
			}
			 flag=userinfoservice.addInfo(id,name,sex,birthdate,category,attritube,dept,telephone,idcard,password);
			response.setContentType("text/html;charset=UTF-8");
			try
			{
				response.getWriter().print(flag);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		//获取身份属性和部门(attritube:员工身份属性)
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping("/getAllInfo.do")
		public String getAllInfo(HttpServletRequest request,HttpServletResponse response,ModelMap map)
		{  
			
			List<Admin> list = userinfoservice.getAttritube();//身份属性
			List<ApplyDept> la= userinfoservice.getDepts();//部门		
						
			try {		
				List list1=new ArrayList();
				list1.add(list);
				list1.add(la);
				JSONArray json = JSONArray.fromObject(list1);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(json.toString());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		//导出功能
		@RequestMapping("/exportPersonInfo.do")
		public String exportPersonInfo(HttpServletRequest request,HttpServletResponse response,ModelMap map){
			String dept=request.getParameter("dept");
			if(dept.equals("全部")){
				dept=null;
			}
			List<Manger> list = userinfoservice.exportPersonInfo(dept);
			ExcelReport export=new ExcelReport();
			export.exportPersonInfo(list);
			
			String filename = "湖南农业大学人员信息表";
			
			String fullFileName = "E://PersonInfo.xlsx";
			// 显示中文文件名
			response.setContentType("application/octet-stream;charset=utf-8");
			try {
				response.setHeader("Content-Disposition", "attachment;filename="
						+ new String(filename.getBytes(), "iso-8859-1") + ".xlsx");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			// 读取文件
			InputStream in =null;
			try
			{
				in = new FileInputStream(fullFileName);
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OutputStream out =null;
			try
			{
				out = response.getOutputStream();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 写文件
			int b;
			try
			{
				while ((b = in.read()) != -1) {
					out.write(b);
				}
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try
			{
				in.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try
			{
				out.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		   return null;
		}

}
