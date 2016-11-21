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
import com.base.po.LandApply;
import com.base.po.LandRentInfo;
import com.base.po.RentMaintain;
import com.base.utils.SqlConnectionUtils;


@Repository("landRentInfoDao")
public class LandRentInfoDaoImpl implements LandRentInfoDao {

	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	
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

	@Override
	public List<LandRentInfo> getLandRentInfos()
	{
		return null;
	}
	
	public List<RentMaintain> getRentMaintain(String bname,String lid,String deptName,String plantingContent,String lr_id) {	
		List<RentMaintain> list=new ArrayList<RentMaintain>();
		RentMaintain rm=null;
		
		try {
			
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();			
			sp= (CallableStatement) conn.prepareCall("{call baseweb.rent_maintain(?,?,?,?,?)}");  //发送存储过程
			sp.setString(1,bname);
			sp.setString(2,lid);			
			sp.setString(3,deptName);
			sp.setString(4, plantingContent);
			sp.setString(5, lr_id);
			
			sp.execute();   //执行存储过程

			rs=sp.getResultSet();  //获得结果集
			int i=0;
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
				rm.setRentMoney(rs.getInt("rentmoney"));
				rm.setChargeDate(rs.getString("chargedate"));
				rm.setTimes(rs.getInt("times"));
				rm.setApplydept(rs.getInt("deptid"));
				
				list.add(rm);    //加到list中
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{			
			
			SqlConnectionUtils.free(conn, sp, rs);
			
		}
		
		return list;
	
	}
	
	
	public void deleteRentInfo(String str) throws SQLException
	{
		Session session=sessionFactory.openSession();
		//hibernate调用存储过程(无返回参数)
		SQLQuery sqlQuery =session.createSQLQuery("{CALL baseweb.`delete_landrentinfo`(?)}");
		sqlQuery.setString(0, str);		
		sqlQuery.executeUpdate();
		session.close();			
		 
	}

}
