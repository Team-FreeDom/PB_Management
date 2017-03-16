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
	
	
	public long getUserCount()
	{
        long userCount=userInfoDaoImpl.getUserCount();
		return userCount;
	}
	
	
	public long getApplyCount()
	{
		Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
    	int year = c.get(Calendar.YEAR);     	
    	String date=String.valueOf(year);
    	
		long applyCount=landApplyDaoImpl.getApplyCount(date);
		return applyCount;
	}

	public long[] getRepairAndPracCount(String semester){
		long[] value=landApplyDaoImpl.getRepairAndPracCount(semester);
		return value;
	}
}
