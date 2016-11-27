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
         	}
	 /**
	 * �޸ĸ�����Ϣ
	 */
	@Override
	public void update(String id, String name, String telephone,
			String password, String img) {
		userInfodao.updateuser(id, name, telephone, password, img);
		
	}
      /**
     * ��ȡ������Ϣ
     * @param id �û�id
     * @return �û���Ϣ
     */
	@Override
	public List<UserInfo> getInfoPerson(String id) {
		 List<UserInfo> list=userInfodao.getInfoPerson(id);
			return list;
	}
/**
     * �û�����
     * @param pageindex ��ǰҳ��
     * @param size      ��ǰ��ʾ������¼
     * @return
     * @throws SQLException
     */
		@Override
		public MangerList manger(int pageindex, int size,String searchValue) 
		{
			MangerList list=userInfodao.manger(pageindex,size,searchValue);
			return list;
		}
		/**
		 * 
		 * @param id �û�id
		 * @return �û�������Ϣ
		 * @throws SQLException
		 */
		@Override
		public List<Manger> Mangerdetail(String id) 
		{
			List<Manger> list=userInfodao.Mangerdetail(id);
			return list;
		}
		/**
		 * ɾ����Ա������Ϣ
		 * @param str Ϊ��Աid���ַ���
		 * @throws SQLException
		 */
		@Override
		public void deleteInfo(String str) 
		{
			userInfodao.deleteInfo(str);
			
		}
		/**
		 * �޸��û���Ϣ
		 * @param id
		 * @param name
		 * @param sex
		 * @param birthdate
		 * @param category
		 * @param attritube
		 * @param telephone
		 * @param idcard
		 * @param password
		 * @throws SQLException
		 */
		public void upInfo(String id, String name, String sex, String birthdate,
				String category, String attritube,String dept,String telephone, String idcard,
				String password) 
		{
			userInfodao.upInfo(id,name,sex,birthdate,category,attritube,dept,telephone,idcard,password);
			
		}
		  /**
		   * ���ż���
		   * @return
		  */
		    public List<ApplyDept> getDepts(){
		    	List<ApplyDept> list=userInfodao.getDepts();
				return list;
		    }
		    /**
		     * Admin����Ա���Լ���
		     * @return
		     */
		    public List<Admin> getAttritube(){
		    	List<Admin> list=userInfodao.getAttritube();
		    	return list;
		    }
		    /**
		     * �����û�
		     * @param id
		     * @param name
		     * @param sex
		     * @param birthdate
		     * @param category
		     * @param attritube
		     * @param dept
		     * @param telephone
		     * @param idcard
		     * @param password
		     */
			@Override
			public int  addInfo(String id, String name, String sex,
					String birthdate, String category, String attritube,
					String dept, String telephone, String idcard, String password)
			{
				int flag=userInfodao.addInfo(id, name, sex, birthdate, category, attritube, dept, telephone, idcard, password);
				return flag;
			}
			/**
		      * ������Ա��Ϣ
		      * @return
		     */
			@Override
			public List<Manger> exportPersonInfo(String dept)
			{
				 List<Manger> list=userInfodao.exportPersonInfo(dept);
				return list;
			}


}
