package com.base.service;

import com.base.po.MaintainList;

public interface RepairApproveService {

	public MaintainList getNoRepair(int pageIndex,int size,int order,String orderDir,String searchValue);
	
	public MaintainList getAgreeRepair(int pageIndex,int size,int order,String orderDir,String searchValue);
	
	public MaintainList getChooseRepair1(String baseid,String userid,String date,int pageIndex,int size,int order,String orderDir,String searchValue);
	
	public MaintainList getChooseRepair2(String baseid,String userid,String date,int pageIndex,int size,int order,String orderDir,String searchValue);
	
	public void refuseRepairApply();
	
	public void agreeRepairApply();
	
	
}
