package com.base.service;

import java.util.List;

import com.base.po.MaintainList;

public interface RepairApproveService {

	public MaintainList getRepairInfo(String baseid,String userid,int pageIndex,int size,int order,String orderDir,String searchValue,int status);	
	
	public void refuseRepairApply(String refusestr,String infostr);
	
	public void agreeRepairApply(String agreestr,String infostr);
	
	public List getInfoApply();
	
	public void finishRepairApply(String storestr,String infostr);
	
}
