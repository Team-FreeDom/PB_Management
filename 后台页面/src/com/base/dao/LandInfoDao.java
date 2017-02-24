package com.base.dao;

import java.util.List;

import com.base.po.LandInfo;

//土地布局管理表的数据库连接层接口
public interface LandInfoDao {
	
	/*
	   参数说明：lid,整型，表示土地编号;spareValue,整型
	   返回值：   无返回值
	   函数功能：更改土地空闲值
	 */
	public void updateSpareValue(int lid,int spareValue);
	
	/*
	   参数说明：lid,整型，表示土地编号
	   返回值：   List<LandInfo>,为土地管理LandInfo对象的集合
	   函数功能：根据土地编号获取该土地布局管理信息
	 */
    public List<LandInfo> getLandInfo(String lid);
    
    /*
	   参数说明：bid,整型，表示基地编号
	   返回值：   List<LandInfo>,为土地管理LandInfo对象的集合
	   函数功能：根据基地编号获得该基地中的所有土地布局管理信息
	 */
    public List<LandInfo> getLandInfos(int bid);
}