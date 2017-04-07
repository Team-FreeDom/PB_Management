package com.base.dao;

import java.util.List;

import com.base.po.BaseInfo;
import com.base.po.Classcourse;
import com.base.po.Majoraim;
import com.base.po.PlanList;
import com.base.po.UserInfo;

//实习申请的数据交互层
public interface PlanDao {

	/*
     1.参数：userid,字符串型,代表用户的编号; pageindex,为当前页数;size,为每页的条数; order,排序列;
            orderDir,为排序的顺序; searchValue,为模糊查询的值. 
     2.返回值： PlanList,需要显示给用户的数据对象
     3.函数功能：根据用户编号获取该用户所在学院的实习计划
     */
    public PlanList getThisCollegePlan(String userid, int pageindex, int size,
	    String columnName, String orderDir, String searchValue,String semester);

    /*
     1.参数：str1,整型，为课程表id; str3,字符串型，为插入 班级安排_课程表 的多条记录所构成的字符串 
     2.返回值： 无返回值
     3.函数功能：保存实习计划
     */
    public void updatePlan(int str1, String str3);

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
      1.参数：collagename,字符串型，代表学院名字 
      2.返回值： List<UserInfo>,教师的字符串集合
      3.函数功能：根据学院编号获取该学院的老师
     */
    public List<UserInfo> getCollege_Teacher(String collagename);

    /*
     * 1.参数：id,整型 ,为实习申请记录的编号
     * 2.返回值： List<Classcourse>,班级安排的数据集合
     * 3.函数功能：根据实习申请记录的编号获取班级安排数据集合
     */
    public List<Classcourse> plandata(int id);

    /*
    1.参数：plandata，为实习申请信息
    2.返回值： 无返回值
    3.函数功能：修改课程安排表
   */
    public void alterRecord(String plandata);
    
    /*
    1.参数：userid,用户id;semester,学年学期
    2.返回值： int型，记录数
    3.函数功能：获得该年该用户所处学院的实习记录条数
   */
    public int checkinfo(String userid,String semester); 
    
    /*
    1.参数：typename,字符串型，为基地类型
    2.返回值： List<String>，为基地名字的集合
    3.函数功能：根据基地类型名获得基地名称
   */
    public List<String> getProperBase(String typename);
}
