package com.base.serviceImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.bridge.MessageUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Service;

import com.base.dao.RepairApproveDao;
import com.base.daoImpl.CheckViewDaoImpl;
import com.base.po.MaintainList;
import com.base.service.RepairApproveService;
import com.base.utils.MessageUtils;
import com.base.utils.SqlConnectionUtils;

@Service("repairApproveService")
public class RepairApproveServiceImpl implements RepairApproveService {

	@Autowired
	private RepairApproveDao repairApproveDao;	
	@Autowired
	private CheckViewDaoImpl checkViewDaoImpl;
	
   //同意申请
	@Override
	public void agreeRepairApply(String agreestr,String infostr) {
		//获得通知消息字符串
		String insertStr=MessageUtils.getinfoMs(infostr,12);
		//改变记录的状态，将特定记录的状态由申请中改为维修中
		repairApproveDao.changeStatus(agreestr, 14);
		//向消息表中插入信息 
		 checkViewDaoImpl.insertMessage(insertStr);
		
	}

	@Override
	public MaintainList getRepairInfo(String baseid, String userid,
			int pageIndex, int size, int order, String orderDir,
			String searchValue, int status) {
		String columnName="";
		if(order==2){
			columnName="basename";
		}else if(order==0){
			columnName="id";
		}else if(order==3){
			columnName="username";
		}else if(order==4){
			columnName="apply_time";
		}
		MaintainList list=repairApproveDao.getRepairInfo(baseid, userid, pageIndex, size, columnName, orderDir, searchValue, status);
		return list;
	}

	//拒绝申请
	@Override
	public void refuseRepairApply(String refusestr,String infostr) {
		//获得通知消息字符串
		String insertStr=MessageUtils.getinfoMs(infostr,13);
		//改变记录的状态，将特定记录的状态由申请中改为申请失败
		repairApproveDao.refuseApply(refusestr);
		//向消息表中插入信息 
		checkViewDaoImpl.insertMessage(insertStr);
		
	}

	@Override
	public List getInfoApply() {
		
		List list=new ArrayList();
		List list1=repairApproveDao.getUser(13);
		List list2=repairApproveDao.getBase(13);
		List list3=repairApproveDao.getUser(14);
		List list4=repairApproveDao.getBase(14);
		list.add(list1);
		list.add(list2);
		list.add(list3);
		list.add(list4);
		return list;
	}

	//维修完成
	@Override
	public void finishRepairApply(String storestr, String infostr) {
		//获得通知消息字符串
		String insertStr=MessageUtils.getinfoMs(infostr,14);
		//改变记录的状态，将特定记录的状态由申请中改为申请失败
		repairApproveDao.finish(storestr);
		//向消息表中插入信息 
		checkViewDaoImpl.insertMessage(insertStr);
		
	}

	

}
