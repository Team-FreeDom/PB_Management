package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.po.Land_base;
import com.base.po.Layout_InfoView;

@Repository("landLayout_infoDao")
public class LandLayout_infoDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Layout_InfoView> getlayout_info()
	{
		Session session=sessionFactory.openSession();		
		String hql="from Layout_InfoView";		
		List<Layout_InfoView> li=null;
		
		try {
	    	 Query query=session.createQuery(hql);	    	 
	    	 li=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return li;
	}
	
	public List<Layout_InfoView> getlayout_info(int bid)
	{
		Session session=sessionFactory.openSession();		
		String hql="from Layout_InfoView where bid=?";		
		List<Layout_InfoView> li=null;
		
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

}
