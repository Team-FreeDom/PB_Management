package com.base.serviceImpl;

import org.springframework.stereotype.Service;

import com.base.po.MaintainList;
import com.base.service.RepairApproveService;

@Service("repairApproveService")
public class RepairApproveServiceImpl implements RepairApproveService {

	@Override
	public MaintainList getNoRepair(int pageIndex, int size, int order,
			String orderDir, String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MaintainList getAgreeRepair(int pageIndex, int size, int order,
			String orderDir, String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MaintainList getChooseRepair1(String baseid, String userid,
			String date, int pageIndex, int size, int order, String orderDir,
			String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MaintainList getChooseRepair2(String baseid, String userid,
			String date, int pageIndex, int size, int order, String orderDir,
			String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refuseRepairApply() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agreeRepairApply() {
		// TODO Auto-generated method stub
		
	}

}
