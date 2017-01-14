package com.base.dao;

import java.util.List;
import java.util.Map;

import com.base.po.MaintainApplys;
import com.base.po.MaintainApply;
import com.base.po.MaintainList;

public interface MaintainApplyDao {
	
	public List<Map<String,String>> find_basename();
	
	public void insert_maintain(String str);
	
	public MaintainList query_maintainapply(int offsets,int page,String str,String str1,String str2);
	
	public void delete_maintainapply(String str);
	
	public void add_maintain(MaintainApply ma);
	
	public List<MaintainApplys> export_maintainapply(String bname,int years);
	
	public List<Map<String,String>> find_basenameFinish();

}
