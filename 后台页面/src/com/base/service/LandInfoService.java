package com.base.service;

import java.util.List;

import com.base.po.LandLayout;

//土地布局管理表的业务逻辑层接口
public interface LandInfoService {

	/*
	   参数说明：list,LandLayout对象的集合，表示所有的土地布局对象
	   返回值：   无返回值
	   函数功能：存储土地布局
	 */
	public void addLandLayout(List<LandLayout> list);
	
	/*
	   参数说明：list,LandLayout对象的集合，表示所有的土地布局对象
	   返回值：   无返回值
	   函数功能：修改土地布局
	 */
	public void updateLandLayout(List<LandLayout> list);
	
	/*
	   参数说明：id,整型，表示土地布局记录的编号
	   返回值：   无返回值
	   函数功能：删除土地
	 */
	public void deleteLandLayout(String id);
	
	
}
