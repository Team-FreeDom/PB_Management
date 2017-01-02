package com.base.po;

import java.util.List;

public class MaintenanceList {
    private int recordsTotal;
    private List<Maintenance> data;
    
    public MaintenanceList() {
	super();
    }
    public MaintenanceList(int recordsTotal, List<Maintenance> data) {
	super();
	this.recordsTotal = recordsTotal;
	this.data = data;
    }
    public int getRecordsTotal() {
        return recordsTotal;
    }
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
    public List<Maintenance> getData() {
        return data;
    }
    public void setData(List<Maintenance> data) {
        this.data = data;
    }
    
}
