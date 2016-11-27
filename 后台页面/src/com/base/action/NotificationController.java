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

	// ����֪ͨ��Ϣ�����ݿ�
	@RequestMapping("jsp/notification.do")
	public String notification(HttpServletRequest request, ModelMap map,
				HttpServletResponse response) {

			//��ȡ���еĲ���
		    List<ApplyDept> applyDeptList = landApplyServiceImpl.getDepts();
		    map.addAttribute("applyDeptList", applyDeptList);
		    
			return "notification";
	}
	
	
	// ����֪ͨ��Ϣ�����ݿ�
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

	// ����֪ͨ��Ϣ�����ݿ�
	@RequestMapping("jsp/saveMessage.do")
	public String saveMessage(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");		
		String content = request.getParameter("content");
		String depatment = request.getParameter("depatment");
		int isRead = 0;
        
		
		notificationServiceImpl.addMessage("ϵͳ֪ͨ",content,depatment.trim() );

		return null;
	}

	@RequestMapping("jsp/getMessage.do")
	public String getMessage(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");
		String userid = CookieUtils.getCookieUsername(request, response);
		List<Message> messageList = null;
		System.out.println(userid);
		if (userid != "")
			messageList = notificationServiceImpl.getMessageInfos(userid);

		if (messageList != null){
			map.addAttribute("messageList", messageList);
			System.out.println("messageList��Ϊ��");
		}

		
		return "msgUI";
	}
	
	@RequestMapping("jsp/setReadMessage.do")
	public String setReadMessage(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		notificationServiceImpl.setReadMessage(Integer.valueOf(id));
		
		//��cookiesֵ�����޸�
		String noReadNumber = CookieUtils.getCookieNoReadNumber(request, response);
		int number = Integer.valueOf(noReadNumber)-1;
		CookieUtils.addCookie("noReadNumber", String.valueOf(number), response);
		
		return null;
	}
}
