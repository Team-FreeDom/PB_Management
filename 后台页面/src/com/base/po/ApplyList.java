package com.base.po;

import java.util.List;

public class ApplyList {
	
	private int recordsTotal;		
	private List<LandApply_view> data;
	
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public List<LandApply_view> getData() {
		return data;
	}
	public void setData(List<LandApply_view> data) {
		this.data = data;
	}
	public ApplyList() {
		super();
	}
	
	

}
