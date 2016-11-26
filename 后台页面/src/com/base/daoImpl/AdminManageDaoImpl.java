package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.AdminMangeDao;
import com.base.po.Admin;
import com.base.po.AdminFunction;
import com.base.po.UserInfo;

@Repository("adminManageDao")
public class AdminManageDaoImpl implements AdminMangeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AdminFunction> getAdminFunctionInfos() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from AdminFunction";
		List<AdminFunction> list = null;

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
	public List<Admin> getAdminInfos() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from Admin";
		List<Admin> list = null;

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
	public void setAdminFunction(String insertSql) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		int i = 0;
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Query q = session.createSQLQuery(insertSql);
			q.executeUpdate();
			t.commit();
		} catch (Exception ex) {
			if (t != null) {
				t.rollback();
			}
		} finally {
			session.close();
		}

		// 以下为批量插入
		/*
		 * if (adminList != null && adminList.size() > 0) { Transaction t =
		 * null; try {
		 * 
		 * t = session.beginTransaction(); Admin admin = null; // 创建药品对象 //
		 * 循环获取药品对象 for (int i = 0; i < adminList.size(); i++) { admin = (Admin)
		 * adminList.get(i); // 获取药品 session.save(admin); // 保存药品对象 //
		 * 批插入的对象立即写入数据库并释放内存 if (i % 100 == 0) { session.flush();
		 * session.clear(); } }
		 * 
		 * t.commit(); } catch (Exception ex) { if (t != null) { t.rollback(); }
		 * } finally { session.close(); } }
		 */

	}
}
