package com.base.dao;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import com.base.po.CheckView;
import com.base.po.landprocedure;
import com.base.po.song;


@Repository("land")
public class land 
{
		
	@Autowired
	private SessionFactory sessionfactory;  //获得会话工厂
	public List<landprocedure> query() throws SQLException
	{
		Session session=sessionfactory.openSession();   //获得session	
		
		//转入JDBC模式
		Connection conn = (Connection) SessionFactoryUtils.getDataSource(sessionfactory).getConnection(); //获得connection对象 		
		CallableStatement sp; 
		sp = conn.prepareCall("{call baseweb.land_procedure1(?)}");  //调用存储过程
		sp.setString(1, "201440509");  //向存储过程传递有参的参数(参数为字符串类型),1代表第一个参数
//	    sp.setInt(2,12);   //传递整形的参数
	    sp.execute(); // 执行存储过程  
	     ResultSet rs=sp.getResultSet();   //获得结果集	     
	     List<landprocedure> listTemp= new ArrayList<landprocedure>();  //定义一个相应类型的list集合去接受
	     while (rs.next()) { 
	    	 landprocedure lp = new landprocedure();   //实例化一个相应结果集类型的JavaBean
	    	 lp.setLid(rs.getInt("la_id"));
	    	 lp.setBname(rs.getString("bname"));
	    	 lp.setStartTime(rs.getString("startTime"));
	    	 lp.setEndTime(rs.getString("endTime"));
	    	 lp.setTimes(rs.getInt("times"));
	    	 lp.setApplicantId(rs.getString("applicantId"));
	    	 lp.setLa_id(rs.getInt("la_id"));
	    	 lp.setStatus(rs.getInt("status"));
	    	 System.out.println(rs.getString("bname"));	 
	    	 listTemp.add(lp);	    	 
	    	/* System.out.print(rs.getInt("la_id")+" ");
	    	 System.out.print(rs.getString("bname")+" ");
	    	 System.out.print(rs.getString("startTime")+" ");
	    	 System.out.print(rs.getString("endTime")+" ");
	    	 System.out.print(rs.getString("applicantId")+" ");
	    	 System.out.print(rs.getInt("status")+" ");
	    	 System.out.print(rs.getInt("lid")+" ");
	    	 System.out.println(rs.getInt("times"));*/
	     
	     }
		session.close();
		return listTemp;
	}
	
	//如果进行增删改操作(无返回参数)或者查只返回一条记录的可以使用这一种(hibernate)
	public void update()
	{
		Session session=sessionfactory.openSession();		
		//hibernate调用存储过程(无返回参数)
		SQLQuery sqlQuery =session.createSQLQuery("{call report.test111(?,?)}");
		sqlQuery.setInteger(0, 0);
		sqlQuery.setInteger(1, 20);
		sqlQuery.executeUpdate();
		session.close();		
	}
   //查询只返回一条记录的	
	public song getproduct()
	{
		song pr=null;
		Session session=sessionfactory.openSession();
		try
		{
			Query query=session.createQuery("{call report.test111()}");
			pr=(song)query.uniqueResult();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		finally
		{
			session.close();
		}
		return pr;
	}

	
	public List<CheckView> getLandApplys() throws SQLException
	{
		List<CheckView> list=new ArrayList<CheckView>();
		Session session=sessionfactory.openSession();
		Connection conn=(Connection)SessionFactoryUtils.getDataSource(sessionfactory).getConnection();
		CallableStatement sp;
		sp= (CallableStatement) conn.prepareCall("{call baseweb.rent_approve()}");
		boolean flag=sp.execute();
		if(flag)
		{
			System.out.println("执行成功");
		}
		else
		{
			System.out.println("执行失败");
		}
		ResultSet rs=sp.getResultSet();
		int i=0;
		while(rs.next())
		{
			CheckView ch=new CheckView();
			ch.setBasename(rs.getString("basename"));
			System.out.print(ch.getBasename()+" ");
			ch.setEndtime(rs.getString("endtime"));
			System.out.print(ch.getEndtime()+" ");
			ch.setLanddesp(rs.getString("landdesp"));
			System.out.print(ch.getLanddesp()+" ");
			ch.setLandname(rs.getString("landname"));
			System.out.print(ch.getLandname()+" ");
			ch.setLandoriented(rs.getString("landoriented"));
			System.out.print(ch.getLandoriented()+" ");
			ch.setLandstatus(rs.getInt("landstatus"));
			System.out.print(ch.getLandstatus()+" ");
			ch.setLi(rs.getInt("li"));
			System.out.print(ch.getLi()+" ");
			ch.setPlant(rs.getString("plant"));
		    System.out.print(ch.getPlant()+" ");
			ch.setStarttime(rs.getString("startime"));
			System.out.print(ch.getStarttime()+" ");
			ch.setTimes(rs.getInt("times"));
			System.out.print(ch.getTimes()+" ");
			ch.setUsercollage(rs.getString("usercollage"));
			System.out.println(ch.getUsercollage());
			list.get(i).setBasename(rs.getString("basename"));
			list.get(i).setEndtime(rs.getString("endtime"));
			list.get(i).setLanddesp(rs.getString("landdesp"));
			list.get(i).setLandname(rs.getString("landname"));
			list.get(i).setLandoriented(rs.getString("landoriented"));
			list.get(i).setLandstatus(rs.getInt("landstatus"));
		}
		return null;
	}


}
