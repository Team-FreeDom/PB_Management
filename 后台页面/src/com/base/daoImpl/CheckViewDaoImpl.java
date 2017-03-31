package com.base.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.CheckViewDao;
import com.base.po.BaseInfo;
import com.base.po.CheckList;
import com.base.po.CheckView;
import com.base.po.LandApply;
import com.base.po.UserInfo;
import com.base.utils.CookieUtils;
import com.base.utils.SqlConnectionUtils;

import java.sql.CallableStatement;
import java.sql.Connection;

@Repository("checkViewDao")
public class CheckViewDaoImpl implements CheckViewDao{
 
    @Autowired
    private SessionFactory sessionFactory;

    // 查询所有土地
    @SuppressWarnings("unchecked")
    public List<BaseInfo> getBaseInfos() {

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

    // 查询申请人
    @SuppressWarnings("unchecked")
    public List<UserInfo> getappliInfos() throws SQLException {
	List<UserInfo> list = new ArrayList();
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();

	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.find_name()}");
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {
		UserInfo rc = new UserInfo();
		rc.setName(rs.getString("username"));
		list.add(rc);
	    }
	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	return list;
    }

   
    // 数据库分页
    @Override
    public CheckList getLandApply(int id, int pageindex, int size,
	    String basename, String username, String usercollage,
	    String columnName, String orderDir){

	List<CheckView> list = new ArrayList<CheckView>();
	int recordsTotal = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.rent_check(?,?,?,?,?,?,?,?,?)}");
	    sp.setInt(1, id);
	    sp.setInt(2, pageindex);
	    sp.setInt(3, size);
	    sp.setString(4, basename);
	    sp.setString(5, username);
	    sp.setString(6, usercollage);
	    sp.setString(7, columnName);
	    sp.setString(8, orderDir);	    
	    sp.registerOutParameter(9, java.sql.Types.INTEGER);
	    sp.execute();
	    recordsTotal = sp.getInt(9);
	    rs = sp.getResultSet();
	    while (rs.next()) {
		CheckView ch = new CheckView();
		ch.setLa_id(rs.getInt("id"));
		ch.setStartime(rs.getString("startime"));
		ch.setEndtime(rs.getString("endtime"));
		ch.setBasename(rs.getString("basename"));
		ch.setLanddesp(rs.getString("landdesp"));
		ch.setLi(rs.getString("li"));
		ch.setUsername(rs.getString("username"));
		ch.setUsercollage(rs.getString("usercollage"));
		ch.setTimes(rs.getInt("times"));
		ch.setPlant(rs.getString("plant"));
		ch.setLandoriented(rs.getString("landoriented"));
		ch.setUserid(rs.getString("userid"));
		ch.setLandstatus(rs.getInt("landstatus"));
		ch.setLandname(rs.getString("landname"));
		list.add(ch);
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}

