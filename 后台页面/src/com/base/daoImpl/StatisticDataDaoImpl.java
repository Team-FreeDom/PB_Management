package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.StatisticDataDao;
import com.base.po.AllPlan;
import com.base.po.Classcourse;
import com.base.po.PlanList;
import com.base.po.PracticeCollection;
import com.base.po.StatisticData;
import com.base.po.basetype;
import com.base.utils.SqlConnectionUtils;

@Repository("StatisticDataDao")
public class StatisticDataDaoImpl implements StatisticDataDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PlanList statisticData(int pageindex, int size, String columnName,
	    String orderDir, String semester) {
	List<AllPlan> list = new ArrayList<AllPlan>();
	int recordsTotal = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	    try {
		conn = (Connection) SessionFactoryUtils.getDataSource(
			sessionFactory).getConnection();
		sp = (CallableStatement) conn
			.prepareCall("{call baseweb.query_teachercoursearranges(?,?,?,?,?,?)}");
		sp.setInt(1, pageindex);
		sp.setInt(2, size);
		sp.setString(3, columnName);
		sp.setString(4, orderDir);
		sp.setString(5, semester);
		sp.registerOutParameter(6, java.sql.Types.INTEGER);
		sp.execute();
		recordsTotal = sp.getInt(6);
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
		    // System.out.println(ch.getCheckMethod()+"有没有");
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

    // 获取基地类型
    @SuppressWarnings("null")
    @Override
    public List<basetype> getDept(String semester) {
	List<basetype> list = new ArrayList<basetype>();
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL baseweb.query_basetype(?)}");
	    sp.setString(1, semester);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {
		basetype ba=new basetype(rs.getString("source"));		
		list.add(ba);
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    SqlConnectionUtils.free(conn, sp, rs);
	}
	return list;
    }
 // 获取记录已有的学院
    @SuppressWarnings("null")
    @Override
    public List<Map<String, String>> getCollege(String semester) {
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Map<String, String> map = null;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL query_coursearrangecollege(?)}");
	    sp.setString(1, semester);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {	
		map = new HashMap<String, String>();
		map.put("college", rs.getString("college"));		
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
   //获取基地名字
    @Override
    public List<Map<String, String>> getName(String semester, String basename) {
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Map<String, String> map = null;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL query_base(?,?)}");
	    sp.setString(1, semester);
	    sp.setString(2, basename);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {	
		map = new HashMap<String, String>();
		map.put("site", rs.getString("site"));		
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
   //获取专业
    @Override
    public List<Map<String, String>> getMajor(String semester, String college) {
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Map<String, String> map = null;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	try {
	    conn = (Connection) SessionFactoryUtils.getDataSource(
		    sessionFactory).getConnection();
	    sp = (CallableStatement) conn
		    .prepareCall("{CALL baseweb.`query_major`(?,?)}");
	    sp.setString(1, semester);
	    sp.setString(2, college);
	    sp.execute();
	    rs = sp.getResultSet();
	    while (rs.next()) {	
		map = new HashMap<String, String>();
		map.put("major", rs.getString("major"));
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
    //刷选
    @Override
    public PlanList statisticDataBrush(String semester,
	    String basetype, String basename, String college,String grade, 
	    String major, String class1,int pageindex, int size,
	    String columnName, String orderDir ) {
	
	List<AllPlan> list = new ArrayList<AllPlan>();
	int recordsTotal = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	    try {
		conn = (Connection) SessionFactoryUtils.getDataSource(
			sessionFactory).getConnection();
		sp = (CallableStatement) conn
			.prepareCall("{call baseweb.`filter_teachercoursearrange`(?,?,?,?,?,?,?,?,?,?,?,?)}");
		sp.setString(1, semester);		
		sp.setString(2, basetype);		
		sp.setString(3, basename);		
		sp.setString(4, college);		
		sp.setString(5, grade);		
		sp.setString(6, major);		
		sp.setString(7, class1);		
		sp.setInt(8, pageindex);		
		sp.setInt(9, size);		
		sp.setString(10, columnName);	
		sp.setString(11, orderDir);		
		sp.registerOutParameter(12, java.sql.Types.INTEGER);
		sp.execute();
		recordsTotal = sp.getInt(12);
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
		    // System.out.println(ch.getCheckMethod()+"有没有");
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
    
   //获取导出的数据 
    @Override
    public List<PracticeCollection> getExportstatisticData(String semester,
    	    String basetype, String basename, String college,String grade, 
    	    String major, String class1) {
    	
    	Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;

		List<PracticeCollection> list = new ArrayList<PracticeCollection>();
		PracticeCollection pc = null;
		List<Classcourse> lra = plandata(semester,basetype,basename,grade,college,major,class1);

		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn.prepareCall("{CALL  baseweb.export_courarrange(?,?,?,?,?,?,?)}");
			sp.setString(1, semester);
			sp.setString(2, basetype);
			sp.setString(3, basename);
			sp.setString(4, grade);
			sp.setString(5, college);
			sp.setString(6, major);
			sp.setString(7, class1);			
			sp.execute(); // 执行存储过程
			rs = sp.getResultSet(); // 获得结果集

			while (rs.next()) // 遍历结果集，赋值给list
			{
				pc = new PracticeCollection();

				pc.setComposition(rs.getString("composition"));
				pc.setCountPeople("学习人数:"+rs.getString("count"));
				pc.setCourseId(rs.getString("cid"));
				pc.setCourseName(rs.getString("coursename"));
				pc.setCredit("学分:"+rs.getString("credit"));
				pc.setDepartment(rs.getString("college"));
				pc.setMajor_oriented(rs.getString("major_oriented"));
				pc.setWeekCount("实习周数:"+rs.getString("week"));
				
				List<Classcourse> lis = new ArrayList<Classcourse>();
				int i=0;
				for (Classcourse ra : lra) {
					if (String.valueOf(rs.getInt("id")).equals(ra.getCourse())) {
						lis.add(ra);
						
					}
					
				}				
				pc.setData(lis);
				list.add(pc);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);

		}

		return list;	
        }
    
    //获取导出的数据    
    @Override
    public List<String> getstatisticCount(String semester) {
    	Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;

		List<String> list = new ArrayList<String>();	

		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn.prepareCall("{CALL baseweb.count_basetypenum(?,?,?,?,?,?)}");
			sp.setString(1, semester);
			sp.registerOutParameter(2, java.sql.Types.INTEGER);
			sp.registerOutParameter(3, java.sql.Types.INTEGER);
			sp.registerOutParameter(4, java.sql.Types.INTEGER);
			sp.registerOutParameter(5, java.sql.Types.INTEGER);
			sp.registerOutParameter(6, java.sql.Types.INTEGER);			
			sp.execute(); // 执行存储过程
			rs = sp.getResultSet(); // 获得结果集
            list.add(String.valueOf(sp.getInt(2)));
            list.add(String.valueOf(sp.getInt(3)));
            list.add(String.valueOf(sp.getInt(4)));
            list.add(String.valueOf(sp.getInt(5)));
            list.add(String.valueOf(sp.getInt(6)));
           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);

		}
		return list;
    }
    
    @Override
    public List<Classcourse> plandata(String semester,
    	    String basetype, String basename, String college,String grade, 
    	    String major, String class1){
    	List<Classcourse> list = new ArrayList<Classcourse>();
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn.prepareCall("{CALL baseweb.find_classcourarrange(?,?,?,?,?,?,?)}");
			sp.setString(1, semester);
			sp.setString(2, basetype);
			sp.setString(3, basename);
			sp.setString(4, grade);
			sp.setString(5, college);
			sp.setString(6, major);
			sp.setString(7, class1);		
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
				ch.setCourse(rs.getString("course"));
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
    
  //基地利用率记录
    @Override
    public StatisticData BaseUseRatio(String semester) {
	StatisticData st=new StatisticData();
	int typenum = 0;
	int namenum = 0;
	int collegenum = 0;
	int majornum = 0;
	int teachernum = 0;
	int classnum = 0;
	int expensenum = 0;
	int personnum = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	    try {
		conn = (Connection) SessionFactoryUtils.getDataSource(
			sessionFactory).getConnection();
		sp = (CallableStatement) conn
			.prepareCall("{call baseweb.`count_coursearrange`(?,?,?,?,?,?,?,?,?)}");
		sp.setString(1, semester);
		sp.registerOutParameter(2, java.sql.Types.INTEGER);
		sp.registerOutParameter(3, java.sql.Types.INTEGER);
		sp.registerOutParameter(4, java.sql.Types.INTEGER);
		sp.registerOutParameter(5, java.sql.Types.INTEGER);
		sp.registerOutParameter(6, java.sql.Types.INTEGER);
		sp.registerOutParameter(7, java.sql.Types.INTEGER);
		sp.registerOutParameter(8, java.sql.Types.INTEGER);
		sp.registerOutParameter(9, java.sql.Types.INTEGER);
		sp.execute();
		typenum = sp.getInt(2);
		namenum = sp.getInt(3);
		collegenum = sp.getInt(4);
		majornum = sp.getInt(5);
		teachernum = sp.getInt(6);
		classnum = sp.getInt(7);
		expensenum = sp.getInt(8);
		personnum = sp.getInt(9);
		rs = sp.getResultSet();
		st.setTypenum(typenum);
		st.setNamenum(namenum);
		st.setCollegenum(collegenum);
		st.setMajornum(majornum);
		st.setTeachernum(teachernum);
		st.setClassnum(classnum);
		st.setExpensenum(expensenum);
		st.setPersonnum(personnum);
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } finally {
		SqlConnectionUtils.free(conn, sp, rs);
	    }
	return st;
    }
  //基地利用率刷选
    @Override
    public StatisticData BaseUseRatioBrush(String semester, String basetype,
	    String basename, String grade, String college, String major,
	    String class1, String teacherName) {
	StatisticData st=new StatisticData();
	int typenum = 0;
	int namenum = 0;
	int collegenum = 0;
	int majornum = 0;
	int teachernum = 0;
	int classnum = 0;
	int expensenum = 0;
	int personnum = 0;
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	    try {
		conn = (Connection) SessionFactoryUtils.getDataSource(
			sessionFactory).getConnection();
		sp = (CallableStatement) conn
			.prepareCall("{call baseweb.`filter_countcoursearrange`(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		sp.setString(1, semester);
		sp.setString(2, basetype);
		sp.setString(3, basename);
		sp.setString(4, grade);
		sp.setString(5, college);
		sp.setString(6, major);
		sp.setString(7, class1);
		sp.setString(8, teacherName);
		sp.registerOutParameter(9, java.sql.Types.INTEGER);
		sp.registerOutParameter(10, java.sql.Types.INTEGER);
		sp.registerOutParameter(11, java.sql.Types.INTEGER);
		sp.registerOutParameter(12, java.sql.Types.INTEGER);
		sp.registerOutParameter(13, java.sql.Types.INTEGER);
		sp.registerOutParameter(14, java.sql.Types.INTEGER);
		sp.registerOutParameter(15, java.sql.Types.INTEGER);
		sp.registerOutParameter(16, java.sql.Types.INTEGER);
		sp.execute();
		typenum = sp.getInt(9);
		
		namenum = sp.getInt(10);
		
		collegenum = sp.getInt(11);
		
		majornum = sp.getInt(12);
		
		teachernum = sp.getInt(13);
		
		classnum = sp.getInt(14);
		
		expensenum = sp.getInt(15);
		
		personnum = sp.getInt(16);
		
		rs = sp.getResultSet();
		st.setTypenum(typenum);
		
		st.setNamenum(namenum);
		st.setCollegenum(collegenum);
		st.setMajornum(majornum);		
		st.setTeachernum(teachernum);
		
		st.setClassnum(classnum);
		st.setExpensenum(expensenum);
		st.setPersonnum(personnum);
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } finally {
		SqlConnectionUtils.free(conn, sp, rs);
	    }
	return st;
    }

	@Override
	public List<String> getAllStatisticCount() {
		Connection conn = null;
		CallableStatement sp = null;
		ResultSet rs = null;

		List<String> list = new ArrayList<String>();	

		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn.prepareCall("{CALL baseweb.count_baseenum(?,?,?,?,?)}");
			sp.registerOutParameter(1, java.sql.Types.INTEGER);
			sp.registerOutParameter(2, java.sql.Types.INTEGER);
			sp.registerOutParameter(3, java.sql.Types.INTEGER);
			sp.registerOutParameter(4, java.sql.Types.INTEGER);
			sp.registerOutParameter(5, java.sql.Types.INTEGER);						
			sp.execute(); // 执行存储过程
			rs = sp.getResultSet(); // 获得结果集
			list.add(String.valueOf(sp.getInt(1)));
            list.add(String.valueOf(sp.getInt(2)));
            list.add(String.valueOf(sp.getInt(3)));
            list.add(String.valueOf(sp.getInt(4)));
            list.add(String.valueOf(sp.getInt(5)));
           
           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);

		}
		return list;
	}

}
