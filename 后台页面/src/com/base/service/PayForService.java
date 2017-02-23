package com.base.service;

//交费模块的业务逻辑层接口
public interface PayForService {

	/*
	  参数说明：la_id,整型，为申请记录编号；lid,整型，为土地编号	          
	 返回值：    无返回值
	 函数功能： 拒绝交费
	  */
	public void refusePay(int la_id,int lid);
	
	/*
	  参数说明：la_id,整型，为申请记录编号；lid,整型，为土地编号	          
	 返回值：    无返回值
	 函数功能： 交费成功
	  */
	public void paySuccess(int la_id,int lid);
}
