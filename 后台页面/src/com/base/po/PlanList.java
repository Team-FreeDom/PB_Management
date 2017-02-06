package com.base.po;

import java.util.List;

public class PlanList {
	
	 private int recordsTotal;
	 private List<AllPlan> data;
	 
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public List<AllPlan> getData() {
		return data;
	}
	public void setData(List<AllPlan> data) {
		this.data = data;
	}
	public PlanList() {
		super();
	}
	 
	 

}
