package com.base.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("checkViewDao")
public class CheckViewDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

}
