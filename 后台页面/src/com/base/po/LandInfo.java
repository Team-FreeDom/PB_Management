package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class LandInfo {
	
	@Id	
	private String lid;
	private String lname;
	private int landArea;
	private int afford;
	private String aptPlanting;
	private int buildingArea;
	private int bid;
	private int spareValue;
	private String remark;
	private String aptCollege;
	private String img;
	public String getAptCollege()
	{
		return aptCollege;
	}
	public void setAptCollege(String aptCollege)
	{
		this.aptCollege = aptCollege;
	}	
	public String getImg()
	{
		return img;
	}
	public void setImg(String img)
	{
		this.img = img;
	}
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

	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
	public LandInfo(String img)
	{
		super();
		this.img = img;
	}
	

}
