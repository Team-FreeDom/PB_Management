package com.base.service;

import java.util.List;

import com.base.po.ProfessionalmanageList;


public interface ProfessionalmanageService {

	/*
	 	 函数功能： 向专业表中插入一条新的专业
	 */
	
	public int insertMajor(String mid,String mname,int aid);
	public String deleteMajor(String str);
	public ProfessionalmanageList query_majors(int size,int pageindex,int order,String orderDir,String searchValue);
	public void updatemajor(String mid,String mname);
	public int CheckmName(String name);
	public int CheckmName1(String name);
}
