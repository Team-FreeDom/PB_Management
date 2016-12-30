package com.base.dao;

import java.util.List;

import com.base.po.ApplyDept;
import com.base.po.Major;
import com.base.po.basetype;

public interface baseApplyDao {

    /**
     * 得到对应部门
     * @param type 基地类型
     * @return
     */
    public List<ApplyDept> getDept(int type);
    /**
     * 获取基地类型
     * @return
     */
    public  List<basetype> getBasetype();
    /**
     * 获取学院对应的专业
     * @param aid 学院id
     * @return
     */
    public List<Major> getMajor(int aid);
    /**
     * 插入用户基地申请信息
     * @param str1 基地名称id 申报部门id 基地类型id等的string字符串
     * @param str2 (基地id,专业id)
     */
    public void getRequestBaseInfo(String str1,String str2);
    /**
     * 
     * @param name 用户输入的名称
     * @return 0-false 1-true
     */
    public int CheckName(String name);
}
