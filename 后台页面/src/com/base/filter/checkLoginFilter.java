package com.base.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.utils.CookieUtils;

public class checkLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getServletPath();
		// 设置不需要过滤的页面
		if (url.endsWith("login.do")) {
			
			arg2.doFilter(arg0, arg1);
			return;
		}

        if (url.endsWith("getAuthCode.do")) {
			
			arg2.doFilter(arg0, arg1);
			return;
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			
			response.sendRedirect("/BaseWeb/login_soft.html");
			return;
		}
      
		boolean flag = CookieUtils.checkLogin(request, response,cookies);
		
		if (!flag) { // 如果没有登录或者登录后超过失效时间都跳转到登录页面
			
			response.sendRedirect("/BaseWeb/login_soft.html");
			return;
		}
		
		//访问权限控制
		//获取admin.properties中的权限值，然后用户权限做
	    Properties prop = new Properties();
	    prop.load(request.getSession().getServletContext().getResourceAsStream("/WEB-INF/admin.properties"));
	    String requestPage = url.substring(url.lastIndexOf('/')+1); 
	    String urlAdminValue= prop.getProperty(requestPage);	  
	    if(urlAdminValue!=null){
	    	for(Cookie co:cookies)
			{
	    		if(co.getName().equalsIgnoreCase("adminValue"))
				{
	    		  //大数值字符串不能转化为Integer
				  BigInteger adminValueTemp = new BigInteger(co.getValue());
				  long adminValue = adminValueTemp.longValue();
				  long a = (long)Math.pow(2,Integer.valueOf(urlAdminValue));
				  System.out.println("a:"+a);
				  
				  if((a & adminValue)==0){ //如果没有访问权限则返回
					  response.setContentType("text/html;charset=UTF-8");
					  PrintWriter out=response.getWriter();
					  out.println("<HTML><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> <BODY>");
					  out.println("<script language='javascript'>");
					  out.println("alert('你没有访问权限！');");//可以不写
					  out.println("window.history.back();");//返回
					  out.println("</script>");
					  out.println("</body> </html>"); 
					  return;
				  }
				}
			}
		}

		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
