package com.base.po;

import java.util.List;

public class MyBaseList {

    private int recordsTotal;
    private List<MyBase> data;
    
    public MyBaseList() {
	super();
    }
    public MyBaseList(int recordsTotal, List<MyBase> data) {
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
    public List<MyBase> getData() {
        return data;
    }
    public void setData(List<MyBase> data) {
        this.data = data;
    }
    
}
