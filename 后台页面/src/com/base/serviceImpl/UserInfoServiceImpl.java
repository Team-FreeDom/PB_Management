package com.base.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.UserInfoDao;
import com.base.daoImpl.UserInfoDaoImpl;
import com.base.po.Admin;
import com.base.po.ApplyDept;
import com.base.po.Manger;
import com.base.po.MangerList;
import com.base.po.UserInfo;
import com.base.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDaoImpl userInfoDaoImpl;
    @Autowired
    private UserInfoDao userInfodao;

    public long login(String id, String pwd) {
	return userInfoDaoImpl.login(id, pwd);
    }

    @Override
    public List<UserInfo> getUserInfos(int userright, String sex, int id) {

	List<UserInfo> list = null;

	if (userright != 0 && !sex.isEmpty() && id != 0) {
	    list = userInfoDaoImpl.getUserInfo(userright, sex, id);

	} else if (userright != 0 && !sex.isEmpty()) {
	    list = userInfoDaoImpl.getUserInfo(userright, sex);

	} else if (!sex.isEmpty() && id != 0) {
	    list = userInfoDaoImpl.getUserInfos(sex, id);

	} else if (userright != 0 && id != 0) {
	    list = userInfoDaoImpl.getUserInfo(userright, id);

	} else if (userright != 0) {
	    list = userInfoDaoImpl.getUserInfos(userright);

	} else if (id != 0) {
	    list = userInfoDaoImpl.getUserInfo(id);

	} else if (!sex.isEmpty()) {
	    list = userInfoDaoImpl.getUserInfos(sex);
	}

	return list;
    }

    public UserInfo getImage(String userId) {
	// List<UserInfo> list = userInfodao.getInfoPerson(userId);
	UserInfo ui = userInfoDaoImpl.getUserInfo(userId);
	// String image = list.get(0).getImg();

	return ui;

    }

    /**
     * 修改个人信息
     */
    @Override
    public void update(String id, String name, String telephone,
	    String password, String img) {
	userInfodao.updateuser(id, name, telephone, password, img);

    }

    /**
     * 获取个人信息
     * 
     * @param id
     *            用户id
     * @return 用户信息
     */
    @Override
    public List<UserInfo> getInfoPerson(String id) {
	List<UserInfo> list = userInfodao.getInfoPerson(id);
	return list;
    }

    /**
     * 用户管理
     * 
     * @param pageindex
     *            当前页数
     * @param size
     *            当前显示几条记录
     * @return
     * @throws SQLException
     */
    @Override
    public MangerList manger(int pageindex, int size, String searchValue) {
	MangerList list = userInfodao.manger(pageindex, size, searchValue);
	return list;
    }

    /**
     * 
     * @param id
     *            用户id
     * @return 用户基本信息
     * @throws SQLException
     */
    @Override
    public List<Manger> Mangerdetail(String id) {
	List<Manger> list = userInfodao.Mangerdetail(id);
	return list;
    }

    /**
     * 删除人员基本信息
     * 
     * @param str
     *            为人员id的字符串
     * @throws SQLException
     */
    @Override
    public void deleteInfo(String str) {
	userInfodao.deleteInfo(str);

    }

    /**
     * 修改用户信息
     * 
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
    public void upInfo(String id, String name, String sex, String birthdate,
	    String category, String attritube, String dept, String telephone,
	    String idcard, String password) {
	userInfodao.upInfo(id, name, sex, birthdate, category, attritube, dept,
		telephone, idcard, password);

    }

    /**
     * 部门集合
     * 
     * @return
     */
    public List<ApplyDept> getDepts() {
	List<ApplyDept> list = userInfodao.getDepts();
	return list;
    }

    /**
     * Admin中人员属性集合
     * 
     * @return
     */
    public List<Admin> getAttritube() {
	List<Admin> list = userInfodao.getAttritube();
	return list;
    }

    /**
     * 增加用户
     * 
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
    @Override
    public int addInfo(String id, String name, String sex, String birthdate,
	    String category, String attritube, String dept, String telephone,
	    String idcard, String password) {
	int flag = userInfodao.addInfo(id, name, sex, birthdate, category,
		attritube, dept, telephone, idcard, password);
	return flag;
    }

    /**
     * 导出人员信息
     * 
     * @return
     */
    @Override
    public List<Manger> exportPersonInfo(String dept) {
	List<Manger> list = userInfodao.exportPersonInfo(dept);
	return list;
    }

    /**
     * 筛选userInfo中的部门
     * 
     * @return
     */
    @Override
    public List<UserInfo> userdept() {
	List<UserInfo> list = userInfodao.userdept();
	return list;
    }
   //获取职称
    @Override
    public List<Map<String, String>> Title() {
	List<Map<String, String>> list=userInfodao.Title();
	return list;
    }

}
