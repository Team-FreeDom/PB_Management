package com.base.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.po.BaseInfo;
import com.base.po.LandApply_view;
import com.base.po.LandInfo;
import com.base.po.LandLayout;
import com.base.po.Land_Planting;
import com.base.po.Land_base;
import com.base.po.TemperateSave_View;
import com.base.serviceImpl.LandApplyServiceImpl;
import com.base.utils.CookieUtils;

//����ģ��Ŀ��Ʋ�
@Controller("landApplyController")
@RequestMapping("/jsp")
public class LandApplyController {
	
	@Autowired
	private LandApplyServiceImpl landApplyServiceImpl;

	//���ز�ѯ
	@RequestMapping("/baseInfo.do")
	public String selectBase(ModelMap map)
	{
		return null;		
	}
	
	//������������ҳ��
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
	
	//���ز��ֲ�ѯ
	@RequestMapping("/layout.do")
	public String selectLandLayout(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//������Ϣ��ѯ
	@RequestMapping("/landInfo.do")
	public String selectLandInfo(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//�·ݿ��в�ѯ
	@RequestMapping("/timeSpare.do")
	public String timeSpare(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	
	//��ȡ������Ϣ(������ް�ť����ȡ��ʦ+����+������Ϣ)
	@RequestMapping("/AllApplyInfo.do")
	public String getAllApplyInfo(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//�ύ����
	@RequestMapping("/submitApply.do")
	public String submitApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//�޸�����
	@RequestMapping("/updateApply.do")
	public String updateApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//�鿴����
	@RequestMapping("/checkApply.do")
	public String checkApply(ModelMap map)
	{
		return null;		
	}
	
	//ȡ������
	@RequestMapping("/cancelApply.do")
	public String cancelApply(HttpServletRequest request,ModelMap map)
	{
		return null;		
	}
	
	//��ȡ�û����˵������¼
	@RequestMapping("/selfApply.do")
	public String selfApply(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{
		System.out.println("hello");
		String applicantId="201440509";
		List<LandApply_view> list=landApplyServiceImpl.getselfApply(applicantId);
		for(LandApply_view lv:list)
		{
			System.out.println(lv.getDescp());
		}
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
	
	@RequestMapping("/mySelfandStateApply1.do")
	public String mySelfandStateApply1(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{
		String applicantId="201440509";
		List<LandApply_view> list=null;
		String str=request.getParameter("status");
		int status;
		if(str==null)
		{
		   status=2;
		}else{
			status=Integer.valueOf(str);
		}
		list=landApplyServiceImpl.getselfApply(applicantId,status);
		
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
	
	@RequestMapping("/mySelfandStateApply2.do")
	public String mySelfandStateApply2(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{
		String applicantId="201440509";
		List<LandApply_view> list=null;		
		int status=1;
		
		list=landApplyServiceImpl.getselfApply(applicantId,status);
		
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
	
	@RequestMapping("/myRentdetail.do")
	public String myRentdetail(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{
		System.out.println(request.getParameter("����myRentdetail.do"));
		int la_id=Integer.valueOf(request.getParameter("la_id"));
		
		List<LandApply_view> list=null;			
		
		list=landApplyServiceImpl.myRentdetail(la_id);
		
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
	
	@RequestMapping("/myRentEdit.do")
	public String myRentEdit(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{		
		int la_id=Integer.valueOf(request.getParameter("la_id"));
		
		
		List<TemperateSave_View> list=new ArrayList<TemperateSave_View>();			
		
		list.add(landApplyServiceImpl.getTs(la_id));
		
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
	
	@RequestMapping("/myRentFont.do")
	public String myRentFont(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{
		
		String applicantId="201440509";
		List list=null;			
		list=landApplyServiceImpl.myRentFont1(applicantId);
		//list=landApplyServiceImpl.myRentFont(applicantId);
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
	
	@RequestMapping("/myFameCancel1.do")
	public String myFameCancel1(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{		
		int la_id=Integer.valueOf(request.getParameter("la_id"));
		boolean flag=false;
		try{
			
		    landApplyServiceImpl.myFameCancel1(la_id);
		    flag=true;
		    String str="[{\"flag\":"+flag+"}]";
		    JSONArray json = JSONArray.fromObject(str);
		    
		    response.getWriter().print(json.toString());
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		return null;
		
	}
	
	@RequestMapping("/myFrameDel1.do")
	public String myFrameDel1(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{		
		int la_id=Integer.valueOf(request.getParameter("la_id"));
		boolean flag=false;
		try{
			
		   landApplyServiceImpl.myFrameDel1(la_id);
		    flag=true;
		    String str="[{\"flag\":"+flag+"}]";
		    JSONArray json = JSONArray.fromObject(str);
		    
		    response.getWriter().print(json.toString());
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		return null;
		
	}
	
	
	@RequestMapping("/myFrameSumbit.do")
	public String myFrameSumbit(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{		
		int la_id=Integer.valueOf(request.getParameter("la_id"));
		boolean flag=false;
		try{
			
		   landApplyServiceImpl.myFrameSubmit(la_id);
		    flag=true;
		    String str="[{\"flag\":"+flag+"}]";
		    JSONArray json = JSONArray.fromObject(str);
		    
		    response.getWriter().print(json.toString());
		}catch(Exception e){
			System.out.println(e);
		}		
		
		return null;		
	}
	
	@RequestMapping("/exportFile")
	public String exportFile(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws IOException
	{
		String fileName=request.getParameter("fileName");
		String filename=fileName.substring(0,fileName.lastIndexOf('.'));
		//String filetype=fileName.substring(fileName.lastIndexOf("."));
		//System.out.println(filename);
		
		//�ļ�����
		  response.setContentType(request.getServletContext().getMimeType(filename));  
	       
	        //��ȡĿ���ļ���ͨ��response��Ŀ���ļ�д���ͻ���  
	        //��ȡĿ���ļ��ľ���·��  
	        String fullFileName = request.getServletContext().getRealPath("file/" +fileName);    
	       
	        
	        //����Content-Disposition  
	        response.setHeader("Content-Disposition", "attachment;filename="+fileName); 
	        //��ȡ�ļ�  
	        InputStream in = new FileInputStream(fullFileName);  
	        OutputStream out = response.getOutputStream();  
	          
	        //д�ļ�  
	        int b;  
	        while((b=in.read())!= -1)  
	        {  
	            out.write(b); 	        	
	        }  
	          
	        in.close();  
	       out.close();      
		
		return null;
	}
	
	@RequestMapping("/unionSelect.do")
	public String unionSelect(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws IOException
	{
		System.out.println("��������");
		String applicantId="201440509";
		
		String bname=request.getParameter("bname");
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		int lid;
		if(request.getParameter("lid")==null||request.getParameter("lid").equals(""))
		{
			lid=0;
		}else{
			lid=Integer.valueOf(request.getParameter("lid"));
		}
		
		String desc=request.getParameter("desc");		
		
		List<LandApply_view> list=landApplyServiceImpl.getUnionInfo(applicantId, bname, startTime, endTime, lid, desc);
		
		JSONObject getObj = new JSONObject(); 
		getObj.put("data", list);
		map.addAttribute("flag", 2);
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
