package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="landRentInfo")
public class LandRentInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int lr_id;
	private int lid;
	private String startTime;
	private String endTime;	
	private String planting;	
	private int userId;
	private int rentMoney;
	private String chargeDate;
	public int getLr_id() {
		return lr_id;
	}
	public void setLr_id(int lr_id) {
		this.lr_id = lr_id;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRentMoney() {
		return rentMoney;
	}
	public void setRentMoney(int rentMoney) {
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
	
}
