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
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.MaintenanceDao;
import com.base.po.ApplyDept;
import com.base.po.ExportBase;
import com.base.po.Prabaseinfo;
import com.base.po.MaintenanceList;
import com.base.utils.SqlConnectionUtils;

@Repository("MaintenanceDao")
public class MaintenanceDaoImpl implements MaintenanceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public MaintenanceList maintenance(int pageindex, int size, String order,
			String orderDir, String searchValue) {
		MaintenanceList ma = new MaintenanceList();
		List<Prabaseinfo> list = new ArrayList<Prabaseinfo>();
		int recordsTotal = 0;
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		// System.out.println(' '+size+','+pageindex+','+order+','+orderDir+','+searchValue);
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.`query_prabaseinfo`(?,?,?,?,?,?)}");
			sp.setInt(1, size);
			sp.setInt(2, pageindex);
			sp.setString(3, order);
			sp.setString(4, orderDir);
			sp.setString(5, searchValue);
			sp.registerOutParameter(6, java.sql.Types.INTEGER);
			sp.execute();
			recordsTotal = sp.getInt(6);
			rs = sp.getResultSet();
			while (rs.next()) {
				Prabaseinfo ch = new Prabaseinfo();
				ch.setId(rs.getString("id"));
				ch.setName(rs.getString("basename"));
				ch.setType(rs.getString("basetype"));
				ch.setLandarea(rs.getString("landarea"));
				ch.setConstructionarea(rs.getString("constructionarea"));
				ch.setUndertake(rs.getInt("undertake"));
				ch.setApplydp(rs.getString("dept"));
				ch.setBuildtime(rs.getString("buildtime"));
				ch.setLand_address(rs.getString("land_address"));
				ch.setUsername(rs.getString("username"));
				ch.setPhone(rs.getString("phone"));
				ch.setMaterial_path(rs.getString("material_path"));			
				ch.setFacemajor(rs.getString("mname"));// 闂傚牄鍨归幃婊勭▔閹捐尙鐟�
				ch.setStar(rs.getInt("star"));// 闁哄嫮鍠撴锟�
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
		ma.setRecordsTotal(recordsTotal);
		ma.setData(list);
		return ma;
	}

	@Override
	public void delInfo(String str) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		// hibernate閻犲鍟伴弫銈囷拷濡搫浜堕弶鈺佹川閳伙拷闁哄啰濮剧换鎴﹀炊閻愭彃妫橀柡渚婃嫹
		SQLQuery sqlQuery = session
				.createSQLQuery("{call baseweb.`delete_prabaseinfo`(?)}");
		sqlQuery.setString(0, str);
		sqlQuery.executeUpdate();
		session.close();

	}

	public List<ApplyDept> getExistDept() {
		ApplyDept ad = null;
		List<ApplyDept> list = new ArrayList<ApplyDept>();

		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;

		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{call baseweb.`query_prabasedept`()}");
			sp.execute();
			rs = sp.getResultSet();
			while (rs.next()) {
				ad = new ApplyDept();
				ad.setAid(rs.getInt("aid"));
				ad.setDept(rs.getString("dept"));
				list.add(ad);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}

		return list;
	}

	// 缁涙盯锟�
	@Override
	public MaintenanceList getshaiBaseInfo(int basetype, int dept, int star,
			int pageindex, int size, String order, String orderDir,
			String searchValue) {
		MaintenanceList ma = new MaintenanceList();
		List<Prabaseinfo> list = new ArrayList<Prabaseinfo>();
		int recordsTotal = 0;
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.`filter_prabaseinfo`(?,?,?,?,?,?,?,?,?)}");
			sp.setInt(1, size);
			sp.setInt(2, pageindex);
			sp.setString(3, order);
			sp.setString(4, orderDir);
			sp.setString(5, searchValue);
			sp.setInt(6, basetype);
			sp.setInt(7, dept);
			sp.setInt(8, star);

			sp.registerOutParameter(9, java.sql.Types.INTEGER);
			sp.execute();
			recordsTotal = sp.getInt(9);
			rs = sp.getResultSet();
			while (rs.next()) {
				Prabaseinfo ch = new Prabaseinfo();
				ch.setId(rs.getString("id"));
				ch.setName(rs.getString("basename"));
				ch.setType(rs.getString("basetype"));
				ch.setLandarea(rs.getString("landarea"));
				ch.setConstructionarea(rs.getString("constructionarea"));
				ch.setUndertake(rs.getInt("undertake"));
				ch.setApplydp(rs.getString("dept"));
				ch.setBuildtime(rs.getString("buildtime"));
				ch.setLand_address(rs.getString("land_address"));
				ch.setUsername(rs.getString("username"));
				ch.setPhone(rs.getString("phone"));
				ch.setMaterial_path(rs.getString("material_path"));				
				ch.setFacemajor(rs.getString("mname"));// 闂傚牄鍨归幃婊勭▔閹捐尙鐟�
				ch.setStar(rs.getInt("star"));// 闁哄嫮鍠撴锟�
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
		ma.setRecordsTotal(recordsTotal);
		ma.setData(list);
		return ma;
	}

	@Override
	public void updateBaseInfo(String baseid, int star, String adddate) {
		Connection conn = null;
		CallableStatement sp = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.base_management(?,?,?)}");
			sp.setString(1, baseid);
			sp.setString(2, adddate);
			sp.setInt(3, star);
			sp.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, null);
		}
		

	}

	// 閼惧嘲绶辩�鐓庡毉閻ㄥ嫭鏆熼幑锟� @Override
	public List<ExportBase> getInfo(int basetype, int dept, int star) {

		List<ExportBase> list = new ArrayList<ExportBase>();
		ExportBase mt = null;

		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.export_prabaseinfo(?,?,?)}");
			sp.setInt(1, basetype);
			sp.setInt(2, dept);
			sp.setInt(3, star);
			System.out.println("basetype:"+basetype+", dept:"+dept+", star:"+star);
			sp.execute();
			rs = sp.getResultSet();
			while (rs.next()) {
				mt = new ExportBase();
				mt.setId(rs.getString("id"));
				mt.setName(rs.getString("basename"));
				mt.setType(rs.getString("basetype"));
				mt.setLandarea(rs.getString("landarea"));
				mt.setConstructionarea(rs.getString("constructionarea"));
				mt.setUndertake(rs.getInt("undertake"));
				mt.setApplydp(rs.getString("dept"));
				mt.setDeptId(rs.getInt("applydp"));
				mt.setLand_address(rs.getString("land_address"));
				mt.setFacemajor(rs.getString("mname"));// 
				list.add(mt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}
		return list;
	}

	//向基地信息表prabaseinfo中插入一条数据
	@Override
	public void increaseBaseInfo(String str1, String str2) {
		Connection conn = null;
		CallableStatement sp = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{call baseweb.add_base(?,?)}");
			sp.setString(1, str1);
			sp.setString(2, str2);
			System.out.println("str1:"+str1+";  str2:"+str2);
			sp.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, null);
		}
		
		
	}
	
	//判断是否存在基地名称，若不存在，则插入基地信息你，否则不插入
		@Override
		public int judge_insert_base(String userid, String recordStr1,String recordStr2,String recordStr3,int count,String resultStr4) {
			Connection conn = null;
			CallableStatement sp = null;
			System.out.println("record1:"+recordStr1);
			System.out.println("record2:"+recordStr2);
			System.out.println("record3:"+recordStr3);			
			System.out.println("userid:"+userid);
			System.out.println("record4:"+resultStr4);
			System.out.println("count:"+count);
			int flag=0;
			try {
				conn = (Connection) SessionFactoryUtils.getDataSource(
						sessionFactory).getConnection();
				sp = (CallableStatement) conn
						.prepareCall("{call baseweb.import_prabaseinfo(?,?,?,?,?,?,?)}");
				sp.setString(1, recordStr1);
				sp.setString(2, recordStr2);
				sp.setString(3, recordStr3);
				sp.setString(4, userid);
				sp.setInt(5, count);
				sp.setString(6, resultStr4);
				sp.registerOutParameter(7, java.sql.Types.INTEGER);
				sp.execute();
				flag=sp.getInt(7);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				SqlConnectionUtils.free(conn, sp, null);
			}
			System.out.println("flag:"+flag);
			return flag;
		}

}
