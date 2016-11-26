package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.po.ApplyList;
import com.base.po.LandApply_view;
import com.base.po.Land_Planting;
import com.base.po.RentAdd;
import com.base.utils.SqlConnectionUtils;

@Repository("landapply_viewDao")
public class LandApply_viewDaoImpl {
	

	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	/*
	 * ����ͨ���߸�������ѯ�û�������ʷ 
	 * */
	public ApplyList getapplys(String applicantId,String bname,String startTime,String endTime,int status,int page,int length)
	{
		System.out.println(applicantId+"  "+bname+"  "+startTime+"   "+endTime+"  "+status);
		ApplyList al=new ApplyList();
		LandApply_view lv=null;
		List<LandApply_view> list=new ArrayList<LandApply_view>();
		
		try {
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{call baseweb.landapplys(?,?,?,?,?,?,?,?)}");
			sp.setString(1,applicantId);
			sp.setString(2, bname);
			sp.setString(3, startTime);
			sp.setString(4,endTime);
			sp.setInt(5, status);
			sp.setInt(6, page);
			sp.setInt(7,length);
			sp.registerOutParameter(8,java.sql.Types.INTEGER);
			
			sp.execute();   //ִ�д洢����
			rs=sp.getResultSet();  //��ý����
			al.setRecordsTotal(sp.getInt(8));
			
			while(rs.next())
			{
				lv=new LandApply_view();
				lv.setAfford(rs.getInt("afford"));
				lv.setApplicantId(rs.getString("applicantId"));
				lv.setAptPlanting(rs.getString("aptPlanting"));
				lv.setBname(rs.getString("bname"));
				lv.setBuildingArea(rs.getInt("buildingArea"));
				lv.setCollege(rs.getString("college"));
				lv.setDescp(rs.getString("descp"));
				lv.setEndTime(rs.getString("endTime"));
				lv.setLa_id(rs.getInt("la_id"));
				lv.setLandArea(rs.getInt("landArea"));
				lv.setLid(rs.getString("lid"));
				lv.setLname(rs.getString("lname"));
				lv.setName(rs.getString("name"));
				lv.setPlanting(rs.getString("planting"));
				lv.setResource(rs.getString("resource"));
				lv.setStartPayTime(rs.getString("startPayTime"));
				lv.setStartTime(rs.getString("startTime"));
				lv.setStatus(rs.getInt("status"));
				lv.setTenancy(rs.getInt("tenancy"));
				
				list.add(lv);
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnectionUtils.free(conn, sp, rs);			
		}		
		al.setData(list);
		return al;
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
	
	public ApplyList getapply(String applicantId,int page,int length)
	{
		
		ApplyList al=new ApplyList();
		LandApply_view lv=null;
		List<LandApply_view> list=new ArrayList<LandApply_view>();
		
		try {
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL tem_applyinfo(?,?,?,?)}");
			sp.setString(1,applicantId);			
			sp.setInt(2, page);
			sp.setInt(3,length);
			sp.registerOutParameter(4,java.sql.Types.INTEGER);
			
			sp.execute();   //ִ�д洢����
			rs=sp.getResultSet();  //��ý����
			al.setRecordsTotal(sp.getInt(4));
			
			while(rs.next())
			{
				lv=new LandApply_view();
				lv.setAfford(rs.getInt("afford"));
				lv.setApplicantId(rs.getString("applicantId"));
				lv.setAptPlanting(rs.getString("aptPlanting"));
				lv.setBname(rs.getString("bname"));
				lv.setBuildingArea(rs.getInt("buildingArea"));
				lv.setCollege(rs.getString("college"));
				lv.setDescp(rs.getString("descp"));
				lv.setEndTime(rs.getString("endTime"));
				lv.setLa_id(rs.getInt("la_id"));
				lv.setLandArea(rs.getInt("landArea"));
				lv.setLid(rs.getString("lid"));
				lv.setLname(rs.getString("lname"));
				lv.setName(rs.getString("name"));
				lv.setPlanting(rs.getString("planting"));
				lv.setResource(rs.getString("resource"));
				lv.setStartPayTime(rs.getString("startPayTime"));
				lv.setStartTime(rs.getString("startTime"));
				lv.setStatus(rs.getInt("status"));
				lv.setTenancy(rs.getInt("tenancy"));
				
				list.add(lv);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnectionUtils.free(conn, sp, rs);			
		}		
		al.setData(list);
		return al;
	
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
			
		List<LandApply_view> lav=new ArrayList<LandApply_view>();
		
		LandApply_view lv=null;
		try
		{
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.landapply_detail(?)}");
			sp.setInt(1, la_id);
			sp.execute();   //ִ�д洢����
			rs=sp.getResultSet();  //��ý����			
			
			while(rs.next())
			{
				lv=new LandApply_view();
				lv.setAfford(rs.getInt("afford"));
				lv.setApplicantId(rs.getString("applicantId"));
				lv.setAptPlanting(rs.getString("aptPlanting"));
				lv.setBname(rs.getString("bname"));
				lv.setBuildingArea(rs.getInt("buildingArea"));
				lv.setCollege(rs.getString("college"));
				lv.setDescp(rs.getString("descp"));
				lv.setEndTime(rs.getString("endTime"));
				lv.setLa_id(rs.getInt("la_id"));
				lv.setLandArea(rs.getInt("landArea"));
				lv.setLid(rs.getString("lid"));
				lv.setLname(rs.getString("lname"));
				lv.setName(rs.getString("name"));
				lv.setPlanting(rs.getString("planting"));
				lv.setResource(rs.getString("resource"));
				lv.setStartPayTime(rs.getString("startPayTime"));
				lv.setStartTime(rs.getString("startTime"));
				lv.setStatus(rs.getInt("status"));
				lv.setTenancy(rs.getInt("tenancy"));
				
				lav.add(lv);
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnectionUtils.free(conn, sp, rs);			
		}		
		
		return lav;
	}
	
	public List<LandApply_view> getAllStudents(LandApply_view searchModel)
	{
		Session session=sessionFactory.openSession();
		List<LandApply_view> list=null;
		List<Object> paramList = new ArrayList<Object>();			
		
		String bname = searchModel.getBname();
		String startTime = searchModel.getStartTime();
		String endTime=searchModel.getEndTime();
		String lid=searchModel.getLid();
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
		
		if (lid!=null&&!lid.equals("")) {
			hql.append(" and lid=?");
			paramList.add(String.valueOf(lid)+"true");
			
		}
		
		if (desc != null && !desc.equals("")) { 
			if(desc.equals("����ɹ�"))
			{
			hql.append(" and descp=?");
			paramList.add(desc);
			}else if(desc.equals("����ʧ��")){
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
					if(((String) paramList.get(i)).matches("[1-9]*(true)?")&&i!=0)
					{
						if(((String) paramList.get(i)).matches("[1-9]*(true)"))
						{
							String str=(String) paramList.get(i);
							str=str.substring(0, str.indexOf("true"));
							System.out.println("�ҵõ���lid�ǣ�"+str);
							query.setInteger(i, Integer.valueOf(str));
							
						}else{
						query.setInteger(i, Integer.valueOf((String) paramList.get(i)));
						}
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


