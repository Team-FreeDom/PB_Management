package com.base.dao;

import java.util.List;

import com.base.po.LandLayout;

//土地布局管理表的数据库连接层接口
public interface LandLayoutDao {

	/*
	   参数说明：layout,为土地布局LandLayout对象
	   返回值：   无返回值
	   函数功能：向土地布局管理表中插入一条记录
	 */
	public void doLandLayout(LandLayout layout);
	
	/*
	   参数说明：layout,为土地布局LandLayout对象
	   返回值：   无返回值
	   函数功能：更新土地布局管理表中的一条记录
	 */
	public void updateLandLayout(LandLayout layout);
	
	/*
	   参数说明：id,整型，表示土地布局记录的编号
	   返回值：   无返回值
	   函数功能：删除土地布局管理表中的一条记录
	 */
	public void delLandLayout(int id);
}
