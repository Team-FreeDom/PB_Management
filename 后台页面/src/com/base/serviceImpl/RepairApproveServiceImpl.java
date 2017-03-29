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

    // 同意申请
    @Override
    public int agreeRepairApply(String agreestr, String infostr) {
	
	// 将特定记录状态改为14,维修中
	int flag=repairApproveDao.changeStatus(agreestr, 14);
	if(flag==1){
	 // 获得插入语句
		String insertStr = MessageUtils.getinfoMs(infostr, 12);
		// 插入语句
		checkViewDaoImpl.insertMessage(insertStr);
	}
	return flag;

    }

    @Override
    public MaintainList getRepairInfo(String baseid, String userid,
	    int pageIndex, int size, int order, String orderDir,
	    String searchValue, int status) {
	String columnName = "";
	if (order == 2) {
	    columnName = "basename";
	} else if (order == 0) {
	    columnName = "id";
	} else if (order == 3) {
	    columnName = "username";
	} else if (order == 4) {
	    columnName = "apply_time";
	}
	MaintainList list = repairApproveDao.getRepairInfo(baseid, userid,
		pageIndex, size, columnName, orderDir, searchValue, status);
	return list;
    }

    // 拒绝报修申请
    @Override
    public int refuseRepairApply(String recorddigit, String refusestr,
	    String infostr) {
	int flag = repairApproveDao.refuseApply(recorddigit, refusestr);
	if (flag == 1) {
	    // 获得插入语句
	    String insertStr = MessageUtils.getinfoMs(infostr, 13);
	    // 插入语句
	    checkViewDaoImpl.insertMessage(insertStr);
	}
	return flag;

    }

    @Override
    public List getInfoApply() {

	List list = new ArrayList();
	List list1 = repairApproveDao.getUser(13);
	List list2 = repairApproveDao.getBase(13);
	List list3 = repairApproveDao.getUser(14);
	List list4 = repairApproveDao.getBase(14);
	list.add(list1);
	list.add(list2);
	list.add(list3);
	list.add(list4);
	return list;
    }

    // 维修完成
    @Override
    public void finishRepairApply(String storestr, String infostr) {
	// 获得插入语句
	String insertStr = MessageUtils.getinfoMs(infostr, 14);
	//
	repairApproveDao.finish(storestr);
	// 插入语句
	checkViewDaoImpl.insertMessage(insertStr);

    }

}
