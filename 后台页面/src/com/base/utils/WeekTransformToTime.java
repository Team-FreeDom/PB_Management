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

    public static String weekTransformToTime(String startTime, int week) {
	String time = null;
	SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
	Date date = null;
	int tag = 1;
	try {
	    date = format0.parse(startTime);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    tag = 0;
	}
	if (tag == 0) {
	    return time;
	}
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.WEDNESDAY, week - 1);
	time = format0.format(cal.getTime());
	;
	return time;
    }

    // 当application为null时,将数据库的数据同步至application
    public static void getLatestStartTime(ServletContext application,
	    List<StartDate> list) {

	for (StartDate sd : list) {
	    application.setAttribute(sd.getSemester(), sd.getStartTime());
	}
    }

    public static String Time(String startTime) {
	String endtime = null;
	SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
	// Date date=format0.parse(d1);
	Calendar cal = Calendar.getInstance();
	try {
	    cal.setTime(format0.parse(startTime));
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	cal.add(Calendar.WEDNESDAY, 20);
	endtime = format0.format(cal.getTime());
	return endtime;
    }

    public static String getThisSemester(ServletContext application) {
	Calendar c = Calendar.getInstance();
	Date d = new Date();
	SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
	String localtime = d1.format(d);
	int year = c.get(Calendar.YEAR);
	String localsemester1 = (year + "-" + (year + 1) + "-" + "2")+"";
	String localsemester2 = (year - 1)+ "-" + year + "-" + "1";
	System.out.println(localsemester2);
	String semester = null;// 此变量存放本时间所处于的学期
	// 判断是否有application内是否有该key值，同时获取该key值的value值
	String semester1 = (String) application.getAttribute(localsemester1);//(第二学期) 每年的上半年所在的学期
	//System.out.println(semester1+"hahahha");
	String semester2 =(String) application.getAttribute(localsemester2);//(第一学期) 每年的下半年所在的学期
	//System.out.println(semester2+"aaaaa");
	if (semester1 != null) {
	    String startTime = semester1;
	    System.out.println(startTime);
	    String endtime = Time(semester1);
	    System.out.println(startTime.compareTo(localtime));
	    // 此处判断当前时间是否大于semester1，小于semester1+20周后的时间,若处于其中，则semester=semester1
	    if (startTime.compareTo(localtime)< 0
		    && endtime.compareTo(localtime) > 0) {
		semester = semester1;
	    }
	}
	if (semester2 != null) {
	    String startTime = semester2;	 
	    String endtime = Time(semester2);
	    /*System.out.println(startTime);
	    System.out.println(endtime);
	    System.out.println(startTime.compareTo(localtime)+"www");
	    System.out.println(endtime.compareTo(localtime)+"qqq");*/
	    // 此处判断当前时间是否大于semester2，小于semester2+20周后的时间,若处于其中，则semester=semester2
	    if (startTime.compareTo(localtime) < 0
		    && endtime.compareTo(localtime) > 0) {
		semester = semester2;
	    }
	}
	return semester;

	// 经过上述操作则获得了现处于的学年学期semester

    }
}
