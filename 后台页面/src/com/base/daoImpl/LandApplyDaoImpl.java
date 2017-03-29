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
import com.base.po.Admin;
import com.base.po.College;
import com.base.po.LandApply;
import com.base.po.RentAdd;
import com.base.po.RentCollection;
import com.base.po.RentMaintain;
import com.base.po.Startplan;
import com.base.utils.SqlConnectionUtils;

@Repository("landApplyDao")
public class LandApplyDaoImpl implements LandApplyDao {

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
				tx.rollback();// 回滚事务，撤消查询语句
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
	
	public LandApply getapply(int la_id,int tag1,int tag2)
	{
		Session session=sessionFactory.openSession();		
		String hql="from LandApply where la_id=? and status in (?,?)";
		LandApply la=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, la_id);
	    	 query.setInteger(1, tag1);	
	    	 query.setInteger(2, tag2);
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
				tx.rollback();// 回滚事务，撤消查询语句
			}
			System.out.println(e);
		}finally{
			session.close();
		}
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
	
	//根据基地编号bid获取土地布局+土地信息+土地租赁历史
	@Override
	public List<RentCollection> getRentCollection(int bid)
	{	
		
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		
		List<RentCollection> list=new ArrayList<RentCollection>();
		RentCollection rc=null;		
		List<RentAdd> lra=getRentAdd(bid);
		
	   try {
		conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		sp= (CallableStatement) conn.prepareCall("{CALL baseweb.landinfos(?)}");
		sp.setInt(1,bid);		
		sp.execute();   //执行存储过程
		rs=sp.getResultSet();  //获得结果集
			
		while(rs.next())    //遍历结果集，赋值给list
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
			rc.setTag(rs.getInt("tag"));
			rc.setAptCollege(rs.getString("aptCollege"));
			rc.setImg(rs.getString("img"));
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
	
	
	//获得土地租赁历史
	@Override
	public List<RentAdd> getRentAdd(int bid)
	{
		
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		
		RentAdd ra=null;
		List<RentAdd> lra=new ArrayList<RentAdd>();
		
		try {
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.landrentinfos(?)}");
			sp.setInt(1,bid);		
			sp.execute();   //执行存储过程
			rs=sp.getResultSet();  //获得结果集
			
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
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		
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
	
	@Override
	public int submitApply(String userid,String lidList,String str)
	{
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		
		int flag=0;
		try {
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.insert_landapply(?,?,?,?)}");
			sp.setString(1,userid);		
			sp.setString(2,lidList);
			sp.setString(3, str);
			sp.registerOutParameter(4,java.sql.Types.INTEGER);
			sp.execute();   //执行存储过程
			flag=sp.getInt(4);
			rs=sp.getResultSet();  //获得结果集
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnectionUtils.free(conn, sp, rs);			
		}			
	    
		return flag;
	}

	@Override
	public void updateLandApplyDate(Startplan sp) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
	
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.saveOrUpdate(sp);
			t.commit();
		} catch (Exception ex) {
			if (t != null) {
				t.rollback();
			}
		} finally {
			session.close();
		}

	}

	@Override
	public List<Startplan> getLandApplyDate() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from Startplan where id='zl'";
		List<Startplan> list = null;

		try {
			Query query = session.createQuery(hql);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
		
	}
	
	@Override
	public Startplan getStartPlan(String id) {
		
		Session session = sessionFactory.openSession();
		String hql = "from Startplan where id='zl'";
		Startplan sp = null;

		try {
			Query query = session.createQuery(hql);
			sp = (Startplan) query.uniqueResult();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return sp;
		
	}
	
	@Override
	public void endAllRent(){
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		
			try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{call baseweb.clear_data()}");
			
			sp.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, null);
		}
		
	}
	
	@Override
	public long[] getRepairAndPracCount(String semester){
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		long[] value=new long[3];
			try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.count_numbers(?,?,?,?)}");
			sp.setString(1, semester);
			sp.execute();
			long value1=sp.getLong(2);
			long value2=sp.getLong(3);
			long value3=sp.getLong(4);
			value[0]=value1;
			value[1]=value2;
			value[2]=value3;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, null);
		}
			return value;
	}

}
