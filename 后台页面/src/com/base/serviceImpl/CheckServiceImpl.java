package com.base.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.CheckViewDaoImpl;
import com.base.daoImpl.LandApplyDaoImpl;
import com.base.po.BaseInfo;
import com.base.po.CheckList;
import com.base.po.CheckView;
import com.base.po.LandApply;
import com.base.po.UserInfo;
import com.base.service.checkService;
import com.base.utils.MessageUtils;

@Service("checkService")
public class CheckServiceImpl<E> implements checkService {

    @Autowired
    private LandApplyDaoImpl landApplyDaoImpl;
    @Autowired
    private CheckViewDaoImpl checkViewDaoImpl;

    // 查询status中记录为审核的函数（status=2）
    @Override
    public CheckList getLandApply(int id, int pageindex, int size, int order,
	    String orderDir) throws SQLException {
	String columnName = "";
	if (order == 0) {
	    columnName = "id";
	} else if (order == 1) {
	    columnName = "startime";
	} else if (order == 2) {
	    columnName = "endtime";
	} else if (order == 4) {
	    columnName = "li";
	} else if (order == 8) {
	    columnName = "times";
	}
	CheckList list = checkViewDaoImpl.getLandApply(id, pageindex, size,
		null, null, null, columnName, orderDir);
	return list;
    }

    // 拒绝申请
    @Override
    public int refuseapply(String recordStr, String infoStr) {
	// 获得插入的消息语句
	String insertStr = MessageUtils.getInsertStr(infoStr, 3);	

	// 将特定编号的土地记录状态改为同类竞争
	int flag=checkViewDaoImpl.updateStatus(recordStr,2,5);
    if(flag==1){
    	// 向消息表中插入信息
    	checkViewDaoImpl.insertMessage(insertStr);
    }	
	return flag;
    }

    //同意申请
    @Override
    public int agreeApply(String landstr, String recordstr, String infostr) {
	
	// 获得插入的消息语句
	String insertStr = MessageUtils.getInsertStr(infostr, 2);	
	int tag = checkViewDaoImpl.agreeInfo(recordstr, 1);
	
	if (tag == 1) {
	    // 把相同土地的其他申请置为锁定
	    checkViewDaoImpl.lockInfo(landstr);
	    // 向消息表中插入信息
	    checkViewDaoImpl.insertMessage(insertStr);
	}

	return tag;
    }

    public int cancelPayFor(String landstr, String recordstr, String infostr) {

	// 获得插入的消息语句
	String insertStr = MessageUtils.getInsertStr(infostr, 5);

	// 把特定记录的状态改为未交费
	int flag=checkViewDaoImpl.updateStatus(recordstr, 1,10);
	
    if(flag==1){
    	// 把相同土地的状态为锁定的土地状态变为审核中
    	checkViewDaoImpl.releaseInfo(landstr);

    	// 向消息表中插入信息
    	checkViewDaoImpl.insertMessage(insertStr);
    }
	
      return flag;
    }

    // 确认交费
    @Override
    public int confirmPayFor(String landstr, String recordstr, String infostr) {

	// 获得插入的消息语句
	String insertStr = MessageUtils.getInsertStr(infostr, 6);

	// 把特定记录的状态改为申请成功，并将记录插入土地租赁历史表中
	int flag=checkViewDaoImpl.payForSuccess(recordstr, 6);
    if(flag==1){
    	// 把相同土地的状态为锁定的土地状态变为同类竞争
    	checkViewDaoImpl.confirmInfo(landstr);
    	// 向消息表中插入信息
    	checkViewDaoImpl.insertMessage(insertStr);
    }
	 return flag;
    }

    // 详情查看
    @Override
    public List<CheckView> Rentdetail(int la_id) throws SQLException {
	List<CheckView> list = checkViewDaoImpl.detail(la_id);
	return list;
    }

    // 详情查看2
    @Override
    public List<CheckView> Rentdetail2(int la_id) throws SQLException {
	List<CheckView> list = checkViewDaoImpl.detail(la_id);
	return list;
    }

    // 刷选
    public CheckList getInfo(int flag, int startIndex, int pageindex,
	    String basename, String username, String usercollage, int order,
	    String orderDir) throws SQLException {

	String columnName = "";
	if (order == 0) {
	    columnName = "id";
	} else if (order == 1) {
	    columnName = "startime";
	} else if (order == 2) {
	    columnName = "endtime";
	} else if (order == 4) {
	    columnName = "li";
	} else if (order == 8) {
	    columnName = "times";
	}
	CheckList list = checkViewDaoImpl.getLandApply(flag, startIndex,
		pageindex, basename, username, usercollage, columnName,
		orderDir);
	return list;
    }

    // 基地查询
    @Override
    public List<BaseInfo> getBaseInfos() {
	List<BaseInfo> list = checkViewDaoImpl.getBaseInfos();
	return list;
    }

    // 申请人查询
    @Override
    public List<UserInfo> getappliInfos() throws SQLException {
	List<UserInfo> list = checkViewDaoImpl.getappliInfos();
	return list;
    }

    // 申请部门查询
    @Override
    public List<UserInfo> getDept() throws SQLException {
	// List<UserInfo> list=checkViewDaoImpl.getDept();
	return null;
    }

    @Override
    public void getApplys(int flag, String la_id) throws SQLException {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Map<String, String>> getCheckDept() {

	List<Map<String, String>> list = checkViewDaoImpl.getCheckDept();

	return list;
    }

    @Override
    public List getList() {

	List list = new ArrayList();

	List<BaseInfo> baseinfo = checkViewDaoImpl.getBaseInfos();
	List<Map<String, String>> checkApplicant = checkViewDaoImpl
		.getCheckApplicant();// 租赁审批未审核的申请人
	List<Map<String, String>> payApplicant = checkViewDaoImpl
		.getPayApplicant();// 租赁审批未交费的申请人
	List<Map<String, String>> checkDept = checkViewDaoImpl.getCheckDept();// 租赁审批未审核的部门
	List<Map<String, String>> payDept = checkViewDaoImpl.getPayDept();// 租赁审批未交费的部门
	List<Map<String, String>> overdueDept = checkViewDaoImpl
		.getoverdueDept();// 租赁审批逾期的部门
	List<Map<String, String>> overdueApplicant = checkViewDaoImpl
		.getoverdueApplicant();// 租赁审批逾期的申请人

	list.add(baseinfo);
	list.add(checkApplicant);
	list.add(payApplicant);
	list.add(checkDept);
	list.add(payDept);
	list.add(overdueDept);
	list.add(overdueApplicant);

	return list;
    }

    // 逾期恢复
    @Override
    public void overduerecovery(String recordStr, String infoStr) {
	// 获得插入的消息语句
	String insertStr = MessageUtils.getInsertStr(infoStr, 20);
	System.out.println(insertStr);

	// 
	checkViewDaoImpl.overduerecovery(recordStr);

	// 向消息表中插入信息
	checkViewDaoImpl.insertMessage(insertStr);

    }
}
