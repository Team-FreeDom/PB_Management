package com.base.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.base.po.MaintainApply;
import com.base.po.MaintainApplys;
import com.base.po.MaintainList;

//报修申请逻辑层
public interface MaintainApplyService {

	/*
	   参数说明：years,字符串型，为年份
	   返回值：   List，存放基地列表
	   函数功能：获取维修管理记录里存在的基地列表
	 */	
	public List find_basename(String year);
	
	/*
	   参数说明：str，为报修申请信息的封装;infostr，字符串型，为要发送的信息
	   返回值：   无返回值
	   函数功能：增加报修申请信息记录，并发送信息给申请人
	 */
	public void insert_maintain(String str,String infostr);
	
	/*
	   参数说明：page,为当前页数;offsets,为每页的条数; order,排序列;
               str1,为排序的顺序; str2,为模糊查询的值. 
	   返回值：   MaintainList,包括报修信息记录的集合和记录条数
	   函数功能：获取报修信息记录
	 */
	public MaintainList query_maintainapply(int offsets,int page,int order,String str1,String str2);
	
	/*
	   参数说明：str,为要删除的报修信息记录的编号的封装
	   返回值：   无返回值
	   函数功能：删除报修信息
	 */
	public void delete_maintainapply(String str);
	
	/*
	   参数说明：ma，为MaintainApply对象
	   返回值：   无返回值
	   函数功能：增加报修信息记录
	 */
	public void insert_maintainhistory(MaintainApply ma);
	
	/*
	   参数说明：bname,字符串型，为基地名称;years,int型，为年份
	   返回值：   List<MaintainApplys>，为MaintainApplys对象的集合
	   函数功能：获取导出的报修信息数据
	 */
	public List<MaintainApplys> export_maintainapply(String bname,int years);
	
	/*
	   参数说明：无参数值
	   返回值：   List<String>，为年份的集合
	   函数功能：获取报修信息记录存在的年份
	 */
	public List<String> getThoseYear();
	
	/*
	   参数说明：无参数
	   返回值：    List<Map<String,String>>，为基地名字的集合
	   函数功能：获取校内基地名称
	 */
	public List<Map<String,String>> find_basenamenei();
	
}
