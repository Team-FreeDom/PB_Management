package com.base.po;

import java.util.List;

public class RentList {
	
	
	private int recordsTotal;		
	private List<RentMaintain> data;




	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}



	public List<RentMaintain> getData() {
		return data;
	}

	public void setData(List<RentMaintain> data) {
		this.data = data;
	}

	public RentList() {
		super();
	}
	
	

}
