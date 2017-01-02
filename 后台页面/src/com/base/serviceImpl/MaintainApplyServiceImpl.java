package com.base.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.MaintainApplyDao;
import com.base.po.ProBaseinfo;

@Service("MaintainApplyServiceImpl")
public class MaintainApplyServiceImpl
{
	@Autowired
	private MaintainApplyDao maintainapplydao;
	public List<ProBaseinfo> find_basename()
	{
		List<ProBaseinfo> list=maintainapplydao.find_basename();
		return list;
	}
	public void insert_maintain(String str)
	{
		maintainapplydao.insert_maintain(str);
	}
}
