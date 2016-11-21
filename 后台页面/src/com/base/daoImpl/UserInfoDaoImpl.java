package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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
				tx.rollback();// 回滚事务，撤消查询语句
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
		// 获得connection对象
		long adminValue = -1;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = conn.prepareCall("{call baseweb.user_login(?,?,?)}"); // 调用存储过程
			sp.setString(1, id); // 向存储过程传递有参的参数(参数为字符串类型),1代表第一个参数
			sp.setString(2, pwd);
			sp.registerOutParameter(3, java.sql.Types.INTEGER);
			sp.execute(); // 执行存储过程
			adminValue = sp.getInt(3);
			// 最后调用工具类释放连接
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
				tx.rollback();// 回滚事务，撤消查询语句
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
				tx.rollback();// 回滚事务，撤消查询语句
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

}
