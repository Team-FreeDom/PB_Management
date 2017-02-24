package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.AdminMangeDao;
import com.base.po.Admin;
import com.base.po.AdminFunction;
import com.base.po.MessageShow;
import com.base.po.UserInfo;
import com.base.utils.SqlConnectionUtils;

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
	public void setAdminFunction(String sql) {
		// TODO Auto-generated method stub
		/*Session session = sessionFactory.openSession();

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
		}*/

		//实现批量插入
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			// 设置事务为非自动提交  
	        conn.setAutoCommit(false);
	        pst = conn.prepareStatement(""); 
			
			// 添加执行sql  
	        pst.addBatch(sql);  
	        // 执行操作  
	        pst.executeBatch();  
	        // 提交事务  
	        conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnectionUtils.free(conn, pst, null);
		}
		  
        

	}

	@Override
	public long getAdminValue(String userid) {
		// TODO Auto-generated method stub
		Connection conn=null;
		CallableStatement sp=null; 
		long adminValue = 0;
		try
		{
			conn = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();	
			sp = conn.prepareCall("{CALL baseweb.find_upow(?,?)}");  //调用存储过程
			sp.setString(1, userid);
			sp.registerOutParameter(2,java.sql.Types.INTEGER);
			sp.execute(); // 执行存储过程 
			adminValue=sp.getInt(2);//接收输出参数
		
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, null);
		}

		return adminValue;
	}
}
