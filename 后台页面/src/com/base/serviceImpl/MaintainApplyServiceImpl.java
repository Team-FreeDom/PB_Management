package com.base.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.MaintainApplyDao;
import com.base.po.MaintainApplys;
import com.base.po.MaintainList;
import com.base.po.Prabaseinfo;

@Service("MaintainApplyServiceImpl")
public class MaintainApplyServiceImpl
{
	@Autowired
	private MaintainApplyDao maintainapplydao;
	//查询所有的基地列表
	public List<Prabaseinfo> find_basename()
	{
		List<Prabaseinfo> list=maintainapplydao.find_basename();
		return list;
	}
	//插入项目维修申请
	public void insert_maintain(String str)
	{
		maintainapplydao.insert_maintain(str);
	}
	//查询完成的校内基地维修申请记录,参数顺序：当前页面记录数，当前页数，排序列，排序顺序，模糊查询的字符串,返回总记录数
	public List<MaintainList> query_maintainapply(int offsets,int page,String str,String str1,String str2)
	{
		List<MaintainList> list=new ArrayList<MaintainList>();
		list=maintainapplydao.query_maintainapply(offsets, page, str, str1, str2);
		return list;
	}
	//删除维修基地申请记录，传的值为维修记录id的集合
	public void delete_maintainapply(String str)
	{
		maintainapplydao.delete_maintainapply(str);
	}
	//增加维修基地记录（已完成的维修）
	public void insert_maintainhistory(String pronames,String bids,String usernames,String address,String reasons,String files,String userids,double actuals)
	{
		maintainapplydao.add_maintain(pronames, bids, usernames, address, reasons, files, userids, actuals);
	}
	//导出基地维修记录，参数为筛选条件，第一个基地名字，第二个为年份（如没有，则为-1）
	public List<MaintainApplys> export_maintainapply(String bname,int years)
	{
		List<MaintainApplys> ma=new ArrayList<MaintainApplys>();
		ma=maintainapplydao.export_maintainapply(bname, years);
		return ma;
	}
}
