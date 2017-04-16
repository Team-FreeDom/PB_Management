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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import com.base.po.AllPlan;
import com.base.po.PlanList;
import com.base.po.PracticeCollection;
import com.base.po.StatisticData;
import com.base.po.basetype;
import com.base.service.StatisticDataService;
import com.base.utils.CookieUtils;
import com.base.utils.ExcelReport;
import com.base.utils.WeekTransformToTime;

@Controller("statisticData")
@RequestMapping("/jsp")
public class StatisticDataController implements ServletContextAware {
    @Autowired
    private StatisticDataService statisticDataservice;
    private ServletContext application;

    @Override
    public void setServletContext(ServletContext arg0) {
	this.application = arg0;

    }

    // 显示所有实习计划(数据统计)
    @RequestMapping("/statisticData.do")
    public String statisticData(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取用户登录的id
	// String userid = CookieUtils.getUserid(request);
	// System.out.println("哈哈哈哈哈======");
	// 获取学年学期
	String semester = WeekTransformToTime.getThisSemester(application);
	System.out.println("semester:"+semester);
	if (semester==null||semester.equals("")) {
	    semester = null;
	}
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	// System.out.println(size + "lialiaiaiaiaia");
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
	pl = statisticDataservice.statisticData(pageindex, size, order,
		orderDir,semester);
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
    
    //导出数据
    @RequestMapping("/exportStatisticForm.do")
    public String exportStatisticForm(HttpServletRequest request,
    	    HttpServletResponse response) {
    	
    	String semester = WeekTransformToTime.getThisSemester(application);
    	if (semester==null||semester.equals("")) {
    	    semester = null;
    	}
    	String basetype = request.getParameter("baseCategory");// 获取基地类型
    	if (basetype.equals("")) {
    	    basetype = null;
    	}
    	String basename = request.getParameter("baseName");// 获取基地名字
    	if (basename.equals("")) {
    	    basename = null;
    	}
    	String grade = request.getParameter("gradeClass");// 获取年级
    	if (grade.equals("")) {
    	    grade = null;
    	}
    	String college = request.getParameter("college");// 获取学院
    	if (college.equals("")) {
    	    college = null;
    	}
    	String major = request.getParameter("major");// 获取专业
    	if (major.equals("")) {
    	    major = null;
    	}
    	String class1 = request.getParameter("className");// 获取班级
    	if (class1.equals("")) {
    	    class1 = null;
    	}
    	 List<PracticeCollection> list = statisticDataservice.getExportstatisticData(semester, basetype, basename, college, grade, major, class1);

 		if (CollectionUtils.isNotEmpty(list)) {			
 			String path = ExcelReport.getWebRootUrl(request,"/upload/"); 
 			String fullFileName = path + "/statisticData.xlsx";
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
 		return "statisticData";
    	
    }
    
    @RequestMapping("/getstatisticCount.do")
    public String getstatisticCount(HttpServletRequest request,
	    HttpServletResponse response) {
    	
    	String semester = WeekTransformToTime.getThisSemester(application);
    	if (semester==null||semester.equals("")) {
    	    semester = null;
    	}
    	List<String> list1=statisticDataservice.getstatisticCount(semester);
    	List<String> list2=statisticDataservice.getAllStatisticCount();
    	
    	JSONObject getObj = new JSONObject();
    	getObj.put("list1", list1); 
    	getObj.put("list2", list2);
    	response.setContentType("text/html;charset=UTF-8");

    	try {
    	    response.getWriter().print(getObj.toString());
    	} catch (IOException e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	}
    	return null;
    }  
  

    // (刷选)显示所有实习计划(数据统计)
    @RequestMapping("/statisticDataBrush.do")
    public String statisticDataBrush(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取用户登录的id
	// String userid = CookieUtils.getUserid(request);
	String semester = WeekTransformToTime.getThisSemester(application);
	if (semester==null||semester.equals("")) {
	    semester = null;
	}
	String basetype = request.getParameter("baseCategory");// 获取基地类型
	if (basetype.equals("")) {
	    basetype = null;
	}
	String basename = request.getParameter("baseName");// 获取基地名字
	if (basename.equals("")) {
	    basename = null;
	}
	String grade = request.getParameter("gradeClass");// 获取年级
	if (grade.equals("")) {
	    grade = null;
	}
	String college = request.getParameter("college");// 获取学院
	if (college.equals("")) {
	    college = null;
	}
	String major = request.getParameter("major");// 获取专业
	if (major.equals("")) {
	    major = null;
	}
	String class1 = request.getParameter("className");// 获取班级
	if (class1.equals("")) {
	    class1 = null;
	}
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
	pl = statisticDataservice.statisticDataBrush(semester, basetype,
		basename, college, grade, major, class1, pageindex, size,
		order, orderDir);
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

    // 基地利用率(数据统计)
    @RequestMapping("/BaseUseRatio.do")
    public String BaseUseRatio(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取学年学期
	String semester = WeekTransformToTime.getThisSemester(application);
	if (semester==null||semester.equals("")) {
	    semester = null;
	}
	List list = new ArrayList();

	StatisticData st = new StatisticData();
	st = statisticDataservice.BaseUseRatio(semester);
	list.add(st);
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

    // 基地利用率刷选(数据统计)
    @RequestMapping("/BaseUseRatioBrush.do")
    public String BaseUseRatioBrush(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取学年学期
	String semester = WeekTransformToTime.getThisSemester(application);
	if (semester==null||semester.equals("")) {
	    semester = null;
	}
	String basetype = request.getParameter("baseCategory2");// 获取基地类型
	if (basetype.equals("")) {
	    basetype = null;
	}
	String basename = request.getParameter("baseName2");// 获取基地名字
	if (basename.equals("")) {
	    basename = null;
	}
	String grade = request.getParameter("gradeClass2");// 获取年级
	if (grade.equals("")) {
	    grade = null;
	}
	String college = request.getParameter("college2");// 获取学院
	if (college.equals("")) {
	    college = null;
	}
	String major = request.getParameter("major2");// 获取专业
	if (major.equals("")) {
	    major = null;
	}
	String class1 = request.getParameter("className2");// 获取班级
	if (class1.equals("")) {
	    class1 = null;
	}
	String teacherName = request.getParameter("teacherName");
	if (teacherName.equals("全部") || teacherName.equals("")) {
	    teacherName = null;
	}
	List<StatisticData> list = new ArrayList<StatisticData>();

	StatisticData st = new StatisticData();
	// System.out.println(basetype+"  "+basename+"  "+grade+"  "+college+"  "+major+"  "+class1+"  "+teacherName+"  "+"彭心雨");
	st = statisticDataservice.BaseUseRatioBrush(semester, basetype,
		basename, grade, college, major, class1, teacherName);
	list.add(st);
	// System.out.println(st.getTypenum() + "李彩彩彩");
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

    // 得到基地类型和学院(第一个刷选)
    @RequestMapping("/statisticgetDept.do")
    public String getDept(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取学年学期
	String semester = WeekTransformToTime.getThisSemester(application);
	if (semester==null||semester.equals("")) {
	    semester = null;
	}

	List list = statisticDataservice.getDept(semester);
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

    // 得到基地名字(第一个刷选)
    @RequestMapping("/statisticgetName.do")
    public String statisticgetName(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取学年学期

	String semester = WeekTransformToTime.getThisSemester(application);
	if (semester==null||semester.equals("")) {
	    semester = null;
	}

	String basename = request.getParameter("baseCategory");
	if (basename.equals("")) {
	    basename = null;
	}
	List<Map<String, String>> list = statisticDataservice.getName(semester,
		basename);
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

    // 得到基地名字(第二个刷选)
    @RequestMapping("/statisticgetName2.do")
    public String statisticgetName2(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取学年学期

	String semester = WeekTransformToTime.getThisSemester(application);
	if (semester==null||semester.equals("")) {
	    semester = null;
	}

	String basename = request.getParameter("baseCategory2");
	if (basename.equals("")) {
	    basename = null;
	}
	List<Map<String, String>> list = statisticDataservice.getName(semester,
		basename);
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

    // 得到专业(第一个刷选)
    @RequestMapping("/statisticgetmajor.do")
    public String statisticgetmajor(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取学年学期

	String semester = WeekTransformToTime.getThisSemester(application);	
	if (semester==null||semester.equals("")) {
	    semester = null;
	}

	String college = request.getParameter("college");
	if (college.equals("")) {
	    college = null;
	}
	// System.out.println(college + "专业专业");
	List<Map<String, String>> list = statisticDataservice.getMajor(
		semester, college);
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

    // 得到专业(第二个刷选)
    @RequestMapping("/statisticgetmajor2.do")
    public String statisticgetmajor2(HttpServletRequest request,
	    HttpServletResponse response) {
	// 获取学年学期

	String semester = WeekTransformToTime.getThisSemester(application);
	if (semester==null||semester.equals("")) {
	    semester = null;
	}

	String college = request.getParameter("college2");
	if (college.equals("")) {
	    college = null;
	}
	// System.out.println(college + "专业专业");
	List<Map<String, String>> list = statisticDataservice.getMajor(
		semester, college);
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

}
