package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class BaseInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bid;//基地编号
	private String bname;//基地名称
	private int btype;//基地类型，默认为1，代表为旗下为土地
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getBtype() {
		return btype;
	}
	public void setBtype(int btype) {
		this.btype = btype;
	}
	public BaseInfo(int bid, String bname, int btype) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.btype = btype;
	}
	public BaseInfo(String bname, int btype) {
		super();
		this.bname = bname;
		this.btype = btype;
	}
	public BaseInfo() {
		super();
	}
	
	

}
