package com.base.dao;

import com.base.po.MymaintainList;

public interface MymaintainDao {
    /**
     * 页面数据
     * 
     * @param pageindex
     * @param size
     * @param columnName
     * @param orderDir
     * @param year
     * @param status
     * @param userid
     * @return
     */
    public MymaintainList Mymaintain(int pageindex, int size,
	    String columnName, String orderDir, int year, int status,
	    String userid);
    /**
     * 撤回
     * 
     * @param id
     * @param infostr
     */
    public void recallmymaint(String id);

    /**
     * 封装消息的方法 发送消息
     * 
     * @param sql
     *            插入封装好的sql语句
     */
    public void insertMessage(String sql);
}
