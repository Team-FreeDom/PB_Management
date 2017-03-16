package com.base.service;

import java.util.List;

import com.base.po.Admin;
import com.base.po.AdminFunction;

public interface AdminManageService {

	 public List<AdminFunction> getAdminFunctionInfos();
	 
	 /*
	   参数说明：
	   返回值：   List<Admin>
	   函数功能：获取Admin表中的所有信息
	 */
     public List<Admin> getAdminInfos();
     
     public void setAdminFunction(String insertSql);
     
     public long getAdminValue(String usrid);
}
