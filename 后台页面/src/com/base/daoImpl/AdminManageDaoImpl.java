package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

}
