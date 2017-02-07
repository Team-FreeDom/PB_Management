package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.PlanMaintainDao;
import com.base.po.AllPlan;
import com.base.po.BaseCheckList;
import com.base.po.PlanList;
import com.base.service.PlanMaintainService;

@Service("planMaintainService")
public class PlanMaintainServiceImpl implements PlanMaintainService {

	@Autowired
	private PlanMaintainDao planMaintainDao;
	@Override
	public PlanList getPlanInfo(String semester, int pageindex, int size,
			int order, String orderDir, String searchValue) {
		String columnName = "";
		/*if (order == 0) {
		    columnName = "id";
		} else if (order == 2) {
		    columnName = "basename";
		} else if (order == 3) {
		    columnName = "basetype";
		} else if (order == 4) {
		    columnName = "dept";
		}*/
		PlanList list = planMaintainDao.getPlanInfo(semester, pageindex, size, columnName, orderDir, searchValue);
		return list;
	}

	@Override
	public void addOnePlanInfo(String str) {
		
		planMaintainDao.addPlanInfo(str);

	}

	@Override
	public List<AllPlan> getPlanTable(String semester,String college) {
		
		List<AllPlan> list=planMaintainDao.getPlanTable(semester, college);
		return list;
	}

	@Override
	public PlanList checkIsSave(String semester, int status, int pageindex,
			int size, int order, String orderDir, String searchValue) {
		
		String columnName = "";
		/*if (order == 0) {
		    columnName = "id";
		} else if (order == 2) {
		    columnName = "basename";
		} else if (order == 3) {
		    columnName = "basetype";
		} else if (order == 4) {
		    columnName = "dept";
		}*/
		PlanList list = planMaintainDao.checkIsSave(semester, status, pageindex, size, columnName, orderDir, searchValue);
		return list;
	}

	@Override
	public void deletePlanInfo(String recordstr) {
		
		planMaintainDao.deletePlanInfo(recordstr);

	}

	@Override
	public void callAttention(String recordstr) {
		
		planMaintainDao.callAttention(recordstr);

	}

}
