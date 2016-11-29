package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Land_Planting {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String lid;
	private int bid;
	private String planting;
	
	
	public String getPlanting() {
		return planting;
	}
	public void setPlanting(String planting) {
		this.planting = planting;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	private String aptplanting;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getAptplanting() {
		return aptplanting;
	}
	public void setAptplanting(String aptplanting) {
		this.aptplanting = aptplanting;
	}
	public Land_Planting() {
		super();
	}
		
}
