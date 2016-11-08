package com.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.serviceImpl.UserInfoServiceImpl;
import com.base.utils.CookieUtils;

@Controller("loginController")
@RequestMapping("/jsp1")
public class LoginController {
	
	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl;
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,HttpServletResponse response)
	{
		String userid=request.getParameter("username");
		String pwd=request.getParameter("pwd");
		boolean flag=userInfoServiceImpl.login(userid, pwd);
		if(flag)
		{
			CookieUtils.addCookie("username", userid, response);
			CookieUtils.addCookie("password", pwd, response);
			return "index";
		}else{
			return "login";
		}
	}

}
