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
		if (order == 0) {
		    columnName = "id";
		} else if (order == 4) {
		    columnName = "count";
		} else if (order == 5) {
		    columnName = "selectedCount";
		} else if (order == 7) {
		    columnName = "college";
		}else if (order == 8) {
		    columnName = "weekClassify";
		}else if (order == 9) {
		    columnName = "credit";
		}else if (order == 12) {
		    columnName = "tid";
		}else if (order == 13) {
		    columnName = "tname";
		}
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
		if (order == 0) {
		    columnName = "id";
		} else if (order == 4) {
		    columnName = "count";
		} else if (order == 5) {
		    columnName = "selectedCount";
		} else if (order == 7) {
		    columnName = "college";
		}else if (order == 8) {
		    columnName = "weekClassify";
		}else if (order == 9) {
		    columnName = "credit";
		}else if (order == 12) {
		    columnName = "tid";
		}else if (order == 13) {
		    columnName = "tname";
		}
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

	@Override
	public List<String> getSemester() {
		List<String> list=planMaintainDao.getSemester();
		return list;
	}

	@Override
	public boolean checkIsUser(String id) {
		boolean flag=planMaintainDao.checkIsUser(id);
		return flag;
	}

	@Override
	public boolean checkIsMid(String aid) {
		boolean flag=planMaintainDao.checkIsMid(aid);
		return flag;
	}

	@Override
	public List<String> getPlanCollege(String semester) {
		List<String> list=planMaintainDao.getPlanCollege(semester);
		return list;
	}

}
