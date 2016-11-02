package com.base.service;

import java.util.List;

import com.base.po.CheckView;
import com.base.po.LandApply;

//审核模块业务逻辑层接口
public interface checkService {
	
	/*
	   参数说明：status,整型，为申请记录的状态值
	   返回值：   List<CheckView>,为土地申请视图CheckView对象的集合
	   函数功能：查询所有土地申请视图记录(获得的记录按申请时间，默认为倒序)
	 */
	public List<CheckView> getLandApplys(int status);
	

	/*
	   参数说明：无参
	   返回值：   List<CheckView>,为土地申请视图CheckView对象的集合
	   函数功能：查询所有土地申请记录(获得的记录按申请时间，默认为倒序)
	 */
	public List<CheckView> getLandApplys();
	
	/*
	  参数说明：la_id,整型，为申请记录编号；lid,整型，为土地编号	          
	 返回值：    无返回值
	 函数功能： 同意申请(将申请记录编号为la_id的记录状态变为"交费中"即1，同时将土地编号
	                     为lid且状态为申请中的记录状态变为"锁定"即4)
	          当状态设为交费中，启动数据库定时器，检测是否超过固定期限，超过期限，则将la_id记录设为未交费
	  */
	public void agreeApply(int la_id,int lid);
	
	/*
	  参数说明：la_id,整型，为申请记录编号；lid,整型，为土地编号	          
	 返回值：    无返回值
	 函数功能： 拒绝他人(将土地编号为lid(除了la_id的这条记录)的记录状态为申请中即2的变为"管理员拒绝"即8)
	  */
	public void refuseOthers(int la_id,int lid);	
	
	/*
	   参数说明：bname,字符串型，为基地名称
	           lid,整型，为土地编号
	           college，字符串型，为学院名称
	           userCount,整型，为租赁个数
	   返回值：  List<CheckView>,为土地申请视图CheckView对象的集合 
	   函数功能：根据条件查询获取土地申请视图信息
	   注：           6个参数是联合查询，但前五个参数也可能存在没有值的情况
	                     因此在此需要进行判断参数是否有值，根据结果调用dao层的方法
	                     在此规定，从上层传来的lid和usercount值若为0，则代表此两参数无值
	 */
	public List<CheckView> getLandApplys(String bname,int lid,String college,int userCount,String planting,int status);
	


}
