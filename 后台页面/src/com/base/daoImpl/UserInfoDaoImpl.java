package com.base.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.UserInfoDao;
import com.base.po.UserInfo;

@Repository("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {

  @Autowired
  private SessionFactory sessionFactory;
  
	@Override
	public void delUser(int id) {
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
	public List<UserInfo> getUserInfos(int userright) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> getUserInfos(String sex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> getUserInfo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> getUserInfo(int userright, String sex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> getUserInfo(int userright, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> getUserInfos(String sex, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> getUserInfo(int userright, String sex, int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