	CheckList ck = new CheckList();
	ck.setRecordsTotal(recordsTotal);
	ck.setData(list);
	return ck;
    }

    // 查看详情
    @Override
    public List<CheckView> detail(int la_id){
	List<CheckView> list = new ArrayList<CheckView>();
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.rent_detail(?)}");
	    sp.setInt(1, la_id);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {
		CheckView ch = new CheckView();
		ch.setLa_id(rs.getInt("id"));
		ch.setStartime(rs.getString("startime"));
		ch.setEndtime(rs.getString("endtime"));
		ch.setBasename(rs.getString("basename"));
		ch.setLanddesp(rs.getString("landdesp"));
		ch.setLi(rs.getString("li"));
		ch.setUsername(rs.getString("username"));
		ch.setUsercollage(rs.getString("usercollage"));
		ch.setTimes(rs.getInt("times"));
		ch.setPlant(rs.getString("plant"));
		ch.setLandoriented(rs.getString("landoriented"));
		ch.setUserid(rs.getString("userid"));
		ch.setLandstatus(rs.getInt("landstatus"));
		ch.setLandname(rs.getString("landname"));
		list.add(ch);
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}

	return list;
    }

    // 同意和拒绝申请
    public void getLandApplys(int flag, String la_id){
	Connection conn = null;
	CallableStatement sp = null;

	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.rent_approve(?,?)}");
	    sp.setInt(1, flag);
	    sp.setString(2, la_id);
	    sp.execute();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

    }

    // 同意和取消交费
    public void getApplys(int flag, String la_id) throws SQLException {
	Connection conn = null;
	CallableStatement sp = null;

	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.rent_pay(?,?)}");
	    sp.setInt(1, flag);
	    sp.setString(2, la_id);
	    sp.execute();

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

    }

    // 更改状态(参数1：拒绝的申请记录id;参数2：发送消息)
    @Override
    public int updateStatus(String recordStr, int status1,int status2) {

    	int tag = 0;
    	Connection conn = null;
    	CallableStatement sp = null;
    	ResultSet rs = null;
    	Session session = sessionFactory.openSession();

    	try {
    	    conn = (Connection) SessionFactoryUtils.getDataSource(
    		    sessionFactory).getConnection();
    	    sp = (CallableStatement) conn
    		    .prepareCall("{CALL baseweb.`state_trans`(?,?,?,?)}");
    	    sp.setString(1, recordStr);
    	    sp.setInt(2, status1);
    	    sp.setInt(3, status2);
    	    sp.registerOutParameter(4, java.sql.Types.INTEGER);
    	    sp.execute();

    	    tag = sp.getInt(4);

    	} catch (SQLException e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();

    	} finally {
    	    SqlConnectionUtils.free(conn, sp, null);
    	}

    	return tag;    
    }

    public void updateStatusP(String recordStr, int status) {

	Session session = sessionFactory.openSession();
	try {
	    // hibernate调用存储过程(无返回参数)
	    SQLQuery sqlQuery = session
		    .createSQLQuery("{CALL baseweb.state_pay(?,?)}");
	    sqlQuery.setString(0, recordStr);
	    sqlQuery.setInteger(1, status);
	    sqlQuery.executeUpdate();
	} finally {
	    session.close();
	}

    }

    @Override
    public void insertMessage(String sql) {
	System.out.println("insert---start");

	Session session = sessionFactory.openSession();

	try {
	    SQLQuery sqlQuery = session.createSQLQuery(sql);
	    // hibernate调用存储过程(无返回参数)
	    sqlQuery.executeUpdate();
	} finally {
	    session.close();
	}

	System.out.println("insert---end");

    }

    // 将土地状态为2的改为1，status1为转变后，status2为转变前
    @Override
    public void changeSolid(String landstr, int status1, int status2) {

	System.out.println("改变啦");
	System.out.println(landstr);
	Session session = sessionFactory.openSession();
	try {
	    SQLQuery sqlQuery = session
		    .createSQLQuery("{CALL baseweb.`update_applystate`(?,?,?)}");
	    sqlQuery.setString(0, landstr);
	    sqlQuery.setInteger(1, status1);
	    sqlQuery.setInteger(2, status2);
	    sqlQuery.executeUpdate();
	} finally {
	    session.close();
	}

	System.out.println("改变完成");

    }

    // 确认交费，将记录的状态改为申请成功，并将记录插入土地租赁历史表中
    @Override
    public int payForSuccess(String recordStr, int status) {
    	
	int tag = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	Session session = sessionFactory.openSession();

	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.state_success(?,?,?)}");
	    sp.setString(1, recordStr);
	    sp.setInt(2, status);
	    sp.registerOutParameter(3, java.sql.Types.INTEGER);
	    sp.execute();

	    tag = sp.getInt(3);

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();

	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

	return tag;   
    }

    // 同意申请，将审核状态的变为锁定状态，同时给锁定状态的发送通知
    @Override
    public void lockInfo(String landstr) {

	Session session = sessionFactory.openSession();
	try {
	    // hibernate调用存储过程(无返回参数)
	    SQLQuery sqlQuery = session
		    .createSQLQuery("{CALL baseweb.trans_lock(?)}");
	    sqlQuery.setString(0, landstr);
	    sqlQuery.executeUpdate();
	} finally {
	    session.close();
	}

    }

    // 取消交费，将锁定状态的变为审核状态，发送通知
    @Override
    public void releaseInfo(String landstr) {

	Session session = sessionFactory.openSession();
	try {
	    // hibernate调用存储过程(无返回参数)
	    SQLQuery sqlQuery = session
		    .createSQLQuery("{CALL baseweb.trans_pay(?)}");
	    sqlQuery.setString(0, landstr);
	    sqlQuery.executeUpdate();
	} finally {
	    session.close();
	}

    }

    // 确认交费，将锁定状态的变为同类竞争，发送通知
    @Override
    public void confirmInfo(String landstr) {

	Session session = sessionFactory.openSession();
	try {
	    // hibernate调用存储过程(无返回参数)
	    SQLQuery sqlQuery = session
		    .createSQLQuery("{CALL baseweb.`trans_fail`(?)}");
	    sqlQuery.setString(0, landstr);
	    sqlQuery.executeUpdate();
	} finally {
	    session.close();
	}

    }

    // 同意申请，将审核中的记录变为交费中，判断土地编号是否有相同的，返回flag值
    @Override
    public int agreeInfo(String recordStr, int status) {

	int tag = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	Session session = sessionFactory.openSession();

	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL baseweb.agree_apply(?,?,?)}");
	    sp.setString(1, recordStr);
	    sp.setInt(2, status);
	    sp.registerOutParameter(3, java.sql.Types.INTEGER);
	    sp.execute();

	    tag = sp.getInt(3);

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();

	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

	return tag;
    }

    // 租赁审批未审核的申请人
    @Override
    public List<Map<String, String>> getCheckApplicant() {

	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	Session session = sessionFactory.openSession();
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Map<String, String> map = null;

	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.check_applicant()}");
	    sp.execute();

	    rs = sp.getResultSet();
	    while (rs.next()) {

		map = new HashMap<String, String>();
		map.put("userid", rs.getString("userid"));
		map.put("username", rs.getString("username"));
		list.add(map);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();

	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

	return list;

    }

    // 租赁审批未交费的申请人
    @Override
    public List<Map<String, String>> getPayApplicant() {

	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	Session session = sessionFactory.openSession();
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Map<String, String> map = null;

	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.pay_applicant()}");
	    sp.execute();

	    rs = sp.getResultSet();
	    while (rs.next()) {

		map = new HashMap<String, String>();
		map.put("userid", rs.getString("userid"));
		map.put("username", rs.getString("username"));
		list.add(map);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();

	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

	return list;

    }

    // 租赁审批逾期的申请人
    @Override
    public List<Map<String, String>> getoverdueApplicant() {

	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	Session session = sessionFactory.openSession();
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Map<String, String> map = null;

	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.outdate_applyname()}");
	    sp.execute();

	    rs = sp.getResultSet();
	    while (rs.next()) {

		map = new HashMap<String, String>();
		map.put("userid", rs.getString("applicantId"));
		map.put("username", rs.getString("username"));
		list.add(map);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();

	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

	return list;

    }

    // 租赁审批未审核的部门
    @Override
    public List<Map<String, String>> getCheckDept() {

	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	Session session = sessionFactory.openSession();
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Map<String, String> map = null;

	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.check_college()}");
	    sp.execute();

	    rs = sp.getResultSet();
	    while (rs.next()) {

		map = new HashMap<String, String>();
		map.put("deptid", rs.getString("deptid"));
		map.put("dept", rs.getString("dept"));
		list.add(map);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();

	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

	return list;

    }

    // 租赁审批未交费的部门
    @Override
    public List<Map<String, String>> getPayDept() {

	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	Session session = sessionFactory.openSession();
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Map<String, String> map = null;

	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.pay_college()}");
	    sp.execute();

	    rs = sp.getResultSet();
	    while (rs.next()) {

		map = new HashMap<String, String>();
		map.put("deptid", rs.getString("deptid"));
		map.put("dept", rs.getString("dept"));
		list.add(map);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();

	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

	return list;

    }

    // 租赁审批逾期的部门
    @Override
    public List<Map<String, String>> getoverdueDept() {

	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	Session session = sessionFactory.openSession();
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Map<String, String> map = null;

	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.outdate_applyDept()}");
	    sp.execute();

	    rs = sp.getResultSet();
	    while (rs.next()) {

		map = new HashMap<String, String>();
		map.put("deptid", rs.getString("applyDept"));
		map.put("dept", rs.getString("dept"));
		list.add(map);

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();

	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

	return list;
    }

    // 逾期恢复
    @Override
    public void overduerecovery(String recordStr) {
	Session session = sessionFactory.openSession();
	try {
	    // hibernate调用存储过程(无返回参数)
	    SQLQuery sqlQuery = session
		    .createSQLQuery("{CALL baseweb.renewal_costsdate(?)}");
	    sqlQuery.setString(0, recordStr);	   
	    sqlQuery.executeUpdate();
	} finally {
	    session.close();
	}
    }
}
