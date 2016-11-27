package com.base.po;

import java.util.List;

public class MangerList
{
	    private int recordsTotal;	
		private List<Manger> data;
		public int getRecordsTotal()
		{
			return recordsTotal;
		}
		public void setRecordsTotal(int recordsTotal)
		{
			this.recordsTotal = recordsTotal;
		}
		public List<Manger> getData()
		{
			return data;
		}
		public void setData(List<Manger> data)
		{
			this.data = data;
		}

}
