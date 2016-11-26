package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
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

import com.base.dao.NotificationDao;
import com.base.po.Admin;
import com.base.po.Message;
import com.base.po.Notification;
import com.base.utils.SqlConnectionUtils;

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

	@Override
	public List<Message> getMessageTop5Infos(String userid) {
		// TODO Auto-generated method stub
		/*Session session = sessionFactory.openSession();
		String hql = "from Message where userid='"+userid+"' order by time desc limit 0,4" ;
		System.out.println(hql);
		List<Message> list = null;

		try {
			Query query = session.createQuery(hql);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}*/
		CallableStatement sp=null;
		Connection conn = null;
		ResultSet rs = null;
		List<Message> list = new ArrayList<Message>() ;
		try {
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.find_message(?)}");  //发送存储过程
			sp.setString(1,userid);					
			sp.execute();   //执行存储过程

			rs=sp.getResultSet();  //获得结果集
			int i=0;
			
			while(rs.next())    //遍历结果集，赋值给list
			{
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setTitle(rs.getString("title"));
				message.setUserid(rs.getString("userid"));
				message.setTime(rs.getString("time"));
				message.setContent(rs.getString("content"));
			    message.setIsRead(rs.getInt("isRead"));
			    //System.out.println(message.getContent());
			    
				list.add(message);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnectionUtils.free(conn, sp, rs);			
		}			
		
		return list;
	}

	@Override
	public int getNoreadMessageCount(String userid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from Message where userid='"+userid+"' and isRead=0";
		List<Message> list = null;
        int number =0;
		try {
			Query query = session.createQuery(hql);
			list = query.list();
			if(list!=null)
				number=list.size();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return number;
	}
}
