package com.base.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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

	// 用户单点登录控制
	@RequestMapping("/index.do")
	public String index(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		// 获得通知公告标题列表
		response.setContentType("text/html;charset=UTF-8");
		List<Notification> notificationList = notificationServiceImpl.getNotificationTop5Infos();
		
		if(CollectionUtils.isNotEmpty(notificationList)){
	
			map.addAttribute("notificationList", notificationList);
			//System.out.println("messageList不为空");
		}
		
		//获取注册用户
		long userCount =  mainServiceImpl.getUserCount();
		map.addAttribute("userCount", userCount);
		
		//获取租赁申请
		long applyCount =  mainServiceImpl.getApplyCount();
		map.addAttribute("applyCount", applyCount);
		
		//获取时间排序前5条
		String userid = CookieUtils.getCookieUsername(request, response);
		List<Message> messageList = notificationServiceImpl.getMessageTop5Infos(userid);
		if(CollectionUtils.isNotEmpty(messageList)){
			map.addAttribute("messageList", messageList);
			//System.out.println("messageList不为空");
		}
		
		return "index";
	}
	
	
}
