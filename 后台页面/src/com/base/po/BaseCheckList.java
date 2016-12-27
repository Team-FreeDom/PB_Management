package com.base.po;

import java.util.List;

public class BaseCheckList {
    
    private List<BaseCheck> data;
    private int recordsTotal;
    
    public BaseCheckList() {
	super();
    }
    public BaseCheckList(List<BaseCheck> data, int recordsTotal) {
	super();
	this.data = data;
	this.recordsTotal = recordsTotal;
    }
    public List<BaseCheck> getData() {
        return data;
    }
    public void setData(List<BaseCheck> data) {
        this.data = data;
    }
    public int getRecordsTotal() {
        return recordsTotal;
    }
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

}
