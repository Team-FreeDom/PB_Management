package com.base.dao;

import java.util.List;
import java.util.Map;

import com.base.po.ApplyDept;
import com.base.po.MaintainList;

public interface RepairApproveDao {

	public MaintainList getRepairInfo(String baseid, String userid,	int pageIndex, int size, String orderColumn, String orderDir,
			String searchValue, int status);
	
	public List<Map<String,String>> getUser(int status);
	
	public List<Map<String,String>> getBase(int status);
}
