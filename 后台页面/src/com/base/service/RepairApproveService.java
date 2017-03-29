package com.base.service;

import java.util.List;

import com.base.po.MaintainList;

//保修申请审核逻辑
public interface RepairApproveService {

	/*
	  参数说明：baseid,字符串型，为基地名称; userid,字符串型,为用户名字    
	 返回值：    MaintainList,该对象包括报修申请数据记录+记录条数
	 函数功能： 获取报修申请数据
	  */
	public MaintainList getRepairInfo(String baseid,String userid,int pageIndex,int size,int order,String orderDir,String searchValue,int status);	
	
	/*
	  参数说明：refusestr，为保修申请记录的编号的封装;infostr，字符串型，为要发送的信息    
	 返回值：    flag0或1代表是否可以拒绝报修申请
	 函数功能： 拒绝保修申请
	  */
	public int refuseRepairApply(String recorddigit,String refusestr,String infostr);
	
	/*
	  参数说明：agreestr，为保修申请记录的编号的封装;infostr，字符串型，为要发送的信息    
	 返回值：   flag0或1代表是否可以同意报修申请
	 函数功能：同意报修申请
	  */
	public int agreeRepairApply(String agreestr,String infostr);
	
	/*
	  参数说明：无参数值
	 返回值：    List，为基地名字和申报部门的集合
	 函数功能：获取基地名字和申报部门的集合
	  */
	public List getInfoApply();
	
	/*
	  参数说明：storestr，为保修申请记录的编号的封装;infostr，字符串型，为要发送的信息 
	 返回值：   无返回值
	 函数功能：维修完成
	  */
	public void finishRepairApply(String storestr,String infostr);
	
}
