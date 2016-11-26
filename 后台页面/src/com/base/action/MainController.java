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

	// �û������¼����
	@RequestMapping("/index.do")
	public String index(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		// ���֪ͨ����
		response.setContentType("text/html;charset=UTF-8");
		Notification notification = notificationServiceImpl
				.getNotificationInfo();

		if (notification != null)
			map.addAttribute("notification", notification.getMessage());

		//��ȡע���û�
		long userCount =  mainServiceImpl.getUserCount();
		map.addAttribute("userCount", userCount);
		
		//��ȡ��������
		long applyCount =  mainServiceImpl.getApplyCount();
		map.addAttribute("applyCount", applyCount);
		
		//��ȡʱ������ǰ5��
		String userid = CookieUtils.getCookieUsername(request, response);
		List<Message> messageList = notificationServiceImpl.getMessageTop5Infos(userid);
		if (messageList != null){
			map.addAttribute("messageList", messageList);
			System.out.println("messageList��Ϊ��");
		}
		
		return "index";
	}
}
