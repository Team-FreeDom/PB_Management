package com.base.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.base.po.UserInfo;
import com.base.service.UserInfoService;
import com.base.utils.CookieUtils;
import com.base.utils.ExcelReport;

@Controller("UserController")
@RequestMapping("/jsp")
public class UserController {
	@Autowired
	private UserInfoService userInfoservice;

	// 修改个人资料
	@RequestMapping("/Userupdata.do")
	public String Userupdata(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws IOException {
		String filename = null;
		Cookie[] cookies = request.getCookies();// 获得所有cookie对象
		boolean flag = false;
		for (Cookie co : cookies) { // 遍历cookie数组
			// System.out.println(co.getName());
			if (co.getName().equals("username")) { // 判断此cookie的key值是否是username
				String id = co.getValue();

				// 上传文件（图片），将文件存入服务器指定路径下，并获得文件的相对路径
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 得到上传的文件
				MultipartFile mFile = multipartRequest.getFile("img");
				if (!mFile.isEmpty()){
					// 得到上传服务器的路径
					/*
					 * String path = request.getSession().getServletContext()
					 * .getRealPath("/imgdraw/");
					 */
					String path = ExcelReport.getWebRootUrl(request,"/imgdraw/");
 
					//先删除原有的图像
					String deleteFile = CookieUtils.getCookieImage(request,response);
					deleteFile = deleteFile.substring(deleteFile.lastIndexOf("/"));
					File tempFile = new File(path+deleteFile);
					if (tempFile.isFile() && tempFile.exists()) { 
					   tempFile.delete();
					}
					//System.out.println(path+deleteFile);
					
					// 得到上传的文件的文件名
					String fileName = mFile.getOriginalFilename();
					String fileType = fileName.substring(fileName
							.lastIndexOf("."));
					filename = new Date().getTime() + fileType;
					InputStream inputStream = mFile.getInputStream();
					byte[] b = new byte[1048576];
					int length = inputStream.read(b);
					path += "/" + filename;
					// 文件流写到服务器端
					FileOutputStream outputStream = new FileOutputStream(path);
					outputStream.write(b, 0, length);
					inputStream.close();
					outputStream.close();
					filename = "../imgdraw/" + filename;
					
					//重新写cookie中的img属性值
					CookieUtils.addCookie("image", filename,response);
					
					
				}
				String name = request.getParameter("name");
				if (name.equals("")){
					name = null;
				}
				String telephone = request.getParameter("telephone");
				if (telephone.equals("")) {
					telephone = null;
				}
				String possword = request.getParameter("possword");
				if (possword.equals("")) {
					possword = null;
				}
				userInfoservice.update(id, name, telephone, possword, filename);
			}
		}

		//CookieUtils.addCookie("image", filename, response);

		return "redirect:user.jsp";
	}

	// 获取个人信息
	@RequestMapping("/getInfoPerson.do")
	public String getInfoPerson(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		Cookie[] cookies = request.getCookies();// 获得所有cookie对象
		boolean flag = false;
		for (Cookie co : cookies) { // 遍历cookie数组
			// System.out.println(co.getName());
			if (co.getName().equals("username")) { // 判断此cookie的key值是否是username
				// System.out.println(co.getValue());
				String id = co.getValue();
				List<UserInfo> list = userInfoservice.getInfoPerson(id);
				JSONArray json = JSONArray.fromObject(list);
				response.setContentType("text/html;charset=UTF-8");
				try {
					response.getWriter().print(json.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
