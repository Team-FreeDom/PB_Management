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
   /**
    * 修改个人信息
    */
	@Override
	public void update(String id,String name,String telephone,String password,String img)throws SQLException
	{
		userInfodao.updateuser(id, name, telephone, password, img);
		
	}
	/**
     * 获取个人信息
     * @param id 用户id
     * @return 用户信息
     */
	 public List<UserInfo> getInfoPerson(String id){
		 List<UserInfo> list=userInfodao.getInfoPerson(id);
		return list;
	 }

}
