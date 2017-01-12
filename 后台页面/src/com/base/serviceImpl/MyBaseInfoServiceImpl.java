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
	System.out.println(pageindex+"pageindex");
	    
	   
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
	    columnName = "basename";
	} else if (order == 2) {
	    columnName = "basetype";
	} else if (order == 3) {
	    columnName = "dept";
	} else if (order == 15) {
	    columnName = "descp";
	}
	
	     
	   
	    System.out.println(userid+"userid");
	MyBaseList list = mybaseinfodao.MybaseInfo(pageindex, size, columnName,
		orderDir, year, status, userid);
	return list;
    }
   //撤回
    @Override
    public void recall(String id, String infostr) {
	// 获得插入的消息语句
	String insertStr = MessageUtils.getinfoMs(infostr,10);
	System.out.println(insertStr + "到底是什么信息");
	// 撤回（str:字符串id；11：失效的状态值）
	String recordstr="("+id+","+"null"+","+"11"+")";
	System.out.println(recordstr+"包装成什么样");
	basecheckdao.refuseapply(recordstr);
	// 向消息表中插入信息
	mybaseinfodao.insertMessage(insertStr);
    }

	@Override
	public void updateDate(String baseid, int adddate) {
		
		mybaseinfodao.updateDate(baseid,adddate);
	}

}
