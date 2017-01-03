package com.base.service;

import java.util.List;

import com.base.po.ApplyDept;
import com.base.po.ExportBase;
import com.base.po.Prabaseinfo;
import com.base.po.MaintenanceList;

public interface MaintenanceService {
    /**
     * 获取基地申请信息
     * @param pageindex 当前页数
     * @param size      当前显示几条记录
     * @return MaintenanceList基地申请信息集合
     * 
     */
    public MaintenanceList maintenance(int pageindex, int size,int order,String orderDir,String searchValue);
    
    public void delInfo(String str);
    
    public List<ApplyDept> getExistDept();
    
    public MaintenanceList getshaiBaseInfo(int basetype,int dept,int star,int pageindex, int size,int order,String orderDir,String searchValue);
    
    public void updateBaseInfo(String baseid,int star,int adddate);
    
    public List<ExportBase> getExportBaseInfo(int basetype,int dept,int star);
}
