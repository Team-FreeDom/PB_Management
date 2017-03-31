package com.base.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.BaseCheckDao;
import com.base.dao.MyBaseInfoDao;
import com.base.po.MyBaseList;
import com.base.service.MyBaseInfoService;
import com.base.utils.MessageUtils;

@Service("MyBaseInfoService")
public class MyBaseInfoServiceImpl implements MyBaseInfoService {

	@Autowired
	private MyBaseInfoDao mybaseinfodao;
	@Autowired
	private BaseCheckDao basecheckdao;

	@Override
	// 页面一
	public MyBaseList MybaseInfo(int pageindex, int size, String columnName,
			String orderDir, int year, int status, String userid) {
		System.out.println(pageindex + "pageindex");

		MyBaseList list = mybaseinfodao.MybaseInfo(pageindex, size, columnName,
				orderDir, year, status, userid);
		return list;
	}

	@Override
	// 页面二
	public MyBaseList MybaseInfo2(int pageindex, int size, int order,
			String orderDir, int year, int status, String userid) {
		String columnName = "";
		if (order == 0) {
			columnName = "id";
		} else if (order == 1) {
			columnName = "type";
		} else if (order == 2) {
			columnName = "dept";
		} else if (order == 16) {
			columnName = "descp";
		}

		System.out.println(userid + "userid");
		MyBaseList list = mybaseinfodao.MybaseInfo(pageindex, size, columnName,
				orderDir, year, status, userid);
		return list;
	}

	// 撤回
	@Override
	public int recall(String id, String infostr, int tag) {

		int flag = 0;
		int number_0 = 0;
		if (tag == 2) {
			flag = mybaseinfodao.changeThisStatus(id, 2, 11);
			number_0 = 10;
		} else if (tag == 18) {
			flag = mybaseinfodao.changeThisStatus(id, 18, 17);
			number_0 = 18;
		}
		// 获得插入的消息语句
		if (flag == 1) {
			String insertStr = MessageUtils.getinfoMs(infostr, number_0);
			// 向消息表中插入信息
			mybaseinfodao.insertMessage(insertStr);
		}		
		return flag;
	}

	@Override
	public void updateDate(int id, String adddate) {

		mybaseinfodao.updateDate(id, adddate);
	}

}
