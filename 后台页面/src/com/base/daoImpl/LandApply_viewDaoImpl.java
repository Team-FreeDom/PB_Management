package com.base.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.po.LandApply_view;
import com.base.po.Land_Planting;

@Repository("landapply_viewDao")
public class LandApply_viewDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LandApply_view> getapplys(String applicantId)
	{
		Session session=sessionFactory.openSession();		
		String hql="from LandApply_view where applicantId=? and status in(?,?,?,?)";		
		List<LandApply_view> lp=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, applicantId);
	    	 query.setInteger(1, 3);
	    	 query.setInteger(2, 5);
	    	 query.setInteger(3, 6);
	    	 query.setInteger(4, 8);
	    	 lp=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}		
		
		return lp;
	}
	
	public List<LandApply_view> getapply(String applicantId,int i)
	{
		
		Session session=sessionFactory.openSession();		
		String hql="from LandApply_view where applicantId=? and status in(?,?)";		
		List<LandApply_view> lp=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, applicantId);
	    	 query.setInteger(1, 1);
	    	 query.setInteger(2, 2);	    	 
	    	 lp=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}		
		
		return lp;
	
	}
	
	public List<LandApply_view> getapply(String applicantId,String date)
	{
		
		Session session=sessionFactory.openSession();	
		System.out.println(date);
		String hql="from LandApply_view where applicantId=? and (year(startTime)<=? and year(endTime)>=?) order by status";		
		List<LandApply_view> lp=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, applicantId);
	    	 query.setString(1, date);
	    	 query.setString(2, date);	    	 
	    	 lp=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}		
		
		return lp;
	
	}
	
	public List<LandApply_view> getapplys(String applicantId,int status)
	{
		Session session=sessionFactory.openSession();		
		String hql="from LandApply_view where applicantId=? and status=?";		
		List<LandApply_view> lp=null;
		
		try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, applicantId);
	    	 query.setInteger(1, status);
	    	 lp=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return lp;
	}
	
	public List<LandApply_view> getapplys(int la_id)
	{
		Session session=sessionFactory.openSession();		
		String hql="from LandApply_view where la_id=?";		
		List<LandApply_view> lp=null;
		
		try {
	    	 Query query=session.createQuery(hql);	    	 
	    	 query.setInteger(0, la_id);
	    	 lp=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return lp;
	}
	
	public List<LandApply_view> getAllStudents(LandApply_view searchModel)
	{
		Session session=sessionFactory.openSession();
		List<LandApply_view> list=null;
		List<Object> paramList = new ArrayList<Object>();	
		
		
		String bname = searchModel.getBname();
		String startTime = searchModel.getStartTime();
		String endTime=searchModel.getEndTime();
		int lid=searchModel.getLid();
		String desc=searchModel.getDescp();
		StringBuilder hql = new StringBuilder("from LandApply_view where applicantId=? and status in(?,?,?)");
		
		paramList.add(searchModel.getApplicantId());
		paramList.add(String.valueOf(3));
		paramList.add(String.valueOf(5));
		paramList.add(String.valueOf(6));
		
		if (bname != null && !bname.equals("")) {
			hql.append(" and bname = ?");			
			paramList.add(bname);
		
		}

		if (startTime != null && !startTime.equals("")) {
			hql.append(" and year(startTime)=?");
			paramList.add(startTime);
			
		}
		
		if (endTime != null && !endTime.equals("")) {
			hql.append(" and year(endTime)=?");
			paramList.add(endTime);
			
		}
		
		if (lid!=0) {
			hql.append(" and lid=?");
			paramList.add(String.valueOf(lid));
			
		}
		
		if (desc != null && !desc.equals("")) { 
			if(desc.equals("…Í«Î≥…π¶"))
			{
			hql.append(" and descp=?");
			paramList.add(desc);
			}else if(desc.equals("…Í«Î ß∞‹")){
				hql.append(" and status in(?,?)");
				paramList.add(String.valueOf(3));
				paramList.add(String.valueOf(5));
			}
			
		}
		System.out.println(paramList.size());
		
		try{
			Query query=session.createQuery(hql.toString());
			if (paramList != null && !paramList.isEmpty()) {
				for (int i = 0; i < paramList.size(); i++) {	
					System.out.println("start");
					if(((String) paramList.get(i)).matches("[1-9]*")&&i!=0)
					{
						System.out.println("if");
						query.setInteger(i, Integer.valueOf((String) paramList.get(i)));
					}else{
						System.out.println("else");
						query.setString(i,(String) paramList.get(i));
					}
					System.out.println(i);
					System.out.println("end");
				}
			}
			
			list=query.list();
			
		}catch (Exception ex) {
			System.out.println(ex);
		} finally {
			session.close();
		}
		
		return list;
	}

}
