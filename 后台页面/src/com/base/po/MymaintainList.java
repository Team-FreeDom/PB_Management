package com.base.po;

import java.util.List;

public class MymaintainList {

    private int recordsTotal;//我的 维修总记录数
    private List<Mymaintain> data;
    
    public MymaintainList() {
	super();
    }
    public MymaintainList(int recordsTotal, List<Mymaintain> data) {
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
    public List<Mymaintain> getData() {
        return data;
    }
    public void setData(List<Mymaintain> data) {
        this.data = data;
    }
    
}
