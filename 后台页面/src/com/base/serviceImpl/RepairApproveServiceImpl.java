package com.base.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.RepairApproveDao;
import com.base.po.MaintainList;
import com.base.service.RepairApproveService;

@Service("repairApproveService")
public class RepairApproveServiceImpl implements RepairApproveService {

	@Autowired
	private RepairApproveDao repairApproveDao;	
	

	@Override
	public void agreeRepairApply() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MaintainList getRepairInfo(String baseid, String userid,
			int pageIndex, int size, int order, String orderDir,
			String searchValue, int status) {
		String columnName="";
		if(order==2){
			columnName="basename";
		}else if(order==0){
			columnName="id";
		}else if(order==3){
			columnName="username";
		}else if(order==4){
			columnName="apply_time";
		}
		MaintainList list=repairApproveDao.getRepairInfo(baseid, userid, pageIndex, size, columnName, orderDir, searchValue, status);
		return list;
	}

	@Override
	public void refuseRepairApply() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getInfoApply() {
		
		List list=new ArrayList();
		List list1=repairApproveDao.getUser(13);
		List list2=repairApproveDao.getBase(13);
		List list3=repairApproveDao.getUser(14);
		List list4=repairApproveDao.getBase(14);
		list.add(list1);
		list.add(list2);
		list.add(list3);
		list.add(list4);
		return list;
	}

}
