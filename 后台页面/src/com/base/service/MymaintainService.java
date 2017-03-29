package com.base.service;

import com.base.po.MymaintainList;


//我的报修逻辑层
public interface MymaintainService {

	/*
	  参数说明：year,整型，为年份;status,整型，为状态值;userid,字符串型，为用户编号;
	          pageindex,为当前页数;size,为每页的条数; columnName,排序列名;
              orderDir,为排序的顺序       
	 返回值：    MymaintainList,该对象包括报修申请数据记录+记录条数
	 函数功能： 获取该用户当年的报修申请记录
	  */
    public MymaintainList Mymaintain(int pageindex,int  size,String columnName,
		String orderDir,int year,int status,String userid);
    
    /*
	  参数说明：year,整型，为年份;status,整型，为状态值;userid,字符串型，为用户编号;
	          pageindex,为当前页数;size,为每页的条数; columnName,排序列名;
            orderDir,为排序的顺序       
	 返回值：    MymaintainList,该对象包括报修申请数据记录+记录条数
	 函数功能： 获取该用户所有的报修申请记录
	  */
    public MymaintainList Mymaintain2(int pageindex,int  size,int order,
		String orderDir,int year,int status,String userid);
    
    /*
	 参数说明：id,为报修申请记录的编号;infostr，字符串型，为要发送的信息                
	 返回值：    flag0或1代表是否可以撤回
	 函数功能： 撤回报修申请
	  */
    public int recallmymaint(String id,String infostr);  
}
