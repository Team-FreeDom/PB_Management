package com.base.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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

import com.base.po.LandRentInfo;
import com.base.po.RentList;
import com.base.po.RentMaintain;
import com.base.serviceImpl.LandRentServiceImpl;
import com.base.utils.ExcelReport;

//ʹ��ģ��Ŀ��Ʋ�
@Controller("landRentController")
@RequestMapping("/jsp")
public class LandRentController {

	@Autowired
	private LandRentServiceImpl landRentServiceImpl;
    
	
	// �������޼�¼
	@RequestMapping("/landRentInfo.do")
	public String selectBase(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		System.out.println("չʾ����������Ϣ");

		
		int length=Integer.valueOf(request.getParameter("length"));
		int start=Integer.valueOf(request.getParameter("start"));
		int draw=Integer.valueOf(request.getParameter("draw"));  //�ӿͻ��˻��length(ÿҳ3����)��start()��ʼҳ����draw������
		
		System.out.println(length+"   "+start+" "+draw);
		int page=start/length+1; //��ǰҳ��
		
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
		
		System.out.println(bname+" "+dept+" "+planting+"ҳ��"+request.getParameter("length"));
		
		
		int length=Integer.valueOf(request.getParameter("length"));
		int start=Integer.valueOf(request.getParameter("start"));
		int draw=Integer.valueOf(request.getParameter("draw"));  //�ӿͻ��˻��length(ÿҳ3����)��start()��ʼҳ����draw������
		int page=start/length+1; //��ǰҳ��
		
       
        
		RentList str = landRentServiceImpl.getLandRentInfos(bname,
				dept, planting, page,length);
		
		JSONObject getObj = new JSONObject();
		getObj.put("draw",draw);
		getObj.put("recordsFiltered",str.getRecordsTotal());		
		getObj.put("recordsTotal",str.getRecordsTotal());
		getObj.put("data",str.getData());	
		response.setContentType("text/html;charset=UTF-8");

		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());

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
		
	   return "fieldRent_maintain";
	}
	
	
	@RequestMapping("/exportLandRentInfo.do")
	public String exportLandRentInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws Exception {
		
		String dept=request.getParameter("dept");	
		System.out.println(dept);
		List<RentMaintain> list =landRentServiceImpl.getSingleRentInfo(null,dept);
	
		
		ExcelReport er=new ExcelReport();
		er.landRentPreserveReport(list);
		
		
		String filename = "����ũҵ��ѧ����������Ϣ��";
		
		String fullFileName = "E://landRentPreserveReport.xlsx";
		// ��ʾ�����ļ���
		response.setContentType("application/octet-stream;charset=utf-8");
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1") + ".xlsx");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		// ��ȡ�ļ�
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = response.getOutputStream();
		// д�ļ�
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
		
		
		int deptSelect=Integer.valueOf(request.getParameter("deptSelect"));		
		String planCareer=request.getParameter("planCareer");
		int expense=Integer.valueOf(request.getParameter("expense"));		
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		int lr_id=Integer.valueOf(request.getParameter("lr_id"));
		
		landRentServiceImpl.landManageUpdate(deptSelect, planCareer, expense, startTime, endTime, lr_id);	
		
		
		return "redirect:fieldRent_maintain.jsp";
	}
	
	
	@RequestMapping("/landManageAdd.do")
	public String landManageAdd(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws Exception {	
		
		System.out.println("��ӭ�������ӵ�");
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
		int expense;		
		if(fee!=null&&!fee.equals(""))
		{
			expense=Integer.valueOf(fee);
			lr=new LandRentInfo(lid,startTime,endTime,planting,userid,expense,chargeDate,dept);
		}else{
			lr=new LandRentInfo(lid,startTime,endTime,planting,userid,chargeDate,dept);
		}	
	
		
		landRentServiceImpl.landManageAdd(lr);
		
		return "redirect:fieldRent_maintain.jsp";
	}
	
	
}
