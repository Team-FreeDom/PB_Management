package com.base.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.Admin;
import com.base.po.AdminFunction;
import com.base.po.ApplyDept;
import com.base.po.Message;
import com.base.po.Notification;
import com.base.serviceImpl.LandApplyServiceImpl;
import com.base.serviceImpl.NotificationServiceImpl;
import com.base.utils.CookieUtils;

@Controller("NotificationController")
public class NotificationController {

	@Autowired
	private NotificationServiceImpl notificationServiceImpl;
	
	@Autowired
	private LandApplyServiceImpl landApplyServiceImpl;

	// 保存通知信息到数据库
	@RequestMapping("jsp/notification.do")
	public String notification(HttpServletRequest request, ModelMap map,
				HttpServletResponse response) {

			//获取所有的部门
		    List<ApplyDept> applyDeptList = landApplyServiceImpl.getDepts();
		    map.addAttribute("applyDeptList", applyDeptList);
		    
			return "notification";
	}
	
	
	// 保存通知信息到数据库
	@RequestMapping("jsp/saveNotification.do")
	public String saveNotification(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		String values = request.getParameter("data");
		// System.out.println("values:"+values);
		String insertSql = "insert into Notification(id,message) values(1,\""
				+ values.trim()
				+ "\") on duplicate key update message=values(message)";
		// System.out.println(insertSql);
		notificationServiceImpl.setNotification(insertSql);

		return null;
	}

	@RequestMapping("jsp/getNotification.do")
	public String getNotification(ModelMap map, HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");
		Notification notification = notificationServiceImpl
				.getNotificationInfo();

		try {
			if (notification != null)
				response.getWriter().print(notification.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 保存通知信息到数据库
	@RequestMapping("jsp/saveMessage.do")
	public String saveMessage(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");		
		String content = request.getParameter("content");
		String depatment = request.getParameter("depatment");
		int isRead = 0;
        
		notificationServiceImpl.addMessage("系统通知",content,depatment.trim() );

		return null;
	}

	@RequestMapping("jsp/getMessage.do")
	public String getMessage(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");
		String userid = CookieUtils.getCookieUsername(request, response);
		List<Message> messageList = null;
		//System.out.println(userid);
		if (userid != "")
			messageList = notificationServiceImpl.getMessageInfos(userid);

		if (messageList != null){
			map.addAttribute("messageList", messageList);
			//System.out.println("messageList不为空");
		}

		return "msgUI";
	}
	
	@RequestMapping("jsp/getNoReadMessageCount.do")
	public String getNoReadMessageCount(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");	
		String userid = CookieUtils.getCookieUsername(request, response);
		int number = notificationServiceImpl.getNoreadMessageCount(userid);
		//System.out.println("未读消息数为："+number);
		try {
			if(number==0)
				response.getWriter().print("");
			else
				response.getWriter().print(number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("jsp/setReadMessage.do")
	public String setReadMessage(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");	
		String id = request.getParameter("id");
		notificationServiceImpl.setReadMessage(Integer.valueOf(id));
		
		//读取未读消息数，更新消息界面
		String userid = CookieUtils.getCookieUsername(request, response);
		int number = notificationServiceImpl.getNoreadMessageCount(userid);
		//System.out.println("未读消息数为："+number);
		try {
			if(number==0)
				response.getWriter().print("");
			else
				response.getWriter().print(number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
