package com.base.dao;

import java.util.List;

import com.base.po.LandApply;
import com.base.po.RentAdd;
import com.base.po.RentCollection;
import com.base.po.Startplan;

//土地申请表的数据库连接层接口
public interface LandApplyDao {
	
	/*
	   参数说明：la,为土地申请LandApply对象
	   返回值：   无返回值
	   函数功能：向土地申请表中插入一条新纪录
	 */
	public void doLandApply(LandApply la);
	
	
	/*
	   参数说明：applicantId,字符串型，为申请人的Id
	   返回值：   List<LandApply>,为土地申请LandApply对象的集合
	   函数功能：根据applicantId(申请人Id)查询土地申请记录
	 */
    public List<LandApply> getUserApplys(String applicantId);
    
    /*
	   参数说明：无参
	   返回值：   List<LandApply>,为土地申请LandApply对象的集合
	   函数功能：查询所有土地申请记录
	 */    
    public List<LandApply> getLandApplys();
  	
	public List<Startplan> getLandApplyDate();	
	
	/*
	  参数说明：sp, 为Startplan对象(工作计划对象)        
	 返回值：    无返回值
	 函数功能：修改工作计划
	 */
	public void updateLandApplyDate(Startplan sp);
	
	/*
	  参数说明：无参数值      
	 返回值：    无返回值
	 函数功能：结束工作计划
	 */
	public void endAllRent();	
	
	/*
	  参数说明：id,为工作计划表的主键   
	 返回值：    为Startplan对象(工作计划对象)
	 函数功能：获取工作计划详情
	 */
	public Startplan getStartPlan(String id);
	
	/*
	  参数说明：bid,整型，为基地编号	          
	 返回值：    List<RentCollection>，为RentCollection对象的集合
	 函数功能： 根据基地编号bid获取土地布局+土地信息+土地租赁历史
	 */
	public List<RentCollection> getRentCollection(int bid);
	
	/*
	  参数说明：semester,字符串型，为学年学期	          
	 返回值：    long[],长整形的数值集合;
	 函数功能： 获取当前阶段的维修申请，租赁申请，实习申请
	 */
	public long[] getRepairAndPracCount(String semester);
	
	/*
	  参数说明：bid,整型，为基地编号	          
	 返回值：    List<RentAdd>，为RentAdd对象的集合
	 函数功能： 获得土地租赁历史
	 */
	public List<RentAdd> getRentAdd(int bid);
	
	/*
	  参数说明：userid,字符串型，为用户编号;lidList，土地编号集合;str,为申报的信息集合	          
	 返回值：    int型，申报成功为1，同一个人申请同一块土地多次，为0
	 函数功能：租赁申报， 申报成功为1，申报失败为0 
	 */
	public int submitApply(String userid,String lidList,String str);
	
	
	

}
