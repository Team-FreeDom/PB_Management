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

import com.base.dao.LandRentInfoDao;
import com.base.po.ApplyDept;
import com.base.po.LandApply;
import com.base.po.LandRentInfo;
import com.base.po.RentList;
import com.base.po.RentMaintain;
import com.base.utils.SqlConnectionUtils;


@Repository("landRentInfoDao")
public class LandRentInfoDaoImpl<E> implements LandRentInfoDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void doLandRentInfo(LandRentInfo lr) {
		Session session=sessionFactory.openSession();		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.save(lr);
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
	public List<LandRentInfo> getLandRentInfo(String userId) {
		Session session=sessionFactory.openSession();		
		String hql="from LandRentInfo where userId=?";
		List<LandRentInfo> list=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setString(0, userId);	    	
	    	 list=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return list;
	}


	public RentList getRentMaintain(String bname,String dept,String planting,int page,int length)
	{
		
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		
		RentList rl=new RentList();
		List<RentMaintain> data=new ArrayList<RentMaintain>();
		RentMaintain rm=null;
		int recordsTotal=0;
		
           try{
			
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();			
			sp = conn.prepareCall("{CALL `rent_maintain`(?,?,?,?,?,?)}"); 
			sp.setString(1,bname);	
			sp.setString(2, dept);
			sp.setString(3, planting);
			sp.setInt(4, page);
			sp.setInt(5, length);
			sp.registerOutParameter(6,java.sql.Types.INTEGER);
			
			sp.execute();   //执行存储过程
			
			recordsTotal=sp.getInt(6);
			rs=sp.getResultSet(); 
			
			while(rs.next())    //遍历结果集，赋值给list
			{
				rm=new RentMaintain();
				rm.setLr_id(rs.getInt("lrid"));
				rm.setStartTime(rs.getString("starttime"));
				rm.setEndTime(rs.getString("endtime"));
				rm.setPlanting(rs.getString("plant"));	
				rm.setDeptName(rs.getString("dept"));
				rm.setBname(rs.getString("basename"));
				rm.setLid(rs.getString("lids"));
				rm.setLandname(rs.getString("landname"));
				rm.setAptplanting(rs.getString("aptplanting"));
				rm.setName(rs.getString("username"));
				rm.setRentMoney(rs.getDouble("rentmoney"));
				rm.setChargeDate(rs.getString("chargedate"));
				rm.setTimes(rs.getInt("times"));
				rm.setApplydept(rs.getInt("deptid"));
				
				data.add(rm);    //加到list中
			}
			
             }catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}finally{			
     			
     			SqlConnectionUtils.free(conn, sp, rs);
     			
     		}
           
           rl.setRecordsTotal(recordsTotal);          
           rl.setData(data);
         
     	return rl;	
	}
	

	public void deleteRentInfo(String str)
	{
		Session session=sessionFactory.openSession();
		//hibernate调用存储过程(无返回参数)
		SQLQuery sqlQuery =session.createSQLQuery("{CALL baseweb.`delete_landrentinfo`(?)}");
		sqlQuery.setString(0, str);		
		sqlQuery.executeUpdate();
		session.close();			
		 
	}
	
	public List<RentMaintain> getSingleRentInfo(String lr_id,String dept )
	{	
		
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;		
	
		List<RentMaintain> list=new ArrayList<RentMaintain>();
		RentMaintain rm=null;		
		
           try{
			
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();			
			sp = conn.prepareCall("{call baseweb.rentinfo_maintain(?,?)}"); 
			sp.setString(1,lr_id);			
			sp.setString(2, dept);
			sp.execute();   //执行存储过程			
			
			rs=sp.getResultSet(); 
			
			while(rs.next())    //遍历结果集，赋值给list
			{
				
				rm=new RentMaintain();
				rm.setLr_id(rs.getInt("lrid"));
				rm.setStartTime(rs.getString("starttime"));
				rm.setEndTime(rs.getString("endtime"));
				rm.setPlanting(rs.getString("plant"));	
				rm.setDeptName(rs.getString("dept"));
				rm.setBname(rs.getString("basename"));
				rm.setLid(rs.getString("lids"));
				rm.setLandname(rs.getString("landname"));
				rm.setAptplanting(rs.getString("aptplanting"));
				rm.setName(rs.getString("username"));
				rm.setRentMoney(rs.getDouble("rentmoney"));
				rm.setChargeDate(rs.getString("chargedate"));
				rm.setTimes(rs.getInt("times"));
				rm.setApplydept(rs.getInt("deptid"));
				
				list.add(rm);    //加到list中
			}
			
             }catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}finally{			
     			
     			SqlConnectionUtils.free(conn, sp, rs);
     			
     		}         
          
         System.out.println("导出呀"+list);
     	return list;	
	}
	
	public LandRentInfo getOne(int lr_id) {
		Session session=sessionFactory.openSession();		
		String hql="from LandRentInfo where lr_id=?";
		LandRentInfo lr=null;
		
	    try {
	    	 Query query=session.createQuery(hql);
	    	 query.setInteger(0, lr_id);	    	
	    	 lr=(LandRentInfo) query.uniqueResult();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return lr;
	}
	
	public void updateOne(LandRentInfo lr) {
		Session session=sessionFactory.openSession();	
		
		Transaction tx=null;
		
		try {
			 tx=session.beginTransaction();
	    	 session.update(lr);
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
	public List<ApplyDept> getExistDept(){
		
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		
		List<ApplyDept> list=new ArrayList<ApplyDept>();
		ApplyDept dept=null;
		
		 try{
				
				conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();			
				sp = conn.prepareCall("{call baseweb.`rent_dept`()}");				
				sp.execute();   //执行存储过程			
				
				rs=sp.getResultSet(); 
				if(rs!=null){
				while(rs.next())    //遍历结果集，赋值给list
				{
					dept=new ApplyDept();
					dept.setAid(rs.getInt("aid"));
					dept.setDept(rs.getString("dept"));
					list.add(dept);    //加到list中
				}
				}
				
	             }catch (SQLException e) {
	     			// TODO Auto-generated catch block
	     			e.printStackTrace();
	     		}finally{			
	     			
	     			SqlConnectionUtils.free(conn, sp, rs);
	     			
	     		} 
		 return list;
	}

	@Override
public List<String> getExistPlant(){
		
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		
		List<String> list=new ArrayList<String>();
		String plant=null;
		
		 try{
				
				conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();			
				sp = conn.prepareCall("{CALL baseweb.rent_plant()}");				
				sp.execute();   //执行存储过程			
				
				rs=sp.getResultSet(); 
				if(rs!=null){
				while(rs.next())    //遍历结果集，赋值给list
				{
					plant=rs.getString("planting");
					list.add(plant);    //加到list中
				}
				}
				
	             }catch (SQLException e) {
	     			// TODO Auto-generated catch block
	     			e.printStackTrace();
	     		}finally{			
	     			
	     			SqlConnectionUtils.free(conn, sp, rs);
	     			
	     		} 
		 return list;
	}

}
