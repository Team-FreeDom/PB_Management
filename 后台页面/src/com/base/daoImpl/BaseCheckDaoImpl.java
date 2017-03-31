package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseCheckDao;
import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;
import com.base.utils.SqlConnectionUtils;

@Repository("BaseCheckDao")
public class BaseCheckDaoImpl implements BaseCheckDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取基地申请信息（基地审核）
     */
    @Override
    public BaseCheckList getBaseCheck(int applydpid, int pageindex, int size,
	    String columnName, String orderDir, String searchValue) {
	List<BaseCheck> list = new ArrayList<BaseCheck>();
	int recordsTotal = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.query_baseapply(?,?,?,?,?,?,?)}");
	    sp.setInt(1, applydpid);
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
		BaseCheck ch = new BaseCheck();
		ch.setId(rs.getInt("id"));
		ch.setBid(rs.getString("bids"));
		ch.setName(rs.getString("basename"));
		ch.setLandarea(rs.getString("landarea"));
		ch.setConstructionarea(rs.getString("constructionarea"));
		ch.setLand_address(rs.getString("land_address"));
		ch.setUsername(rs.getString("username"));
		ch.setPhone(rs.getString("phone"));
		ch.setUndertake(rs.getInt("undertake"));
		ch.setMaterial_path(rs.getString("material_path"));
		ch.setUserid(rs.getString("userid"));
		ch.setType(rs.getString("basetype"));
		ch.setApplydp(rs.getString("dept"));
		ch.setMmajor(rs.getString("mname"));
		ch.setResperson(rs.getString("resperson"));
		list.add(ch);
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	BaseCheckList ck = new BaseCheckList();
	ck.setRecordsTotal(recordsTotal);
	ck.setData(list);
	return ck;
    }

    // 获得续期中的记录
    @Override
    public BaseCheckList getaddCheck(int applydpid, int pageindex, int size,
	    String columnName, String orderDir, String searchValue) {
	List<BaseCheck> list = new ArrayList<BaseCheck>();
	int recordsTotal = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.query_renewalbaseapply(?,?,?,?,?,?,?)}");
	    sp.setInt(1, applydpid);
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
		BaseCheck ch = new BaseCheck();
		ch.setId(rs.getInt("id"));
		ch.setBid(rs.getString("bids"));
		ch.setName(rs.getString("basename"));
		ch.setLandarea(rs.getString("landarea"));
		ch.setConstructionarea(rs.getString("constructionarea"));
		ch.setLand_address(rs.getString("land_address"));
		ch.setUsername(rs.getString("username"));
		ch.setPhone(rs.getString("phone"));
		ch.setUndertake(rs.getInt("undertake"));
		ch.setMaterial_path(rs.getString("material_path"));
		ch.setUserid(rs.getString("userid"));
		ch.setType(rs.getString("basetype"));
		ch.setApplydp(rs.getString("dept"));
		ch.setMmajor(rs.getString("mname"));
		ch.setResperson(rs.getString("resperson"));
		ch.setBuildtime(rs.getString("buildtime"));
		ch.setEndtime(rs.getString("endtime"));
		list.add(ch);
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	BaseCheckList ck = new BaseCheckList();
	ck.setRecordsTotal(recordsTotal);
	ck.setData(list);
	return ck;
    }

    /**
     * 
     * @return 获取部门集合（部门id和具体部门）
     */
    @Override
    public List<Map<String, String>> getDept(int tag) {
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	HashMap<String, String> map = null;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL baseweb.query_baseapplydept(?)}");
	    sp.setInt(1, tag);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {
		map = new HashMap<String, String>();
		map.put("aid", "" + rs.getInt("aid"));
		map.put("dept", rs.getString("dept"));
		list.add(map);
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	return list;
    }

    /**
     * 拒绝申请
     * 
     * @param str
     *            封装的id表示哪几条数据
     * @param status
     *            变为申请失败状态（12）
     */
    @Override
    public int refuseapply(String recorddigit, String recordstr) {
	int flag = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    System.out.println(recordstr);
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL baseweb.transstate_baseapply(?,?,?)}");
	    sp.setString(1, recorddigit);
	    sp.setString(2, recordstr);
	    sp.registerOutParameter(3, java.sql.Types.INTEGER);
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

    /**
     * 同意申请
     * 
     * @param str
     *            封装的id表示哪几条数据
     * @param recordstr
     *            封装的信息记录id和申请年限
     */
    @Override
    public int agreeApply(String recorddigit, String recordstr) {
	int flag = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	System.out.println(recorddigit + recordstr + "李彩李彩李彩");
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.agree_baseapply(?,?,?)}");
	    sp.setString(1, recorddigit);
	    sp.setString(2, recordstr);
	    sp.registerOutParameter(3, java.sql.Types.INTEGER);
	    sp.execute();
	    flag = sp.getInt(3);
	    System.out.println(flag + "彩别彩别");
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

    // 同意续期
    @Override
    public int addDateApply(String recordstr) {
	int flag = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	System.out.println(recordstr);
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL baseweb.agree_renewalbaseapply(?,?)}");
	    sp.setString(1, recordstr);
	    sp.registerOutParameter(2, java.sql.Types.INTEGER);
	    sp.execute();
	    flag = sp.getInt(2);
	    System.out.println(flag+"fffffffffffff");
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	return flag;
    }

    // 拒绝续期
    @Override
    public int refuseDateApply(String recorddigit,String recordstr) {
	int flag = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL baseweb.renewalfail(?,?,?)}");
	    sp.setString(1, recorddigit);
	    sp.setString(2, recordstr);
	    sp.registerOutParameter(3, java.sql.Types.INTEGER);
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
  //管理员点击同意申请检查基地是否同名字
    @Override
    public int checkBaseName(String recorddigit) {
	int flag = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL  baseweb.check_prabaseinfo(?,?)}");
	    sp.setString(1, recorddigit);	   
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
}
