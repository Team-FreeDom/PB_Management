package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.PlanDao;
import com.base.po.AllPlan;
import com.base.po.Classarragecourse;
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
		    .prepareCall("{call baseweb.query_teachercoursearrange(?,?,?,?,?,?,?)}");
	    sp.setString(1, userid);
	    sp.setInt(2, pageindex);
	    sp.setInt(3, size);
	    sp.setString(4, columnName);
	    sp.setString(5, orderDir);
	    sp.setString(6, searchValue);
	    sp.registerOutParameter(7, java.sql.Types.INTEGER);
	    sp.execute();
	    recordsTotal = sp.getInt(7);
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
		//System.out.println(ch.getCheckMethod()+"有没有");
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
    public void updatePlan(String cid, String semester, String plandata) {
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.add_classarrangecourse(?,?,?)}");
	    sp.setString(1, cid);
	    sp.setString(2, semester);
	    sp.setString(3, plandata);
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
    public List<Majoraim> getPlanAim(String cid) {
	List<Majoraim> list = new ArrayList<Majoraim>();
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.query_majoraim(?)}");
	    sp.setString(1, cid);
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
		//System.out.println(ch.getName());
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
    public List<Classcourse> plandata(String cid) {
	List<Classcourse> list = new ArrayList<Classcourse>();
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.query_course(?)}");
	    sp.setString(1, cid);
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
		ch.setCourse(rs.getInt("course"));
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

}
