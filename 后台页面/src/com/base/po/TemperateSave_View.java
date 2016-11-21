package com.base.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class TemperateSave_View implements Serializable{
	
	@Id
	private int la_id;
	@Id
	private String startTime;
	@Id
	private String endTime;
	@Id
	private String lid;
	@Id
	private String lname;
	@Id
	private String major_oriented;
	
	@Column(name = "landArea")
	private int landArea;
	
	@Column(name = "afford")
	private int afford;
	
	@Id
	private String aptPlanting;
	
	@Column(name = "buildingArea")
	private int buildingArea;
	
	@Id
	private String bname;
	
	@Column(name = "descp")
	private String descp;
	
	@Column(name = "applicantId")
	private String applicantId;
	
	@Id
	private int tenancy;
	@Id
	private int status;
	@Id
	private String planting;
	@Id
	private String name;
	@Id
	private String college;
	
	@Column(name = "resource")
	private String resource;
	
	@Column(name = "applyDept")
	private int applyDept;
	
	
	
	
	public int getApplyDept() {
		return applyDept;
	}
	public void setApplyDept(int applyDept) {
		this.applyDept = applyDept;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
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

	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public TemperateSave_View() {
		super();
	}
	
	
}
