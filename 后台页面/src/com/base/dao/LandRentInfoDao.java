package com.base.dao;

import java.util.List;

import com.base.po.ApplyDept;
import com.base.po.LandRentInfo;
import com.base.po.RentList;
import com.base.po.RentMaintain;

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
	   参数说明：bname,字符串型，基地名称;dept,为部门名称;planting,为种植内容;page,为页码;length,为每页条数
	   返回值：  RentList，存放记录和记录条数的对象
	   函数功能：根据条件刷选并获得土地租赁信息
	 */
	public RentList getRentMaintain(String bname,String dept,String planting,int page,int length);
	
	/*
	   参数说明：str,为土地信息记录的编号集合
	   返回值：  无返回值
	   函数功能：删除多条土地租赁信息记录
	 */
	public void deleteRentInfo(String str);
	
	/*
	   参数说明：lr_id,为
	   返回值：  无返回值
	   函数功能：删除多条土地租赁信息记录
	 */
	public List<RentMaintain> getSingleRentInfo(String lr_id,String dept );
	
	/*
	   参数说明：无参数
	   返回值：  List<ApplyDept>，为ApplyDept对象的集合
	   函数功能：获得土地信息记录中存在的部门
	 */
	public List<ApplyDept> getExistDept();
	
	/*
	   参数说明：无参数
	   返回值：  List<String>,String的集合
	   函数功能：获得土地信息记录中存在的种植内容
	 */
	public List<String> getExistPlant();
}
