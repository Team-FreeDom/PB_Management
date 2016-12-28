package com.base.dao;

import java.util.List;

import com.base.po.BaseCheck;
import com.base.po.BaseCheckList;

public interface BaseCheckDao {
    
    
    /**
     * 
     * @param applydpid 申请部门id
     * @param pageindex 当前页数
     * @param size      当前记录数
     * @param columnName     排序列
     * @param orderDir  排序顺序
     * @return 基地审核信息
     */
    public BaseCheckList getBaseCheck(int applydpid,int pageindex,int size, String columnName, String orderDir);
    /**
     * 
     * @return 获取部门集合（部门id和具体部门）
     */
    public List<BaseCheck> getDept();
    /**
     * 拒绝申请
     * @param str 封装的id表示哪几条数据
     * @param status 变为申请失败状态（12）
     */
    public void refuseapply(String str,int status);
    /**
     * 同意申请
     * @param str 封装的id表示哪几条数据
     * @param date 基地有效期
     */
    public void agreeApply(String str,int date);
    
    /**封装消息的方法
     * 发送消息
     * @param sql 插入封装好的sql语句
     */
    public void insertMessage(String sql);
}
