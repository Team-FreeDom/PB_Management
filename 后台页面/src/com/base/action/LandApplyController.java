package com.base.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.base.po.ApplyDept;
import com.base.po.ApplyList;
import com.base.po.BaseInfo;
import com.base.po.LandApply_view;
import com.base.po.LandInfo;
import com.base.po.LandLayout;
import com.base.po.Land_Planting;
import com.base.po.Land_base;
import com.base.po.Layout_InfoView;
import com.base.po.RentCollection;
import com.base.po.Startplan;
import com.base.po.TemperateSave_View;
import com.base.serviceImpl.LandApplyServiceImpl;
import com.base.utils.CookieUtils;
import com.base.utils.ExcelReport;

//申请模块的控制层
@Controller("landApplyController")
@RequestMapping("/jsp")
public class LandApplyController {

	@Autowired
	private LandApplyServiceImpl landApplyServiceImpl;

	// 基地查询
		@RequestMapping("/baseInfo.do")
		public String selectBase(HttpServletRequest request, ModelMap map,
				HttpServletResponse response) {

			List<BaseInfo> list = landApplyServiceImpl.getBaseInfos();

			response.setContentType("text/html;charset=UTF-8");

			try {
				JSONArray json = JSONArray.fromObject(list);
				response.getWriter().print(json.toString());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	
	// 基地+部门查询
	@RequestMapping("/getBase_deptInfo.do")
	public String selectBase_deptInfo(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		List<BaseInfo> list1 = landApplyServiceImpl.getBaseInfos();
		List<ApplyDept> list2= landApplyServiceImpl.getDepts();
		Startplan sp=landApplyServiceImpl.getStartPlan("zl");
		String rents="";
		String rente="";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		String str=dateFormat.format(Calendar.getInstance().getTime());
		int tag=0;
		if(sp!=null){
		rents= sp.getApply_start();
		rente=sp.getApply_end();				
		}
		int number1=0;
		int number2=0;
		
		try {
			date=dateFormat.parse(str);
			number1 = date.compareTo(dateFormat.parse(rents));
			number2=date.compareTo(dateFormat.parse(rente));			
			if(number1>=0&&number2<=0){
				tag=2;
			}else{
				tag=1;
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JSONObject getObj = new JSONObject();
		getObj.put("base",list1);
		getObj.put("xy",list2);
		getObj.put("stime",rents);
		getObj.put("etime",rente);
		getObj.put("tag", tag);
		
		response.setContentType("text/html;charset=UTF-8");

		try {
			
			response.getWriter().print(getObj.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@RequestMapping("/getInfo.do")
	public String getInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws IOException {
		String lid = request.getParameter("lid");
		List<Land_base> li = landApplyServiceImpl.getLand_baseView(lid);
		JSONArray json = JSONArray.fromObject(li);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(json.toString());
		return null;

	}

	// 租赁申请时，获取土地布局+土地基本信息+土地现租赁情况+土地租赁历史
	@RequestMapping("/getRentCollection.do")
	public String getRentCollection(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {

		int bid = Integer.valueOf(request.getParameter("bid"));
		List<RentCollection> list = landApplyServiceImpl.getRentCollection(bid);

		JSONArray json = JSONArray.fromObject(list);
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(json.toString());
			// response.getWriter().print(getObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 租赁申请时，提交租赁申请
	@RequestMapping("/submitLandApply.do")
	public String submitLandApply(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {

		String str = request.getParameter("str");
        String info_str=request.getParameter("info_str");
        String lidList=request.getParameter("lidList");
		String userid=request.getParameter("userid");       
       
        
		int flag=landApplyServiceImpl.submitLandApply(userid,lidList,str,info_str);
		
		
		
		response.setContentType("text/html;charset=UTF-8");

		String str1 =""+flag+'$'+"" ;
		
		try {
			response.getWriter().print(str1);
			// response.getWriter().print(getObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 获取用户个人的申请记录
	@RequestMapping("/selfApply.do")
	public String selfApply(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		
		String bname = request.getParameter("bname");		
		String desc = request.getParameter("desc");
		
		int length=Integer.valueOf(request.getParameter("length"));
		int start=Integer.valueOf(request.getParameter("start"));
		int draw=Integer.valueOf(request.getParameter("draw"));  //从客户端获得length(每页3长度)，start()起始页数，draw计数器
		
		
		int page=start/length+1; //当前页数
		
	    String applicantId = CookieUtils.getUserid(request);	
		
		
		ApplyList al=landApplyServiceImpl.getselfApply(applicantId, bname,desc, page, length);
		
		JSONObject getObj = new JSONObject();
		getObj.put("draw",draw);
		getObj.put("recordsFiltered",al.getRecordsTotal());		
		getObj.put("recordsTotal",al.getRecordsTotal());
		getObj.put("data",al.getData());	
		
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
	public String myRentdetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		
		int la_id = Integer.valueOf(request.getParameter("la_id"));

		List<LandApply_view> list = null;

		list = landApplyServiceImpl.myRentdetail(la_id);

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
	public String myRentEdit(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		int la_id=21;
		//int la_id = Integer.valueOf(request.getParameter("la_id"));

		List<TemperateSave_View> list = landApplyServiceImpl.getTs(la_id);		

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

	//获取用户当年的土地租赁（除了通过，不通过，失效）
	@RequestMapping("/myRentFont.do")
	public String myRentFont(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		
		int length=Integer.valueOf(request.getParameter("length"));
		int start=Integer.valueOf(request.getParameter("start"));
		int draw=Integer.valueOf(request.getParameter("draw"));  //从客户端获得length(每页3长度)，start()起始页数，draw计数器
			
		int page=start/length+1; //当前页数		
		String applicantId = CookieUtils.getUserid(request);
		
		ApplyList al = landApplyServiceImpl.myRentFont1(applicantId,page,length);		
		
		JSONObject getObj = new JSONObject();
		getObj.put("draw",draw);
		getObj.put("recordsFiltered",al.getRecordsTotal());		
		getObj.put("recordsTotal",al.getRecordsTotal());
		getObj.put("data",al.getData());	

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
	public String myFameCancel1(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		
		int la_id = Integer.valueOf(request.getParameter("la_id"));
		String info_str=request.getParameter("info_str");
		int tag = Integer.valueOf(request.getParameter("flag"));		
		
		try {

			int flag=landApplyServiceImpl.myFameCancel1(la_id,info_str,tag);		
			
			String str = "[{\"flag\":" + flag+"}]";
			JSONArray json = JSONArray.fromObject(str);

			response.getWriter().print(json.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;

	}

	@RequestMapping("/myFrameDel1.do")
	public String myFrameDel1(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		int la_id = Integer.valueOf(request.getParameter("la_id"));
		boolean flag = false;
		try {

			landApplyServiceImpl.myFrameDel1(la_id);
			flag = true;
			String str = "[{\"flag\":" + flag + "}]";
			JSONArray json = JSONArray.fromObject(str);

			response.getWriter().print(json.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;

	}

	@RequestMapping("/myFrameSumbit.do")
	public String myFrameSumbit(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		int la_id = Integer.valueOf(request.getParameter("la_id"));
		boolean flag = false;
		try {

			landApplyServiceImpl.myFrameSubmit(la_id);
			flag = true;
			String str = "[{\"flag\":" + flag + "}]";
			JSONArray json = JSONArray.fromObject(str);

			response.getWriter().print(json.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	@RequestMapping("/exportFile.do")
	public String exportFile(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws IOException {
		
		String fileName = null;
		int flag=Integer.valueOf(request.getParameter("flag"));
		if(flag==1){
			fileName="教学科研用地协议.pdf";
		}else if(flag==2){
			fileName="土地有偿使用协议.docx";
		}	
		
		String filename = fileName.substring(0, fileName.lastIndexOf('.'));
		String filetype=fileName.substring(fileName.lastIndexOf("."));
		// System.out.println(filename);

		// 文件下载
		response.setContentType(request.getServletContext().getMimeType(
				filename));

		// 读取目标文件，通过response将目标文件写到客户端
		// 获取目标文件的绝对路径
		/*String fullFileName = request.getServletContext().getRealPath(
				"file/" + fileName);*/
		String fullFileName = ExcelReport.getWebRootUrl(request,"/upload/")+ fileName;
		
		// 设置Content-Disposition
		/*response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);*/
		
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(filename.getBytes(), "iso-8859-1") + filetype);
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

	/*@RequestMapping("/unionSelect.do")
	public String unionSelect(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws IOException {
		System.out.println("调用了吗");
		String applicantId = "201440509";

		String bname = request.getParameter("bname");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String lid = request.getParameter("lid");

		System.out.println("我得到的lid是：" + lid);

		String desc = request.getParameter("desc");

		List<LandApply_view> list = landApplyServiceImpl.getUnionInfo(
				applicantId, bname, startTime, endTime, lid, desc);

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

	}*/

	@RequestMapping("/updateContent.do")
	public String updateContent(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws IOException {

		String lid = request.getParameter("lid");
		int dept = Integer.valueOf(request.getParameter("dept"));
		String planting = request.getParameter("planting");
		int la_id = Integer.valueOf(request.getParameter("hide"));
		String filename = "";

		System.out.println(lid + "  " + dept + "  " + planting + "  " + la_id);

		// 上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 得到上传的文件
		MultipartFile mFile = multipartRequest.getFile("fileResource");
		// 得到上传服务器的路径
		String path = request.getSession().getServletContext()
				.getRealPath("/infor/");
		// 得到上传的文件的文件名
		String fileName = mFile.getOriginalFilename();
		System.out.println(fileName);

		if (!fileName.isEmpty()) {
			filename = new Date().getTime() + "$" + fileName;
			InputStream inputStream = mFile.getInputStream();
			byte[] b = new byte[1048576];
			int length = inputStream.read(b);
			path += "\\" + filename;
			// 文件流写到服务器端
			FileOutputStream outputStream = new FileOutputStream(path);
			outputStream.write(b, 0, length);
			inputStream.close();
			outputStream.close();
			filename = "../infor/" + filename;
		}
		String path1 = request.getSession().getServletContext().getRealPath("");
		landApplyServiceImpl.updateContent(la_id, lid, dept, planting,
				filename, path1);
		return "redirect:myRent.jsp";

	}

	@RequestMapping("/getDept.do")
	public String getDept(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws IOException {

		List<ApplyDept> list = landApplyServiceImpl.getDepts();

		response.setContentType("text/html;charset=UTF-8");

		try {
			JSONArray json = JSONArray.fromObject(list);
			response.getWriter().print(json.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("/getLayout_Info.do")
	public String getLayout_Info(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {

		List<Layout_InfoView> list = landApplyServiceImpl.getLayout();

		/*
		 * JSONObject getObj = new JSONObject(); getObj.put("data", list);
		 */
		JSONArray json = JSONArray.fromObject(list);
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(json.toString());
			// response.getWriter().print(getObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@RequestMapping("/getDifferLayout.do")
	public String getDifferLayout(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {

		int bid = Integer.valueOf(request.getParameter("bid"));
		System.out.println(bid);
		List<Layout_InfoView> list = landApplyServiceImpl.getDifferLayout(bid);

		JSONArray json = JSONArray.fromObject(list);
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(json.toString());
			// response.getWriter().print(getObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	
	@RequestMapping("/uploadImage.do")
	@ResponseBody
	public String uploading(HttpServletRequest request,HttpServletResponse response, ModelMap map) 
	{
		System.out.println("hello");	
		//上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 得到上传的文件
		MultipartFile mFile = multipartRequest.getFile("imgfile");   //有问题		
		String filename = "";
		if (!mFile.isEmpty()){
			// 得到上传服务器的路径			
			String path = ExcelReport.getWebRootUrl(request,"/landImage/");
			// 得到上传的文件的文件名
			String fileName = mFile.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			filename = new Date().getTime() + fileType;			
			InputStream inputStream;
			try {
				inputStream = mFile.getInputStream();
				byte[] b = new byte[1048576];
				int length = inputStream.read(b);
				path += "/" + filename;
				// 文件流写到服务器端
				FileOutputStream outputStream = new FileOutputStream(path);
				outputStream.write(b, 0, length);
				inputStream.close();
				outputStream.close();
				filename = "../landImage/" + filename;
				System.out.println(filename);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		JSONObject getObj = new JSONObject();
		getObj.put("imgurl",filename);
		getObj.put("msg","success");
		return getObj.toString();
	}

	
	@RequestMapping("/updateLayout_Info.do")
	public String updateLayout_Info(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws IOException {

		String str= request.getParameter("layinfo");
		int bid=Integer.valueOf(request.getParameter("bid"));
		int tag=Integer.valueOf(request.getParameter("tag"));
		String path1 = request.getSession().getServletContext().getRealPath("");	 
		
		System.out.println(str);
		List<Layout_InfoView> list = new ArrayList<Layout_InfoView>();
	    Layout_InfoView view = null;
		String layoutStr = "";
		String landinfoStr = "";       
		if (tag == 0) {
			System.out.println("清空");
		    landApplyServiceImpl.delLayout_info(bid,path1);
		} else {
			System.out.println("更新：controller层");
			JSONArray obj = JSONArray.fromObject(str);
			for (int i = 0; i < obj.size(); i++) {

				JSONObject temp = obj.getJSONObject(i);		
				
				landinfoStr += "('" + temp.getString("id") + "',"  //拼装土地信息
						+ temp.getInt("bid") + ","
						+ Integer.valueOf(temp.getString("Afford")) + ","
						+ temp.getInt("buildingArea") + ","
						+ temp.getInt("landArea") + ",'"
						+ temp.getString("lname") + "','"
						+ temp.getString("plantingContent") +"','"
						+ temp.getString("college") +"','"
						+ temp.getString("img")+"'";

				layoutStr += "(" + temp.getInt("bid") + ","    //拼装土地布局信息
						+ temp.getInt("height") + "," + temp.getInt("width")
						+ "," + temp.getInt("x") + "," + temp.getInt("y")
						+ ",'" + temp.getString("id") + "'";
				
				if(i==obj.size()-1)
				{
					landinfoStr+=")";
					layoutStr+=")";
				}else{
					landinfoStr+="),";
					layoutStr+="),";
				}			

			}
			landApplyServiceImpl.delLayout_info(bid,path1);
			landApplyServiceImpl.updateLayInfo(landinfoStr, layoutStr);
		}

		String str1 = "[{\"flag\":" + true + "}]";
		JSONArray json = JSONArray.fromObject(str1);

		response.getWriter().print(json.toString());

		return null;

	}

	//修改或更新租赁开始时间
	@RequestMapping("/updateLandApplyDate.do")
	public String updateLandApplyDate(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String planstime = request.getParameter("planstime");
		String planetime = request.getParameter("planetime");
		
		String rentstime = request.getParameter("rentstime");
		String rentetime = request.getParameter("rentetime");
		
		Long limitday=Long.valueOf(request.getParameter("limitday"));		
		Startplan sp =new Startplan("zl","土地租赁",planstime,planetime,rentstime,rentetime,limitday);		
		landApplyServiceImpl.updateLandApplyDate(sp);
		
		JSONObject getObj = new JSONObject();
		getObj.put("flag",true);
		
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(getObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	//获取租赁开始时间
	@RequestMapping("/getLandApplyDate.do")
	public String getLandApplyDate(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String plans="";
		String plane="";
		String rents="";
		String rente="";
		String limitday="";
		Startplan sp = landApplyServiceImpl.getStartPlan("zl");
		
		if(sp!=null){
		plans=sp.getApply_start();
		plane=sp.getApply_end();
		rents=sp.getRent_start();
		rente=sp.getRent_end();
		limitday=String.valueOf(sp.getLimitday());
		}
		JSONObject getObj = new JSONObject();
		getObj.put("planstime",plans);
		getObj.put("planetime",plane);
		getObj.put("rentstime",rents);
		getObj.put("rentetime",rente);
		getObj.put("limitday",limitday);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(getObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/endLandApply.do")
	public String endLandApply(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		
		landApplyServiceImpl.endStartPlan();
		JSONObject getObj = new JSONObject();
		getObj.put("flag",true);
		
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
