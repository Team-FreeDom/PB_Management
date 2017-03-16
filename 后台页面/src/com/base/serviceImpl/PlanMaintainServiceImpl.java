package com.base.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.contants.Contants;
import com.base.dao.PlanMaintainDao;
import com.base.po.AllPlan;
import com.base.po.BaseCheckList;
import com.base.po.PlanList;
import com.base.po.PracticeCollection;
import com.base.po.StartDate;
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
	public List<AllPlan> getPlanTable(String year,int semester,String college) {
		
		List<AllPlan> list=planMaintainDao.getPlanTable(year,semester, college);
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

	@Override
	public void delete_0(String semester) {
		
		planMaintainDao.delete_0(semester);
	}
	
	@Override
	public void add_0(String str) {
		
		planMaintainDao.add_0(str);
	}

	@Override
	public int checkIsCid(String semester,String cid) {
		int flag=planMaintainDao.checkIsCid(semester,cid);
		return flag;
	}

	@Override
	public List<String> getSem(String semester) {
		List<String> list=planMaintainDao.getSem(semester);		
		return list;
	}

	@Override
	public List<String> getCollegehh(String year, int semester) {
		List<String> list=planMaintainDao.getCollegehh(year, semester);
		return list;
	}

	@Override
	public void addStartDate(String semester, String startTime) {
		
		planMaintainDao.addStartDate(semester, startTime);
	}

	@Override
	public List<StartDate> getStartDate() {
		List<StartDate> list=planMaintainDao.getStartDate();
		return list;
	}
	
	// 修改课程安排表(单条)李彩页面功能
    @Override
    public void alterRecord(int id,String plandata) {
    	planMaintainDao.alterRecord(id,plandata);

    }

	@Override
	public List<PracticeCollection> getPlanTable_0(String year, int semester,
			String college) {
		List<PracticeCollection> list=planMaintainDao.getPlanTable_0(year, semester, college);
		return list;
	}
}
