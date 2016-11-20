package com.base.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.dao.BaseInfoDao;
import com.base.po.BaseInfo;
import com.base.po.College;

@Repository("baseInfoDao")
public class BaseInfoDaoImpl implements BaseInfoDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<BaseInfo> getBaseInfos() {
		
		Session session=sessionFactory.openSession();		
		String hql="from BaseInfo";		
		List<BaseInfo> list=null;
		
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
