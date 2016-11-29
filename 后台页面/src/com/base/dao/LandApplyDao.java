package com.base.dao;

import java.util.List;

import com.base.po.LandApply;
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
	   参数说明：la_id,整型，为土地申请记录编号；status，整型，为申请状态值
	   返回值：   无返回值
	   函数功能：根据土地申请记录编号改变该记录的状态值为指定的status
	 */
	public void updateStatus(int la_id,int status);
	
	/*
	   参数说明：lid,整型，为土地编号；formerStatus,整型，为原先状态值；status，整型，为现申请状态值
	   返回值：   无返回值
	   函数功能：将土地编号为lid且状态值为formerStatus的记录的状态值改为status
	 */
	public void updateStatus(int lid,int formerStatus,int status);
	
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
    
    
	/*
	  参数说明：la_id,整型，为申请记录编号；lid,整型，为土地编号	          
	 返回值：    无返回值
	 函数功能： 将申请土地编号为lid但不为此la_id的记录拒绝(即申请失败)
	  */
	public void updateOthers(int la_id,int lid);
	
	
	
	public void updateLandApplyDate(String sql);
	
	
	public List<Startplan> getLandApplyDate();
	
	public Startplan getStartPlan(String id);

}
