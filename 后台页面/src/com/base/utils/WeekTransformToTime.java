package com.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;



import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.base.contants.Contants;
import com.base.po.StartDate;

public class WeekTransformToTime {
	
	public static String weekTransformToTime(String startTime,int week){
		String time=null;				
		SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		int tag=1;
		try {
			 date= format0.parse(startTime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tag=0;
		}
		if(tag==0){
			return time;
		}
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		cal.add(Calendar.WEDNESDAY, week-1); 
		time=format0.format(cal.getTime());;
		return time;
	}
	
	//当application为null时,将数据库的数据同步至application
	public static void getLatestStartTime(ServletContext application,List<StartDate> list){
		
		for(StartDate sd:list){
			application.setAttribute(sd.getSemester(), sd.getStartTime());
		}
	}
	
	public static void getThisSemester(ServletContext application){
		
		String semester=null;//此变量存放本时间所处于的学期
		//判断是否有application内是否有该key值，同时获取该key值的value值
		String semester1=(String) application.getAttribute("2016-2017-2");//每年的上半年所在的学期
		String semester2=(String) application.getAttribute("2017-2018-1");//每年的下半年所在的学期
		if(semester1!=null){
			//此处判断当前时间是否大于semester1，小于semester1+20周后的时间,若处于其中，则semester=semester1
		}
		if(semester2!=null){
			//此处判断当前时间是否大于semester2，小于semester2+20周后的时间,若处于其中，则semester=semester2
		}
		
		//经过上述操作则获得了现处于的学年学期semester
		
		
	}
	

}
