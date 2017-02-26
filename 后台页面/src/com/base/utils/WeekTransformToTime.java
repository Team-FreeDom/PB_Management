package com.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;



import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import com.base.contants.Contants;

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

}
