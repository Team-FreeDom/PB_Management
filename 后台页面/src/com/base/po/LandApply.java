package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="landApply")
public class LandApply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int la_id;
	private int lid;
	private int rentTime;
	private int applicantId;
	private int Status;
	private String startTime;
	public int getLa_id() {
		return la_id;
	}
	public void setLa_id(int la_id) {
		this.la_id = la_id;
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
	public int getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(int applicantId) {
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
	public LandApply() {
		super();
	}
	

}
