package com.base.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.MymaintainDao;
import com.base.po.MymaintainList;
import com.base.service.MymaintainService;
import com.base.utils.MessageUtils;

@Service("MymaintainService")
public class MymaintainServiceImpl implements MymaintainService {
    @Autowired
    private MymaintainDao mymaintaindao;

    // 页面一
    @Override
    public MymaintainList Mymaintain(int pageindex, int size,
	    String columnName, String orderDir, int year, int status,
	    String userid) {
	MymaintainList list = mymaintaindao.Mymaintain(pageindex, size,
		columnName, orderDir, year, status, userid);
	return list;
    }

    // 页面二
    @Override
    public MymaintainList Mymaintain2(int pageindex, int size, int order,
	    String orderDir, int year, int status, String userid) {
	String columnName = "";
	if (order == 0) {
	    columnName = "id";
	} else if (order == 1) {
	    columnName = "basename";
	} else if (order == 2) {
	    columnName = "username";
	} else if (order == 3) {
	    columnName = "apply_time";
	}
	MymaintainList list = mymaintaindao.Mymaintain(pageindex, size,
		columnName, orderDir, year, status, userid);
	return list;
    }

    // 撤回
    @Override
    public int recallmymaint(String id, String infostr) {
	
	int flag = mymaintaindao.recallmymaint(id);
	if (flag == 1) {
	    // 获得插入的消息语句
	    String insertStr = MessageUtils.getinfoMs(infostr, 11);
	    System.out.println(insertStr + "到底是什么信息");
	    // 向消息表中插入信息
	    mymaintaindao.insertMessage(insertStr);
	}
	return flag;

    }

}
