package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class TemperateSave {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int la_id;
	private String lid;
	private String startTime;
	private String endTime;	
	private String applicantId;
	private String planting;
	private int Status;
	private String startPayTime;
	private String resource;
	private int applyDept;
	
	
	public int getApplyDept() {
		return applyDept;
	}
	public void setApplyDept(int applyDept) {
		this.applyDept = applyDept;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public int getLa_id() {
		return la_id;
	}
	public void setLa_id(int la_id) {
		this.la_id = la_id;
	}


	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
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
	public String getStartPayTime() {
		return startPayTime;
	}
	public void setStartPayTime(String startPayTime) {
		this.startPayTime = startPayTime;
	}

	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public TemperateSave() {
		super();
	}
	
	

}
