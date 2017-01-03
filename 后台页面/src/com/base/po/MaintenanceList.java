package com.base.po;

import java.util.List;

public class MaintenanceList {
    private int recordsTotal;
    private List<Prabaseinfo> data;
    
    public MaintenanceList() {
	super();
    }
    public MaintenanceList(int recordsTotal, List<Prabaseinfo> data) {
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
    public List<Prabaseinfo> getData() {
        return data;
    }
    public void setData(List<Prabaseinfo> data) {
        this.data = data;
    }
    
}
