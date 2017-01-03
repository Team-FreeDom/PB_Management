package com.base.service;

import java.util.List;

import com.base.po.MaintainList;

public interface RepairApproveService {

	public MaintainList getRepairInfo(String baseid,String userid,int pageIndex,int size,int order,String orderDir,String searchValue,int status);	
	
	public void refuseRepairApply();
	
	public void agreeRepairApply();
	
	public List getInfoApply();
	
}
