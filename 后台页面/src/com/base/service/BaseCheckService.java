package com.base.service;

import java.util.List;
import java.util.Map;

import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;

//实习基地申请审核逻辑层
public interface BaseCheckService {
    
	/*
	  参数说明：applydpid,整型，为申请部门编号; pageindex,为当前页数;size,为每页的条数; order,排序列;
              orderDir,为排序的顺序; searchValue,为模糊查询的值.       
	 返回值：    BaseCheckList,该对象包括实习基地申请数据记录+记录条数
	 函数功能： 获取实习基地申请数据
	  */
    public BaseCheckList getBaseCheck(int applydpid, int pageindex, int size,
	    int order, String orderDir,String searchValue);
    
    /*
	  参数说明：无参数值       
	 返回值：    List<Map<String,String>>,为Map<String,String>对象的集合
	 函数功能：获取部门集合
	  */
    public List<Map<String,String>> getDept();
    
    /*
	  参数说明：recordstr，字符串型，为实习基地申请记录编号的封装;infoStr,字符串型，为要发送的消息
	 返回值：    无返回值
	 函数功能：拒绝实习基地申请
	  */
    public void refuseapply(String recordstr,String infostr);    
   
    /*
	  参数说明：str,字符串型，为('实习基地编号','建立时间','结束时间')的封装;
	          infoStr,字符串型，为要发送的消息
	          recordstr，字符串型，为实习基地申请记录编号的封装    
	 返回值：    无返回值
	 函数功能：同意实习基地申请
	  */
    public void agreeApply(String str,String infoStr,String recordstr);
    
    /*
	  参数说明：applydpid,整型，为申请部门编号; pageindex,为当前页数;size,为每页的条数; order,排序列;
            orderDir,为排序的顺序; searchValue,为模糊查询的值.       
	 返回值：    BaseCheckList,该对象包括续期申请数据记录+记录条数
	 函数功能： 获取续期申请数据
	  */
    public BaseCheckList getaddCheck(int applydpid, int pageindex, int size,
    	    int order, String orderDir,String searchValue);

    /*
	  参数说明：recordstr，字符串型，为实习基地申请记录编号的封装;infoStr,字符串型，为要发送的消息    
	 返回值：    无返回值
	 函数功能：同意续期申请
	  */
	public void addDateApply(String infostr, String recordstr);

	/*
	  参数说明：recordstr，字符串型，为实习基地申请记录编号的封装;infoStr,字符串型，为要发送的消息    
	 返回值：    无返回值
	 函数功能：拒绝续期申请
	 */
	public void refuseDateApply(String infostr, String recordstr);
}
