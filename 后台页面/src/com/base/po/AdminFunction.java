package com.base.po;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class AdminFunction {
  
	@Id
	int qid;
	long value;
	String pname;
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
	
}
