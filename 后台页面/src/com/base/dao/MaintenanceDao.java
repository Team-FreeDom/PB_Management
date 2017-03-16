package com.base.dao;

import java.util.List;

import com.base.po.ApplyDept;
import com.base.po.ExportBase;
import com.base.po.Prabaseinfo;
import com.base.po.MaintenanceList;

public interface MaintenanceDao {

    /**
     * 获取基地申请信息
     * @param pageindex 当前页数
     * @param size      当前显示几条记录
     * @return
     * 
     */
    public MaintenanceList maintenance(int pageindex, int size,String order,String orderDir,String searchValue);
    
    public void delInfo(String str);
    
    public List<ApplyDept> getExistDept();
    
    public MaintenanceList getshaiBaseInfo(int basetype,int dept,int star,int pageindex, int size,String order,String orderDir,String searchValue);
    
    public void updateBaseInfo(String baseid, int star, String adddate);

	public List<ExportBase> getInfo(int basetype, int dept, int star);

	public void increaseBaseInfo(String str1, String str2);

	
}
