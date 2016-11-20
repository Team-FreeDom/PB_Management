package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.LandLayoutDao;
import com.base.po.LandApply;
import com.base.po.LandInfo;
import com.base.po.LandLayout;

@Repository("landLayoutDao")
public class LandLayoutDaoImpl implements LandLayoutDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void doLandLayout(LandLayout layout) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.save(layout);
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
	public void updateLandLayout(LandLayout layout) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.update(layout);
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
	public void delLandLayout(String id) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
			 LandLayout lla=(LandLayout) session.get(LandLayout.class, id);
	    	 session.delete(lla);
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
	
	public List<LandLayout> getLayout(int bid)
	{
		Session session=sessionFactory.openSession();		
		String hql="from LandLayout where bid=?";
		List<LandLayout> list=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, bid);	    	
	    	 list=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return list;
	}
	
	public void delLandLayout(LandLayout ll) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.delete(ll);
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

}
