package com.base.action;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.UserInfo;
import com.base.serviceImpl.UserInfoServiceImpl;
import com.base.utils.CookieUtils;

@Controller("loginController")
/*@RequestMapping("/jsp1")*/
public class LoginController {
	
	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl;
	
	
	//用户单点登录控制
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
	
		String userid=request.getParameter("username");
		String pwd=request.getParameter("pwd");
		String authCode = request.getParameter("authCode");
		//System.out.println(userid+"  "+pwd);	
		
		//判断验证码
		String strCode = (String) session.getAttribute("strCode");
		if(!authCode.equals(strCode)){
			
			return "redirect:login_soft.html";
		}
		
		
		//判断登录是否成功
		long adminValue=userInfoServiceImpl.login(userid, pwd);
		
		if(adminValue!=-1)
		{
			CookieUtils.addCookie("username", userid, response);
			CookieUtils.addCookie("password", pwd, response);
			CookieUtils.addCookie("logintime",String.valueOf(new Date().getTime()),response);
			CookieUtils.addCookie("adminValue", String.valueOf(adminValue),response);			
			
			
			UserInfo ui=userInfoServiceImpl.getImage(userid);
			//System.out.println(ui.getImg());
			String src="";
			String name="";
			if(ui!=null)
			{
			  src=ui.getImg();
			  name=ui.getName();
			}
			
			CookieUtils.addCookie("image", src,response);
			try {
				CookieUtils.addCookie("name",java.net.URLEncoder.encode(name,"utf-8") ,response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:jsp/index.do";
		}else{
			return "redirect:login_soft.html";
		}
	}
	
	
	//用户单点登录控制
	@RequestMapping("/loginout.do")
	public String loginOut(HttpServletRequest request,HttpServletResponse response)
	{
		CookieUtils.loginout(request, response);
		return "redirect:login_soft.html";
	}
	
	
	@RequestMapping("/getAuthCode.do")
    public void getAuthCode(HttpServletRequest request, HttpServletResponse response,HttpSession session)
            throws IOException {
		//System.out.println("yanzhengma");
        int width = 63;
        int height = 37;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman",0,28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(this.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        String strCode = "";
        for(int i=0;i<4;i++){
            String rand = String.valueOf(random.nextInt(10));
            strCode = strCode + rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand, 13*i+6, 28);
        }
        //将字符保存到session中用于前端的验证
        session.setAttribute("strCode", strCode);
        g.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();

    }
	
	//创建颜色
    Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc>255)
            fc = 255;
        if(bc>255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }
}
