package com.base.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.base.po.BaseInfo;
import com.base.po.CheckList;
import com.base.po.CheckView;
import com.base.po.LandApply;
import com.base.po.UserInfo;

//审核模块业务逻辑层接口
public interface checkService {
	/*
	   参数说明：无参
	   返回值：   List<CheckView>,为土地申请视图CheckView对象的集合
	   函数功能：查询所有土地申请记录(获得的记录按申请时间，默认为倒序)
	 */
	public CheckList getLandApply(int id,int pageindex,int size,int order,String orderDir) throws SQLException  ;
	/**
	 * 
	 * @param flag 0为拒绝，1为同意
	 * @param la_id
	 * @throws SQLException
	 */
	public int refuseapply(String recordStr,String infoStr);
	/**
	 * 逾期恢复
	 * @param recordStr
	 * @param infoStr
	 */
	public void overduerecovery(String recordStr,String infoStr);

    /*
     * 详情查看table1(tableCheck)
     */
	public List<CheckView> Rentdetail(int la_id) throws SQLException;
	/*
	 * 详情查看table2(tablePay)
	 */
	public List<CheckView> Rentdetail2(int la_id) throws SQLException;
	/*
	 * 筛选
	 */
	public CheckList getInfo(int flag,int pageindex,int size,String basename,String username,String usercollage,int order,String orderDir) throws SQLException;
	/*
	 * 基地 查询
	 */
	public List<BaseInfo> getBaseInfos();
	/*
	 * 申请人查询
	 */
	public List<UserInfo> getappliInfos() throws SQLException;

    /**
     * 查看部门
     * @return
     * @throws SQLException
     */
	public List<UserInfo> getDept() throws SQLException;

    /**
     * 交费
     * @param flag  状态值1为取消0为确认
     * @param la_id id号
     * @throws SQLException
     */
	public void getApplys(int flag,String la_id) throws SQLException;
	
	
	public int agreeApply(String landstr,String recordstr,String infostr);
	
	public int cancelPayFor(String landstr, String recordstr, String infostr);
	
	public int confirmPayFor(String landstr, String recordstr, String infostr);
	
	public List<Map<String,String>> getCheckDept();
	
	public List getList();
}
