package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.po.College;

@Repository("collegeDao")
public class CollegeDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<College> getColleges()
	{		
		Session session=sessionFactory.openSession();		
		String hql="from college";
		List<College> list=null;
		
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
