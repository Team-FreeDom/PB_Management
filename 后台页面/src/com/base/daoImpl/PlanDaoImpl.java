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

import com.base.dao.PlanDao;
import com.base.po.AllPlan;
import com.base.po.BaseInfo;
import com.base.po.Classcourse;
import com.base.po.Majoraim;
import com.base.po.PlanList;
import com.base.po.UserInfo;
import com.base.utils.SqlConnectionUtils;

@Repository("PlanDao")
public class PlanDaoImpl implements PlanDao {

    @Autowired
    private SessionFactory sessionFactory;

    // 获取该用户所在学院的实习计划
    @Override
    public PlanList getThisCollegePlan(String userid, int pageindex, int size,
	    String columnName, String orderDir, String searchValue,String semester) {
	List<AllPlan> list = new ArrayList<AllPlan>();
	int recordsTotal = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.query_teachercoursearrange(?,?,?,?,?,?,?,?)}");
	    sp.setString(1, userid);
	    sp.setInt(2, pageindex);
	    sp.setInt(3, size);
	    sp.setString(4, columnName);
	    sp.setString(5, orderDir);
	    sp.setString(6, searchValue);
	    sp.setString(7, semester);
	    sp.registerOutParameter(8, java.sql.Types.INTEGER);
	    sp.execute();
	    recordsTotal = sp.getInt(8);
	    rs = sp.getResultSet();
	    while (rs.next()) {
		AllPlan ch = new AllPlan();
		ch.setId(rs.getInt("id"));
		ch.setCid(rs.getString("cid"));
		ch.setCount(rs.getInt("count"));
		ch.setSelectedCount(rs.getInt("selectedCount"));
		ch.setComposition(rs.getString("composition"));
		ch.setCollege(rs.getString("college"));
		ch.setCoursename(rs.getString("coursename"));
		ch.setWeekClassify(rs.getDouble("weekClassify"));
		ch.setCredit(rs.getDouble("credit"));
		ch.setCourseNature(rs.getString("courseNature"));
		ch.setCourseCategory(rs.getString("courseCategory"));
		ch.setMid(rs.getString("mid"));
		ch.setTid(rs.getString("tid"));
		ch.setTname(rs.getString("tname"));
		ch.setSemester(rs.getString("semester"));
		ch.setWeek(rs.getString("week"));
		ch.setCheckMethod(rs.getString("checkMethod"));
		// System.out.println(ch.getCheckMethod()+"有没有");
		ch.setMajor_oriented(rs.getString("major_oriented"));
		list.add(ch);
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	PlanList ck = new PlanList();
	ck.setRecordsTotal(recordsTotal);
	ck.setData(list);
	return ck;
    }

    // 提供保存按钮的功能
    @Override
    public void updatePlan(int id, String plandata) {
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.add_classarrangecourse(?,?)}");
	    sp.setInt(1, id);
	    sp.setString(2, plandata);
	    sp.execute();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
    }

    // 删除单条班级安排记录
    @Override
    public void deleteClassPlan(int id) {
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.delete_classArrangecourse(?)}");
	    sp.setInt(1, id);
	    sp.execute();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}

    }

    // 从专业培训表中获取特定专业的多个培训目的
    @Override
    public List<Majoraim> getPlanAim(String mid) {
	List<Majoraim> list = new ArrayList<Majoraim>();
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.query_majoraim(?)}");
	    sp.setString(1, mid);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {
		Majoraim ch = new Majoraim();
		ch.setId(rs.getInt("id"));
		ch.setAim(rs.getString("aim"));
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

    // 根据学院名称获取该学院的老师
    @Override
    public List<UserInfo> getCollege_Teacher(String collagename) {
	List<UserInfo> list = new ArrayList<UserInfo>();
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.find_teacher(?)}");
	    sp.setString(1, collagename);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {
		UserInfo ch = new UserInfo();
		ch.setName(rs.getString("name"));
		// System.out.println(ch.getName());
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

    // 根据课程代码获取申请表的数据集合
    @Override
    public List<Classcourse> plandata(int id) {
	List<Classcourse> list = new ArrayList<Classcourse>();
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.query_course(?)}");
	    sp.setInt(1, id);
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

    // 获取基地集合
    @Override
    public List<BaseInfo> getBaseInfo() {
	Session session = sessionFactory.openSession();
	String hql = "from BaseInfo";
	List<BaseInfo> list = null;

	try {
	    Query query = session.createQuery(hql);
	    list = query.list();

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    session.close();
	}
	return list;
    }

    // 修改课程安排表(单条)李彩页面功能
    @Override
    public void alterRecord(String plandata) {
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call alter_coursearrange(?)}");
	    sp.setString(1, plandata);
	    sp.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}

    }
    //检测学年学期和数据条数
    @Override
    public int checkinfo(String userid, String semester) {
	int record=0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call query_teachercoursearrangecount(?,?,?)}");
	    sp.setString(1, userid);
	    sp.setString(2, semester);
	    sp.registerOutParameter(3, java.sql.Types.INTEGER);
	    sp.execute();
	    record = sp.getInt(3);
	    rs = sp.getResultSet();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	return record;
    }

	@Override
	public List<String> getOutBase(int tag) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
		    conn = (Connection) SessionFactoryUtils.getDataSource(
			    sessionFactory).getConnection();
		    sp = (CallableStatement) conn
			    .prepareCall("{CALL baseweb.`query_basename`(?)}");	
		    sp.setInt(1, tag);
		    sp.execute();
		    rs = sp.getResultSet();
		    while (rs.next()) {				
			list.add(rs.getString("basename"));
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    SqlConnectionUtils.free(conn, sp, rs);
		}
		return list;
	}

}
