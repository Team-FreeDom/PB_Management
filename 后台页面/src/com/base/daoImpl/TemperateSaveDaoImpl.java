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

import com.base.po.LandApply;
import com.base.po.LandApply_view;
import com.base.po.TemperateSave;
import com.base.po.TemperateSave_View;
import com.base.utils.SqlConnectionUtils;

@Repository("temperateSaveDao")
public class TemperateSaveDaoImpl {
	
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<TemperateSave_View> getTemperate(String applicantId,String date)
	{
		Session session=sessionFactory.openSession();		
		String hql="from TemperateSave_View where applicantId=? and (year(startTime)<=? and year(endTime)>=?)";		
		List<TemperateSave_View> lp=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, applicantId);
	    	 query.setString(1, date);
	    	 query.setString(2, date);
	    	 lp=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}		
		
		return lp;
	}
	
	
	
	public void doTemperate(TemperateSave ts) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.save(ts);
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

	
	public void updateTemperate(TemperateSave ts) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.update(ts);
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
	
	public void delTemperate(int la_id) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
			 TemperateSave ts=(TemperateSave) session.get(TemperateSave.class, la_id);
	    	 session.delete(ts);
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
	
	public TemperateSave getTemperate(int la_id) {
		
		Session session=sessionFactory.openSession();		
		String hql="from TemperateSave where la_id=?";		
		TemperateSave ts=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, la_id);	    	 
	    	 ts=(TemperateSave) query.uniqueResult();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}		
		
		return ts;
	}
	
public List<TemperateSave_View> getTemperates(int la_id) {
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	
	Session session=sessionFactory.openSession();
	
	List<TemperateSave_View> lav=new ArrayList<TemperateSave_View>();
	
	TemperateSave_View lv=null;
	try
	{
		conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		sp= (CallableStatement) conn.prepareCall("{CALL baseweb.`temapply_detail`(?)}");
		sp.setInt(1, la_id);
		sp.execute();   //执行存储过程
		rs=sp.getResultSet();  //获得结果集			
		
		while(rs.next())
		{
			lv=new TemperateSave_View();
			lv.setAfford(rs.getInt("afford"));
			lv.setApplicantId(rs.getString("applicantId"));
			lv.setAptPlanting(rs.getString("aptPlanting"));
			lv.setBname(rs.getString("bname"));
			lv.setBuildingArea(rs.getInt("buildingArea"));
			lv.setCollege(rs.getString("college"));
			lv.setDescp(rs.getString("descp"));
			lv.setEndTime(rs.getString("endTime"));
			lv.setLa_id(rs.getInt("la_id"));
			lv.setLandArea(rs.getInt("landArea"));
			lv.setLid(rs.getString("lid"));
			lv.setLname(rs.getString("lname"));
			lv.setName(rs.getString("name"));
			System.out.println("haha"+rs.getString("name"));
			lv.setPlanting(rs.getString("planting"));
			lv.setResource(rs.getString("resource"));			
			lv.setStartTime(rs.getString("startTime"));
			lv.setStatus(rs.getInt("status"));
			lv.setTenancy(rs.getInt("tenancy"));
			lv.setApplyDept(rs.getInt("deptid"));
			lav.add(lv);
		
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		SqlConnectionUtils.free(conn, sp, rs);			
	}		
	
	return lav;
	
	}



}
