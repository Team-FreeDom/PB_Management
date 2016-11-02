package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.UserInfoDaoImpl;
import com.base.po.UserInfo;
import com.base.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoDaoImpl userInfoDaoImpl;

	@Override
	public void delUser(String[] id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUser(UserInfo ui) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(UserInfo ui) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserInfo> getUserInfos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> getUserInfos(int userright, String sex, int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
