package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.MaintainApplyDao;
import com.base.po.MaintainApply;
import com.base.po.MaintainApplys;
import com.base.po.MaintainList;
import com.base.po.Prabaseinfo;
import com.base.utils.SqlConnectionUtils;

@Repository("MaintainApplyDao")
public class MaintainApplyDaoImpl implements MaintainApplyDao
{
	@Autowired
	private SessionFactory sessionfactory;
	
	@Override
	//查询所有的基地列表	
	public List<Map<String,String>> find_basename()
	{
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		List<Map<String,String>> list =new ArrayList<Map<String,String>>();
		HashMap<String,String> map=null;
		try
		{
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionfactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.query_schoolbase()}");
			sp.execute();
			rs=sp.getResultSet();
			while(rs.next())
			{
				map=new HashMap<String, String>();
				map.put("id",rs.getString("id"));
				map.put("name",rs.getString("name"));
				list.add(map);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			SqlConnectionUtils.free(conn, sp, rs);
		}
		return list;
	}
	
	@Override
	//查询所有的除了校外基地的基地列表	
	public List<Map<String,String>> find_basenamenei()
	{
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		List<Map<String,String>> list =new ArrayList<Map<String,String>>();
		HashMap<String,String> map=null;
		try
		{
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionfactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.`select_base`()}");
			sp.execute();
			rs=sp.getResultSet();
			while(rs.next())
			{
				map=new HashMap<String, String>();
				map.put("id",rs.getString("id"));
				map.put("name",rs.getString("name"));
				list.add(map);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			SqlConnectionUtils.free(conn, sp, rs);
		}
		return list;
	}
	
	@Override
	//查询维修完成的基地列表	
	public List find_basenameFinish(String year)
	{
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		List list =new ArrayList();
		HashMap<String,String> map=null;
		try
		{
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionfactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.find_basenameFinish(?)}");
			sp.setString(1, year);
			sp.execute();
			rs=sp.getResultSet();
			System.out.println(".........year:"+year);
			while(rs.next())
			{				
				list.add(rs.getString("name"));
				System.out.println("lala"+rs.getString("name"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			SqlConnectionUtils.free(conn, sp, rs);
		}
		return list;
	}
	
	@Override
	//插入项目维修申请
	public void insert_maintain(String str)
	{
		System.out.println(str);
		Connection conn = null;
		CallableStatement sp = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionfactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.`insert_maintain`(?)}");
			sp.setString(1, str);
			sp.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, null);
		}
	}
	
	@Override
	//查询完成的校内基地维修申请记录,参数顺序：当前页面记录数，当前页数，排序列，排序顺序，模糊查询的字符串,返回总记录数
	public MaintainList query_maintainapply(int offsets,int page,String str,String str1,String str2)
	{
		MaintainList list=new MaintainList();
		List<MaintainApplys> ma=new ArrayList<MaintainApplys>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try
		{
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionfactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.query_maintainapply(?,?,?,?,?,?)}");
			sp.setInt(1,offsets);
			sp.setInt(2,page);
			sp.setString(3, str);
			sp.setString(4, str1);
			sp.setString(5, str2);
			System.out.println("1:"+offsets+";  2:"+page+"; 3:"+str+"; 4:"+str1+"; 5:"+str2);
			sp.registerOutParameter(6,java.sql.Types.INTEGER);
			sp.execute(); 
			((MaintainList) list).setRecordsTotal(sp.getInt(6));
			rs=sp.getResultSet();
			while(rs.next())
			{
				MaintainApplys mt=new MaintainApplys();
				mt.setAddress(rs.getString("address"));
				mt.setApply_time(rs.getString("apply_time"));
				mt.setBasename(rs.getString("basename"));
				mt.setFile(rs.getString("file"));
				mt.setId(rs.getInt("id"));
				mt.setMoney(rs.getDouble("money"));
				mt.setPro_name(rs.getString("pro_name"));
				mt.setReason(rs.getString("reason"));
				mt.setUserid(rs.getString("userid"));
				mt.setUsername(rs.getString("username"));
				mt.setActualmoney(rs.getDouble("actualmoney"));
				ma.add(mt);
			}
			list.setData(ma);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}
		return list;
	}
	
	@Override
	//删除维修基地申请记录，传的值为维修记录id的集合
	public void delete_maintainapply(String str)
	{
		Connection conn = null;
		CallableStatement sp = null;
		try
		{
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionfactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.delete_maintainapply(?)}");
			sp.setString(1, str);
			sp.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			SqlConnectionUtils.free(conn, sp, null);
		}
	}
	
	@Override
	//增加维修基地记录（已完成的维修）
	public void add_maintain(MaintainApply ma)
	{
		Session session = sessionfactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(ma);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();//
			}
			System.out.println(e);
		} finally {
			session.close();
		}
	}
	
	@Override
	//导出基地维修记录，参数为筛选条件，第一个基地名字，第二个为年份（如没有，则为-1）
	public List<MaintainApplys> export_maintainapply(String bname,int years)
	{
		List<MaintainApplys> ma=new ArrayList<MaintainApplys>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try
		{
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionfactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.export_maintainapply(?,?)}");
			sp.setString(1, bname);
			sp.setInt(2,years);
			sp.execute();
			rs=sp.getResultSet();
			while(rs.next())
			{
				MaintainApplys mt=new MaintainApplys();
				mt.setAddress(rs.getString("address"));//
				mt.setApply_time(rs.getString("applytime"));//
				mt.setBasename(rs.getString("basename"));//
				mt.setPro_name(rs.getString("proname"));//
				mt.setReason(rs.getString("reason"));//
				mt.setUsername(rs.getString("username"));//
				mt.setActualmoney(rs.getDouble("moneys"));//
				ma.add(mt);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			SqlConnectionUtils.free(conn, sp, rs);
		}
		return ma;
	}
	
	public List<String> getThoseYear(){
		List<String> ma=new ArrayList<String>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try
		{
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionfactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.`count_maintainyear`()}");			
			sp.execute();
			rs=sp.getResultSet();
			while(rs.next())
			{
				ma.add(rs.getString("years"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			SqlConnectionUtils.free(conn, sp, rs);
		}
		return ma;
		
	}
}
