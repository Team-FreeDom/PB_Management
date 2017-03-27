package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.baseApplyDao;
import com.base.po.ApplyDept;
import com.base.po.Major;
import com.base.po.basetype;
import com.base.utils.SqlConnectionUtils;


@Repository("baseApplyDao")
public class baseApplyDaoImpl implements baseApplyDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    
    /**
     * 得到对应部门
     * @param type 基地类型
     * @return
     */
    @Override
    public List<ApplyDept> getDept(int type) {
	
	Session session=sessionFactory.openSession();		
	String hql="from ApplyDept where type=? and aid !=-1";
	List<ApplyDept> list=null;
	
    try {
    	 Query query=session.createQuery(hql);
    	 query.setInteger(0, type);
    	 list=query.list();
		
	} catch (Exception e) {
		System.out.println(e);
	}finally{
		session.close();
	}
	return list;
    }
    /**
     * 获取基地类型
     * @return
     */
    @Override
    public List<basetype> getBasetype() {
	Session session=sessionFactory.openSession();		
	String hql="from basetype";
	List<basetype> list=null;
	
    try {
    	 Query query=session.createQuery(hql);
    	 list=query.list();
		
	} catch (Exception e) {
		System.out.println(e);
	}finally{
		session.close();
	}
	return list;
    }
    /**
     * 获取学院对应的专业
     * @param aid 学院id
     * @return
     */
    @Override
    public List<Major> getMajor(int aid) {
	Session session=sessionFactory.openSession();		
	String hql="from Major where aid=?";
	List<Major> list=null;
	
    try {
    	 Query query=session.createQuery(hql);
    	 query.setInteger(0, aid);
    	 list=query.list();
		
	} catch (Exception e) {
		System.out.println(e);
	}finally{
		session.close();
	}
	return list;
    }
    /**
     * 插入用户基地申请信息
     * @param str1 (基地id,专业id)
     * @param str2 基地名称id 申报部门id 基地类型id等的string字符串
     */
    @Override
    public void getRequestBaseInfo(String str1, String str2) {
	Connection conn = null;
	CallableStatement sp = null;
	try {
		conn = (Connection) SessionFactoryUtils.getDataSource(
				sessionFactory).getConnection();
		sp = (CallableStatement) conn
				.prepareCall("{call baseweb.base_apply(?,?)}");
		sp.setString(1, str1);
		sp.setString(2, str2);
		sp.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		SqlConnectionUtils.free(conn, sp, null);
	}
	
    }
    //检测用户输入的名称是否存在
    @Override
    public int CheckName(String name) {
	Connection conn = null;
	CallableStatement sp = null;
	int flag = 0;
	try {
		conn = (Connection) SessionFactoryUtils.getDataSource(
				sessionFactory).getConnection();
		sp = (CallableStatement) conn
				.prepareCall("{call baseweb.check_baseapplyname(?,?)}");
		sp.setString(1, name);
		sp.registerOutParameter(2, java.sql.Types.INTEGER);
	        sp.execute();
	        flag=sp.getInt(2);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		SqlConnectionUtils.free(conn, sp, null);
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
