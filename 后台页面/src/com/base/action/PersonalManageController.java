package com.base.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.base.po.Admin;
import com.base.po.ApplyDept;
import com.base.po.CheckList;
import com.base.po.CheckView;
import com.base.po.Manger;
import com.base.po.MangerList;
import com.base.po.RentMaintain;
import com.base.po.UserInfo;
import com.base.service.UserInfoService;
import com.base.serviceImpl.AdminManageServiceImpl;
import com.base.serviceImpl.InputExcelServiceImpl;
import com.base.utils.ExcelReport;

@Controller("personalManageController")
@RequestMapping("/jsp")
public class PersonalManageController {
    @Autowired
    private UserInfoService userinfoservice;
    @Autowired
    private AdminManageServiceImpl adminManageServiceImpl;

    // 获取用户管理人员信息
    @RequestMapping("/manger.do")
    public String manger(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	// 获取用户过滤框里的字符
	String searchValue = request.getParameter("search[value]");
	if (searchValue.equals("")) {
	    searchValue = null;
	}
	// 获取当前页面的传输几条记录
	Integer size = Integer.parseInt(request.getParameter("length"));
	;
	// System.out.println(size+"出现了");
	// 数据起始位置
	Integer startIndex = Integer.parseInt(request.getParameter("start"));
	Integer draw = Integer.parseInt(request.getParameter("draw"));
	// 通过计算求出当前页面为第几页
	Integer pageindex = (startIndex / size + 1);
	MangerList str = null;
	str = userinfoservice.manger(pageindex, size, searchValue);
	JSONObject getObj = new JSONObject();
	getObj.put("draw", draw);
	getObj.put("recordsFiltered", str.getRecordsTotal());
	getObj.put("recordsTotal", str.getRecordsTotal());
	getObj.put("data", str.getData());
	response.setContentType("text/html;charset=UTF-8");

	try {
	    response.getWriter().print(getObj.toString());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 人员信息详情查看
    @RequestMapping("/Mangerdetail.do")
    public String Mangerdetail(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String id = request.getParameter("iddetail");
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

    // 删除人员信息
    @RequestMapping("/deInfo.do")
    public String deleteInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String check[] = request.getParameterValues("idname");
	String str = "";
	int i = 0;
	if (check != null) {
	    for (String st : check) {
		if (i == check.length - 1) {
		    str += st;
		} else {
		    str += st + ",";
		}
		i++;
	    }
	}

	userinfoservice.deleteInfo(str);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print("删除成功");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 修改人员信息
    @RequestMapping("/upInfo.do")
    public String upInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String id = request.getParameter("EworkerId");// 获取人员id
	String name = request.getParameter("Ename");// 获取人员名字
	if (name.equals("")) {
	    name = null;
	}
	String sex = request.getParameter("inlineRadioOptions");// 性别
	if (sex.equals("")) {
	    sex = null;
	}
	String birthdate = request.getParameter("demo");// 获取人员出生日期
	if (birthdate.equals("")) {
	    birthdate = null;
	}
	String category = request.getParameter("Eworkerclass");// 获取职称
	if (category.equals("请选择")) {
	    category = null;
	}
	String attritube = request.getParameter("Estatus");// 获取人员身份属性
	if (attritube.equals("请选择")) {
	    attritube = null;
	}
	String dept = request.getParameter("Edivision");// 获取人员部门
	if (dept.equals("")) {
	    dept = null;
	}
	String telephone = request.getParameter("Ephone");// 获取人员电话
	if (telephone.equals("")) {
	    telephone = null;
	}
	String idcard = request.getParameter("IDnumber");// 身份证
	if (idcard.equals("")) {
	    idcard = null;
	}
	String password = request.getParameter("Epassword");// 密码
	if (password.equals("")) {
	    password = null;
	}
	userinfoservice.upInfo(id, name, sex, birthdate, category, attritube,
		dept, telephone, idcard, password);

	return "mangeruser";
    }

    // 增加人员信息
    @RequestMapping("/addInfo.do")
    public String addInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	int flag = 0;
	String id = request.getParameter("workerId");// 获取人员id
	UserInfo Info = new UserInfo();
	// System.out.println(id);
	String name = request.getParameter("name");// 获取人员名字
	if (name.equals("")) {
	    name = null;
	}
	String sex = request.getParameter("inlineRadioOptions");// 性别
	if (sex.equals("")) {
	    sex = null;
	}
	String birthdate = request.getParameter("demo2");// 获取人员出生日期
	if (birthdate.equals("")) {
	    birthdate = null;
	}
	// System.out.println(birthdate);
	String category = request.getParameter("Awkclass");// 获取员工类别
	// System.out.println(category);
	if (category.equals("")) {
	    category = null;
	}
	// System.out.println(category);
	String attritube = request.getParameter("Astatus");// 获取人员身份属性
	if (attritube.equals("")) {
	    attritube = null;
	}
	// System.out.println(attritube);
	String dept = request.getParameter("Adivision");// 获取人员部门
	if (dept.equals("")) {
	    dept = null;
	}
	// System.out.println(dept);
	String telephone = request.getParameter("phone");// 获取人员电话
	if (telephone.equals("")) {
	    telephone = null;
	}
	String idcard = request.getParameter("IDnumber1");// 身份证
	// System.out.println(idcard);
	if (idcard.equals("")) {
	    idcard = null;
	}
	String password = request.getParameter("password");// 密码
	if (password.equals("")) {
	    password = null;
	}
	flag = userinfoservice.addInfo(id, name, sex, birthdate, category,
		attritube, dept, telephone, idcard, password);
	response.setContentType("text/html;charset=UTF-8");
	try {
	    response.getWriter().print(flag);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    // 获取身份属性和部门(attritube:员工身份属性)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping("/getAllInfo.do")
    public String getAllInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {

	List<Admin> list = userinfoservice.getAttritube();// 身份属性
	List<ApplyDept> la = userinfoservice.getDepts();// 部门
	List<Map<String, String>> title = userinfoservice.Title();

	try {
	    List list1 = new ArrayList();
	    list1.add(list);
	    list1.add(la);
	    list1.add(title);
	    JSONArray json = JSONArray.fromObject(list1);
	    response.setContentType("text/html;charset=UTF-8");
	    response.getWriter().print(json.toString());

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return null;
    }

    // 导出功能
    @RequestMapping("/exportPersonInfo.do")
    public String exportPersonInfo(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	String dept = request.getParameter("dept");
	// System.out.println(dept);
	if (dept.equals("1")) {// 1表示导出所有部门人员信息
	    dept = null;
	}
	List<Manger> list = userinfoservice.exportPersonInfo(dept);

	if (CollectionUtils.isNotEmpty(list)) {

	    /*
	     * String path = request.getSession().getServletContext()
	     * .getRealPath("/upload/");
	     */
	    String path = ExcelReport.getWebRootUrl(request, "/upload/");
	    String fullFileName = path + "/PersonInfo.xlsx";
	    ExcelReport export = new ExcelReport();
	    export.exportPersonInfo(list, fullFileName);
	    String filename = "湖南农业大学人员信息表.xlsx";
	    // System.out.println(fullFileName);

	    // 显示中文文件名
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
	    // 读取文件
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

	    } catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    return null;
	}

	return "mangeruser";
    }

    // 导入功能
    @RequestMapping("/inputExcel.do")
    public String inputExcel(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) throws IOException {

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
	    List<List<String>> list = InputExcelServiceImpl.getExcelRows(
		    InputExcelServiceImpl.getSheet(wb, 0), -1, -1);
	    // System.out.println("获得数据啦！！！！！！！！！");
	    // ！！！！！！注意此处是遍历list，可在下面写插入数据库的语句

	    if (CollectionUtils.isNotEmpty(list)) {
		// 实现批量插入
		String prefix = "INSERT IGNORE INTO baseweb.userinfo(id,name,sex,ID_number,"
			+ "arriveTime,birthdate,college,"
			+ "contactForm,endContactTime,formerUnit,"
			+ "hukou,startContactTime,telephone,"
			+ "userType,workTime,workingForm,majorid,titles) " + "values";

		StringBuffer suffix = new StringBuffer();
		// 遍历行（下面当i为0时可看到列头名）		
		for (int i = 1; i < list.size(); i++) {
			boolean tag_this=true;
		    String resultStr = "";
		    // 循环每一个sheet中的每一行
		    List<String> row = list.get(i);		   
		    // 遍历列
		    if (row != null && row.size() > 0) {
			for (int j = 0; j < row.size(); j++) {
				if(row.get(0).equals("")){
					tag_this=false;
					break;
				}
			    resultStr = resultStr + '"' + row.get(j) + '"'
				    + ',';			   
			}
			if(tag_this){
			resultStr = resultStr.substring(0,
				resultStr.length() - 1);
			suffix.append("(" + resultStr + "),");
			}

		    }
		}
		// 构建完整sql
		String sql = prefix
			+ suffix.substring(0, suffix.length() - 1)
			+ " on duplicate key update name=values(name),sex=values(sex),ID_number=values(ID_number),arriveTime=values(arriveTime)"
			+ ",birthdate=values(birthdate),college=values(college),contactForm=values(contactForm),endContactTime=values(endContactTime)"
			+ ",formerUnit=values(formerUnit),hukou=values(hukou),startContactTime=values(startContactTime),telephone=values(telephone)"
			+ ",userType=values(userType),workTime=values(workTime),workingForm=values(workingForm),majorid=values(majorid),titles=values(titles)";
		// System.out.println(sql);
		adminManageServiceImpl.setAdminFunction(sql);
	    }

	    wb.close();
	    outputStream.close();
	    inputStream.close();
	    tempFile.delete(); // 删除临时文件

	}
	return "redirect:mangeruser.jsp";
    }

    // 筛选userInfo中的部门
    @RequestMapping("/Checkdept.do")
    public String Checkdept(HttpServletRequest request,
	    HttpServletResponse response, ModelMap map) {
	List<UserInfo> list = userinfoservice.userdept();
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
