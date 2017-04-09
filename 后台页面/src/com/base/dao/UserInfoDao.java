package com.base.dao;

import java.util.List;
import java.util.Map;

import com.base.po.Admin;
import com.base.po.ApplyDept;
import com.base.po.BaseInfo;
import com.base.po.Manger;
import com.base.po.MangerList;
import com.base.po.UserInfo;

public interface UserInfoDao {

    /*
     * 参数说明：ui,代表用户记录对象UserInfo 返回值： 无返回值 函数功能：更新用户信息表中的一条信息
     */
    public void updateUser(UserInfo ui);

    /*
     * 参数说明：无参 返回值： List<UserInfo>，用户信息记录的集合 函数功能：获取所有的用户信息
     */
    public List<UserInfo> getUserInfos();

    /*
     * 参数说明：userright,整型，代表用户的权限值 返回值： List<UserInfo>，用户信息记录的集合
     * 函数功能：获取所有用户权限值为userright的用户信息
     */
    public List<UserInfo> getUserInfos(int userright);

    /*
     * 参数说明：sex,字符串，为性别 返回值： List<UserInfo>，用户信息记录的集合 函数功能：根据条件获取用户信息
     */
    public List<UserInfo> getUserInfos(String sex);

    /*
     * 参数说明：id，整型，为员工编号 返回值： List<UserInfo>，用户信息记录的集合 函数功能：根据条件获取用户信息
     */
    public List<UserInfo> getUserInfo(int id);

    /*
     * 参数说明：sex,字符串，为性别 userright,整型，代表用户的权限值 返回值： List<UserInfo>，用户信息记录的集合
     * 函数功能：根据条件获取用户信息
     */
    public List<UserInfo> getUserInfo(int userright, String sex);

    /*
     * 参数说明：userright,整型，代表用户的权限值 id，整型，为员工编号 返回值： List<UserInfo>，用户信息记录的集合
     * 函数功能：根据条件获取用户信息
     */
    public List<UserInfo> getUserInfo(int userright, int id);

    /*
     * 参数说明：sex,字符串，为性别 id，整型，为员工编号 返回值： List<UserInfo>，用户信息记录的集合
     * 函数功能：根据条件获取用户信息
     */
    public List<UserInfo> getUserInfos(String sex, int id);

    /*
     * 参数说明：sex,字符串，为性别 id，整型，为员工编号 userright,整型，代表用户的权限值 返回值：
     * List<UserInfo>，用户信息记录的集合 函数功能：根据条件获取用户信息
     */
    public List<UserInfo> getUserInfo(int userright, String sex, int id);

    public boolean login1(String id, String pwd);

    public long login(String id, String pwd);

    /**
     * 修改个人信息
     * 
     * @param userinfo
     */
    public void updateuser(String id, String name, String telephone,
	    String password, String img);

    /**
     * 获取个人信息
     * 
     * @param id
     *            用户id
     * @return 用户信息
     */
    public List<UserInfo> getInfoPerson(String id);

    /*
     * 参数： pageindex,为页码;size,为每页条数 ;searchValue,为模糊查询值 返回值：
     * MangerList，包括了用户信息记录和记录条数 函数功能：获取用户信息记录
     */
    public MangerList manger(int pageindex, int size, String searchValue);

    /*
     * 参数： id,为用户编号 返回值： List<Manger>，Manger对象的集合 函数功能：获取用户信息详情
     */
    public List<Manger> Mangerdetail(String id);

    /*
     * 参数： str,为多个用户编号的封装 返回值：无返回值 函数功能：删除多个用户信息记录
     */
    public void deleteInfo(String str);

    /*
     * 参数说明：id,为用户编号;name,为用户名称;sex,为用户性别;birthdate,为用户生日日期;category,用工类型...
     * 返回值： 无返回值 函数功能：修改用户信息
     */
    public void upInfo(String id, String name, String sex, String birthdate,
	    String category, String attritube, String dept, String telephone,
	    String idcard, String password);

    /*
     * 参数说明：无参数 返回值： List<ApplyDept>，为ApplyDept对象的集合 函数功能：获取用户信息所存在部门
     */
    public List<ApplyDept> getDepts();

    /*
     * 参数说明：无参数 返回值： List<Admin>，为Admin对象的集合 函数功能：获取Admin中人员属性集合
     */
    public List<Admin> getAttritube();

    /*
     * 参数说明：各参数分别为用户表中的字段 返回值： int型，成功返回1 函数功能：增加用户信息
     */
    public int addInfo(String id, String name, String sex, String birthdate,
	    String category, String attritube, String dept, String telephone,
	    String idcard, String password);

    /*
     * 参数说明：dept,为部门 返回值： List<Manger>，Manger对象的集合 函数功能：获得导出的用户信息记录
     */
    public List<Manger> exportPersonInfo(String dept);

    /**
     * 筛选userInfo中的部门
     * 
     * @return
     */
    public List<UserInfo> userdept();

   /**
    * 获取职称
    * @return
    */
    public List<Map<String, String>> Title();

}
