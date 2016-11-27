package com.base.service;

import java.util.List;

import com.base.po.UserInfo;

public interface UserInfoService {
	
	 /*
	   参数说明：id,字符串数组，表示用户编号的数组
	   返回值：  无返回值
	   函数功能：删除一个或多个用户信息
	 */
     public void delUser(String[] delid);
  
     /*
	   参数说明：ui,代表用户记录对象UserInfo
	   返回值：  无返回值
	   函数功能：增加一条用户信息
	 */
    public void doUser(UserInfo ui);

     /*
             参数说明：ui,代表用户记录对象UserInfo
            返回值：  无返回值
            函数功能：更新用户信息
    */
     public void updateUser(UserInfo ui);

    /*
            参数说明：无参
           返回值：  List<UserInfo>，用户信息记录UserInfo对象的集合
          函数功能：显示所有的用户信息
      */
    public List<UserInfo> getUserInfos();
    
    /* 参数说明：userright,整型，代表用户的权限值
                sex,字符串，为性别
                id，整型，为员工编号
               返回值：  List<UserInfo>，用户信息记录UserInfo对象的集合
               函数功能：根据条件显示所有的用户信息
                 注：         3个参数是联合查询，但这三个参数也可能存在没有值的情况
	                     因此在此需要进行判断参数是否有值，根据结果调用dao层的方法
	                     在此规定，从上层传来的userright和id值若为0，则代表此两参数无值
    */
    public List<UserInfo> getUserInfos(int userright,String sex,int id);
/**
     * 修改个人信息
     * @param userinfo
     */
    public void update(String id,String name,String telephone,String password,String img);
    /**
     * 获取个人信息
     * @param id 用户id
     * @return 用户信息
     */
    public List<UserInfo> getInfoPerson(String id);
    /**
     * 
     * @param pageindex 当前页数
     * @param size      当前显示几条记录
     * @return
     * @throws SQLException
     */
    public MangerList manger(int pageindex,int size,String searchValue);
    /**
     * 
     * @param id 用户id
     * @return 用户基本信息
     * @throws SQLException
     */
    public List<Manger> Mangerdetail(String id);
    /**
     * 删除人员基本信息
     * @param str 为人员id的字符串
     * @throws SQLException
     */
    public void deleteInfo(String str);
    /**
     * 修改用户信息
     * @param id
     * @param name
     * @param sex
     * @param birthdate
     * @param category
     * @param attritube
     * @param telephone
     * @param idcard
     * @param password
     * @throws SQLException
     */
    public void upInfo(String id,String name,String sex,String birthdate,String category,String attritube,String dept,String telephone,String idcard,String password);
  /**
   * 部门集合
   * @return
  */
    public List<ApplyDept> getDepts();
    /**
     * Admin中人员属性集合
     * @return
     */
    public List<Admin> getAttritube();
    /**
     * 增加用户
     * @param id
     * @param name
     * @param sex
     * @param birthdate
     * @param category
     * @param attritube
     * @param dept
     * @param telephone
     * @param idcard
     * @param password
     */
    public int addInfo(String id,String name,String sex,String birthdate,String category,String attritube,String dept,String telephone,String idcard,String password);
     /**
      * 导出人员信息
      * @return
      */
     public List<Manger> exportPersonInfo(String dept);
 

}
