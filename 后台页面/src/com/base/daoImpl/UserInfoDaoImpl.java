package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.UserInfoDao;
import com.base.po.LandLayout;
import com.base.po.UserInfo;
import com.base.utils.SqlConnectionUtils;

@Repository("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {

	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void delUser(int id) {
		Session session = sessionFactory.openSession();
		String hql = "from userinfo where id=?";
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(id);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();// �ع����񣬳�����ѯ���
			}
			System.out.println(e);
		} finally {
			session.close();
		}

	}

	public boolean login1(String id, String pwd) {
		Session session = sessionFactory.openSession();
		String hql = "from userinfo where id=? and password=?";
		boolean flag = false;

		try {
			Query query = session.createQuery(hql);
			query.setString(0, id);
			query.setString(1, pwd);
			UserInfo ui = (UserInfo) query.uniqueResult();
			if (ui != null) {
				flag = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return flag;
	}

	public long login(String id, String pwd) {
		// Session session=sessionFactory.openSession();
		CallableStatement sp=null;
		Connection conn = null;
		// ���connection����
		long adminValue = -1;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = conn.prepareCall("{call baseweb.user_login(?,?,?)}"); // ���ô洢����
			sp.setString(1, id); // ��洢���̴����вεĲ���(����Ϊ�ַ�������),1�����һ������
			sp.setString(2, pwd);
			sp.registerOutParameter(3, java.sql.Types.INTEGER);
			sp.execute(); // ִ�д洢����
			adminValue = sp.getInt(3);
			// �����ù������ͷ�����
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			SqlConnectionUtils.free(conn, sp, null);
		}
		return adminValue;
	}

	@Override
	public void doUser(UserInfo ui) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(ui);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();// �ع����񣬳�����ѯ���
			}
			System.out.println(e);
		} finally {
			session.close();
		}

	}

	@Override
	public void updateUser(UserInfo ui) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(ui);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();// �ع����񣬳�����ѯ���
			}
			System.out.println(e);
		} finally {
			session.close();
		}

	}

	@Override
	public List<UserInfo> getUserInfos() {
		Session session = sessionFactory.openSession();
		String hql = "from userinfo";
		List<UserInfo> list = null;

		try {
			Query query = session.createQuery(hql);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<UserInfo> getUserInfos(int userright) {
		Session session = sessionFactory.openSession();
		String hql = "from userinfo where userright=?";
		List<UserInfo> list = null;

		try {
			Query query = session.createQuery(hql);
			query.setInteger(0, userright);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<UserInfo> getUserInfos(String sex) {
		Session session = sessionFactory.openSession();
		String hql = "from UserInfo where sex=?";
		List<UserInfo> list = null;

		try {
			Query query = session.createQuery(hql);
			query.setString(0, sex);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<UserInfo> getUserInfo(int id) {
		Session session = sessionFactory.openSession();
		String hql = "from UserInfo where id=?";
		List<UserInfo> list = null;

		try {
			Query query = session.createQuery(hql);
			query.setInteger(0, id);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<UserInfo> getUserInfo(int userright, String sex) {
		Session session = sessionFactory.openSession();
		String hql = "from UserInfo where userright=? and sex=?";
		List<UserInfo> list = null;

		try {
			Query query = session.createQuery(hql);
			query.setInteger(0, userright);
			query.setString(1, sex);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<UserInfo> getUserInfo(int userright, int id) {
		Session session = sessionFactory.openSession();
		String hql = "from UserInfo where userright=? and id=?";
		List<UserInfo> list = null;

		try {
			Query query = session.createQuery(hql);
			query.setInteger(0, userright);
			query.setInteger(1, id);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<UserInfo> getUserInfos(String sex, int id) {
		Session session = sessionFactory.openSession();
		String hql = "from UserInfo where sex=? and id=?";
		List<UserInfo> list = null;

		try {
			Query query = session.createQuery(hql);
			query.setString(0, sex);
			query.setInteger(1, id);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<UserInfo> getUserInfo(int userright, String sex, int id) {
		Session session = sessionFactory.openSession();
		String hql = "from UserInfo where userright=? and id=? and sex=?";
		List<UserInfo> list = null;

		try {
			Query query = session.createQuery(hql);
			query.setInteger(1, userright);
			query.setInteger(1, id);
			query.setString(0, sex);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
	}
	
	public Long getUserCount() {
		
		Session session = sessionFactory.openSession();		
		Long userCount=(long) 0;
        String hql="select count(*) from UserInfo";
		try {			
			Query query=session.createQuery(hql);
			userCount=(Long) query.uniqueResult();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		System.out.println(userCount);
		return userCount;
	}
//�޸ĸ�����Ϣ
		public void updateuser(String id,String name,String telephone,String password,String img)
		{
			Connection conn = null;
			CallableStatement sp = null;
			ResultSet rs = null;
			try {
				conn=(Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
				sp=(CallableStatement) conn.prepareCall("{call baseweb.update_userinfo(?,?,?,?,?)}");
				sp.setString(1,id);
				sp.setString(2,name);
				sp.setString(3,telephone);
				sp.setString(4,password);
				sp.setString(5,img);
				sp.execute();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{			
				SqlConnectionUtils.free(conn, sp, rs);
			}
			
			
		}
	//��ȡ���� ��Ϣ
		public List<UserInfo> getInfoPerson(String id){
			Connection conn = null;
			CallableStatement sp = null;
			ResultSet rs = null;
			List<UserInfo> list=new ArrayList<UserInfo>();
			try{
			    conn=(Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
				sp=(CallableStatement) conn.prepareCall("{call baseweb.userdetail(?)}");
				sp.setString(1, id);
				sp.execute();		
				rs=sp.getResultSet();
				while(rs.next())
				{		
					UserInfo ch=new UserInfo();
					ch.setName(rs.getString("name"));
					ch.setTelephone(rs.getString("telephone"));
					ch.setImg(rs.getString("img"));
					list.add(ch);
				}
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{			
					SqlConnectionUtils.free(conn, sp, rs);
				}
				
				return list;
		}

public UserInfo getUserInfo(String userid)
	{
		Session session = sessionFactory.openSession();
		String hql = "from UserInfo where id=?";
		UserInfo ui = null;

		try {
			Query query = session.createQuery(hql);			
			query.setString(0, userid);
			
			ui = (UserInfo) query.uniqueResult();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return ui;
	}

}
