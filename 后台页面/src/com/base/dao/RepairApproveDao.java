package com.base.dao;

import java.util.List;
import java.util.Map;

import com.base.po.ApplyDept;
import com.base.po.MaintainList;

//报修申请审核的数据交互层
public interface RepairApproveDao {

	/*
	  参数说明：baseid,字符串型，为基地名称; userid,字符串型,为用户名字    
	 返回值：    MaintainList,该对象包括报修申请数据记录+记录条数
	 函数功能： 获取报修申请数据
	  */
	public MaintainList getRepairInfo(String baseid, String userid,	int pageIndex, int size, String orderColumn, String orderDir,
			String searchValue, int status);
	
	/*
	  参数说明：status,整型，为标志位
	 返回值：    List<Map<String,String>>，为用户名字的集合
	 函数功能： 获取用户名字
	  */
	public List<Map<String,String>> getUser(int status);
	
	/*
	  参数说明：status,整型，为标志位
	 返回值：    List<Map<String,String>>，为基地名字的集合
	 函数功能： 获取基地名字
	  */
	public List<Map<String,String>> getBase(int status);
	
	/*
	  参数说明：recordstr,字符串型,为报修申请记录的编号;status,整型，为状态值
	 返回值：    flag0或1代表是否可以同意报修申请
	  */
	public int changeStatus(String recordstr,int status);
	
	/*
	  参数说明：refusestr,字符串型,为报修申请记录的编号
	 返回值：    flag0或1代表是否可以拒绝报修申请
	 函数功能： 拒绝申请
	  */
	public int refuseApply(String recorddigit,String refusestr);
	
	/*
	  参数说明：storestr,字符串型,为报修申请记录的编号
	 返回值：    无返回值
	 函数功能： 维修完成
	  */
	public void finish(String storestr);
}
