package com.base.dao;

import java.util.List;


import com.base.po.Majoraim;
import com.base.po.PlanList;


public interface PlanDao {
	
	/*
	 1.参数：userid,字符串型,代表用户的编号;   pageindex,为当前页数;size,为每页的条数;
	         order,排序列;   orderDir,为排序的顺序;   searchValue,为模糊查询的值.
	 2.返回值： PlanList,需要显示给用户的数据对象
	 3.函数功能：获取该用户所在学院的实习计划
	 */
	public PlanList getThisCollegePlan(String userid,int pageindex,int size,int order,String orderDir,String searchValue);

	/*
	 1.参数：str1,字符串型，为插入  班级安排_课程表 的多条记录所构成的字符串
	         str2,字符串型，为课程代码
	 2.返回值： 无返回值
	 3.函数功能：删除班级安排_课程表中课程代码为str2的记录，再插入新的记录
	 	 */
	public void updatePlan(String str1,String str2);
	
	/*
	 1.参数：id,整型,代表班级安排记录的主键值
	 2.返回值： 无返回值
	 3.函数功能：删除单条班级安排记录
	 */
	public void deleteClassPlan(int id);
	
	/*
	 1.参数：majorid,字符串型，代表专业代码
	 2.返回值： List<major_aim>,存放专业培训表多条记录的对象集合
	 3.函数功能：从专业培训表中获取特定专业的多个培训目的
	 */
	public List<Majoraim> getPlanAim(String majorid);
	
	/*
	 1.参数：coid,字符串型，代表学院编号
	 2.返回值： List<String>,教师的字符串集合
	 3.函数功能：从用户表中获取特定学院的老师姓名
	 */
	public List<String> getCollege_Teacher(String coid);
}
