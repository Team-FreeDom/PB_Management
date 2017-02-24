package com.base.dao;

import java.util.List;

import com.base.po.Admin;
import com.base.po.AdminFunction;

public interface AdminMangeDao {

	   /*
	   参数说明：
	   返回值：   List<AdminFunction>,为权限功能的AdminFunction对象的集合
	   函数功能：返回所有需要控制的权限功能ID和名称
	 */
     public List<AdminFunction> getAdminFunctionInfos();
     
     
     /*
	   参数说明：
	   返回值：   List<Admin>
	   函数功能：获取Admin表中的所有信息
	 */
     public List<Admin> getAdminInfos();
     
     public void setAdminFunction(String insertSql);
     
     public long getAdminValue(String userid);
}

