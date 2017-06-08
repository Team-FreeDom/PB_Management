package com.base.po;

import java.util.List;

public class ProfessionalmanageList {
	
	private List<Professionalmanage> data;
    private int recordsTotal;
    
	public ProfessionalmanageList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProfessionalmanageList(List<Professionalmanage> data, int recordsTotal) {
		super();
		this.data = data;
		this.recordsTotal = recordsTotal;
	}


	public List<Professionalmanage> getData() {
		return data;
	}
	public void setData(List<Professionalmanage> data) {
		this.data = data;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	
}
