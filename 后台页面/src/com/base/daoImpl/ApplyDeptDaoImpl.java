package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.po.ApplyDept;
import com.base.po.LandRentInfo;

@Repository("applyDeptDao")
public class ApplyDeptDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<ApplyDept> getDepts()
	{
		Session session=sessionFactory.openSession();		
		String hql="from ApplyDept where dept!=?";
		List<ApplyDept> list=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, "''");
	    	 list=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return list;
	}

}
