package com.base.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.base.contants.Contants;
import com.base.po.AllPlan;
import com.base.po.ApplyDept;
import com.base.po.BaseCheckList;
import com.base.po.ExportBase;
import com.base.po.PlanList;
import com.base.service.PlanMaintainService;
import com.base.service.baseApplyService;
import com.base.serviceImpl.AdminManageServiceImpl;
import com.base.serviceImpl.InputExcelServiceImpl;
import com.base.utils.ExcelReport;
import com.base.utils.WeekTransformToTime;

@Controller("planMaintainController")
@RequestMapping("/jsp")
public class PlanMaintainController {

	@Autowired
	private PlanMaintainService planMaintainService;
	@Autowired
	private AdminManageServiceImpl adminManageServiceImpl;
	@Autowired
	private baseApplyService baseapplyservice;

	// 显示实习计划数据
	@RequestMapping("/displayPlanInfo.do")
	public String displayPlanInfo(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取学年学期
		String semester = request.getParameter("semester");
		System.out.println("semester:"+semester);
		if (semester.equals("")) {
			semester = null;
		}
		
		// 获取用户过滤框里的字符
		String searchValue = request.getParameter("search[value]");
		if (searchValue.equals("")) {
			searchValue = null;
		}
		// 获取当前页面的传输几条记录
		Integer size = Integer.parseInt(request.getParameter("length"));
		// 数据起始位置
		Integer startIndex = Integer.parseInt(request.getParameter("start"));
		Integer draw = Integer.parseInt(request.getParameter("draw"));
		int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
		String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
		// // desc
		// 通过计算求出当前页面为第几页
		Integer pageindex = (startIndex / size + 1);
		
		int recordsTotal=0;
		List<AllPlan> list=new ArrayList<AllPlan>();
		if(semester!=null){		
			PlanList pl = null;
		    pl = planMaintainService.getPlanInfo(semester, pageindex, size, order,
				orderDir, searchValue);
		    list=pl.getData();
		recordsTotal=pl.getRecordsTotal();
		}
		JSONObject getObj = new JSONObject();
		getObj.put("draw", draw);
		getObj.put("recordsFiltered", recordsTotal);
		getObj.put("recordsTotal", recordsTotal);
		getObj.put("data", list);
		
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 导出实习计划安排表
	@RequestMapping("/exportPlanInfo.do")
	public String exportPlanInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String semester =request.getParameter("semester");
		String college = request.getParameter("college");
        if(college.equals("-1")){
        	college=null;
        }
		List<AllPlan> list = planMaintainService
				.getPlanTable(semester, college);

		if (CollectionUtils.isNotEmpty(list)) {
			/*String path = request.getSession().getServletContext()
					.getRealPath("/upload/");*/
			 String path = ExcelReport.getWebRootUrl(request,"/upload/"); 
			String fullFileName = path + "/PracticePlanInfo.xlsx";
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
		return "practicePlanMaintain";
	}
	
	// 判断是否存在该教师
		@RequestMapping("/checkIsThisUser.do")
		public String checkIsThisUser(HttpServletRequest request,
				HttpServletResponse response) {
			String id = request.getParameter("id");
			boolean flag=planMaintainService.checkIsUser(id);
			JSONObject getObj = new JSONObject();		
			getObj.put("flag", flag);			
			response.setContentType("text/html;charset=UTF-8");

			try {
				response.getWriter().print(getObj.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		// 判断是否存在该专业编号
		@RequestMapping("/checkIsThisMid.do")
		public String checkIsThisMid(HttpServletRequest request,
				HttpServletResponse response) {
			String aid = request.getParameter("aid");
			boolean flag=planMaintainService.checkIsMid(aid);
			JSONObject getObj = new JSONObject();		
			getObj.put("flag", flag);			
			response.setContentType("text/html;charset=UTF-8");

			try {
				response.getWriter().print(getObj.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	// 增加单条实习计划数据
	@RequestMapping("/addOnePlanInfo.do")
	public String addOnePlanInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String str = request.getParameter("str");
		System.out.println(str);
		str = "insert into baseweb.coursearrange(semester,college,cid,count,selectedCount,composition,coursename,weekClassify,credit,courseNature,courseCategory,tid,tname,"
				+ "Week,checkMethod,mid,major_oriented) values" + str;
		planMaintainService.addOnePlanInfo(str);
		JSONObject getObj = new JSONObject();		
		getObj.put("flag", true);			
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 获取所有学院,学期学年
	@RequestMapping("/getPlanMaintainInfo.do")
	public String getPlanMaintainInfo(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取所有学院
		List<ApplyDept> list1 = baseapplyservice.getDept(1);
		//获取学年
		List<String> list2=planMaintainService.getSemester();
		JSONObject getObj = new JSONObject();		
		getObj.put("college", list1);
		getObj.put("semester", list2);	
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// 获取开课学院
	@RequestMapping("/getReadyCollege.do")
	public String getReadyCollege(HttpServletRequest request,
			HttpServletResponse response) {
	    String semester=request.getParameter("semester");
		//获取开课学院
		List<String> list=planMaintainService.getPlanCollege(semester);
		JSONObject getObj = new JSONObject();		
		getObj.put("college", list);	
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 导入实习计划数据
	@RequestMapping("/importPlanInfo.do")
	public String importPlanInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String semesterfile=request.getParameter("semesterfile");
		System.out.println("semesterfile:"+semesterfile);
		// 上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 得到上传的文件
		MultipartFile mFile = multipartRequest.getFile("fileResource");
		// 得到上传服务器的路径
		String path = request.getSession().getServletContext()
				.getRealPath("/upload/");
		// 得到上传的文件的文件名
		String fileName = mFile.getOriginalFilename();
		String filename = "";
		if (!fileName.isEmpty()) {
			filename = new Date().getTime() + "$" + fileName;
			InputStream inputStream = mFile.getInputStream();
			byte[] b = new byte[1048576];
			int length = inputStream.read(b);
			path += "\\" + filename;
			// 文件流写到服务器端
			File tempFile = new File(path);
			FileOutputStream outputStream = new FileOutputStream(tempFile);
			outputStream.write(b, 0, length);

			filename = path; // 这是文件在服务器的绝对路径
			// 遍历文件中的数据：下面的list为读出的数据
			Workbook wb = (Workbook) InputExcelServiceImpl.getWb(path);
			List<List<String>> list = InputExcelServiceImpl.getPlanExcelRows(InputExcelServiceImpl.getSheet(wb, 0), -1, -1);
			// System.out.println("获得数据啦！！！！！！！！！");
			// ！！！！！！注意此处是遍历list，可在下面写插入数据库的语句

			if (CollectionUtils.isNotEmpty(list)) {
				// 实现批量插入
				String prefix = "INSERT IGNORE INTO baseweb.coursearrange(count,selectedCount,composition,"
						+ "college,cid,coursename,"
						+ "weekClassify,credit,courseNature,courseCategory,"
						+ "siteRepuire,tid,tname,"
						+ "technicalTitle,semester,week,checkMethod,mid,major_oriented,checkTime) "
						+ "values";

				StringBuffer suffix = new StringBuffer();
				// 遍历行（下面当i为0时可看到列头名）
				for (int i = 1; i < list.size(); i++) {
					String resultStr = "";
					// 循环每一个sheet中的每一行
					List<String> row = list.get(i);
					// System.out.println(row.size());
					System.out.println("row size is " + row.size());
					// 遍历列
					if (row != null && row.size() > 0) {
						String value = null;
						String termYear=null;
						int week=0;
						for (int j = 0; j < row.size(); j++) {
							value = row.get(j);
							if (j == 6) {
								if(value.length()==0){
									value="0";
								}else{
									value = value.substring(1);
								}
							
							} else if (j == 7) {
								if(value.length()==0){
									value="0";
								}
								
							}else if (j == 15) {								
								if(value.length()!=0){
									week=Integer.valueOf(value.substring(0,value.indexOf('-')));
								}
							}else if (j == 14) {
								
								value = value.substring(value.indexOf('(') + 1,
										value.lastIndexOf(')'));
								termYear=value;
							}
							resultStr = resultStr + '"' + value + '"' + ',';

						}
						
						resultStr=resultStr+WeekTransformToTime.weekTransformToTime(termYear, week);
						suffix.append("(" + resultStr + "),");

					}
				}
				// 构建完整sql
				String sql = prefix
						+ suffix.substring(0, suffix.length() - 1)
						+ " on duplicate key update count=values(count),selectedCount=values(selectedCount),composition=values(composition),college=values(college)"
						+ ",cid=values(cid),coursename=values(coursename),weekClassify=values(weekClassify),credit=values(credit)"
						+ ",courseNature=values(courseNature),courseCategory=values(courseCategory),siteRepuire=values(siteRepuire),tid=values(tid)"
						+ ",tname=values(tname),technicalTitle=values(technicalTitle),semester=values(semester),week=values(week),checkMethod=values(checkMethod),mid=values(mid),major_oriented=values(major_oriented),checkTime=values(checkTime)";
				System.out.println(sql);
				planMaintainService.deleteAndAdd(semesterfile, sql);
			}

			wb.close();
			outputStream.close();
			inputStream.close();
			tempFile.delete(); // 删除临时文件

		}
		return "redirect:practicePlanMaintain.jsp";
	}

	// 检测数据完整性
	@RequestMapping("/checkIsSave.do")
	public String checkIsSave(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取学年学期
		String semester = "2016-2017-1";// request.getParameter("semester");
		if (semester.equals("")) {
			semester = null;
		}
		// 获取用户过滤框里的字符
		String searchValue = request.getParameter("search[value]");
		if (searchValue.equals("")) {
			searchValue = null;
		}
		// 获取当前页面的传输几条记录
		Integer size = Integer.parseInt(request.getParameter("length"));
		// 数据起始位置
		Integer startIndex = Integer.parseInt(request.getParameter("start"));
		Integer draw = Integer.parseInt(request.getParameter("draw"));
		int order = Integer.valueOf(request.getParameter("order[0][column]"));// 排序的列号
		String orderDir = request.getParameter("order[0][dir]");// 排序的顺序asc or
		// // desc
		// 通过计算求出当前页面为第几页
		Integer pageindex = (startIndex / size + 1);
		PlanList pl = null;
		int status = 0;
		pl = planMaintainService.checkIsSave(semester, status, pageindex, size,
				order, orderDir, searchValue);

		JSONObject getObj = new JSONObject();
		getObj.put("draw", draw);
		getObj.put("recordsFiltered", pl.getRecordsTotal());
		getObj.put("recordsTotal", pl.getRecordsTotal());
		getObj.put("data", pl.getData());
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 删除实习计划数据
	@RequestMapping("/deletePlanInfo.do")
	public String deletePlanInfo(HttpServletRequest request,
			HttpServletResponse response) {

		String recordstr = request.getParameter("deletstr");
		planMaintainService.deletePlanInfo(recordstr);

		JSONObject getObj = new JSONObject();
		getObj.put("msg", "删除成功");
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 提醒教师完善实习计划
	@RequestMapping("/callAttention.do")
	public String callAttention(HttpServletRequest request,
			HttpServletResponse response) {
		String recordstr = request.getParameter("recordstr");
		planMaintainService.callAttention(recordstr);

		JSONObject getObj = new JSONObject();
		getObj.put("msg", "已发送信息提醒教师完善实习计划");
		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 存储学年第一周的日期
		@RequestMapping("/saveSemesterTime.do")
		public String saveSemesterTime(HttpServletRequest request,
				HttpServletResponse response) {
			String teamYearw = request.getParameter("teamYearw");
			String oneSemesterTime = request.getParameter("oneSemesterTime");			
			Contants.map.put(teamYearw, oneSemesterTime);			
			JSONObject getObj = new JSONObject();
			getObj.put("msg", true);
			response.setContentType("text/html;charset=UTF-8");

			try {
				response.getWriter().print(getObj.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
