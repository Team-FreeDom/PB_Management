package com.base.dao;

import java.util.List;

import com.base.po.BaseInfo;

//基地信息表的数据库连接层接口
public interface BaseInfoDao {
	    
    /*
	   参数说明：baseType,整型，表示基地类型
	   返回值：   List<BaseInfo>,为基地信息BaseInfo对象的集合
	   函数功能：根据基地类型获得相应基地的信息
	 */
    public List<BaseInfo> getBaseInfos();

}
