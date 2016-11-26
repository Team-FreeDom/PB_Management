package com.base.daoImpl;

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

import com.base.po.BaseInfo;
import com.base.po.CheckList;
import com.base.po.CheckView;
import com.base.po.LandApply;
import com.base.po.UserInfo;
import com.base.utils.SqlConnectionUtils;

import java.sql.CallableStatement;
import java.sql.Connection;

@Repository("checkViewDao")
public class CheckViewDaoImpl {
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	@Autowired
	private SessionFactory sessionFactory;
	
	//查询所有土地
	@SuppressWarnings("unchecked")
	public List<BaseInfo> getBaseInfos() {
		
		Session session=sessionFactory.openSession();		
		String hql="from BaseInfo";		
		List<BaseInfo> list=null;
		
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
	//查询申请人
        @SuppressWarnings("unchecked")
		public List<UserInfo> getappliInfos() throws SQLException {
    		List<UserInfo> list=new ArrayList();
    		Connection conn=(Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
    		CallableStatement sp;
    		sp=(CallableStatement) conn.prepareCall("{call baseweb.find_name()}");
    		sp.execute();
    		ResultSet rs=sp.getResultSet();
    		while(rs.next()){
    			UserInfo rc=new UserInfo();
    		   rc.setName(rs.getString("username"));
    		   list.add(rc);
    		}
            /*for(int i=0;i<list.size();i++){
            	 System.out.println(list.get(i).getName());
            }*/
    		
		return list;
	}
      //查询部门
        @SuppressWarnings("unchecked")
		public List<UserInfo> getDept() throws SQLException {
    		List<UserInfo> list=new ArrayList();
    		try{
    		conn=(Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
    		sp=(CallableStatement) conn.prepareCall("{call baseweb.find_dept()}");
    		sp.execute();
    		ResultSet rs=sp.getResultSet();
    		while(rs.next()){
    			UserInfo rc=new UserInfo();
    		   rc.setDept(rs.getString("depart"));
    		   list.add(rc);
    		}
    		}catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}finally{			
    			
    			SqlConnectionUtils.free(conn, sp, rs);
    			
    		}
		return list;
	}
    //数据库分页
	public CheckList getLandApply(int id,int pageindex,int size,String basename,String username,String usercollage ) throws SQLException
	{
		CheckList ck=new CheckList();
		List<CheckView> list=new ArrayList<CheckView>();
		int recordsTotal=0;
		try{
	    conn=(Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		sp=(CallableStatement) conn.prepareCall("{call baseweb.rent_check(?,?,?,?,?,?,?)}");
		sp.setInt(1, id);
		sp.setInt(2, pageindex);
		sp.setInt(3, size);
		sp.setString(4,basename);
		sp.setString(5,username);
		sp.setString(6,usercollage);
		sp.registerOutParameter(7,java.sql.Types.INTEGER);
		sp.execute();		
		recordsTotal=sp.getInt(7);
		ResultSet rs=sp.getResultSet();
		while(rs.next())
		{		
			CheckView ch=new CheckView();
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
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{			
			SqlConnectionUtils.free(conn, sp, rs);
		}
		ck.setRecordsTotal(recordsTotal);
		ck.setData(list);
		return ck;
	}
	//查看详情
	public List<CheckView> detail(int la_id) throws SQLException
	{
		List<CheckView> list=new ArrayList<CheckView>();
		try{
	    conn=(Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		sp=(CallableStatement) conn.prepareCall("{call baseweb.rent_detail(?)}");
		sp.setInt(1, la_id);
		sp.execute();
		ResultSet rs=sp.getResultSet();
		while(rs.next())
		{		
			CheckView ch=new CheckView();
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
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{			
			SqlConnectionUtils.free(conn, sp, rs);
		}
		
		return list;
	}
	//同意和拒绝申请
	public void getLandApplys(int flag,String la_id) throws SQLException
	{
		conn=(Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		sp=(CallableStatement) conn.prepareCall("{call baseweb.rent_approve(?,?)}");
		sp.setInt(1, flag);
		sp.setString(2,la_id);
		sp.execute();
	}
	//同意和取消交费
	public void getApplys(int flag,String la_id) throws SQLException
	{
		conn=(Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		sp=(CallableStatement) conn.prepareCall("{call baseweb.rent_pay(?,?)}");
		sp.setInt(1, flag);
		sp.setString(2,la_id);
		sp.execute();
		
	}
	
	

}
