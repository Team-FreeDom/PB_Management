package com.base.service;

import java.util.List;
import java.util.Map;

import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;

public interface BaseCheckService {
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
    public BaseCheckList getBaseCheck(int applydpid, int pageindex, int size,
	    int order, String orderDir,String searchValue);

    /**
     * 
     * @return 获取部门集合（部门id和具体部门）
     */
    public List<Map<String,String>> getDept();
    /**
     * 拒绝申请
     * @param str 封装的id表示哪几条数据
     * @param infoStr 消息数据
     */
    public void refuseapply(String recordstr,String infostr);
    /**
     * 同意申请
     * @param str 封装的id表示哪几条数据
     * @param infoStr 消息数据
     * @param date 有效期
     */
    public void agreeApply(String str,String infoStr,String recordstr);
    
    public BaseCheckList getaddCheck(int applydpid, int pageindex, int size,
    	    int order, String orderDir,String searchValue);

	public void addDateApply(String infostr, String recordstr);

	void refuseDateApply(String infostr, String recordstr);
}
