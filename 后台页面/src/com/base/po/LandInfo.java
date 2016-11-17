package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="landInfo")
public class LandInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int lid;
	private String lname;	
	private String major_oriented;	
	private int landArea;
	private int afford;
	private String aptPlanting;
	private int buildingArea;
	private int bid;
	private int spareValue;
	private String remark;
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAptPlanting() {
		return aptPlanting;
	}
	public void setAptPlanting(String aptPlanting) {
		this.aptPlanting = aptPlanting;
	}
	public int getSpareValue() {
		return spareValue;
	}
	public void setSpareValue(int spareValue) {
		this.spareValue = spareValue;
	}
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
	public LandInfo() {
		super();
	}
	

}
