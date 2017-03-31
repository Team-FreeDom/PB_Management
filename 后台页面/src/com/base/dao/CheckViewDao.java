package com.base.dao;

import java.util.List;
import java.util.Map;

import com.base.po.CheckList;
import com.base.po.CheckView;
import com.base.po.UserInfo;

public interface CheckViewDao {
		
	/*
	   参数说明：id,为状态值;basename,为基地名称;username,为用户名;usercollage,为部门;
	           pageindex,为页码;size,为每页条数;columnName,为排序的列数;orderDir,为升序还是倒序
	   返回值： CheckList，该对象包含了土地租赁申请记录和记录条数
	   函数功能：根据基地名称，用户名，部门三个条件刷选申请记录状态为id的记录
	 */
	 public CheckList getLandApply(int id, int pageindex, int size,
			    String basename, String username, String usercollage,
			    String columnName, String orderDir);
	 
	 /*
	   参数说明：la_id,为土地租赁申请记录编号
	   返回值：  List<CheckView>，为土地租赁记录
	   函数功能：获得特定的土地租赁记录信息
	 */
	 public List<CheckView> detail(int la_id);
	 
	 /*
	   参数说明：recordStr,为土地租赁申请记录编号封装后的字符串,status1,为记录所处的状态值,status2,为要变成的状态值
	   返回值：  无返回值
	   函数功能：更改特定记录的状态值
	 */
	 public int updateStatus(String recordStr, int status1,int status2);
	 
	 /*
	   参数说明：sql,为插入的消息的sql语句
	   返回值：  无返回值
	   函数功能：向消息表中插入消息
	 */
	 public void insertMessage(String sql);
	 
	 /*
	   参数说明：landstr,为土地编号;status1为转变后的状态值，status2为转变前的状态值
	   返回值：  无返回值
	   函数功能：将特定的土地由状态status1改成status2
	 */
	 public void changeSolid(String landstr, int status1, int status2);
	 
	 /*
	   参数说明：recordStr,为土地租赁记录的编号;status,为要改成的状态值
	   返回值：  无返回值
	   函数功能：确认交费，将记录的状态改为申请成功，并将记录插入土地租赁历史表中
	 */
	 public int payForSuccess(String recordStr, int status);
	 
	 /*
	   参数说明：landstr,为土地编号的封装
	   返回值：  无返回值
	   函数功能：同意申请，将审核状态的变为锁定状态，同时给锁定状态的发送通知
	 */
	 public void lockInfo(String landstr);
	 
	 /*
	   参数说明：landstr,为土地编号的封装
	   返回值：  无返回值
	   函数功能：取消交费，将锁定状态的变为审核状态，发送通知
	 */
	 public void releaseInfo(String landstr);
	 
	 /*
	   参数说明：landstr,为土地编号的封装
	   返回值：  无返回值
	   函数功能：确认交费，将锁定状态的变为同类竞争，发送通知
	 */
	 public void confirmInfo(String landstr);
	 
	 /*
	   参数说明：recordStr,为土地租赁申请编号的封装;status，为要改成的状态值
	   返回值：  int型
	   函数功能：同意申请，将审核中的记录变为交费中，判断土地编号是否有相同的，返回flag值
	 */
	 public int agreeInfo(String recordStr, int status);
	 
	 /*
	   参数说明：无参数值
	   返回值：  List<Map<String, String>>
	   函数功能：获取租赁审批未审核的申请人
	 */
	 public List<Map<String, String>> getCheckApplicant();
	 
	 /*
	   参数说明：无参数值
	   返回值：  List<Map<String, String>>
	   函数功能：获取租赁审批未交费的申请人
	 */
	 public List<Map<String, String>> getPayApplicant();
	 
	 /*
	   参数说明：无参数值
	   返回值：  List<Map<String, String>>
	   函数功能：获取租赁审批逾期的申请人
	 */
	 public List<Map<String, String>> getoverdueApplicant();
	 
	 /*
	   参数说明：无参数值
	   返回值：  List<Map<String, String>>
	   函数功能：获取租赁审批未审核的部门
	 */
	 public List<Map<String, String>> getCheckDept();
	 
	 /*
	   参数说明：无参数值
	   返回值：  List<Map<String, String>>
	   函数功能：获取租赁审批未交费的部门
	 */
	 public List<Map<String, String>> getPayDept();
	 
	 /*
	   参数说明：无参数值
	   返回值：  List<Map<String, String>>
	   函数功能：获取租赁审批逾期的部门
	 */
	 public List<Map<String, String>> getoverdueDept();
	 
	 /*
	   参数说明：recordStr,为土地租赁申请记录的编号
	   返回值：  无返回值
	   函数功能：逾期恢复
	 */
	 public void overduerecovery(String recordStr);

}
