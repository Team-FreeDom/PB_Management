package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.MaintenanceDao;
import com.base.po.ApplyDept;
import com.base.po.ExportBase;
import com.base.po.Maintenance;
import com.base.po.MaintenanceList;
import com.base.service.MaintenanceService;

@Service("MaintenanceService")
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    private MaintenanceDao maintenancedao;
    
    
    /**
     * 获取基地申请信息
     * @param pageindex 当前页数
     * @param size      当前显示几条记录
     * @return
     * 
     */
    @Override
    public MaintenanceList maintenance(int pageindex, int size,int order,String orderDir,String searchValue)
    {
    	String columnName="";
		if(order==0||order==2){
			columnName="id";
		}else if(order==1){
			columnName="buildtime";
		}else if(order==9){
			columnName="star";
		}
	  MaintenanceList list=maintenancedao.maintenance(pageindex, size,columnName,orderDir,searchValue);
	  return list;
    }


	@Override
	public void delInfo(String str) {
		
		maintenancedao.delInfo(str);
	}
	
	@Override
	public List<ApplyDept> getExistDept() {
		List<ApplyDept> list=maintenancedao.getExistDept();
		return list;
	}


	@Override
	public MaintenanceList getshaiBaseInfo(int basetype, int dept, int star,
			int pageindex, int size) {
		MaintenanceList list=maintenancedao.getshaiBaseInfo(basetype,dept,star,pageindex, size);
		return list;
	}

   //���»�����Ϣ
	@Override
	public void updateBaseInfo(String baseid, int star, int adddate) {
		maintenancedao.updateBaseInfo(baseid,star,adddate);
		
	}


	@Override
	public List<ExportBase> getExportBaseInfo(int basetype, int dept, int star) {
		List<ExportBase> list=maintenancedao.getInfo(basetype,dept,star);
		return list;
	}

}
