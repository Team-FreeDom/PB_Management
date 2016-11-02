package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.LandInfoDao;
import com.base.po.BaseInfo;
import com.base.po.LandInfo;

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

	@Override
	public LandInfo getLandInfo(int lid) {
		Session session=sessionFactory.openSession();		
		String hql="from LandInfo where lid=?";		
		LandInfo li=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, lid);
	    	 li=(LandInfo) query.uniqueResult();
			
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
		return list;
	}

}
