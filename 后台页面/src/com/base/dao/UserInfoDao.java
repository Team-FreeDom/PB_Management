package com.base.dao;

import java.util.List;

import com.base.po.BaseInfo;
import com.base.po.UserInfo;


public interface UserInfoDao {
	
	 /*
	   参数说明：id,整型，表示用户编号
	   返回值：  无返回值
	   函数功能：从用户信息表中删除一条信息
	 */
     public void delUser(int id);
     
     /*
	   参数说明：ui,代表用户记录对象UserInfo
	   返回值：  无返回值
	   函数功能：向用户信息表中增加一条信息
	 */
   public void doUser(UserInfo ui);
   
     /*
           参数说明：ui,代表用户记录对象UserInfo
          返回值：  无返回值
          函数功能：更新用户信息表中的一条信息
     */
     public void updateUser(UserInfo ui);
   
     /*
          参数说明：无参
          返回值：  List<UserInfo>，用户信息记录的集合
          函数功能：获取所有的用户信息
     */
     public List<UserInfo> getUserInfos();
     
     /*
             参数说明：userright,整型，代表用户的权限值
            返回值：  List<UserInfo>，用户信息记录的集合
            函数功能：获取所有用户权限值为userright的用户信息
     */
     public List<UserInfo> getUserInfos(int userright);
     
     /*
                 参数说明：sex,字符串，为性别
                 返回值：  List<UserInfo>，用户信息记录的集合
                  函数功能：根据条件获取用户信息
        */
      public List<UserInfo> getUserInfos(String sex);
      
      /*
              参数说明：id，整型，为员工编号
             返回值：  List<UserInfo>，用户信息记录的集合
             函数功能：根据条件获取用户信息
      */
      public List<UserInfo> getUserInfo(int id);
      
      /*
                 参数说明：sex,字符串，为性别
                  userright,整型，代表用户的权限值
                返回值：  List<UserInfo>，用户信息记录的集合
                函数功能：根据条件获取用户信息
      */
     public List<UserInfo> getUserInfo(int userright,String sex);

       /*
                参数说明：userright,整型，代表用户的权限值
                  id，整型，为员工编号
               返回值：  List<UserInfo>，用户信息记录的集合
               函数功能：根据条件获取用户信息
       */
     public List<UserInfo> getUserInfo(int userright,int id);
     
       /*
                参数说明：sex,字符串，为性别
                  id，整型，为员工编号
                返回值：  List<UserInfo>，用户信息记录的集合
                函数功能：根据条件获取用户信息
       */
     public List<UserInfo> getUserInfos(String sex,int id);
     
       /*
                参数说明：sex,字符串，为性别
                 id，整型，为员工编号
                 userright,整型，代表用户的权限值
                返回值：  List<UserInfo>，用户信息记录的集合
                函数功能：根据条件获取用户信息
       */
     public List<UserInfo> getUserInfo(int userright,String sex,int id);
      
 
     public boolean login1(String id,String pwd);
     
     
     public long login(String id,String pwd);
     
     /**
      * 修改个人信息
      * @param userinfo
      */
     public void updateuser(String id,String name,String telephone,String password,String img);
     /**
      * 获取个人信息
      * @param id 用户id
      * @return 用户信息
      */
     public List<UserInfo> getInfoPerson(String id);
   
     

}
