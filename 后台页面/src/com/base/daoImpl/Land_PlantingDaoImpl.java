package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.po.LandInfo;
import com.base.po.Land_Planting;

@Repository("land_PlantingDao")
public class Land_PlantingDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Land_Planting> getPlanting(int bid){
		Session session=sessionFactory.openSession();		
		String hql="from Land_Planting where bid=?";		
		List<Land_Planting> lp=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, bid);
	    	 lp=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return lp;
		
	}
	
	public List<Land_Planting> getPlanting(int bid,String planting){
		Session session=sessionFactory.openSession();		
		String hql="from Land_Planting where bid=?and planting=?";		
		List<Land_Planting> lp=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, bid);
	    	 query.setString(1, planting);
	    	 lp=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return lp;
		
	}

}
