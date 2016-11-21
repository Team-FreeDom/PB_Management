package com.base.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class CheckView implements Serializable{
	
	@Id
	private int la_id;
	@Id
    private String startTime;
	@Id
    private String endTime;
	@Id
    private String bname;
	@Id
    private int lid;
	@Id
    private String name;
	@Id
    private String college;
	@Id
    private String usercount;
	@Id
    private String planting;
	@Id
	private int status;
	@Id
	private String descStatus;
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
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescStatus() {
		return descStatus;
	}
	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getUsercount() {
		return usercount;
	}
	public void setUsercount(String usercount) {
		this.usercount = usercount;
	}
	public String getPlanting() {
		return planting;
	}
	public void setPlanting(String planting) {
		this.planting = planting;
	}
	public CheckView() {
		super();
	}
	
	
    
}
