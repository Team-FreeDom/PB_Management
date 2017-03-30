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

import com.base.dao.MyBaseInfoDao;
import com.base.po.MyBase;
import com.base.po.MyBaseList;
import com.base.utils.SqlConnectionUtils;

@Repository("MyBaseInfoDao")
public class MyBaseInfoDaoImpl implements MyBaseInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取基地申请信息（我的基地）
     */
    @Override
    public MyBaseList MybaseInfo(int pageindex, int size, String columnName,
	    String orderDir, int year, int status, String userid) {
	List<MyBase> list = new ArrayList<MyBase>();
	int recordsTotal = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.query_baseapplyhistory(?,?,?,?,?,?,?,?)}");
	    sp.setInt(1, pageindex);
	    sp.setInt(2, size);
	    sp.setString(3, columnName);
	    sp.setString(4, orderDir);
	    sp.setInt(5, year);
	    sp.setInt(6, status);
	    // System.out.println(status);
	    sp.setString(7, userid);
	    // System.out.println(userid);
	    sp.registerOutParameter(8, java.sql.Types.INTEGER);
	    sp.execute();
	    recordsTotal = sp.getInt(8);
	    rs = sp.getResultSet();
	    while (rs.next()) {
		MyBase ch = new MyBase();
		ch.setId(rs.getInt("id"));
		ch.setBid(rs.getString("bids"));
		ch.setName(rs.getString("basename"));
		ch.setLandarea(rs.getString("landarea"));
		ch.setConstructionarea(rs.getString("constructionarea"));
		ch.setUndertake(rs.getInt("undertake"));
		ch.setLand_address(rs.getString("land_address"));
		ch.setUsername(rs.getString("username"));
		ch.setPhone(rs.getString("phone"));
		ch.setMaterial_path(rs.getString("material_path"));
		ch.setUserid(rs.getString("userid"));
		ch.setStatusid(rs.getInt("status"));
		ch.setStatusdigital(rs.getString("descp"));
		ch.setType(rs.getString("type"));
		ch.setApplydp(rs.getString("dept"));
		ch.setMmajor(rs.getString("mname"));
		ch.setReason(rs.getString("reason"));
		ch.setEndtime(rs.getString("endtime"));
		ch.setBuildtime(rs.getString("buildtime"));// 创建
		ch.setResperson(rs.getString("resperson"));
		list.add(ch);
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}

	MyBaseList ck = new MyBaseList();
	ck.setRecordsTotal(recordsTotal);
	ck.setData(list);
	return ck;
    }

    /**
     * 发送消息
     * 
     * @param sql
     *            插入封装好的sql语句
     */
    @Override
    public void insertMessage(String sql) {
	System.out.println("insert---start");

	Session session = sessionFactory.openSession();

	try {
	    SQLQuery sqlQuery = session.createSQLQuery(sql);
	    sqlQuery.executeUpdate();
	} finally {
	    session.close();
	}
	System.out.println("insert---end");

    }

    @Override
    public void updateDate(int id, String adddate) {
	Session session = sessionFactory.openSession();

	try {
	    SQLQuery query = session
		    .createSQLQuery("{CALL baseweb.user_renewalbase(?,?)}");
	    query.setInteger(0, id);
	    query.setString(1, adddate);
	    query.executeUpdate();
	} finally {
	    session.close();
	}
    }
    
    @Override
    public int changeThisStatus(String id,int status1,int status2){
    	
    	int flag = 0;
    	Connection conn = null;
    	CallableStatement sp = null;
    	ResultSet rs = null;    	
    	try {
    	    conn = (Connection) SessionFactoryUtils.getDataSource(
    		    sessionFactory).getConnection();
    	    sp = (CallableStatement) conn
    		    .prepareCall("{CALL baseweb.`judge_baseapply`(?,?,?,?)}");
    	    sp.setString(1, id);
    	    sp.setInt(2, status1);
    	    sp.setInt(3, status2);
    	    sp.registerOutParameter(4, java.sql.Types.INTEGER);
    	    sp.execute();
    	    flag = sp.getInt(4);
    	    
    	} catch (SQLException e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	} finally {
    	    SqlConnectionUtils.free(conn, sp, rs);
    	}
    	return flag;
    }

}
