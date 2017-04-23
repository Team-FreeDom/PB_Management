package com.base.service;

import java.util.List;

import com.base.po.BaseInfo;
import com.base.po.LandApply;
import com.base.po.LandInfo;
import com.base.po.LandLayout;
import com.base.po.Startplan;


//土地申请表的业务逻辑层接口
public interface LandApplyService {
	
	/*
	   参数说明：baseType,整型，表示基地类型，可取三个值：1(土地)，2(校内)，3(校外)
	   返回值：   List<BaseInfo>,为基地信息BaseInfo对象的集合
	   函数功能：查询六大基地的信息
	 */
	public List<BaseInfo> getBaseInfos();

	/*
	   参数说明：lid,整型，表示土地编号
	   返回值：   LandInfo,为土地管理LandInfo对象
	   函数功能：查询土地信息
	 */
	public List<LandInfo> getLandInfo(String lid);
	
	/*
	   参数说明：la,为土地申请LandApply对象
	   返回值：   无返回值
	   函数功能：提交土地申请信息
	 */
	public void addLandApply(LandApply la);
	
	/*
	   参数说明：applicantId,字符串型，为申请人的Id
	   返回值：   List<LandApply>,为土地申请LandApply对象的集合
	   函数功能：查询用户个人的土地申请记录
	 */
	public List<LandApply> getUserApplys(String applicantId);
	
	/*
	   参数说明：applicantId,字符串型，为申请人的Id
	   返回值：   无返回值
	   函数功能：修改土地申请信息
	 */
	public void updateUserApply(LandApply la);
	
	
	/* 
	   参数说明：lid,整型，表示土地编号
	   返回值：    整型，返回的是土地空闲值
	   函数功能：根据土地编号查询该土地的土地空闲值，以此在前台判断月份是否有效
	   注：           由于用户选择的月份可能是不连续的，因此存时间段不易获取有效
	                     空闲的时间月份，此处采用土地空闲值的方法.   	
	                     计算原理：1.土地空闲值的初始默认值为2^1+2^2+2^3+...+2^12(代表12个月均有效)
	                    2.当用户租赁了3，4，5三个月份的某一土地时，该土地的值为
	                      2^1+2^2+...+2^12-2^3-2^4-2^5
	                    3.判断某一月份是否空闲：spareValue与2^(月份值)相与，值为2^(月份值),则
	                                            该月份空闲
 */
	public int getSpareValue(String lid);
	
	/*
	   参数说明：la_id,整型，为申请记录编号
	   返回值：    无返回值
	   函数功能：取消申请
	 */
	public void cancelApply(int la_id);
	
	public void updateLandApplyDate(Startplan sp);
	
	public List<Startplan> getLandApplyDate();
	
	public Startplan getStartPlan(String id);

}
 