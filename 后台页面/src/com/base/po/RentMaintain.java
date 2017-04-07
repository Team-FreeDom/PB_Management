package com.base.po;

public class RentMaintain {

	private String landname;
	private String aptplanting;
	private int landArea;
	private int times;// 租用此数
	private String deptName;// 申报部门名称
	private String name;
	private String bname;
	private int lr_id;
	private String chargeDate;// 交费日期
	private String startTime;
	private String endTime;
	private String lid;
	private String planting;// 种植内容
	private Double rentMoney;// 费用
	private String userid;
	private int applydept;// 申报部门id

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getApplydept() {
		return applydept;
	}

	public void setApplydept(int applydept) {
		this.applydept = applydept;
	}

	public String getLandname() {
		return landname;
	}

	public void setLandname(String landname) {
		this.landname = landname;
	}

	public String getAptplanting() {
		return aptplanting;
	}

	public void setAptplanting(String aptplanting) {
		this.aptplanting = aptplanting;
	}

	public int getLandArea() {
		return landArea;
	}

	public void setLandArea(int landArea) {
		this.landArea = landArea;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getLr_id() {
		return lr_id;
	}

	public void setLr_id(int lr_id) {
		this.lr_id = lr_id;
	}

	public String getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(String chargeDate) {
		this.chargeDate = chargeDate;
	}

	public String getPlanting() {
		return planting;
	}

	public void setPlanting(String planting) {
		this.planting = planting;
	}

	public Double getRentMoney() {
		return rentMoney;
	}

	public void setRentMoney(Double rentMoney) {
		this.rentMoney = rentMoney;
	}

}
