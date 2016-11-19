package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.po.LandApply;
import com.base.po.LandApply_view;
import com.base.po.TemperateSave;
import com.base.po.TemperateSave_View;

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
	
public TemperateSave_View getTemperates(int la_id) {
		
		Session session=sessionFactory.openSession();		
		String hql="from TemperateSave_View where la_id=?";		
		TemperateSave_View tsv=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, la_id);	    	 
	    	 tsv=(TemperateSave_View) query.uniqueResult();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}		
		
		return tsv;
	}



}
