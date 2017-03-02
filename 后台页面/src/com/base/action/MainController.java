package com.base.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import com.base.po.Message;
import com.base.po.Notification;
import com.base.po.StartDate;
import com.base.service.PlanMaintainService;
import com.base.serviceImpl.MainServiceImpl;
import com.base.serviceImpl.NotificationServiceImpl;
import com.base.utils.CookieUtils;
import com.base.utils.WeekTransformToTime;

@Controller("mainController")
@RequestMapping("/jsp")
public class MainController implements ServletContextAware{
	
	@Autowired
	private MainServiceImpl mainServiceImpl;
	@Autowired
	private PlanMaintainService planMaintainService;
	@Autowired
	private NotificationServiceImpl notificationServiceImpl;
	
	 private ServletContext application;  
	    @Override  
	    public void setServletContext(ServletContext arg0) {  
	        this.application = arg0;  	       
	    }  

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
		
		if(application.getAttribute("map_0")==null){
			List<StartDate> list1=planMaintainService.getStartDate();
			WeekTransformToTime.getLatestStartTime(application, list1);
			System.out.println("为空呢");
		}
		String semester=WeekTransformToTime.getThisSemester(application);
		System.out.println("不为空呢");
		
		//获取实习申请数目和报修申请数目
		long[] value=mainServiceImpl.getRepairAndPracCount(semester);		
		map.addAttribute("repairCount", value[0]);//获取维修申请
		map.addAttribute("praticecount",value[1]);//获取实习申请
		map.addAttribute("applyCount", value[2]);//获取租赁申请
		
		//获取时间排序前5条
		String userid = CookieUtils.getCookieUsername(request, response);
		List<Message> messageList = notificationServiceImpl.getMessageTop5Infos(userid);
		if(CollectionUtils.isNotEmpty(messageList)){
			map.addAttribute("messageList", messageList);
			
		}
		
		return "index";
	}
	
	
}
