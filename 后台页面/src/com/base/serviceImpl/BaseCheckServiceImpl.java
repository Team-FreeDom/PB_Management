package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.BaseCheckDao;
import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;
import com.base.service.BaseCheckService;
import com.base.utils.MessageUtils;

@Service("BaseCheckService")
public class BaseCheckServiceImpl implements BaseCheckService {

    @Autowired
    private BaseCheckDao basecheckdao;

    /**
     * 
     * @param applydpid
     *            申请部门id
     * @param pageindex
     *            当前页数
     * @param size
     *            当前记录数
     * @param order
     *            排序列
     * @param orderDir
     *            排序顺序
     * @return 基地审核信息
     */
    @Override
    public BaseCheckList getBaseCheck(int applydpid, int pageindex, int size,
	    int order, String orderDir) {
	String columnName = "";
	if (order == 0) {
	    columnName = "id";
	} else if (order == 1) {
	    columnName = "basename";
	} else if (order == 2) {
	    columnName = "basetype";
	} else if (order == 3) {
	    columnName = "dept";
	}
	BaseCheckList list = basecheckdao.getBaseCheck(applydpid, pageindex,
		size, columnName, orderDir);
	return list;
    }

    /**
     * 
     * @return 获取部门集合（部门id和具体部门）
     */
    @Override
    public List<BaseCheck> getDept() {
	List<BaseCheck> list = basecheckdao.getDept();
	return list;
    }
    /**
     * 拒绝申请
     * @param str 封装的id表示哪几条数据
     * @param infoStr 消息数据
     */
    @Override
    public void refuseapply(String recordstr,String infostr) {

	// 获得插入的消息语句
	String insertStr = MessageUtils.getinfoMs(infostr, 8);
	System.out.println(insertStr + "到底是什么信息");

	// 申请失败（str:字符串id；12：失败状态值）
	basecheckdao.refuseapply(recordstr);
	// 向消息表中插入信息
	basecheckdao.insertMessage(insertStr);

    }
    /**
     * 同意申请
     * @param str 封装的id表示哪几条数据
     * @param infoStr 消息数据
     * @param date 有效期
     */
    @Override
    public void agreeApply(String str,String infoStr, String recordstr) {
	basecheckdao.agreeApply(str, recordstr);
	// 获得插入的消息语句
	String insertStr = MessageUtils.getinfoMs(infoStr, 9);
	// 向消息表中插入信息
	basecheckdao.insertMessage(insertStr);

    }

}
