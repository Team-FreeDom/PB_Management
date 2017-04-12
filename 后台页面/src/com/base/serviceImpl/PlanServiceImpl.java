package com.base.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.PlanDao;
import com.base.po.BaseInfo;
import com.base.po.Classcourse;
import com.base.po.Majoraim;
import com.base.po.PlanList;
import com.base.po.PracticeCollection;
import com.base.po.UserInfo;
import com.base.service.PlanService;

@Service("PlanService")
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanDao plandao;

    // 获取该用户所在学院的实习计划
    @Override
    public PlanList getThisCollegePlan(String userid, int pageindex, int size,
	    int order, String orderDir, String searchValue, String semester) {
	String columnName = "";
	if (order == 0) {
	    columnName = "id";
	} else if (order == 3) {
	    columnName = "count";
	} else if (order == 4) {
	    columnName = "selectedCount";
	} else if (order == 6) {
	    columnName = "college";
	} else if (order == 7) {
	    columnName = "weekClassify";
	} else if (order == 8) {
	    columnName = "credit";
	} else if (order == 11) {
	    columnName = "tid";
	} else if (order == 12) {
	    columnName = "tname";
	}
	PlanList list = plandao.getThisCollegePlan(userid, pageindex, size,
		columnName, orderDir, searchValue, semester);
	return list;
    }

    // 删除单条班级安排记录
    @Override
    public void deleteClassRecord(int id) {
	plandao.deleteClassPlan(id);

    }

    // 提供保存按钮的功能
    @Override
    public void savePlanModify(int id, String plandata) {

	plandao.updatePlan(id, plandata);
    }

    // 从专业培训表中获取特定专业的多个培训目的
    @Override
    public List<Majoraim> getPlanAim(String cid) {
	List<Majoraim> list = plandao.getPlanAim(cid);
	return list;
    }

    // 根据学院编号获取该学院的老师
    @Override
    public List<UserInfo> getCollege_Teacher(String collagename) {
	List<UserInfo> list = plandao.getCollege_Teacher(collagename);
	return list;
    }

    // 根据课程代码获取申请表的数据集合
    @Override
    public List<Classcourse> plandata(int id) {
	List<Classcourse> list = plandao.plandata(id);
	return list;
    }

    // 修改课程安排表(单条)李彩页面功能
    @Override
    public void alterRecord(String plandata) {
	plandao.alterRecord(plandata);

    }

    // 检测学年学期和数据条数
    @Override
    public int checkinfo(String userid, String semester) {
	int record = plandao.checkinfo(userid, semester);
	return record;
    }

    @Override
    public List<String> getProperBase(String typename) {
	List<String> list = plandao.getProperBase(typename);
	return list;
    }

    @Override
    public List<PracticeCollection> plandata_export(String userid,
	    String finishCondition, String semester) {
	List<PracticeCollection> list = null;
	if (finishCondition.equals("0")) {
	    list = plandao.plandata_export_0(userid, semester);
	} else {
	    list = plandao.plandata_export_1(userid, finishCondition, semester);
	}
	return list;
    }

    // 通过学院获取专业
    @Override
    public List<Map<String, String>> getCollege_Major(String college) {
	List<Map<String, String>> list=plandao.getCollege_Major(college);
	return list;
    }

}
