package com.base.serviceImpl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.LandApplyDaoImpl;
import com.base.daoImpl.UserInfoDaoImpl;

@Service("mainService")
public class MainServiceImpl {
	
	@Autowired
	private UserInfoDaoImpl userInfoDaoImpl;
	@Autowired
	private LandApplyDaoImpl landApplyDaoImpl;
	
	
	public String getCount()
	{
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
    	int year = c.get(Calendar.YEAR);     	
    	String date=String.valueOf(year);
    	
		Long userCount=userInfoDaoImpl.getUserCount();
		Long applyCount=landApplyDaoImpl.getApplyCount(date);
		
		String str= "[{\"userCount\":" + userCount + ",\"applyCount\":"+applyCount+"}]";
		return str;
	}

}
