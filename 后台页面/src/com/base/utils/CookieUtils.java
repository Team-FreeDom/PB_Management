package com.base.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtils {
	
	public static void addCookie(String name,String value,HttpServletResponse response)
	{
		
		Cookie cookie=new Cookie(name,value);		
		cookie.setMaxAge(10);
		response.addCookie(cookie);
	}
	
	public static boolean addCookie(HttpServletRequest request,HttpServletResponse response)
	{	
		Cookie[] cookies=request.getCookies();		
		boolean flag=false;
		for(Cookie co:cookies)
		{
			if(co.getName().equals("username"))
			{	
				co.setMaxAge(10);
				response.addCookie(co);				
				flag=true;
				break;
			}
			}
		
		return flag;
		
	}

}
