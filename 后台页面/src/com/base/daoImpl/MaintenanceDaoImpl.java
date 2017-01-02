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

import com.base.dao.MaintenanceDao;
import com.base.po.ApplyDept;
import com.base.po.ExportBase;
import com.base.po.Maintenance;
import com.base.po.MaintenanceList;
import com.base.utils.SqlConnectionUtils;

@Repository("MaintenanceDao")
public class MaintenanceDaoImpl implements MaintenanceDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public MaintenanceList maintenance(int pageindex, int size,String order,String orderDir,String searchValue) {
	MaintenanceList ma=new MaintenanceList();
	List<Maintenance> list=new ArrayList<Maintenance>();
	int recordsTotal = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try
	{
		conn = (Connection) SessionFactoryUtils.getDataSource(
				sessionFactory).getConnection();
		sp = (CallableStatement) conn.prepareCall("{CALL baseweb.`query_prabaseinfo`(?,?,?,?,?,?)}");		
		sp.setInt(1, size);
		sp.setInt(2, pageindex);
		sp.setString(3, order);
		sp.setString(4, orderDir);
		sp.setString(5, searchValue);
		sp.registerOutParameter(6, java.sql.Types.INTEGER);
		sp.execute();
		recordsTotal = sp.getInt(6);
		rs = sp.getResultSet();
		while (rs.next())
		{
		    Maintenance ch = new Maintenance();
			ch.setId(rs.getString("id"));
			ch.setName(rs.getString("name"));
			ch.setType(rs.getString("type"));
			ch.setLandarea(rs.getString("landarea"));
			ch.setConstructionarea(rs.getString("constructionarea"));
			ch.setUndertake(rs.getInt("undertake"));
			ch.setApplydp(rs.getString("applydp"));
			ch.setBuildtime(rs.getString("buildtime"));
			ch.setLand_address(rs.getString("land_address"));
			ch.setUsername(rs.getString("username"));
			ch.setUserid(rs.getString("userid"));
			ch.setPhone(rs.getString("phone"));			
			ch.setMaterial_path(rs.getString("material_path"));
			ch.setValid_date(rs.getInt("valid_date"));
			ch.setFacemajor(rs.getString("major"));//闈㈠悜涓撲笟
			ch.setStar(rs.getInt("star"));//鏄熺骇
			list.add(ch);
		}
	} catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally
	{
		SqlConnectionUtils.free(conn, sp, rs);
	}
	ma.setRecordsTotal(recordsTotal);
	ma.setData(list);
	return ma;
    }

	@Override
	public void delInfo(String str) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		//hibernate璋冪敤瀛樺偍杩囩▼(鏃犺繑鍥炲弬鏁�
		SQLQuery sqlQuery =session.createSQLQuery("{call baseweb.`delete_prabaseinfo`(?)}");
		sqlQuery.setString(0, str);		
		sqlQuery.executeUpdate();
		session.close();	
		
	}
	
	public List<ApplyDept> getExistDept() {
		ApplyDept ad=null;
		List<ApplyDept> list=new ArrayList<ApplyDept>();
		
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn.prepareCall("{call baseweb.`query_prabasedept`()}");
			rs=sp.getResultSet();
			
			while(rs.next()){
				ad=new ApplyDept();
				ad.setAid(rs.getInt("aid"));
				ad.setDept(rs.getString("dept"));
				list.add(ad);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return list;
	     }

	//筛选
	@Override
	public MaintenanceList getshaiBaseInfo(int basetype, int dept, int star,
			int pageindex, int size) {
		MaintenanceList ma=new MaintenanceList();
		List<Maintenance> list=new ArrayList<Maintenance>();
		int recordsTotal = 0;
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try
		{
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn.prepareCall("{CALL baseweb.`filter_prabaseinfo`(?,?,?,?,?,?)}");
			sp.setInt(1, size);
			sp.setInt(2, pageindex);
			sp.setInt(3, basetype);
			sp.setInt(4, dept);
			sp.setInt(5, star);		
			
			sp.registerOutParameter(6, java.sql.Types.INTEGER);
			sp.execute();
			recordsTotal = sp.getInt(6);
			rs = sp.getResultSet();
			while (rs.next())
			{
			    Maintenance ch = new Maintenance();
				ch.setId(rs.getString("id"));
				ch.setName(rs.getString("name"));
				ch.setType(rs.getString("type"));
				ch.setLandarea(rs.getString("landarea"));
				ch.setConstructionarea(rs.getString("constructionarea"));
				ch.setUndertake(rs.getInt("undertake"));
				ch.setApplydp(rs.getString("applydp"));
				ch.setBuildtime(rs.getString("buildtime"));
				ch.setLand_address(rs.getString("land_address"));
				ch.setUsername(rs.getString("username"));
				ch.setUserid(rs.getString("userid"));
				ch.setPhone(rs.getString("phone"));			
				ch.setMaterial_path(rs.getString("material_path"));
				ch.setValid_date(rs.getInt("valid_date"));
				ch.setFacemajor(rs.getString("major"));//闈㈠悜涓撲笟
				ch.setStar(rs.getInt("star"));//鏄熺骇
				list.add(ch);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			SqlConnectionUtils.free(conn, sp, rs);
		}
		ma.setRecordsTotal(recordsTotal);
		ma.setData(list);
		return ma;
	}

	@Override
	public void updateBaseInfo(String baseid, int star, int adddate) {
		Session session=sessionFactory.openSession();	
		Maintenance mt=(Maintenance) session.get(Maintenance.class, baseid);
		mt.setStar(star);
		mt.setValid_date(mt.getValid_date()+adddate);
		session.update(mt);
		session.close();
		
	}

	//获得导出的数据
	@Override
	public List<ExportBase> getInfo(int basetype, int dept, int star) {
		
		List<ExportBase> list=new ArrayList<ExportBase>();
		ExportBase mt=null;
		 
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try
		{
			conn = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			sp = (CallableStatement) conn.prepareCall("{CALL baseweb.export_prabaseinfo(?,?,?)}");
			sp.setInt(1,basetype);
			sp.setInt(2, dept);
			sp.setInt(3, star);
			sp.execute();			
			rs = sp.getResultSet();
			while (rs.next())
			{
			    mt = new ExportBase();
				mt.setId(rs.getString("id"));
				mt.setName(rs.getString("basename"));
				mt.setType(rs.getString("basetype"));
				mt.setLandarea(rs.getString("landarea"));
				mt.setConstructionarea(rs.getString("constructionarea"));
				mt.setUndertake(rs.getInt("undertake"));
				mt.setApplydp(rs.getString("applydp"));				
				mt.setLand_address(rs.getString("land_address"));					
				mt.setFacemajor(rs.getString("mname"));//闈㈠悜涓撲笟				
				list.add(mt);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			SqlConnectionUtils.free(conn, sp, rs);
		}		
		return list;
	}

}
