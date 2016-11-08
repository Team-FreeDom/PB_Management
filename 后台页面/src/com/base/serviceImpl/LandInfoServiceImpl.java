package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.LandInfoDaoImpl;
import com.base.daoImpl.LandLayoutDaoImpl;
import com.base.po.LandLayout;
import com.base.service.LandInfoService;

@Service("landInfoService")
public class LandInfoServiceImpl implements LandInfoService {

	@Autowired
	private LandInfoDaoImpl landInfoDaoImpl;
	@Autowired
	private LandLayoutDaoImpl landLayoutDaoImpl;
	
	@Override
	public void addLandLayout(List<LandLayout> list) {
		for(LandLayout lly:list)
		{
			landLayoutDaoImpl.doLandLayout(lly);
		}

	}

	@Override
	public void updateLandLayout(List<LandLayout> list) {
		
		for(LandLayout lly:list)
		{
			landLayoutDaoImpl.updateLandLayout(lly);
		}
	}

	@Override
	public void deleteLandLayout(int id) {
		landLayoutDaoImpl.delLandLayout(id);

	}

}
