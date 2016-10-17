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
	private int rentTime;
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
	public int getRentTime() {
		return rentTime;
	}
	public void setRentTime(int rentTime) {
		this.rentTime = rentTime;
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
