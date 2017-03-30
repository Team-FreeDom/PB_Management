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

import com.base.dao.MymaintainDao;
import com.base.po.MymaintainList;
import com.base.po.Mymaintain;
import com.base.utils.SqlConnectionUtils;

@Repository("MymaintainDao")
public class MymaintainDaoImpl implements MymaintainDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public MymaintainList Mymaintain(int pageindex, int size,
	    String columnName, String orderDir, int year, int status,
	    String userid) {
	List<Mymaintain> list = new ArrayList<Mymaintain>();
	int recordsTotal = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.query_historymaintainapply(?,?,?,?,?,?,?,?)}");
	    sp.setInt(1,pageindex);	
	    //System.out.println(pageindex);
	    sp.setInt(2, size);	 
	   // System.out.println(size);
	    sp.setString(3, columnName);
	   // System.out.println(columnName);
	    sp.setString(4, orderDir);	 
	   // System.out.println(orderDir);
	    sp.setInt(5, year);	 
	    System.out.println(year);
	    sp.setInt(6, status);
	   // System.out.println(status);
	    sp.setString(7, userid);
	   // System.out.println(userid);
	    sp.registerOutParameter(8, java.sql.Types.INTEGER);
	    sp.execute();
	    recordsTotal = sp.getInt(8);
	    System.out.println(recordsTotal+"几条记录");
	    rs = sp.getResultSet();
	    while (rs.next()) {
		Mymaintain ch = new Mymaintain();
		ch.setId(rs.getInt("id"));
		ch.setPro_name(rs.getString("pro_name"));
		ch.setUsername(rs.getString("username"));
		ch.setAddress(rs.getString("address"));
		ch.setReason(rs.getString("reason"));
		ch.setFile(rs.getString("file"));
		ch.setMoney(rs.getDouble("money"));
		ch.setApply_time(rs.getString("apply_time"));
		ch.setUserid(rs.getString("userid"));
		ch.setBid(rs.getString("bid"));
		ch.setBasename(rs.getString("basename"));
		ch.setStatus(rs.getInt("status"));
		ch.setDescp(rs.getString("descp"));
		ch.setActualmoney(rs.getDouble("actualmoney"));
		ch.setRefuse(rs.getString("refuse"));
		list.add(ch);
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	
	MymaintainList ck = new MymaintainList();
	ck.setRecordsTotal(recordsTotal);	
	ck.setData(list);
	return ck;
    }
    //撤回功能
    @Override
    public int recallmymaint(String id) {
	int flag=0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.rollback_maintainapply(?,?)}");
	    sp.setString(1, id);
	    sp.registerOutParameter(2, java.sql.Types.INTEGER);
	    sp.execute();
	    flag = sp.getInt(2);   
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	return flag;
	
    }
    /**
     * 发送消息
     * @param sql 插入封装好的sql语句
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

    
}
