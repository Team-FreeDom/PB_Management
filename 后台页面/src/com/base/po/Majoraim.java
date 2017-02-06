package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Majoraim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id; //编号
	private int majorid;//专业编号
	private String aim;//培训目的
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMajorid() {
		return majorid;
	}
	public void setMajorid(int majorid) {
		this.majorid = majorid;
	}
	public String getAim() {
		return aim;
	}
	public void setAim(String aim) {
		this.aim = aim;
	}
	public Majoraim() {
		super();
	}
	
	

}
