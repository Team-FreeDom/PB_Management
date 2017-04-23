package com.base.dao;

import java.util.List;
import java.util.Map;

import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;

//实习基地审核逻辑层
public interface BaseCheckDao {    
    
	/*
	  参数说明：applydpid,整型，为申请部门编号; pageindex,为当前页数;size,为每页的条数; order,排序列;
            orderDir,为排序的顺序; searchValue,为模糊查询的值.       
	 返回值：    BaseCheckList,该对象包括实习基地申请数据记录+记录条数
	 函数功能： 获取实习基地申请数据
	  */
    public BaseCheckList getBaseCheck(int applydpid,int pageindex,int size, String columnName, String orderDir,String searchValue);
   
    /*
	  参数说明：无参数值       
	 返回值：    List<Map<String,String>>,为Map<String,String>对象的集合
	 函数功能：获取部门集合
	  */
    public List<Map<String,String>> getDept(int tag);
    
    /*
	  参数说明：recordstr，字符串型，为实习基地申请记录编号的封装;infoStr,字符串型，为要发送的消息
	 返回值：   返回0或1代表是否可以拒绝申请
	 函数功能：申请记录的状态值变为申请失败状态12
	  */
    public int refuseapply(String recorddigit,String recordstr);
    
    /*
	  参数说明：str,字符串型，为('实习基地编号','建立时间','结束时间')的封装;
	          infoStr,字符串型，为要发送的消息
	          recordstr，字符串型，为实习基地申请记录编号的封装    
	 返回值：   返回0或1代表是否可以同意申请
	 函数功能：申请记录的状态值变为申请成功状态6,
	  */
    public int agreeApply(String str,String recordstr);
    
    /*
	  参数说明：sql,为消息的封装  
	 返回值：   无返回值
	 函数功能：插入消息
	 */
    public void insertMessage(String sql);
    
    /*
	  参数说明：applydpid,整型，为申请部门编号; pageindex,为当前页数;size,为每页的条数; order,排序列;
          orderDir,为排序的顺序; searchValue,为模糊查询的值.       
	 返回值：    BaseCheckList,该对象包括续期申请数据记录+记录条数
	 函数功能： 获取续期申请数据
	  */
    public BaseCheckList getaddCheck(int applydpid, int pageindex,int size,
   	     String columnName, String orderDir,String searchValue);
    
    /*
	  参数说明：recordstr，字符串型，为实习基地申请记录编号的封装;infoStr,字符串型，为要发送的消息    
	 返回值：    无返回值
	 函数功能：同意续期申请，申请记录的状态值变为申请成功状态6,
	  */
    public int addDateApply(String recordstr);
    
    /*
	  参数说明：recordstr，字符串型，为实习基地申请记录编号的封装;infoStr,字符串型，为要发送的消息    
	 返回值：    无返回值
	 函数功能：拒绝续期申请，申请记录的状态值变为到期已失效状态17
	 */
    public int refuseDateApply(String recorddigit,String recordstr);

    /**
     * 当管理员点击同意时检查基地表里是否有相同的基地名字
     * @param recorddigit 包装的记录id
     * @return flag0不存在1存在
     */
    public int checkBaseName(String recorddigit);    
    
}
