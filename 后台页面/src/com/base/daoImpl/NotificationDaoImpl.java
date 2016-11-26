package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.NotificationDao;
import com.base.po.Admin;
import com.base.po.Message;
import com.base.po.Notification;

@Repository("notificationDao")
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void setNotification(String insertSql) {
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
	}

	@Override
	public Notification getNotificationInfo() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from Notification";
		List<Notification> notificationList = null;
        Notification notification = null;
		try {
			Query query = session.createQuery(hql);
			notificationList = query.list();
			notification =notificationList.get(0);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return notification;
		
	}

	@Override
	public void addMessage(String insertSql) {
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
	}

	@Override
	public List<Message> getMessageInfos(String userid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from Message where userid='"+userid+"'";
		List<Message> list = null;

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
