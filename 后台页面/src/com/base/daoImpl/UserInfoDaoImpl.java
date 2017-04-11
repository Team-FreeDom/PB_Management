package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.UserInfoDao;
import com.base.po.Admin;
import com.base.po.ApplyDept;
import com.base.po.LandLayout;
import com.base.po.Manger;
import com.base.po.MangerList;
import com.base.po.UserInfo;
import com.base.utils.SqlConnectionUtils;

@Repository("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean login1(String id, String pwd) {
	Session session = sessionFactory.openSession();
	String hql = "from userinfo where id=? and password=?";
	boolean flag = false;

	try {
	    Query query = session.createQuery(hql);
	    query.setString(0, id);
	    query.setString(1, pwd);
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

    public long login(String id, String pwd) {
	// Session session=sessionFactory.openSession();
	CallableStatement sp = null;
	Connection conn = null;
	// 获得connection对象
	long adminValue = -1;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = conn.prepareCall("{call baseweb.user_login(?,?,?)}"); // 调用存储过程
	    sp.setString(1, id); // 向存储过程传递有参的参数(参数为字符串类型),1代表第一个参数
	    sp.setString(2, pwd);
	    sp.registerOutParameter(3, java.sql.Types.INTEGER);
	    sp.execute(); // 执行存储过程
	    adminValue = sp.getInt(3);
	    // 最后调用工具类释放连接
	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}
	return adminValue;
    }

    @Override
    public void updateUser(UserInfo ui) {
	Session session = sessionFactory.openSession();
	Transaction tx = null;

	try {
	    tx = session.beginTransaction();
	    session.update(ui);
	    tx.commit();

	} catch (Exception e) {
	    if (tx != null) {
		tx.rollback();// 回滚事务，撤消查询语句
	    }
	    System.out.println(e);
	} finally {
	    session.close();
	}

    }

    @Override
    public List<UserInfo> getUserInfos() {
	Session session = sessionFactory.openSession();
	String hql = "from userinfo";
	List<UserInfo> list = null;

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

    @Override
    public List<UserInfo> getUserInfos(int userright) {
	Session session = sessionFactory.openSession();
	String hql = "from userinfo where userright=?";
	List<UserInfo> list = null;

	try {
	    Query query = session.createQuery(hql);
	    query.setInteger(0, userright);
	    list = query.list();

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    session.close();
	}
	return list;
    }

    @Override
    public List<UserInfo> getUserInfos(String sex) {
	Session session = sessionFactory.openSession();
	String hql = "from UserInfo where sex=?";
	List<UserInfo> list = null;

	try {
	    Query query = session.createQuery(hql);
	    query.setString(0, sex);
	    list = query.list();

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    session.close();
	}
	return list;
    }

    @Override
    public List<UserInfo> getUserInfo(int id) {
	Session session = sessionFactory.openSession();
	String hql = "from UserInfo where id=?";
	List<UserInfo> list = null;

	try {
	    Query query = session.createQuery(hql);
	    query.setInteger(0, id);
	    list = query.list();

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    session.close();
	}
	return list;
    }

    @Override
    public List<UserInfo> getUserInfo(int userright, String sex) {
	Session session = sessionFactory.openSession();
	String hql = "from UserInfo where userright=? and sex=?";
	List<UserInfo> list = null;

	try {
	    Query query = session.createQuery(hql);
	    query.setInteger(0, userright);
	    query.setString(1, sex);
	    list = query.list();

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    session.close();
	}
	return list;
    }

    @Override
    public List<UserInfo> getUserInfo(int userright, int id) {
	Session session = sessionFactory.openSession();
	String hql = "from UserInfo where userright=? and id=?";
	List<UserInfo> list = null;

	try {
	    Query query = session.createQuery(hql);
	    query.setInteger(0, userright);
	    query.setInteger(1, id);
	    list = query.list();

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    session.close();
	}
	return list;
    }

    @Override
    public List<UserInfo> getUserInfos(String sex, int id) {
	Session session = sessionFactory.openSession();
	String hql = "from UserInfo where sex=? and id=?";
	List<UserInfo> list = null;

	try {
	    Query query = session.createQuery(hql);
	    query.setString(0, sex);
	    query.setInteger(1, id);
	    list = query.list();

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    session.close();
	}
	return list;
    }

    @Override
    public List<UserInfo> getUserInfo(int userright, String sex, int id) {
	Session session = sessionFactory.openSession();
	String hql = "from UserInfo where userright=? and id=? and sex=?";
	List<UserInfo> list = null;

	try {
	    Query query = session.createQuery(hql);
	    query.setInteger(1, userright);
	    query.setInteger(1, id);
	    query.setString(0, sex);
	    list = query.list();

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    session.close();
	}
	return list;
    }

    public Long getUserCount() {

	Session session = sessionFactory.openSession();
	Long userCount = (long) 0;
	String hql = "select count(*) from UserInfo";
	try {
	    Query query = session.createQuery(hql);
	    userCount = (Long) query.uniqueResult();

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    session.close();
	}
	// System.out.println(userCount);
	return userCount;
    }

    // 修改个人信息
    public void updateuser(String id, String name, String telephone,
	    String password, String img) {
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.update_userinfo(?,?,?,?,?)}");
	    sp.setString(1, id);
	    sp.setString(2, name);
	    sp.setString(3, telephone);
	    sp.setString(4, password);
	    sp.setString(5, img);
	    sp.execute();

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}

    }

    // 获取个人 信息
    public List<UserInfo> getInfoPerson(String id) {
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	List<UserInfo> list = new ArrayList<UserInfo>();
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.userdetail(?)}");
	    sp.setString(1, id);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {
		UserInfo ch = new UserInfo();
		ch.setName(rs.getString("name"));
		ch.setTelephone(rs.getString("telephone"));
		ch.setImg(rs.getString("img"));
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

    public UserInfo getUserInfo(String userid) {
	Session session = sessionFactory.openSession();
	String hql = "from UserInfo where id=?";
	UserInfo ui = null;

	try {
	    Query query = session.createQuery(hql);
	    query.setString(0, userid);

	    ui = (UserInfo) query.uniqueResult();

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    session.close();
	}
	return ui;
    }

    /**
     * 用户管理(分页)
     * 
     * @param pageindex
     *            当前页数
     * @param size
     *            当前显示几条记录
     * @return
     * @throws SQLException
     */
    @Override
    public MangerList manger(int pageindex, int size, String searchValue) {
	MangerList ck = new MangerList();
	List<Manger> list = new ArrayList<Manger>();
	int recordsTotal = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.finduserinfo(?,?,?,?)}");
	    sp.setInt(1, pageindex);
	    sp.setInt(2, size);
	    sp.registerOutParameter(3, java.sql.Types.INTEGER);
	    sp.setString(4, searchValue);
	    sp.execute();
	    recordsTotal = sp.getInt(3);
	    rs = sp.getResultSet();
	    while (rs.next()) {
		Manger ch = new Manger();
		ch.setId(rs.getString("userid"));
		ch.setUsername(rs.getString("username"));
		ch.setSex(rs.getString("sex"));
		ch.setCategory(rs.getString("category"));
		ch.setAttritube(rs.getString("attr"));
		ch.setBirth(rs.getString("birth"));
		ch.setIdcard(rs.getString("idcard"));
		ch.setTelephone(rs.getString("tel"));
		ch.setDept(rs.getString("college"));
		list.add(ch);
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	ck.setRecordsTotal(recordsTotal);
	ck.setData(list);
	return ck;
    }

    /**
     * 
     * @param id
     *            用户id
     * @return 用户基本信息
     * @throws SQLException
     */
    @Override
    public List<Manger> Mangerdetail(String id) {
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	List<Manger> list = new ArrayList<Manger>();
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.user_message(?)}");
	    sp.setString(1, id);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {
		Manger ch = new Manger();
		ch.setId(rs.getString("uid"));
		ch.setUsername(rs.getString("username"));
		ch.setSex(rs.getString("sex"));
		ch.setBirth(rs.getString("birth"));
		ch.setCategory(rs.getString("usertype"));
		ch.setDept(rs.getString("college"));
		ch.setTelephone(rs.getString("tel"));
		ch.setIdcard(rs.getString("idnumber"));
		ch.setPassword(rs.getString("pwd"));
		ch.setAttritube(rs.getString("rights"));
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

    /**
     * 删除人员基本信息
     * 
     * @param str
     *            为人员id的字符串
     * @throws SQLException
     */
    @Override
    public void deleteInfo(String str) {
	Connection conn = null;
	CallableStatement sp = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.delete_user(?)}");
	    sp.setString(1, str);
	    sp.execute();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

    }

    /**
     * 修改用户信息
     * 
     * @param id
     * @param name
     * @param sex
     * @param birthdate
     * @param category
     * @param attritube
     * @param telephone
     * @param idcard
     * @param password
     * @throws SQLException
     */
    @Override
    public void upInfo(String id, String name, String sex, String birthdate,
	    String category, String attritube, String dept, String telephone,
	    String idcard, String password)

    {
	Connection conn = null;
	CallableStatement sp = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.update_userinfos(?,?,?,?,?,?,?,?,?,?)}");
	    sp.setString(1, id);
	    sp.setString(2, name);
	    sp.setString(3, sex);
	    sp.setString(4, birthdate);
	    sp.setString(5, category);
	    sp.setString(6, attritube);
	    // System.out.println(attritube);
	    sp.setString(7, dept);
	    // System.out.println(dept);
	    sp.setString(8, telephone);
	    sp.setString(9, idcard);
	    sp.setString(10, password);
	    sp.execute();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}

    }

    /**
     * 部门集合
     * 
     * @return
     */
    @Override
    public List<ApplyDept> getDepts() {
	Session session = sessionFactory.openSession();
	String hql = "from ApplyDept";
	List<ApplyDept> list = null;
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

    /**
     * Admin中人员属性集合
     * 
     * @return
     */
    @Override
    public List<Admin> getAttritube() {
	Session session = sessionFactory.openSession();
	String hql = "from Admin";
	List<Admin> list = null;
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

    /**
     * 增加用户
     * 
     * @param id
     * @param name
     * @param sex
     * @param birthdate
     * @param category
     * @param attritube
     * @param dept
     * @param telephone
     * @param idcard
     * @param password
     */

    @Override
    public int addInfo(String id, String name, String sex, String birthdate,
	    String category, String attritube, String dept, String telephone,
	    String idcard, String password) {
	Connection conn = null;
	CallableStatement sp = null;
	int flag = 0;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL baseweb.add_user(?,?,?,?,?,?,?,?,?,?,?)}");
	    sp.setString(1, id);
	    sp.setString(2, name);
	    sp.setString(3, sex);
	    sp.setString(4, birthdate);
	    sp.setString(5, category);
	    sp.setString(6, attritube);
	    sp.setString(7, dept);
	    sp.setString(8, telephone);
	    sp.setString(9, idcard);
	    sp.setString(10, password);
	    /*
	     * System.out.println(id+"1"); System.out.println(name+"2");
	     * System.out.println(sex+"3"); System.out.println(birthdate+"4");
	     * System.out.println(category+"5");
	     * System.out.println(attritube+"6"); System.out.println(dept+"7");
	     * System.out.println(telephone+"8");
	     * System.out.println(idcard+"9");
	     * System.out.println(password+"10");
	     */
	    sp.registerOutParameter(11, java.sql.Types.INTEGER);
	    sp.execute();
	    flag = sp.getInt(11);
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, null);
	}
	return flag;
    }

    /**
     * 导出人员信息
     * 
     * @return
     */
    @Override
    public List<Manger> exportPersonInfo(String dept) {
	List<Manger> list = new ArrayList<Manger>();
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.export(?)}");
	    sp.setString(1, dept);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {
		Manger ch = new Manger();
		ch.setId(rs.getString("userid"));
		ch.setUsername(rs.getString("username"));
		ch.setSex(rs.getString("sex"));
		ch.setCategory(rs.getString("category"));
		ch.setBirth(rs.getString("birth"));
		ch.setIdcard(rs.getString("idcard"));
		ch.setTelephone(rs.getString("tel"));
		ch.setDept(rs.getString("college"));
		ch.setAttritube(rs.getString("attr"));
		ch.setMajor(rs.getString("major"));
		ch.setTitles(rs.getString("titles"));
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

    /**
     * 筛选userInfo中的部门
     * 
     * @return
     */
    @Override
    public List<UserInfo> userdept() {
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	List<UserInfo> list = new ArrayList<UserInfo>();
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{call baseweb.user_dept()}");
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {
		UserInfo ch = new UserInfo();
		ch.setCollege(rs.getString("college"));
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

    // 获取职称
    @Override
    public List<Map<String, String>> Title() {
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Connection conn = null;
	CallableStatement sp = null;
	HashMap<String, String> map = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL baseweb.find_titles()}");	  
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {	
		map = new HashMap<String, String>();
		map.put("titles", rs.getString("titles"));
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

}
