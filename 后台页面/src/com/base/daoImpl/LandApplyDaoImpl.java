package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.LandApplyDao;
import com.base.po.College;
import com.base.po.LandApply;
import com.base.po.RentAdd;
import com.base.po.RentCollection;
import com.base.po.RentMaintain;
import com.base.utils.SqlConnectionUtils;

@Repository("landApplyDao")
public class LandApplyDaoImpl implements LandApplyDao {

	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public void doLandApply(LandApply la) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.save(la);
	    	 tx.commit();
	    	
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();// �ع����񣬳�����ѯ���
			}
			System.out.println(e);
		}finally{
			session.close();
		}

	}
	
	public void delLandApply(LandApply la) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.delete(la);
	    	 tx.commit();
	    	
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();// �ع����񣬳�����ѯ���
			}
			System.out.println(e);
		}finally{
			session.close();
		}

	}
	
	public LandApply getapply(int la_id)
	{
		Session session=sessionFactory.openSession();		
		String hql="from LandApply where la_id=?";
		LandApply la=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, la_id);
	    	 la=(LandApply) query.uniqueResult();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return la;
	}
	
	public List<LandApply> getUserApplys(String lid,int status)
	{
		Session session=sessionFactory.openSession();		
		String hql="from LandApply where lid=? and status=?";
		List<LandApply> list=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, lid);
	    	 query.setInteger(1, status);
	    	 list=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return list;
	}
	
	public void updateLandApply(LandApply la)
	{
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.update(la);
	    	 tx.commit();
	    	
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();// �ع����񣬳�����ѯ���
			}
			System.out.println(e);
		}finally{
			session.close();
		}
	}

	@Override
	public void updateStatus(int la_id, int status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStatus(int lid, int formerStatus, int status) {
		

	}

	@Override
	public List<LandApply> getUserApplys(String applicantId) {
		Session session=sessionFactory.openSession();		
		String hql="from LandApply where applicantId=?";
		List<LandApply> list=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, applicantId);
	    	 list=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return list;
		
	}

	@Override
	public List<LandApply> getLandApplys() {
		Session session=sessionFactory.openSession();		
		String hql="from LandApply";
		List<LandApply> list=null;
		
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

	@Override
	public void updateOthers(int la_id, int lid) {
		// TODO Auto-generated method stub

	}
	
	public List<RentCollection> getRentCollection(int bid)
	{			
		List<RentCollection> list=new ArrayList<RentCollection>();
		RentCollection rc=null;		
		List<RentAdd> lra=getRentAdd(bid);
		
	   try {
		conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		sp= (CallableStatement) conn.prepareCall("{CALL baseweb.landinfos(?)}");
		sp.setInt(1,bid);		
		sp.execute();   //ִ�д洢����
		rs=sp.getResultSet();  //��ý����
			
		while(rs.next())    //�������������ֵ��list
		{
			rc=new RentCollection();
			
			rc.setId(rs.getString("lids"));
			rc.setX(rs.getInt("xs"));
			rc.setY(rs.getInt("ys"));
			rc.setWidth(rs.getInt("widths"));
			rc.setHeight(rs.getInt("heights"));
			rc.setBid(rs.getInt("bids"));
			rc.setLname(rs.getString("landname"));
			rc.setPlantingContent(rs.getString("aptplant"));
			rc.setLandArea(rs.getInt("landareas"));
			rc.setBuildingArea(rs.getInt("buildarea"));			
			rc.setAfford(rs.getInt("affords"));		
			rc.setCollage(rs.getString("depts"));
			rc.setName(rs.getString("username"));
			rc.setPlanting(rs.getString("plants"));
			rc.setLineup(rs.getInt("lineup"));
			List<RentAdd> lis=new ArrayList<RentAdd>();
			for(RentAdd ra:lra)
			{
				if(rs.getString("lids").equals(ra.getLid())){				
				lis.add(ra);
				}
			}
			rc.setData(lra);
			list.add(rc);
			
		}		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		SqlConnectionUtils.free(conn, sp, rs);
		
	}
		
		return list;
	}
	
	
	
	public List<RentAdd> getRentAdd(int bid)
	{
		RentAdd ra=null;
		List<RentAdd> lra=new ArrayList<RentAdd>();
		
		try {
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.landrentinfos(?)}");
			sp.setInt(1,bid);		
			sp.execute();   //ִ�д洢����
			rs=sp.getResultSet();  //��ý����
			
			while(rs.next())
			{
				ra=new RentAdd();
				ra.setLid(rs.getString("lids"));
				ra.setName(rs.getString("username"));
				ra.setPlanting(rs.getString("plants"));
				ra.setPtime(rs.getString("ptime"));
				lra.add(ra);
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnectionUtils.free(conn, sp, rs);			
		}			
			return lra;		
	}
	
	
	
	public Long getApplyCount(String date)
	{
		System.out.println(date);	
		Long applyCount=(long) 0;		
		
		Session session = sessionFactory.openSession();			
        String hql="select count(*) from LandApply where year(startTime)<=? and year(endTime)>=? and status in(?,?,?)";
		try {			
			Query query=session.createQuery(hql);
			query.setString(0, date);
	    	query.setString(1, date);
	    	query.setInteger(2, 1);
	    	query.setInteger(3, 2);
	    	query.setInteger(4, 3);
			applyCount=(Long) query.uniqueResult();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		//System.out.println(applyCount);
		
		return applyCount;
	}
	
	public void submitApply(String userid,String str)
	{
				
		Session session=sessionFactory.openSession();		
		//hibernate���ô洢����(�޷��ز���)
		SQLQuery sqlQuery =session.createSQLQuery("{CALL baseweb.insert_landapply(?,?)}");
		sqlQuery.setString(0, userid);	
		sqlQuery.setString(1, str);	
		sqlQuery.executeUpdate();
		session.close();		
	}
}
