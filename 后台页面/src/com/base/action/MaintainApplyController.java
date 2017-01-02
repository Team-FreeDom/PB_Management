package com.base.action;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.base.po.ProBaseinfo;
import com.base.serviceImpl.MaintainApplyServiceImpl;
import com.base.utils.CookieUtils;

@Controller("MaintainApplyController")
@RequestMapping("/jsp")
public class MaintainApplyController
{
	@Autowired
	private MaintainApplyServiceImpl applyservice;
	//校内基地名查询
	@RequestMapping("/basename.do")
	public String find_basename(HttpServletRequest request, ModelMap map,HttpServletResponse response)
	{
		List<ProBaseinfo> list=applyservice.find_basename();
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			JSONArray json = JSONArray.fromObject(list);
			response.getWriter().print(json.toString());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return null;
	}
	
	//提交维修申请
	@RequestMapping("/insertmaintain.do")
	public String insert_maintain(HttpServletRequest request, ModelMap map,HttpServletResponse response)
	{
		String filename = "";
		// 上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 得到上传的文件
		MultipartFile mFile = multipartRequest.getFile("fileResource");
		// 得到上传服务器的路径
		String path = request.getSession().getServletContext()
				.getRealPath("/maintainfile/");
		// 得到上传的文件的文件名
		String fileName = mFile.getOriginalFilename();
		System.out.println(fileName);
		try
		{
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
				filename = "../maintainfile/" + filename;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		String userid = CookieUtils.getUserid(request);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String apply_time=df.format(new Date());// new Date()为获取当前系统时间
		String pro_name=request.getParameter("projectname");
		String username=request.getParameter("name");
		String address=request.getParameter("address");
		double money=Double.valueOf(request.getParameter("budget"));
		String bid=request.getParameter("baselist");
		String reason=request.getParameter("reason");
		String str="("+pro_name+bid+username+address+reason+filename+money+apply_time+userid+")";
		applyservice.insert_maintain(str);
		return "我的租赁";
	}
	//维修管理界面，把所有信息传到前台
}
