package com.base.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class LandApply_view implements Serializable{
	
	
	private int la_id;
	
	private String startTime;
	
	private String endTime;
	
	private String lid;
	
	private String lname;	
	
	
	private int landArea;
	
	
	private int afford;
	
	
	private String aptPlanting;
	
	
	private int buildingArea;
	
	
	private String bname;
	
	private String descp;
	
	
	private String applicantId;
	
	
	private int tenancy;
	
	private int status;
	
	private String planting;
	
	private String name;
	
	private String college;
	
	
	private String startPayTime;
	
	
	private String resource;
	
	
	
	
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getStartPayTime() {
		return startPayTime;
	}
	public void setStartPayTime(String startPayTime) {
		this.startPayTime = startPayTime;
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
	public String getPlanting() {
		return planting;
	}
	public void setPlanting(String planting) {
		this.planting = planting;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTenancy() {
		return tenancy;
	}
	public void setTenancy(int tenancy) {
		this.tenancy = tenancy;
	}
	public int getLa_id() {
		return la_id;
	}
	public void setLa_id(int la_id) {
		this.la_id = la_id;
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

	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}

	
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public LandApply_view() {
		super();
	}
	
	
	

}
