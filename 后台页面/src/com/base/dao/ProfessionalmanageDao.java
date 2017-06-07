package com.base.dao;

import java.util.List;

import com.base.po.ProfessionalmanageList;


public interface ProfessionalmanageDao {
	public int insertMajor(String mid,String mname,int aid);
	public String deleteMajor(String str);
	public ProfessionalmanageList query_majors(int size,int pageindex,String columnName,String orderDir,String searchValue);
	public void updatemajor(String mid,String mname);
	public int CheckmName(String name);
	public int CheckmName1(String name);
}
