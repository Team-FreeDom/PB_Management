package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.LandRentInfoDaoImpl;
import com.base.po.LandRentInfo;
import com.base.po.RentMaintain;
import com.base.service.LandRentService;

@Service("landRentService")
public class LandRentServiceImpl implements LandRentService {

	@Autowired
	private LandRentInfoDaoImpl landRentInfoDaoImpl;
	
	@Override
	public void addLandRent(LandRentInfo lr) {
		landRentInfoDaoImpl.doLandRentInfo(lr);
	}

	@Override
	public List<LandRentInfo> getUserRentInfos(String userId) {
		List<LandRentInfo> list=landRentInfoDaoImpl.getLandRentInfo(userId);
		return list;
	}

	@Override
	public List<RentMaintain> getLandRentInfos(String bname,String deptName,String lid,String plantingContent) {
		
		List<RentMaintain> list=landRentInfoDaoImpl.getRentMaintain(bname, deptName, lid, plantingContent);
		return list;
		
	}
	
	
	

}
