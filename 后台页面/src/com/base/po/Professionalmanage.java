package com.base.po;

public class Professionalmanage {
	private int aid;
	private String dept;
	private String mid;
	private String mname;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Professionalmanage() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "aMajor [aid=" + aid + ", dept=" + dept + ", mid=" + mid
				+ ", mname=" + mname + "]";
	}
	public Professionalmanage(int aid, String dept, String mid, String mname) {
		super();
		this.aid = aid;
		this.dept = dept;
		this.mid = mid;
		this.mname = mname;
	}
	
}
