package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class LandRentInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int lr_id;
	private String lid;
	private String startTime;
	private String endTime;	
	private String planting;	
	private String userId;
	private Double rentMoney;
	private String chargeDate;
	private int applyDept;
	
	public int getApplyDept() {
		return applyDept;
	}
	public void setApplyDept(int applyDept) {
		this.applyDept = applyDept;
	}
	public int getLr_id() {
		return lr_id;
	}
	public void setLr_id(int lr_id) {
		this.lr_id = lr_id;
	}


	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
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
	public String getPlanting() {
		return planting;
	}
	public void setPlanting(String planting) {
		this.planting = planting;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getRentMoney() {
		return rentMoney;
	}
	public void setRentMoney(Double rentMoney) {
		this.rentMoney = rentMoney;
	}
	public String getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(String chargeDate) {
		this.chargeDate = chargeDate;
	}
	public LandRentInfo() {
		super();
	}
	public LandRentInfo(String lid, String startTime,
			String endTime, String planting, String userId, Double rentMoney,
			String chargeDate, int applyDept) {
		super();		
		this.lid = lid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.planting = planting;
		this.userId = userId;
		this.rentMoney = rentMoney;
		this.chargeDate = chargeDate;
		this.applyDept = applyDept;
	}
	public LandRentInfo(String lid, String startTime, String endTime,
			String planting, String userId, String chargeDate, int applyDept) {
		super();
		this.lid = lid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.planting = planting;
		this.userId = userId;
		this.chargeDate = chargeDate;
		this.applyDept = applyDept;
	}
	
	
	
}
