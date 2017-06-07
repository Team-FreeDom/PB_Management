package com.base.daoImpl;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.ProfessionalmanageDao;
import com.base.po.Professionalmanage;
import com.base.po.ProfessionalmanageList;
import com.base.utils.BaseUtils;
import com.base.utils.SqlConnectionUtils;


@Repository("ProfessionalmanageDao")
public class ProfessionalmanageDaoImpl implements ProfessionalmanageDao{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public int insertMajor(String mid,String mname,int aid) {
		int flag=0;
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    String sql =("insert into major values(?,?,?,?)");
		    ps = conn.prepareStatement(sql);
//		    sp = (CallableStatement) conn.prepareStatement("insert into major values(?,?,?,?)");
		    ps.setString(1, mid);
		    ps.setString(2, mname);
		    ps.setInt(3, aid);
		    ps.setInt(4, 0);
		    ps.executeUpdate();
		    flag = 1;
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, ps, rs);
		}
		return flag;
	}
	
	public String deleteMajor(String str){
		int flag;
		String message = null;
		Connection conn = null;
		CallableStatement sp = null;
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{CALL baseweb.delete_major(?,?)}");
		    sp.setString(1, str);
		    sp.execute();
		    flag=sp.getInt(2);
		    message=BaseUtils.getException(flag);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, sp, null);
		}
		return message;
	}

	@Override
	public ProfessionalmanageList query_majors(int size, int pageindex,
			String columnName, String orderDir, String searchValue) {
		int recordsTotal = 0;
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		List<Professionalmanage> list=new ArrayList<Professionalmanage>();
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{call baseweb.query_majors(?,?,?,?,?,?)}");		    
		    sp.setInt(1, size);
		    sp.setInt(2, pageindex);
		    sp.setString(3, columnName);
		    sp.setString(4, orderDir);
		    sp.setString(5, searchValue);
		    sp.registerOutParameter(6, java.sql.Types.INTEGER);
		    sp.execute();
		    recordsTotal = sp.getInt(6);
		    rs = sp.getResultSet();
		    while (rs.next()) {
		    Professionalmanage ch = new Professionalmanage();
			ch.setAid(rs.getInt("aid"));
			ch.setDept(rs.getString("dept"));
			ch.setMid(rs.getString("mid"));
			ch.setMname(rs.getString("mname"));
			list.add(ch);
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, sp, rs);
		}
		ProfessionalmanageList  ck=new ProfessionalmanageList();
		ck.setRecordsTotal(recordsTotal);
		ck.setData(list);
		return ck;
	}

	@Override
	public void updatemajor(String mid,String mname) {
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		System.out.println(mid);
		System.out.println(mname);
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    String sql =("update major set mname=? where mid=?");
		    ps = conn.prepareStatement(sql);
		    ps.setString(1, mname);
		    ps.setString(2, mid);
		    ps.executeUpdate();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, ps, rs);
		}
	}

	
	public int CheckmName(String name) {
		Connection conn = null;
		CallableStatement sp = null;
		int flag = 0;
		System.out.println(name+"3333333333333");
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{call baseweb.check_major(?,?)}");
			sp.setString(1, name);
			sp.registerOutParameter(2, java.sql.Types.INTEGER);
		    sp.execute();
		    flag=sp.getInt(2);
		    System.out.println(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, null);
		}
		return flag;
	    }
	
	public int CheckmName1(String name) {
		Connection conn = null;
		CallableStatement sp = null;
		int flag = 0;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{call baseweb.check_majorname(?,?)}");
			sp.setString(1, name);
			sp.registerOutParameter(2, java.sql.Types.INTEGER);
		    sp.execute();
		    flag=sp.getInt(2);
		    System.out.println(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, null);
		}
		return flag;
	    }
	
}
