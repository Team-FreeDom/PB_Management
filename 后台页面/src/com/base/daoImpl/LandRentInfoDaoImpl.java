package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.LandRentInfoDao;
import com.base.po.LandApply;
import com.base.po.LandRentInfo;

@Repository("landRentInfoDao")
public class LandRentInfoDaoImpl implements LandRentInfoDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void doLandRentInfo(LandRentInfo lr) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.save(lr);
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
	public List<LandRentInfo> getLandRentInfo(String userId) {
		Session session=sessionFactory.openSession();		
		String hql="from LandRentInfo where userId=?";
		List<LandRentInfo> list=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, userId);	    	
	    	 list=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<LandRentInfo> getLandRentInfos() {
		Session session=sessionFactory.openSession();		
		String hql="from LandRentInfo";
		List<LandRentInfo> list=null;
		
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

}
