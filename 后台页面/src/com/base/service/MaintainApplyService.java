package com.base.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.base.po.MaintainApply;
import com.base.po.MaintainApplys;
import com.base.po.MaintainList;

public interface MaintainApplyService {

	public List find_basename();
	
	public void insert_maintain(String str,String infostr);
	
	public MaintainList query_maintainapply(int offsets,int page,int order,String str1,String str2);
	
	public void delete_maintainapply(String str);
	
	public void insert_maintainhistory(MaintainApply ma);
	
	public List<MaintainApplys> export_maintainapply(String bname,int years);
	
	public List<String> getThoseYear();
	
	public List<Map<String,String>> find_basenamenei();
	
}
