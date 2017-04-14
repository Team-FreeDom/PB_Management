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
import com.base.po.Classcourse;
import com.base.po.LandInfo;
import com.base.po.Major;
import com.base.po.PlanList;
import com.base.po.PracticeCollection;
import com.base.po.RentAdd;
import com.base.po.RentCollection;
import com.base.po.StartDate;
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
			System.out.println(semester + " " + size + " " + pageindex + " "
					+ columnName + " " + orderDir + " " + searchValue);
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
	public List<AllPlan> getPlanTable(String year, int semester, String college) {

		List<AllPlan> list = new ArrayList<AllPlan>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{call baseweb.find_coursearrange(?,?,?)}");
			sp.setString(1, year);
			sp.setInt(2, semester);
			sp.setString(3, college);
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

	// 获得实习申请记录
	public List<Classcourse> plandata(String year, int semester, String college) {
		List<Classcourse> list = new ArrayList<Classcourse>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn.prepareCall("{CALL baseweb.export_classarrangecourse(?,?,?)}");
			sp.setString(1, year);
			sp.setInt(2, semester);
			sp.setString(3, college);
			sp.execute();
			rs = sp.getResultSet();
			while (rs.next()) {
				Classcourse ch = new Classcourse();
				ch.setId(rs.getInt("id"));
				ch.setWeek(rs.getInt("week"));
				ch.setStarttime(rs.getString("starttime"));
				ch.setEndtime(rs.getString("endtime"));
				ch.setContent(rs.getString("content"));
				ch.setSource(rs.getString("source"));
				ch.setSite(rs.getString("site"));
				ch.setCategory(rs.getString("category"));
				ch.setForm(rs.getString("form"));
				ch.setTelephone(rs.getString("telephone"));
				ch.setAim(rs.getString("aim"));
				ch.setExpense(rs.getString("expense"));
				ch.setCourse(rs.getString("course"));
				ch.setGuideTeacher(rs.getString("guideTeacher"));
				ch.setAssistant(rs.getString("assistant"));
				ch.setRemark(rs.getString("remark"));
				ch.setGrade(rs.getString("grade"));
	    		ch.setMajor_oriented(rs.getString("major_oriented"));
				list.add(ch);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}
		return list;
	}

	public List<PracticeCollection> getPlanTable_0(String year, int semester,
			String college) {

		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;

		List<PracticeCollection> list = new ArrayList<PracticeCollection>();
		PracticeCollection pc = null;
		List<Classcourse> lra = plandata(year, semester, college);

		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn.prepareCall("{CALL baseweb.find_coursearrange(?,?,?)}");
			sp.setString(1, year);
			sp.setInt(2, semester);
			sp.setString(3, college);
			sp.execute(); // 执行存储过程
			rs = sp.getResultSet(); // 获得结果集

			while (rs.next()) // 遍历结果集，赋值给list
			{
				pc = new PracticeCollection();

				pc.setComposition(rs.getString("composition"));
				pc.setCountPeople("学习人数:"+rs.getString("count"));
				pc.setCourseId(rs.getString("cid"));
				pc.setCourseName(rs.getString("coursename"));
				pc.setCredit("学分:"+rs.getString("credit"));
				pc.setDepartment(rs.getString("department"));
				pc.setMajor_oriented(rs.getString("major_oriented"));
				pc.setWeekCount("实习周数:"+rs.getString("week"));
				pc.setTid(rs.getString("tid"));
				pc.setTname(rs.getString("tname"));
				List<Classcourse> lis = new ArrayList<Classcourse>();
				int i=0;
				for (Classcourse ra : lra) {
					if (String.valueOf(rs.getInt("id")).equals(ra.getCourse())) {
						lis.add(ra);
						
					}
					
				}				
				pc.setData(lis);
				list.add(pc);

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
		List<String> list = new ArrayList<String>();
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
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.`query_coursearrangecollege`(?)}");
			sp.setString(1, semester);
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
	public void delete_0(String semester) {

		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{ CALL baseweb.`delete_coursearranges`(?)}");
			sp.setString(1, semester);			
			sp.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}
	}

	@Override
	public int checkIsCid(String semester, String cid) {
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		int flag = 0;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.`check_courseid`(?,?,?)}");
			sp.setString(1, cid);
			sp.setString(2, semester);
			sp.execute();
			flag = sp.getInt(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}
		return flag;
	}

	@Override
	public List<String> getSem(String semester) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL  baseweb.query_semesternumber(?)}");
			sp.setString(1, semester);
			sp.execute();
			rs = sp.getResultSet();
			while (rs.next()) {
				list.add(String.valueOf(rs.getInt("number")));
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
	public List<String> getCollegehh(String year, int semester) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.export_college(?,?)}");
			sp.setString(1, year);
			sp.setInt(2, semester);
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
	public void addStartDate(String semester, String startTime) {

		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.check_startdate(?,?)}");
			sp.setString(1, semester);
			sp.setString(2, startTime);
			sp.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}

	}

	@Override
	public List<StartDate> getStartDate() {
		Session session = sessionFactory.openSession();
		String hql = "from StartDate";
		List<StartDate> list = null;

		try {
			Query query = session.createQuery(hql);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		// System.out.println(bid+list.get(0).getAfford());
		return list;

	}

	// 修改课程安排表(单条)李彩页面功能
	@Override
	public void alterRecord(int id, String plandata) {
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.`alter_coursearrange`(?,?)}");
			sp.setInt(1, id);
			sp.setString(2, plandata);
			sp.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}

	}

	@Override
	public void add_0(String str) {
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{ CALL baseweb.`add_coursearrange`(?)}");
			sp.setString(1, str);			
			sp.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}
		
	}

}
