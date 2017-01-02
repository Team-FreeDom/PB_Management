package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.po.MaintainApplys;
import com.base.po.ProBaseinfo;
import com.base.utils.SqlConnectionUtils;

@Repository("MaintainApplyDao")
public class MaintainApplyDao
{
	@Autowired
	private SessionFactory sessionfactory;
	//查询所有的基地列表
	public List<ProBaseinfo> find_basename()
	{
		Session session=sessionfactory.openSession();
		List<ProBaseinfo> list =null;
		String hql="select id,name from ProBaseinfo where type=?";
		try
		{
			Query query=session.createQuery(hql);
	    	 query.setInteger(0, 0);
	    	 list=query.list();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			session.close();
		}
		return list;
	}
	//插入项目维修申请
	public void insert_maintain(String str)
	{
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
	//查询完成的校内基地维修申请记录,参数顺序：当前页面记录数，当前页数，排序列，排序顺序,返回总记录数
	public List<MaintainApplys> query_maintainapply(int offsets,int page,String str,String str1)
	{
		List<MaintainApplys> list=new ArrayList<MaintainApplys>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		int numbers=0;
		try
		{
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionfactory).getConnection();
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.query_maintainapply(?,?,?,?,?)}");
			sp.setInt(1,offsets);
			sp.setInt(2,page);
			sp.setString(3, str);
			sp.setString(4, str1);
			sp.registerOutParameter(5,java.sql.Types.INTEGER);
			sp.execute(); 
			numbers=sp.getInt(5);
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
				list.add(mt);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
}
