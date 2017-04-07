package com.base.service;

import java.util.List;
import java.util.Map;

import com.base.po.Classcourse;
import com.base.po.PlanList;
import com.base.po.PracticeCollection;
import com.base.po.StatisticData;
import com.base.po.basetype;


public interface StatisticDataService {
    /*
     * 1.参数：userid,字符串型,代表用户的编号; pageindex,为当前页数;size,为每页的条数; order,排序列;
     * orderDir,为排序的顺序; searchValue,为模糊查询的值. 2.返回值： PlanList,需要显示给用户的数据对象
     * 3.函数功能：根据用户编号获取该用户所在学院的实习计划
     */
    public PlanList statisticData(int pageindex, int size, int order,
	    String orderDir, String semester);
    /**
     * 获取基地类型和学院
     * @return
     */
    public List<basetype> getDept(String semester);
    /**
     * 获取基地名字
     * @param semester
     * @param basename
     * @return
     */
    public List<Map<String, String>> getName(String semester, String basename);
    /**
     * 获得专业(通过学院和学年学期)
     * @param semester
     * @param college
     * @return
     */
    public List<Map<String, String>> getMajor(String semester, String college);
    /**
     * 刷选
     * @param pageindex
     * @param size
     * @param order
     * @param orderDir
     * @param string
     * @param basetype
     * @param basename
     * @param grade
     * @param college
     * @param major
     * @param class1
     * @return
     */
    public PlanList statisticDataBrush(String semester,
	    String basetype, String basename, String college,String grade, 
	    String major, String class1,int pageindex, int size,
	    int order, String orderDir);
    /**
     * 基地利用率
     * @param semester
     * @return
     */
    public StatisticData BaseUseRatio(String semester);
    /**
     * 基地利用率刷选
     * @param semester
     * @param basetype
     * @param basename
     * @param grade
     * @param college
     * @param major
     * @param class1
     * @param teacherName
     * @return
     */
    public StatisticData BaseUseRatioBrush(String semester, String basetype,
	    String basename, String grade, String college, String major,
	    String class1, String teacherName);
    
    /**
     * 获取导出的整体数据
     * @param pageindex
     * @param size
     * @param order
     * @param orderDir
     * @param string
     * @param basetype
     * @param basename
     * @param grade
     * @param college
     * @param major
     * @param class1
     * @return
     */
    public List<PracticeCollection> getExportstatisticData(String semester,
    	    String basetype, String basename, String college,String grade, 
    	    String major, String class1);
    
    //获得本学期的各基地类型的被实习基地
    public List<String> getstatisticCount(String semester);  
    
    //获得本学期的各基地类型的所有实习基地
    public List<String> getAllStatisticCount();  
    
    
}
