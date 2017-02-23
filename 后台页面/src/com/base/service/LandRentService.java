package com.base.service;

import java.util.List;

import com.base.po.LandRentInfo;
import com.base.po.RentList;
import com.base.po.RentMaintain;

//土地使用信息表的业务逻辑层接口
public interface LandRentService {
	
	/*
	   参数说明：lr，为土地使用LandRentInfo对象实例
	   返回值：    无返回值
	   函数功能：1.向土地使用表中插入一条新纪录
	           2.将土地编号为 lr.getLid()且状态为锁定的土地申请记录的状态值改为申请失败               
	                          将申请编号为lr.getLa_id()的土地申请记录的状态值改为申请成功
	 */	
	public void addLandRent(LandRentInfo lr);
	
	/*
	   参数说明：userId,字符串型，为土地的使用人编号
	   返回值：   List<LandRentInfo>，为土地使用对象LandRentInfo的集合
	   函数功能：查询用户个人的土地使用情况
	 */
	public List<LandRentInfo> getUserRentInfos(String userId);
	
	/*
	   参数说明：无参
	   返回值：   List<LandRentInfo>，为土地使用对象LandRentInfo的集合
	   函数功能：查询所有土地使用情况
	 */
	public RentList getLandRentInfos(String bname,String deptName,String plantingContent,int page,int length);
}
