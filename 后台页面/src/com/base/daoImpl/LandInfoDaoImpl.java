package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
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

import com.base.dao.LandInfoDao;
import com.base.po.BaseInfo;
import com.base.po.LandInfo;
import com.base.po.Land_base;
import com.base.utils.SqlConnectionUtils;

@Repository("landInfoDao")
public class LandInfoDaoImpl implements LandInfoDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void updateSpareValue(int lid,int spareValue) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 LandInfo li=(LandInfo) session.get(LandInfo.class,lid);
	    	 li.setSpareValue(lid);
	    	 session.update(li);
	    	 tx.commit();
	    	
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();// 回滚事务，撤消查询语句
			}
			System.out.println(e);
		}finally{
			session.close();
		}

	}
	
	public List<Land_base> getView(String lid)
	{
		Session session=sessionFactory.openSession();		
		String hql="from Land_base where lid=?";		
		List<Land_base> li=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, lid);
	    	 li=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return li;
	}
	
	public List<Land_base> getlandbase(int bid)
	{
		Session session=sessionFactory.openSession();		
		String hql="from Land_base where bid=?";		
		List<Land_base> li=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, bid);
	    	 li=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return li;
	}

	@Override
	public List<LandInfo> getLandInfo(String lid) {
		Session session=sessionFactory.openSession();		
		String hql="from LandInfo where lid=?";		
		List<LandInfo> li=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, lid);
	    	 li=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return li;
	}

	

	@Override
	public List<LandInfo> getLandInfos(int bid) {
		Session session=sessionFactory.openSession();		
		String hql="from LandInfo where bid=?";		
		List<LandInfo> list=null;
		
		 try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, bid);
	    	 list=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		// System.out.println(bid+list.get(0).getAfford());
		return list;
	}
	
	public void deleteInfo(LandInfo li) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();	    	
	    	 session.delete(li);
	    	 tx.commit();
	    	
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();// 回滚事务，撤消查询语句
			}
			System.out.println(e);
		}finally{
			session.close();
		}

	}
	
	public void doInfo(LandInfo li) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();	    	
	    	 session.save(li);
	    	 tx.commit();
	    	
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();// 回滚事务，撤消查询语句
			}
			System.out.println(e);
		}finally{
			session.close();
		}

	}
	public List<LandInfo> deletelandimg(int bid)
	{
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		Session session=sessionFactory.openSession();
		List list=new ArrayList<LandInfo>();
		LandInfo la=null;
		try
		{
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.`findresource`(?)}");
			sp.setInt(1, bid);
			sp.execute();   //执行存储过程
			rs=sp.getResultSet();  //获得结果集
			while(rs.next())
			{
				la=new LandInfo();
				la.setImg(sp.getString("img"));
				list.add(la);
			}

		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			SqlConnectionUtils.free(conn, sp, rs);			
		}
		return list;

	}
	
	public void delLayout_info(int bid)
	{
		Session session=sessionFactory.openSession();		
		//hibernate调用存储过程(无返回参数)
		SQLQuery sqlQuery =session.createSQLQuery("{call baseweb.`delete_land`(?)}");
		sqlQuery.setInteger(0, bid);		
		sqlQuery.executeUpdate();
		session.close();		
	}
	
	public void doLayout_info(String landinfoStr,String layoutStr)
	{
		Session session=sessionFactory.openSession();		
		//hibernate调用存储过程(无返回参数)
		SQLQuery sqlQuery =session.createSQLQuery("{CALL baseweb.insert_land(?,?)}");
		sqlQuery.setString(0, landinfoStr);	
		sqlQuery.setString(1, layoutStr);	
		sqlQuery.executeUpdate();
		session.close();		
	}

}
