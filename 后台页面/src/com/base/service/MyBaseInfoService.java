package com.base.service;

import com.base.po.MyBaseList;





public interface MyBaseInfoService {
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
   public MyBaseList MybaseInfo(int pageindex,int  size,String columnName,
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
   public MyBaseList MybaseInfo2(int pageindex,int  size,int order,
		String orderDir,int year,int status,String userid);
   /**
    * 撤回功能
    * @param id
    * @param infostr
    */
   public void recall(String id,String infostr);  
   
   public void updateDate(int id,String adddate);

}
