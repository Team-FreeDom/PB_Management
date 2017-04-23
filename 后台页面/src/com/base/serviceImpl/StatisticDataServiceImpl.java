package com.base.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.StatisticDataDao;
import com.base.po.PlanList;
import com.base.po.PracticeCollection;
import com.base.po.StatisticData;
//import com.base.po.StatisticDataList;
import com.base.po.basetype;
import com.base.service.StatisticDataService;

@Service("StatisticDataService")
public class StatisticDataServiceImpl implements StatisticDataService {
    @Autowired
    private StatisticDataDao statisticDatadao;

    @Override
    public PlanList statisticData(int pageindex, int size, int order,
	    String orderDir, String semester) {
	String columnName = "";
	if (order == 0) {
	    columnName = "semester";
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
	PlanList list = statisticDatadao.statisticData(pageindex, size,
		columnName, orderDir, semester);
	return list;
    }

    // 获取基地类型和学院
    @Override
    public List<basetype> getDept(String semester) {
	List list = new ArrayList();
	List<basetype> list1 = statisticDatadao.getDept(semester);
	List<Map<String, String>> college = statisticDatadao
		.getCollege(semester);
	list.add(list1);
	list.add(college);
	return list;
    }

    // 获取基地名字
    @Override
    public List<Map<String, String>> getName(String semester, String basename) {
	List<Map<String, String>> list = statisticDatadao.getName(semester,
		basename);
	return list;
    }

    // 获取专业
    @Override
    public List<Map<String, String>> getMajor(String semester, String college) {
	List<Map<String, String>> list = statisticDatadao.getMajor(semester,
		college);	
	return list;
    }

    // 刷选
    @Override
    public PlanList statisticDataBrush(String semester, String basetype,
	    String basename, String college, String grade, String major,
	    String class1, int pageindex, int size, int order, String orderDir) {
	String columnName = "";
	if (order == 0) {
	    columnName = "semester";
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
	PlanList list = statisticDatadao.statisticDataBrush(semester, basetype,
		basename, college, grade, major, class1, pageindex, size,
		columnName, orderDir);
	return list;
    }

    // 基地利用率记录
    @Override
    public StatisticData BaseUseRatio(String semester) {
	StatisticData st = statisticDatadao.BaseUseRatio(semester);
	return st;
    }

    // 基地利用率记录刷选
    @Override
    public StatisticData BaseUseRatioBrush(String semester, String basetype,
	    String basename, String grade, String college, String major,
	    String class1, String teacherName) {
	StatisticData st = statisticDatadao.BaseUseRatioBrush(semester,
		basetype, basename, grade, college, major, class1, teacherName);
	return st;
    }

	@Override
	public List<PracticeCollection> getExportstatisticData(String semester,
			String basetype, String basename, String college, String grade,
			String major, String class1) {
		List<PracticeCollection> list=statisticDatadao.getExportstatisticData(semester, basetype, basename, college, grade, major, class1);
		return list;
	}
	
	@Override
	public List<String> getstatisticCount(String semester){
		
		List<String> list=statisticDatadao.getstatisticCount(semester);
		return list;
	}

	@Override
	public List<String> getAllStatisticCount() {
		List<String> list=statisticDatadao.getAllStatisticCount();
		return list;
	}

}
