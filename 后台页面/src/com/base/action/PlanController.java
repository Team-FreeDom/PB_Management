package com.base.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import com.base.po.AllPlan;
import com.base.po.ApplyDept;
import com.base.po.BaseInfo;
import com.base.po.Classcourse;
import com.base.po.Majoraim;
import com.base.po.PlanList;
import com.base.po.PracticeCollection;
import com.base.po.StartDate;
import com.base.po.UserInfo;
import com.base.po.basetype;
import com.base.service.PlanMaintainService;
import com.base.service.PlanService;
import com.base.service.baseApplyService;
import com.base.utils.CookieUtils;
import com.base.utils.ExcelReport;
import com.base.utils.WeekTransformToTime;

//实习申请的控制层
@Controller("planController")
@RequestMapping("/jsp")
public class PlanController implements ServletContextAware{
    @Autowired
    private PlanService planservice;
    @Autowired
    private baseApplyService baseapplyservice;
    @Autowired
	private PlanMaintainService planMaintainService;
    private ServletContext application;  
    @Override  
    public void setServletContext(ServletContext arg0) {  
        this.application = arg0;  
    }  
  
    //检测学年学期和数据条数
    @RequestMapping("/Checkinfo.do")
    public String Checkinfo(HttpServletRequest request,
	    HttpServletResponse response) {
	String information="0";	
	String userid = CookieUtils.getUserid(request);	
	
	if(application.getAttribute("map_0")==null){
		List<StartDate> list1=planMaintainService.getStartDate();
		WeekTransformToTime.getLatestStartTime(application, list1);		
	}
	String semester=WeekTransformToTime.getThisSemester(application);
	if(semester==null){
	    information="对不起此时间段没有数据";	    
	}
	int record=planservice.checkinfo(userid,semester);
	if(record==0&&semester!=null){
	    information="对不起管理员还没有导入此学年学期的数据";	    
	}	
	JSONObject getObj = new JSONObject();
	getObj.put("msg", information);	
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
    
    // 显示用户所属学院的所有实习计划
    @RequestMapping("/displayThisCollegePlan.do")
    public String displayThisCollegePlan(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取用户登录的id
	String userid = CookieUtils.getUserid(request);
	System.out.println("能不能进来了======");
	String searchValue = request.getParameter("search[value]");
	if (searchValue.equals("")) {
	    searchValue = null;
	}
	
	//获取学年学期
	String semester=WeekTransformToTime.getThisSemester(application);
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
	// String orderDir = request.getParameter("order[0][dir]");//
	// 排序的顺序asc or
	String orderDir = "desc"; // // desc
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	int recordsTotal = 0;
	List<AllPlan> list = new ArrayList<AllPlan>();
	PlanList pl = null;
	pl = planservice.getThisCollegePlan(userid, pageindex, size, order,
		orderDir, searchValue,semester);
	list = pl.getData();
	recordsTotal = pl.getRecordsTotal();
	JSONObject getObj = new JSONObject();
	getObj.put("draw", draw);
	getObj.put("recordsFiltered", recordsTotal);
	getObj.put("recordsTotal", recordsTotal);
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

    // 获取弹出框里的数据
    @RequestMapping("/getplandata.do")
    public String getplandata(HttpServletRequest request,
	    HttpServletResponse response) {
	// 前台传过来的课程表id
	int id = Integer.valueOf(request.getParameter("mid"));
	List<Classcourse> list = null;
	list = planservice.plandata(id);
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

    // 获取导出的班级安排数据
    @RequestMapping("/getExportplandata.do")
    public String getExportplandata(HttpServletRequest request,
	    HttpServletResponse response) {
    // 获取用户登录的id
    String userid = CookieUtils.getUserid(request);
	String finishCondition=request.getParameter("finishCondition");
	String semester=WeekTransformToTime.getThisSemester(application);
	List<PracticeCollection> list = planservice.plandata_export(userid, finishCondition, semester);	
	if (CollectionUtils.isNotEmpty(list)) {			
		String path = ExcelReport.getWebRootUrl(request,"/upload/"); 
		String fullFileName = path + "/PracticeApplyInfo.xlsx";
		ExcelReport export = new ExcelReport();
		export.exportPracticePlanInfo(list, fullFileName);
		String filename = "湖南农业大学实习信息表.xlsx";

		//
		response.setContentType("application/octet-stream;charset=UTF-8");
		try {
			response.setContentType("application/octet-stream");
			boolean isMSIE = ExcelReport.isMSBrowser(request);
			if (isMSIE) {
				filename = URLEncoder.encode(filename, "UTF-8");
			} else {
				filename = new String(filename.getBytes("UTF-8"),
						"ISO-8859-1");
			}
			response.setHeader("Content-disposition",
					"attachment;filename=\"" + filename + "\"");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(fullFileName);
			out = response.getOutputStream();
			int b = 0;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
			out.close();

		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	return "practiapply";
    }
    
    
    // 保存对实习计划的修改
    @RequestMapping("/savePlanModify.do")
    public String savePlanModify(HttpServletRequest request,
	    HttpServletResponse response) {

	// 获取课程表记录编号
	int id = Integer.valueOf(request.getParameter("courseID"));
	//System.out.println(id + "=====haha");

	String plandata = request.getParameter("str");
	System.out.println(plandata + "liiiiiii");
	planservice.savePlanModify(id, plandata);
	JSONObject getObj = new JSONObject();
	getObj.put("msg", "此申请处理成功");
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 功能:1.专业所对应的培训目的(通过参数课程代码)2.获取基地类型
    @SuppressWarnings("rawtypes")
    @RequestMapping("/getPlanAim.do")
    public String getPlanAim(HttpServletRequest request,
	    HttpServletResponse response) {
	
    // 获取基地类型
    List<basetype> list2 = baseapplyservice.getBasetype();
	// 获取课程代码
	String cid = request.getParameter("mid");
	
	// 获取获取专业所对应的培训目的
	List<Majoraim> list3 = planservice.getPlanAim(cid);
	try {
	    List list4 = new ArrayList();	    
	    list4.add(list3);
	    list4.add(list2);
	    JSONArray json = JSONArray.fromObject(list4);
	    response.setContentType("text/html;charset=UTF-8");
	    response.getWriter().print(json.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
    
    //1.专业所对应的培训目的(通过参数课程代码)2.获取基地类型3.通过基地类型名称获取基地名字
    @RequestMapping("/getBasenameOfType.do")
    public String getBasenameOfType(HttpServletRequest request,
    	    HttpServletResponse response) {
    	
    	String typename=request.getParameter("typename");    	
    	 // 获取基地类型
        List<basetype> list1 = baseapplyservice.getBasetype();
       //根据基地类型获取基地名字
    	List<String> list2 = planservice.getProperBase(typename);
    	// 获取教师编号
    	String cid = request.getParameter("mid");
    	
    	// 获取获取专业所对应的培训目的
    	List<Majoraim> list3 = planservice.getPlanAim(cid);
    	try {
    		 List list4 = new ArrayList();	    
    		 list4.add(list1);
    		 list4.add(list2);
    		 list4.add(list3);
    	    JSONArray json = JSONArray.fromObject(list4);
    	    response.setContentType("text/html;charset=UTF-8");
    	    response.getWriter().print(json.toString());
    	} catch (IOException e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	}
    	return null;
    }
    
    //根据基地类型获取基地名字
    @RequestMapping("/getBasenameOneOfType.do")
    public String getBasenameOneOfType(HttpServletRequest request,
    	    HttpServletResponse response) {
    String typename=request.getParameter("typename");  	
   
      //根据基地类型获取基地名字
   	List<String> list= planservice.getProperBase(typename);
   	try {   		
   	    JSONArray json = JSONArray.fromObject(list);
   	    response.setContentType("text/html;charset=UTF-8");
   	    response.getWriter().print(json.toString());
   	} catch (IOException e) {
   	    // TODO Auto-generated catch block
   	    e.printStackTrace();
   	}
   	return null;
    }

    // 获取所有学院
    @RequestMapping("/getCollege.do")
    public String getCollege(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取学院
	List<ApplyDept> list = baseapplyservice.getDept(1);
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

    // 获取学院所对应的教师
    @RequestMapping("/getCollege_Teacher.do")
    public String getCollege_Teacher(HttpServletRequest request,
	    HttpServletResponse response) {

	// 获取前台传过来的学院
	String college = request.getParameter("college");
	// 获得userinfo表里的教师集合
	List<UserInfo> list = planservice.getCollege_Teacher(college);
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
    // 获取学院所对应的专业
    @RequestMapping("/getCollege_Major.do")
    public String getCollege_Major(HttpServletRequest request,
	    HttpServletResponse response) {

	// 获取前台传过来的学院
	String college = request.getParameter("college");
	// 获得userinfo表里的教师集合
	List<Map<String, String>> list = planservice.getCollege_Major(college);
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

    // 删除班级安排记录(单条)
    @RequestMapping("/deleteClassRecord.do")
    public String deleteClassRecord(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取每条班级安排记录的id
	int planid = Integer.parseInt(request.getParameter("planid"));
	planservice.deleteClassRecord(planid);
	JSONObject getObj = new JSONObject();
	getObj.put("msg", "此申请处理成功");
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 修改课程安排表(单条)李彩页面功能
    @RequestMapping("/alterRecord.do")
    public String alterRecord(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取需要修改打包的数据
	String plandata = request.getParameter("str");
	planservice.alterRecord(plandata);
	JSONObject getObj = new JSONObject();
	getObj.put("msg", "此申请处理成功");
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
