package com.base.po;

import java.util.List;

public class MaintainList
{
	private int recordsTotal;
	private List<MaintainApplys> data;
	public MaintainList()
	{
		super();
	}
	public MaintainList(int recordsTotal, List<MaintainApplys> data)
	{
		super();
		this.recordsTotal = recordsTotal;
		this.data = data;
	}
	public int getRecordsTotal()
	{
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal)
	{
		this.recordsTotal = recordsTotal;
	}
	public List<MaintainApplys> getData()
	{
		return data;
	}
	public void setData(List<MaintainApplys> data)
	{
		this.data = data;
	}
	
}
