package com.base.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.ApplyDept;
import com.base.po.BaseInfo;
import com.base.po.LandRentInfo;
import com.base.po.RentList;
import com.base.po.RentMaintain;
import com.base.serviceImpl.LandApplyServiceImpl;
import com.base.serviceImpl.LandRentServiceImpl;
import com.base.utils.ExcelReport;

//使用模块的控制层
@Controller("landRentController")
@RequestMapping("/jsp")
public class LandRentController<E> {

	@Autowired
	private LandRentServiceImpl landRentServiceImpl;
    @Autowired
    private LandApplyServiceImpl landApplyServiceImpl;
	
	// 土地租赁记录
	@RequestMapping("/landRentInfo.do")
	public String selectBase(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		System.out.println("展示土地租赁信息");

		
		int length=Integer.valueOf(request.getParameter("length"));
		int start=Integer.valueOf(request.getParameter("start"));
		int draw=Integer.valueOf(request.getParameter("draw"));  //从客户端获得length(每页3长度)，start()起始页数，draw计数器
		
		/*int length=8;
		int start=0;
		int draw=1;*/
		
		System.out.println(length+"   "+start+" "+draw);
		int page=start/length+1; //当前页数
		
		RentList str = landRentServiceImpl.getLandRentInfos(null,
				null, null, page,length);
		
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

	@RequestMapping("/getSingleRentInfo.do")
	public String getSingleRentInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String lr_id = request.getParameter("lr_id");
		
		List<RentMaintain> list = landRentServiceImpl.getSingleRentInfo(lr_id,null);
		JSONArray json = JSONArray.fromObject(list);
		response.setContentType("text/html;charset=UTF-8");

		try {

			response.getWriter().print(json.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("/submitChoose.do")
	public String submitChoose(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
	
		String bname = request.getParameter("baseSh");
		String dept = request.getParameter("deptSh");		
		String planting = request.getParameter("contentSh");
		
		System.out.println(bname+" "+dept+" "+planting+"页数"+request.getParameter("length"));
		
		
		int length=Integer.valueOf(request.getParameter("length"));
		int start=Integer.valueOf(request.getParameter("start"));
		int draw=Integer.valueOf(request.getParameter("draw"));  //从客户端获得length(每页3长度)，start()起始页数，draw计数器
		int page=start/length+1; //当前页数
		
       
        
		RentList str = landRentServiceImpl.getLandRentInfos(bname,
				dept, planting, page,length);
		
		JSONObject getObj = new JSONObject();
		getObj.put("draw",draw);
		getObj.put("recordsFiltered",str.getRecordsTotal());		
		getObj.put("recordsTotal",str.getRecordsTotal());
		getObj.put("data",str.getData());	
		response.setContentType("text/html;charset=UTF-8");
	

		try {
			response.getWriter().print(getObj.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	@RequestMapping("/getExistRentInfo.do")
	public String getExistRentInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {	

		//获取存在的部门
		List<ApplyDept> existDept=landRentServiceImpl.getExistRentInfo();
		//获取存在的种植内容
		List<String> existplant=landRentServiceImpl.getExistPlant();
		//获取基地列表
		List<BaseInfo> allBaseList = landApplyServiceImpl.getBaseInfos();
		//获取所有的部门
		List<ApplyDept> allDeptlist=landApplyServiceImpl.getDepts();
		List list=new ArrayList<E>();
		
		list.add(allBaseList);		
		list.add(existDept);
		list.add(existplant);		
		list.add(allDeptlist);
				
		
		JSONArray json = JSONArray.fromObject(list);
		response.setContentType("text/html;charset=UTF-8");
		
		try {
			response.getWriter().print(json.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/deleteLandRentInfo.do")
	public String deleteLandRentInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		  		
		String[] check=request.getParameterValues("idname");		
		landRentServiceImpl.deleteRentInfo(check);
		
	   return "redirect:fieldRent_maintain.jsp";
	}
	
	
	@RequestMapping("/exportLandRentInfo.do")
	public String exportLandRentInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws Exception {
		
		String dept=request.getParameter("dept");	
		System.out.println(dept);
		List<RentMaintain> list =landRentServiceImpl.getSingleRentInfo(null,dept);
	   
		/*if(list.size()==0){
			 System.out.println("ee");
			return null;
		}*/
		System.out.println("hh");
		ExcelReport er=new ExcelReport();		
		
		
		
		String filename = "湖南农业大学土地租赁信息表";
		
		//String fullFileName = "E://landRentPreserveReport.xlsx";
		/*String path = request.getSession().getServletContext()
				.getRealPath("/upload/");*/
		String path = ExcelReport.getWebRootUrl(request,"/upload/");
		String fullFileName = path + "/landRentPreserveReport.xlsx";
		er.landRentPreserveReport(list,fullFileName);
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
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = response.getOutputStream();
		// 写文件
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		in.close();
		out.close();		
	
	   return null;
	}
	
	@RequestMapping("/landManageUpdate.do")
	public String landManageUpdate(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws Exception {	
		
		Double expense=0.0;
		String fee=request.getParameter("expense");
		int deptSelect=Integer.valueOf(request.getParameter("deptSelect"));		
		String planCareer=request.getParameter("planCareer");
		if(fee!=null&&!fee.equals("")){
			
		 expense=Double.valueOf(fee);
		
		}
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		int lr_id=Integer.valueOf(request.getParameter("lr_id"));
		System.out.println(expense);
		System.out.println(lr_id);
		landRentServiceImpl.landManageUpdate(deptSelect, planCareer, expense, startTime, endTime, lr_id);	
		
		return "redirect:fieldRent_maintain.jsp";
	}
	
	
	@RequestMapping("/landManageAdd.do")
	public String landManageAdd(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws Exception {	
		
		System.out.println("欢迎来到增加的");
		String lid=request.getParameter("addLid");
		String userid=request.getParameter("addUserid");
		int dept=Integer.valueOf(request.getParameter("addDept"));
		String planting=request.getParameter("addPlanting");
		String chargeDate=request.getParameter("addChargeDate");
		chargeDate=(chargeDate==""?null:chargeDate);
		
		String fee=request.getParameter("addExpense");
		
		String startTime=request.getParameter("addStartTime");
		startTime=(startTime==""?null:startTime);
		
		String endTime=request.getParameter("addEndTime");
		endTime=(endTime==""?null:endTime);
		
		LandRentInfo lr=null;
		Double expense;		
		if(fee!=null&&!fee.equals(""))
		{
			expense=Double.valueOf(fee);
			lr=new LandRentInfo(lid,startTime,endTime,planting,userid,expense,chargeDate,dept);
		}else{
			lr=new LandRentInfo(lid,startTime,endTime,planting,userid,chargeDate,dept);
		}	
	
		
		landRentServiceImpl.landManageAdd(lr);
		
		return "redirect:fieldRent_maintain.jsp";
	}
	
	
}
