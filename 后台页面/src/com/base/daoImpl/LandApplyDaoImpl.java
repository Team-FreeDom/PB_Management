package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.LandApplyDao;
import com.base.po.College;
import com.base.po.LandApply;

@Repository("landApplyDao")
public class LandApplyDaoImpl implements LandApplyDao {

	
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public void doLandApply(LandApply la) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.save(la);
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
	
	public void delLandApply(LandApply la) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.delete(la);
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
	
	public LandApply getapply(int la_id)
	{
		Session session=sessionFactory.openSession();		
		String hql="from LandApply where la_id=?";
		LandApply la=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, la_id);
	    	 la=(LandApply) query.uniqueResult();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return la;
	}
	
	public List<LandApply> getUserApplys(int lid,int status)
	{
		Session session=sessionFactory.openSession();		
		String hql="from LandApply where lid=? and status=?";
		List<LandApply> list=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, lid);
	    	 query.setInteger(1, status);
	    	 list=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return list;
	}
	
	public void updateLandApply(LandApply la)
	{
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.update(la);
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

	@Override
	public void updateStatus(int la_id, int status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStatus(int lid, int formerStatus, int status) {
		

	}

	@Override
	public List<LandApply> getUserApplys(String applicantId) {
		Session session=sessionFactory.openSession();		
		String hql="from LandApply where applicantId=?";
		List<LandApply> list=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, applicantId);
	    	 list=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return list;
		
	}

	@Override
	public List<LandApply> getLandApplys() {
		Session session=sessionFactory.openSession();		
		String hql="from LandApply";
		List<LandApply> list=null;
		
	    try {
	    	 Query query=session.createQuery(hql);	    	
	    	 list=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public void updateOthers(int la_id, int lid) {
		// TODO Auto-generated method stub

	}

}
