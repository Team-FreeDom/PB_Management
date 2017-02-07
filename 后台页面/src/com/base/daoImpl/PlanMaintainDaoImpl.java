package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.PlanMaintainDao;
import com.base.po.AllPlan;
import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;
import com.base.po.PlanList;
import com.base.utils.SqlConnectionUtils;

@Repository("planMaintainDao")
public class PlanMaintainDaoImpl implements PlanMaintainDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PlanList getPlanInfo(String semester, int pageindex, int size,
			String columnName, String orderDir, String searchValue) {
		
		List<AllPlan> list = new ArrayList<AllPlan>();
		int recordsTotal = 0;
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{  }");
		    sp.setString(1, semester);
		    sp.setInt(2, size);
		    sp.setInt(3, pageindex);
		    sp.setString(4, columnName);
		    sp.setString(5, orderDir);
		    sp.setString(6, searchValue);
		    sp.registerOutParameter(7, java.sql.Types.INTEGER);
		    sp.execute();
		    recordsTotal = sp.getInt(7);
		    rs = sp.getResultSet();
		    while (rs.next()) {
			AllPlan ap = new AllPlan();
			ap.setId(rs.getInt("id"));
			ap.setCheckMethod(rs.getString(""));
			ap.setCid(rs.getString(""));	
			ap.setCollege(rs.getString(""));
			ap.setComposition(rs.getString(""));
			ap.setCount(rs.getInt(""));
			ap.setCourseCategory(rs.getString(""));
			ap.setCoursename(rs.getString(""));
			ap.setCourseNature(rs.getString(""));
			ap.setCredit(rs.getDouble(""));
			ap.setMajor_oriented(rs.getString(""));
			ap.setMid(rs.getString(""));
			ap.setSelectedCount(rs.getInt(""));
			ap.setSemester(rs.getString(""));
			ap.setTid(rs.getString(""));
			ap.setTname(rs.getString(""));
			ap.setWeek(rs.getString(""));
			ap.setWeekClassify(rs.getDouble(""));
			list.add(ap);
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, sp, rs);
		}
		PlanList pl = new PlanList();
		pl.setRecordsTotal(recordsTotal);
		pl.setData(list);
		return pl;
	}

	@Override
	public void addPlanInfo(String str) {
		
		Session session = sessionFactory.openSession();

	   	try {
	   	    SQLQuery sqlQuery = session.createSQLQuery(str);
	   	    sqlQuery.executeUpdate();
	   	} finally {
	   	    session.close();
	   	}
	}

	@Override
	public List<AllPlan> getPlanTable(String semester,String college) {
		
		List<AllPlan> list = new ArrayList<AllPlan>();	
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{  }");
		    sp.setString(1, semester);
		    sp.setString(2, college);		   
		    sp.execute();		   
		    rs = sp.getResultSet();
		    while (rs.next()) {
			AllPlan ap = new AllPlan();
			ap.setId(rs.getInt("id"));
			ap.setCheckMethod(rs.getString(""));
			ap.setCid(rs.getString(""));	
			ap.setCollege(rs.getString(""));
			ap.setComposition(rs.getString(""));
			ap.setCount(rs.getInt(""));
			ap.setCourseCategory(rs.getString(""));
			ap.setCoursename(rs.getString(""));
			ap.setCourseNature(rs.getString(""));
			ap.setCredit(rs.getDouble(""));
			ap.setMajor_oriented(rs.getString(""));
			ap.setMid(rs.getString(""));
			ap.setSelectedCount(rs.getInt(""));
			ap.setSemester(rs.getString(""));
			ap.setTid(rs.getString(""));
			ap.setTname(rs.getString(""));
			ap.setWeek(rs.getString(""));
			ap.setWeekClassify(rs.getDouble(""));
			list.add(ap);
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, sp, rs);
		}		
		return list;
	}

	@Override
	public PlanList checkIsSave(String semester, int status, int pageindex,
			int size, String columnName, String orderDir, String searchValue) {
		
		List<AllPlan> list = new ArrayList<AllPlan>();
		int recordsTotal = 0;
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{  }");
		    sp.setString(1, semester);
		    sp.setInt(2, size);
		    sp.setInt(3, pageindex);
		    sp.setString(4, columnName);
		    sp.setString(5, orderDir);
		    sp.setString(6, searchValue);
		    sp.setInt(7, status);
		    sp.registerOutParameter(8, java.sql.Types.INTEGER);
		    sp.execute();
		    recordsTotal = sp.getInt(8);
		    rs = sp.getResultSet();
		    while (rs.next()) {
			AllPlan ap = new AllPlan();
			ap.setId(rs.getInt("id"));
			ap.setCheckMethod(rs.getString(""));
			ap.setCid(rs.getString(""));	
			ap.setCollege(rs.getString(""));
			ap.setComposition(rs.getString(""));
			ap.setCount(rs.getInt(""));
			ap.setCourseCategory(rs.getString(""));
			ap.setCoursename(rs.getString(""));
			ap.setCourseNature(rs.getString(""));
			ap.setCredit(rs.getDouble(""));
			ap.setMajor_oriented(rs.getString(""));
			ap.setMid(rs.getString(""));
			ap.setSelectedCount(rs.getInt(""));
			ap.setSemester(rs.getString(""));
			ap.setTid(rs.getString(""));
			ap.setTname(rs.getString(""));
			ap.setWeek(rs.getString(""));
			ap.setWeekClassify(rs.getDouble(""));
			list.add(ap);
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, sp, rs);
		}
		PlanList pl = new PlanList();
		pl.setRecordsTotal(recordsTotal);
		pl.setData(list);
		return pl;
	}

	@Override
	public void deletePlanInfo(String recordstr) {
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;		
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{ }");		   
		    sp.setString(1, recordstr);
		    sp.execute();    
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, sp, rs);
		}	
	}

	@Override
	public void callAttention(String recordstr) {
		
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;		
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{ }");		   
		    sp.setString(1, recordstr);
		    sp.execute();    
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, sp, rs);
		}	

	}

}
