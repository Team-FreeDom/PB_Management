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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.PlanMaintainDao;
import com.base.po.AllPlan;
import com.base.po.ApplyDept;
import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;
import com.base.po.Major;
import com.base.po.PlanList;
import com.base.po.UserInfo;
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
			    .prepareCall("{call baseweb.query_coursearrange(?,?,?,?,?,?,?)}");
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
			ap.setCheckMethod(rs.getString("checkMethod"));
			ap.setCid(rs.getString("cid"));	
			ap.setCollege(rs.getString("college"));
			ap.setComposition(rs.getString("composition"));
			ap.setCount(rs.getInt("count"));
			ap.setCourseCategory(rs.getString("courseCategory"));
			ap.setCoursename(rs.getString("coursename"));
			ap.setCourseNature(rs.getString("courseNature"));
			ap.setCredit(rs.getDouble("credit"));
			ap.setMajor_oriented(rs.getString("major_oriented"));
			ap.setMid(rs.getString("mid"));
			ap.setSelectedCount(rs.getInt("selectedCount"));
			ap.setSemester(rs.getString("semester"));
			ap.setTid(rs.getString("tid"));
			ap.setTname(rs.getString("tname"));
			ap.setWeek(rs.getString("week"));
			ap.setWeekClassify(rs.getDouble("weekClassify"));
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
			    .prepareCall("{call baseweb.find_coursearrange(?,?)}");
		    sp.setString(1, semester);
		    sp.setString(2, college);		   
		    sp.execute();		   
		    rs = sp.getResultSet();
		    while (rs.next()) {
			AllPlan ap = new AllPlan();
			ap.setId(rs.getInt("id"));
			ap.setCheckMethod(rs.getString("checkMethod"));
			ap.setCid(rs.getString("cid"));	
			ap.setCollege(rs.getString("college"));
			ap.setComposition(rs.getString("composition"));
			ap.setCount(rs.getInt("count"));
			ap.setCourseCategory(rs.getString("courseCategory"));
			ap.setCoursename(rs.getString("coursename"));
			ap.setCourseNature(rs.getString("courseNature"));
			ap.setCredit(rs.getDouble("credit"));
			ap.setMajor_oriented(rs.getString("major_oriented"));
			ap.setMid(rs.getString("mid"));
			ap.setSelectedCount(rs.getInt("selectedCount"));
			ap.setSemester(rs.getString("semester"));
			ap.setTid(rs.getString("tid"));
			ap.setTname(rs.getString("tname"));
			ap.setWeek(rs.getString("week"));
			ap.setWeekClassify(rs.getDouble("weekClassify"));
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
			    .prepareCall("{call baseweb.query_coursearranges(?,?,?,?,?,?,?,?)}");
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
			ap.setCheckMethod(rs.getString("checkMethod"));
			ap.setCid(rs.getString("cid"));	
			ap.setCollege(rs.getString("college"));
			ap.setComposition(rs.getString("composition"));
			ap.setCount(rs.getInt("count"));
			ap.setCourseCategory(rs.getString("courseCategory"));
			ap.setCoursename(rs.getString("coursename"));
			ap.setCourseNature(rs.getString("courseNature"));
			ap.setCredit(rs.getDouble("credit"));
			ap.setMajor_oriented(rs.getString("major_oriented"));
			ap.setMid(rs.getString("mid"));
			ap.setSelectedCount(rs.getInt("selectedCount"));
			ap.setSemester(rs.getString("semester"));
			ap.setTid(rs.getString("tid"));
			ap.setTname(rs.getString("tname"));
			ap.setWeek(rs.getString("week"));
			ap.setWeekClassify(rs.getDouble("weekClassify"));
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
			    .prepareCall("{call baseweb.`delete_coursearrange`(?)}");		   
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
			    .prepareCall("{ call baseweb.`message_coursearrange`(?)}");		   
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
	public List<String> getSemester() {
		List<String> list=new ArrayList<String>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;		
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{CALL baseweb.query_semester()}");		   
		    sp.execute(); 
		    rs = sp.getResultSet();
		    while (rs.next()) {
		    	list.add(rs.getString("semester"));
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
	public boolean checkIsUser(String id) {
		Session session = sessionFactory.openSession();
		String hql = "from UserInfo where id=?";
		boolean flag = false;

		try {
			Query query = session.createQuery(hql);
			query.setString(0, id);			
			UserInfo ui = (UserInfo) query.uniqueResult();
			if (ui != null) {
				flag = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return flag;
	}
	
	@Override
	public boolean checkIsMid(String aid) {
		Session session = sessionFactory.openSession();
		String hql = "from Major where mid=?";
		boolean flag = false;

		try {
			Query query = session.createQuery(hql);
			query.setString(0, aid);			
			Major ad = (Major) query.uniqueResult();
			if (ad != null) {
				flag = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<String> getPlanCollege(String semester) {
		List<String> list=new ArrayList<String>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;		
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{CALL baseweb.`query_coursearrangecollege`(?)}");	
		    sp.setString(1,semester);
		    sp.execute(); 
		    rs = sp.getResultSet();
		    while (rs.next()) {
		    	list.add(rs.getString("college"));
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
	public void deleteAndAdd(String semester, String str) {
		
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;		
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{ call baseweb.`add_coursearrange`(?,?)}");		   
		    sp.setString(1, semester);
		    sp.setString(2, str);
		    sp.execute();    
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, sp, rs);
		}	
	}
}
