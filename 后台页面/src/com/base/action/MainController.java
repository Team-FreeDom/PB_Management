package com.base.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.Message;
import com.base.po.Notification;
import com.base.serviceImpl.MainServiceImpl;
import com.base.serviceImpl.NotificationServiceImpl;
import com.base.utils.CookieUtils;

@Controller("mainController")
@RequestMapping("/jsp")
public class MainController {
	
	@Autowired
	private MainServiceImpl mainServiceImpl;
	
	@Autowired
	private NotificationServiceImpl notificationServiceImpl;

	/*@RequestMapping("/getCount.do")
	public String getCount(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {
		String str = mainServiceImpl.getCount();
		JSONArray json = JSONArray.fromObject(str);

		try {
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}*/

	// 用户单点登录控制
	@RequestMapping("/index.do")
	public String index(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		// 获得通知公告标题列表
		response.setContentType("text/html;charset=UTF-8");
		List<Notification> notificationList = notificationServiceImpl.getNotificationTop5Infos();
		if (notificationList != null){
			map.addAttribute("notificationList", notificationList);
			//System.out.println("messageList不为空");
		}
		/*Notification notification = notificationServiceImpl
				.getNotificationInfo();

		if (notification != null)
			map.addAttribute("notification", notification.getMessage());*/

		//获取注册用户
		long userCount =  mainServiceImpl.getUserCount();
		map.addAttribute("userCount", userCount);
		
		//获取租赁申请
		long applyCount =  mainServiceImpl.getApplyCount();
		map.addAttribute("applyCount", applyCount);
		
		//获取时间排序前5条
		String userid = CookieUtils.getCookieUsername(request, response);
		List<Message> messageList = notificationServiceImpl.getMessageTop5Infos(userid);
		if (messageList != null){
			map.addAttribute("messageList", messageList);
			//System.out.println("messageList不为空");
		}
		
		//获取未读消息数，并写入到cookies中
		/*int number = notificationServiceImpl.getNoreadMessageCount(userid);
		CookieUtils.addCookie("noReadNumber", String.valueOf(number), response);
		System.out.println("未读消息数："+number);*/
		
		return "index";
	}
	
	
}
