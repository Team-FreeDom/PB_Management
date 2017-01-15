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
		// ���ò���Ҫ���˵�ҳ��
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
		
		if (!flag) { // ���û�е�¼���ߵ�¼�󳬹�ʧЧʱ�䶼��ת����¼ҳ��
			
			response.sendRedirect("/BaseWeb/login_soft.html");
			return;
		}
		
		//����Ȩ�޿���
		//��ȡadmin.properties�е�Ȩ��ֵ��Ȼ���û�Ȩ����
	    Properties prop = new Properties();
	    prop.load(request.getSession().getServletContext().getResourceAsStream("/WEB-INF/admin.properties"));
	    String requestPage = url.substring(url.lastIndexOf('/')+1); 
	    String urlAdminValue= prop.getProperty(requestPage);
	    //System.out.println(requestPage);
	    //System.out.println(urlAdminValue);
	    if(urlAdminValue!=null){
	    	for(Cookie co:cookies)
			{
	    		if(co.getName().equalsIgnoreCase("adminValue"))
				{
	    		  //����ֵ�ַ���ת��ΪInteger
				  BigInteger adminValueTemp = new BigInteger(co.getValue());
				  long adminValue = adminValueTemp.longValue();
				  long a = (long)Math.pow(2,Integer.valueOf(urlAdminValue));
				  //System.out.println(a);
				  
				  if((a & adminValue)==0){ //���û�з���Ȩ���򷵻�
					  response.setContentType("text/html;charset=UTF-8");
					  PrintWriter out=response.getWriter();
					  out.println("<HTML><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> <BODY>");
					  out.println("<script language='javascript'>");
					  out.println("alert('您没有访问的权限');");//���Բ�д
					  out.println("window.history.back();");//����
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
