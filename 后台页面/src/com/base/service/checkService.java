package com.base.service;

import java.util.List;

import com.base.po.LandApply;

//审核模块业务逻辑层接口
public interface checkService {

	/*
	   参数说明：无参
	   返回值：   List<LandApply>,为土地申请LandApply对象的集合
	   函数功能：查询所有土地申请记录
	 */
	public List<LandApply> getLandApplys();
	
	/*
	  参数说明：la_id,整型，为申请记录编号；lid,整型，为土地编号	          
	 返回值：    无返回值
	 函数功能： 同意申请
	  */
	public void agreeApply(int la_id,int lid);
	
	/*
	  参数说明：la_id,整型，为申请记录编号；lid,整型，为土地编号	          
	 返回值：    无返回值
	 函数功能： 拒绝他人
	  */
	public void refuseOthers(int la_id,int lid);	


}
