package com.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.ApplyDept;
import com.base.po.MessageShow;
import com.base.po.Notification;
import com.base.serviceImpl.LandApplyServiceImpl;
import com.base.serviceImpl.NotificationServiceImpl;
import com.base.utils.CookieUtils;
import com.base.utils.MessageUtils;

@Controller("NotificationController")
public class NotificationController {

	@Autowired
	private NotificationServiceImpl notificationServiceImpl;

	@Autowired
	private LandApplyServiceImpl landApplyServiceImpl;

	// 通知发布页面
	@RequestMapping("jsp/notification.do")
	public String notification(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		// 获取所有的部门
		List<ApplyDept> applyDeptList = landApplyServiceImpl.getDepts();
		map.addAttribute("applyDeptList", applyDeptList);

		return "notification";
	}

	// 发布通知
	@RequestMapping("jsp/saveNotification.do")
	public String saveNotification(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		String message = request.getParameter("data");
		String title = request.getParameter("title");
		// System.out.println("values:"+values);
		String insertSql = "insert into Notification(title,message) values" +
				"(\'"+ title.trim()+ "\'," +
				"\'"+ message.trim()+ "\')";
	    //System.out.println(insertSql);
	    
		notificationServiceImpl.setNotification(insertSql);

		return null;
	}

	//获取通知详情
	@RequestMapping("jsp/newlist.do")
	public String newlist(ModelMap map, HttpServletResponse response,HttpServletRequest request) {

		response.setContentType("text/html;charset=UTF-8");
		// 获取当前是第几页
		String currentPage = request.getParameter("page");
		if (currentPage == null)
			currentPage = "1";
		map.addAttribute("page", currentPage); // 返回当前页码
		
		// 每页有多少行，这里固定为每页10行
		//String itemsPerPage = request.getParameter("itemsPerPage");
		int itemsPerPage = 10;
		List list = notificationServiceImpl
				.getNotificationInfo(currentPage, String.valueOf(itemsPerPage));
		//System.out.println("getNotification");
		if(CollectionUtils.isNotEmpty(list)){
			map.addAttribute("notifications", list.get(0));
				
			int maxItems = (Integer) list.get(1);  // 获取最大记录数
	        int maxPage=0;
			//System.out.println(maxItems);
			
			if(maxItems%itemsPerPage==0)//判断是否最大记录是每页记录的整数倍
				maxPage = maxItems/itemsPerPage;
			else
				maxPage = maxItems/itemsPerPage+1;
			//System.out.println(maxPage);
			map.addAttribute("totalPages",maxPage);// 返回最大页数
			
			//下面计算分页的起始页码，最多显示10页
			List pageList = new ArrayList();
			List tempList = MessageUtils.calcPage(Integer.valueOf(currentPage), maxPage, 5);
			
			for (int i = (Integer) tempList.get(0); i <= (Integer)tempList.get(1); i++)   
			    	 pageList.add(i);
			
			map.addAttribute("pageList", pageList); // 用于显示页面1,2,3,4,...效果
			// System.out.println("messageList不为空");
		}

		return "newlist";
	}

	//消息发布
	@RequestMapping("jsp/saveMessage.do")
	public String saveMessage(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");
		String content = request.getParameter("content");
		String depatment = request.getParameter("depatment");
		//System.out.println(depatment);
	    notificationServiceImpl.addMessage("系统消息", content, depatment.trim());

		return null;
	}

	//获取消息列表详情
	@RequestMapping("jsp/getMessage.do")
	public String getMessage(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");

		// 获取当前是第几页
		String currentPage = request.getParameter("page");
		if (currentPage == null)
			currentPage = "1";
		map.addAttribute("page", currentPage); // 返回当前页码
		
		// 每页有多少行，这里固定为每页10行
		//String itemsPerPage = request.getParameter("itemsPerPage");
		int itemsPerPage = 10;

		String userid = CookieUtils.getCookieUsername(request, response);
		List<MessageShow> messageList = null;
		List list = null;
		//System.out.println(userid);
		if (userid != "")
			list = notificationServiceImpl.getMessageInfos(userid, currentPage,
					String.valueOf(itemsPerPage));

		if(CollectionUtils.isNotEmpty(list)){
			messageList = (List<MessageShow>) list.get(0);
			map.addAttribute("messageList", messageList); // 展示的消息列表
					
			int maxItems = (Integer) list.get(1); // 获取最大记录数
			int maxPage=0;
			
			if(maxItems%itemsPerPage==0)//判断是否最大记录是每页记录的整数倍
				maxPage = maxItems/itemsPerPage;
			else
				maxPage = maxItems/itemsPerPage+1;
			
			map.addAttribute("totalPages",maxPage);// 返回最大页数
			
			//下面计算分页的起始页码，最多显示10页
			List pageList = new ArrayList();
			List tempList = MessageUtils.calcPage(Integer.valueOf(currentPage), maxPage, 5);
			
			for (int i = (Integer) tempList.get(0); i <= (Integer)tempList.get(1); i++)   
			    	 pageList.add(i);
			
			map.addAttribute("pageList", pageList); // 用于显示页面1,2,3,4,...效果
			// System.out.println("messageList不为空");
		}

		return "msgUI";
	}

	//获取未读消息数
	@RequestMapping("jsp/getNoReadMessageCount.do")
	public String getNoReadMessageCount(ModelMap map,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String userid = CookieUtils.getCookieUsername(request, response);
		int number = notificationServiceImpl.getNoreadMessageCount(userid);
		// System.out.println("未读消息数为："+number);
		try {
			if (number == 0)
				response.getWriter().print("");
			else
				response.getWriter().print(number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	//设置消息为“已读”状态，返回“未读消息数”
	@RequestMapping("jsp/setReadMessage.do")
	public String setReadMessage(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		notificationServiceImpl.setReadMessage(Integer.valueOf(id));

		// 读取未读消息数，更新消息界面
		String userid = CookieUtils.getCookieUsername(request, response);
		int number = notificationServiceImpl.getNoreadMessageCount(userid);
		// System.out.println("未读消息数为："+number);
		try {
			if (number == 0)
				response.getWriter().print("");
			else
				response.getWriter().print(number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	//通知详情页面
	@RequestMapping("jsp/newdetail.do")
	public String newdetail(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
				
		response.setContentType("text/html;charset=UTF-8");
		// 获取要读取的通知详情id
		String id = request.getParameter("id");
		Notification notification =null;
		if (id != null)
			notification = notificationServiceImpl.getNotification(id);
		
		map.addAttribute("notification", notification);
		return "newdetail";
		
	}
	
		
}
