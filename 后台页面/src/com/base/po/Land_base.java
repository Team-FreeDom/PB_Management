package com.base.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Entity
public class Land_base implements Serializable{
	
	@Id	
	private int lid;
	@Id
	private String lname;	
	@Id
	private String major_oriented;	
	@Id
	private int landArea;
	@Id
	private int afford;
	@Id
	private String aptPlanting;
	@Id
	private int buildingArea;
	@Id
	private int bid;
	@Id
	private int spareValue;
	@Id
	private String remark;	
	@Id
	private String bname;
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getMajor_oriented() {
		return major_oriented;
	}
	public void setMajor_oriented(String major_oriented) {
		this.major_oriented = major_oriented;
	}
	public int getLandArea() {
		return landArea;
	}
	public void setLandArea(int landArea) {
		this.landArea = landArea;
	}
	public int getAfford() {
		return afford;
	}
	public void setAfford(int afford) {
		this.afford = afford;
	}
	public String getAptPlanting() {
		return aptPlanting;
	}
	public void setAptPlanting(String aptPlanting) {
		this.aptPlanting = aptPlanting;
	}
	public int getBuildingArea() {
		return buildingArea;
	}
	public void setBuildingArea(int buildingArea) {
		this.buildingArea = buildingArea;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getSpareValue() {
		return spareValue;
	}
	public void setSpareValue(int spareValue) {
		this.spareValue = spareValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public Land_base() {
		super();
	}
	
	

}
