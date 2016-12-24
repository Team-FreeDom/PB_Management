package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.baseApplyDao;
import com.base.daoImpl.baseApplyDaoImpl;
import com.base.po.ApplyDept;
import com.base.po.Major;
import com.base.po.basetype;
import com.base.service.baseApplyService;


@Service("baseServiceImpl")
public class baseServiceImpl implements baseApplyService {
    @Autowired
    private baseApplyDao baseapplydao;
    
    
    /**
     * 得到对应部门
     * @param type 基地类型
     * @return
     */
    public List<ApplyDept> getDept(int type){
	List<ApplyDept> list=baseapplydao.getDept(type);
	return list;
    }
    /**
     * 获取基地类型
     * @return
     */
    public  List<basetype> getBasetype(){
	List<basetype> list=baseapplydao.getBasetype();
	return list;
    }
    /**
     * 获取学院对应的专业
     * @param aid 学院id
     * @return
     */
    public List<Major> getMajor(int aid){
	List<Major> list=baseapplydao.getMajor(aid);
	return list;
    }
    /**
     * 插入用户基地申请信息
     * @param str1 基地名称id 申报部门id 基地类型id等的string字符串
     * @param str2 (基地id,专业id)
     */
    @Override
    public void getRequestBaseInfo(String str1, String str2) {
	baseapplydao.getRequestBaseInfo(str1, str2);
	
    }

}
