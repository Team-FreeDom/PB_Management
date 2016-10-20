package com.base.dao;

import java.util.List;

import com.base.po.LandRentInfo;

//土地使用表的数据库连接层接口
public interface LandRentInfoDao {
	
	/*
	   参数说明：lr，为土地使用LandRentInfo对象实例
	   返回值：    无返回值
	   函数功能：向土地使用表中插入一条新纪录	           
	 */	
    public void doLandRentInfo(LandRentInfo lr);
    
    /*
	   参数说明：userId,字符串型，为土地的使用人编号
	   返回值：   List<LandRentInfo>，为土地使用对象LandRentInfo的集合
	   函数功能：根据土地使用人编号查询土地使用记录
	 */
	public List<LandRentInfo> getLandRentInfo(String userId);
    
	/*
	   参数说明：无参
	   返回值：   List<LandRentInfo>，为土地使用对象LandRentInfo的集合
	   函数功能：获取所有土地使用情况
	 */
    public List<LandRentInfo> getLandRentInfos(); 
}
