package com.base.utils;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static void addCookie(String name, String value,
			HttpServletResponse response) {

		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(60 * 60 * 24); // ��Чʱ��Ϊ1��
		cookie.setPath("/BaseWeb/");
		response.addCookie(cookie);
	}

      public static String getUserid(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();//�������cookie����		
		String userid=null;
		for (Cookie co : cookies) {   //����cookie����
			if (co.getName().equals("username")) {  //�жϴ�cookie��keyֵ�Ƿ���username
				
				userid=co.getValue();
			}
		}
		return userid;
	
		}


	public static boolean addCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		boolean flag = false;
		for (Cookie co : cookies) {
			if (co.getName().equals("username")) {
				co.setMaxAge(60 * 60 * 24);
				response.addCookie(co);
				flag = true;
				break;
			}
		}

		return flag;
	}

	
	public static String getCookieUsername(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie co : cookies) {
			if (co.getName().equals("username")) {
				return co.getValue();
			}
		}

		return "";
	}
	
	public static String getCookieNoReadNumber(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie co : cookies) {
			if (co.getName().equals("noReadNumber")) {
				return co.getValue();
			}
		}

		return "";
	}
	
	// �ж��û��Ƿ��¼
	// ���һ��δ��¼�����ص�¼����
	// ���������¼�ɹ�����δ��Ӧʱ�䳬����10���ӣ���Ҫ���µ�¼
	// ���������¼�ɹ�����Ӧʱ��Ҳδ������10���ӣ������¸��µ�¼ʱ��
	public static boolean checkLogin(HttpServletRequest request,
			HttpServletResponse response, Cookie[] cookies) {
		// Cookie[] cookies=request.getCookies();
		boolean flag = false;
		for (Cookie co : cookies) {
			if (co.getName().equalsIgnoreCase("username"))// �ж��Ƿ��¼�������¼�ɹ��ͻ���cookie�ֶ�username
			{
				flag = true;
				break;
			}
		}
		// System.out.print(flag);
		if (!flag) // ���û�е�¼����ֱ�ӷ���
			return flag;
		else { // �����¼�ɹ������ж�δ��Ӧʱ���Ƿ񳬹���10����
			for (Cookie co : cookies) {
				if (co.getName().equalsIgnoreCase("logintime"))// �ж�δ��Ӧʱ�䳬����10����
				{
					// ����ֵ�ַ�������ת��ΪInteger
					BigInteger loginTime = new BigInteger(co.getValue());
					long currentTime = new Date().getTime();
					long subTime = currentTime - loginTime.longValue();
					// System.out.println(loginTime);
					// System.out.println(currentTime);
					// System.out.println(subTime / (1000*60));
					if (subTime / (1000 * 60) >= 10) {
						flag = false;
						// System.out.print("����ʱ��");
					} else {// δ������10����,�����ʱ��
						co.setMaxAge(60 * 60 * 10);
						co.setPath("/BaseWeb/");
						co.setValue(String.valueOf(currentTime));
						response.addCookie(co);
					}
				}
			}
		}

		return flag;
	}

	public static void loginout(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {

			for (Cookie co : cookies) {
				String cookieName = co.getName();
				if (cookieName.equals("username")
						|| cookieName.equals("logintime")
						|| cookieName.equals("adminValue")) {
					co.setValue(null);
					co.setPath("/BaseWeb/");
					co.setMaxAge(0);
					response.addCookie(co);
				}
			}
		}

		return;
	}

}
