package com.base.service;

import java.util.List;

import com.base.po.AllPlan;
import com.base.po.PlanList;
import com.base.po.PracticeCollection;
import com.base.po.StartDate;

//实习计划管理的逻辑层
public interface PlanMaintainService {

	/*
	 1.参数：semester,字符串型,代表某一学年的某一学期，例：2016-2017-1; pageindex,为当前页数;
	         size,为每页的条数; order,排序列; orderDir,为排序的顺序; searchValue,为模糊查询的值. 
	 2.返回值：PlanList,需要显示给用户的数据对象 
	 3.函数功能：获取当年所有的实习计划
	 */
	public PlanList getPlanInfo(String semester, int pageindex, int size,
			int order, String orderDir, String searchValue);

	/*
	 1.参数：str,字符串型，为需要插入课程安排表的记录字符串 
	 2.返回值： 无返回值 
	 3.函数功能：增加一条实习计划
	 */
	public void addOnePlanInfo(String str);

	/*
	 1.参数：semester,字符串型,代表某一学年的某一学期，例：2016-2017-1.
	 2.返回值：List<AllPlan>，为导出表所需要的所有记录的对象集合 
	 3.函数功能：获取导出表所需的记录的对象集合
	 */
	public List<AllPlan> getPlanTable(String year, int semester, String college);

	/*
	  1.参数：semester,字符串型,代表某一学年,例：2016-2017; status,整型，修改位，0代表未完善，1代表已完善 
	  2.返回值：List<AllPlan>，为导出表所需要的所有记录的对象集合
	  3.函数功能：获取未完善的记录
	 */
	public PlanList checkIsSave(String semester, int status, int pageindex,
			int size, int order, String orderDir, String searchValue);

	/*
	 1.参数：recordstr,字符串型，为多个记录编号整合所构成的字符串 
	 2.返回值：无返回值 
	 3.函数功能：删除实习计划记录
	 */
	public void deletePlanInfo(String recordstr);

	/*
	  1.参数：recordstr,字符串型，为多个记录编号整合所构成的字符串
	  2.返回值：无返回值 
	  3.函数功能：提醒用户完善实习计划
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
	 1.参数：semester,为学年学期，cid,整型，为课程代码
	 2.返回值：int,0代表没有该课程代码,1代表存在该课程代码
	 3.函数功能：判断是否该学期是否存在该课程代码
	 */
	public int checkIsCid(String semester, String cid);

	/*
	 1.参数：无参 
	 2.返回值：List<String>
	 3.函数功能：获取实习计划的学院
	 */
	public List<String> getPlanCollege(String semester);

	/*
	 1.参数：semester,字符串，为学年学期;str,字符串，为课程表记录的插入语句 
	 2.返回值：无参
	 3.函数功能：删除原来学年学期的记录
	 */
	public void delete_0(String semester);
	
	/*
	 1.参数：str,字符串型，为实习计划记录的封装
	 2.返回值：无返回值
	 3.函数功能： 插入实习计划
	 */
	public void add_0(String str);
	
	/*
	 1.参数：学年
	 2.返回值：List<String>，为学期的集合
	 3.函数功能： 根据学年获取学期
	 */
	public List<String> getSem(String semester);
	
	/*
	 1.参数：year,字符串型，为学年;semester,字符串型，为学期
	 2.返回值：List<String>，为学院的集合
	 3.函数功能： 根据学年学期获取学院
	 */
	public List<String> getCollegehh(String year, int semester);

	/*
	 1.参数：semester,字符串型，为学期学年;startTime,字符串型,为该学期的第一周时间
	 2.返回值：无返回值
	 3.函数功能： 插入该学年学期的第一周时间
	 */
	public void addStartDate(String semester, String startTime);	

	/*
	 1.参数：无参数
	 2.返回值：无返回值
	 3.函数功能： 获取各学年学期的开始时间
	 */
	public List<StartDate> getStartDate();
	
	/*
	 1.参数：id,整型，为实习申请记录的编号;plandata,字符串型,为实习申请记录修改后的信息
	 2.返回值：无返回值
	 3.函数功能： 修改实习申请计划
	 */
    public void alterRecord(int id,String plandata);

	/*
	 1.参数：year,字符串型,为学年;semester,字符串型，为学期;college,字符串型，为学院
	 2.返回值：List<PracticeCollection>，为PracticeCollection对象的集合
	 3.函数功能： 获取导出的信息
	 */
    public List<PracticeCollection> getPlanTable_0(String year, int semester,
			String college);
}
