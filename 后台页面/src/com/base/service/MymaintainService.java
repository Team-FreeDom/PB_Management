package com.base.service;

import com.base.po.MymaintainList;



public interface MymaintainService {

    /**
     * 页面一
     * @param pageindex
     * @param size
     * @param columnName
     * @param orderDir
     * @param year
     * @param status
     * @param userid
     * @return
     */
    public MymaintainList Mymaintain(int pageindex,int  size,String columnName,
		String orderDir,int year,int status,String userid);
    /**
     * 页面二
     * @param pageindex
     * @param size
     * @param order
     * @param orderDir
     * @param year
     * @param status
     * @param userid
     * @return
     */
    public MymaintainList Mymaintain2(int pageindex,int  size,int order,
		String orderDir,int year,int status,String userid);
    /**
     * 撤回
     * @param id
     * @param infostr
     */
    public void recallmymaint(String id,String infostr);  
}
