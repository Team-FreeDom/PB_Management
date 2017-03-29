package com.base.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.BaseCheckDao;
import com.base.dao.MaintainApplyDao;
import com.base.po.MaintainApply;
import com.base.po.MaintainApplys;
import com.base.po.MaintainList;
import com.base.po.Prabaseinfo;
import com.base.service.MaintainApplyService;
import com.base.utils.MessageUtils;

@Service("MaintainApplyServiceImpl")
public class MaintainApplyServiceImpl implements MaintainApplyService
{
	@Autowired
	private MaintainApplyDao maintainapplydao;
	 @Autowired
	 private BaseCheckDao basecheckdao;

	@Override
	//根据年份查询基地列表
	public List find_basename(String year)
	{		
		List list=maintainapplydao.find_basenameFinish(year);		
		return list;
	}
	
	@Override
	//插入项目维修申请
	public void insert_maintain(String str,String infostr)
	{
		// 获得插入的消息语句
		String insertStr = MessageUtils.getinfoMs(infostr, 19);
		maintainapplydao.insert_maintain(str);
		// 向消息表中插入信息
		basecheckdao.insertMessage(insertStr);
	}
	
	@Override
	//查询完成的校内基地维修申请记录,参数顺序：当前页面记录数，当前页数，排序列，排序顺序，模糊查询的字符串,返回总记录数
	public MaintainList query_maintainapply(int offsets,int page,int order,String str1,String str2)
	{
		String column="";
		if(order==0){
			column="id";
		}else if(order==2){
			column="basename";
		}else if(order==3){
			column="username";
		}else if(order==4){
			column="apply_time";
		}
		MaintainList list=maintainapplydao.query_maintainapply(offsets, page, column, str1, str2);		
		return list;
	}
	
	@Override
	//删除维修基地申请记录，传的值为维修记录id的集合
	public void delete_maintainapply(String str)
	{
		maintainapplydao.delete_maintainapply(str);
	}
	
	@Override
	//增加维修基地记录（已完成的维修）
	public void insert_maintainhistory(MaintainApply ma)
	{
		maintainapplydao.add_maintain(ma);
	}
	
	@Override
	//导出基地维修记录，参数为筛选条件，第一个基地名字，第二个为年份（如没有，则为-1）
	public List<MaintainApplys> export_maintainapply(String bname,int years)
	{
		List<MaintainApplys> ma=new ArrayList<MaintainApplys>();
		ma=maintainapplydao.export_maintainapply(bname, years);
		return ma;
	}
	
   public List<String> getThoseYear(){
	   List<String> list=maintainapplydao.getThoseYear();
		return list;
		
	}

@Override
public List<Map<String, String>> find_basenamenei() {
	List<Map<String,String>> list=maintainapplydao.find_basenamenei();	
	return list;
}
}
