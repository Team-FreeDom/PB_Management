package com.base.po;

import java.util.List;

public class CheckList
{
    private int recordsTotal;	
	
	private List<CheckView> data;

	public int getRecordsTotal()
	{
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal)
	{
		this.recordsTotal = recordsTotal;
	}

	public List<CheckView> getData()
	{
		return data;
	}

	public void setData(List<CheckView> data)
	{
		this.data = data;
	}

}
