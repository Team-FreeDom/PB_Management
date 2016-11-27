package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.UserInfoDao;
import com.base.daoImpl.UserInfoDaoImpl;
import com.base.po.UserInfo;
import com.base.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoDaoImpl userInfoDaoImpl;
    @Autowired
	private UserInfoDao userInfodao;

	@Override
	public void delUser(String[] delid) {
		
		for(String id:delid)
		{
			userInfoDaoImpl.delUser(Integer.valueOf(id));
		}
	}

	public long login(String id,String pwd)
	{
		return userInfoDaoImpl.login(id, pwd);
	}
	
	@Override
	public void doUser(UserInfo ui) {
		
		userInfoDaoImpl.doUser(ui);

	}

	@Override
	public void updateUser(UserInfo ui) {
		
		userInfoDaoImpl.updateUser(ui);

	}

	@Override
	public List<UserInfo> getUserInfos() {
		
		List<UserInfo> list=userInfoDaoImpl.getUserInfos();
		return list;
		
	}

	@Override
	public List<UserInfo> getUserInfos(int userright, String sex, int id) {
		
		List<UserInfo> list=null;
		
		if(userright!=0&&!sex.isEmpty()&&id!=0)
		{
			list=userInfoDaoImpl.getUserInfo(userright, sex, id);
			
		}else if(userright!=0&&!sex.isEmpty())
		{
			list=userInfoDaoImpl.getUserInfo(userright, sex);
			
			
		}else if(!sex.isEmpty()&&id!=0)
		{
			list=userInfoDaoImpl.getUserInfos(sex, id);
			
		}else if(userright!=0&&id!=0)
		{
			list=userInfoDaoImpl.getUserInfo(userright, id);
			
		}else if(userright!=0)
		{
			list=userInfoDaoImpl.getUserInfos(userright);
			
		}else if(id!=0)
		{
			list=userInfoDaoImpl.getUserInfo(id);
			
		}else if(!sex.isEmpty())
		{
			list=userInfoDaoImpl.getUserInfos(sex);
		}
		
		return list;
	}
	
	 public String getImage(String userId)
		{
		 List<UserInfo> list=userInfodao.getInfoPerson(userId);
		//  UserInfo ui=userInfoDaoImpl.getUserInfo(userId);
			String image=list.get(0).getImg();
			
			return image;
			
		}

	@Override
	public void update(String id, String name, String telephone,
			String password, String img) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserInfo> getInfoPerson(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
