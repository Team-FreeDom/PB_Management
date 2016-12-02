package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.base.daoImpl.AdminManageDaoImpl;
import com.base.po.Admin;
import com.base.po.AdminFunction;
import com.base.service.AdminManageService;


@Service("adminMangeService")
public class AdminManageServiceImpl implements AdminManageService {

	
	@Autowired
	private AdminManageDaoImpl adminManageDaoImpl;
	
	
	@Override
	public List<AdminFunction> getAdminFunctionInfos() {
		// TODO Auto-generated method stub
		List<AdminFunction> list=adminManageDaoImpl.getAdminFunctionInfos();
		return list;
	}


	@Override
	public List<Admin> getAdminInfos() {
		// TODO Auto-generated method stub
		List<Admin> list=adminManageDaoImpl.getAdminInfos();
		return list;
	}


	@Override
	public void setAdminFunction(String insertSql) {
		// TODO Auto-generated method stub
		adminManageDaoImpl.setAdminFunction(insertSql);
		return;
	}


	@Override
	public long getAdminValue(String userid) {
		// TODO Auto-generated method stub
		return adminManageDaoImpl.getAdminValue(userid);
		//return 0;
	}

}
