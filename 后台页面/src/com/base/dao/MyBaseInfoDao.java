package com.base.dao;

import com.base.po.MyBaseList;

public interface MyBaseInfoDao {

    public MyBaseList MybaseInfo(int pageindex, int size, String columnName,
	    String orderDir, int year, int status, String userid);
    /**封装消息的方法
     * 发送消息
     * @param sql 插入封装好的sql语句
     */
    public void insertMessage(String sql);
    
    public void updateDate(int id, String adddate);
}
