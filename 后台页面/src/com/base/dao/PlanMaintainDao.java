package com.base.dao;

import java.util.List;

import com.base.po.AllPlan;
import com.base.po.PlanList;

public interface PlanMaintainDao {

	/*
	 1.参数：semester,字符串型,代表某一学年的某一学期，例：2016-2017-1; pageindex,为当前页数;    size,为每页的条数;
	         order,排序列;   orderDir,为排序的顺序;   searchValue,为模糊查询的值.
	 2.返回值： PlanList,需要显示给用户的数据对象
	 3.函数功能：获取当年所有的实习计划
	 */
	public PlanList getPlanInfo(String semester,int pageindex,int size,String order,String orderDir,String searchValue);
	
	/*
	 1.参数：str,字符串型，为需要插入课程安排表的记录字符串
	 2.返回值： 无返回值
	 3.函数功能：向课程安排表中插入一条实习计划数据
	 */
	public void addPlanInfo(String str);
	
	/*
	 1.参数：semester,字符串型,代表某一学年的某一学期，例：2016-2017-1.
	 2.返回值： List<AllPlan>，为导出表所需要的所有记录的对象集合
	 3.函数功能：获取导出表所需的记录的对象集合
	 */
	public List<AllPlan> getPlanTable(String semester,String college);
	
	/*
	 1.参数：semester,字符串型,代表某一学年,例：2016-2017; status,整型，修改位，0代表未完善，1代表已完善
	 2.返回值： List<AllPlan>，为导出表所需要的所有记录的对象集合
	 3.函数功能：获取未完善的记录
	 */
	public PlanList checkIsSave(String semester,int status,int pageindex,int size,String order,String orderDir,String searchValue);
	
	
	/*
	 1.参数：recordstr,字符串型，为多个记录编号整合所构成的字符串
	 2.返回值：无返回值
	 3.函数功能：删除实习计划记录
	 */
	public void deletePlanInfo(String recordstr);
	
	/*
	 1.参数：recordstr,字符串型，为课程安排表多个记录编号整合所构成的字符串
	 2.返回值：无返回值
	 3.函数功能：向特定的记录所对应的教师发送提醒的信息
	 */
	public void callAttention(String recordstr);
	
	/*
	 1.参数：无参
	 2.返回值：List<String>
	 3.函数功能：获取学年学期
	 */
	public List<String> getSemester();

	/*
	 1.参数：id,整型，为用户的id
	 2.返回值：boolean
	 3.函数功能：判断是否存在该教师职工号
	 */
	public boolean checkIsUser(String id);
	
	/*
	 1.参数：aid,整型，为专业的编号
	 2.返回值：boolean
	 3.函数功能：判断是否存在该专业编号
	 */
	public boolean checkIsMid(String aid);
	
	/*
	 1.参数：无参
	 2.返回值：List<String>
	 3.函数功能：获取实习计划的学院
	 */
	public List<String> getPlanCollege(String semester);
	
	/*
	 1.参数：semester,字符串，为学年学期;str,字符串，为课程表记录的插入语句
	 2.返回值：无参
	 3.函数功能：删除原来学年学期的记录，并且执行插入语句
	 */
	public void deleteAndAdd(String semester,String str);
}
